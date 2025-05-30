package JFrameN;


import Controlador.ProveedorControler;
import Modelo.Proveedor;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ProveedorJinterno extends javax.swing.JFrame {

    private final ProveedorControler controller;
    
    public ProveedorJinterno() {
        this.controller = new ProveedorControler();
        initComponents();
       
        setLocationRelativeTo(null);
    }
    
     
      
       
       
    

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNomProveedor = new javax.swing.JLabel();
        lblNit = new javax.swing.JLabel();
        lblTipoInsumo = new javax.swing.JLabel();
        lblContacto = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblTelEmpresa = new javax.swing.JLabel();
        lblTelContacto = new javax.swing.JLabel();
        txtNomProveedor = new javax.swing.JTextField();
        txtNit = new javax.swing.JTextField();
        txtTipoInsumo = new javax.swing.JTextField();
        txtContacto = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelEmpresa = new javax.swing.JTextField();
        txtTelContacto = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Proveedores");

        jPanel1.setBackground(new java.awt.Color(204, 204, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setText("GESTIÓN DE PROVEEDORES");
        jPanel1.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        lblNomProveedor.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblNomProveedor.setForeground(new java.awt.Color(0, 0, 153));
        lblNomProveedor.setText("Nombre del proveedor:");
        jPanel1.add(lblNomProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        lblNit.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblNit.setForeground(new java.awt.Color(0, 0, 153));
        lblNit.setText("NIT:");
        jPanel1.add(lblNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        lblTipoInsumo.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblTipoInsumo.setForeground(new java.awt.Color(0, 0, 153));
        lblTipoInsumo.setText("Tipo de insumo:");
        jPanel1.add(lblTipoInsumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        lblContacto.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblContacto.setForeground(new java.awt.Color(0, 0, 153));
        lblContacto.setText("Contacto:");
        jPanel1.add(lblContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        lblDireccion.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblDireccion.setForeground(new java.awt.Color(0, 0, 153));
        lblDireccion.setText("Dirección:");
        jPanel1.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        lblTelEmpresa.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblTelEmpresa.setForeground(new java.awt.Color(0, 0, 153));
        lblTelEmpresa.setText("Teléfono empresa:");
        jPanel1.add(lblTelEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        lblTelContacto.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblTelContacto.setForeground(new java.awt.Color(0, 0, 153));
        lblTelContacto.setText("Teléfono contacto:");
        jPanel1.add(lblTelContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

        txtNomProveedor.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jPanel1.add(txtNomProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 300, -1));

        txtNit.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jPanel1.add(txtNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 300, -1));

        txtTipoInsumo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jPanel1.add(txtTipoInsumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 300, -1));

        txtContacto.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jPanel1.add(txtContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 300, -1));

        txtDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 300, -1));

        txtTelEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jPanel1.add(txtTelEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 300, -1));

        txtTelContacto.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jPanel1.add(txtTelContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 300, -1));

        btnGuardar.setBackground(new java.awt.Color(102, 255, 102));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 100, 40));

        btnActualizar.setBackground(new java.awt.Color(0, 153, 255));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnActualizar.setForeground(new java.awt.Color(0, 0, 0));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 100, 40));

        btnEliminar.setBackground(new java.awt.Color(255, 102, 102));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 100, 40));

        btnBuscar.setBackground(new java.awt.Color(255, 204, 102));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnBuscar.setForeground(new java.awt.Color(0, 0, 0));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 100, 40));

        btnLimpiar.setBackground(new java.awt.Color(153, 153, 153));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnLimpiar.setForeground(new java.awt.Color(0, 0, 0));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 100, 40));

        btnRegresar.setBackground(new java.awt.Color(204, 153, 255));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 440, 100, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );

        pack();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String nombre = txtNomProveedor.getText().trim();
            int nit = Integer.parseInt(txtNit.getText().trim());
            String tipoInsumo = txtTipoInsumo.getText().trim();
            String contacto = txtContacto.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String telEmpresa = txtTelEmpresa.getText().trim();
            String telContacto = txtTelContacto.getText().trim();
            
            if (nombre.isEmpty() || tipoInsumo.isEmpty() || contacto.isEmpty() || 
                direccion.isEmpty() || telEmpresa.isEmpty() || telContacto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            controller.agregarProveedor(nombre, nit, tipoInsumo, contacto, direccion, telEmpresa, telContacto);
            JOptionPane.showMessageDialog(this, "Proveedor guardado exitosamente");
            limpiarCampos();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "NIT debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String nombre = txtNomProveedor.getText().trim();
            int nit = Integer.parseInt(txtNit.getText().trim());
            String tipoInsumo = txtTipoInsumo.getText().trim();
            String contacto = txtContacto.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String telEmpresa = txtTelEmpresa.getText().trim();
            String telContacto = txtTelContacto.getText().trim();
            
            
            
            controller.actualizarProveedor(nombre, nit, tipoInsumo, contacto, direccion, telEmpresa, telContacto);
            JOptionPane.showMessageDialog(this, "Proveedor actualizado exitosamente");
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "NIT debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtNit.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el NIT del proveedor a eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int nit = Integer.parseInt(txtNit.getText().trim());
            
            int confirmacion = JOptionPane.showConfirmDialog(
                this, 
                "¿Está seguro de eliminar este proveedor?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                controller.eliminarProveedor(nit);
                JOptionPane.showMessageDialog(this, "Proveedor eliminado exitosamente");
                limpiarCampos();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "NIT debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtNit.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el NIT del proveedor a buscar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int nit = Integer.parseInt(txtNit.getText().trim());
            Proveedor proveedor = controller.obtenerPorNit(nit);
            
            if (proveedor != null) {
                txtNomProveedor.setText(proveedor.getNombre_pro());
                txtTipoInsumo.setText(proveedor.getTipo_insumo());
                txtContacto.setText(proveedor.getContacto());
                txtDireccion.setText(proveedor.getDireccion());
                txtTelEmpresa.setText(proveedor.getTelefono_emp());
                txtTelContacto.setText(proveedor.getTelefono_con());
            } else {
                JOptionPane.showMessageDialog(this, "Proveedor no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "NIT debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCampos();
    }

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {
        int opcion = JOptionPane.showConfirmDialog(
        this, 
        "¿Desea salir de la gestión de Proveedores?", 
        "Confirmar salida", 
        JOptionPane.YES_NO_OPTION
    );
    
    if (opcion == JOptionPane.YES_OPTION) {
        // En una aplicación real, aquí iría la lógica para volver al menú principal
        // Por ahora simplemente cerraremos la ventana
        this.dispose();
        
        // Si tuvieras un menú principal, sería algo como:
        // new MenuPrincipal().setVisible(true);
    }
    }

    private void limpiarCampos() {
        txtNomProveedor.setText("");
        txtNit.setText("");
        txtTipoInsumo.setText("");
        txtContacto.setText("");
        txtDireccion.setText("");
        txtTelEmpresa.setText("");
        txtTelContacto.setText("");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProveedorJinterno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblContacto;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblNit;
    private javax.swing.JLabel lblNomProveedor;
    private javax.swing.JLabel lblTelContacto;
    private javax.swing.JLabel lblTelEmpresa;
    private javax.swing.JLabel lblTipoInsumo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNomProveedor;
    private javax.swing.JTextField txtTelContacto;
    private javax.swing.JTextField txtTelEmpresa;
    private javax.swing.JTextField txtTipoInsumo;
    // End of variables declaration
}