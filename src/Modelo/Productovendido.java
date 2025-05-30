/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author eagab
 */
public class Productovendido {
    private String nombreProducto;
    private int totalUnidadesVendidas;
    private double ingresoTotal;
    
public Productovendido(){
    this.nombreProducto="";
    this.totalUnidadesVendidas=0;
    this.ingresoTotal=0.0;
}

public Productovendido(String nombreProducto, int totalUnidadesVendidas, double ingresoTotal){
    this.nombreProducto=nombreProducto;
    this.totalUnidadesVendidas=totalUnidadesVendidas;
    this.ingresoTotal=ingresoTotal;
}
    public String getNombreProducto() {
        return nombreProducto;
    }
    
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    public int getTotalUnidadesVendidas() {
        return totalUnidadesVendidas;
    }
    
    public void setTotalUnidadesVendidas(int totalUnidadesVendidas) {
        this.totalUnidadesVendidas = totalUnidadesVendidas;
    }
    
    public double getIngresoTotal() {
        return ingresoTotal;
    }
    
    public void setIngresoTotal(double ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }
    
    @Override
    public String toString() {
        return String.format("%s: %d unidades - $%.2f", 
               nombreProducto, totalUnidadesVendidas, ingresoTotal);
    }

}
