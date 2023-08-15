package backend.analizador.controlador.scanner;

import backend.analizador.controlador.ControlTablaSimbolo;
import backend.analizador.controlador.parser.Parseable;

/**
 *
 * @author michael
 */
public interface Escaneable {

    public void setParser(Parseable parser);

    public boolean verificarGramatica();

    public void setTablasimbolos(ControlTablaSimbolo controlTablaSimbolo);
    
}
