package AnalisisYOrganizacion1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class EsteticaAnalisis extends JFrame {
    private List<Ventas> ventas;
    private JTextArea textArea;

    public EsteticaAnalisis() {
        setTitle("Analisis y Organización");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color customColor = new Color(30, 62, 109);
        getContentPane().setBackground(customColor);
        getContentPane().setLayout(new BorderLayout());

        textArea = new JTextArea(10, 30);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(customColor);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(8, 0, 10, 0);

        JLabel label = new JLabel("Analisis y Organización de información", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
        panel.add(label, constraints);

        constraints.gridy = 1;


        JButton saveButton = new JButton("Guardar información de venta");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSalesInformation();
            }
        });

        panel.add(saveButton, constraints);



        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        constraints.gridy = 3;
        panel.add(scrollPane, constraints);

        JButton mostrarVentasButton = new JButton("Mostrar ventas");
        mostrarVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String content = new String(Files.readAllBytes(Paths.get("src/main/java/ArchivosGuardados/informacionVentas.txt")));
                    textArea.setText(content);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        constraints.gridy = 2;
        panel.add(mostrarVentasButton, constraints);

        getContentPane().add(panel, BorderLayout.NORTH);

        pack();
        setSize(500, 500);

        ventas = new ArrayList<>();
    }



    private void saveSalesInformation() {
        String nombreComprador = JOptionPane.showInputDialog("Nombre del comprador");
        String cantidadStr = JOptionPane.showInputDialog("Cantidad gastada");
        double cantidad = Double.parseDouble(cantidadStr);
        String fechaStr = JOptionPane.showInputDialog("Fecha de la compra (formato: dd/MM/yyyy)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaStr, formatter);

        ventas.add(new Ventas(nombreComprador, cantidad, fecha));
        System.out.println("Venta agregada: " + nombreComprador + " | " + cantidad + " | " + fechaStr); // Imprime un mensaje cuando se agrega una venta

        try (FileWriter writer = new FileWriter("src/main/java/ArchivosGuardados/informacionVentas.txt", true)) {
            for (Ventas venta : ventas) {
                String ventaStr = venta.getNombreComprador() + " | " + venta.getCantidad() + " | " + venta.getFecha().format(formatter) + "\n";
                writer.write(ventaStr);
                System.out.println("Venta escrita en el archivo: " + ventaStr); // Imprime un mensaje cuando se escribe en el archivo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}