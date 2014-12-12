/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeJpa;
import java.util.Date;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;
import model.Projeto;

/**
 *
 * @author Paulo Class controladora para projetos.
 */
public class CtrlProjeto {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public boolean inserirProjeto(String nomeProjeto, String descricao) {

        boolean verify;
        Projeto projeto = new Projeto();

        projeto.setNome(nomeProjeto);
        projeto.setDescricao(descricao);
        projeto.setStatus(projeto.ATIVO);
        projeto.setDataInicio(new Date());

        verify = saveProjeto(projeto);

        return verify;
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
