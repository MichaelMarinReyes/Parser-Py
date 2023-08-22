package frontend;

import frontend.graphviz.VisualizarGrafico;
import backend.Token;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author michael
 */
public class ReportesPanel extends javax.swing.JPanel implements MouseListener {

    /**
     * Creates new form ReportesPanel
     */
    public ReportesPanel() {
        initComponents();
        actualizarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReportes = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 0));
        setLayout(new java.awt.BorderLayout());

        tablaReportes = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tablaReportes.setFont(new java.awt.Font("MesloLGL Nerd Font", 0, 13)); // NOI18N
        tablaReportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaReportes.setGridColor(new java.awt.Color(0, 153, 204));
        tablaReportes.setSelectionForeground(new java.awt.Color(255, 255, 0));
        jScrollPane1.setViewportView(tablaReportes);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaReportes;
    // End of variables declaration//GEN-END:variables

    public void actualizarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"No.", "Token", "Patrón", "Lexema", "Línea", "Columna", "Ver gráfico"}, EditorPanel.listaToken.size());
        this.tablaReportes.setDefaultRenderer(Object.class, new RenderizarTabla());
        tablaReportes.setModel(modelo);
        tablaReportes.addMouseListener(this);

        TableModel modeloDatos = tablaReportes.getModel();
        for (int i = 0; i < EditorPanel.listaToken.size(); i++) {
            Token token = EditorPanel.listaToken.get(i);
            modeloDatos.setValueAt(String.valueOf(i + 1), i, 0);
            modeloDatos.setValueAt(token.getToken(), i, 1);
            modeloDatos.setValueAt(token.getPatron(), i, 2);
            modeloDatos.setValueAt(token.getLexema(), i, 3);
            modeloDatos.setValueAt(String.valueOf(token.getLinea()), i, 4);
            modeloDatos.setValueAt(token.getColumna(), i, 5);
            modeloDatos.setValueAt(new JButton("Ver Gráfico"), i, 6);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int columna = tablaReportes.columnAtPoint(me.getPoint());

        if (columna == 6) {
            VisualizarGrafico ver = new VisualizarGrafico();
            ver.obtenerLexema(EditorPanel.listaToken.get(tablaReportes.getSelectedRow()).getLexema());
            ver.setTitle("Gráfico-" + EditorPanel.listaToken.get(tablaReportes.getSelectedRow()).getLexema());
            ver.setSize(999, 400);
            ver.setLocationRelativeTo(this);
            ver.setVisible(true);

        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
