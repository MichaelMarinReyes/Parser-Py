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

    private Map<String, TokenEnum> diccionarioTipo;
    private Map<String, AritmeticosEnum> diccionarioAritmetico;
    private Map<String, ComparacionEnum> diccionarioComparacion;
    private Map<String, LogicoEnum> diccionarioLogico;
    private Map<String, PalabraClaveEnum> diccionarioPalabraClave;
    private Map<String, OtroEnum> diccionarioOtros;
    private ArrayList<Token> listaToken;
    private String buffer;
    // private List<Token> listasToken;

    public Analizador(ArrayList<Token> listaToken) {
        this.listaToken = listaToken;
        diccionarioAritmeticos();
        diccionarioComparacion();
        diccionarioOtros();
        diccionariosLogico();
        diccionarioPalabraClave();
    }

    private void diccionarioAritmeticos() {
        diccionarioAritmetico = new HashMap<>();
        this.diccionarioAritmetico.put("+", AritmeticosEnum.SUMA);
        this.diccionarioAritmetico.put("-", AritmeticosEnum.RESTA);
        this.diccionarioAritmetico.put("**", AritmeticosEnum.EXPONENTE);
        this.diccionarioAritmetico.put("/", AritmeticosEnum.DIVISION1);
        this.diccionarioAritmetico.put("//", AritmeticosEnum.DIVISION2);
        this.diccionarioAritmetico.put("%", AritmeticosEnum.MODULO);
        this.diccionarioAritmetico.put("*", AritmeticosEnum.MULTIPLICACION);
    }

    private void diccionarioComparacion() {
        diccionarioComparacion = new HashMap<>();
        this.diccionarioComparacion.put("==", ComparacionEnum.IGUAL);
        this.diccionarioComparacion.put("=", ComparacionEnum.ASIGNACION);
        this.diccionarioComparacion.put("!=", ComparacionEnum.DIFERENTE);
        this.diccionarioComparacion.put(">", ComparacionEnum.MAYOR_QUE);
        this.diccionarioComparacion.put("<", ComparacionEnum.MENOR_QUE);
        this.diccionarioComparacion.put(">=", ComparacionEnum.MAYOR_IGUAL_QUE);
        this.diccionarioComparacion.put("<=", ComparacionEnum.MENOR_IGUAL_QUE);
    }

    private void diccionariosLogico() {
        diccionarioLogico = new HashMap<>();
        this.diccionarioLogico.put("and", LogicoEnum.AND);
        this.diccionarioLogico.put("or", LogicoEnum.OR);
        this.diccionarioLogico.put("not", LogicoEnum.NOT);
    }

    private void diccionarioPalabraClave() {
        diccionarioPalabraClave = new HashMap<>();
        this.diccionarioPalabraClave.put("as", PalabraClaveEnum.AS);
        this.diccionarioPalabraClave.put("assert", PalabraClaveEnum.ASSERT);
        this.diccionarioPalabraClave.put("break", PalabraClaveEnum.BREAK);
        this.diccionarioPalabraClave.put("class", PalabraClaveEnum.CLASS);
        this.diccionarioPalabraClave.put("continue", PalabraClaveEnum.CONTINUE);
        this.diccionarioPalabraClave.put("def", PalabraClaveEnum.DEF);
        this.diccionarioPalabraClave.put("del", PalabraClaveEnum.DEL);
        this.diccionarioPalabraClave.put("elif", PalabraClaveEnum.ELIF);
        this.diccionarioPalabraClave.put("else", PalabraClaveEnum.ELSE);
        this.diccionarioPalabraClave.put("except", PalabraClaveEnum.EXCEPT);
        this.diccionarioPalabraClave.put("False", PalabraClaveEnum.FALSE);
        this.diccionarioPalabraClave.put("finally", PalabraClaveEnum.FINALLY);
        this.diccionarioPalabraClave.put("for", PalabraClaveEnum.FOR);
        this.diccionarioPalabraClave.put("global", PalabraClaveEnum.GLOBAL);
        this.diccionarioPalabraClave.put("if", PalabraClaveEnum.IF);
        this.diccionarioPalabraClave.put("import", PalabraClaveEnum.IMPORT);
        this.diccionarioPalabraClave.put("in", PalabraClaveEnum.IN);
        this.diccionarioPalabraClave.put("is", PalabraClaveEnum.IS);
        this.diccionarioPalabraClave.put("lambda", PalabraClaveEnum.LAMBDA);
        this.diccionarioPalabraClave.put("None", PalabraClaveEnum.NONE);
        this.diccionarioPalabraClave.put("nonlocal", PalabraClaveEnum.NONLOCAL);
        this.diccionarioPalabraClave.put("pass", PalabraClaveEnum.PASS);
        this.diccionarioPalabraClave.put("raise", PalabraClaveEnum.RAISE);
        this.diccionarioPalabraClave.put("return", PalabraClaveEnum.RETURN);
        this.diccionarioPalabraClave.put("True", PalabraClaveEnum.TRUE);
        this.diccionarioPalabraClave.put("try", PalabraClaveEnum.TRY);
        this.diccionarioPalabraClave.put("while", PalabraClaveEnum.WHILE);
        this.diccionarioPalabraClave.put("with", PalabraClaveEnum.WITH);
        this.diccionarioPalabraClave.put("yeald", PalabraClaveEnum.YIELD);
    }

    private void diccionarioOtros() {
        diccionarioOtros = new HashMap<>();
        this.diccionarioOtros.put("12345", OtroEnum.ID);
        this.diccionarioOtros.put("12345", OtroEnum.CADENA);
        this.diccionarioOtros.put("12345", OtroEnum.COMA);
        this.diccionarioOtros.put("12345", OtroEnum.COMENTARIO);
        this.diccionarioOtros.put("12345", OtroEnum.CORCHETE_DE);
        this.diccionarioOtros.put("12345", OtroEnum.CORCHETE_IZ);
        this.diccionarioOtros.put("12345", OtroEnum.DECIMAL);
        this.diccionarioOtros.put("12345", OtroEnum.DOS_PUNTOS);
        this.diccionarioOtros.put("12345", OtroEnum.ENTERO);
        this.diccionarioOtros.put("12345", OtroEnum.LLAVE_DE);
        this.diccionarioOtros.put("12345", OtroEnum.LLAVE_IZ);
        this.diccionarioOtros.put("12345", OtroEnum.PARENTESIS_DE);
        this.diccionarioOtros.put("12345", OtroEnum.PARENTESIS_IZ);
        this.diccionarioOtros.put("12345", OtroEnum.PUNTO_COMA);
    }

    /*
    //DECKARAR EL ALFABETO
    public void analizador2() {
        this.diccionario = new HashMap<>();
        this.listasToken = new List();
        this.diccionario.put("def", TokenEnum.PALABRA_RESERVADA);
    }

    public void buscarCoincidencias(String cadena) {
        char[] entradaChar = cadena.toCharArray();
        int lineas = 0;
        int columnas = 0;

        for (char letra : entradaChar) {
            switch (letra) {
                case ' ':
                    if (this.diccionario.containsKey(buffer)) {
                        Token token = new Token(buffer, 1, lineas, columnas, this.diccionario.containsValue(this), "asdf");
                        this.listasToken.add(token);
                        buffer = "";
                    }
                    break;
                case '\n':
                    columnas = 1;
                    break;
                case '(':
                    
                    break;
                case ')':

                    break;
                case '\"':

                    break;
                default:
                    buffer += letra;
            }
        }
    }

    private void espacio(String buffer) {
        if (!buffer.isBlank()) {

        }
    }*/
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
                    listaToken.add(new Token(lexema, numeroToken, i + 1, j + 1, tipo, patron));
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
}
