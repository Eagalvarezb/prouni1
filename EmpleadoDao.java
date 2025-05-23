/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Conexion.CreateConection;
import Modelo.Empleado;

import java.sql.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author eagab
 */
public class EmpleadoDao {
    private final CreateConection connFactory = new CreateConection();
    
 public List<Empleado> obtenerTodos() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado";
       
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado emp = new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("puesto"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("estado"),
                        rs.getString("fecha_in"),
                        rs.getDouble("salario"),
                        rs.getString("email")
                );
                lista.add(emp);
            }
             ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
//búsqueda de empleados
 
 public Empleado obtenerPorID(int id) {

        String sql = "SELECT * FROM empleado Where Id=?";
        Empleado emp= null;
        
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             ps.setInt(1,id);

            while (rs.next()) {
               
                        rs.getInt("id");
                        rs.getString("nombre");
                        rs.getString("apellido");
                        rs.getString("puesto");
                        rs.getString("telefono");
                        rs.getString("direccion");
                        rs.getString("estado");
                        rs.getString("fecha_in");
                        rs.getDouble("salario");
                        rs.getString("email");
            }
             ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;

    }
    public boolean guardar(Empleado emp) {
        if (!EmpleadoDao.Validador.validarTelefono(emp.getTelefono()) || !EmpleadoDao.Validador.validarDireccion(emp.getDireccion()) ||!EmpleadoDao.Validador.validarEmail(emp.getEmail())) {
            return false;
        }
        
        String sql = "INSERT INTO empleado (nombre, apellido,puesto, telefono, direccion,estado, fecha_in, salario, email) VALUES (?, ?, ?, ?, ?, "
                + "?, ?, ?, ?)";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String status;
        if("1".equals(emp.getEstado())){
            status="Activo";
        }else{
            status="Inactivo";
        }
         
        try (Connection conn = connFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            java.util.Date date =sdf.parse(emp.getFecha_in());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApellido());
            ps.setString(3, emp.getPuesto());
            ps.setString(4, emp.getTelefono());
            ps.setString(5, emp.getDireccion());
            ps.setString(6, status);
            ps.setDate(7,sqlDate);
            ps.setDouble(8, emp.getSalario());
            ps.setString(9, emp.getEmail());

            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return true;
           
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(EmpleadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public class Validador {
    // Validación de teléfono (México como ejemplo)
    public static boolean validarTelefono(String telefono) {
        String numerolimpio = telefono.replaceAll("[\\s\\-()+]","");
        String regex = "^(\\+?520|520)?(2-8)\\d{7}$";
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
    
    public boolean actualizar(Empleado emp) {
        Empleado empleadoe = obtenerPorID(emp.getId());
        if (!EmpleadoDao.Validador.validarTelefono(emp.getTelefono()) || !EmpleadoDao.Validador.validarDireccion(emp.getDireccion()) ||!EmpleadoDao.Validador.validarEmail(emp.getEmail())) {
            return false;
        }
    if (emp.getNombre() != null) {
        empleadoe.setNombre(emp.getNombre());
    }
    if (emp.getApellido() != null) {
        empleadoe.setApellido(emp.getApellido());
    }
    if (emp.getPuesto() != null) {
        empleadoe.setPuesto(emp.getPuesto());
    }
    if (emp.getTelefono() != null) {
        empleadoe.setTelefono(emp.getTelefono());
    }
    if (emp.getDireccion() != null) {
        empleadoe.setDireccion(emp.getDireccion());
    }
    if (emp.getEstado() != null) {
        empleadoe.setEstado(emp.getEstado());
    }
    if (emp.getFecha_in() != null) {
         empleadoe.setFecha_in(emp.getFecha_in());
    }
    if (emp.getSalario() != 0.0) {
        empleadoe.setSalario(emp.getSalario());
    }
    if (emp.getEmail() != null) {
        empleadoe.setEmail(emp.getEmail());
    }
       
        String sql = "UPDATE empleado SET apellido=?, puesto=?, telefono=?, direccion=?, estado=?, salario=?, email=? WHERE id=?";

        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getApellido());
            ps.setString(2, emp.getPuesto());
            ps.setString(3, emp.getTelefono());
            ps.setString(4, emp.getDireccion());
            ps.setString(5, emp.getEstado());
            ps.setDouble(6, emp.getSalario());
            ps.setString(7, emp.getEmail());
            ps.setInt(8, emp.getId());
      
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM empleado WHERE id=?";

        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
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
