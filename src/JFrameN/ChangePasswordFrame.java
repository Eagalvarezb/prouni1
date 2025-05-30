package JFrameN;
import javax.swing.*;
import java.awt.*;
import Controlador.UsuarioSistemaController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordFrame extends JFrame {
    private UsuarioSistemaController controller;
    private JTextField txtUsername;
    private JPasswordField txtCurrentPassword, txtNewPassword;
    private JButton btnChange;

    public ChangePasswordFrame() {
        this.controller = new UsuarioSistemaController();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Cambiar Contraseña");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        txtUsername = new JTextField(15);
        panel.add(txtUsername, gbc);

        // Contraseña Actual
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Contraseña Actual:"), gbc);

        gbc.gridx = 1;
        txtCurrentPassword = new JPasswordField(15);
        panel.add(txtCurrentPassword, gbc);

        // Nueva Contraseña
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Nueva Contraseña:"), gbc);

        gbc.gridx = 1;
        txtNewPassword = new JPasswordField(15);
        panel.add(txtNewPassword, gbc);

        // Botón Cambiar
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        btnChange = new JButton("Cambiar Contraseña");
        btnChange.addActionListener(e -> cambiarPassword());
        panel.add(btnChange, gbc);

        add(panel);
    }

    private void cambiarPassword() {
        String username = txtUsername.getText();
        String currentPassword = new String(txtCurrentPassword.getPassword());
        String newPassword = new String(txtNewPassword.getPassword());

        try {
            // Primero verificar credenciales actuales
            controller.verificarCredenciales(username, currentPassword);
            
            // Luego actualizar
            controller.actualizar(username, newPassword);
            JOptionPane.showMessageDialog(this, "Contraseña cambiada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}