package JFrameN;

import java.awt.Container;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import Controlador.PedidosController;
import Modelo.Productos;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author usuario
 */
public class Menu2 extends javax.swing.JInternalFrame{
     private PedidosController pedidoController;
    
     public Menu2(PedidosController pedidoController) {
        this.pedidoController = pedidoController;
        initComponents();
       
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setResizable(true);
        this.setSize(900,700);
//        this.Imagen(this.LblImgaen, "src/Img/2.png");
//        this.Imagen(this.LblImgaen1, "src/Img/1.png");
//        this.Imagen(this.LblImgaen2, "src/Img/Tacos.png");
//        this.Imagen(this.LblImgaen3, "src/Img/Fondo.jpg");
        SwingUtilities.invokeLater(() -> {
        this.Imagen(this.LblImgaen, "src/Img/2.png");
        this.Imagen(this.LblImgaen1, "src/Img/1.png");
        this.Imagen(this.LblImgaen2, "src/Img/Tacos.png");
        this.Imagen(this.LblImgaen3, "src/Img/Fondo.jpg");
         });
         this.setSize(720, 480);
       BtnPerso.addActionListener(e -> abrirMenuPersonales());
        jButton4.addActionListener(e -> abrirMenuCombos());
        jButton3.addActionListener(e -> abrirMenuExtras());
        btnPago.addActionListener(e -> abrirPago());  //mejor lo agrego en otro lugar
    }
     
     @Override
     public JDesktopPane getDesktopPane() {
        // Busca el JDesktopPane en la jerarquía de padres
        Container parent = this.getParent();
        while (parent != null) {
            if (parent instanceof JDesktopPane) {
                return (JDesktopPane) parent;
            }
            parent = parent.getParent();
        }
        return null;
    }

    
    private void abrirMenuPersonales() {
        try {
            MenuPersonales menuPersonales = new MenuPersonales(pedidoController);
             menuPersonales.setSize(740, 550);
            getParent().add(menuPersonales);
            menuPersonales.setVisible(true);
            menuPersonales.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al abrir Menú Personales: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   private void abrirMenuCombos() {
        try {
            MenuCombos menuCombos = new MenuCombos(pedidoController);
            menuCombos.setSize(810, 600);
            JInternalFrame internalFrame = new JInternalFrame("Combos", true, true, true, true);
            internalFrame.setContentPane(menuCombos.getContentPane());
            internalFrame.pack();
            getParent().add(internalFrame);
            internalFrame.setVisible(true);
            internalFrame.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al abrir Menú Combos: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
   private void abrirPago() {
        try {
              if (pedidoController.getProductosSeleccionados().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay productos en el pedido para pagar", 
                "Pedido Vacío", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
            Pago pago = new Pago(pedidoController);
            pago.setSize(700,550);
            JInternalFrame internalFrame = new JInternalFrame("Pago", true, true, true, true);
            internalFrame.setContentPane(pago.getContentPane());
            internalFrame.pack();
            getParent().add(internalFrame);
            internalFrame.setVisible(true);
            internalFrame.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al abrir Pago: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
   private void abrirMenuExtras() {
        try {
            MenuExtras menuExtras = new MenuExtras(pedidoController);
            menuExtras.setSize(740, 650);
            JInternalFrame internalFrame = new JInternalFrame("Extras", true, true, true, true);
            internalFrame.setContentPane(menuExtras.getContentPane());
            internalFrame.pack();
            getParent().add(internalFrame);
            internalFrame.setVisible(true);
            internalFrame.toFront();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al abrir Menú Extras: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ImageIcon imagen;
    private Icon icono;

    /**
     * Creates new form Menu2
     */
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BtnPerso = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnPago = new javax.swing.JButton();
        LblImgaen = new javax.swing.JLabel();
        LblImgaen1 = new javax.swing.JLabel();
        LblImgaen2 = new javax.swing.JLabel();
        LblImgaen3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnPerso.setBackground(new java.awt.Color(255, 102, 0));
        BtnPerso.setFont(new java.awt.Font("SimSun-ExtG", 1, 48)); // NOI18N
        BtnPerso.setForeground(new java.awt.Color(0, 51, 153));
        BtnPerso.setText("Personales");
        BtnPerso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPersoActionPerformed(evt);
            }
        });
        jPanel1.add(BtnPerso, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 570, 90));

        jButton3.setBackground(new java.awt.Color(255, 102, 0));
        jButton3.setFont(new java.awt.Font("SimSun-ExtB", 1, 48)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 153));
        jButton3.setText("Extras");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 570, 90));

        jButton4.setBackground(new java.awt.Color(255, 102, 0));
        jButton4.setFont(new java.awt.Font("SimSun", 1, 48)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 51, 153));
        jButton4.setText("Combos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 570, 90));

        btnPago.setBackground(new java.awt.Color(255, 102, 0));
        btnPago.setForeground(new java.awt.Color(0, 51, 153));
        btnPago.setText("Pago");
        btnPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagoActionPerformed(evt);
            }
        });
        jPanel1.add(btnPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 150, 60));
        jPanel1.add(LblImgaen, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 230, 140));
        jPanel1.add(LblImgaen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 140));
        jPanel1.add(LblImgaen2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 420, 210));
        jPanel1.add(LblImgaen3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPersoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPersoActionPerformed
     //abrirMenuPersonales();
      BtnPerso.addActionListener(e -> {
    this.setVisible(false);
    new MenuPersonales(pedidoController).setVisible(true);});
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPersoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
   jButton3.addActionListener(e -> {
    this.setVisible(false);
    new MenuExtras().setVisible(true);
});
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jButton4.addActionListener(e -> {
    this.setVisible(false);
    new MenuCombos(pedidoController).setVisible(true);
});
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoActionPerformed
 btnPago.addActionListener(e -> {
     this.setVisible(false);
     new Pago(pedidoController).setVisible(true);
 });
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPagoActionPerformed
     private void showInternalFrame(JInternalFrame frame, String title) {
        JInternalFrame internalFrame = new JInternalFrame(title, true, true, true, true);
        internalFrame.setContentPane(((javax.swing.JInternalFrame)frame).getContentPane());
        internalFrame.pack();
        getParent().add(internalFrame);
        internalFrame.setVisible(true);
        internalFrame.toFront();
    }

    private void BtnRegresarActionPerformed(java.awt.event.ActionEvent evt) {
     
int opcion = JOptionPane.showConfirmDialog(
        this, 
        "¿Desea salir de la gestión de clientes?", 
        "Confirmar salida", 
        JOptionPane.YES_NO_OPTION
    );
    
    if (opcion == JOptionPane.YES_OPTION) {
        // En una aplicación real, aquí iría la lógica para volver al menú principal
        // Por ahora simplemente cerraremos la ventana
        this.dispose();
        
        // Si tuvieras un menú principal, sería algo como:
        // new MenuPrincipal().setVisible(true);
    }     // Muestra la ventana anterior (Menu2)
}
    /**
     * @param args the command line arguments
     */
    
    
    private void Imagen(JLabel lbl, String ruta){
        this.imagen = new ImageIcon(ruta);
        int width = lbl.getWidth() <= 0 ? imagen.getIconWidth() : lbl.getWidth();
        int height = lbl.getHeight() <= 0 ? imagen.getIconHeight() : lbl.getHeight();
    
        this.icono = new ImageIcon(this.imagen.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH));

        lbl.setIcon(this.icono);
        this.repaint();
    }
    
    private static Menu2 instance;

/*public static Menu2 getInstance() {
    if (instance == null || instance.isClosed()) {
        instance = new Menu2();
    }
    return instance;
}*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnPerso;
    private javax.swing.JLabel LblImgaen;
    private javax.swing.JLabel LblImgaen1;
    private javax.swing.JLabel LblImgaen2;
    private javax.swing.JLabel LblImgaen3;
    private javax.swing.JButton btnPago;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
