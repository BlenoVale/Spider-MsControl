package util;

import facade.FacadeJpa;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author DAN JHONATAN
 */
public class Observer {

    private final Usuario usuario;
    private static Thread thread = null;
    private final FacadeJpa jpa = FacadeJpa.getInstance();
    private int count = 0;

    public Observer(Usuario usuario) {
        this.usuario = usuario;

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
                    Usuario usuarioAux = jpa.getUsuarioJpa().findByLogin(usuario.getLogin());

                    if (!usuario.equals(usuarioAux)) {
                        JOptionPane.showMessageDialog(null, "Houve uma alteração em sua conta.\nA programa irá reiniciar.");
                        Copia.getViewPrincipal().deslogar();
                        break;
                    }

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    count++;
                    //System.out.println("Checagem: " + count);
                }
            }
        };
    }
}
