package backend.sintactico;

import backend.lexico.Token;
import backend.sintactico.*;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class AnalizadorSintactico {

    private ArrayList<BloqueCodigo> listaBloques;
    private String bloqueDeCodigoIdentificado = "";
    private int index;
    private ArrayList<Token> listaTokens;

    public AnalizadorSintactico(ArrayList<Token> listaTokens, ArrayList<BloqueCodigo> bloqueCodigo) {
        this.listaTokens = listaTokens;
        this.listaBloques = bloqueCodigo;
    }

    public void analizar() {
        try {
            while (index < listaTokens.size()) {
                declaracion();
            }
            System.out.println("Análisis completado.");
        } catch (Exception e) {
            System.out.println("Error de sintaxis: " + e.getMessage());
        }
    }

    private void declaracion() {
        identificador();
        match("=");
        expresion();
        match(";");
    }

    private void identificador() {
        Token token = getNextToken();
        if (!token.getToken().equals("IDENTIFICADOR")) {
            throw new RuntimeException("Se esperaba un identificador, pero se encontró: " + token.getToken());
        }
    }

    private void expresion() {
        Token token = getNextToken();
        if (!token.getToken().equals("NUMERO")) {
            throw new RuntimeException("Se esperaba un número, pero se encontró: " + token.getToken());
        }
    }

    private void match(String expected) {
        Token token = getNextToken();
        if (!token.getToken().equals(expected)) {
            throw new RuntimeException("Se esperaba '" + expected + "', pero se encontró: " + token.getToken());
        }
    }

    private Token getNextToken() {
        if (index < listaTokens.size()) {
            return listaTokens.get(index++);
        } else {
            throw new RuntimeException("Se esperaba más tokens, pero el análisis ha llegado al final.");
        }
    }

    public String getBloqueDeCodigoIdentificado() {
        return bloqueDeCodigoIdentificado;
    }
}
