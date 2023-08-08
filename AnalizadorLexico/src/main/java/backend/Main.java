package backend;

import backend.analizador.controlador.ControlAnalizador;
import backend.analizador.controlador.parser.ParserFactory;
import backend.analizador.controlador.scanner.ScannerFactory;
import frontend.VentanaPrincipal;

/**
 *
 * @author michael
 */
public class Main {

    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        //new Main().ejecutarAnalisis();
    }

    public void ejecutarAnalisis() {
        String path = " ";
        ControlAnalizador analizador = new ControlAnalizador(ScannerFactory.PYTHONAUTOMATAPILA, ParserFactory.PYTHONAUTOMATA);
        if (!analizador.setFuente(path)) {
            System.out.println("Error en el analisis, intente de nuevo");
            return;
        }

        if (!analizador.hacerAnalisis()) {
            System.out.println("No se pudo completar en analisis");
            return;
        }

        if (analizador.esError()) {
            System.out.println("El archivo tenia errores");
            System.out.println("Mostrando errores");
            System.err.println(analizador.getErrores());

            mostrarResultadoAnalisis(analizador);
            return;
        }
        mostrarResultadoAnalisis(analizador);
    }

    public void mostrarResultadoAnalisis(ControlAnalizador analisis) {
        System.out.println("Arbol sintactico");
        System.out.println("Pintar arbol");
        System.out.println("Tabla de símbolos");
    }
}
