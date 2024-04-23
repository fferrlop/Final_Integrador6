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
import java.util.stream.Collectors;

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
        constraints.insets = new Insets(5, 0, 5, 0);

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


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0)); // Create a new panel with a FlowLayout
        buttonPanel.setBackground(customColor);

        JButton eliminarVentasButton = new JButton("Eliminar");
        eliminarVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarVenta();
            }
        });

        buttonPanel.add(eliminarVentasButton); // Add the button to the new panel

        JButton filtrarVentasButton = new JButton("Filtrar");
        filtrarVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarVentas();
            }
        });

        buttonPanel.add(filtrarVentasButton); // Add the button to the new panel

        constraints.gridy = 4;
        panel.add(buttonPanel, constraints); // Add the new panel to the original panel

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

        Ventas venta = new Ventas(nombreComprador, cantidad, fecha);
        ventas.add(venta);
        System.out.println("Venta agregada: " + nombreComprador + " | " + cantidad + "€ | " + fechaStr); // Imprime un mensaje cuando se agrega una venta

        try (FileWriter writer = new FileWriter("src/main/java/ArchivosGuardados/informacionVentas.txt", true)) {
            String ventaStr = venta.getNombreComprador() + " | " + venta.getCantidad() + "€ | " + venta.getFecha().format(formatter) + "\n";
            writer.write(ventaStr);
            System.out.println("Venta escrita en el archivo: " + ventaStr); // Imprime un mensaje cuando se escribe en el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarVenta() {
        try {
            // Leer todas las líneas del archivo
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/ArchivosGuardados/informacionVentas.txt"));

            // Mostrar las ventas al usuario y pedirle que seleccione una para eliminar
            String ventaParaEliminar = (String) JOptionPane.showInputDialog(null, "Seleccione la venta que desea eliminar:",
                    "Eliminar venta", JOptionPane.QUESTION_MESSAGE, null, lines.toArray(), lines.get(0));

            if (ventaParaEliminar != null) {
                // Filtrar la lista para eliminar la venta seleccionada
                List<String> updatedLines = lines.stream()
                        .filter(line -> !line.equals(ventaParaEliminar))
                        .collect(Collectors.toList());

                // Reescribir el archivo con la lista actualizada
                Files.write(Paths.get("src/main/java/ArchivosGuardados/informacionVentas.txt"), updatedLines);

                JOptionPane.showMessageDialog(null, "Venta eliminada exitosamente.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void filtrarVentas() {
        String[] options = {"Nombre", "Precio", "Fecha"};
        int response = JOptionPane.showOptionDialog(null, "Seleccione el criterio de filtrado:", "Filtrar ventas",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (response) {
            case 0:
                filtrarPorNombre();
                break;
            case 1:
                filtrarPorPrecio();
                break;
            case 2:
                filtrarPorFecha();
                break;
            default:
                break;
        }
    }

    private void filtrarPorNombre() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre para filtrar:");
        if (nombre != null) {
            try {
                List<String> lines = Files.readAllLines(Paths.get("src/main/java/ArchivosGuardados/informacionVentas.txt"));
                List<String> filteredLines = lines.stream()
                        .filter(line -> line.startsWith(nombre + " |"))
                        .collect(Collectors.toList());

                textArea.setText(String.join("\n", filteredLines));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void filtrarPorPrecio() {
        String precioStr = JOptionPane.showInputDialog("Ingrese el precio para filtrar:");
        if (precioStr != null) {
            try {
                double precio = Double.parseDouble(precioStr);
                List<String> lines = Files.readAllLines(Paths.get("src/main/java/ArchivosGuardados/informacionVentas.txt"));
                List<String> filteredLines = lines.stream()
                        .filter(line -> {
                            String[] parts = line.split(" \\| ");
                            return parts.length > 1 && Double.parseDouble(parts[1].replace("€", "")) == precio;
                        })
                        .collect(Collectors.toList());

                textArea.setText(String.join("\n", filteredLines));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void filtrarPorFecha() {
        String fechaStr = JOptionPane.showInputDialog("Ingrese la fecha para filtrar (formato: dd/MM/yyyy):");
        if (fechaStr != null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fecha = LocalDate.parse(fechaStr, formatter);
                List<String> lines = Files.readAllLines(Paths.get("src/main/java/ArchivosGuardados/informacionVentas.txt"));
                List<String> filteredLines = lines.stream()
                        .filter(line -> {
                            String[] parts = line.split(" \\| ");
                            return parts.length > 2 && LocalDate.parse(parts[2], formatter).isEqual(fecha);
                        })
                        .collect(Collectors.toList());

                textArea.setText(String.join("\n", filteredLines));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}