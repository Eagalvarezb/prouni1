/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import DAO.ProveedorDao;
import Modelo.Proveedor;
import java.util.List;
/**
 *
 * @author eagab
 */
public class ProveedorControler {
    // nombre del proveedor, NIT, contacto, direccion, telefono de la empresa, telefono del contacto
 private final ProveedorDao dao = new ProveedorDao();
  
    public List<Proveedor> obtenerProveedor(){
        return dao.obtenerTodos();
    }
    
    public Proveedor obtenerPorNit(int nit){
        try{
        if(nit <=0){
            System.out.println("Error: NIT no valido" );
            return null;
             }
        return dao.obtenerPorNIT(nit);
        }catch(Exception e){
            System.out.println("Erro al buscar Proveedor: "+e.getMessage());  
            return null;
        }
    }
    
    public void agregarProveedor(String nombre_pro,int nit,String tipos_insumo, String contacto,String direccion, String telefono_emp, String telefono_con){
        Proveedor pro = new Proveedor(nombre_pro,nit, tipos_insumo, contacto, direccion, telefono_emp, telefono_con);
        if(pro.getNombre_pro() == null) {
            System.out.println("Error: Nombre es requerido");  
        }
        if (nit <= 0) {
            System.err.println("Error: NIT debe ser un número positivo");
        }
        dao.guardar(pro);
    }
    
    public void actualizarProveedor(String nombre_pro,int nit, String tipos_insumo,String contacto, String direccion,String telefono_emp, String telefono_con){
        Proveedor pro = new Proveedor(nombre_pro, nit, tipos_insumo, contacto, direccion, telefono_emp, telefono_con);   
        dao.actualizar(pro);
    }
    
     public void eliminarProveedor(int nit){
        dao.eliminar(nit);
    }
}
