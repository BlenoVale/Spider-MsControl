package instalador;

import util.Texto;
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
        ExecutaBanco executaBanco = new ExecutaBanco(Texto.lerTXT(), "SpiderMsControl", "SpiderMsControl");
        boolean existe = executaBanco.checaConexao();

        if (existe) {
            new ViewLogin().main(args);
        } else {
            new Instalador().main(args);
        }
    }
}
