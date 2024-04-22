package DatosDinámicos;

import DatosDinámicos2.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EsteticaDatos extends JFrame {
    // Crear un JTextArea para mostrar los resultados
    JTextArea textArea = new JTextArea();

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

        // Crear un JPanel con FlowLayout y agregar el botón a este
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(customColor); // Establecer el color de fondo del JPanel
        buttonPanel.add(button);

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

        // Obtener y mostrar algunos datos
        String text = "Primer elemento de listaReales: " + modelado.getListaReales().obtenerElemento(0) + "\n";
        text += "Primer elemento de listaPares: " + modelado.getListaPares().obtenerElemento(0).getPrimero() + ", " + modelado.getListaPares().obtenerElemento(0).getSegundo() + "\n";
        textArea.setText(text);
    }

    public void setTextArea(String text) {
        this.textArea.setText(text);
    }
}