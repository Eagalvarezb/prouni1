/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import DAO.EmpleadoDao;
import Modelo.Empleado;

import java.util.List;
/**
 *
 * @author eagab
// */
public class EmpleadoController {
    private final EmpleadoDao dao = new EmpleadoDao();
    
    public List<Empleado> obtenerEmpleado(){
        return dao.obtenerTodos();
    }
    
    public Empleado obtenerPorID(int id){
        try{
        if(id <=0){
            System.out.println("Error: Id valido" );
            return null;
             }
        return dao.obtenerPorID(id);
        }catch(Exception e){
            System.out.println("Erro al buscar emleado: "+e.getMessage());  
            return null;
        }
    }
    
    public void agregarEmpleado(String nombre, String apellido, String puesto,String telefono, String direccion, String estado, String fecha_in, double salario,String email){
        Empleado emp = new Empleado(0, nombre, apellido,puesto,telefono, direccion, estado, fecha_in,salario,email);
        if(emp.getNombre() == null) {
            System.out.println("Nombre es requerido");
        }
        dao.guardar(emp);
    }
    
    public void actualizarEmpleado(int id,String nombre, String apellido, String puesto,String telefono, String direccion, String estado, String fecha_in, double salario,String email){
        Empleado emp = new Empleado(id, nombre, apellido,puesto,telefono, direccion, estado, fecha_in,salario,email);
        dao.actualizar(emp);
    }
    
    public void eliminarEmpleado(int id){
        dao.eliminar(id);
    }
}
