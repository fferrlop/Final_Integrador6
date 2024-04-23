package MapasYAsociacion;

import javax.swing.*;
import java.awt.*;

public class EsteticaMapas extends JFrame {
    public EsteticaMapas() {

        setTitle("Mapas y Asociación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        Color customColor = new Color(30, 62, 109);
        getContentPane().setBackground(customColor);

        JLabel label = new JLabel("Mapas y Asociación de Datos", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
        getContentPane().add(label, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        char column = 'A';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JLabel cellLabel = new JLabel(String.valueOf(column) + (j + 1));
                cellPanel.add(cellLabel);
                gridPanel.add(cellPanel);
            }
            column++;
        }

        getContentPane().add(gridPanel, BorderLayout.CENTER);

        JButton button = new JButton("Escribir coordenada");
        getContentPane().add(button, BorderLayout.SOUTH);
    }
}