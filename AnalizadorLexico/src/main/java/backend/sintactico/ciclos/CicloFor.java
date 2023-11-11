package backend.sintactico.ciclos;

import backend.lexico.Token;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class CicloFor {

    private static ArrayList<Token> tokens;
    private static int currentToken;

    public CicloFor(ArrayList<Token> tokens) {
        CicloFor.tokens = tokens;
        currentToken = 0;
        cicloFor();
    }

    private static void cicloFor() {
        match("for");
        identificador();
        match("in");
        expresion();
        match(":");
        bloque();
    }

    private static void bloque() {
        while (currentToken < tokens.size() && !match("else") && !match("EOF")) {
            sentencia();
        }
    }

    private static void sentencia() {
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
        } else if (match("range")) {
            match("(");
            numero();
            match(",");
            numero();
            match(")");
        } else if (match("IDENTIFICADOR")) {
            match("(");
            argumentos();
            match(")");
        }
    }

    private static void argumentos() {
        expresion();
        while (match(",")) {
            expresion();
        }
    }

    private static void expresion() {
        if (match("range")) {
            match("(");
            numero();
            match(",");
            numero();
            match(")");
        } else if (match("IDENTIFICADOR")) {
            match("(");
            argumentos();
            match(")");
        } else if (match("IDENTIFICADOR")) {
            match("=");
            expresion();
        }
    }

    private static void identificador() {
        // Implementar lógica para identificador
    }

    private static void numero() {
        // Implementar lógica para número
    }

    private static boolean match(String expected) {
        if (currentToken < tokens.size() && tokens.get(currentToken).getToken().equals(expected)) {
            currentToken++;
            return true;
        }
        return false;
    }
}
