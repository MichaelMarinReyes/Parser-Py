package backend.sintactico.ciclos;

import backend.lexico.Token;

/**
 *
 * @author michael
 */
public class CicloFor {

    private static String[] tokens;
    private static int currentToken;

    public CicloFor() {
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
        while (!match("else") && !match("EOF")) {
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
        } else if (match("identificador")) {
            match("=");
            expresion();
        } else if (match("range")) {
            match("(");
            numero();
            match(",");
            numero();
            match(")");
        } else if (match("identificador")) {
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
        } else if (match("identificador")) {
            match("(");
            argumentos();
            match(")");
        } else if (match("identificador")) {
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
        if (currentToken < tokens.length && tokens[currentToken].equals(expected)) {
            currentToken++;
            return true;
        }
        return false;
    }

}
