/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Conexion.CreateConection;
import Modelo.Empleado;
import Modelo.UsuarioSistema;

import java.sql.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eagab
 */
public class UsuarioSistemaDao {
    private final CreateConection connFactory = new CreateConection();
    private static final int MAX_INTENTOS = 3;
    private static final ConcurrentHashMap<String, AtomicInteger> intentosFallidos = new ConcurrentHashMap<>();

public boolean existeUsuario(String username) {
    String sql = "SELECT * FROM public.usuarios WHERE username = ?";
    try (Connection conn = connFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, username);
        
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); 
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al verificar usuario", e);
    }
}

public boolean crearUsuario(Empleado emp, UsuarioSistema user) {
        if (!validarPassword(user.getPassword())) {
            throw new SecurityException("La contraseña no cumple con los requisitos de seguridad");
        }
        
        String sql = "INSERT INTO public.usuarios (nombre, apellido,username, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = connFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            String username = generarUsuarioUnico(emp);
            user.setUsername(username);//username generado
            
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApellido());
            ps.setString(3,username);
            ps.setString(4, user.getPassword());
            

            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return true;
           
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
 
 public String generarUsuarioUnico(Empleado emp) {
    String baseUsuario = (emp.getNombre().substring(0, 1) + emp.getApellido()).toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]", "");
    return generarVarianteUnica(baseUsuario); 
}

private String generarVarianteUnica(String base) {
    String username = base;
    int contador = 1;
        
    while (existeUsuario(username)) {
    username = base + contador;
    contador++;
      if (contador > 100) {
        throw new IllegalStateException("No se pudo generar un usuario único después de 100 intentos");
      }
    }
       
    return username;
}

 public boolean actualizar(UsuarioSistema user) {
        String sql = "UPDATE public.usuarios SET password=? WHERE username=?";

        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getUsername());
      
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
 
public boolean verificarCredenciales(String username, String password) {
   if (intentosFallidos.getOrDefault(username, new AtomicInteger(0)).get() >= MAX_INTENTOS) {
            throw new RuntimeException("Cuenta bloqueada por demasiados intentos fallidos");
        }
    String sql = "SELECT password FROM public.usuarios WHERE username = ?";
    
    try (Connection conn = connFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, username);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String passwordDB = rs.getString("password");
                return password.equals(passwordDB);
            }
            return false;
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al verificar credenciales", e);
    }
}

 private void incrementarIntentosFallidos(String username) {
        intentosFallidos.compute(username, (k, v) -> {
            if (v == null) return new AtomicInteger(1);
            v.incrementAndGet();
            return v;
        });
    }
 
 public boolean validarPassword(String password) {
   
    if (password == null || password.length() < 8) {
        return false;
    }
    
    boolean tieneMayuscula = false;
    boolean tieneMinuscula = false;
    boolean tieneNumero = false;
    boolean tieneEspecial = false;
    
    for (char c : password.toCharArray()) {
        if (Character.isUpperCase(c)) {
            tieneMayuscula = true;
        } else if (Character.isLowerCase(c)) {
            tieneMinuscula = true;
        } else if (Character.isDigit(c)) {
            tieneNumero = true;
        } else {
            tieneEspecial = true;
        }
         
        if (tieneMayuscula && tieneMinuscula && tieneNumero && tieneEspecial) {
            break; // Todo cumplido
        }
    }
    
    return tieneMayuscula && tieneMinuscula && tieneNumero && tieneEspecial;
}
 
 public boolean eliminar(String username) {
        String sql = "DELETE FROM public.usuarios WHERE username=?";

        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            
            return true;
            
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}


