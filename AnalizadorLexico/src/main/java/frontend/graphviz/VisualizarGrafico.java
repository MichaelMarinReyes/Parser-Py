package frontend.graphviz;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author michael
 */
public class VisualizarGrafico extends javax.swing.JFrame {

    /**
     * Creates new form VisualizarGrafico
     */
    public VisualizarGrafico() {
        this.setTitle("Gráfico");
        initComponents();
        labelGrafico.setText("");
    }

    public void obtenerLexema(String lexema) {
        // Crear el código DOT para el diagrama
        String dotCode = "digraph G {\n";
        dotCode += "rankdir=LR;";
        dotCode += "node [shape=oval];\n";

        for (int i = 0; i < lexema.length(); i++) {
            dotCode += i + " [label=\"" + lexema.charAt(i) + "\"];\n";
            if (i == lexema.length() - 1) {
                dotCode += i + " [peripheries=2];\n";
            }
            if (i > 0) {
                dotCode += (i - 1) + " -> " + i + ";\n";
            }
        }

        dotCode += "}";

        // Generar el archivo DOT
        try {
            File dotFile = File.createTempFile("graph", ".dot");
            dotFile.deleteOnExit();
            File imageFile = new File(dotFile.getAbsolutePath() + ".png");

            Utils.writeStringToFile(dotCode, dotFile.getAbsolutePath());

            // Ejecutar Graphviz para generar la imagen
            ProcessBuilder processBuilder = new ProcessBuilder("dot", "-Tpng", dotFile.getAbsolutePath(), "-o", imageFile.getAbsolutePath());
            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                // Mostrar la imagen en el JLabel
                labelGrafico.setIcon(new javax.swing.ImageIcon(imageFile.getAbsolutePath()));
                labelGrafico.setPreferredSize(new Dimension(400, 400));
                pack();
            } else {
                System.err.println("Error al generar el gráfico");
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelGrafico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelGrafico.setBackground(new java.awt.Color(153, 153, 153));
        labelGrafico.setText("jLabel1");
        getContentPane().add(labelGrafico, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelGrafico;
    // End of variables declaration//GEN-END:variables

    private static class Utils {

        public static void writeStringToFile(String content, String filePath) throws IOException {
            try (java.io.FileWriter writer = new java.io.FileWriter(filePath)) {
                writer.write(content);
            }
        }
    }
}
