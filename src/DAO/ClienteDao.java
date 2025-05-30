/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Conexion.CreateConection;
import Modelo.Cliente;

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
        String sql = "SELECT * FROM public.cliente";
       
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cli = new Cliente(
                        rs.getLong("DPI"),
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
 
 public Cliente obtenerPorDPI(long dpi) {

        String sql = "SELECT * FROM public.cliente Where dpi=?";
        Cliente  cli= null;
        
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setLong(1,dpi);
              
            try(ResultSet re = ps.executeQuery()){
                if(re.next()){
                 cli =new Cliente(
                    re.getLong("dpi"),
                    re.getString("nombre"),
                    //re.getString("apellido");
                    re.getString("telefono"),
                    re.getString("direccion"),
                    re.getString("email")
                   );
               cli.setPuntos(re.getInt("puntos"));
                cli.setFecha_res(re.getDate("fecha_res")!= null ? re.getDate("fecha_res").toLocalDate() : null);
                cli.setNivel((re.getString("nivel")));
                }
            }

             ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cli;

    }
 
    public boolean guardar(Cliente cli) {
    if (
        !Validador.validarTelefono(cli.getTelefono()) || 
        !Validador.validarDireccion(cli.getDireccion()) || 
        !Validador.validarEmail(cli.getEmail())) {
        return false;
    }
    if (cli.getFecha_res() == null) {
        return false;
    }
     
        String sql = "INSERT INTO public.cliente (dpi, nombre, telefono, direccion, email, puntos, fecha_res, nivel) VALUES (?, ?, ?, ?, ?,?,?,?)";
             
        try (Connection conn = connFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, cli.getDpi());
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
    
public boolean actualizarPuntos(long dpi, int puntos) {
        String sql = "UPDATE public.cliente SET puntos=? WHERE dpi=?";
        
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, puntos);
            ps.setLong(2, dpi);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
}

 public boolean actualizarNivel(Long dpi, String nivel) {
        String sql = "UPDATE public.cliente SET nivel=? WHERE dpi=?";
        
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, nivel);
            ps.setLong(2, dpi);
            
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

public boolean registrodeClientesfrecuentes(long dpi, double montoCompra) {
    Connection conn = null;
    try {
        conn = connFactory.getConnection();
        conn.setAutoCommit(false); 
        
        Cliente cliente = obtenerPorDPI(dpi);
        if (cliente == null) return false;
       
        int puntosGanados = (int) (montoCompra / 10);
        int nuevosPuntos = cliente.getPuntos() + puntosGanados;
        String nuevoNivel = determinarNivel(nuevosPuntos);
        
        String sqlPuntos = "UPDATE public.cliente SET puntos=? WHERE dpi=?";
        try (PreparedStatement ps = conn.prepareStatement(sqlPuntos)) {
            ps.setInt(1, nuevosPuntos);
            ps.setLong(2, dpi);
            ps.executeUpdate();
        }
        
        String sqlNivel = "UPDATE public.cliente SET nivel=? WHERE dpi=?";
        try (PreparedStatement ps = conn.prepareStatement(sqlNivel)) {
            ps.setString(1, nuevoNivel);
            ps.setLong(2, dpi);
            ps.executeUpdate();
        }
        
        conn.commit();
        return true;
    } catch (SQLException e) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace();
        return false;
    } finally {
        if (conn != null) {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
 
 public boolean actualizar(Cliente cli) {
    // Validar solo los campos que se están actualizando
    if (cli.getTelefono() != null && !Validador.validarTelefono(cli.getTelefono())) {
        return false;
    }
    if (cli.getDireccion() != null && !Validador.validarDireccion(cli.getDireccion())) {
        return false;
    }
    if (cli.getEmail() != null && !Validador.validarEmail(cli.getEmail())) {
        return false;
    }
        
    String sql = "UPDATE public.cliente SET nombre=COALESCE(?, nombre), telefono=COALESCE(?, telefono), " +
                 "direccion=COALESCE(?, direccion), email=COALESCE(?, email) WHERE dpi=?";
    
    try (Connection conn = connFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        // Usamos setObject para manejar posibles valores nulos
        ps.setObject(1, cli.getNombre(), Types.VARCHAR);
        ps.setObject(2, cli.getTelefono(), Types.VARCHAR);
        ps.setObject(3, cli.getDireccion(), Types.VARCHAR);
        ps.setObject(4, cli.getEmail(), Types.VARCHAR);
        ps.setLong(5, cli.getDpi());
  
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}
