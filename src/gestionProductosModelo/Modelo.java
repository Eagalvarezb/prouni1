/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionProductosModelo;
/**
 *
 * @author jimem
 */
public class Modelo {
    private int id_product;
    private String product_name;
    private int cantidad;
    private double product_price;

    public Modelo() {
        this.id_product = 0;
        this.product_name = "";
        this.cantidad = 0;
        this.product_price = 0.0;
    }

    public Modelo(int id_product, String product_name, int cantidad, double product_price) {
        this.id_product = id_product;
        this.product_name = product_name;
        this.cantidad = cantidad;
        this.product_price = product_price;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }
    
}
