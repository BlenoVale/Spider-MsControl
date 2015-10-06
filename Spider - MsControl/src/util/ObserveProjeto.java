package util;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jpa.extensao.ObjetivoDeMedicaoJpa;
import model.Objetivodemedicao;

/**
 *
 * @author Sandro Bezerra
 */
public class ObserveProjeto {

    private static Thread thread = null;
    private int idProjeto;
    private List<Objetivodemedicao> listaMedição;

    public ObserveProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
        inicarThread();
    }

    private void inicarThread() {
        if (thread != null) {
            thread.stop();
        }

        thread = new Thread(gerarRunnable());
        thread.start();
    }

    private Runnable gerarRunnable() {
        return new Runnable() {

            @Override
            public void run() {
                while (true) {
                    listaMedição = new ObjetivoDeMedicaoJpa(Conexao.URLdoBanco(Texto.lerTXT())).findObjetivoMedicaoByIdProjeto(idProjeto);
                    Copia.getViewPrincipal().viewProjeto_ObjetivosDeMedicao.atualiza(listaMedição);

                    try {
                        thread.stop();
                    } catch (Exception error) {
                        Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, error);
                    }
                }
            }
        };
    }

    public List<Objetivodemedicao> getListaMedição() {
        return listaMedição;
    }
}
