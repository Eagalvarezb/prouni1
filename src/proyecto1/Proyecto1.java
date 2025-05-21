/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1;
import conexion.CreateConection;
import java.sql.SQLException;
/**
 *
 * @author jimem
 */
public class Proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
            CreateConection cn = new CreateConection ();       
            cn.getConnection();
    }
    
}
