package AnalisisYOrganizacion1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EsteticaAnalisis extends JFrame {
    private List<Ventas> ventas;

    public EsteticaAnalisis() {
        setTitle("Analisis y Organización");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        Color customColor = new Color(30, 62, 109);
        getContentPane().setBackground(customColor);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(customColor);

        panel.add(Box.createVerticalGlue());

        JLabel label = new JLabel("Analisis y Organización de información", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
        panel.add(label);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton saveButton = new JButton("Guardar información de venta");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSalesInformation();
            }
        });

        panel.add(saveButton);

        panel.add(Box.createVerticalGlue());

        getContentPane().add(panel);
    }

    private void saveSalesInformation() {
        try (FileWriter writer = new FileWriter("ventas.txt")) {
            for (Ventas venta : ventas) {
                writer.write(venta.getProducto() + ", " + venta.getCantidad() + ", " + venta.getPrecio() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}