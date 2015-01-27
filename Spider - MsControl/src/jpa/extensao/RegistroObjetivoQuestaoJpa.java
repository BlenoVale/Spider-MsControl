
package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import jpa.RegistroobjetivoquestaoJpaController;
import model.Registroobjetivoquestao;
import util.Conexao;

/**
 *
 * @author BlenoVale
 */
public class RegistroObjetivoQuestaoJpa extends RegistroobjetivoquestaoJpaController{

    public RegistroObjetivoQuestaoJpa() {
        super(Conexao.conectar());
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
