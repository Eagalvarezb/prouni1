package JFrameN;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexion.CreateConection;
import Controlador.UsuarioSistemaController;
import Modelo.UsuarioSistema;
import Modelo.Empleado;

public class CreateUserFrame extends JFrame {
    private UsuarioSistemaController controller;
    private JTextField txtNombre, txtApellido, txtPassword;
    private JLabel lblUsername;
    private JButton btnCreate;
    private CreateConection connFactory = new CreateConection();

    public CreateUserFrame() {
        this.controller = new UsuarioSistemaController();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Crear Nuevo Usuario");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panel.add(txtNombre, gbc);

        // Apellido
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Apellido:"), gbc);

        gbc.gridx = 1;
        txtApellido = new JTextField(15);
        panel.add(txtApellido, gbc);

        // Usuario generado
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Usuario generado:"), gbc);

        gbc.gridx = 1;
        lblUsername = new JLabel("");
        panel.add(lblUsername, gbc);

        // Contraseña
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        txtPassword = new JPasswordField(15);
        panel.add(txtPassword, gbc);

        // Botón Generar Usuario
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        JButton btnGenerate = new JButton("Generar Usuario");
        btnGenerate.addActionListener(e -> generarYMostrarUsuario());
        panel.add(btnGenerate, gbc);

        // Botón Crear
        gbc.gridy = 5;
        btnCreate = new JButton("Crear Usuario");
        btnCreate.setEnabled(false);
        btnCreate.addActionListener(e -> crearUsuario());
        panel.add(btnCreate, gbc);

        add(panel);
    }

    private void generarYMostrarUsuario() {
        String nombre = txtNombre.getText().trim();
    String apellido = txtApellido.getText().trim();

    if (nombre.isEmpty() || apellido.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nombre y apellido son requeridos", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        Empleado emp = new Empleado();
        emp.setNombre(nombre);
        emp.setApellido(apellido);

        String username = generarUsuarioUnico(emp);
        lblUsername.setText(username);
        btnCreate.setEnabled(true);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error generando usuario: " + ex.getMessage(), 
                                    "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace(); // Para debug
    }
    }

    private String generarUsuarioUnico(Empleado emp) throws SQLException {
        String baseUsername = (emp.getNombre().charAt(0) + emp.getApellido()).toLowerCase();
        String username = baseUsername;
        int counter = 1;

        // Verificar si el usuario ya existe
        String checkSql = "SELECT COUNT(*) FROM public.usuarios WHERE username = ?";
        try (Connection conn = connFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(checkSql)) {
            
            while (true) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        break; // Usuario no existe, podemos usarlo
                    }
                }
                username = baseUsername + counter;
                counter++;
            }
        }

        return username;
    }

    private void crearUsuario() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String password = new String(txtPassword.getText());
        String username = lblUsername.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || password.isEmpty() || username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son requeridos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Empleado emp = new Empleado();
            emp.setNombre(nombre);
            emp.setApellido(apellido);

            UsuarioSistema user = new UsuarioSistema();
            user.setUsername(username);
            user.setPassword(password);

            // Insertar directamente usando la consulta SQL proporcionada
            String sql = "INSERT INTO public.usuarios (nombre, apellido, username, password) VALUES (?, ?, ?, ?)";
            try (Connection conn = connFactory.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
                ps.setString(1, emp.getNombre());
                ps.setString(2, emp.getApellido());
                ps.setString(3, username);
                ps.setString(4, user.getPassword());
                
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Usuario creado exitosamente\nUsuario: " + username, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    throw new SQLException("No se pudo crear el usuario");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}