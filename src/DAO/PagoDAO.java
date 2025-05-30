package DAO;


import Modelo.Pago;
import Conexion.CreateConection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {
    private final CreateConection connFactory = new CreateConection();
    
    public boolean agregar(Pago pago) {
        String sql = "INSERT INTO PAGOS (id_pedido, metodo, monto, fecha, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, pago.getIdPedido());
            ps.setString(2, pago.getMetodo());
            ps.setDouble(3, pago.getMonto());
            ps.setDate(4, new java.sql.Date(pago.getFecha().getTime()));
            ps.setString(5, pago.getEstado());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean actualizarEstado(int id, String estado) {
        String sql = "UPDATE PAGOS SET estado=? WHERE id=?";
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, estado);
            ps.setInt(2, id);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Pago> obtenerTodos() {
        List<Pago> pagos = new ArrayList<>();
        String sql = "SELECT * FROM PAGOS";
        
        try (Connection conn = connFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pago p = new Pago(
                    rs.getInt("id"),
                    rs.getInt("id_pedido"),
                    rs.getString("metodo"),
                    rs.getDouble("monto"),
                    rs.getDate("fecha"),
                    rs.getString("estado")
                );
                pagos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagos;
    }
    
    public Pago obtenerPorId(int id) {
        String sql = "SELECT * FROM PAGOS WHERE id=?";
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Pago(
                        rs.getInt("id"),
                        rs.getInt("id_pedido"),
                        rs.getString("metodo"),
                        rs.getDouble("monto"),
                        rs.getDate("fecha"),
                        rs.getString("estado")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}