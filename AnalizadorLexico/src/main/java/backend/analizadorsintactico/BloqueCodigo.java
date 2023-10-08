package backend.analizadorsintactico;

/**
 *
 * @author michael
 */
public class BloqueCodigo {

    private String tipo;
    private String bloqueCodigo;
    private String comentario;

    public BloqueCodigo(String tipo, String bloqueCodigo, String comentario) {
        this.tipo = tipo;
        this.bloqueCodigo = bloqueCodigo;
        this.comentario = comentario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBloqueCodigo() {
        return bloqueCodigo;
    }

    public void setBloqueCodigo(String bloqueCodigo) {
        this.bloqueCodigo = bloqueCodigo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + "\nBloque de código:\n" + bloqueCodigo + "\n" + comentario;
    }
}
