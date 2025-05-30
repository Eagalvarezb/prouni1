/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Conexion.CreateConection;
import Modelo.Proveedor;

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
public class ProveedorDao {
    private final CreateConection connFactory = new CreateConection();
//   (Insumos de Limpieza y Mantenimiento,Insumos de Empaque y Servicio,Insumos de Cocina (Utensilios y Equipo),Insumos Alimenticios (Materia Prima))
 public List<Proveedor> obtenerTodos() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM public.proveedor";
       
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proveedor pro = new Proveedor(
                        rs.getString("nombre_pro"),
                        rs.getInt("nit"),
                        rs.getString("tipo_insumo"),
                        rs.getString("contacto"),
                        rs.getString("direccion"),
                        rs.getString("telefono_emp"),
                        rs.getString("telefono_con")
                );
                lista.add(pro);
            }
             ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
//búsqueda de empleados
 
 public Proveedor obtenerPorNIT(int nit) {

        String sql = "SELECT * FROM public.proveedor Where nit=?";
        Proveedor pro= null;
        
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setInt(1,nit);
        try(ResultSet rs = ps.executeQuery()){
            if(rs.next()) {
                pro=new Proveedor(
                rs.getString("nombre_pro"),
                rs.getInt("nit"),
                rs.getString("tipo_insumo"),
                rs.getString("contacto"),
                rs.getString("direccion"),
                rs.getString("telefono_emp"),
                rs.getString("telefono_con")
                );
            }
        }
             ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pro;

    }
    public boolean guardar(Proveedor pro) {
    if (!ProveedorDao.Validador.validarLongitudNIT(String.valueOf(pro.getNit())) || 
       !ProveedorDao.Validador.validarTelefono(pro.getTelefono_emp(), pro.getTelefono_con()) || 
       !ProveedorDao.Validador.validarDireccion(pro.getDireccion())) {
        return false;
    }
    
        
        String sql = "INSERT INTO public.proveedor (nombre_pro, nit,tipo_insumo, contacto, direccion,telefono_emp, telefono_con) VALUES (?, ?, ?, ?, ?, "
                + "?, ?)";
                 
        try (Connection conn = connFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, pro.getNombre_pro());
            ps.setInt(2, pro.getNit());
            ps.setString(3, pro.getTipo_insumo());
            ps.setString(4, pro.getContacto());
            ps.setString(5, pro.getDireccion());
            ps.setString(6, pro.getTelefono_emp());
            ps.setString(7,pro.getTelefono_con());
           
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
        
    public  static boolean validarLongitudNIT(String nit) {
    return nit != null && nit.matches("\\d{9}");
    }

    public static boolean validarTelefono(String telefono_emp, String telefono_con) {
        if (telefono_emp == null) return false;
            String numeroLimpioPro = telefono_emp.replaceAll("[\\s\\-()+]", "");
            String regexPro = "^(\\+?502|502)?[2-8]\\d{7}$";  // Guatemalan phone format
            Pattern patternPro = Pattern.compile(regexPro);
             Matcher matcherPro = patternPro.matcher(numeroLimpioPro);
    boolean ProValido = matcherPro.matches();
    if (telefono_con != null && !telefono_con.isEmpty()) {
        String numeroLimpioCon = telefono_con.replaceAll("[\\s\\-()+]", "");
        String regexCon = "^(\\+?\\d{1,3})?\\d{8,15}$";  
        Pattern patternCon = Pattern.compile(regexCon);
        Matcher matcherCon = patternCon.matcher(numeroLimpioCon);
        return ProValido && matcherCon.matches();
    }
    
    return ProValido;
    }
      
    public static boolean validarDireccion(String direccion) {
        String regex = "^[a-zA-Z0-9\\s#\\-.,áéíóúÁÉÍÓÚñÑ]{5,100}$";
        return direccion.matches(regex);
    }
}
    
    public boolean actualizar(Proveedor pro) {
    if (!ProveedorDao.Validador.validarTelefono(pro.getTelefono_emp(), pro.getTelefono_con()) || 
        !ProveedorDao.Validador.validarDireccion(pro.getDireccion())) {
        return false;
    }
    if (pro.getNombre_pro() != null) {
        pro.setNombre_pro(pro.getNombre_pro());
    }
    if (pro.getContacto() != null) {
        pro.setContacto(pro.getContacto());
    }
    if (pro.getDireccion()!= null) {
        pro.setDireccion(pro.getDireccion());
    }
    if (pro.getTipo_insumo()!= null) {
        pro.setTipo_insumo(pro.getTipo_insumo());
    }
    if (pro.getTelefono_emp()!= null) {
        pro.setTelefono_emp(pro.getTelefono_emp());
    }
    if (pro.getTelefono_con()!= null) {
        pro.setTelefono_con(pro.getTelefono_con());
    }
    
       
        String sql = "UPDATE public.proveedor SET tipo_insumo=?, contacto=?, direccion=?, telefono_emp=?, telefono_con=? WHERE nit=?";

        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pro.getTipo_insumo());
            ps.setString(2, pro.getContacto());
            ps.setString(3, pro.getDireccion());
            ps.setString(4, pro.getTelefono_emp());
            ps.setString(5, pro.getTelefono_con());
            ps.setInt(6, pro.getNit());
      
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminar(int nit) {
        String sql = "DELETE FROM public.proveedor WHERE nit=?";

        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, nit);
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
