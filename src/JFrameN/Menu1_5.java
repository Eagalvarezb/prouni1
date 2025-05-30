package JFrameN;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Controlador.PedidosController;
import Controlador.facturacion;
import Modelo.Productos;

public class Menu1_5 extends JFrame {
    private JDesktopPane desktopPane;
    private JLabel lblImagenLogo;
    private JLabel lblImagenTaco;
    private JLabel lblImagenRestaurante;
    private JLabel lblImagenDecoracion;
    private JButton botonOrdenar;
     private PedidosController pedidoController;

    public Menu1_5() {
        super("Menú");
         pedidoController = new PedidosController();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);
        desktopPane.setLayout(new BorderLayout());

        // Panel Superior (Logo)
        lblImagenLogo = new JLabel();
        addResizeListener(lblImagenLogo, "src/Img/Logo.png");

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(lblImagenLogo);
        desktopPane.add(topPanel, BorderLayout.NORTH);

        // Panel Central (Imagen de fondo y decoración)
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        lblImagenRestaurante = new JLabel();
        lblImagenDecoracion = new JLabel();

        addResizeListener(lblImagenRestaurante, "src/Img/Restaurante.jpg");
        addResizeListener(lblImagenDecoracion, "src/Img/D.png");

        centerPanel.add(lblImagenRestaurante);
        centerPanel.add(lblImagenDecoracion);
        desktopPane.add(centerPanel, BorderLayout.CENTER);

        // Panel Inferior (Botón y otro gráfico)
        JPanel bottomPanel = new JPanel(new BorderLayout());

        lblImagenTaco = new JLabel();
        addResizeListener(lblImagenTaco, "src/Img/Taco.png");

        botonOrdenar = new JButton("ORDENAR");
        botonOrdenar.setFont(new Font("Engravers MT", Font.BOLD, 24));
        botonOrdenar.setBackground(new Color(153, 0, 0));
        botonOrdenar.setForeground(new Color(255, 204, 0));
        botonOrdenar.addActionListener(e -> abrirMenu2());

        bottomPanel.add(lblImagenTaco, BorderLayout.WEST);
        bottomPanel.add(botonOrdenar, BorderLayout.CENTER);

        desktopPane.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void abrirMenu2() {
        try {
            Menu2 menu2 = new Menu2(pedidoController); 
            desktopPane.add(menu2);
            menu2.setVisible(true);
            menu2.setLocation(50, 50);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al abrir el menú: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addResizeListener(JLabel label, String imagePath) {
        label.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setImage(label, imagePath);
            }
        });
    }

    private void setImage(JLabel label, String path) {
        ImageIcon imgIcon = new ImageIcon(path);
        int width = label.getWidth() > 0 ? label.getWidth() : imgIcon.getIconWidth();
        int height = label.getHeight() > 0 ? label.getHeight() : imgIcon.getIconHeight();

        ImageIcon resizedIcon = new ImageIcon(
                imgIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        label.setIcon(resizedIcon);
        label.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Menu1_5::new);
    }
}
