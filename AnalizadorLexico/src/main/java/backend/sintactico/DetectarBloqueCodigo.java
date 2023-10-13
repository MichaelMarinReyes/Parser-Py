package backend.sintactico;

import backend.lexico.Token;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class DetectarBloqueCodigo {

    private ArrayList<Token> listaTokens;
    private int index;

    public DetectarBloqueCodigo(ArrayList<Token> listaTokens) {
        this.listaTokens = listaTokens;
        this.index = 0;
    }
/*
    public ArrayList<BloqueCodigo> detectarBloques() {
        ArrayList<BloqueCodigo> bloques = new ArrayList<>();
        while (index < listaTokens.size()) {
            BloqueCodigo bloque = detectarSiguienteBloque();
            if (bloque != null) {
                bloques.add(bloque);
            }
        }
        return bloques;
    }
/*
    private BloqueCodigo detectarSiguienteBloque() {
        int inicioBloque = index;
        int nivelIndentacion = listaTokens.get(inicioBloque).getIndentacion();

        while (index < listaTokens.size() && listaTokens.get(index).getIndentacion() >= nivelIndentacion) {
            index++;
        }

        if (index > inicioBloque) {
            ArrayList<Token> tokensBloque = new ArrayList<>(listaTokens.subList(inicioBloque, index));
            return new BloqueCodigo(tokensBloque);
        }

        return null;
    }*/
}
