package backend.sintactico.asignaciondeclaracion;

import backend.lexico.Token;
import backend.lexico.identificadores.ComparacionEnum;
import backend.lexico.identificadores.LogicoEnum;
import backend.lexico.identificadores.PalabraClaveEnum;

/**
 *
 * @author michael
 */
public class OperadorTernario {

    private Token[] tokens = new Token[8];
    private boolean aceptado = false;

    public OperadorTernario(Token[] tokens) {
        this.tokens = tokens;
        ternario();
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void ternario() {
        if (tokens[0].getToken().equals("ID")) {
            esAsignacion();
        }
    }

    private void esAsignacion() {
        if (tokens[1].getToken().equals(ComparacionEnum.ASIGNACION.name())) {
            condicionTrue();
        }
    }

    private void condicionTrue() {
        if (tokens[2].getToken().equals("ID") || tokens[2].getToken().equals("ENTERO") || tokens[2].getToken().equals("DECIMAL") || tokens[2].getToken().equals("CADENA")) {
            siguienteCondicional();
        }
    }

    private void siguienteCondicional() {
        if (tokens[3].getToken().equals(PalabraClaveEnum.IF.name()) && !tokens[4].getToken().equals(LogicoEnum.NOT.name())) {
            condicionFalse(4);
        } else if (tokens[3].getToken().equals(PalabraClaveEnum.IF.name()) && tokens[4].getToken().equals(LogicoEnum.NOT.name())) {
            condicionFalse(5);
        }
    }

    private void condicionFalse(int indice) {
        if (indice == 4) {
            if (tokens[indice].getToken().equals("ID") || tokens[indice].getToken().equals("ENTERO") || tokens[indice].getToken().equals("DECIMAL") || tokens[indice].getToken().equals("CADENA")) {
                condicionElse(5);
            }
        } else if (indice == 5) {
            if (tokens[indice].getToken().equals("ID") || tokens[indice].getToken().equals("ENTERO") || tokens[indice].getToken().equals("DECIMAL") || tokens[indice].getToken().equals("CADENA")) {
                condicionElse(6);
            }
        }
    }

    private void condicionElse(int indice) {
        if (indice == 5) {
            if (tokens[indice].getToken().equals(PalabraClaveEnum.ELSE.name())) {
                ultimoToken(6);
            }
        } else if (indice == 6) {
            if (tokens[indice].getToken().equals(PalabraClaveEnum.ELSE.name())) {
                ultimoToken(7);
            }
        }
    }

    private void ultimoToken(int indice) {
        if (indice == 6) {
            if (tokens[indice].getToken().equals("ID") || tokens[indice].getToken().equals("ENTERO") || tokens[indice].getToken().equals("DECIMAL") || tokens[indice].getToken().equals("CADENA")) {
                aceptado = true;
            }
        } else if (indice == 7) {
            if (tokens[indice].getToken().equals("ID") || tokens[indice].getToken().equals("ENTERO") || tokens[indice].getToken().equals("DECIMAL") || tokens[indice].getToken().equals("CADENA")) {
                aceptado = true;
            }
        }

    }
}
