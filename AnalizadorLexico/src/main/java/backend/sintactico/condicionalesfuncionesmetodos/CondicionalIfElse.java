package backend.sintactico.condicionalesfuncionesmetodos;

import backend.lexico.Token;
import backend.lexico.identificadores.OtroEnum;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class CondicionalIfElse {

    private Token[] tokens;
    private int currentToken;
    private boolean aceptado = false;

    public CondicionalIfElse(Token[] tokens) {
        System.out.println(tokens.length);
        this.tokens = tokens;
        currentToken = 0;
        condicional();
    }

    public boolean isAceptado() {
        return aceptado;
    }

    private void condicional() {
        if (tokens[0].getToken().equals("IF")) {
            expresion();
        }
    }
    
    private void expresion() {
        if (esExpresion()) {
            dosPuntos();
        } else {
            aceptado = false;
        }
    }
    
    private boolean esExpresion() {
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[1].getToken().equals("TRUE") || tokens[1].getToken().equals("FALSE")) {
                return true;
            }
        }
        return false;
    }
    
    private void dosPuntos() {
        if (tokens[2].getToken().equals(OtroEnum.DOS_PUNTOS)) {
            aceptado = true;
        }
    }
}
