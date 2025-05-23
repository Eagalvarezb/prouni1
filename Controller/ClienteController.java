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
    
    public Cliente obtenerPorID(int dpi){
        try{
        if(dpi <=0){
            System.out.println("Error: DPI no valido" );
            return null;
             }
        return dao.obtenerPorDPI(dpi);
        }catch(Exception e){
            System.out.println("Erro al buscar Cliente: "+e.getMessage());  
            return null;
        }
    }
    
    public void agregarCliente(int dpi,String nombre,String telefono, String direccion,String email){
        Cliente cli = new Cliente(0, nombre, telefono, direccion, email);
        
        if(cli.getNombre() == null) {
            System.out.println("Nombre es requerido");            
        }
        
        cli.setPuntos(0);
        cli.setNivel("Bronce");
        cli.setFecha_res(java.time.LocalDate.now());
        dao.guardar(cli);
    }
    
    public void actualizarCliente(int dpi,String nombre,String telefono, String direccion,String email){
        Cliente cli = new Cliente(dpi, nombre, telefono, direccion,email);
        if (cli == null) {
            System.err.println("Error: Cliente no encontrado");
        }
          cli.setPuntos(cli.getPuntos());
        cli.setNivel(cli.getNivel());
        cli.setFecha_res(cli.getFecha_res());
        
        dao.actualizar(cli);
    }  
     public boolean registroClienteFre(int dpi, double monto) {
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
