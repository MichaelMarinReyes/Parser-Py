package backend.sintactico.asignaciondeclaracion;

import backend.lexico.Token;
import backend.lexico.identificadores.ComparacionEnum;
import backend.lexico.identificadores.OtroEnum;

/**
 *
 * @author michael
 */
public class Declaracion {


    private Token[] tokens = new Token[3];
    private boolean esAceptado = false;

    public Declaracion(Token[] tokens) {
        this.tokens = tokens;
        asignacion();
    }

    public boolean isEsAceptado() {
        return esAceptado;
    }

    public void asignacion() {
        if (tokens[0].getToken().equals("ID")) {
            operadorAsignacion();
        }
    }

    private void operadorAsignacion() {
        if (tokens[1].getToken().equals(ComparacionEnum.ASIGNACION.toString())) {
            valorAsignado();
        }
    }
    
    private void valorAsignado() {
        if (tokens[2].getToken().equals(OtroEnum.CADENA.toString()) || tokens[2].getToken().equals(OtroEnum.DECIMAL.toString()) || tokens[2].getToken().equals(OtroEnum.ENTERO.toString())) {
            esAceptado = true;
        } else {
            esAceptado = false;
        }
    }
}
