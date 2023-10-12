package backend.sintactico.asignaciondeclaracion;

import backend.lexico.Token;

/**
 *
 * @author michael
 */
public class Asignacion {

    private static String input;
    private static int index;

    public Asignacion() {
        asignacion();
    }

    private void asignacion() {
        if (index < input.length() && Character.isLetter(input.charAt(index))) {
            identificador();
            operadorAsignacion();
            expresion();
        } else if (index < input.length() && input.charAt(index) == '[') {
            listaIdentificadores();
            operadorAsignacion();
            listaExpresiones();
        } else {
            throw new RuntimeException();
        }
    }

    private void listaIdentificadores() {
        identificador();
        while (index < input.length() && input.charAt(index) == ',') {
            index++;
            identificador();
        }
    }

    private void listaExpresiones() {
        expresion();
        while (index < input.length() && input.charAt(index) == ',') {
            index++;
            expresion();
        }
    }

    private void identificador() {
        letra();
        while (index < input.length() && (Character.isLetterOrDigit(input.charAt(index)))) {
            index++;
        }
    }

    private void operadorAsignacion() {
        if (index < input.length() && (input.charAt(index) == '=' || input.charAt(index) == '+'
                || input.charAt(index) == '-' || input.charAt(index) == '*'
                || input.charAt(index) == '/' || input.charAt(index) == '%')) {
            index++;
            if (index < input.length() && input.charAt(index) == '=') {
                index++;
            }
        } else {
            throw new RuntimeException();
        }
    }

    private void expresion() {
        if (index < input.length() && input.charAt(index) == '"') {
            cadena();
        } else {
            numero();
        }
    }

    private void cadena() {
        if (index < input.length() && input.charAt(index) == '"') {
            index++;
            while (index < input.length() && input.charAt(index) != '"') {
                if (input.charAt(index) == '\\') {
                    index += 2; // Saltar el carácter de escape y el siguiente
                } else {
                    index++;
                }
            }
            if (index < input.length() && input.charAt(index) == '"') {
                index++;
            } else {
                throw new RuntimeException();
            }
        } else {
            throw new RuntimeException();
        }
    }

    private void numero() {
        if (index < input.length() && Character.isDigit(input.charAt(index))) {
            while (index < input.length() && Character.isDigit(input.charAt(index))) {
                index++;
            }
        } else {
            throw new RuntimeException();
        }
    }

    private void letra() {
        if (index < input.length() && (Character.isLetter(input.charAt(index)))) {
            index++;
        } else {
            throw new RuntimeException();
        }
    }
}
