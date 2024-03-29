package backend.lexico;

import backend.lexico.identificadores.OtroEnum;
import backend.lexico.identificadores.LogicoEnum;
import backend.lexico.identificadores.TipoToken;
import backend.lexico.identificadores.ComparacionEnum;
import backend.lexico.identificadores.AritmeticosEnum;
import backend.lexico.identificadores.PalabraClaveEnum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author michael
 */
public class AnalizadorLexico {

    private Map<String, AritmeticosEnum> aritmeticosDiccionario;
    private Map<String, ComparacionEnum> comparacionDiccionacio;
    private Map<String, OtroEnum> otrosDiccionario;
    private Map<String, LogicoEnum> logicosDiccionario;
    private Map<String, PalabraClaveEnum> palabrasClaveDiccionario;
    private Map<String, TipoToken> diccionarioTipo;
    private ArrayList<Token> listaToken;
    private boolean identacion = false;

    public AnalizadorLexico(ArrayList<Token> listaToken) {
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

        for (int i = 0; i < entradaChar.length; i++) {
            char letra = entradaChar[i];

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
                        if (letra == ' ') {
                            if (!buffer.isEmpty()) {
                                crearToken(buffer, linea, columna);
                                buffer = "";
                            }
                            columna++;
                        } else if (entradaChar[i] == '\t' && entradaChar[i - 1] == '\n') {
                            this.identacion = true;
                        }
                        break;
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
                                if (entradaChar[i + 1] == '=') {
                                    listaToken.add(new Token(ComparacionEnum.SUMA_ASIGNA.toString(), "+=", linea, columna, "+=", identacion));
                                    buffer = "";
                                    columna++;
                                } else if (entradaChar[i] == '+' && entradaChar[i + 1] != '=') {
                                    listaToken.add(new Token(AritmeticosEnum.SUMA.toString(), "+", linea, columna, "+", identacion));
                                    buffer = "";
                                    columna++;
                                }
                                break;
                            case '-':
                                if (entradaChar[i + 1] == '=') {
                                    listaToken.add(new Token(ComparacionEnum.RESTA_ASIGNA.toString(), "-=", linea, columna, "-=", identacion));
                                    buffer = "";
                                    columna++;
                                } else if (entradaChar[i] == '-' && entradaChar[i + 1] != '=') {
                                    listaToken.add(new Token(AritmeticosEnum.RESTA.toString(), "-", linea, columna, "-", identacion));
                                    buffer = "";
                                    columna++;
                                }
                                break;
                            case '*':
                                if (entradaChar[i + 1] == '*') {
                                    listaToken.add(new Token(AritmeticosEnum.EXPONENTE.toString(), "**", linea, columna, "**", identacion));
                                    buffer = "";
                                    columna++;
                                } else if (entradaChar[i] == '*' && entradaChar[i + 1] == '=') {
                                    listaToken.add(new Token(ComparacionEnum.MULTIPLICA_ASIGNA.toString(), "*=", linea, columna, "*=", identacion));
                                    buffer = "";
                                    columna++;
                                } else if (entradaChar[i + 1] == ' ' && entradaChar[i + 2] == '*') {
                                    listaToken.add(new Token(TipoToken.ERROR_LEXICO.toString(), "* *", linea, columna, "No existe en el alfabeto", identacion));
                                    buffer = "";
                                    columna++;
                                } else if (entradaChar[i] == '*' && (entradaChar[i - 1] != '*')) {
                                    listaToken.add(new Token(AritmeticosEnum.MULTIPLICACION.toString(), "*", linea, columna, "*", identacion));
                                    buffer = "";
                                    columna++;
                                }
                                break;
                            case '/':
                                if (entradaChar[i] == '/' && !(entradaChar[i - 1] == '/')) {
                                    if (entradaChar[i + 1] == '/') {
                                        listaToken.add(new Token(AritmeticosEnum.DIVISION2.toString(), "//", linea, columna, "//", identacion));
                                        buffer = "";
                                        columna++;
                                    } else if (entradaChar[i + 1] == '=') {
                                        listaToken.add(new Token(ComparacionEnum.DIVIDE_ASIGNA.toString(), "/=", linea, columna, "/=", identacion));
                                        buffer = "";
                                        columna++;
                                    } else if (entradaChar[i] == '/' && (entradaChar[i - 1] != '/')) {
                                        listaToken.add(new Token(AritmeticosEnum.DIVISION1.toString(), "/", linea, columna, "/", identacion));
                                        buffer = "";
                                    }
                                    columna++;
                                }
                                break;
                            case '%':
                                if (!buffer.isEmpty()) {
                                    crearToken(buffer, linea, columna);
                                    buffer = "";
                                }
                                crearToken(String.valueOf(letra), linea, columna);
                                columna++;
                                break;
                            case '=':
                                if (entradaChar[i] == '=' && !(entradaChar[i - 1] == '<' || entradaChar[i - 1] == '>' || entradaChar[i - 1] == '!' || entradaChar[i - 1] == '*' || entradaChar[i - 1] == '/' || entradaChar[i - 1] == '+' || entradaChar[i - 1] == '-')) {
                                    if (entradaChar[i + 1] == '=') {
                                        listaToken.add(new Token(ComparacionEnum.IGUAL.toString(), "==", linea, columna, "==", identacion));
                                        buffer = "";
                                        columna++;
                                    } else if (entradaChar[i] == '=' && (entradaChar[i - 1] != '=')) {
                                        listaToken.add(new Token(TipoToken.ASIGNACION.toString(), "=", linea, columna, "=", identacion));
                                        buffer = "";
                                    }
                                    columna++;
                                }
                                break;
                            case '>':
                                if (entradaChar[i + 1] == '=') {
                                    listaToken.add(new Token(ComparacionEnum.MAYOR_IGUAL_QUE.toString(), ">=", linea, columna, ">=", identacion));
                                    buffer = "";
                                    columna++;
                                } else {
                                    listaToken.add(new Token(ComparacionEnum.MAYOR_QUE.toString(), ">", linea, columna, ">", identacion));
                                    buffer = "";
                                }
                                columna++;
                                break;
                            case '<':
                                if (entradaChar[i + 1] == '=') {
                                    listaToken.add(new Token(ComparacionEnum.MENOR_IGUAL_QUE.toString(), "<=", linea, columna, "<=", identacion));
                                    buffer = "";
                                    columna++;
                                } else {
                                    listaToken.add(new Token(ComparacionEnum.MENOR_QUE.toString(), "<", linea, columna, "<", identacion));
                                    buffer = "";
                                }
                                columna++;
                                break;
                            case '!':
                                if (entradaChar[i + 1] == '=') {
                                    listaToken.add(new Token(ComparacionEnum.DIFERENTE.toString(), "!=", linea, columna, "!=", identacion));
                                    buffer = "";
                                    columna++;
                                } else if (entradaChar[i + 1] == ' ') {
                                    listaToken.add(new Token(TipoToken.ERROR_LEXICO.toString(), "! ", linea, columna, "No existe en el alfabeto", identacion));
                                    buffer = "";
                                }
                                columna++;
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
        if (esCadena) {
            crearToken("error " + buffer, linea, columna);
            buffer = "";
        }
    }

    private void crearToken(String lexema, int linea, int columna) {
        if (esNumero(lexema)) {
            if (lexema.contains(".")) {
                listaToken.add(new Token(TipoToken.DECIMAL.toString(), lexema, linea, columna, "([0-9]+.[0-9]+)", identacion));
            } else {
                listaToken.add(new Token(TipoToken.ENTERO.toString(), lexema, linea, columna, "[0-9]+", identacion));
            }
        } else if (esID(lexema)) {
            listaToken.add(new Token(TipoToken.ID.toString(), lexema, linea, columna, "([\\w]|_)+(\\w|\\d)*", identacion));
        } else if (lexema.contains("#")) {
            listaToken.add(new Token(TipoToken.COMENTARIO.toString(), lexema, linea, columna, "(#[0-9]*[a-z]* | #[0-9]*[A-Z]* #[a-z]*[0-9]* | #[A-Z]*[0-9]*)", identacion));
        } else if (errorLexico(lexema)) {
            if (lexema.contains("\n")) {
                //String[] errores = lexema.split("\n");
                listaToken.add(new Token(TipoToken.ERROR_LEXICO.toString(), lexema, linea, columna, "No existe en el alfabeto", identacion));
                //listaToken.add(new Token(TipoToken.ERROR_LEXICO.toString(), errores[1], linea, columna, "No existe en el alfabeto"));
            } else {
                listaToken.add(new Token(TipoToken.ERROR_LEXICO.toString(), lexema, linea, columna, "No existe en el alfabeto", identacion));
            }
        } else if (esCadena(lexema)) {
            listaToken.add(new Token(TipoToken.CADENA.toString(), lexema, linea, columna, "((\"[a-z]*[0-9]*\") | (\"[A-Z]*[0-9])\") | ((\'[a-z]*[0-9]*\') | (\'[A-Z]*[0-9])\')", identacion));
        } else if (palabrasClaveDiccionario.containsKey(lexema)) {
            PalabraClaveEnum palabraClave = palabrasClaveDiccionario.get(lexema);
            listaToken.add(new Token(palabraClave.toString(), lexema, linea, columna, lexema, identacion));
        } else if (lexema.contains("error")) {
            String[] texto = lexema.split("error");
            listaToken.add(new Token(TipoToken.ERROR_LEXICO.toString(), texto[1], linea, columna, texto[1], identacion));
        } else if (lexema.contains("_")) {
            char[] texto = lexema.toCharArray();
            String inicial = String.valueOf(texto[0]);
            boolean comienzaNumero = false;
            comienzaNumero = esNumero(inicial);
            if (comienzaNumero) {
                listaToken.add(new Token(TipoToken.ERROR_LEXICO.toString(), lexema, linea, columna, "No existe en el lenguaje", identacion));
            } else {
                listaToken.add(new Token(TipoToken.ID.toString(), lexema, linea, columna, "([\\w]|_)+(\\w|\\d)*", identacion));
            }
        } else if (diccionarioTipo.containsKey(lexema)) {
            TipoToken tipoToken = diccionarioTipo.get(lexema);
            listaToken.add(new Token(tipoToken.toString(), lexema, linea, columna, lexema, identacion));
        } else {
            if (aritmeticosDiccionario.containsKey(lexema)) {
                AritmeticosEnum aritmetico = aritmeticosDiccionario.get(lexema);
                listaToken.add(new Token(aritmetico.toString(), lexema, linea, columna, lexema, identacion));
            } else if (comparacionDiccionacio.containsKey(lexema)) {
                ComparacionEnum comparacion = comparacionDiccionacio.get(lexema);
                listaToken.add(new Token(comparacion.toString(), lexema, linea, columna, lexema, identacion));
            } else if (logicosDiccionario.containsKey(lexema)) {
                LogicoEnum logico = logicosDiccionario.get(lexema);
                listaToken.add(new Token(logico.toString(), lexema, linea, columna, lexema, identacion));
            } else if (otrosDiccionario.containsKey(lexema)) {
                OtroEnum otro = otrosDiccionario.get(lexema);
                listaToken.add(new Token(otro.toString(), lexema, linea, columna, lexema, identacion));
            } else {
                listaToken.add(new Token(TipoToken.ERROR_LEXICO.toString(), lexema, linea, columna, "No existe en el alfabeto", identacion));
            }
        }
    }

    private boolean errorLexico(String lexema) {
        char[] cadena = lexema.toCharArray();

        for (int i = 0; i < cadena.length; i++) {
            if (cadena[i] == 10) {
                return true;
            } else if (cadena[0] == 39 || cadena[cadena.length - 1] == 34) {
                if (cadena[0] == 34 && cadena[cadena.length - 1] == 34) {
                    return false;
                } else if (cadena[0] == 39 && cadena[cadena.length - 1] == 39) {
                    return false;
                }
            }
        }
        return false;
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
        return ((lexema.startsWith("\"") && lexema.endsWith("\""))) || ((lexema.startsWith("\'") && lexema.endsWith("\'")));
    }

    private boolean esID(String lexema) {
        return lexema.matches("[a-zA-Z_][a-zA-Z0-9_]*") && !palabrasClaveDiccionario.containsKey(lexema) && !logicosDiccionario.containsKey(lexema);
    }

    private void iniciarDiccionarios() {
        aritmeticosDiccionario = new HashMap<>();
        comparacionDiccionacio = new HashMap<>();
        logicosDiccionario = new HashMap<>();
        otrosDiccionario = new HashMap<>();
        palabrasClaveDiccionario = new HashMap<>();
        diccionarioTipo = new HashMap<>();

        this.aritmeticosDiccionario.put("+", AritmeticosEnum.SUMA);
        this.aritmeticosDiccionario.put("-", AritmeticosEnum.RESTA);
        this.aritmeticosDiccionario.put("**", AritmeticosEnum.EXPONENTE);
        this.aritmeticosDiccionario.put("/", AritmeticosEnum.DIVISION1);
        this.aritmeticosDiccionario.put("//", AritmeticosEnum.DIVISION2);
        this.aritmeticosDiccionario.put("%", AritmeticosEnum.MODULO);
        this.aritmeticosDiccionario.put("*", AritmeticosEnum.MULTIPLICACION);
        //LOGICOS
        this.logicosDiccionario.put("and", LogicoEnum.AND);
        this.logicosDiccionario.put("or", LogicoEnum.OR);
        this.logicosDiccionario.put("not", LogicoEnum.NOT);
        //PALABRAS CLAVE
        this.palabrasClaveDiccionario.put("as", PalabraClaveEnum.AS);
        this.palabrasClaveDiccionario.put("assert", PalabraClaveEnum.ASSERT);
        this.palabrasClaveDiccionario.put("break", PalabraClaveEnum.BREAK);
        this.palabrasClaveDiccionario.put("class", PalabraClaveEnum.CLASS);
        this.palabrasClaveDiccionario.put("continue", PalabraClaveEnum.CONTINUE);
        this.palabrasClaveDiccionario.put("def", PalabraClaveEnum.DEF);
        this.palabrasClaveDiccionario.put("del", PalabraClaveEnum.DEL);
        this.palabrasClaveDiccionario.put("elif", PalabraClaveEnum.ELIF);
        this.palabrasClaveDiccionario.put("else", PalabraClaveEnum.ELSE);
        this.palabrasClaveDiccionario.put("except", PalabraClaveEnum.EXCEPT);
        this.palabrasClaveDiccionario.put("False", PalabraClaveEnum.FALSE);
        this.palabrasClaveDiccionario.put("finally", PalabraClaveEnum.FINALLY);
        this.palabrasClaveDiccionario.put("for", PalabraClaveEnum.FOR);
        this.palabrasClaveDiccionario.put("range", PalabraClaveEnum.RANGE);
        this.palabrasClaveDiccionario.put("global", PalabraClaveEnum.GLOBAL);
        this.palabrasClaveDiccionario.put("if", PalabraClaveEnum.IF);
        this.palabrasClaveDiccionario.put("import", PalabraClaveEnum.IMPORT);
        this.palabrasClaveDiccionario.put("in", PalabraClaveEnum.IN);
        this.palabrasClaveDiccionario.put("is", PalabraClaveEnum.IS);
        this.palabrasClaveDiccionario.put("lambda", PalabraClaveEnum.NONE);
        this.palabrasClaveDiccionario.put("None", PalabraClaveEnum.NONE);
        this.palabrasClaveDiccionario.put("nonlocal", PalabraClaveEnum.NONLOCAL);
        this.palabrasClaveDiccionario.put("pass", PalabraClaveEnum.PASS);
        this.palabrasClaveDiccionario.put("raise", PalabraClaveEnum.RAISE);
        this.palabrasClaveDiccionario.put("return", PalabraClaveEnum.RETURN);
        this.palabrasClaveDiccionario.put("True", PalabraClaveEnum.TRUE);
        this.palabrasClaveDiccionario.put("try", PalabraClaveEnum.TRY);
        this.palabrasClaveDiccionario.put("while", PalabraClaveEnum.WHILE);
        this.palabrasClaveDiccionario.put("with", PalabraClaveEnum.WITH);
        this.palabrasClaveDiccionario.put("yield", PalabraClaveEnum.YIELD);
        //COMPARACION
        this.comparacionDiccionacio.put("==", ComparacionEnum.IGUAL);
        this.comparacionDiccionacio.put("=", ComparacionEnum.ASIGNACION);
        this.comparacionDiccionacio.put("!=", ComparacionEnum.DIFERENTE);
        this.comparacionDiccionacio.put(">", ComparacionEnum.MAYOR_QUE);
        this.comparacionDiccionacio.put("<", ComparacionEnum.MAYOR_QUE);
        this.comparacionDiccionacio.put(">=", ComparacionEnum.MAYOR_IGUAL_QUE);
        this.comparacionDiccionacio.put("<=", ComparacionEnum.MENOR_IGUAL_QUE);
        //OTROS
        this.otrosDiccionario.put("#", OtroEnum.COMENTARIO);
        this.otrosDiccionario.put(",", OtroEnum.COMA);
        this.otrosDiccionario.put("]", OtroEnum.CORCHETE_CIERRA);
        this.otrosDiccionario.put("[", OtroEnum.CORCHETE_ABRE);
        this.otrosDiccionario.put(":", OtroEnum.DOS_PUNTOS);
        this.otrosDiccionario.put("}", OtroEnum.LLAVE_CIERRA);
        this.otrosDiccionario.put("{", OtroEnum.LLAVE_ABRE);
        this.otrosDiccionario.put(")", OtroEnum.PARENTESIS_CIERRA);
        this.otrosDiccionario.put("(", OtroEnum.PARENTESIS_ABRE);
        this.otrosDiccionario.put(";", OtroEnum.PUNTO_COMA);
        this.otrosDiccionario.put("\'", OtroEnum.CADENA);
        this.otrosDiccionario.put("\"", OtroEnum.CADENA);
        this.otrosDiccionario.put("_", OtroEnum.GUION_BAJO);
    }
}
