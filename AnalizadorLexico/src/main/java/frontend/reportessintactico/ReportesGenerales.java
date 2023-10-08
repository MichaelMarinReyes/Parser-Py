package frontend.reportessintactico;

import frontend.*;
import frontend.graphviz.VisualizarGrafico;
import backend.lexico.Token;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author michael
 */
public class ReportesGenerales extends javax.swing.JPanel implements MouseListener {

    private TableRowSorter<DefaultTableModel> sorter;

    /**
     * Creates new form ReportesPanel
     */
    public ReportesGenerales() {
        initComponents();
        actualizarTabla();
        llenarComboBox();
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
        filtroComboBox = new javax.swing.JComboBox<>();

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

        filtroComboBox.setBackground(new java.awt.Color(255, 255, 0));
        filtroComboBox.setForeground(new java.awt.Color(0, 153, 204));
        filtroComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS" }));
        filtroComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtroComboBoxItemStateChanged(evt);
            }
        });
        add(filtroComboBox, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void filtroComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filtroComboBoxItemStateChanged
        if (!filtroComboBox.getSelectedItem().toString().equals("TODOS")) {
            sorter.setRowFilter(RowFilter.regexFilter(filtroComboBox.getSelectedItem().toString(), 1));
        } else {
            sorter.setRowFilter(null);
        }
    }//GEN-LAST:event_filtroComboBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> filtroComboBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaReportes;
    // End of variables declaration//GEN-END:variables

    private void llenarComboBox() {
        ArrayList<Token> copiaTokens = new ArrayList<>();
        HashSet<String> tiposToken = new HashSet<>();

        if (copiaTokens != null) {
            for (Token token : EditorPanel.listaToken) {
                String tokenTipo = token.getToken();
                if (!tiposToken.contains(tokenTipo)) {
                    copiaTokens.add(token);
                    tiposToken.add(tokenTipo);
                }
            }
            Collections.sort(copiaTokens, new Comparator<Token>() {
                @Override
                public int compare(Token t1, Token t2) {
                    return t1.getToken().compareTo(t2.getToken());
                }
            });
            for (int i = 0; i < copiaTokens.size(); i++) {
                filtroComboBox.addItem(copiaTokens.get(i).getToken());
            }
        }
    }

    public void actualizarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Símbolo", "Tipo", "Valor", "Línea", "Columnas"}, EditorPanel.listaToken.size());
        this.tablaReportes.setDefaultRenderer(Object.class, new RenderizarTabla());
        tablaReportes.setModel(modelo);
        tablaReportes.setAutoCreateRowSorter(true);
        sorter = new TableRowSorter<>(modelo);
        tablaReportes.setRowSorter(sorter);
        tablaReportes.addMouseListener(this);

        TableModel modeloDatos = tablaReportes.getModel();
        for (int i = 0; i < EditorPanel.listaToken.size(); i++) {
         /*   Token token = EditorPanel.listaToken.get(i);
            modeloDatos.setValueAt(String.valueOf(i + 1), i, 0); //Funciones / métodos
            modeloDatos.setValueAt(token.getToken(), i, 1); //Llamadas
            modeloDatos.setValueAt(String.valueOf(token.getLinea()), i, 4); //Errores léxicos
            modeloDatos.setValueAt(token.getColumna(), i, 5); //Errores sintácticos
            modeloDatos.setValueAt(token.getColumna(), i, 5); //Parámetros*/
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int columna = tablaReportes.columnAtPoint(me.getPoint());

        if (columna == 6) {
            VisualizarGrafico ver = new VisualizarGrafico();
            ver.obtenerLexema(EditorPanel.listaToken.get(tablaReportes.getSelectedRow()).getLexema());
            ver.setTitle("Gráfico-" + EditorPanel.listaToken.get(tablaReportes.getSelectedRow()).getToken());
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
