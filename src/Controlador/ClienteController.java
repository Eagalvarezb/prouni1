/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.ClienteDao;
import Modelo.Cliente;  

import java.util.List;

/**
 *
 * @author eagab
 */
public class ClienteController {
    private final ClienteDao dao = new ClienteDao();
  
    public List<Cliente> obtenerCliente(){
        return dao.obtenerTodos();
    }
    
    public Cliente obtenerPorID(long dpi){
        try{
       
        return dao.obtenerPorDPI(dpi);
        }catch(Exception e){
            System.out.println("Erro al buscar Cliente: "+e.getMessage());  
            return null;
        }
    }
    
    public void agregarCliente(long dpi, String nombre, String telefono, String direccion, String email){
        Cliente cli = new Cliente(dpi, nombre, telefono, direccion, email);
        
        if(cli.getNombre() == null) {
            System.out.println("Nombre es requerido");            
        }
        
        cli.setPuntos(0);
        cli.setNivel("Bronce");
        cli.setFecha_res(java.time.LocalDate.now());
        dao.guardar(cli);
    }
    
    public void actualizarCliente(long dpi, String nombre, String telefono, String direccion, String email) {
    Cliente cli = dao.obtenerPorDPI(dpi);
    if (cli == null) {
        System.err.println("Error: Cliente no encontrado");
        return;
    }
    
    // Actualizar solo los campos que no son nulos o vacíos
    if (nombre != null && !nombre.isEmpty()) {
        cli.setNombre(nombre);
    }
    if (telefono != null && !telefono.isEmpty()) {
        cli.setTelefono(telefono);
    }
    if (direccion != null && !direccion.isEmpty()) {
        cli.setDireccion(direccion);
    }
    if (email != null && !email.isEmpty()) {
        cli.setEmail(email);
    }
    
    boolean success = dao.actualizar(cli);
    if (!success) {
        System.err.println("Error al actualizar cliente");
    }
} 
     public boolean registroClienteFre(long dpi, double monto) {
        if (dpi <= 0 || monto <= 0) {
            System.err.println("Error: DPI y monto deben ser valores positivos");
            return false;
        }
         Cliente cli = dao.obtenerPorDPI(dpi);
        if (cli == null) {
            System.err.println("Error: Cliente no encontrado");
            return false;
        }
        return dao.registrodeClientesfrecuentes(dpi, monto);
     }
}
