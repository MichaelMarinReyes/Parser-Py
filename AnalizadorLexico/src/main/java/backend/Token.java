package backend;

import backend.identificadores.TipoToken;

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

    public Token(String token, String lexema, int linea, int columna) {
        this.token = token;
        this.lexema = lexema;
        this.patron = this.definirPatron(token);
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

    private String definirPatron(String token) {
        if (token.equals(TipoToken.ARITMETICO)) {
            return lexema;
        } else if (token.equals(TipoToken.COMPARACION)) {
            return lexema;
        } else if (token.equals(TipoToken.LOGICO)) {
            return lexema;
        } else if (token.equals(TipoToken.ASIGNACION)) {
            return "(=)";
        } else if (token.equals(TipoToken.PALABRA_RESERVADA)) {
            return lexema;
        } else if (token.equals(TipoToken.CONSTANTE)) {
            return "";
        } else if (token.equals(TipoToken.COMENTARIO)) {
            return "(# ([a-z]*[0-9]*) | #([A-Z]*[0-9]))";
        } else if (token.equals(TipoToken.CADENA)) {
            return "(\"([a-z]*[0-9]*)\" | \"([A-Z]*[0-9])\")";
        } else if (token.equals(TipoToken.ERROR_LEXICO)) {
            return "(\"([a-z]*[0-9]*) | \"([A-Z]*[0-9]))";
        } else {
            return lexema;
        }
    }

    @Override
    public String toString() {
        return "TOKEN: " + token + " PATRON: " + patron + " Lexema: " + lexema + " Linea: " + linea + " Columna: " + columna;
        //return "Token{" + "lexema=" + lexema + "} {numeroToken=" + numeroToken + "} {linea=" + linea + "} {columna=" + columna + "} {token=" + token + '}';
    }

}
