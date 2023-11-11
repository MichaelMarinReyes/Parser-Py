package backend.sintactico.condicionalesfuncionesmetodos;

import backend.lexico.Token;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class FuncionMetodo {

    private static ArrayList<Token> tokens;
    private static int currentToken;

    public FuncionMetodo(ArrayList<Token> tokens) {
        FuncionMetodo.tokens = tokens;
        currentToken = 0;
        declaracion();
    }

    private void declaracion() {
        match("def");
        identificador();
        match("(");
        listaParametros();
        match(")");
        match(":");
        bloque();
    }

    private void listaParametros() {
        if (match("IDENTIFICADOR")) {
            while (match(",")) {
                identificador();
            }
        }
    }

    private void bloque() {
        while (currentToken < tokens.size() && !match("return") && !match("EOF")) {
            sentencia();
        }
        if (match("return")) {
            expresion();
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
        // Implementar lógica para expresiones
    }

    private void identificador() {
        // Implementar lógica para identificador
    }

    private boolean match(String expected) {
        if (currentToken < tokens.size() && tokens.get(currentToken).getToken().equals(expected)) {
            currentToken++;
            return true;
        }
        return false;
    }
}
