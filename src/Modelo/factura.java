package Modelo;


import java.util.Date;
/**
 *
 * @author asist
 */
public class factura {
    private int id_factura;
    private String cliente;
    private Date fecha_actual;
    private String productos_consumidos;
    private String usuario_atendio;
    private double costo_total;

    public factura() {
        this.id_factura = 0;
        this.cliente = "";
        this.fecha_actual = new Date();
        this.productos_consumidos = "";
        this.usuario_atendio = "";
        this.costo_total = 0.00;
    }
    
    public factura(int id_factura, String cliente, Date fecha_actual, String productos_consumidos, String usuario_atendio, double costo_total) {
        this.id_factura = id_factura;
        this.cliente = cliente;
        this.fecha_actual = fecha_actual;
        this.productos_consumidos = productos_consumidos;
        this.usuario_atendio = usuario_atendio;
        this.costo_total = costo_total;
    }

     public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFecha_actual() {
        return fecha_actual;
    }

    public void setFecha_actual(Date fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    public String getProductos_consumidos() {
        return productos_consumidos;
    }

    public void setProductos_consumidos(String productos_consumidos) {
        this.productos_consumidos = productos_consumidos;
    }

    public String getUsuario_atendio() {
        return usuario_atendio;
    }

    public void setUsuario_atendio(String usuario_atendio) {
        this.usuario_atendio = usuario_atendio;
    }

    public double getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(double costo_total) {
        this.costo_total = costo_total;
    }

}