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

    public Token[] reconocerBloque(int cantidadTokens) {
        int contador = 0;
        int tamañoLista = cantidadTokens;

        for (int i = cantidadTokens; i < listaTokens.size(); i++) {
            if (listaTokens.get(i).isIdentacion() == false) {
                break;
            }
            contador++;
        }
        
        Token[] arregloToken = new Token[contador];
        for (int i = 0; i < arregloToken.length; i++) {
            System.out.println(arregloToken[i].getToken());
            
        }
        return arregloToken;
    }
}
