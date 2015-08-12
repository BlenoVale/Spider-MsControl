
package jpa.extensao;

import jpa.ValorindicadorJpaController;
import util.Conexao;

/**
 *
 * @author Bleno Vale
 */
public class ValorIndicadorJpa extends ValorindicadorJpaController {
    
    public ValorIndicadorJpa(){
        super(Conexao.conectar());
    }
}
