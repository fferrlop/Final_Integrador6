package MapasYAsociacion;

import javax.swing.*;
import java.awt.*;

public class EsteticaMapas extends JFrame {
    public EsteticaMapas() {

        setTitle("Mapas y Asociación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        Color customColor = new Color(30, 62, 109);
        getContentPane().setBackground(customColor);
    }
}