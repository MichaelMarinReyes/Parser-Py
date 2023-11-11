package backend.sintactico.ciclos;

import backend.lexico.Token;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class CicloWhile {

    private static ArrayList<Token> tokens;
    private static int currentToken;

    public CicloWhile(ArrayList<Token> tokens) {
        CicloWhile.tokens = tokens;
        currentToken = 0;
        cicloWhile();
    }

    private void cicloWhile() {
        match("while");
        expresion();
        match(":");
        bloque();
    }

    private void bloque() {
        while (currentToken < tokens.size() && !match("else") && !match("EOF")) {
            sentencia();
        }
    }

    private void sentencia() {
        if (match("if")) {
            expresion();
            match(":");
            bloque();
            if (match("else")) {
                match(":");
                bloque();
            }
        } else if (match("IDENTIFICADOR")) {
            match("=");
            expresion();
        } else if (match("IDENTIFICADOR")) {
            match("(");
            argumentos();
            match(")");
        }
    }

    private void argumentos() {
        expresion();
        while (match(",")) {
            expresion();
        }
    }

    private void expresion() {
        comparacion();
    }

    private void comparacion() {
        expresion();
        operadorComparacion();
        expresion();
    }

    private boolean operadorComparacion() {
        return match(">=") || match(">") || match("<") || match("<=") || match("==") || match("!=");
    }

    private void identificador() {
        // Implementar lógica para identificador
    }

    private void numero() {
        // Implementar lógica para número
    }

    private boolean match(String expected) {
        if (currentToken < tokens.size() && tokens.get(currentToken).getToken().equals(expected)) {
            currentToken++;
            return true;
        }
        return false;
    }
}
