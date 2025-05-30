/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author eagab
 */
public class Empleado {
   
    private int id;
    private String nombre;
    private String apellido;
    private String puesto;
    private String telefono;
    private String direccion;
    private String estado;
    private String  fecha_in;
    private double salario;
    private String email;
    
    // nombre, apellido, cargo, teléfono, dirección, estado (activo/inactivo), fechaIngreso, salario, email

 public Empleado(){
        this.id=0;
        this.nombre="";
        this.apellido="";
        this.puesto="";
        this.telefono="";
        this.direccion="";
        this.estado="";
        this.fecha_in="";
        this.salario=0.0;
        this.email="";
}
    
    public Empleado(int id,String nombre, String apellido, String puesto,String telefono, String direccion, String estado, String fecha_in, double salario,String email){
        this.id=id;
        this.nombre=nombre;
        this.apellido=apellido;
        this.puesto=puesto;
        this.telefono=telefono;
        this.direccion=direccion;
        this.estado=estado;
        this.fecha_in=fecha_in;
        this.salario=salario;
        this.email=email;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_in() {
        return fecha_in;
    }

    public void setFecha_in(String fecha_in) {
        this.fecha_in = fecha_in;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emial) {
        this.email = emial;
    }
    
   
    
}
