package backend.sintactico.condicionalesfuncionesmetodos;

import backend.lexico.Token;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class CondicionalIfElse {

    private static String[] tokens;
    private static int currentToken;

    public CondicionalIfElse() {
        condicional();
    }

    private static void condicional() {
        if (match("if")) {
            expresion();
            match(":");
            bloqueCodigo();
            while (match("elif")) {
                expresion();
                match(":");
                bloqueCodigo();
            }
            if (match("else")) {
                match(":");
                bloqueCodigo();
            }
        } else {
            throw new RuntimeException();
        }
    }

    private static void bloqueCodigo() {
        match("INDENT");
        while (!match("DEDENT")) {
            sentencia();
        }
    }

    private static void sentencia() {
        if (match("identificador")) {
            match("=");
            expresion();
        } else if (match("if")) {
            expresion();
            match(":");
            bloqueCodigo();
            while (match("elif")) {
                expresion();
                match(":");
                bloqueCodigo();
            }
            if (match("else")) {
                match(":");
                bloqueCodigo();
            }
        } else {
            throw new RuntimeException();
        }
    }

    private static void expresion() {
        // Implementar lógica para expresiones
    }

    private static boolean match(String expected) {
        if (currentToken < tokens.length && tokens[currentToken].equals(expected)) {
            currentToken++;
            return true;
        }
        return false;
    }
}
