/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Conexion.CreateConection;
import Modelo.Reportes;
import Modelo.Productovendido;
import java.awt.Font;

import java.sql.*;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.PrinterJob;
import java.awt.print.PrinterException;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
/**
 *
 * @author eagab
 */
public class ReportesDao {
    private final CreateConection connFactory = new CreateConection();
    
     public boolean CrearR(Reportes re) {
        String sql = "INSERT INTO  public.reportes (id_v,fecha, usuario, producto,cantidad, precio_u, total,stock_actual) "+
                "VALUES (?,CURRENT_TIMESTAMP,?,?,?,?,?,?);";

          
        try (Connection conn = connFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
           
            ps.setInt(1,re.getId_v());
            ps.setString(2, re.getUsuario());
            ps.setString(3, re.getProducto());
            ps.setInt(4, re.getCantidad());
            ps.setDouble(5, re.getPrecio_u());
            ps.setDouble(6,re.getTotal());
            ps.setInt(7, re.getStock());
           

            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return true;
           
            
        } catch (SQLException e) {
        Logger.getLogger(ReportesDao.class.getName()).log(Level.SEVERE, "Error al crear reporte", e);
        return false;
      }
    }
    
    public boolean actualizar(Reportes re) {
      
    if (re.getFecha() != null) {
        re.setFecha(re.getFecha());
    }
    if (re.getUsuario() != null) {
        re.setUsuario(re.getUsuario());
    }
    if (re.getCantidad() != 0) {
        re.setCantidad(re.getCantidad());
    }
    if (re.getPrecio_u() != 0) {
        re.setPrecio_u(re.getPrecio_u());
    }
    if (re.getTotal() != 0) {
        re.setTotal(re.getTotal());
    }
    if (re.getStock() != 0) {
        re.setStock(re.getStock());
    }
       
        String sql = "UPDATE public.reportes_ventas SET usuario=?, producto=?, cantidad=?, precio_u=?, total=?,stock_actual=? WHERE id_v=?";

        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, re.getUsuario());
            ps.setString(2, re.getProducto());
            ps.setInt(3, re.getCantidad());
            ps.setDouble(4, re.getPrecio_u());
            ps.setDouble(5, re.getTotal());
            ps.setInt(6, re.getStock());
            ps.setInt(7, re.getId_v());
      
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<Reportes> PorFecha(Date fechaI, Date fechaF){
        List<Reportes> reportes = new ArrayList<>();
        String sql = "SELECT * FROM public.reportes_ventas WHERE fecha BETWEEN ? AND ? ORDER BY fecha DESC";
        
        try (Connection conn = connFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setTimestamp(1, new java.sql.Timestamp(fechaI.getTime()));
        ps.setTimestamp(2, new java.sql.Timestamp(fechaF.getTime()));
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Reportes r = new Reportes();
                r.setId_v(rs.getInt("id_venta"));
                r.setFecha(rs.getTimestamp("fecha"));
                r.setUsuario(rs.getString("usuario"));
                r.setProducto(rs.getString("producto"));
                r.setCantidad(rs.getInt("cantidad"));
              //  r.setPrecio_u(rs.getDouble("precio_u"));
                r.setTotal(rs.getDouble("total"));
               // r.setStock(rs.getInt("Stock"));
                
                reportes.add(r);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return reportes;
    }
    
    public List<Reportes> PorUsuario(String usuario) {
        List<Reportes> reportes = new ArrayList<>();
    String sql = "SELECT * FROM public.reportes_ventas WHERE usuario = ? ORDER BY fecha DESC";
    
    try (Connection conn = connFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, usuario);
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Reportes r = new Reportes();
               // r.setId_v(rs.getInt("id_venta"));
                r.setFecha(rs.getTimestamp("fecha"));
                r.setUsuario(rs.getString("usuario"));
                r.setProducto(rs.getString("producto"));
                r.setCantidad(rs.getInt("cantidad"));
              //  r.setPrecio_u(rs.getDouble("precio_u"));
                r.setTotal(rs.getDouble("total"));
               // r.setStock(rs.getInt("Stock"));
                
                ps.close();
                conn.close();
                reportes.add(r);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return reportes;
}
    
    public List<Reportes> PorProducto(String producto) {
    List<Reportes> reportes = new ArrayList<>();
    String sql = "SELECT * FROM public.reportes_ventas WHERE producto = ? ORDER BY fecha DESC";    
    
    try (Connection conn = connFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, producto);
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Reportes r = new Reportes();
                
                r.setId_v(rs.getInt("id_venta"));
                r.setFecha(rs.getTimestamp("fecha"));
                r.setUsuario(rs.getString("usuario"));
                r.setProducto(rs.getString("producto"));
                r.setCantidad(rs.getInt("cantidad"));
                r.setPrecio_u(rs.getDouble("precio_u"));
                r.setTotal(rs.getDouble("total"));
                r.setStock(rs.getInt("stock_actual"));
                
                ps.close();
                conn.close();
                reportes.add(r);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return reportes;
}
 public List<Reportes> MasVendidos() {
    List<Reportes> Productos = new ArrayList<>();
    String sql = "SELECT public.productos, SUM(cantidad) AS cantidad FROM reportes GROUP BY producto ORDER BY cantidad DESC LIMIT 10";

    try (Connection conn = connFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Reportes r = new Reportes();
            r.setProducto(rs.getString("producto"));
            r.setCantidad(rs.getInt("cantidad"));
            Productos.add(r);
        }
        rs.close();
    } catch (SQLException e) {
        Logger.getLogger(ReportesDao.class.getName()).log(Level.SEVERE, "Error al obtener más vendidos", e);
    }
    return Productos;
}

public double obtenerTotalVentasPorFecha(Date fechaI, Date fechaF) {
    String sql = "SELECT SUM(total) FROM public.reportes_ventas WHERE fecha BETWEEN ? AND ?";
    try (Connection conn = connFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setTimestamp(1, new java.sql.Timestamp(fechaI.getTime()));
        ps.setTimestamp(2, new java.sql.Timestamp(fechaF.getTime()));
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

public int obtenerTotalProductosVendidos(Date fechaI, Date fechaF) {
    String sql = "SELECT SUM(cantidad) FROM public.reportes_ventas WHERE fecha BETWEEN ? AND ?";
    try (Connection conn = connFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setTimestamp(1, new java.sql.Timestamp(fechaI.getTime()));
        ps.setTimestamp(2, new java.sql.Timestamp(fechaF.getTime()));
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

public List<String> obtenerUsuariosActivos(Date fechaI, Date fechaF) {
    List<String> usuarios = new ArrayList<>();
    String sql = "SELECT DISTINCT usuario FROM public.reportes_ventas WHERE fecha BETWEEN ? AND ?";
    
    try (Connection conn = connFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setTimestamp(1, new java.sql.Timestamp(fechaI.getTime()));
        ps.setTimestamp(2, new java.sql.Timestamp(fechaF.getTime()));
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usuarios.add(rs.getString("usuario"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return usuarios;
}

    public boolean eliminar(int id_v) {
        String sql = "DELETE FROM public.reportes WHERE id_v=?";

        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_v);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
            
            return true;
            
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
   public static void generarPDFReportes(List<Reportes> reportes, String nombreArch, String titulo) {
    try (PDDocument document = new PDDocument()) {
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - margin;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float yPosition = yStart;
          
            PDType1Font fontBold = PDType1Font.HELVETICA_BOLD;
            PDType1Font fontNormal = PDType1Font.HELVETICA;
      
            contentStream.setFont(fontBold, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText(titulo);
            contentStream.endText();
            yPosition -= 30;
            
            contentStream.setFont(fontNormal, 10);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Generado el: " + new java.util.Date());
            contentStream.endText();
            yPosition -= 30;
             yPosition -= 30;
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            Object[] totalVentas = null;
            contentStream.showText("Total Ventas: " + String.format("$%.2f", totalVentas));
             contentStream.endText();
    
            yPosition -= 15;
           contentStream.beginText();
           contentStream.newLineAtOffset(margin, yPosition);
            String totalProductos = null;
           contentStream.showText("Total Productos Vendidos: " + totalProductos);
           contentStream.endText();
      
            String[] headers = {"ID Venta", "Fecha", "Usuario", "Producto", "Cantidad", "P. Unitario", "Total", "Stock"};
            float[] columnWidths = {50f, 80f, 80f, 100f, 50f, 60f, 60f, 50f};
            dibujarEncabezado(contentStream, margin, yPosition, tableWidth, headers, columnWidths, fontBold);
            yPosition -= 20;
            
            contentStream.setFont(fontNormal, 10);
            for (Reportes reporte : reportes) {
                String[] rowData = {
                    String.valueOf(reporte.getId_v()),
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").format(reporte.getFecha()),
                    reporte.getUsuario(),
                    reporte.getProducto(),
                    String.valueOf(reporte.getCantidad()),
                    String.format("$%.2f", reporte.getPrecio_u()),
                    String.format("$%.2f", reporte.getTotal()),
                    String.valueOf(reporte.getStock())
                };
                dibujarR(contentStream, margin, yPosition, tableWidth, rowData, columnWidths);
                yPosition -= 15;
            }
        }
        
        document.save(nombreArch);
        System.out.println("Reporte PDF generado exitosamente: " + nombreArch);
        
    } catch (IOException e) {
        System.err.println("Error al generar el PDF: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Error inesperado: " + e.getMessage());
    }
}

   
private static void dibujarEncabezado(PDPageContentStream contentStream, float x, float y,float tableWidth, String[] headers, float[] columnWidths,PDType1Font font) throws IOException {
    contentStream.setFont(font, 10);
    float nextX = x;
   
    for (int i = 0; i < headers.length; i++) {
        contentStream.beginText();
        contentStream.newLineAtOffset(nextX, y);
        contentStream.showText(headers[i]);
        contentStream.endText();
        nextX += columnWidths[i];
    }
    
    contentStream.moveTo(x, y - 5);
    contentStream.lineTo(x + tableWidth, y - 5);
    contentStream.stroke();
}

private static void dibujarR(PDPageContentStream contentStream, float x, float y, float tableWidth, String[] rowData, float[] columnWidths) throws IOException {
     float nextX = x;
   
    for (int i = 0; i < rowData.length; i++) {
        contentStream.beginText();
        contentStream.newLineAtOffset(nextX, y);
        contentStream.showText(rowData[i]);
        contentStream.endText();
        nextX += columnWidths[i];
    }
}

  public void imprimirReporte(List<Reportes> reportes, String titulo) {
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintable((Graphics graphics, PageFormat pageFormat, int pageIndex) -> {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString(titulo, 100, 100);

        int y = 140;
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        for (Reportes r : reportes) {
            g2d.drawString("Fecha: " + r.getFecha() + " | Usuario: " + r.getUsuario()
                    + " | Producto: " + r.getProducto() + " | Cantidad: " + r.getCantidad()
                    + " | Total: Q" + r.getTotal(), 50, y);
            y += 20;
        }
        return Printable.PAGE_EXISTS;
    });

    try {
        job.print();
    } catch (PrinterException e) {
        Logger.getLogger(ReportesDao.class.getName()).log(Level.SEVERE, "Error al imprimir", e);
    }
  }
}
