package DatosDinámicos;

import javax.swing.*;
import java.awt.*;

public class EsteticaDatos extends JFrame {
    public EsteticaDatos() {

        setTitle("Datos dinámicos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        Color customColor = new Color(30, 62, 109);
        getContentPane().setBackground(customColor);
    }
}