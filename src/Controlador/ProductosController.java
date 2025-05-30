/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Modelo.Productos;
import DAO.ProductosDao;
import java.util.List;
/**
 *
 * @author eagab
 */
public class ProductosController {
    private final ProductosDao dao = new ProductosDao();
  
    public void agregarProducto(int idProducto, String nombre,String tipo,int cantidad, double precio, String descripcion) {
        Productos producto = new Productos(0, nombre,cantidad, precio, tipo);
        dao.agregarProducto(producto);
    }

    public void eliminarProducto(int idProducto) {
        dao.eliminarProducto(idProducto);
    }

    public void modificarProducto(int idProducto, String nombre,String tipo, int cantidad, double precio, String descripcion) {
        Productos modificarProducto = new Productos(idProducto, nombre, cantidad, precio, tipo);
        dao.modificarProducto(modificarProducto);
    }
    
    public List<Productos> mostrarTodos() {
        return dao.obtenerTodos();
    }
     public Productos obtenerPorId(int id) {
        return dao.obtenerPorId(id);
    }
    
    // Métodos específicos para tipos de productos
    public List<Productos> obtenerPersonales() {
        return dao.obtenerPorTipo("PERSONAL");
    }
    
    public List<Productos> obtenerCombos() {
        return dao.obtenerPorTipo("COMBO");
    }
    
    public List<Productos> obtenerExtras() {
        return dao.obtenerPorTipo("EXTRA");
    }
    
    
}
