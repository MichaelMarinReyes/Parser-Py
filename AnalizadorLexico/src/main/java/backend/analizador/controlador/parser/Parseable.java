package backend.analizador.controlador.parser;

import backend.analizador.controlador.ControlTablaSimbolo;

/**
 *
 * @author michael
 */
public interface Parseable {

    public boolean setFuente(String path);

    public void setTablasimbolos(ControlTablaSimbolo controlTablaSimbolo);
    
}
