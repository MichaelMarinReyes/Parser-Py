package backend.sintactico.ciclos;

import backend.lexico.Token;

/**
 *
 * @author michael
 */
public class CicloWhile {

    private static String[] tokens;
    private static int currentToken;

    public CicloWhile() {
    }

    private void cicloWhile() {
        match("while");
        expresion();
        match(":");
        bloque();
    }

    private void bloque() {
        while (!match("else") && !match("EOF")) {
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
        } else if (match("identificador")) {
            match("=");
            expresion();
        } else if (match("identificador")) {
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
        if (currentToken < tokens.length && tokens[currentToken].equals(expected)) {
            currentToken++;
            return true;
        }
        return false;
    }
}
