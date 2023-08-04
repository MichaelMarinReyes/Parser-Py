package backend.analizador.controlador.scanner;

import backend.analizador.controlador.ControlTablaSimbolo;
import backend.analizador.controlador.parser.Parseable;
import backend.modelos.ArbolSintactico;

/**
 *
 * @author michael
 */
public interface Escaneable {

    public void setParser(Parseable parser);

    public boolean verificarGramatica();

    public void setTablasimbolos(ControlTablaSimbolo controlTablaSimbolo);

    public ArbolSintactico getArbolSintactico();
    
}
