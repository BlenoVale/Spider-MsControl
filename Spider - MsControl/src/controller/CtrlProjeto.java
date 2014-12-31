package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;
import jpa.exceptions.NonexistentEntityException;
import model.Projeto;

/**
 * Class controladora para projetos.
 *
 * @author Paulo, DAN JHONATAN, Bleno Vale
 */
public class CtrlProjeto {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public boolean inserirProjeto(String nomeProjeto, String descricao) {

        Projeto projeto = new Projeto();

        projeto.setNome(nomeProjeto);
        projeto.setDescricao(descricao);
        projeto.setStatus(Projeto.ATIVO);
        projeto.setDataInicio(new Date());

        return saveProjeto(projeto);
    }

    public void editarProjeto(Projeto projeto) {
        try {
            facadeJpa.getProjetoJpa().edit(projeto);
            JOptionPane.showMessageDialog(null, "Alterações salvas com sucesso");
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CtrlProjeto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possível salvar", "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(CtrlProjeto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possível salvar", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mudarStatusDoProjeto(String nomeDoProjeto, int status) {
        Projeto projeto = facadeJpa.getProjetoJpa().findByNome(nomeDoProjeto);

        this.mudarStatusDoProjeto(projeto, status);
    }

    public void mudarStatusDoProjeto(Projeto projeto, int status) {
        projeto.setStatus(status);

        this.editarProjeto(projeto);
    }

    public boolean saveProjeto(Projeto projeto) {

        try {
            facadeJpa.getProjetoJpa().create(projeto);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            return true;
        } catch (RollbackException error) {
            JOptionPane.showMessageDialog(null, "Esse nome de projeto já existe! Por favor, escolha outro nome.", "", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception er) {
            JOptionPane.showMessageDialog(null, "Error ao salvar!", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Projeto> buscaProjetos() {
        try {
            return facadeJpa.getProjetoJpa().findProjetoEntities();
        } catch (Exception error) {
            throw error;
        }
    }

    public Projeto buscaProjetoPeloNome(String nome) {
        try {
            return facadeJpa.getProjetoJpa().findByNome(nome);
        } catch (Exception error) {
            throw error;
        }
    }
    
    /**
     * Busca todos os projetos distintos que o usuario faz parte.
     * 
     * @param id_usuario
     * @return Lista de Strings contendo todos os nomes dos projetos.
     */
    public List<String> buscarProjetosDoUsuario(int id_usuario) {
        try {
            return facadeJpa.getProjetoJpa().findTodosProjetosDistintosByUsuario(id_usuario);
        } catch (Exception error) {
            throw error;
        }
    }
}
