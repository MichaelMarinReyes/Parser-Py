package frontend.reportessintactico;

/**
 *
 * @author michael
 */
public class VisualizarBloqueCodigo extends javax.swing.JFrame {

    /**
     * Creates new form VisualizarGrafico
     */
    public VisualizarBloqueCodigo() {
        this.setTitle("Gráfico");
        initComponents();
        labelGrafico.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        labelGrafico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        jPanel1.setLayout(new java.awt.BorderLayout());

        labelGrafico.setBackground(new java.awt.Color(153, 153, 153));
        labelGrafico.setText("jLabel1");
        jPanel1.add(labelGrafico, java.awt.BorderLayout.PAGE_START);

        scrollPane.setViewportView(jPanel1);

        getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelGrafico;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables

    public void obtenerLexema(String lexema) {
        labelGrafico.setText(lexema);
    }
}
