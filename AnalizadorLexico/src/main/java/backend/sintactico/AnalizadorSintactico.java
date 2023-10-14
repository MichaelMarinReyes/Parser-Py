package backend.sintactico;

import backend.lexico.Token;
import static backend.lexico.identificadores.TipoToken.ASIGNACION;
import backend.sintactico.asignaciondeclaracion.*;
import backend.sintactico.ciclos.CicloFor;
import backend.sintactico.ciclos.CicloWhile;
import backend.sintactico.condicionalesfuncionesmetodos.CondicionalIfElse;
import backend.sintactico.condicionalesfuncionesmetodos.FuncionMetodo;
import frontend.EditorPanel;
import java.util.ArrayList;

/**
 *
 * @author michael
 */
public class AnalizadorSintactico {

    private ArrayList<BloqueCodigo> listaBloques;
    private String bloqueDeCodigoIdentificado = "";
    private ArrayList<Token> listaTokens;
    private DetectarBloqueCodigo detectarBloque;

    public AnalizadorSintactico(ArrayList<Token> listaTokens, ArrayList<BloqueCodigo> bloqueCodigo) {
        this.listaTokens = listaTokens;
        this.listaBloques = bloqueCodigo;
        this.detectarBloque = new DetectarBloqueCodigo(listaTokens);
    }

    public String getBloqueDeCodigoIdentificado() {
        return bloqueDeCodigoIdentificado;
    }

    public void analizar() {
        for (int i = 0; i < listaTokens.size(); i++) {
            if (listaTokens.get(i).getToken().equals("COMENTARIO")) {
                listaBloques.add(new BloqueCodigo("Comentario", "Comentario", listaTokens.get(i).getLexema(), listaTokens.get(i).getLinea(), listaTokens.get(i).getColumna(), 1));
            } else if (listaTokens.get(i).getToken().equals("ID") && listaTokens.get(i + 1).getToken().equals("ASIGNACION") && (listaTokens.get(i + 2).getToken().equals("ENTERO") || listaTokens.get(i + 2).getToken().equals("DECIMAL") || listaTokens.get(i + 2).getToken().equals("CADENA"))) {
                String valor = listaTokens.get(i).getLexema() + " " + listaTokens.get(i + 1).getLexema() + " " + listaTokens.get(i + 2).getLexema();
                Token[] tokens = {listaTokens.get(i), listaTokens.get(i + 1), listaTokens.get(i + 2)};
                Declaracion asignacion = new Declaracion(tokens);
                asignacion.asignacion();
                if (asignacion.isEsAceptado()) {
                    System.out.println(valor);
                    listaBloques.add(new BloqueCodigo("Declaración", "Declaración", valor, listaTokens.get(i).getLinea(), listaTokens.get(i).getColumna(), 1));
                }
                i += 2;
            } else if (listaTokens.get(i).getToken().equals("ID")) {
                String valor = listaTokens.get(i).getToken() + " " + listaTokens.get(i + 1).getToken() + " " + listaTokens.get(i + 2).getToken() + " " + listaTokens.get(i + 3).getToken();
                Token[] tokens = {listaTokens.get(i), listaTokens.get(i + 1), listaTokens.get(i + 2), listaTokens.get(i + 3)};
                listaBloques.add(new BloqueCodigo("Asignación", "Asignación", valor, listaTokens.get(i).getLinea(), listaTokens.get(i).getColumna(), 1));
                i += 2;
            } else {
                listaBloques.add(new BloqueCodigo("Error sintáctico", "Error sintáctico", listaTokens.get(i).getLexema(), listaTokens.get(i).getLinea(), listaTokens.get(i).getColumna(), 1));
            }

        }
    }
}
