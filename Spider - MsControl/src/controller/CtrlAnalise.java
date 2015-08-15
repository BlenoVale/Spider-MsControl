package controller;

import facade.FacadeJpa;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Analise;
import model.Registroanalise;
import util.Constantes;
import util.Copia;

/**
 *
 * @author Bleno Vale
 */
public class CtrlAnalise {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public void cadastrarAnalise(Analise analise) {
        try {
            facadeJpa.getAnaliseJpa().create(analise);
            analise = facadeJpa.getAnaliseJpa().findAnaliseByIDProjeto(Copia.getProjetoSelecionado().getId());

            Registroanalise registroanalise = new Registroanalise();
            registroanalise.setData(new Date());
            registroanalise.setNomeUsuario(Copia.getUsuarioLogado().getNome());
            registroanalise.setTipo(Constantes.CADASTRO);
            registroanalise.setAnaliseid(analise);

            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error inesperado.");
            throw error;
        }
    }

}
