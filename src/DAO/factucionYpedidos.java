package DAO;


import Conexion.CreateConection;
import Modelo.factura;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author asist
 */
public class factucionYpedidos {
    private final CreateConection connFactory = new CreateConection();

    public List<factura> obtenerTodos(){
        List<factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM PEDIDOS";
        
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                factura fact = new factura(
                rs.getInt("id_factura"),
                rs.getString("cliente"),
                rs.getDate("fecha_actual"),
                rs.getString("productos_consumidos"),
                rs.getString("usuario_atendio"),
                rs.getDouble("costo_total")
                );
              lista.add(fact);
            }
            ps.close();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    
    public boolean actualizar(factura fact){
        String sql = "UPDATE PEDIDOS SET cliente = ?, fecha_actual = ?, productos_consumidos = ?, usuario_atendio = ?, costo_total = ? where id_factura = ?";
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
             ps.setString(1, fact.getCliente());
             ps.setDate(2, (Date) fact.getFecha_actual());
             ps.setString(3, fact.getProductos_consumidos());
             ps.setString(4, fact.getUsuario_atendio());
             ps.setDouble(5, fact.getCosto_total());
             ps.setInt(6, fact.getId_factura());
        
             ps.executeUpdate();
             
             ps.close();
             conn.close();
             return true;
        
        } catch (SQLException e){
                e.printStackTrace();
            }
        return false;
    }
    
    public boolean agregarFactura(factura pedidos){
        String sql = "INSERT INTO PEDIDOS (cliente, fecha_actual, productos_consumidos, usuario_atendio, costo_total) VALUES (?,?,?,?,?)";
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, pedidos.getCliente());
            ps.setDate(2, (Date) pedidos.getFecha_actual());
            ps.setString(3, pedidos.getProductos_consumidos());
            ps.setString(4, pedidos.getUsuario_atendio());
            ps.setDouble(5, pedidos.getCosto_total());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean eliminarFactura(int id_factura){
        String sql = "DELETE FROM PEDIDOS WHERE id_factura=?";
        
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id_factura);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /* public void actualizar(prograproyect.MODELO.factura facts) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void agregarFactura(prograproyect.MODELO.factura facts) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}