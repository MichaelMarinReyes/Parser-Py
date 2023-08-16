package backend;

import backend.identificadores.PalabraReservada;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class Analizador {

    private ArrayList<Token> listaToken;

    public Analizador(ArrayList<Token> listaToken) {
        this.listaToken = listaToken;
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
