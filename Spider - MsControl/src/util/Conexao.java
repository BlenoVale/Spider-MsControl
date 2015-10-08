package util;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {

//    public static EntityManagerFactory conectar() {
//        return Persistence.createEntityManagerFactory("Spider_-_MsControlPU");
//    }

    public static EntityManagerFactory URLdoBanco(String URL) {
        try {
            Map map = new HashMap();
            map.put("javax.persistence.jdbc.url", URL);

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Spider_-_MsControlPU", map);
            //System.out.println(">>funcionou!!!\n>>" + URL);
            return entityManagerFactory;
        } catch (Exception error) {
            System.out.println(">>Não funcionou!!!\n\n");
            throw error;
        }
    }
}
