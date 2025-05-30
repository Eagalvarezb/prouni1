package JFrameN;
import javax.swing.*;
import java.awt.*;
import Controlador.UsuarioSistemaController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame {
    // Suponiendo que tenemos información del usuario logueado
    private String userRole = "admin"; // Esto debería venir de la autenticación

    public MainMenuFrame() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Menú Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Barra de título con información de usuario
        JLabel lblWelcome = new JLabel("Bienvenido - Rol: " + userRole, SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblWelcome, BorderLayout.NORTH);

        // Panel de botones según rol
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Botones comunes a todos los roles
        JButton btnChangePass = new JButton("Cambiar Mi Contraseña");
        btnChangePass.addActionListener(e -> new ChangePasswordFrame().setVisible(true));
        buttonPanel.add(btnChangePass);

        // Botones según rol
        if (userRole.equals("admin")) {
            // Botones solo para admin
           
            JButton btnReports = new JButton("Reportes");
            btnReports.addActionListener(e -> new ReportesJinterno().setVisible(true));
            buttonPanel.add(btnReports);

         
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
            btnMenu.addActionListener(e -> new Menu1_5().setVisible(true));
            buttonPanel.add(btnMenu);
        }

        panel.add(new JScrollPane(buttonPanel), BorderLayout.CENTER);

        add(panel);
    }
}