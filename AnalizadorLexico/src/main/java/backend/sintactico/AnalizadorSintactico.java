package backend.sintactico;

import backend.lexico.Token;
import static backend.lexico.identificadores.TipoToken.ASIGNACION;
import backend.sintactico.asignaciondeclaracion.Asignacion;
import backend.sintactico.asignaciondeclaracion.Declaracion;
import backend.sintactico.ciclos.CicloFor;
import backend.sintactico.ciclos.CicloWhile;
import backend.sintactico.condicionalesfuncionesmetodos.CondicionalIfElse;
import backend.sintactico.condicionalesfuncionesmetodos.FuncionesMetodos;
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
    private int index;

    public AnalizadorSintactico(ArrayList<Token> listaTokens, ArrayList<BloqueCodigo> bloqueCodigo) {
        this.listaTokens = listaTokens;
        this.listaBloques = bloqueCodigo;
        this.detectarBloque = new DetectarBloqueCodigo(listaTokens);
    }

    public String getBloqueDeCodigoIdentificado() {
        return bloqueDeCodigoIdentificado;
    }
/*
    public void analizar() {
        try {
            while (index < listaTokens.size()) {
                DetectarBloqueCodigo bloque = detectarSiguienteBloque();
                if (bloque != null) {
                    // Continúa con el análisis
                    switch (bloque.getTipo()) {
                        case ASIGNACION:
                            analizarAsignacion(bloque);
                            listaBloques.add(new BloqueCodigo("Asignación", "Asignación", bloque.toString(), listaTokens.get(index).getLinea(), listaTokens.get(index).getColumna(), 0)); // Agrega el bloque a la lista de bloques
                            break;
                        case DECLARACION:
                            analizarDeclaracion(bloque);
                            listaBloques.add(new BloqueCodigo("Declaración", "Declaración", bloque.toString(), listaTokens.get(index).getLinea(), listaTokens.get(index).getColumna(), 0)); // Agrega el bloque a la lista de bloques
                            break;
                        case CICLO_FOR:
                            analizarCicloFor(bloque);
                            listaBloques.add(new BloqueCodigo("Ciclo For", "Ciclo For", bloque.toString(), listaTokens.get(index).getLinea(), listaTokens.get(index).getColumna(), 0)); // Agrega el bloque a la lista de bloques
                            break;
                        case CICLO_WHILE:
                            analizarCicloWhile(bloque);
                            listaBloques.add(new BloqueCodigo("Ciclo While", "Ciclo While", bloque.toString(), listaTokens.get(index).getLinea(), listaTokens.get(index).getColumna(), 0)); // Agrega el bloque a la lista de bloques
                            break;
                        case CONDICIONAL_IF_ELSE:
                            analizarCondicionalIfElse(bloque);
                            listaBloques.add(new BloqueCodigo("If-else", "If-else", bloque.toString(), listaTokens.get(index).getLinea(), listaTokens.get(index).getColumna(), 0)); // Agrega el bloque a la lista de bloques
                            break;
                        case FUNCION_METODO:
                            analizarFuncionMetodo(bloque);
                            listaBloques.add(new BloqueCodigo("Función/Método", "Función/Método", bloque.toString(), listaTokens.get(index).getLinea(), listaTokens.get(index).getColumna(), 0)); // Agrega el bloque a la lista de bloques
                            break;
                    }
                }
            }
            System.out.println("Análisis completado.");
        } catch (Exception e) {
            System.out.println("Error de sintaxis: " + e.getMessage());
        }
    }/*

    private void analizarAsignacion(BloqueCodigo bloque) {
        String input = bloque.getValor();
        Asignacion analizador = new Asignacion(input);
        analizador.analizar();
    }

    private void analizarDeclaracion(BloqueCodigo bloque) {
        String input = bloque.getValor();
        Declaracion analizador = new Declaracion(input);
        analizador.analizar();
    }

    private void analizarCicloFor(BloqueCodigo bloque) {
        String input = bloque.getValor();
        CicloFor analizador = new CicloFor(input);
        analizador.analizar();
    }

    private void analizarCicloWhile(BloqueCodigo bloque) {
        String input = bloque.getValor();
        CicloWhile analizador = new CicloWhile(input);
        analizador.analizar();
    }

    private void analizarCondicionalIfElse(BloqueCodigo bloque) {
        String input = bloque.getValor();
        CondicionalIfElse analizador = new CondicionalIfElse(input);
        analizador.analizar();
    }

    private void analizarFuncionMetodo(BloqueCodigo bloque) {
        String input = bloque.getValor();
        FuncionesMetodos analizador = new FuncionesMetodos(input);
        analizador.analizar();
    }

    private DetectarBloqueCodigo detectarSiguienteBloque() {
        int inicioBloque = index;
        int nivelIndentacion = listaTokens.get(inicioBloque).getIndentacion();

        while (index < listaTokens.size() && listaTokens.get(index).getIndentacion() >= nivelIndentacion) {
            index++;
        }

        if (index > inicioBloque) {
            ArrayList<Token> tokensBloque = new ArrayList<>(listaTokens.subList(inicioBloque, index));
            // Aquí construyes el objeto BloqueCodigo con la información correcta
            // Puedes utilizar los tokens dentro de tokensBloque para obtener la información necesaria
            return new BloqueCodigo(/* Parámetros con la información correcta );
        }

        return null;
    }*/
}
