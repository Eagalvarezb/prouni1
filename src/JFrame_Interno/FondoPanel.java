/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JFrame_Interno;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
/**
 *
 * @author Gabriela
 */
public class FondoPanel extends JPanel {
    private Image imagen;

    public FondoPanel(Image imagen) {
        this.imagen = imagen;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
    

