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
public class Reportes {
    private int id_v;
    private Date fecha;
    private String usuario;
    private String producto;
    private int cantidad;
    private double precio_u;
    private double total;
    private int Stock;
    
    public Reportes(){
        this.id_v=0;
        Date fecha= this.fecha;
        this.usuario="";
        this.producto="";
        this.cantidad=0;
        this.precio_u=0.0;
        this.total=0.0;
        this.Stock=0;
    }
    
    public Reportes(int id_v, Date fecha, String usuario, String producto, int cantidad, double precio_u, double total, int Stock){
        this.id_v=id_v;
        this.fecha=fecha;
        this.usuario=usuario;
        this.producto=producto;
        this.cantidad=cantidad;
        this.precio_u=precio_u;
        this.total=total;
        this.Stock=Stock;
    }

    public int getId_v() {
        return id_v;
    }

    public void setId_v(int id_v) {
        this.id_v = id_v;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_u() {
        return precio_u;
    }

    public void setPrecio_u(double precio_u) {
        this.precio_u = precio_u;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }
    
}
