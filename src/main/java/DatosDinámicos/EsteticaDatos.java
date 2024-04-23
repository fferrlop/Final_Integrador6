package DatosDinámicos;

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
import java.nio.file.*;
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

// Crear un JPanel con FlowLayout para contener el botón de eliminar
        JPanel deleteButtonPanel = new JPanel(new FlowLayout());
        deleteButtonPanel.setOpaque(true);
        deleteButtonPanel.setBackground(customColor);
        deleteButtonPanel.add(deleteButton);

// Crear un JPanel para contener el JTextArea y el botón de eliminar
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setOpaque(false);
        panel2.add(deleteButtonPanel, BorderLayout.NORTH);
        panel2.add(textArea, BorderLayout.CENTER);

// Agregar el JPanel al JFrame
        add(panel2);

// Crear un JPanel con FlowLayout y agregar los botones y el JTextField a este
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(customColor);
        buttonPanel.add(button);
        buttonPanel.add(textField);
        buttonPanel.add(saveButton);

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
        panel.add(buttonPanel, BorderLayout.NORTH);
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
                if (! directory.exists()){
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
}