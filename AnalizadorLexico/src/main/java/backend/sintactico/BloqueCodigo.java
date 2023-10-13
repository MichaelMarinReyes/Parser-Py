package backend.sintactico;

/**
 *
 * @author michael
 */
public class BloqueCodigo {
    private String simbolo;
    private String tipo;
    private String valor;
    private int linea;
    private int columna;
    private int usos;

    public BloqueCodigo(String simbolo, String tipo, String valor, int linea, int columna, int usos) {
        this.simbolo = simbolo;
        this.tipo = tipo;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
        this.usos = usos;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

    public int getUsos() {
        return usos;
    }

    public void setUsos(int usos) {
        this.usos = usos;
    }

    @Override
    public String toString() {
        return "Bloque de Codigo: " + "simbolo: " + simbolo + ", tipo: " + tipo + ", valor: " + valor + ", fila: " + linea + ", columna; " + columna + ", usos: " + usos;
    }    
}
