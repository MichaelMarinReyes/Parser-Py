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

    public Analizador(ArrayList<Token> listaToken) {
        this.listaToken = listaToken;
        iniciarDiccionarios();
    }

    public void analizar(String cadena) {
        String buffer = "";
        char[] entradaChar = cadena.toCharArray();
        int linea = 1;
        int columna = 1;
        boolean esCadena = false;
        boolean esComentario = false;

        for (char letra : entradaChar) {
            if (esCadena) {
                buffer += letra;
                if (letra == '"' || letra == '\'') {
                    esCadena = false;
                    crearToken(buffer, linea, columna);
                    buffer = "";
                }
            } else if (esComentario) {
                buffer += letra;
                if (letra == '\n') {
                    esComentario = false;
                    crearToken(buffer, linea, columna);
                    buffer = "";
                    linea++;
                    columna = 1;
                }
            } else {
                switch (letra) {
                    case ' ':
                    case '\t':
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
                    case '#':
                        if (!buffer.isEmpty()) {
                            crearToken(buffer, linea, columna);
                            buffer = "";
                        }
                        buffer += letra;
                        esComentario = true;
                        columna++;
                        break;
                    case '"':
                    case '\'':
                        if (!buffer.isEmpty()) {
                            crearToken(buffer, linea, columna);
                            buffer = "";
                        }
                        if (esCadena) {
                            buffer += letra;
                            esCadena = false;
                            crearToken(buffer, linea, columna);
                            buffer = "";
                        } else {
                            buffer += letra;
                            esCadena = true;
                        }
                        columna++;
                        break;
                    default:
                        switch (letra) {
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
                                if (columna < entradaChar.length - 1 && entradaChar[columna] == '&') {
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
                                if (columna < entradaChar.length - 1 && entradaChar[columna] == '|') {
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
                                if (!buffer.isEmpty()) {
                                    crearToken(buffer, linea, columna);
                                    buffer = "";
                                }
                                buffer += letra;
                                crearToken(buffer, linea, columna);
                                buffer = "";
                                columna++;
                                break;
                            default:
                                if (esID(buffer)) {
                                    if (diccionarioTipo.containsKey(buffer)) {
                                        crearToken(buffer, linea, columna);
                                        buffer = "";
                                    } else {
                                        buffer += letra;
                                    }
                                    columna++;
                                } else {
                                    buffer += letra;
                                    columna++;
                                }
                                break;
                        }
                }
            }
        }
    }

    private void crearToken(String lexema, int linea, int columna) {
        if (esNumero(lexema)) {
            if (lexema.contains(".")) {
                listaToken.add(new Token(TipoToken.DECIMAL.toString(), lexema, linea, columna));
            } else {
                listaToken.add(new Token(TipoToken.ENTERO.toString(), lexema, linea, columna));
            }
        } else if (esCadena(lexema)) {
            listaToken.add(new Token(TipoToken.CADENA.toString(), lexema, linea, columna));
        } else if (diccionarioTipo.containsKey(lexema)) {
            TipoToken tipoToken = diccionarioTipo.get(lexema);
            if (tipoToken == TipoToken.ID) {
                listaToken.add(new Token(TipoToken.PALABRA_RESERVADA.toString(), lexema, linea, columna));
            } else {
                listaToken.add(new Token(tipoToken.toString(), lexema, linea, columna));
            }
        } else if (lexema.contains("#")) {
            listaToken.add(new Token(TipoToken.COMENTARIO.toString(), lexema, linea, columna));
        } else if (lexema.equals("\'") || lexema.equals("\"")) {
            listaToken.add(new Token(TipoToken.ERROR_LEXICO.toString(), "Error al no cerrar las comillas", linea, columna));
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
        return (lexema.startsWith("\"") && lexema.endsWith("\"")) || (lexema.startsWith("\'") && lexema.endsWith("\'"));
    }

    private boolean esID(String lexema) {
        return diccionarioTipo.containsKey(lexema);
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
        this.diccionarioTipo.put("yield", TipoToken.PALABRA_RESERVADA);
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
        this.diccionarioTipo.put("\'", TipoToken.CADENA);
        this.diccionarioTipo.put("\"", TipoToken.CADENA);
        this.diccionarioTipo.put("_", TipoToken.OTROS_TOKENS);
    }
}
