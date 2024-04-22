package AnalisisYOrganizacion;

import javax.swing.*;
import java.awt.*;

public class EsteticaAnalisis extends JFrame {
    public EsteticaAnalisis() {

        setTitle("Analisis y Organización");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        Color customColor = new Color(30, 62, 109);
        getContentPane().setBackground(customColor);
        getContentPane().setLayout(new BorderLayout());

        JLabel label = new JLabel("Analisis y Organización de información", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        getContentPane().add(label, BorderLayout.NORTH);
    }
}