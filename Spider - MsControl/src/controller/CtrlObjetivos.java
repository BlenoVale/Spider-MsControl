package controller;

import facade.FacadeJpa;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Objetivodemedicao;
import model.Objetivodequestao;

/**
 *
 * @author DAN JHONATAN, bleno vale
 */
public class CtrlObjetivos {

    private final FacadeJpa facadejpa = FacadeJpa.getInstance();

    public boolean criarNovoObjetivoMedicao(Objetivodemedicao objetivo) {
        try {
            facadejpa.getObjetivodemedicao().create(objetivo);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível criar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CtrlObjetivos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean editarObjetivoMedicao(Objetivodemedicao objetivo) {
        try {
            facadejpa.getObjetivodemedicao().edit(objetivo);
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
            facadejpa.getObjetivoDeQuestaoJpa().create(objetivodequestao);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            return true;
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Não foi possível Cadastrar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean editarQuestao(Objetivodequestao objetivodequestao) {
        try {
            facadejpa.getObjetivoDeQuestaoJpa().edit(objetivodequestao);
            JOptionPane.showMessageDialog(null, "Editado om sucesso.");
            return true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível Editar", "ERRO DE EDIÇÃO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Objetivodequestao> buscaListaDeQuestoes() {
        try {
            return facadejpa.getObjetivoDeQuestaoJpa().findObjetivodequestaoEntities();
        } catch (Exception error) {
            throw error;
        }
    }

    public Objetivodemedicao buscaObjetivoDeMedicaoPeloNome(String nome) {
        try {
            return facadejpa.getObjetivoDeMedicaoJpa().findByNome(nome);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Objetivodequestao> getQuestoesDoProjeto(int id_projeto) {
        try {
            return facadejpa.getObjetivoDeQuestaoJpa().ListQuestoesByProjeto(id_projeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public int getQuantidadeQuestoesPorProjeto(int id_projeto) {
        try {
            return getQuestoesDoProjeto(id_projeto).size();
        } catch (Exception error) {
            throw error;
        }
    }

    public Objetivodequestao buscaObjetivoDeQuestaoPeloNomeEIdProjeto(String nome_questao, int id_projeto) {
        try {
            return facadejpa.getObjetivoDeQuestaoJpa().findQuestaoByNomeAndIdProjeto(nome_questao, id_projeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Objetivodequestao> buscaParteDoNomeQuestao(String nome_questao, int id_projeto) {
        try {
            return facadejpa.getObjetivoDeQuestaoJpa().findParteNomeQuestao(nome_questao, id_projeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Objetivodequestao> buscaSeNomeQuestaoJaExiste(String nome, int id_projeto, int prioridade) {
        try {
            return facadejpa.getObjetivoDeQuestaoJpa().findListQuestaoByNomeAndIdProejto(nome, id_projeto, prioridade);
        }
        catch(Exception error)
        {
            throw error;
        }
    }

}
