package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {
    public static EntityManagerFactory conectar(){
        return Persistence.createEntityManagerFactory("Spider_-_MsControlPU");
    }
}
