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
            try {
                if (listaTokens.get(i).getToken().equals("COMENTARIO")) {
                    listaBloques.add(new BloqueCodigo("Comentario", "Comentario", listaTokens.get(i).getLexema(), listaTokens.get(i).getLinea(), listaTokens.get(i).getColumna(), 1));

                } else if (listaTokens.get(i).getToken().equals("ID") && listaTokens.get(i + 3).getToken().equals("IF") && !listaTokens.get(i + 4).getToken().equals("NOT")) {

                    Token[] arregloTernario = {listaTokens.get(i), listaTokens.get(i + 1), listaTokens.get(i + 2), listaTokens.get(i + 3), listaTokens.get(i + 4), listaTokens.get(i + 5), listaTokens.get(i + 6)};
                    String valor = listaTokens.get(i).getLexema() + " " + listaTokens.get(i + 1).getLexema() + " " + listaTokens.get(i + 2).getLexema() + " " + listaTokens.get(i + 3).getLexema() + " " + listaTokens.get(i + 4).getLexema() + " " + listaTokens.get(i + 5).getLexema() + " " + listaTokens.get(i + 6).getLexema();

                    OperadorTernario ternario = new OperadorTernario(arregloTernario);
                    if (ternario.isAceptado()) {
                        listaBloques.add(new BloqueCodigo("Ternario", "Ternario", valor, listaTokens.get(i).getLinea(), listaTokens.get(i).getLinea(), 1));
                        if (arregloTernario[arregloTernario.length - 1] == null) {
                            i += 6;
                        } else if (arregloTernario[arregloTernario.length - 1] != null) {
                            i += 7;
                        }
                    }
                } else if (listaTokens.get(i).getToken().equals("ID") && listaTokens.get(i + 3).getToken().equals("IF") && listaTokens.get(i + 4).getToken().equals("NOT")) {
                    Token[] arregloTernario = {listaTokens.get(i), listaTokens.get(i + 1), listaTokens.get(i + 2), listaTokens.get(i + 3), listaTokens.get(i + 4), listaTokens.get(i + 5), listaTokens.get(i + 6), listaTokens.get(i + 7)};
                    String valor = listaTokens.get(i).getLexema() + " " + listaTokens.get(i + 1).getLexema() + " " + listaTokens.get(i + 2).getLexema() + " " + listaTokens.get(i + 3).getLexema() + " " + listaTokens.get(i + 4).getLexema() + " " + listaTokens.get(i + 5).getLexema() + " " + listaTokens.get(i + 6).getLexema() + " " + listaTokens.get(i + 7).getLexema();

                    OperadorTernario ternario = new OperadorTernario(arregloTernario);
                    if (ternario.isAceptado()) {
                        listaBloques.add(new BloqueCodigo("Ternario", "Ternario", valor, listaTokens.get(i).getLinea(), listaTokens.get(i).getLinea(), 1));
                        if (arregloTernario[arregloTernario.length - 1] == null) {
                            i += 6;
                        } else if (arregloTernario[arregloTernario.length - 1] != null) {
                            i += 7;
                        }
                    }
                } else if (listaTokens.get(i).getToken().equals("ID") && listaTokens.get(i + 1).getToken().equals("ASIGNACION")
                        && (listaTokens.get(i + 2).getToken().equals("ENTERO") || listaTokens.get(i + 2).getToken().equals("DECIMAL")
                        || listaTokens.get(i + 2).getToken().equals("CADENA") || listaTokens.get(i + 2).getToken().equals("FALSE")
                        || listaTokens.get(i + 2).getToken().equals("TRUE"))) {
                    String valor = listaTokens.get(i).getLexema() + " " + listaTokens.get(i + 1).getLexema() + " " + listaTokens.get(i + 2).getLexema();
                    Token[] tokens = {listaTokens.get(i), listaTokens.get(i + 1), listaTokens.get(i + 2)};
                    Declaracion declarar = new Declaracion(tokens);
                    if (declarar.isEsAceptado()) {
                        listaBloques.add(new BloqueCodigo("Declaración", "Declaración", valor, listaTokens.get(i).getLinea(), listaTokens.get(i).getColumna(), 1));
                    }
                    i += 2;

                } else if (listaTokens.get(i).getToken().equals("ID")) {
                    String valor = listaTokens.get(i).getLexema() + " " + listaTokens.get(i + 1).getLexema() + " " + listaTokens.get(i + 2).getLexema();
                    Token[] tokens = {listaTokens.get(i), listaTokens.get(i + 1), listaTokens.get(i + 2)};
                    Asignacion asignacion = new Asignacion(tokens);
                    if (asignacion.isEsAceptado()) {
                        listaBloques.add(new BloqueCodigo("Asignación", "Asignación", valor, listaTokens.get(i).getLinea(), listaTokens.get(i).getColumna(), 1));
                    }

                } else if (listaTokens.get(i).getToken().equals("IF")) {
                    /* String valor = " ";
                DetectarBloqueCodigo detectarBloque = new DetectarBloqueCodigo(listaTokens);
                CondicionalIfElse condicionalIf = new CondicionalIfElse(detectarBloque.reconocerBloque(i));
                while (i < (detectarBloque.reconocerBloque(i).length)) {
                    valor.concat(" " + listaTokens.get(i).getLexema());
                }
                if (condicionalIf.isAceptado()) {
                    listaBloques.add(new BloqueCodigo("IF, IF-ELSE, IF-ELIF-ELSE", "Condicional", valor, listaTokens.get(i).getLinea(), listaTokens.get(i).getLinea(), 1));
                }*/

                } else {
                    listaBloques.add(new BloqueCodigo("Error sintáctico", "Error sintáctico", listaTokens.get(i).getLexema(), listaTokens.get(i).getLinea(), listaTokens.get(i).getColumna(), 1));
                }
            } catch (IndexOutOfBoundsException e) {
                //e.printStackTrace();
            }

        }
    }

}
