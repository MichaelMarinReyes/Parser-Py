package backend;

/**
 *
 * @author michael
 */
public class Token {

    private String lexema;
    private int numeroToken;
    private int linea;
    private int columna;
    private String token;
    private String patron = "no implementado";

    public Token() {
    }

    public Token(String lexema, int numeroToken, int fila, int columna, String tipo, String patron) {
        this.lexema = lexema;
        this.numeroToken = numeroToken;
        this.linea = fila;
        this.columna = columna;
        this.token = tipo;
        this.patron = patron;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String tipo) {
        this.token = tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getNumeroToken() {
        return numeroToken;
    }

    public void setNumeroToken(int numeroToken) {
        this.numeroToken = numeroToken;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "TOKEN: " + token + " PATRON: " + patron + " Lexema: " + lexema + " Linea: " + linea + " Columna: " + columna;
        //return "Token{" + "lexema=" + lexema + "} {numeroToken=" + numeroToken + "} {linea=" + linea + "} {columna=" + columna + "} {token=" + token + '}';
    }

}
