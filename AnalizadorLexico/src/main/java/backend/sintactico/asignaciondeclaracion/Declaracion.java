package backend.sintactico.asignaciondeclaracion;

import backend.lexico.Token;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class Declaracion {

    private static ArrayList<Token> tokens;
    private static int index;

    public Declaracion(ArrayList<Token> tokens) {
        Declaracion.tokens = tokens;
        index = 0;
        verificar();
    }

    private void verificar() {
        try {
            declaracion();
            if (index == tokens.size()) {
                System.out.println("La declaración es válida.");
            } else {
                System.out.println("Error de sintaxis.");
            }
        } catch (Exception e) {
            System.out.println("Error de sintaxis.");
        }
    }

    private void declaracion() {
        identificador();
        operadorAsignacion();
        expresion();
    }

    private void identificador() {
        if (index < tokens.size() && tokens.get(index).getToken().equals("IDENTIFICADOR")) {
            index++;
        } else {
            throw new RuntimeException();
        }
    }

    private void operadorAsignacion() {
        if (index < tokens.size() && tokens.get(index).getLexema().equals("=")) {
            index++;
        } else {
            throw new RuntimeException();
        }
    }

    private void expresion() {
        if (index < tokens.size() && tokens.get(index).getLexema().equals("\"")) {
            cadena();
        } else {
            lista();
        }
    }

    private void lista() {
        if (index < tokens.size() && tokens.get(index).getLexema().equals("[")) {
            index++;
            while (index < tokens.size() && !tokens.get(index).getLexema().equals("]")) {
                expresion();
                if (index < tokens.size() && tokens.get(index).getLexema().equals(",")) {
                    index++;
                } else if (!tokens.get(index).getLexema().equals("]")) {
                    throw new RuntimeException();
                }
            }
            index++;
        } else {
            throw new RuntimeException();
        }
    }

    private void cadena() {
        if (index < tokens.size() && tokens.get(index).getLexema().equals("\"")) {
            index++;
            while (index < tokens.size() && !tokens.get(index).getLexema().equals("\"")) {
                if (tokens.get(index).getLexema().equals("\\")) {
                    index += 2;
                } else {
                    index++;
                }
            }
            if (index < tokens.size() && tokens.get(index).getLexema().equals("\"")) {
                index++;
            } else {
                throw new RuntimeException();
            }
        } else {
            throw new RuntimeException();
        }
    }
}
