package backend.sintactico.condicionalesfuncionesmetodos;

/**
 *
 * @author michael
 */
public class FuncionesMetodos {

    private static String[] tokens;
    private static int currentToken;

    public FuncionesMetodos() {
    }

    private void declaracion() {
        match("def");
        identificador();
        match("(");
        listaParametros();
        match(")");
        match(":");
        bloque();
    }

    private void listaParametros() {
        if (match("identificador")) {
            while (match(",")) {
                identificador();
            }
        }
    }

    private void bloque() {
        while (!match("return") && !match("EOF")) {
            sentencia();
        }
        if (match("return")) {
            expresion();
        }
    }

    private void sentencia() {
        if (match("if")) {
            expresion();
            match(":");
            bloque();
            if (match("else")) {
                match(":");
                bloque();
            }
        } else if (match("identificador")) {
            match("=");
            expresion();
        } else if (match("identificador")) {
            match("(");
            argumentos();
            match(")");
        }
    }

    private void argumentos() {
        expresion();
        while (match(",")) {
            expresion();
        }
    }

    private void expresion() {
        // Implementar lógica para expresiones
    }

    private void identificador() {
        // Implementar lógica para identificador
    }

    private boolean match(String expected) {
        if (currentToken < tokens.length && tokens[currentToken].equals(expected)) {
            currentToken++;
            return true;
        }
        return false;
    }
}
