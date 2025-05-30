/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.ReportesDao;
import Modelo.Reportes;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author eagab
 */
public class ReportesController {
    private final ReportesDao reportesDao = new ReportesDao();

    public List<Reportes> obtenerPorFecha(Date fechaI, Date fechaF) {
       return reportesDao.PorFecha(fechaI, fechaF);
    }

    public List<Reportes> obtenerPorUsuario(String usuario) {
        return reportesDao.PorUsuario(usuario);
    }

    public List<Reportes> obtenerMasVendidos() {
        return reportesDao.MasVendidos();
    }

   public void exportarPDF(List<Reportes> reportes, String nombreArchivo, String titulo, 
                       Date fechaI, Date fechaF) {
    double totalVentas = calcularTotalVentas(fechaI, fechaF); // Usa el método existente del controlador
    int totalProductos = calcularTotalProductosVendidos(fechaI, fechaF); // Usa el método existente del controlador
reportesDao.generarPDFReportes(reportes, nombreArchivo, titulo);
}
   
    public void imprimirReporte(List<Reportes> datos, String titulo) {
        reportesDao.imprimirReporte(datos, titulo);
    }
    
public double calcularTotalVentas(Date fechaI, Date fechaF) {
    return reportesDao.obtenerTotalVentasPorFecha(fechaI, fechaF);
}

public int calcularTotalProductosVendidos(Date fechaI, Date fechaF) {
    return reportesDao.obtenerTotalProductosVendidos(fechaI, fechaF);
}

public List<String> obtenerUsuariosConVentas(Date fechaI, Date fechaF) {
    return reportesDao.obtenerUsuariosActivos(fechaI, fechaF);
}

public Map<String, Object> generarResumenVentas(Date fechaI, Date fechaF) {
    Map<String, Object> resumen = new HashMap<>();
    
    resumen.put("ventas", obtenerPorFecha(fechaI, fechaF));
    resumen.put("totalVentas", calcularTotalVentas(fechaI, fechaF));
    resumen.put("totalProductos", calcularTotalProductosVendidos(fechaI, fechaF));
    resumen.put("usuariosActivos", obtenerUsuariosConVentas(fechaI, fechaF));
    resumen.put("masVendidos", obtenerMasVendidos());
    
    return resumen;
}
}
