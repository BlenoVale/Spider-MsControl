package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DAN JHONATAN
 */
public class Texto {

    public static String formataData(Date data) {

        if (data == null) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE   dd / MMMM / yyyy");
        return sdf.format(data);
    }

    public static String formataDataPraTabela(Date data) {

        if (data == null) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd / MMMM / yyyy");
        return sdf.format(data);
    }

    public static void criaTXT(String texto) {
        try {
            FileWriter arquivo = new FileWriter(new File(System.getProperty("user.dir") + "infosBD.txt"));
            arquivo.write(texto);
            arquivo.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public static String lerTXT() {
        try {
            FileReader arquivo = new FileReader(System.getProperty("user.dir") + "infosBD.txt");
            BufferedReader reader = new BufferedReader(arquivo);
            String dado = null;
            if ((dado = reader.readLine()) != null) {
                return dado;
            }else {
                return null;
            }
        } catch (Exception error) {
            return null;
        }
    }
}
