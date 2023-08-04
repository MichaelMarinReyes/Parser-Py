package backend.analizador.controlador;

import backend.analizador.controlador.parser.Parseable;
import backend.analizador.controlador.parser.ParserFactory;
import backend.analizador.controlador.scanner.Escaneable;
import backend.analizador.controlador.scanner.ScannerFactory;
import backend.modelos.ArbolSintactico;
import backend.modelos.TablaSimbolo;

/**
 *
 * @author michael
 */
public class ControlAnalizador {

    private Escaneable scanner; //Scanneable
    private Parseable parser;
    private ControlTablaSimbolo controlTablaSimbolo;
    private ControlError error;
    private boolean isSourceLoad = false;
    private boolean isAnalized = false;

    public ControlAnalizador(ScannerFactory scanner, ParserFactory parser) {
        this.scanner = scanner.getScanner();
        this.parser = parser.getParser();
        this.scanner.setParser(this.parser);
        this.parser.setTablasimbolos(this.controlTablaSimbolo);
        this.scanner.setTablasimbolos(this.controlTablaSimbolo);
    }

    public boolean setFuente(String path) { //setSouce
        if (parser.setFuente(path)) {
            isSourceLoad = true;
        }
        return isSourceLoad;
    }

    public boolean hacerAnalisis() { //doAnalysis
        if (!isSourceLoad) {
            return false;
        }
        isAnalized = true;
        if (scanner.verificarGramatica()) {
            return true;
        }
        return false;
    }

    public boolean esError() { //areErrors
        if (!isSourceLoad || !isAnalized) {
            return true;
        }
        return error.getErrors();
    }

    public ArbolSintactico getArbolSintactico() {
        if (!isSourceLoad || !isAnalized) {
            return null;
        }
        return scanner.getArbolSintactico();
    }

    public TablaSimbolo getTablaSimbolo() {
        return ControlTablaSimbolo.getTablaSimbolo();
    }
    
    public boolean getErrores() {
        return false;
    }

}
