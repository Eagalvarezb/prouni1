/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
import Conexion.CreateConection;
import Modelo.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author eagab
 */
public class ProductosDao {
    private final CreateConection connFactory = new CreateConection();
    private List<Productos> productos;
    
    public ProductosDao(){
        this.productos= new ArrayList<>();
    }
    
    public boolean agregarProducto(Productos producto) {
        String sql = "INSERT INTO PRODUCTOS (nombre,precio, descripcion, cantidad) VALUES (?, ?, ?, ?)";
        try (Connection conn = connFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getCantidad());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarProducto(int id_producto){
         String sql = "DELETE FROM PRODUCTOS WHERE id=?";
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id_producto);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean modificarProducto(Productos producto){
       String sql = "UPDATE PRODUCTOS SET nombre=?,precio=?, descripcion=?, cantidad=? WHERE id=?";
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setString(4, producto.getDescripcion());
            ps.setInt(5, producto.getCantidad());
            ps.setInt(6, producto.getId_producto());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Productos> obtenerTodos() {
        List<Productos> productos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTOS";
        
        try (Connection conn = connFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Productos p = new Productos(
                     rs.getInt("id"),
                        rs.getString("nombre"),
                         rs.getInt("cantidad"),
                          rs.getDouble("precio"), 
                        rs.getString("tipo")
                );
                productos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
    
    public Productos obtenerPorId(int id) {
        String sql = "SELECT * FROM PRODUCTOS WHERE id=?";
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Productos(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                         rs.getInt("cantidad"),
                          rs.getDouble("precio"), 
                            rs.getString("tipo")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Productos> obtenerPorTipo(String tipo) {
        List<Productos> productos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTOS WHERE tipo=?";
        
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, tipo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Productos p = new Productos(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                         rs.getInt("cantidad"),
                          rs.getDouble("precio"), 
                            rs.getString("tipo")
                    );
                    productos.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

}
