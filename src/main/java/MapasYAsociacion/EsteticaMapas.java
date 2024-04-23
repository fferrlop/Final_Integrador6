package MapasYAsociacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class EsteticaMapas extends JFrame {
    private Map<String, JPanel> cellPanels = new HashMap<>();

    public EsteticaMapas() {

        setTitle("Mapas y Asociación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        Color customColor = new Color(30, 62, 109);
        getContentPane().setBackground(customColor);

        JLabel label = new JLabel("Mapas y Asociación de Datos", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
        getContentPane().add(label, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        char column = 'A';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JLabel cellLabel = new JLabel(String.valueOf(column) + (j + 1));
                cellPanel.add(cellLabel);
                cellPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (cellPanel.getBackground().equals(Color.RED)) {
                            cellPanel.setBackground(null);
                        } else {
                            cellPanel.setBackground(Color.RED);
                            JOptionPane.showMessageDialog(null, "Has pulsado el cuadrante: " + cellLabel.getText());
                        }
                    }
                });
                gridPanel.add(cellPanel);
                cellPanels.put(String.valueOf(column) + (j + 1), cellPanel);
            }
            column++;
        }

        getContentPane().add(gridPanel, BorderLayout.CENTER);

        JButton button = new JButton("Escribir coordenada");
        button.addActionListener(e -> {
            String coordenada = JOptionPane.showInputDialog("Ingrese la coordenada:");
            JPanel cellPanel = cellPanels.get(coordenada);
            if (cellPanel != null) {
                if (cellPanel.getBackground().equals(Color.RED)) {
                    cellPanel.setBackground(null);
                } else {
                    cellPanel.setBackground(Color.RED);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Esta coordenada no existe");
            }
        });
        getContentPane().add(button, BorderLayout.SOUTH);
    }
}