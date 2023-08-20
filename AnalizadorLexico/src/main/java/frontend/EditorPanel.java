package frontend;

import backend.Analizador;
import backend.Token;
import backend.identificadores.TipoToken;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author michael
 */
public class EditorPanel extends javax.swing.JPanel {

    private NumeroLinea numerarEditor;
    private NumeroLinea numerarConsola;
    private ReportesPanel reportes = new ReportesPanel();
    public static ArrayList<Token> listaToken = new ArrayList();
    public static ArrayList<Error> errores = new ArrayList<>();

    /**
     * Creates new form PruebaEditor
     */
    public EditorPanel() {
        initComponents();
        mostrarColumnaLabel.setText("Columna: 1");
        numerarEditor = new NumeroLinea(areaEditor);
        scrollEditor.setRowHeaderView(numerarEditor);
        numerarConsola = new NumeroLinea(areaConsola);
        scrollConsola.setRowHeaderView(numerarConsola);
        mostrarColumna();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mostrarColumnaLabel = new javax.swing.JLabel();
        limpiarBoton = new javax.swing.JButton();
        ejecutarBoton = new javax.swing.JButton();
        scrollConsola = new javax.swing.JScrollPane();
        areaConsola = new javax.swing.JEditorPane();
        scrollEditor = new javax.swing.JScrollPane();
        areaEditor = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(255, 153, 102));
        setPreferredSize(new java.awt.Dimension(844, 590));
        setLayout(new java.awt.GridBagLayout());

        mostrarColumnaLabel.setFont(new java.awt.Font("MesloLGL Nerd Font", 0, 13)); // NOI18N
        mostrarColumnaLabel.setForeground(new java.awt.Color(0, 0, 0));
        mostrarColumnaLabel.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 423, 0, 0);
        add(mostrarColumnaLabel, gridBagConstraints);

        limpiarBoton.setBackground(new java.awt.Color(0, 102, 102));
        limpiarBoton.setFont(new java.awt.Font("MesloLGL Nerd Font", 0, 13)); // NOI18N
        limpiarBoton.setForeground(new java.awt.Color(255, 255, 255));
        limpiarBoton.setText("Limpiar Editor");
        limpiarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarBotonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 101, 0, 0);
        add(limpiarBoton, gridBagConstraints);

        ejecutarBoton.setBackground(new java.awt.Color(51, 255, 51));
        ejecutarBoton.setFont(new java.awt.Font("MesloLGL Nerd Font", 1, 13)); // NOI18N
        ejecutarBoton.setForeground(new java.awt.Color(0, 0, 0));
        ejecutarBoton.setText("Ejecutar");
        ejecutarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarBotonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 29;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 60, 0, 0);
        add(ejecutarBoton, gridBagConstraints);

        scrollConsola.setViewportView(areaConsola);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 882;
        gridBagConstraints.ipady = 124;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 32, 7, 31);
        add(scrollConsola, gridBagConstraints);

        scrollEditor.setViewportView(areaEditor);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 882;
        gridBagConstraints.ipady = 362;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 32, 0, 31);
        add(scrollEditor, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void ejecutarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarBotonActionPerformed
        if (areaEditor.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "No hay nada para analizar\nEscribe algo en el editor de código");
        } else {
            new Analizador(listaToken).analizar(areaEditor.getText() + "\n");
/*
            StyledDocument doc = areaEditor.getStyledDocument();
            String nuevoTexto = "";  // Paso 1: Variable para almacenar el nuevo texto*/

            for (int i = 0; i < listaToken.size(); i++) {
                areaConsola.setText(areaConsola.getText() + "\n" + listaToken.get(i).toString());
/*
                Style style = doc.addStyle(listaToken.get(i).getToken(), null);
                StyleConstants.setForeground(style, obtenerColorToken(listaToken.get(i).getToken()));

                try {
                    doc.insertString(doc.getLength(), listaToken.get(i).getLexema(), style);
                    nuevoTexto += listaToken.get(i).getLexema();  // Paso 2: Agregar al nuevo texto
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }*/
            }

            // Reemplazar el contenido anterior del área de editor con el nuevo texto
            //areaEditor.setText(nuevoTexto);

            areaConsola.setText(areaConsola.getText() + "\n\nARCHIVO ANALIZADO\n---------------------------------------------------------------------------------------------------------------------------------");
        }
    }//GEN-LAST:event_ejecutarBotonActionPerformed

    private void limpiarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarBotonActionPerformed
        areaEditor.setText("");
        areaConsola.setText("");
        listaToken.clear();
    }//GEN-LAST:event_limpiarBotonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane areaConsola;
    private javax.swing.JTextPane areaEditor;
    private javax.swing.JButton ejecutarBoton;
    private javax.swing.JButton limpiarBoton;
    private javax.swing.JLabel mostrarColumnaLabel;
    private javax.swing.JScrollPane scrollConsola;
    private javax.swing.JScrollPane scrollEditor;
    // End of variables declaration//GEN-END:variables

    private void mostrarColumna() {
        areaEditor.addCaretListener(e -> {
            int caretPosition = areaEditor.getCaretPosition();
            int linea = 0;
            try {
                linea = areaEditor.getDocument().getDefaultRootElement().getElementIndex(caretPosition);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            int columna = 0;
            try {
                int inicioLinea = areaEditor.getDocument().getDefaultRootElement().getElement(linea).getStartOffset();
                columna = caretPosition - inicioLinea;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            mostrarColumnaLabel.setText("Columna: " + (columna + 1));
        });
    }

    public void setAreaEditor(String textoLeido) {
        areaEditor.setText(textoLeido);
    }

    private Color obtenerColorToken(String tipoToken) {
        if (tipoToken.equals(TipoToken.ARITMETICO) || tipoToken.equals(TipoToken.COMPARACION) || tipoToken.equals(TipoToken.LOGICO) || tipoToken.equals(TipoToken.ASIGNACION)) {
            return Color.cyan;
        } else if (tipoToken.equals(TipoToken.PALABRA_RESERVADA)) {
            return Color.MAGENTA;
        } else if (tipoToken.equals(TipoToken.COMENTARIO)) {
            return Color.gray;
        } else if (tipoToken.equals(TipoToken.OTROS_TOKENS)) {
            return Color.GREEN;
        } else {
            return Color.red;
        }
    }
}
