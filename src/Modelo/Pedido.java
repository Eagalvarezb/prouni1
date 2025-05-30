package Modelo;


import java.util.Date;

public class Pedido {
    private int id;
    private String cliente;
    private String productos; 
    private double total;
    private Date fecha;
    private String usuario;
    
     public Pedido() {
        this.id = 0;
        this.cliente = "";
        this.productos = "";
        this.total = 0.0;
        this.fecha = fecha;
        this.usuario = "";
    }
    
    public Pedido(int id, String cliente, String productos, double total, Date fecha, String usuario) {
        this.id = id;
        this.cliente = cliente;
        this.productos = productos;
        this.total = total;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
}