package Modelo;


import java.util.Date;

public class Pago {
    private int id;
    private int idPedido;
    private String metodo; // EFECTIVO, TARJETA
    private double monto;
    private Date fecha;
    private String estado; // PENDIENTE, COMPLETADO, RECHAZADO
    
    public Pago() {
        this.id = 0;
        this.idPedido = 0;
        this.metodo = "";
        this.monto =0.0;
        this.fecha = fecha;
        this.estado = "";
    }
    
    public Pago(int id, int idPedido, String metodo, double monto, Date fecha, String estado) {
        this.id = id;
        this.idPedido = idPedido;
        this.metodo = metodo;
        this.monto = monto;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
 
}