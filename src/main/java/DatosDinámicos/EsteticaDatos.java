package DatosDinámicos;

import DatosDinámicos2.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EsteticaDatos extends JFrame {
    // Crear un JTextArea para mostrar los resultados
    JTextArea textArea = new JTextArea();
    // Crear un JTextField para ingresar los números
    JTextField textField = new JTextField(20);

    public EsteticaDatos() {

        setTitle("Datos dinámicos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
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

        // Crear un JPanel con FlowLayout y agregar el botón y el JTextField a este
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(customColor); // Establecer el color de fondo del JPanel
        buttonPanel.add(button);
        buttonPanel.add(textField);

        // Agregar el JTextArea a un JScrollPane
        textArea.setEditable(false); // Hacer el JTextArea no editable
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450, 150)); // Establecer el tamaño preferido del JScrollPane
        scrollPane.setMaximumSize(new Dimension(450, 150)); // Establecer el tamaño máximo del JScrollPane

        // Crear un JPanel adicional y agregar el JScrollPane a este
        JPanel scrollPanePanel = new JPanel();
        scrollPanePanel.setLayout(new BoxLayout(scrollPanePanel, BoxLayout.PAGE_AXIS));
        scrollPanePanel.setBackground(customColor); // Establecer el color de fondo del JPanel
        scrollPanePanel.add(scrollPane);

        // Crear un JPanel con BorderLayout y agregar el buttonPanel y el scrollPanePanel a este
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPanePanel, BorderLayout.CENTER);

        // Agregar el JPanel a la ventana
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    // Método para ejecutar ModeladoMultidimensional y mostrar los resultados en el JTextArea
    public void executeModeladoMultidimensional() {
        // Crear una instancia de ModeladoMultidimensional
        ModeladoMultidimensional modelado = new ModeladoMultidimensional();

        // Obtener el número ingresado en el JTextField
        String input = textField.getText();

        // Usar el número ingresado en lugar de los números predefinidos
        // Aquí debes adaptar el código según cómo quieras usar el número ingresado
        // Este es solo un ejemplo
        String text = "Número ingresado: " + input + "\n";
        textArea.setText(text);
    }

    public void setTextArea(String text) {
        this.textArea.setText(text);
    }
}