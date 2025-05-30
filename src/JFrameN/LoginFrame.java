package JFrameN;
import javax.swing.*;
import java.awt.*;
import Controlador.UsuarioSistemaController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private UsuarioSistemaController controller;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public LoginFrame() {
        this.controller = new UsuarioSistemaController();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Inicio de Sesión");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        txtUsername = new JTextField(15);
        panel.add(txtUsername, gbc);

        // Contraseña
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        txtPassword = new JPasswordField(15);
        panel.add(txtPassword, gbc);

        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.addActionListener(e -> verificarCredenciales());
        buttonPanel.add(btnLogin);

        JButton btnCreateUser = new JButton("Crear Usuario");
        btnCreateUser.addActionListener(e -> abrirCrearUsuario());
        buttonPanel.add(btnCreateUser);

        JButton btnChangePass = new JButton("Cambiar Contraseña");
        btnChangePass.addActionListener(e -> abrirCambiarPassword());
        buttonPanel.add(btnChangePass);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        add(panel);
    }

    private void verificarCredenciales() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        try {
            controller.verificarCredenciales(username, password);
            JOptionPane.showMessageDialog(this, "Credenciales válidas", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Aquí iría la lógica para abrir el menú principal según el rol
            new MainMenuFrame().setVisible(true);
            JOptionPane.showMessageDialog(this, "Login exitoso - Implementa tu menú principal aquí");
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirCrearUsuario() {
        new CreateUserFrame().setVisible(true);
    }

    private void abrirCambiarPassword() {
        new ChangePasswordFrame().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}