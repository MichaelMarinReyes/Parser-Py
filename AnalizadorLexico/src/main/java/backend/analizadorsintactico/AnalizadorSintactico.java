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

    public AnalizadorSintactico(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.indiceTokenActual = 0;
    }

    public void analizar() {
        while (indiceTokenActual < tokens.size()) {
            analizarSentencia();
        }
    }

    private void analizarSentencia() {
        if (coincide(TipoToken.IDENTIFICADOR) && coincide(TipoToken.ASIGNACION)) {
            analizarExpresion();
        } else if (coincide(TipoToken.SI)) {
            analizarExpresion();
            analizarBloque();
        } else if (coincide(PalabraClaveEnum.MIENTRAS)) {
            analizarExpresion();
            analizarBloque();
        } else if (coincide(TipoToken.PARA)) {
            coincide(TipoToken.IDENTIFICADOR); // Suponiendo por ahora que hay un identificador después de 'para'
            coincide(TipoToken.EN);
            analizarExpresion();
            analizarBloque();
        } else if (coincide(TipoToken.DEFINIR)) {
            coincide(TipoToken.IDENTIFICADOR); // Suponiendo por ahora que hay un identificador después de 'definir'
            analizarParametros();
            analizarBloque();
        }
    }

    private void analizarExpresion() {
        // Lógica de análisis de expresiones
    }

    private void analizarBloque() {
        coincide(TipoToken.DOS_PUNTOS);
        while (!coincide(TipoToken.FIN_BLOQUE) && indiceTokenActual < tokens.size()) {
            analizarSentencia();
        }
    }

    private void analizarParametros() {
        coincide(TipoToken.PARENTESIS_IZQUIERDO);
        while (!coincide(TipoToken.PARENTESIS_DERECHO)) {
            coincide(TipoToken.IDENTIFICADOR);
            if (!coincide(TipoToken.COMA)) {
                break;
            }
        }
    }

    private boolean coincide(TipoToken tipo) {
        if (indiceTokenActual < tokens.size() && tokens.get(indiceTokenActual).getTipo() == tipo) {
            indiceTokenActual++;
            return true;
        }
        return false;
    }
}
