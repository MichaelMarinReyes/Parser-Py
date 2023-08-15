package backend;

/**
 *
 * @author michael
 */
public class PosicionArchivo {
    final private int linea;
    final private int columna;
    final private String filePath;

    public PosicionArchivo(int linea, int columna, String filePath) {
        this.linea = linea;
        this.columna = columna;
        this.filePath = filePath;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    public String getFilePath() {
        return filePath;
    }
    
    
}
