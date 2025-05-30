/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Menu extends javax.swing.JFrame {
    
    
    public Menu() {
        initComponents();
        
        this.setLocationRelativeTo(this);
        this.Imagen(this.LblImgaen, "src/Img/Logo.png");
        this.Imagen(this.LblImgaen1, "src/Img/Taco.png");
        this.Imagen(this.LblImgaen3, "src/Img/Restaurante.jpg");
        this.Imagen(this.LblImgaen4, "src/Img/D.png");
 
    }

    private ImageIcon imagen;
    private Icon icono;
    
    
    

 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        LblImgaen = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        LblImgaen1 = new javax.swing.JLabel();
        LblImgaen4 = new javax.swing.JLabel();
        LblImgaen3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 102));

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label1.setFont(new java.awt.Font("Eras Medium ITC", 1, 24)); // NOI18N
        label1.setForeground(new java.awt.Color(0, 51, 153));
        label1.setMaximumSize(new java.awt.Dimension(42767, 42767));
        label1.setPreferredSize(new java.awt.Dimension(500, 200));
        label1.setText("de nuestro delicioso menú");
        jPanel1.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 305, 25));
        jPanel1.add(LblImgaen, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 305, 256));

        jToggleButton1.setBackground(new java.awt.Color(153, 0, 0));
        jToggleButton1.setFont(new java.awt.Font("Engravers MT", 1, 36)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 204, 0));
        jToggleButton1.setText("Odenar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 366, 52));
        jPanel1.add(LblImgaen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 275, 216, 191));
        jPanel1.add(LblImgaen4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 370, 60));
        jPanel1.add(LblImgaen3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 470));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
    
    
    private void Imagen(JLabel lbl, String ruta){
        this.imagen = new ImageIcon(ruta);
        this.icono = new ImageIcon(
                this.imagen.getImage().getScaledInstance(lbl.getWidth(), 
                        lbl.getHeight(), 
                        Image.SCALE_DEFAULT)
        );
        lbl.setIcon(this.icono);
        this.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblImgaen;
    private javax.swing.JLabel LblImgaen1;
    private javax.swing.JLabel LblImgaen3;
    private javax.swing.JLabel LblImgaen4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables

  

}
