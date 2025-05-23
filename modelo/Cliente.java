/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author eagab
 */
public class Cliente {
    //nombre,dpi, teléfono, direccion, email
    private int dpi;
    private String nombre;
    private String telefono;
    private String direccion;
    private String email;
    private int puntos;
    private LocalDate fecha_res;
    private String nivel;
   
public Cliente (){
        this.dpi=0;
        this.nombre="";
        this.telefono="";
        this.direccion="";
        this.email="";
        this.puntos=0;
        LocalDate fecha_re = fecha_res;
        this.nivel="";
}

public Cliente(int dpi, String nombre, String telefono, String direccion, String email){
    this.dpi=dpi;
    this.nombre=nombre;
    this.telefono=telefono;
    this.direccion=direccion;
    this.email=email;
    this.puntos=puntos;
    this.fecha_res=fecha_res;
    this.nivel=nivel;
}

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public LocalDate getFecha_res() {
        return fecha_res;
    }

    public void setFecha_res(LocalDate fecha_res) {
        this.fecha_res = fecha_res;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
