/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.UsuarioSistemaDao;
import Modelo.UsuarioSistema;
import DAO.EmpleadoDao;
import Modelo.Empleado;

/**
 *
 * @author eagab
 */
public class UsuarioSistemaController{
    private final UsuarioSistemaDao dao = new UsuarioSistemaDao();
    public final EmpleadoDao empleadoDao = new EmpleadoDao();
        
    public void crearUsuario( Empleado emp, UsuarioSistema user){
        if (emp.getNombre() == null) {
            throw new IllegalArgumentException("El nombre es requerido");
        }
        if (emp.getApellido() == null) {
            throw new IllegalArgumentException("El apellido es requerido");
        }
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("La contraseña es requerida");
        }
               
        Empleado empleadoExistente = empleadoDao.obtenerPorID(emp.getId());
        if (empleadoExistente == null) {
            throw new IllegalStateException("El empleado no está registrado en el sistema");
        }
        
      dao.crearUsuario(empleadoExistente, user);
    }
    
    public void verificarCredenciales(String username, String password){
        UsuarioSistema user = new UsuarioSistema( username, password);
        dao.verificarCredenciales(username, password);
    }
    
    public void actualizar(String username, String password){
        UsuarioSistema user = new UsuarioSistema( username, password);
        dao.actualizar(user);
    }
    
    public void eliminarUsuario (String username){
        dao.eliminar(username);
    }
    
}
