package frontend;

import frontend.reporteslexico.ReportesLexicoPanel;
import backend.LeerArchivoTexto;
import frontend.reportessintactico.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author michael
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    EditorPanel editor = new EditorPanel();

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        this.setTitle("PARSER-PY");
        initComponents();
        this.pintarPanel(editor);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedorPanel = new javax.swing.JPanel();
        menuOpciones = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        abrirBoton = new javax.swing.JMenuItem();
        editorTexto = new javax.swing.JMenu();
        reportesBoton = new javax.swing.JMenu();
        reportesLexico = new javax.swing.JMenuItem();
        reportesSintactico = new javax.swing.JMenuItem();
        ayudaBoton = new javax.swing.JMenu();
        acerdaDeBoton = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));

        contenedorPanel.setBackground(new java.awt.Color(255, 255, 0));
        contenedorPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(contenedorPanel, java.awt.BorderLayout.CENTER);

        menuOpciones.setBackground(new java.awt.Color(0, 153, 255));
        menuOpciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menuOpciones.setForeground(new java.awt.Color(153, 153, 153));

        jMenu1.setText("Archivo");

        abrirBoton.setBackground(new java.awt.Color(0, 153, 255));
        abrirBoton.setText("Abrir archivo");
        abrirBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirBotonActionPerformed(evt);
            }
        });
        jMenu1.add(abrirBoton);

        menuOpciones.add(jMenu1);

        editorTexto.setText("Editor");
        editorTexto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editorTextoMouseClicked(evt);
            }
        });
        menuOpciones.add(editorTexto);

        reportesBoton.setText("Reportes");

        reportesLexico.setBackground(new java.awt.Color(0, 153, 255));
        reportesLexico.setText("Reporte Tokens");
        reportesLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesLexicoActionPerformed(evt);
            }
        });
        reportesBoton.add(reportesLexico);

        reportesSintactico.setBackground(new java.awt.Color(0, 153, 255));
        reportesSintactico.setText("Reportes Sintáctico");
        reportesSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesSintacticoActionPerformed(evt);
            }
        });
        reportesBoton.add(reportesSintactico);

        menuOpciones.add(reportesBoton);

        ayudaBoton.setText("Ayuda");
        ayudaBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ayudaBotonMouseClicked(evt);
            }
        });
        menuOpciones.add(ayudaBoton);

        acerdaDeBoton.setText("Acerca de");
        acerdaDeBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acerdaDeBotonMouseClicked(evt);
            }
        });
        menuOpciones.add(acerdaDeBoton);

        setJMenuBar(menuOpciones);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abrirBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirBotonActionPerformed
        LeerArchivoTexto miArchivo = new LeerArchivoTexto();
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT, *.PY", "txt", "py");
        chooser.setFileFilter(filtro);
        int seleccion = chooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            String nombreArchivo = chooser.getSelectedFile().getName();
            String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf('.') + 1).toLowerCase();

            if (extension.equals("txt") || extension.equals("py")) {
                String textoLeido = miArchivo.abrirArchivo(chooser.getSelectedFile().getAbsolutePath());
                this.pintarPanel(editor);
                editor.setAreaEditor(textoLeido);
            } else {
                JOptionPane.showMessageDialog(this, "Solo se permiten archivos .txt y .py", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_abrirBotonActionPerformed

    private void editorTextoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editorTextoMouseClicked
        this.pintarPanel(editor);
    }//GEN-LAST:event_editorTextoMouseClicked

    private void ayudaBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ayudaBotonMouseClicked
        String ayuda = "\n   * Presione Archivo y Cargar archivo para cargar un archivo de texto nuevo.\n"
                + "   * Presione Editor para regresar a la pestaña de editor de texto.\n"
                + "   * Presione Reportes para ver la tabla de reportes con los tokens encontrados en el documento.\n"
                + "   * Presione Ayuda para ver ayuda.\n"
                + "   * Presione Acerda de para ver datos del desarrollador.\n\n"
                + "   * Comuniquese con el desarrollador para más información.";

        JTextArea textoAyuda = new JTextArea(ayuda);
        textoAyuda.setBackground(Color.LIGHT_GRAY);
        textoAyuda.setForeground(Color.BLUE);
        textoAyuda.setBorder(new CompoundBorder());
        textoAyuda.setFont(new Font("Bold", Font.BOLD, 12) {
        });
        textoAyuda.setEditable(false);
        JDialog dialogoAyuda = new JDialog(this, true);
        dialogoAyuda.add(textoAyuda);
        dialogoAyuda.setTitle("Ayuda");
        dialogoAyuda.setSize(750, 200);
        dialogoAyuda.setLocationRelativeTo(this);
        dialogoAyuda.setVisible(true);
    }//GEN-LAST:event_ayudaBotonMouseClicked

    private void acerdaDeBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acerdaDeBotonMouseClicked
        /*String ayuda /*= "\n   \t   Parser-py\n   \t   Versión 2.0\n\n   Lenguajes Formales y de Programación\n\n   Carné: 201831260\n   Nombre: Michael Kristopher Marín Reyes";

        JTextArea textoAcercaDe = new JTextArea(ayuda);
        textoAcercaDe.setBackground(Color.LIGHT_GRAY);
        textoAcercaDe.setForeground(Color.BLUE);
        textoAcercaDe.setBorder(new CompoundBorder());
        textoAcercaDe.setFont(new Font("Bold", Font.BOLD, 12) {
        });
        textoAcercaDe.setEditable(false);
        JDialog dialogoAyuda = new JDialog(this, true);
        dialogoAyuda.add(textoAcercaDe);
        dialogoAyuda.setTitle("Acerca de");
        dialogoAyuda.setSize(320, 200);
        dialogoAyuda.setLocationRelativeTo(this);
        dialogoAyuda.setVisible(true);*/
    }//GEN-LAST:event_acerdaDeBotonMouseClicked

    private void reportesLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportesLexicoActionPerformed
        ReportesLexicoPanel reportes = new ReportesLexicoPanel();
        pintarPanel(reportes);
    }//GEN-LAST:event_reportesLexicoActionPerformed

    private void reportesSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportesSintacticoActionPerformed
        ReportesSintacticoPanel reportesSintactico = new ReportesSintacticoPanel();
        pintarPanel(reportesSintactico);
    }//GEN-LAST:event_reportesSintacticoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirBoton;
    private javax.swing.JMenu acerdaDeBoton;
    private javax.swing.JMenu ayudaBoton;
    private javax.swing.JPanel contenedorPanel;
    private javax.swing.JMenu editorTexto;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar menuOpciones;
    private javax.swing.JMenu reportesBoton;
    private javax.swing.JMenuItem reportesLexico;
    private javax.swing.JMenuItem reportesSintactico;
    // End of variables declaration//GEN-END:variables

    private void pintarPanel(Component panel) {
        contenedorPanel.removeAll();
        contenedorPanel.add(panel);
        contenedorPanel.repaint();
        contenedorPanel.revalidate();
    }
}
