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
    
    public void analizar(String cadena) {
        String buffer = "";
        char[] entradaChar = cadena.toCharArray();
        int linea = 1;
        int columna = 1;

        for (char letra : entradaChar) {
            switch (letra) {
                case ' ':
                case '\t': // Agregamos tabulación como separador
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
                case '+':
                case '-':
                case '*':
                case '/':
                case '%':
                    if (!buffer.isEmpty()) {
                        crearToken(buffer, linea, columna);
                        buffer = "";
                    }
                    crearToken(String.valueOf(letra), linea, columna);
                    columna++;
                    break;
                case '=':
                case '>':
                case '<':
                case '!':
                    if (!buffer.isEmpty()) {
                        crearToken(buffer, linea, columna);
                        buffer = "";
                    }
                    buffer += letra;
                    columna++;
                    break;
                case '&':
                    if (!buffer.isEmpty()) {
                        crearToken(buffer, linea, columna);
                        buffer = "";
                    }
                    if (entradaChar[columna] == '&') {
                        buffer = "&&";
                        crearToken(buffer, linea, columna);
                        buffer = "";
                        columna++;
                    }
                    break;
                case '|':
                    if (!buffer.isEmpty()) {
                        crearToken(buffer, linea, columna);
                        buffer = "";
                    }
                    if (entradaChar[columna] == '|') {
                        buffer = "||";
                        crearToken(buffer, linea, columna);
                        buffer = "";
                        columna++;
                    }
                    break;
                case '(':
                case ')':
                case '{':
                case '}':
                case '[':
                case ']':
                case ',':
                case ';':
                case ':':
                case '#':
                    if (!buffer.isEmpty()) {
                        crearToken(buffer, linea, columna);
                        buffer = "";
                    }
                    crearToken(String.valueOf(letra), linea, columna);
                    columna++;
                    break;
                default:
                    buffer += letra;
                    columna++;
            }
        }
    }
/*
    public void analizar(String cadena) {
        String buffer = "";
        char[] entradaChar = cadena.toCharArray();
        int linea = 1;
        int columna = 1;
        System.out.println(buffer);
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
    }*/

    private void crearToken(String lexema, int linea, int columna) {
        // Reconocer IDs, enteros, decimales y cadenas
        if (esNumero(lexema)) {
            if (lexema.contains(".")) {
                listaToken.add(new Token(TipoToken.DECIMAL.toString(), lexema, linea, columna));
            } else {
                listaToken.add(new Token(TipoToken.ENTERO.toString(), lexema, linea, columna));
            }
        } else if (esCadena(lexema)) {
            listaToken.add(new Token(TipoToken.CADENA.toString(), lexema, linea, columna));
        } else if (esID(lexema)) {
            listaToken.add(new Token(TipoToken.ID.toString(), lexema, linea, columna));
        } else if (diccionarioTipo.containsKey(lexema)) {
            TipoToken tipoToken = diccionarioTipo.get(lexema);
            listaToken.add(new Token(tipoToken.toString(), lexema, linea, columna));
        } else {
            listaToken.add(new Token(TipoToken.OTROS_TOKENS.toString(), lexema, linea, columna));
        }
    }
    
    private boolean esNumero(String lexema) {
        try {
            Integer.parseInt(lexema);
            return true;
        } catch (NumberFormatException e1) {
            try {
                Double.parseDouble(lexema);
                return true;
            } catch (NumberFormatException e2) {
                return false;
            }
        }
    }
    
    private boolean esCadena(String lexema) {
        return (lexema.startsWith("\"") && lexema.endsWith("\"")) ||
               (lexema.startsWith("'") && lexema.endsWith("'"));
    }
     private boolean esID(String lexema) {
        return lexema.matches("[a-zA-Z_][a-zA-Z0-9_]*");
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
    }
}
