package IndexacionYVisualizacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

public class EsteticaIndexacion extends JFrame {
    private FileIndexer fileIndexer;

    public EsteticaIndexacion() {
        fileIndexer = new FileIndexer();

        setTitle("Indexación y Visualización");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        Color customColor = new Color(30, 62, 109);
        getContentPane().setBackground(customColor);

        JLabel label = new JLabel("Indexación y Visualización de archivos", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 20));
        getContentPane().add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); // Make the buttonPanel transparent

        JButton indexButton = new JButton("Indexar archivos");
        indexButton.setMaximumSize(new Dimension(200, indexButton.getMinimumSize().height)); // Limit the maximum width
        indexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String directoryPath = JOptionPane.showInputDialog("Ingrese la ruta del directorio a indexar:");
                if (directoryPath != null) {
                    fileIndexer.indexFiles(directoryPath);
                    JOptionPane.showMessageDialog(null, "Archivos indexados exitosamente.");
                }
            }
        });
        buttonPanel.add(indexButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton searchButton = new JButton("Buscar archivo");
        searchButton.setMaximumSize(new Dimension(200, searchButton.getMinimumSize().height)); // Limit the maximum width
        // Botón para buscar archivos
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog("Ingrese el nombre del archivo a buscar:");
                if (fileName != null) {
                    List<IndexFile> files = fileIndexer.searchFiles(fileName);
                    if (!files.isEmpty()) {
                        StringBuilder filesList = new StringBuilder();
                        for (IndexFile file : files) {
                            File actualFile = new File(file.getPath());
                            filesList.append(file.getName())
                                    .append(" (")
                                    .append(file.getPath())
                                    .append(") - Última modificación: ")
                                    .append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(actualFile.lastModified()))
                                    .append("\n");
                        }
                        JOptionPane.showMessageDialog(null, "Archivos encontrados:\n" + filesList.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontraron archivos.");
                    }
                }
            }
        });
        buttonPanel.add(searchButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton listButton = new JButton("Listar archivos");
        listButton.setMaximumSize(new Dimension(200, listButton.getMinimumSize().height)); // Limit the maximum width
        // Botón para listar archivos
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<IndexFile> files = fileIndexer.listFiles();
                if (!files.isEmpty()) {
                    StringBuilder filesList = new StringBuilder();
                    for (IndexFile file : files) {
                        filesList.append(file.getName()).append(" (").append(file.getPath()).append(")\n");
                    }
                    JOptionPane.showMessageDialog(null, "Archivos indexados:\n" + filesList.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No hay archivos indexados.");
                }
            }
        });
        buttonPanel.add(listButton);

        JPanel gridPanel = new JPanel(new GridBagLayout());
        gridPanel.setOpaque(false);
        gridPanel.add(buttonPanel);

        getContentPane().add(gridPanel, BorderLayout.CENTER);
    }
}