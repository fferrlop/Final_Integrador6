package IndexacionYVisualizacion;

import javax.swing.*;
import java.awt.*;

public class EsteticaIndexacion extends JFrame {
    public EsteticaIndexacion() {

        setTitle("Indexación y Visualización");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        Color customColor = new Color(30, 62, 109);
        getContentPane().setBackground(customColor);

        JLabel label = new JLabel("Indexación y Visualización de archivos", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        getContentPane().add(label, BorderLayout.NORTH);
    }
}