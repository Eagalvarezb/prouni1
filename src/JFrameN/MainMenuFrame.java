package JFrameN;
import javax.swing.*;
import java.awt.*;
import Controlador.UsuarioSistemaController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame {
    
    private String userRole = "admin"; // Esto debería venir de la autenticación

    public MainMenuFrame() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Menú Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel backgroundPanel= new JPanel(new BorderLayout()){
            @Override
            protected void paintComponent (Graphics g){
                super.paintComponent(g);
                ImageIcon imageIcon =new ImageIcon(getClass().getClassLoader().getResource("Img/Fondo.jpg"));
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setOpaque(false);
        
        // Barra de título con información de usuario
        JLabel lblWelcome = new JLabel("Bienvenido - Rol: " + userRole, SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        backgroundPanel.add(lblWelcome, BorderLayout.NORTH);

        // Panel de botones según rol
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setBackground(new Color(255, 255, 255, 100));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Botones comunes a todos los roles
        JButton btnChangePass = new JButton("Cambiar Mi Contraseña");
        btnChangePass.addActionListener(e -> new ChangePasswordFrame().setVisible(true));
        buttonPanel.add(btnChangePass);

        // Botones según rol
        if (userRole.equals("admin")) {
            // Botones solo para admin
                    
            JButton btnProviders = new JButton("Gestión de Proveedores");
            btnProviders.addActionListener(e -> new ProveedorJinterno().setVisible(true));
            buttonPanel.add(btnProviders);

            JButton btnEmployees = new JButton("Gestión de Empleados");
            btnEmployees.addActionListener(e -> new EmpleadoJinternoMo().setVisible(true));
            buttonPanel.add(btnEmployees);

            JButton btnClients = new JButton("Gestión de Clientes");
            btnClients.addActionListener(e -> new ClienteJinterno(new Controlador.ClienteController()).setVisible(true));
            buttonPanel.add(btnClients);
        }

        if (userRole.equals("admin") || userRole.equals("cashier")) {
            
            JButton btnReports = new JButton("Reportes");
            btnReports.addActionListener(e -> new ReportesJinterno().setVisible(true));
            buttonPanel.add(btnReports);
        }

        if (userRole.equals("admin") || userRole.equals("cashier") || userRole.equals("waiter")) {
            JButton btnMenu = new JButton("Menú");
            btnMenu.addActionListener(e -> new Menu().setVisible(true));
            buttonPanel.add(btnMenu);
            
            
        }
        
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
        add(backgroundPanel);
    }
}