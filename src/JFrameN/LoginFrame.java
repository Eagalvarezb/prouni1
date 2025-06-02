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
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel= new JPanel(new GridBagLayout()){
            @Override
            protected void paintComponent (Graphics g){
                super.paintComponent(g);
                ImageIcon imageIcon =new ImageIcon(getClass().getClassLoader().getResource("Img/Fondo.jpg"));
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setOpaque(false);//para que se vea la imagen de fondo

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel titleLabel = new JLabel("Inicar Sesión",SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial",Font.BOLD,18));
        titleLabel.setForeground(Color.BLACK);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth=2;
        panel.add(titleLabel, gbc);


        // Usuario
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 3;
        txtUsername = new JTextField(15);
        panel.add(txtUsername, gbc);

        // Contraseña
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 3;
        txtPassword = new JPasswordField(15);
        panel.add(txtPassword, gbc);

        // Botones
        JPanel buttonPanel = new JPanel(new GridLayout(1,3, 10, 10));
        buttonPanel.setOpaque(false);

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
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        panel.add(buttonPanel, gbc);

        add(panel);
    }

    private void verificarCredenciales() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        
        if(username.isEmpty()|| password.isEmpty()){
            JOptionPane.showMessageDialog(this, "usuario y contraseña son necesarios", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            controller.verificarCredenciales(username, password);
            JOptionPane.showMessageDialog(this, "Credenciales válidas", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Aquí va la lógica para abrir el menú principal según el rol
            new MainMenuFrame().setVisible(true);
       
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
             LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }
}