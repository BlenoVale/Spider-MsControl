package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DAN JHONATAN
 */
public class Texto {

    public static String formataData(Date data) {

        if (data == null)
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE   dd / MMMM / yyyy");
        return sdf.format(data);
    }

}
