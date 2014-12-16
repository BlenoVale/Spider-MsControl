package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;
import jpa.exceptions.NonexistentEntityException;
import model.Projeto;

/**
 *
 * @author Paulo Class controladora para projetos.
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
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CtrlProjeto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possível salvar", "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(CtrlProjeto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possível salvar", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
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
}
