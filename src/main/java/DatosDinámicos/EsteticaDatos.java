package DatosDinámicos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EsteticaDatos extends JFrame {
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
                ModeladoMultidimensional.main(new String[]{});
            }
        });

        // Crear un JPanel con FlowLayout y agregar el botón a este
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(customColor); // Establecer el color de fondo del JPanel
        panel.add(button);

        // Agregar el JPanel a la ventana
        getContentPane().add(panel, BorderLayout.CENTER);
    }
}