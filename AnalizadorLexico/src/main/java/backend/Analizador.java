package backend;

import backend.identificadores.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author michael
 */
public class Analizador {

    private Map<String, TipoToken> diccionarioTipo;
    private ArrayList<Token> listaToken;
    private String cadenaTemp;

    public Analizador(ArrayList<Token> listaToken) {
        this.listaToken = listaToken;
        iniciarDiccionarios();
    }

    public void analizar2(String cadena) {
        String buffer = "";
        char[] entradaChar = cadena.toCharArray();
        int linea = 1;
        int columna = 1;

        for (char letra : entradaChar) {
            switch (letra) {
                case ' ':
                    if (!buffer.isEmpty()) {
                        crearToken(buffer, linea, columna);
                        buffer = "";
                    }
                    columna++;
                    break;
                case '\n':
                    if (!buffer.isEmpty()) {
                        crearToken(buffer, linea, columna);
                        buffer = "";
                    }
                    linea++;
                    columna = 1;
                    break;
                default:
                    buffer += letra;
                    columna++;
            }
        }
    }

    private void crearToken(String lexema, int linea, int columna) {
        //cadenaTemp = lexema;
        if (diccionarioTipo.containsKey(lexema)) {
            TipoToken tipoToken = diccionarioTipo.get(lexema);
            listaToken.add(new Token(tipoToken.toString(), lexema, lexema, linea, columna));
        } else {
            listaToken.add(new Token("OTROS_TOKENS", lexema, lexema, linea, columna));
        }
    }

    private void iniciarDiccionarios() {
        diccionarioTipo = new HashMap<>();
        this.diccionarioTipo.put("+", TipoToken.ARITMETICO);
        this.diccionarioTipo.put("-", TipoToken.ARITMETICO);
        this.diccionarioTipo.put("**", TipoToken.ARITMETICO);
        this.diccionarioTipo.put("/", TipoToken.ARITMETICO);
        this.diccionarioTipo.put("//", TipoToken.ARITMETICO);
        this.diccionarioTipo.put("%", TipoToken.ARITMETICO);
        this.diccionarioTipo.put("*", TipoToken.ARITMETICO);
        //LOGICOS
        this.diccionarioTipo.put("and", TipoToken.LOGICO);
        this.diccionarioTipo.put("or", TipoToken.LOGICO);
        this.diccionarioTipo.put("not", TipoToken.LOGICO);
        //ASIGNACION
        this.diccionarioTipo.put("=", TipoToken.ASIGNACION);
        //PALABRAS CLAVE
        this.diccionarioTipo.put("as", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("assert", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("break", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("class", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("continue", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("def", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("del", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("elif", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("else", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("except", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("False", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("finally", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("for", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("global", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("if", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("import", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("in", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("is", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("lambda", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("None", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("nonlocal", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("pass", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("raise", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("return", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("True", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("try", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("while", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("with", TipoToken.PALABRA_RESERVADA);
        this.diccionarioTipo.put("yeald", TipoToken.PALABRA_RESERVADA);
        //COMPARACION
        this.diccionarioTipo.put("==", TipoToken.COMPARACION);
        this.diccionarioTipo.put("=", TipoToken.COMPARACION);
        this.diccionarioTipo.put("!=", TipoToken.COMPARACION);
        this.diccionarioTipo.put(">", TipoToken.COMPARACION);
        this.diccionarioTipo.put("<", TipoToken.COMPARACION);
        this.diccionarioTipo.put(">=", TipoToken.COMPARACION);
        this.diccionarioTipo.put("<=", TipoToken.COMPARACION);
        //OTROS
        this.diccionarioTipo.put("#", TipoToken.COMENTARIO);
        this.diccionarioTipo.put(",", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("]", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("[", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put(":", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("}", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("{", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put(")", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("(", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put(";", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("\'", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("\"", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("_", TipoToken.OTROS_TOKENS);
/*
        if (this.reconocerCadenas(cadenaTemp).equals("CADENA")) {
            this.diccionarioTipo.put("CADENA", TipoToken.CADENA);
        } else if (this.reconocerCadenas(cadenaTemp).equals("ENTERO")) {
            this.diccionarioTipo.put("ENTERO", TipoToken.ERROR);
        } else if (this.reconocerCadenas(cadenaTemp).equals("DECIMAL")) {
            this.diccionarioTipo.put("DECIMAL", TipoToken.CADENA);
        } else {
            this.diccionarioTipo.put("ERROR", TipoToken.ERROR);
        }*/
    }

    private String reconocerCadenas(String cadena) {
        char[] string = cadena.toCharArray();
        if (string[0] == '\"' && string[string.length] == '\"') {
            return "CADENA";
        } else if (Integer.valueOf(cadena) instanceof Integer) {
            return "ENTERO";
        } else if (Double.valueOf(cadena) instanceof Double) {
            return "DECIMAL";
        }
        return "ERROR";
    }
}
