/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Conexion.CreateConection;
import Modelo.Cliente;
import Modelo.Empleado;

import java.sql.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author eagab
 */
public class ClienteDao {
  //Registro de clientes frecuentes
     private final CreateConection connFactory = new CreateConection();
    
 public List<Cliente> obtenerTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
       
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cli = new Cliente(
                        rs.getInt("DPI"),
                        rs.getString("nombre"),
                       // rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("email")   
                );
                cli.setPuntos(rs.getInt("puntos"));
                cli.setFecha_res(rs.getDate("fecha_res")!= null ? rs.getDate("fecha_res").toLocalDate() : null);
                cli.setNivel((rs.getString("nivel")));
                lista.add(cli);
            }
             ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
 
 public Cliente obtenerPorDPI(int dpi) {

        String sql = "SELECT * FROM cliente Where dpi=?";
        Cliente  cli= null;
        
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             ps.setInt(1,dpi);
             
            while (rs.next()) {
               cli =new Cliente(
                    rs.getInt("dpi"),
                    rs.getString("nombre"),
                    //rs.getString("apellido");
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("email")
                   );
               cli.setPuntos(rs.getInt("puntos"));
                cli.setFecha_res(rs.getDate("fecha_res")!= null ? rs.getDate("fecha_res").toLocalDate() : null);
                cli.setNivel((rs.getString("nivel")));
            }
             ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cli;

    }
 
    public boolean guardar(Cliente cli) {
        
        if (!Validador.validarTelefono(cli.getTelefono()) || !Validador.validarDireccion(cli.getDireccion()) ||!Validador.validarEmail(cli.getEmail())) {
            return false;
        }
     
        String sql = "INSERT INTO cliente (dpi, nombre, telefono, direccion, email, puntos, fecha_res, nivel) VALUES (?, ?, ?, ?, ?,?,?,?)";
             
        try (Connection conn = connFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cli.getDpi());
            ps.setString(2, cli.getNombre());
            //ps.setString(3, emp.getApellido());
            ps.setString(3, cli.getTelefono());
            ps.setString(4, cli.getDireccion());
            ps.setString(5, cli.getEmail());
            ps.setInt(6, cli.getPuntos());
            ps.setDate(7, java.sql.Date.valueOf(cli.getFecha_res()));
            ps.setString(8,cli.getNivel());

            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return true;
                       
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public class Validador {
    
    public  static boolean validarLongitudDPI(String dpi) {
    return dpi != null && dpi.matches("\\d{13}");
    }

    public static boolean validarTelefono(String telefono) {
       
        String regex = "^(\\+?502|502)?[2-8]\\d{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telefono);
        return matcher.matches();
    }
    public static boolean validarEmail(String email) {
            if (email == null) return false;
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            return email.matches(regex);
        }
   
    public static boolean validarDireccion(String direccion) {
        String regex = "^[a-zA-Z0-9\\s#\\-.,áéíóúÁÉÍÓÚñÑ]{5,100}$";
        return direccion.matches(regex);
    }
}
    
public boolean actualizarPuntos(int dpi, int puntos) {
        String sql = "UPDATE cliente SET puntos=? WHERE dpi=?";
        
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, puntos);
            ps.setInt(2, dpi);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
}

 public boolean actualizarNivel(int dpi, String nivel) {
        String sql = "UPDATE cliente SET nivel=? WHERE dpi=?";
        
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, nivel);
            ps.setInt(2, dpi);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
}
 
private String determinarNivel(int puntos) {
        if (puntos >= 1000) return "Oro";
        if (puntos >= 500) return "Plata";
        return "Bronce";
}

public boolean registrodeClientesfrecuentes(int dpi, double montoCompra) {
        Cliente cliente = obtenerPorDPI(dpi);
        if (cliente == null) return false;
       
        int puntosGanados = (int) (montoCompra / 10);
        int nuevosPuntos = cliente.getPuntos() + puntosGanados;
        
        String nuevoNivel = determinarNivel(nuevosPuntos);
      
        try {
            boolean puntosActualizados = actualizarPuntos(dpi, nuevosPuntos);
            boolean nivelActualizado = actualizarNivel(dpi, nuevoNivel);
            return puntosActualizados && nivelActualizado;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
}
    
 
 public boolean actualizar(Cliente cli) {
     Cliente cliente = obtenerPorDPI(cli.getDpi());
     if (!Validador.validarTelefono(cli.getTelefono()) || !Validador.validarDireccion(cli.getDireccion()) ||!Validador.validarEmail(cli.getEmail())) {
            return false;
     }
        
    if (cli.getNombre() != null) {
        cliente.setNombre(cli.getNombre());
    }
    /*if (emp.getApellido() != null) {
        empleadoe.setApellido(emp.getApellido());
    }*/
    if (cli.getTelefono() != null) {
        cliente.setTelefono(cli.getTelefono());
    }
    if (cli.getDireccion() != null) {
        cliente.setDireccion(cli.getDireccion());
    }
    if (cli.getEmail() != null) {
        cliente.setEmail(cli.getEmail());
    }
       
    String sql = "UPDATE cliente SET telefono=?, direccion=?, email=? WHERE dpi=?";
//    //teléfono, direccion, email
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            //ps.setString(1, cli.getApellido());
            ps.setString(1, cli.getTelefono());
            ps.setString(2, cli.getDireccion());
            ps.setString(3,cli.getEmail());
            ps.setInt(4, cli.getDpi());
      
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
