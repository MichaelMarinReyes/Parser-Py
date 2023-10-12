package backend.sintactico.asignaciondeclaracion;

/**
 *
 * @author michael
 */
public class Declaracion {

    private static String input;
    private static int index;

    public Declaracion() {
        verificar();
    }

    private void verificar() {
        try {
            declaracion();
            if (index == input.length()) {
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
        letra();
        while (index < input.length() && (Character.isLetterOrDigit(input.charAt(index)))) {
            index++;
        }
    }

    private void operadorAsignacion() {
        if (index < input.length() && input.charAt(index) == '=') {
            index++;
        } else {
            throw new RuntimeException();
        }
    }

    private void expresion() {
        if (index < input.length() && input.charAt(index) == '"') {
            cadena();
        } else {
            lista();
        }
    }

    private void lista() {
        if (index < input.length() && input.charAt(index) == '[') {
            index++;
            while (index < input.length() && input.charAt(index) != ']') {
                expresion();
                if (index < input.length() && input.charAt(index) == ',') {
                    index++;
                } else if (input.charAt(index) != ']') {
                    throw new RuntimeException();
                }
            }
            index++;
        } else {
            throw new RuntimeException();
        }
    }

    private void cadena() {
        if (index < input.length() && input.charAt(index) == '"') {
            index++;
            while (index < input.length() && input.charAt(index) != '"') {
                if (input.charAt(index) == '\\') {
                    index += 2;
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

    private void letra() {
        if (index < input.length() && (Character.isLetter(input.charAt(index)))) {
            index++;
        } else {
            throw new RuntimeException();
        }
    }
}
