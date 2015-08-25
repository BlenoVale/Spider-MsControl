package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Analise;
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

            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Error inesperado.");
            throw error;
        }
    }

    public List<Analise> buscarAnalisesDoProjeto(int idProjeto) {
        try {
            return facadeJpa.getAnaliseJpa().findListAnaliseByIDProjeto(idProjeto);
        } catch (Exception error) {
            throw error;
        }
    }

}
