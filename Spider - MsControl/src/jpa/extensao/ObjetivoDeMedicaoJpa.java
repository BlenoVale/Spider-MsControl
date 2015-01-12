package jpa.extensao;

import javax.persistence.EntityManager;
import jpa.ObjetivodemedicacaoJpaController;
import model.Objetivodemedicacao;
import util.Conexao;

/**
 *
 * @author BlenoVale
 */
public class ObjetivoDeMedicaoJpa extends ObjetivodemedicacaoJpaController{
    
    public ObjetivoDeMedicaoJpa(){
         super(Conexao.conectar());
    }
    
    public Objetivodemedicacao findByNome (String nomeObjMedicao){
        try {
            EntityManager entityManager = super.getEntityManager();
            return (Objetivodemedicacao) entityManager.createQuery("SELECT o FROM Objetivodemedicacao o WHERE o.nome = :nome")
                    .setParameter("nome", nomeObjMedicao).getSingleResult();
        } catch (Exception error) {
            throw error;
        }
    }
    
}
