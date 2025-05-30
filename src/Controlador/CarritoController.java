package Controlador;


import Modelo.Productos;
import java.util.ArrayList;
import java.util.List;

public class CarritoController {
    private final List<Productos> items = new ArrayList<>();
    
    public void agregarProducto(Productos producto) {
        items.add(producto);
    }
    
    public void eliminarProducto(int id) {
        items.removeIf(p -> p.getId_producto() == id);
    }
    
    public void vaciarCarrito() {
        items.clear();
    }
    
    public List<Productos> obtenerItems() {
        return new ArrayList<>(items);
    }
    
    public double calcularTotal() {
        return items.stream().mapToDouble(Productos::getPrecio).sum();
    }
    
    public int cantidadItems() {
        return items.size();
    }
}