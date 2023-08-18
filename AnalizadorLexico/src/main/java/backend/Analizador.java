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
    private String buffer;

    public Analizador(ArrayList<Token> listaToken) {
        this.listaToken = listaToken;
        iniciarDiccionarios();
    }

    private void definirTipoToken(String buffer) {
        if (!buffer.isBlank()) {
            if (this.diccionarioTipo.containsKey(buffer)) {
                //Token token = new Token(this.diccionarioTipo.get(buffer), "nose", "buffer", 0, 0);
            }
        }
    }

    public void analizar2(String cadena) {
        String buffer = "";
        char[] entradaChar = cadena.toCharArray();
        int linea = 1;
        int columna = 1;

        for (char letra : entradaChar) {
            columna++;
            switch (letra) {
                case ' ':
                    if (this.diccionarioTipo.containsKey(buffer)) {
                        listaToken.add(new Token(this.diccionarioTipo.get(buffer).toString(), buffer, buffer, linea, columna));
                        buffer = "";
                    } else if ((!(this.diccionarioTipo.containsKey(buffer)) || (letra != ' ') && (letra != '\n'))) {
                        listaToken.add(new Token(/*this.diccionarioTipo.get(buffer).toString()*/"CADENA", buffer, buffer, linea, columna));
                        buffer = "";
                    }
                    break;
                case '\n':
                    linea++;
                    columna = 1;
                    break;
                case '\'':
                    if (this.diccionarioTipo.containsKey("\'")) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case '\"':
                    if (this.diccionarioTipo.containsKey("\"")) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                //ARITMETICOS
                case '+':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case '-':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case '*' + '*':
                    if (this.diccionarioTipo.containsKey("**")) {
                        listaToken.add(new Token(this.diccionarioTipo.get("**").toString(), "**", "**", linea, columna));
                        buffer = "";
                    }
                    break;
                case '/':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case '/' + '/':
                    if (this.diccionarioTipo.containsKey("//")) {
                        listaToken.add(new Token(this.diccionarioTipo.get("//").toString(), "//", "//", linea, columna));
                        buffer = "";
                    }
                    break;
                case '%':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case '*':
                    if (this.diccionarioTipo.containsKey("*")) {
                        listaToken.add(new Token(this.diccionarioTipo.get("*").toString(), "*", "*", linea, columna));
                        buffer = "";
                    }
                    break;
                //COMPARACION
                case '=' + '=':
                    if (this.diccionarioTipo.containsKey("==")) {
                        listaToken.add(new Token(this.diccionarioTipo.get("==").toString(), "==", "==", linea, columna));
                        buffer = "";
                    }
                    break;/*
                case '!'+'=':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;*/
                case '<':

                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case '>':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case '>' + '=':
                    if (this.diccionarioTipo.containsKey(">=")) {
                        listaToken.add(new Token(this.diccionarioTipo.get(">=").toString(), "(w*>=w*)", ">=", linea, columna));
                        buffer = "";
                    }
                    break;
                case '<' + '=':
                    if (this.diccionarioTipo.containsKey("<=")) {
                        listaToken.add(new Token(this.diccionarioTipo.get("<=").toString(), "(w*<=w*)", ">=", linea, columna));
                        buffer = "";
                    }
                    break;
                case '=':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                //OTROS
                case '#':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case '(':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case ')':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;/*
                case '{':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;*/
                case '}':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case '[':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case ']':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case ',':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case '.':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case ':':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                case ';':
                    if (this.diccionarioTipo.containsKey(String.valueOf(letra))) {
                        listaToken.add(new Token(this.diccionarioTipo.get(String.valueOf(letra)).toString(), String.valueOf(letra), String.valueOf(letra), linea, columna));
                        buffer = "";
                    }
                    break;
                default:
                    buffer += letra;
            }
        }
    }

    public void analizar(String cadena) {
        int estado = 0;
        int decimal = 0;
        int numeroToken = 0;
        String lexema = "";
        String tipo = "";
        String patron = "";
        String[] lineas = separarPalabras(cadena, '\n');

        for (int i = 0; i < lineas.length; i++) {
            for (int j = 0; j < lineas[i].length(); j++) {
                int nActual = -1;
                int nSiguiente = -1;

                nActual = lineas[i].codePointAt(j);
                if (estado == 0) {
                    estado = estadoTransicion(nActual);
                }

                try {
                    nSiguiente = lineas[i].codePointAt(j + 1);
                } catch (Exception e) {

                }

                switch (estado) {
                    case 1:
                        lexema = lexema + lineas[i].charAt(j);
                        if ((nSiguiente > 96 && nSiguiente < 123) || (nSiguiente > 64 && nSiguiente < 91)) {
                            estado = 1;
                        } else {
                            numeroToken = 1;
                            tipo = "Palabra Reservada";
                            patron = "NO implementado";
                            estado = 0;
                        }
                        break;
                    case 2:
                        lexema = lexema + lineas[i].charAt(j);
                        if (nSiguiente > 47 && nSiguiente < 58) {
                            estado = 2;
                        } else {
                            numeroToken = 2;
                            tipo = "Entero";
                            patron = "[0-9]+";
                            estado = 0;
                        }
                        break;
                    case 3:
                        lexema = lexema + lineas[i].charAt(j);
                        numeroToken = 23;
                        tipo = "Asignación";
                        patron = "[=]+";
                        estado = 0;
                        break;
                    case 4:
                        lexema = lexema + lineas[i].charAt(j);
                        numeroToken = 4;
                        tipo = "Suma";
                        patron = "[+]+";
                        estado = 0;
                        break;
                    case 5:
                        lexema = lexema + lineas[i].charAt(j);
                        numeroToken = 5;
                        tipo = "Resta";
                        patron = "[-]+";
                        estado = 0;
                        break;
                    case 6:
                        lexema = lexema + lineas[i].charAt(j);
                        numeroToken = 6;
                        tipo = "Multiplicación";
                        patron = "[*]+";
                        estado = 0;
                        break;
                    case 7:
                        lexema = lexema + lineas[i].charAt(j);
                        numeroToken = 7;
                        tipo = "División";
                        patron = "[/ | //]+";

                        estado = 0;
                        break;
                    case 8:
                        lexema = lexema + lineas[i].charAt(j);
                        numeroToken = 8;
                        tipo = "Módulo";
                        patron = "[%]+";
                        estado = 0;
                        break;
                    case 9:
                        lexema = lexema + lineas[i].charAt(j);
                        numeroToken = 9;
                        tipo = "Menor que";
                        patron = "[<]+";
                        estado = 0;
                        break;
                    case 10:
                        lexema = lexema + lineas[i].charAt(j);
                        numeroToken = 10;
                        tipo = "Mayor que";
                        patron = "[>]+";
                        estado = 0;
                        break;
                    case 11:
                        lexema = lexema + lineas[i].charAt(j);
                        numeroToken = 11;
                        tipo = "Comentario";
                        patron = "[#]+";
                        estado = 0;
                        break;
                    case 100:
                        estado = -2;
                        break;
                    case 999:
                        lexema = String.valueOf(lineas[i].charAt(j));
                        numeroToken = 999;
                        tipo = "Error léxico";
                        estado = 0;
                        break;
                    default:
                        System.out.println("Error");
                }

                if (estado == 0) {
                    //listaToken.add(new Token(lexema, numeroToken, i + 1, j + 1, tipo, patron));
                    lexema = "";
                }

                if (estado == -2) { //Viene salto de linea o tabulacion o espacios
                    estado = 0;
                }
            }
        }
    }

    public int estadoTransicion(int numero) {
        if ((numero > 96 && numero < 123) || (numero > 64 && numero < 91)) {
            return 1;
        } else if (numero > 47 && numero < 58) {
            return 2;
        } else if (numero == 61) {
            return 3;
        } else if (numero == 43) {
            return 4;
        } else if (numero == 45) {
            return 5;
        } else if (numero == 42) {
            return 6;
        } else if (numero == 47) {
            return 7;
        } else if (numero == 37) {
            return 8;
        } else if (numero == 60) {
            return 9;
        } else if (numero == 62) {
            return 10;
        } else if (numero == 35) {
            return 11;
        } else if (numero == 32 || numero == 13 || numero == 9) {
            return 100;
        } else {
            return 999;
        }
    }

    public String[] separarPalabras(String texto, char separar) {
        String linea = "";
        int contador = 0;

        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == separar) {
                contador++;
            }
        }

        String[] cadenas = new String[contador];
        contador = 0;

        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) != separar) {
                linea = linea + String.valueOf(texto.charAt(i));
            } else {
                cadenas[contador] = linea;
                contador++;
                linea = "";
            }
        }
        return cadenas;
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
        this.diccionarioTipo.put("12345", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("12345", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put(",", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("]", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("[", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("12345", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put(":", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("12345", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("}", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("{", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put(")", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("(", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put(";", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("\'", TipoToken.OTROS_TOKENS);
        this.diccionarioTipo.put("\"", TipoToken.OTROS_TOKENS);

    }
}
