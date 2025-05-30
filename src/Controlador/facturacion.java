package Controlador;

import java.util.Date;
import DAO.factucionYpedidos;
import Modelo.factura;
import java.util.List;
/**
 *
 * @author asist
 */
public class facturacion {
    private final factucionYpedidos dao = new factucionYpedidos();
    
    public List<factura> obtenerTodos(){
        return dao.obtenerTodos();
    }
    
    public void agregarPedido(int id_factura,String cliente, Date fecha_actual, String productos_consumidos, String usuario_atendio, double costo_total){
        factura facts = new factura(0,cliente, fecha_actual, productos_consumidos, usuario_atendio, costo_total);
        dao.agregarFactura(facts);
    }
    
    public void actualizarPedido(int id_factura, String cliente, Date fecha_actual, String productos_consumidos, String usuario_atendio, double costo_total){
        factura facts = new factura(id_factura, cliente, fecha_actual, productos_consumidos, usuario_atendio, costo_total);
        dao.actualizar(facts);
    }
    
    public void borrarPedido(int id_factura){
        dao.eliminarFactura(id_factura);
    }
}