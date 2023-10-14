package backend.sintactico.asignaciondeclaracion;

import backend.lexico.Token;
import backend.lexico.identificadores.ComparacionEnum;
import backend.lexico.identificadores.OtroEnum;

/**
 *
 * @author michael
 */
public class Asignacion {

    private Token[] tokens = new Token[4];
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
            if (tokens[3] != null) {
                if (tokens[2].getToken().equals(ComparacionEnum.ASIGNACION.name())) {
                    valorAsignado();
                }
            } else {
                esAceptado = false;
            }

        }
    }

    private void valorAsignado() {
        if (tokens[3].getToken().equals(OtroEnum.CADENA.toString()) || tokens[3].getToken().equals(OtroEnum.DECIMAL.toString()) || tokens[3].getToken().equals(OtroEnum.ENTERO.toString())) {
            esAceptado = true;
        } else {
            esAceptado = false;
        }
    }

    private boolean esComparacion(String token) {
        for (int i = 0; i < ComparacionEnum.values().length; i++) {
            if (!ComparacionEnum.values()[i].name().equals("ASIGANCION")) {
                if (token.equals(ComparacionEnum.values()[i].name())) {
                    System.out.println("Token " + token + " enum " + ComparacionEnum.values()[i].name());
                    return true;
                }
            }
        }
        return false;
    }
}
