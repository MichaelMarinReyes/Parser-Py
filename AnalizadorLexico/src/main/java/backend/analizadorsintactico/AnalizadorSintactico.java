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
    private ArrayList<Integer> cantidadFunciones;
    private ArrayList<Integer> cantidadLlamadas;
    private ArrayList<Integer> erroresLexicos;
    private ArrayList<Integer> erroresSintacticos;

    private ArrayList<Token> listaToken;
    private int indiceActual;
    private StringBuilder bloqueDeCodigoIdentificado = new StringBuilder();

    public AnalizadorSintactico(ArrayList<Token> listaToken) {
        this.listaToken = listaToken;
        this.indiceActual = 0;
    }

    public void analizar() {
        while (indiceActual < listaToken.size()) {
            Token tokenActual = listaToken.get(indiceActual);

            if (tokenActual.getToken().equals(TipoToken.ID.toString())) {
                // Regla para declaración y asignación de variables
                if (indiceActual + 2 < listaToken.size()
                        && listaToken.get(indiceActual + 1).getToken().equals(TipoToken.ASIGNACION.toString())) {
                    Token identificador = tokenActual;
                    Token asignacion = listaToken.get(indiceActual + 1);
                    Token expresion = listaToken.get(indiceActual + 2);

                    System.out.println("Declaración de variable:");
                    System.out.println("Identificador: " + identificador.getLexema());
                    System.out.println("Operador de asignación: " + asignacion.getLexema());
                    System.out.println("Expresión: " + expresion.getLexema());

                    indiceActual += 3; // Avanzar 3 tokens (identificador, asignación, expresión)
                    mostrarBloqueDeCodigo();
                }
            } else if (tokenActual.getToken().equals(TipoToken.ERROR_LEXICO.toString())) {
                // Manejar errores léxicos
                System.out.println("Error léxico en la línea " + tokenActual.getLinea() + ", columna " + tokenActual.getColumna() + ": " + tokenActual.getLexema());
                indiceActual++;
                mostrarBloqueDeCodigo();
            } else if (tokenActual.getToken().equals(TipoToken.ID.toString()) && listaToken.get(indiceActual + 1).getLexema().equals("=") && listaToken.get(indiceActual + 2).getLexema().equals("expresión?")) {
                Token identificador = tokenActual;
                Token operadorTernario = listaToken.get(indiceActual + 1);
                Token expresion = listaToken.get(indiceActual + 2);
                Token valorTrue = listaToken.get(indiceActual + 3);
                Token separador = listaToken.get(indiceActual + 4);
                Token valorFalse = listaToken.get(indiceActual + 5);

                System.out.println("Operador ternario:");
                System.out.println("Identificador: " + identificador.getLexema());
                System.out.println("Operador ternario: " + operadorTernario.getLexema());
                System.out.println("Expresión: " + expresion.getLexema());
                System.out.println("Valor True: " + valorTrue.getLexema());
                System.out.println("Separador: " + separador.getLexema());
                System.out.println("Valor False: " + valorFalse.getLexema());

                indiceActual += 6; // Avanzar 6 tokens (identificador, operador ternario, expresión, valor true, separador, valor false)
                mostrarBloqueDeCodigo();
            } else if (tokenActual.getLexema().equals("for")) {
                Token forToken = tokenActual;
                Token variable = listaToken.get(indiceActual + 1);
                Token inToken = listaToken.get(indiceActual + 2);
                Token rangeToken = listaToken.get(indiceActual + 3);
                Token dosPuntos = listaToken.get(indiceActual + 4);

                System.out.println("Ciclo For:");
                System.out.println("Token 'for': " + forToken.getLexema());
                System.out.println("Variable: " + variable.getLexema());
                System.out.println("Token 'in': " + inToken.getLexema());
                System.out.println("Token 'range': " + rangeToken.getLexema());
                System.out.println("Token ':': " + dosPuntos.getLexema());

                indiceActual += 5; // Avanzar 5 tokens (for, variable, in, range, dosPuntos)
                mostrarBloqueDeCodigo();
            } else if (tokenActual.getLexema().equals("while")) {
                Token whileToken = tokenActual;
                Token condicion = listaToken.get(indiceActual + 1);
                Token dosPuntos = listaToken.get(indiceActual + 2);

                System.out.println("Ciclo While:");
                System.out.println("Token 'while': " + whileToken.getLexema());
                System.out.println("Condición: " + condicion.getLexema());
                System.out.println("Token ':': " + dosPuntos.getLexema());

                indiceActual += 3; // Avanzar 3 tokens (while, condicion, dosPuntos)
                mostrarBloqueDeCodigo();
            } else if (tokenActual.getLexema().equals("def")) {
                Token defToken = tokenActual;
                Token nombreFuncion = listaToken.get(indiceActual + 1);
                Token parentesisAbre = listaToken.get(indiceActual + 2);
                Token parametro = listaToken.get(indiceActual + 3);
                Token parentesisCierra = listaToken.get(indiceActual + 4);
                Token dosPuntos = listaToken.get(indiceActual + 5);

                System.out.println("Definición de Función:");
                System.out.println("Token 'def': " + defToken.getLexema());
                System.out.println("Nombre de la función: " + nombreFuncion.getLexema());
                System.out.println("Token '(': " + parentesisAbre.getLexema());
                System.out.println("Parámetro: " + parametro.getLexema());
                System.out.println("Token ')': " + parentesisCierra.getLexema());
                System.out.println("Token ':': " + dosPuntos.getLexema());

                indiceActual += 6; // Avanzar 6 tokens (def, nombreFuncion, parentesisAbre, parametro, parentesisCierra, dosPuntos)
                mostrarBloqueDeCodigo();
            } else if (tokenActual.getToken().equals(TipoToken.COMENTARIO.toString())) {
                System.out.println("Comentario en línea " + tokenActual.getLinea() + ": " + tokenActual.getLexema());
                indiceActual++;
                mostrarBloqueDeCodigo();
            } else {
                // Otros casos
                // ...
                indiceActual++;
                mostrarBloqueDeCodigo();
            }
        }
    }

    private void mostrarBloqueDeCodigo() {
        // Obtener el inicio y fin del bloque de código
        int inicioBloque = indiceActual;
        int finBloque = obtenerFinBloque(); // Implementa este método según tus necesidades

        // Imprimir el bloque de código
        System.out.println("Bloque de código:");
        bloqueDeCodigoIdentificado.setLength(0); // Limpiar el StringBuilder
        for (int i = inicioBloque; i <= finBloque; i++) {
            if (i <= listaToken.size() - 1) {
                Token token = listaToken.get(i);
                bloqueDeCodigoIdentificado.append("Línea ").append(token.getLinea()).append(": ").append(token.getLexema()).append("\n");
            }
        }

        // Actualizar el índice actual
        indiceActual = finBloque + 1;
    }

    private int obtenerFinBloque() {
        // Implementa la lógica para determinar el final del bloque de código.
        // Puedes utilizar diferentes criterios según el lenguaje que estés analizando.
        // Por ejemplo, puedes buscar el cierre de llaves o algún otro indicador específico.

        // Ejemplo básico: buscar el siguiente punto y coma como indicador de fin de bloque
        int i = indiceActual;
        while (i < listaToken.size() && !listaToken.get(i).getLexema().equals(";")) {
            i++;
        }
        return i;
    }

    public String getBloqueDeCodigoIdentificado() {
        return bloqueDeCodigoIdentificado.toString();
    }
}
