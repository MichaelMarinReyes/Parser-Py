package backend.identificadores;

/**
 * Clase que define el tipo de Toke que puede existir.
 *
 * @author michael
 */
public enum TipoToken {
    ARITMETICO,
    COMPARACION,
    LOGICO,
    ASIGNACION,
    PALABRA_RESERVADA,
    CONSTANTE,
    COMENTARIO,
    OTROS_TOKENS,
    ERROR;
}
