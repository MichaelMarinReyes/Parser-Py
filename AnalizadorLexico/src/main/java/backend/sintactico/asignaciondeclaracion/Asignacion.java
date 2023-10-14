package backend.sintactico.asignaciondeclaracion;

import backend.lexico.Token;
import backend.lexico.identificadores.*;

/**
 *
 * @author michael
 */
public class Asignacion {

    private Token[] tokens = new Token[3];
    private boolean esAceptado = false;

    public Asignacion(Token[] tokens) {
        this.tokens = tokens;
        asignacion();
    }

    public boolean isEsAceptado() {
        return esAceptado;
    }

    public void asignacion() {
        if (tokens[0].getToken().equals("ID")) {
            operadorIdentificador();
        }
    }

    private void operadorIdentificador() {
        if (esComparacion(tokens[1].getToken())) {
            if (tokens[2] != null) {
                if (!tokens[1].getToken().equals(ComparacionEnum.ASIGNACION.name())) {
                    valorAsignado();
                }
            } else {
                esAceptado = false;
            }

        }
    }

    private void valorAsignado() {
        if (tokens[2].getToken().equals(OtroEnum.CADENA.toString()) || tokens[2].getToken().equals(OtroEnum.DECIMAL.toString()) || tokens[2].getToken().equals(OtroEnum.ENTERO.toString())) {
            esAceptado = true;
        } else if (tokens[2].getToken().equals(PalabraClaveEnum.FALSE.toString()) || tokens[2].getToken().equals(PalabraClaveEnum.TRUE.toString())) {
            esAceptado = true;
        } else {
            esAceptado = false;
        }
    }

    private boolean esComparacion(String token) {
        for (int i = 0; i < ComparacionEnum.values().length; i++) {
            if (!ComparacionEnum.values()[i].name().equals("ASIGNACION")) {
                if (token.equals(ComparacionEnum.values()[i].name())) {
                    return true;
                }
            }
        }
        return false;
    }
}
