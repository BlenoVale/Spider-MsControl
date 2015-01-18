package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Objetivodemedicao;
import model.Objetivodequestao;
import model.Registroobjetivomedicao;
import util.Copia;
import view.objetivos.ViewProjeto_ObjetivosDeMedicao_Novo;

/**
 *
 * @author DAN JHONATAN, bleno vale
 */
public class CtrlObjetivos {

    private final FacadeJpa facadejpa = FacadeJpa.getInstance();

    /**
     * Cria um novo objetivo de medicao em um projeto.
     *
     * @param objetivo Objetivo que serah criado
     * @param idProjeto Projeto onde serah criado o objetivo
     * @return true caso seja criado um novo objetivo, false caso ja exista um
     * objetivo com mesmo nome.
     */
    public boolean criarNovoObjetivoMedicao(Objetivodemedicao objetivo, int idProjeto) {

        Objetivodemedicao objetivoAux = facadejpa.getObjetivoDeMedicaoJpa().findByNomeAndIdProjeto(objetivo.getNome(), idProjeto);
        if (objetivoAux != null) {
            JOptionPane.showMessageDialog(null, "Ja existe um objetivo de medição com este nome neste projeto");
            return false;
        }

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

    /**
     * Edita um objetivo de medicao em um projeto.
     *
     * @param objetivo Objetivo que serah editado
     * @param idProjeto Projeto onde serah editado o objetivo
     * @return true caso o objetivo seja editado, false caso ja exista um
     * objetivo com mesmo nome.
     */
    public boolean editarObjetivoMedicao(Objetivodemedicao objetivo, int idProjeto) {

        Objetivodemedicao objetivoAux = facadejpa.getObjetivoDeMedicaoJpa().findByNomeAndIdProjeto(objetivo.getNome(), idProjeto);
        if (objetivoAux != null) {
            if (objetivoAux.getObjetivodemedicaoPK().getId() != objetivo.getObjetivodemedicaoPK().getId()) {
                JOptionPane.showMessageDialog(null, "Ja existe um objetivo de medição com este nome neste projeto");
                return false;
            }
        }

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
        } catch (Exception error) {
            throw error;
        }
    }

    /**
     * Criar um registro sobre objetivo de medicao
     *
     * @param objetivo Objetivo ao qual serah acrescentado um registro
     * @param tipo tipo de registro. Ex: CADASTRO, EDICAO ...
     */
    public void registrar(Objetivodemedicao objetivo, int tipo) {
        Registroobjetivomedicao registro = new Registroobjetivomedicao();
        registro.setData(new Date());
        registro.setNomeUsuario(Copia.getUsuarioLogado().getNome());
        registro.setTipo(tipo);
        registro.setObjetivodemedicao(objetivo);
        try {
            FacadeJpa.getInstance().getRegistroObjetivoMedicaoJpa().create(registro);
            System.out.println("Registro criado");
        } catch (Exception ex) {
            Logger.getLogger(ViewProjeto_ObjetivosDeMedicao_Novo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
