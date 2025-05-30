package DAO;


import Modelo.Pedido;
import Conexion.CreateConection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private final CreateConection connFactory = new CreateConection();
    
    public boolean agregar(Pedido pedido) {
        String sql = "INSERT INTO PEDIDOS (cliente, productos, total, fecha, usuario) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, pedido.getCliente());
            ps.setString(2, pedido.getProductos());
            ps.setDouble(3, pedido.getTotal());
            ps.setDate(4, new java.sql.Date(pedido.getFecha().getTime()));
            ps.setString(5, pedido.getUsuario());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean actualizar(Pedido pedido) {
        String sql = "UPDATE PEDIDOS SET cliente=?, productos=?, total=?, fecha=?, usuario=? WHERE id=?";
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, pedido.getCliente());
            ps.setString(2, pedido.getProductos());
            ps.setDouble(3, pedido.getTotal());
            ps.setDate(4, new java.sql.Date(pedido.getFecha().getTime()));
            ps.setString(5, pedido.getUsuario());
            ps.setInt(6, pedido.getId());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminar(int id) {
        String sql = "DELETE FROM PEDIDOS WHERE id=?";
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Pedido> obtenerTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM PEDIDOS";
        
        try (Connection conn = connFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pedido p = new Pedido(
                    rs.getInt("id"),
                    rs.getString("cliente"),
                    rs.getString("productos"),
                    rs.getDouble("total"),
                    rs.getDate("fecha"),
                    rs.getString("usuario")
                );
                pedidos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }
    
    public Pedido obtenerPorId(int id) {
        String sql = "SELECT * FROM PEDIDOS WHERE id=?";
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Pedido(
                        rs.getInt("id"),
                        rs.getString("cliente"),
                        rs.getString("productos"),
                        rs.getDouble("total"),
                        rs.getDate("fecha"),
                        rs.getString("usuario")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}