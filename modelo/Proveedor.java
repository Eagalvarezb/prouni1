/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author eagab
 */
public class Proveedor {
//     nombre del proveedor, NIT, contacto, direccion, telefono de la empresa, telefono del contacto
    private String nombre_pro;
    private int nit;
    private String tipo_insumo;
    private String contacto;
    private String direccion;
    private String telefono_emp;
    private String telefono_con;
    
 public Proveedor(){
     this.nombre_pro="";
     this.nit=0;
     this.tipo_insumo="";
     this.contacto="";
     this.direccion="";
     this.telefono_emp="";
     this.telefono_con="";
 }
 
 public Proveedor(String nombre_pro,int nit, String tipo_insumo, String contacto, String direccion, String telefono_emp, String telefono_con){
     this.nombre_pro=nombre_pro;
     this.nit=nit;
     this.tipo_insumo=tipo_insumo;
     this.contacto=contacto;
     this.direccion=direccion;
     this.telefono_emp=telefono_emp;
     this.telefono_con=telefono_con;
 }

    public String getNombre_pro() {
        return nombre_pro;
    }

    public void setNombre_pro(String nombre_pro) {
        this.nombre_pro = nombre_pro;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }
    public String getTipo_insumo() {
        return tipo_insumo;
    }

    public void setTipo_insumo(String tipo_insumo) {
        this.tipo_insumo = tipo_insumo;
    }
    
    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono_emp() {
        return telefono_emp;
    }

    public void setTelefono_emp(String telefono_emp) {
        this.telefono_emp = telefono_emp;
    }

    public String getTelefono_con() {
        return telefono_con;
    }

    public void setTelefono_con(String telefono_con) {
        this.telefono_con = telefono_con;
    }

}
