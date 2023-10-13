package backend.sintactico.asignaciondeclaracion;

import backend.lexico.Token;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class Asignacion {

    private static ArrayList<Token> tokens;
    private static int index;

    public Asignacion(ArrayList<Token> tokens) {
        Asignacion.tokens = tokens;
        index = 0;
        asignacion();
    }

    private void asignacion() {
        if (index < tokens.size() && tokens.get(index).getToken().equals("IDENTIFICADOR")) {
            identificador();
            operadorAsignacion();
            expresion();
        } else if (index < tokens.size() && tokens.get(index).getLexema().equals("[")) {
            listaIdentificadores();
            operadorAsignacion();
            listaExpresiones();
        } else {
            throw new RuntimeException();
        }
    }

    private void listaIdentificadores() {
        identificador();
        while (index < tokens.size() && tokens.get(index).getLexema().equals(",")) {
            index++;
            identificador();
        }
    }

    private void listaExpresiones() {
        expresion();
        while (index < tokens.size() && tokens.get(index).getLexema().equals(",")) {
            index++;
            expresion();
        }
    }

    private void identificador() {
        if (index < tokens.size() && tokens.get(index).getToken().equals("IDENTIFICADOR")) {
            index++;
        } else {
            throw new RuntimeException();
        }
    }

    private void operadorAsignacion() {
        if (index < tokens.size() && (tokens.get(index).getLexema().equals("=")
                || tokens.get(index).getLexema().equals("+")
                || tokens.get(index).getLexema().equals("-")
                || tokens.get(index).getLexema().equals("*")
                || tokens.get(index).getLexema().equals("/")
                || tokens.get(index).getLexema().equals("%"))) {
            index++;
            if (index < tokens.size() && tokens.get(index).getLexema().equals("=")) {
                index++;
            }
        } else {
            throw new RuntimeException();
        }
    }

    private void expresion() {
        if (index < tokens.size() && tokens.get(index).getLexema().equals("\"")) {
            cadena();
        } else {
            numero();
        }
    }

    private void cadena() {
        if (index < tokens.size() && tokens.get(index).getLexema().equals("\"")) {
            index++;
            while (index < tokens.size() && !tokens.get(index).getLexema().equals("\"")) {
                if (tokens.get(index).getLexema().equals("\\")) {
                    index += 2; // Saltar el carácter de escape y el siguiente
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

    private void numero() {
        if (index < tokens.size() && tokens.get(index).getToken().equals("NUMERO")) {
            index++;
        } else {
            throw new RuntimeException();
        }
    }
}
