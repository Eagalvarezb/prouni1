/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.Final;

import Conexion.CreateConection;
import java.sql.SQLException;
import java.sql.SQLException;

/**
 *
 * @author eagab
 */
public class ProyectoFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       CreateConection cn = new CreateConection();       
       cn.getConnection();
    
    }
   
}
