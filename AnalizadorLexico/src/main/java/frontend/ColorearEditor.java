package frontend;

import backend.lexico.Token;
import backend.lexico.identificadores.*;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author michael
 */
public class ColorearEditor {

    public static void colorPalabras(StyledDocument doc, String textoPane, ArrayList<Token> tablaToken) {
        SwingUtilities.invokeLater(() -> {
            doc.setCharacterAttributes(0, textoPane.length(), doc.getStyle("default"), true);
            for (Token token : tablaToken) {
                Color color = getColorForTokenType(token);
                String tokenValue = token.getLexema();

                int tokenStart = textoPane.indexOf(tokenValue);

                while (tokenStart >= 0) {
                    int tokenEnd = tokenStart + tokenValue.length();
                    Style style = doc.addStyle("MyStyle", null);
                    StyleConstants.setForeground(style, color);
                    doc.setCharacterAttributes(tokenStart, tokenEnd - tokenStart, style, false);

                    // Encuentra el siguiente inicio del token en el texto
                    tokenStart = textoPane.indexOf(tokenValue, tokenEnd);
                }
            }
        });
    }

    public static Color getColorForTokenType(Token tokenAColorear) {
        if (esComentario(tokenAColorear.getToken())) {
            return Color.GRAY;
        } else if (tokenAColorear.getToken().equals("ID")) {
            return Color.BLACK;
        } else if (esPalabraReservada(tokenAColorear.getToken())) {
            return new Color(128, 0, 128);
        } else if (esAritmetico(tokenAColorear.getToken()) || esComparacion(tokenAColorear.getToken()) || esLogico(tokenAColorear.getToken())) {
            return new Color(0, 191, 255);
        } else if (esConstante(tokenAColorear.getToken())) {
            return new Color(216, 112, 0);
        } else if (esOtro(tokenAColorear.getToken())) {
            return Color.GREEN;
        } else {
            System.out.println("Token desconocido: " + tokenAColorear);
            return Color.GREEN; // Valor por defecto para tipos desconocidos
        }
    }

    private static boolean esAritmetico(String tipoToken) {
        for (AritmeticosEnum operador : AritmeticosEnum.values()) {
            if (operador.name().equals(tipoToken)) {
                return true;
            }
        }
        return false;

    }

    private static boolean esComparacion(String tipoToken) {
        for (ComparacionEnum operador : ComparacionEnum.values()) {
            if (operador.name().equals(tipoToken)) {
                return true;
            }
        }
        return false;
    }

    private static boolean esLogico(String tipoToken) {
        for (LogicoEnum operador : LogicoEnum.values()) {
            if (operador.name().equals(tipoToken)) {
                return true;
            }
        }
        return false;
    }

    private static boolean esPalabraReservada(String tipoToken) {
        for (PalabraClaveEnum operador : PalabraClaveEnum.values()) {
            if (operador.name().equals(tipoToken)) {
                return true;
            }
        }
        return false;
    }

    private static boolean esOtro(String tipoToken) {
        for (OtroEnum operador : OtroEnum.values()) {
            if (tipoToken.equals("ENTERO") || tipoToken.equals("DECIMAL") || tipoToken.equals("CADENA") || tipoToken.equals("COMENTARIO") || tipoToken.equals("ID")) {
                return false;
            } else if (operador.name().equals(tipoToken)) {
                return true;
            }
        }
        return false;
    }

    private static boolean esConstante(String tipoToken) {
        for (OtroEnum operador : OtroEnum.values()) {
            if (operador.name().equals("ENTERO") || tipoToken.equals("DECIMAL") || tipoToken.equals("CADENA")) {
                return true;
            }
        }
        return false;
    }

    private static boolean esComentario(String tipoToken) {
        for (OtroEnum operador : OtroEnum.values()) {
            if (operador.name().equals(tipoToken)) {
                return true;
            }
        }
        return false;
    }
}
