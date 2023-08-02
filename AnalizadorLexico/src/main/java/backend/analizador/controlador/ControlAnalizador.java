package backend.analizador.controlador;

import backend.analizador.controlador.parser.Parseable;
import backend.analizador.controlador.scanner.Escaneable;

/**
 *
 * @author michael
 */
public class ControlAnalizador {

    private Escaneable scanner; //Scanneable
    private Parseable parser;
    private ControlTablaSimbolo controlTablaSimbolo;
    private ControlError error;

    boolean setFuente(String path) { //setSouce
        return false;
    }

    boolean hacerAnalisis() { //doAnalysis
        return false;
    }

    boolean esError() { //areErrors
        return false;
    }

    public char[] getArbolSintactico() {
        return null;
    }
    
    boolean getErrores() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
