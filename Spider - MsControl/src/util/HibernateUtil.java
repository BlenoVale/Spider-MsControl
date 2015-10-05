package util;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Bleno Vale
 */
public class HibernateUtil {
    
    private final String ip;
    private final String porta;
    
    public HibernateUtil(String ip, String porta){
        this.ip = ip;
        this.porta = porta;
    }

    public void mudaURLdoBanco() {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Spider_-_MsControlPU", getConfiguracao());
            System.out.println(">>funcionou!!!");
        } catch (Exception error) {
            System.out.println(">>Não funcionou!!!\n\n");
            error.printStackTrace();

        }
    }

    public Map getConfiguracao() {
        Map map = new HashMap();
        map.put("hibernate.connection.url", "jdbc:mysql://" + ip + ":" + porta + "/spidermscontrol");
        return map;
    }
}
