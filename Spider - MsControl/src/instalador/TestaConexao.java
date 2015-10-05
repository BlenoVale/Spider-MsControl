package instalador;

import view.ViewLogin;

/**
 *
 * @author Bleno Vale
 */
public class TestaConexao {

    public static void main(String[] args) {
        jaExisteBanco(args);           
    }
    
    private static void jaExisteBanco(String[] args) {
        ExecutaBanco executaBanco = new ExecutaBanco("jdbc:mysql://localhost:3306/spidermscontrol", "SpiderMsControl", "SpiderMsControl");
        boolean existe = executaBanco.checaConexao();

        if (existe) {
            new ViewLogin().main(args);
        } else {
            new Instalador().main(args);
        }
    }
}
