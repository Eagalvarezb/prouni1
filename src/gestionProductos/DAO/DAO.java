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
    
    public List<Producto> obtenerTodos(){
            List<Producto> lista = new ArrayList<>();
            }
}
