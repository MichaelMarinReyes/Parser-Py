package frontend;

import backend.LeerArchivoTexto;
import java.awt.Component;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author michael
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    //EditorPanel editor = new EditorPanel();
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
        ayudaBoton = new javax.swing.JMenu();
        acerdaDeBoton = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));

        contenedorPanel.setBackground(new java.awt.Color(255, 153, 153));
        contenedorPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(contenedorPanel, java.awt.BorderLayout.CENTER);

        menuOpciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menuOpciones.setForeground(new java.awt.Color(153, 153, 153));

        jMenu1.setText("Archivo");

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
        reportesBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportesBotonMouseClicked(evt);
            }
        });
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
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
        chooser.setFileFilter(filtro);
        int seleccion = chooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            String textoLeido;
            textoLeido = miArchivo.abrirArchivo(chooser.getSelectedFile().getAbsolutePath());
            editor.setAreaEditor(textoLeido);
            //JOptionPane.showMessageDialog(this, "Archivo cargado");
        }


    }//GEN-LAST:event_abrirBotonActionPerformed

    private void editorTextoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editorTextoMouseClicked
        this.pintarPanel(editor);
        //this.pintarPanel(new EditorPanel());
    }//GEN-LAST:event_editorTextoMouseClicked

    private void reportesBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportesBotonMouseClicked
        ReportesPanel reportes = new ReportesPanel();
        pintarPanel(reportes);
    }//GEN-LAST:event_reportesBotonMouseClicked

    private void ayudaBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ayudaBotonMouseClicked
        JOptionPane.showMessageDialog(this, "Aquí va el tutorial");
    }//GEN-LAST:event_ayudaBotonMouseClicked

    private void acerdaDeBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acerdaDeBotonMouseClicked
       // JOptionPane.showMessageDialog(this, "Lenguajes Formales y de Programación\n\nCarné: 201831260\nNombre: Michael Kristopher Marín Reyes");
    }//GEN-LAST:event_acerdaDeBotonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirBoton;
    private javax.swing.JMenu acerdaDeBoton;
    private javax.swing.JMenu ayudaBoton;
    private javax.swing.JPanel contenedorPanel;
    private javax.swing.JMenu editorTexto;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar menuOpciones;
    private javax.swing.JMenu reportesBoton;
    // End of variables declaration//GEN-END:variables

    public void pintarPanel(Component panel) {
        contenedorPanel.removeAll();
        contenedorPanel.add(panel);
        contenedorPanel.repaint();
        contenedorPanel.revalidate();
    }
}
