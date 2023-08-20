package frontend.graphviz;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author michael
 */
public class GraphvizUtil {

    public static void generarGrafico(String dotCode, String outputFileName) throws IOException {
        File dotFile = new File("temp.dot");
        try (PrintWriter writer = new PrintWriter(dotFile)) {
            writer.println(dotCode);
        }

        String[] cmd = {"dot", "-Tpng", "-o" + outputFileName, "temp.dot"};
        ProcessBuilder processBuilder = new ProcessBuilder(cmd);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dotFile.delete();
    }
}