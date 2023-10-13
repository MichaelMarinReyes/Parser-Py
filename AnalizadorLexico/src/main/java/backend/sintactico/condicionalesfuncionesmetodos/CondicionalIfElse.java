package backend.sintactico.condicionalesfuncionesmetodos;

import backend.lexico.Token;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class CondicionalIfElse {

    private static ArrayList<Token> tokens;
    private static int currentToken;

    public CondicionalIfElse(ArrayList<Token> tokens) {
        CondicionalIfElse.tokens = tokens;
        currentToken = 0;
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
        while (currentToken < tokens.size() && !match("DEDENT")) {
            sentencia();
        }
    }

    private static void sentencia() {
        if (match("IDENTIFICADOR")) {
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
        if (currentToken < tokens.size() && tokens.get(currentToken).getToken().equals(expected)) {
            currentToken++;
            return true;
        }
        return false;
    }
}
