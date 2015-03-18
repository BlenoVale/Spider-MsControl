package util;

import facade.FacadeJpa;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jpa.extensao.AcessaJpa;
import jpa.extensao.UsuarioJpa;
import model.Acessa;
import model.Usuario;

/**
 *
 * @author DAN JHONATAN
 */
public class Observer {

    private int idUsuario;
    private List<Acessa> acessoDoUsuario;
    private static Thread thread = null;
    private final FacadeJpa jpa = FacadeJpa.getInstance();

    public Observer(Usuario usuario) {
        idUsuario = usuario.getId();
        acessoDoUsuario = new AcessaJpa().findAcessaByIdUsuario(idUsuario);

        inicarThread();
        System.out.println("Observer iniciou");
    }

    private void inicarThread() {
        if (thread != null)
            thread.stop();

        thread = new Thread(gerarRunnable());
        thread.start();
    }

    private Runnable gerarRunnable() {
        return new Runnable() {

            @Override
            public void run() {
                while (true) {
                    List<Acessa> acessaList = new AcessaJpa().findAcessaByIdUsuario(idUsuario);

                    if (!acessaList.equals(acessoDoUsuario)) {
                        JOptionPane.showMessageDialog(null, "Houve uma alteração em sua conta.\nA programa irá reiniciar.");
                        Copia.getViewPrincipal().deslogar();
                        break;
                    }

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
    }
}
