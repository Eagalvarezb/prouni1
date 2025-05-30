/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.PedidoDAO;
import Modelo.Pedido;
import Modelo.Productos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eagab
 */
public class PedidosController {
    private final PedidoDAO dao = new PedidoDAO();
    
    private List<Productos> productosSeleccionados = new ArrayList<>();
    private double total = 0.0;
    
    public void agregarPedido(Pedido pedido) {
        dao.agregar(pedido);
    }
    
    public void agregarProducto(Productos producto) {
        productosSeleccionados.add(producto);
        total += producto.getPrecio() * producto.getCantidad();
    }
    
    public List<Productos> getProductosSeleccionados() {
        return new ArrayList<>(productosSeleccionados);
    }
    
    public double getTotal() {
        return total;
    }
    
    public void limpiarPedido() {
        productosSeleccionados.clear();
        total = 0.0;
    }
    
    
    public Pedido agregarPedido(String cliente, List<Productos> productos, String usuario) {
        
     double total = calcularTotal(productos);
     
    StringBuilder productosStr = new StringBuilder();
    for (Productos p : productos) {
        productosStr.append(p.getNombre())
                   .append(" (x").append(p.getCantidad()).append(")")
                   .append(" - $").append(p.getPrecio())
                   .append("\n");
    }
    
    Pedido nuevoPedido = new Pedido();
    nuevoPedido.setCliente(cliente);
    nuevoPedido.setProductos(productosStr.toString());
    nuevoPedido.setTotal(total);
    nuevoPedido.setUsuario(usuario);
    nuevoPedido.setFecha(new java.util.Date()); // Fecha actual
    
    // Guardar en la base de datos
    dao.agregar(nuevoPedido);
    
    return nuevoPedido;
    }
    public boolean agregarProductosAPedido(int idPedido, List<Productos> nuevosProductos) {
    // Obtener el pedido existente
    Pedido pedido = dao.obtenerPorId(idPedido);
    
    if (pedido == null) {
        return false;
    }
    
    // Reconstruir la lista de productos (asumiendo que están en un formato parseable)
    String productosActuales = pedido.getProductos();
    
    // Agregar los nuevos productos
    StringBuilder productosStr = new StringBuilder(productosActuales);
    for (Productos p : nuevosProductos) {
        productosStr.append("\n")
                   .append(p.getNombre())
                   .append(" (x").append(p.getCantidad()).append(")")
                   .append(" - $").append(p.getPrecio());
    }
    
    // Actualizar el pedido
    pedido.setProductos(productosStr.toString());
    pedido.setTotal(pedido.getTotal() + calcularTotal(nuevosProductos));
    
    return dao.actualizar(pedido);
}
    public void actualizarPedido(Pedido pedido) {
        dao.actualizar(pedido);
    }
    
    public void eliminarPedido(int id) {
        dao.eliminar(id);
    }
    
    public List<Pedido> obtenerTodos() {
        return dao.obtenerTodos();
    }
    
    public Pedido obtenerPorId(int id) {
        return dao.obtenerPorId(id);
    }
    
    public double calcularTotal(List<Productos> productos) {
        return productos.stream().mapToDouble(Productos::getPrecio).sum();
    }

    public void agregarPedido(Productos producto) {
  
    }
}
