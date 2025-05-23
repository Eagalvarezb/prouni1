/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GESTIONPRODUCTOSCONTROL;

import gestionProductos.DAO.DAO;
import gestionProductosModelo.Modelo;
import java.util.List;


/**
 *
 * @author jimem
 */
public class CONTROLADOR {
    private final gestionProductos.DAO.DAO dao = new gestionProductos.DAO.DAO();
    
    public List<Modelo> obtenerProductos(){
        return dao.obtenerTodos();
    }
    
    public void insertarPedido (String product_name, String description, int cantidad, double product_price, String category){
        Modelo producto = new Modelo(0, product_name, description, cantidad, product_price, category);
        dao.insertar(producto);
    }
    
    public void actualizarPedido (int id_product, String product_name, String description, int cantidad, double product_price, String category){
        Modelo producto = new Modelo(id_product, product_name, description, cantidad, product_price, category);
        dao.actualizar(producto);
    }
}
