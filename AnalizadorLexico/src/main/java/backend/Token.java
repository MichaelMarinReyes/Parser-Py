package backend;

/**
 *
 * @author michael
 */
public class Token {
    private String lexema;
    private int numeroToken;
    private int fila;
    private int columna;
    private String tipo;

    public Token() {
    }

    public Token(String lexema, int numeroToken, int fila, int columna, String tipo) {
        this.lexema = lexema;
        this.numeroToken = numeroToken;
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLexema() {
        return lexema;
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

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Token{" + "lexema=" + lexema + "} {numeroToken=" + numeroToken + "} {fila=" + fila + "} {columna=" + columna + "} {tipo=" + tipo + '}';
    }
    
    
}