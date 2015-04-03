package jpa.extensao;

import javax.persistence.EntityManagerFactory;
import jpa.ProcedimentodeanaliseJpaController;
import util.Conexao;

/**
 *
 * @author Géssica
 */
public class ProcedimentoDeAnaliseJpa extends ProcedimentodeanaliseJpaController {

    public ProcedimentoDeAnaliseJpa() {
        super(Conexao.conectar());
    }
    
    
}
