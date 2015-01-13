package controller;

import facade.FacadeJpa;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Objetivodemedicacao;
import model.Objetivodequestao;

/**
 *
 * @author DAN JHONATAN, bleno vale
 */
public class CtrlObjetivos {

    private final FacadeJpa facadejpa = FacadeJpa.getInstance();

    public boolean criarNovoObjetivoMedicao(Objetivodemedicacao objetivo) {
        try {
            facadejpa.getObjetivodemedicacao().create(objetivo);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível criar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CtrlObjetivos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean editarObjetivoMedicao(Objetivodemedicacao objetivo) {
        try {
            facadejpa.getObjetivodemedicacao().edit(objetivo);
            JOptionPane.showMessageDialog(null, "Editado com sucesso");
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível editar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CtrlObjetivos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean criarNovaQuestao(Objetivodequestao objetivodequestao) {
        try {
            facadejpa.getObjetivodequestaoJpa().create(objetivodequestao);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            return true;
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Não foi possível Cadastrar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean editarQuestao(Objetivodequestao objetivodequestao) {
        try {
            facadejpa.getObjetivodequestaoJpa().edit(objetivodequestao);
            JOptionPane.showMessageDialog(null, "Editado om sucesso.");
            return true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível Editar", "ERRO DE EDIÇÃO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Objetivodequestao> buscaListaDeQuestoes() {
        try {
            return facadejpa.getObjetivodequestaoJpa().findObjetivodequestaoEntities();
        } catch (Exception error) {
            throw error;
        }
    }

    public Objetivodemedicacao buscaObjetivoDeMedicaoPeloNome(String nome) {
        try {
            return facadejpa.getObjetivoDeMedicaoJpa().findByNome(nome);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Objetivodequestao> listaQuestoesDoProjeto(int id_projeto) {
        try {
            return facadejpa.getObjetivoDeQuestaoJpa().ListQuestoesByProjeto(id_projeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public int contaQuantidadeQuestoesPorProjeto(int id_projeto) {
        try {
            return listaQuestoesDoProjeto(id_projeto).size();
        } catch (Exception error) {
            throw error;
        }
    }

    public Objetivodequestao buscaObjetivoDeQuestaoPeloNome(String nome_questao) {
        try {
            return facadejpa.getObjetivoDeQuestaoJpa().findQuestaoByNome(nome_questao);
        } catch (Exception error) {
            throw error;
        }
    }

}
