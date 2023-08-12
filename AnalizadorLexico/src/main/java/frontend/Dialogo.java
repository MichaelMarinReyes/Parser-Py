package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 *
 * @author michael
 */
public class Dialogo extends JDialog {

    public Dialogo() {
        // Configuración de frame2

        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra el diálogo modal
            }
        });

        // Añade closeButton u otros componentes a tu frame2
    }
}
