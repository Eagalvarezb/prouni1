/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JFrameN;

import Controlador.ClienteController;
import Modelo.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;
/**
 *
 * @author eagab
 */

   
public class ClienteJinterno extends JFrame {
    private final ClienteController controller;
    private DefaultTableModel tableModel;
    private JTable clientesTable;
    
    // Componentes del formulario
    private JTextField txtDpi, txtNombre, txtTelefono, txtDireccion, txtEmail;
    private JTextField txtPuntos, txtNivel, txtFechaRegistro;
    private JButton btnGuardar, btnActualizar, btnBuscar, btnLimpiar;
    
    // Colores del diseño
    private final Color COLOR_PRIMARIO = new Color(204, 204, 0);
    private final Color COLOR_SECUNDARIO = new Color(204,204, 0);
    private final Color COLOR_BOTONES = new Color(0, 51, 255);
    private final Color COLOR_BOTONES_SECUNDARIOS = new Color(255, 153, 51);

    public ClienteJinterno(ClienteController controller) {
        this.controller = controller;
        initComponents();
        cargarClientes();
        setLocationRelativeTo(null);
    }
    
    private JPanel panelTabla;
    private boolean tablaVisible = false;

    private void initComponents() {
        setTitle("Gestión de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLayout(new BorderLayout(10, 10));
        
        JPanel backgroundPanel = new JPanel(new BorderLayout()){
          @Override
          protected void paintComponent(Graphics g){
              super.paintComponent(g);
              
                ImageIcon imageIcon =new ImageIcon(getClass().getClassLoader().getResource("Img/Fondo.jpg"));
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
          
        };
        backgroundPanel.setOpaque(false);
        
        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(204, 204, 0,150));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN DE CLIENTES");
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panelTitulo.add(lblTitulo);
        backgroundPanel.add(panelTitulo, BorderLayout.NORTH);
        
        // Panel principal (formulario + tabla)
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setOpaque(false);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel del formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setOpaque(false);
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Campos del formulario
        agregarCampo(panelFormulario, gbc, 0, "DPI:", txtDpi = new JTextField(15));
        agregarCampo(panelFormulario, gbc, 1, "Nombre:", txtNombre = new JTextField(15));
        agregarCampo(panelFormulario, gbc, 2, "Teléfono:", txtTelefono = new JTextField(15));
        agregarCampo(panelFormulario, gbc, 3, "Dirección:", txtDireccion = new JTextField(15));
        agregarCampo(panelFormulario, gbc, 4, "Email:", txtEmail = new JTextField(15));
        agregarCampo(panelFormulario, gbc, 5, "Puntos:", txtPuntos = new JTextField(15));
        txtPuntos.setEditable(false);
        agregarCampo(panelFormulario, gbc, 6, "Nivel:", txtNivel = new JTextField(15));
        txtNivel.setEditable(false);
        agregarCampo(panelFormulario, gbc, 7, "Fecha Registro:", txtFechaRegistro = new JTextField(15));
        txtFechaRegistro.setEditable(false);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setOpaque(false);
        
        btnGuardar = crearBoton("Guardar", COLOR_BOTONES, e -> guardarCliente());
        btnActualizar = crearBoton("Actualizar", COLOR_BOTONES, e -> actualizarCliente());
        btnBuscar = crearBoton("Buscar", new Color(102, 255, 102), e -> buscarCliente());
        btnLimpiar = crearBoton("Limpiar", new Color(204, 0, 51), e -> limpiarFormulario());
        JButton btnMostrarTodos = crearBoton("Mostrar Todos", new Color(102, 153, 255), e -> toggleTabla());
        JButton btnRegresar = crearBoton("Regresar", COLOR_BOTONES_SECUNDARIOS , e -> regresar());

        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnMostrarTodos);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnRegresar);
        
        // Agregar componentes al panel del formulario
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panelFormulario.add(panelBotones, gbc);
        
        // Panel de la tabla
        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setOpaque(false);
        panelTabla.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));
        panelTabla.setVisible(false);
        // Modelo y tabla de clientes
        String[] columnas = {"DPI", "Nombre", "Teléfono", "Dirección", "Email", "Puntos", "Nivel", "Fecha Registro"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable
            }
        };
        
        clientesTable = new JTable(tableModel);
        clientesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clientesTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDatosDesdeTabla();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(clientesTable);
        panelTabla.add(scrollPane, BorderLayout.CENTER);
        
        // Agregar paneles al panel principal
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);
        backgroundPanel.add(panelPrincipal,BorderLayout.CENTER);
        
        add(backgroundPanel);
    }
    
    private void agregarCampo(JPanel panel, GridBagConstraints gbc, int fila, String etiqueta, JTextField campo) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        panel.add(new JLabel(etiqueta), gbc);
        
        gbc.gridx = 1;
        panel.add(campo, gbc);
    }
    
    private JButton crearBoton(String texto, Color color, ActionListener listener) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        boton.addActionListener(listener);
        return boton;
    }
private void toggleTabla() {
    tablaVisible = !tablaVisible;
    panelTabla.setVisible(tablaVisible);
    if (tablaVisible) {
        cargarClientes();
    }
    this.pack();
}
    
private void regresar() {
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
    }
}
    private void cargarClientes() {
        tableModel.setRowCount(0);
        List<Cliente> clientes = controller.obtenerCliente();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        for (Cliente cliente : clientes) {
            Object[] fila = {
                cliente.getDpi(),
                cliente.getNombre(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getEmail(),
                cliente.getPuntos(),
                cliente.getNivel(),
                cliente.getFecha_res() != null ? cliente.getFecha_res().format(formatter) : ""
            };
            tableModel.addRow(fila);
        }
    }
    
    private void cargarDatosDesdeTabla() {
        int filaSeleccionada = clientesTable.getSelectedRow();
        if (filaSeleccionada >= 0) {
            txtDpi.setText(tableModel.getValueAt(filaSeleccionada, 0).toString());
            txtNombre.setText(tableModel.getValueAt(filaSeleccionada, 1).toString());
            txtTelefono.setText(tableModel.getValueAt(filaSeleccionada, 2).toString());
            txtDireccion.setText(tableModel.getValueAt(filaSeleccionada, 3).toString());
            txtEmail.setText(tableModel.getValueAt(filaSeleccionada, 4).toString());
            txtPuntos.setText(tableModel.getValueAt(filaSeleccionada, 5).toString());
            txtNivel.setText(tableModel.getValueAt(filaSeleccionada, 6).toString());
            txtFechaRegistro.setText(tableModel.getValueAt(filaSeleccionada, 7).toString());
        }
    }
    
    private void guardarCliente() {
        
        try {
            String dpit = txtDpi.getText(); // Cambiado a String en lugar de int
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String direccion = txtDireccion.getText();
            String email = txtEmail.getText();
            
            if (dpit.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!dpit.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "DPI debe contener solo números", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }            
            long dpi = Long.parseLong(dpit);
            controller.agregarCliente(dpi, nombre, telefono, direccion, email);
            JOptionPane.showMessageDialog(this, "Cliente guardado exitosamente");
            limpiarFormulario();
            cargarClientes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Error al guardar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarCliente() {
       try {
           if (txtDpi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "DPI es obligatorio para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            long dpi = Long.parseLong(txtDpi.getText()); 
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String direccion = txtDireccion.getText();
            String email = txtEmail.getText();
            
            controller.actualizarCliente(dpi, nombre, telefono, direccion, email);
            JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente");
            limpiarFormulario();
            cargarClientes();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void buscarCliente() {
        try {
            String dpit = txtDpi.getText(); // Cambiado a String
            long dpi = Long.parseLong(dpit);
            Cliente cliente = controller.obtenerPorID(dpi); // Convertimos a int para el controlador
            
            if (cliente != null) {
                txtNombre.setText(cliente.getNombre());
                txtTelefono.setText(cliente.getTelefono());
                txtDireccion.setText(cliente.getDireccion());
                txtEmail.setText(cliente.getEmail());
                txtPuntos.setText(String.valueOf(cliente.getPuntos()));
                txtNivel.setText(cliente.getNivel());
                txtFechaRegistro.setText(cliente.getFecha_res().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarFormulario() {
        txtDpi.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtPuntos.setText("");
        txtNivel.setText("");
        txtFechaRegistro.setText("");
        clientesTable.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteController controller = new ClienteController();
            ClienteJinterno vista = new ClienteJinterno(controller);
           vista.setVisible(true);
        });
    }

}

