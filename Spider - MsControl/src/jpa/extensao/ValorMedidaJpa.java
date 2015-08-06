package jpa.extensao;

import java.util.List;
import javax.persistence.EntityManager;
import jpa.ValormedidaJpaController;
import model.Valormedida;
import util.Conexao;

/**
 *
 * @author Bleno Vale
 */
public class ValorMedidaJpa extends ValormedidaJpaController {

    public ValorMedidaJpa() {
        super(Conexao.conectar());
    }

    public List<Valormedida> findListValorMedidaBynameAndProjeto(String nome, int idProjeto) {
        try {
            EntityManager entityManager = super.getEntityManager();
            return entityManager.createQuery("SELECT v FROM Valormedida v WHERE v.medidaid.mnemonico = :nome AND v.medidaid.projetoId = :idProjeto ORDER BY v.medidaid.mnemonico ASC")
                    .setParameter("nome", nome).setParameter("idProjeto", idProjeto)
                    .getResultList();
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Valormedida findValorMedidaBynameAndIdProjeto(String nome, int idProjeto){
        try {
            EntityManager entityManager = super.getEntityManager();
            return (Valormedida) entityManager.createQuery("SELECT v FROM Valormedida v WHERE v.medidaid.mnemonico = :nome AND v.medidaid.projetoId = :idProjeto")
                    .setParameter("nome", nome).setParameter("idProjeto", idProjeto)
                    .getResultList().get(0);
        } catch (Exception error) {
            throw error;
        }
    }
}
