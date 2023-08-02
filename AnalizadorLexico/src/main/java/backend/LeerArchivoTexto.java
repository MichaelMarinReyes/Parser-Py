package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author michael
 */
public class LeerArchivoTexto {

    private String path;

    public String abrirArchivo(String ruta) {
        String texto = "";
        path = ruta;
        try {
            File archivo = new File(ruta);
            FileReader lector = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(lector);
            String linea;
            while ((linea = buffer.readLine()) != null) {
                texto += linea + "\n";
            }
            buffer.close();
            lector.close();
            //System.out.println(texto);
        } catch (IOException error) {
            System.out.println(error);
        }
        return texto;
    }
}
