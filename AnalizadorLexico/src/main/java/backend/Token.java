package backend;

/**
 *
 * @author michael
 */
public class Token {

    private String lexema;
    private int linea;
    private int columna;
    private String token;
    private String patron = "no implementado";

    public Token() {
    }

    public Token (String token, String patron, String lexema, int linea, int columna) {
        this.token = token;
        this.patron = patron;
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
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
