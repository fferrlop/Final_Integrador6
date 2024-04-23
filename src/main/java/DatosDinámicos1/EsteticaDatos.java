package DatosDinámicos1;

import DatosDinámicos2.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.*;

public class EsteticaDatos extends JFrame {
    // Crear un JTextArea para mostrar los resultados
    JTextArea textArea = new JTextArea();
    // Crear un JTextField para ingresar los números
    JTextField textField = new JTextField(20);

    public EsteticaDatos() {

        setTitle("Datos dinámicos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 500);
        setLocationRelativeTo(null);

        Color customColor = new Color(30, 62, 109);
        getContentPane().setBackground(customColor);

        JLabel label = new JLabel("Datos dinámicos", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
        getContentPane().add(label, BorderLayout.NORTH);

        // Crear un botón
        JButton button = new JButton("Modelado Multidimensional");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al método main de ModeladoMultidimensional
                executeModeladoMultidimensional();
            }
        });

        // Crear un botón de guardar
        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al método para guardar los datos
                saveData();
            }
        });


        // Crear un botón de eliminar
        JButton deleteButton = new JButton("Eliminar");
        deleteButton.setPreferredSize(new Dimension(100, 25)); // Establecer un tamaño fijo para el botón de eliminar
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una ventana emergente para eliminar una pareja
                JDialog deleteDialog = new JDialog(EsteticaDatos.this, "Eliminar pareja", true);
                deleteDialog.setLayout(new FlowLayout());

                // Crear un JTextField para que el usuario ingrese la pareja que desea eliminar
                JTextField deleteField = new JTextField(20);
                deleteDialog.add(deleteField);

                // Crear un botón "Confirmar" para realizar la eliminación
                JButton confirmButton = new JButton("Confirmar");
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Llamar al método para eliminar la pareja
                        deleteData(deleteField.getText());
                        deleteDialog.dispose();
                    }
                });
                deleteDialog.add(confirmButton);

                deleteDialog.pack();
                deleteDialog.setLocationRelativeTo(null);
                deleteDialog.setVisible(true);
            }
        });

        // Crear un botón de ordenar
        // Crear un botón de ordenar
        JButton sortButton = new JButton("Ordenar");
        sortButton.setPreferredSize(new Dimension(100, 25)); // Establecer un tamaño fijo para el botón de ordenar
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una ventana emergente para elegir el criterio de ordenación
                JDialog sortDialog = new JDialog(EsteticaDatos.this, "Ordenar parejas", true);
                sortDialog.setLayout(new FlowLayout());

                // Crear un botón "Ordenar por primer número" para realizar la ordenación
                JButton firstNumberButton = new JButton("Ordenar por primer número");
                firstNumberButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Llamar al método para ordenar los datos por el primer número
                        sortDataByFirstNumber();
                        sortDialog.dispose();
                    }
                });
                sortDialog.add(firstNumberButton);

                // Crear un botón "Ordenar por segundo número" para realizar la ordenación
                JButton secondNumberButton = new JButton("Ordenar por segundo número");
                secondNumberButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Llamar al método para ordenar los datos por el segundo número
                        sortDataBySecondNumber();
                        sortDialog.dispose();
                    }
                });
                sortDialog.add(secondNumberButton);

                sortDialog.pack();
                sortDialog.setLocationRelativeTo(null); // Centrar la ventana emergente en la pantalla
                sortDialog.setVisible(true);
            }
        });

// Crear un JPanel con FlowLayout para contener el botón de eliminar
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(customColor);
        buttonPanel.add(deleteButton);
        buttonPanel.add(sortButton);

// Crear un JPanel para contener el JTextArea y el botón de eliminar
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setOpaque(false);
        panel2.add(buttonPanel, BorderLayout.NORTH);
        panel2.add(textArea, BorderLayout.CENTER);

// Agregar el JPanel al JFrame
        add(panel2);

// Crear un JPanel con FlowLayout y agregar los botones y el JTextField a este
        JPanel buttonPanel2 = new JPanel(new FlowLayout());
        buttonPanel2.setBackground(customColor);
        buttonPanel2.add(button);
        buttonPanel2.add(textField);
        buttonPanel2.add(saveButton);

// Agregar el JTextArea a un JScrollPane
        textArea.setEditable(false); // Hacer el JTextArea no editable
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450, 150)); // Establecer el tamaño preferido del JScrollPane
        scrollPane.setMaximumSize(new Dimension(450, 150)); // Establecer el tamaño máximo del JScrollPane

// Crear un JPanel adicional y agregar el JScrollPane y panel2 a este
        JPanel scrollPanePanel = new JPanel();
        scrollPanePanel.setLayout(new BoxLayout(scrollPanePanel, BoxLayout.PAGE_AXIS));
        scrollPanePanel.setBackground(customColor); // Establecer el color de fondo del JPanel
        scrollPanePanel.add(scrollPane);
        scrollPanePanel.add(panel2); // Agregar panel2 a scrollPanePanel

// Crear un JPanel con BorderLayout y agregar el buttonPanel y el scrollPanePanel a este
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel2, BorderLayout.NORTH);
        panel.add(scrollPanePanel, BorderLayout.CENTER);

// Agregar el JPanel a la ventana
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    // Método para ejecutar ModeladoMultidimensional y mostrar los resultados en el JTextArea
    public void executeModeladoMultidimensional() {
        try {
            // Crear un BufferedReader para leer el archivo
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/ArchivosGuardados/ListaPares.txt"));

            // Limpiar el JTextArea antes de agregar el contenido del archivo
            textArea.setText("");

            // Leer todas las líneas del archivo y agregarlas al JTextArea
            String line = reader.readLine();
            while (line != null) {
                textArea.append(line + "\n");
                line = reader.readLine();
            }

            // Cerrar el BufferedReader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para guardar los datos en un archivo
    public void saveData() {
        String input = textField.getText();
        // Verificar si el texto ingresado tiene el formato correcto (x, y)
        if (input.matches("\\(\\d+, \\d+\\)")) {
            try {
                // Crear la carpeta ArchivosGuardados si no existe
                File directory = new File("ArchivosGuardados");
                if (!directory.exists()) {
                    directory.mkdir();
                }

                FileWriter writer = new FileWriter("src/main/java/ArchivosGuardados/ListaPares.txt", true);
                writer.write(input + "\n");
                writer.close();

                textField.setText("");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese los números en el formato correcto (x, y)");
        }
    }

    //eliminar informacion
    public void deleteData(String dataToDelete) {
        try {
            Path path = Paths.get("src/main/java/ArchivosGuardados/ListaPares.txt");
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            // Eliminar la pareja de la lista
            lines.remove(dataToDelete);

            // Reescribir el archivo con las parejas restantes
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTextArea(String text) {
        this.textArea.setText(text);
    }

    public void sortData() {
        // Assuming you have a List<Integer> data to be sorted
        List<Integer> data = new ArrayList<>();

        // Add data to the list
        // data.add();

        // Sort the list
        Collections.sort(data);

        // Print sorted data or do something with it
        for (int num : data) {
            System.out.println(num);
        }
    }

    public void sortDataByFirstNumber() {
        try {
            Path path = Paths.get("src/main/java/ArchivosGuardados/ListaPares.txt");
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            // Convertir las líneas a objetos Pareja
            List<Pareja> data = lines.stream()
                    .filter(line -> line.matches("\\(\\d+, \\d+\\)"))  // Only process lines that match the format "(d, d)"
                    .map(line -> {
                        String[] numbers = line.replaceAll("[()]", "").split(", ");
                        return new Pareja(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
                    })
                    .collect(Collectors.toList());

            // Ordenar la lista por el primer número
            Collections.sort(data, Comparator.comparingInt(Pareja::getPrimero));

            // Convertir los objetos Pareja a texto y escribirlos en el archivo
            List<String> sortedLines = data.stream()
                    .map(pareja -> "(" + pareja.getPrimero() + ", " + pareja.getSegundo() + ")")
                    .collect(Collectors.toList());
            Files.write(path, sortedLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortDataBySecondNumber() {
        try {
            Path path = Paths.get("src/main/java/ArchivosGuardados/ListaPares.txt");
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            // Convertir las líneas a objetos Pareja
            List<Pareja> data = lines.stream()
                    .filter(line -> line.matches("\\(\\d+, \\d+\\)"))  // Only process lines that match the format "(d, d)"
                    .map(line -> {
                        String[] numbers = line.replaceAll("[()]", "").split(", ");
                        return new Pareja(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
                    })
                    .collect(Collectors.toList());

            // Ordenar la lista por el segundo número
            Collections.sort(data, Comparator.comparingInt(Pareja::getSegundo));

            // Convertir los objetos Pareja a texto y escribirlos en el archivo
            List<String> sortedLines = data.stream()
                    .map(pareja -> "(" + pareja.getPrimero() + ", " + pareja.getSegundo() + ")")
                    .collect(Collectors.toList());
            Files.write(path, sortedLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}