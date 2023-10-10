package backend.sintactico;

import backend.lexico.Token;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class CondicionalIfElse {

    private static final int q0 = 0;
    private static final int q1 = 1;
    private static final int q2 = 2;
    private static final int q3 = 3;
    private static final int q4 = 4;
    private static final int qA = 5;
    private static final int qR = 6;

    private int estadoActual;

    public CondicionalIfElse() {
        this.estadoActual = q0;
    }

    public void procesarToken(Token token) {
        String lexema = token.getLexema();

        switch (estadoActual) {
            case q0:
                if (lexema.equals("if")) {
                    estadoActual = q1;
                } else {
                    estadoActual = qR;
                }
                break;
            case q1:
                // Comprobamos si el token es una expresión
                if (token.getPatron().equals("expresion")) {
                    estadoActual = q2;
                } else {
                    estadoActual = qR;
                }
                break;
            case q2:
                if (lexema.equals(":")) {
                    estadoActual = q3;
                } else {
                    estadoActual = qR;
                }
                break;
            case q3:
                 
                if (token.getPatron().equals("bloque_codigo")) {
                    estadoActual = q4;
                } else {
                    estadoActual = qR;
                }
                break;
            case q4:
                if (lexema.equals("elif") || lexema.equals("else")) {
                    estadoActual = q1;
                } else if (lexema.equals(":")) {
                    estadoActual = q3;
                } else {
                    estadoActual = qR;
                }
                break;
            default:
                estadoActual = qR;
        }
    }

    public boolean esAceptado() {
        return estadoActual == qA;
    }
}
