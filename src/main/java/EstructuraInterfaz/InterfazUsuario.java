package EstructuraInterfaz;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class InterfazUsuario extends JFrame {

    public InterfazUsuario() {
        setTitle("Inicio");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());

        JButton botonDatosDinamicos = new JButton("Datos dinámicos");
        JButton botonAnalisisOrganizacion = new JButton("Analisis y Organización de información");
        JButton botonMapasAsociacion = new JButton("Mapas y Asociación de Datos");
        JButton botonIndexacionVisualizacion = new JButton("Indexación y Visualización de archivos");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(6, 0, 10, 0); // 10 pixels of space at the top and bottom
        add(botonDatosDinamicos, gbc);

        gbc.gridy = 1;
        add(botonAnalisisOrganizacion, gbc);

        gbc.gridy = 2;
        add(botonMapasAsociacion, gbc);

        gbc.gridy = 3;
        add(botonIndexacionVisualizacion, gbc);

        setVisible(true);
    }
}