
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.RegistroobjetivoquestaoJpaController;
import model.Registroobjetivoquestao;

/**
 *
 * @author BlenoVale
 */
public class RegistroObjetivoQuestaoJpa extends RegistroobjetivoquestaoJpaController{

    public RegistroObjetivoQuestaoJpa(EntityManagerFactory emf) {
        super(emf);
    }
    
    
    public List<Registroobjetivoquestao> findRegistroByTipoAndIdQuestao(int tipo, int idQuestao) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return  entityManager.createQuery("SELECT r FROM Registroobjetivoquestao r WHERE r.tipo = :tipo AND r.objetivoDeQuestaoid.id = :idQuestao")
                    .setParameter("tipo", tipo)
                    .setParameter("idQuestao", idQuestao)
                    .getResultList();
        } catch (Exception error) {
            throw error;
        }
    }
    
}
