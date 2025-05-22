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
    
    public void insertarPedido (String nombre, int cantidad, double precio){
        Modelo producto = new Modelo(0, nombre, cantidad, precio);
        dao.insertar(producto);
    }
    
    public void actualizarPedido (int id, String nombre, int cantidad, double precio){
        Modelo producto = new Modelo(0, nombre, cantidad, precio);
        dao.actualizar(producto);
    }
}
