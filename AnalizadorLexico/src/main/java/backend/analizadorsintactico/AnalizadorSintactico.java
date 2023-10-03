package backend.analizadorsintactico;

import backend.lexico.Token;
import backend.lexico.identificadores.*;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class AnalizadorSintactico {

    private ArrayList<Token> tokens;
    private int indiceTokenActual;
    private ArrayList<Integer> cantidadFunciones;
    private ArrayList<Integer> cantidadLlamadas;
    private ArrayList<Integer> erroresLexicos;
    private ArrayList<Integer> erroresSintacticos;

    private ArrayList<Token> listaToken;
    private int indice;

    public AnalizadorSintactico(ArrayList<Token> listaToken) {
        this.listaToken = listaToken;
        this.indice = 0;
        cantidadFunciones = new ArrayList<>();
        cantidadLlamadas = new ArrayList<>();
        erroresLexicos = new ArrayList<>();
        erroresSintacticos = new ArrayList<>();
    }

    public void analizar() {
        while (indice < listaToken.size()) {
            if (reconocerVariable()) {
                System.out.println("Se ha reconocido una declaración de variable.");
            } else if (reconocerFuncion()) {
                System.out.println("Se ha reconocido una declaración de función.");

            } else if (reconocerCiclo()) {
                System.out.println("Se ha reconocido un ciclo.");
            } else if (reconocerComentario()) {
                System.out.println("Se ha encontrado un comentario");
            } else {
                erroresSintacticos.add(1);
                System.out.println("Error de sintaxis en la línea " + listaToken.get(indice).getLinea() + ", columna " + listaToken.get(indice).getColumna());
                System.out.println("Errores sintácticos: " + erroresSintacticos.size());

            }
        }
    }

    private boolean reconocerVariable() {
        if (indice < listaToken.size() && listaToken.get(indice).getToken().equals("ID")) {
            indice++;
            if (indice < listaToken.size() && listaToken.get(indice).getToken().equals("ASIGNACION")) {
                indice++;
                return true;
            }
        }
        return false;
    }

    private boolean reconocerFuncion() {
        if (indice < listaToken.size() && listaToken.get(indice).getToken().equals("DEF")) {
            indice++;
            if (indice < listaToken.size() && listaToken.get(indice).getToken().equals("ID")) {
                indice++;
                if (indice < listaToken.size() && listaToken.get(indice).getToken().equals("PARENTESIS_ABRE")) {
                    indice++;
                    // Aquí puedes implementar la lógica para reconocer los parámetros de la función
                    // ...
                    if (indice < listaToken.size() && listaToken.get(indice).getToken().equals("PARENTESIS_CIERRA")) {
                        indice++;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean reconocerCiclo() {
        if (indice < listaToken.size() && listaToken.get(indice).getToken().equals("FOR")) {
            indice++;
            if (indice < listaToken.size() && listaToken.get(indice).getToken().equals("ID")) {
                indice++;
                if (indice < listaToken.size() && listaToken.get(indice).getToken().equals("IN")) {
                    indice++;
                    // Aquí puedes implementar la lógica para reconocer la expresión iterable
                    // ...
                    if (indice < listaToken.size() && listaToken.get(indice).getToken().equals("DOS_PUNTOS")) {
                        indice++;
                        // Aquí puedes implementar la lógica para reconocer el bloque del ciclo
                        // ...
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean reconocerComentario() {
        if (indice < listaToken.size() && listaToken.get(indice).getToken().equals("COMENTARIO")) {
            indice++;
            return true;
        }
        return false;
    }
}
