/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionProductos.DAO;
import conexion.CreateConection;
import gestionProductosModelo.Modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jimem
 */
public class DAO {
    private final CreateConection connFactory = new CreateConection();
    
    public List<Modelo> obtenerTodos(){
            List<Modelo> lista = new ArrayList<>();
            String sql = "SELECT FROM producto";
            try(Connection conn = connFactory.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Modelo producto = new Modelo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio"),
                    rs.getString("categoria")
                    );
                    lista.add(producto);
                 }
                ps.close();
                conn.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            return lista;
    }
    public boolean insertar(Modelo producto){
        String sql = "INSERT INTO producto (nombre, descripcion, cantidad, precio, categoria) VALUES (?,?,?,?,?)";
        
        try(Connection conn = connFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, producto.getProduct_name());
            ps.setString(2, producto.getDescription());
            ps.setInt(3, producto.getCantidad());
            ps.setDouble(4, producto.getProduct_price());
            ps.setString(5, producto.getCategory());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean actualizar(Modelo producto){
       String sql = "UPDATE producto SET nombre=?, descripcion=?, cantidad=?, precio=?, categoria=? WHERE id=?";
        
        try(Connection conn = connFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, producto.getProduct_name());
            ps.setString(2, producto.getDescription());
            ps.setInt(3, producto.getCantidad());
            ps.setDouble(4, producto.getProduct_price());
            ps.setString(5, producto.getCategory());
            ps.setInt(6, producto.getId_product());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false; 
    }
}
