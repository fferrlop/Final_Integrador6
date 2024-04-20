package EstructuraInterfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class InterfazUsuario extends JFrame {

    public InterfazUsuario() {
        setTitle("Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/fondoUAX.jpg"));
        JLabel background = new JLabel(imageIcon);
        background.setSize(background.getPreferredSize());

        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());

        layeredPane.add(background, BorderLayout.CENTER);
        layeredPane.setLayer(background, 1);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);

        JButton botonDatosDinamicos = new JButton("Datos dinámicos");
        JButton botonAnalisisOrganizacion = new JButton("Analisis y Organización de información");
        JButton botonMapasAsociacion = new JButton("Mapas y Asociación de Datos");
        JButton botonIndexacionVisualizacion = new JButton("Indexación y Visualización de archivos");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 0, 10, 50); // 10 pixels of space at the top and bottom, 50 pixels of space on the right

        gbc.gridy = 0;
        buttonPanel.add(botonDatosDinamicos, gbc);

        gbc.gridy = 1;
        buttonPanel.add(botonAnalisisOrganizacion, gbc);

        gbc.gridy = 2;
        buttonPanel.add(botonMapasAsociacion, gbc);

        gbc.gridy = 3;
        buttonPanel.add(botonIndexacionVisualizacion, gbc);

        layeredPane.add(buttonPanel, BorderLayout.EAST);
        layeredPane.setLayer(buttonPanel, 2);

        layeredPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Image originalImage = imageIcon.getImage();
                Image scaledImage = originalImage.getScaledInstance(layeredPane.getWidth(), layeredPane.getHeight(), Image.SCALE_SMOOTH);
                background.setIcon(new ImageIcon(scaledImage));
                background.setSize(layeredPane.getSize());
            }
        });

        setLocationRelativeTo(null);
        add(layeredPane);

        setVisible(true);
    }
}