/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author eagab
 */
public class Productos {
    private int id_producto;
    private String nombre;
    private String tipo; 
    private int cantidad;
    private double precio;
    private String descripcion;

    public Productos() {
        this.id_producto = 0;
        this.nombre = "";
        this.tipo="";
        this.cantidad = 0;
        this.precio = 0.0;
        this.descripcion = "";
    }
    public Productos(int id_producto, String nombre, int cantidad, double precio, String tipo) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.tipo=tipo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
