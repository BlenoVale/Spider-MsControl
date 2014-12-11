/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeJpa;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import jpa.exceptions.NonexistentEntityException;
import model.Funcionalidade;
import model.Perfil;

/**
 *
 * @author Bleno Vale
 */
public class CtrlPermissoesDePerfil {

    private FacadeJpa facadeJpa = FacadeJpa.getInstance();
    private List<Perfil> lista_perfil;
    private Perfil perfil_selecionado;
    private List<Funcionalidade> lista_funcionalidade;

    public CtrlPermissoesDePerfil() {
    }

    public List<Perfil> buscaListaDePerfil() {
        return this.lista_perfil = facadeJpa.getPerfilJpa().findPerfilEntities();
    }

    public List<Funcionalidade> buscarListaDeFuncionalidades() {
        try {
            return this.lista_funcionalidade = facadeJpa.getFuncionalidadeJpa().findFuncionalidadeEntities();
        } catch (Exception error) {
            throw error;
        }
    }

    public Perfil buscarPerfilSelecionado(String nome_perfil) {
        return perfil_selecionado = facadeJpa.getPerfilJpa().findByNome(nome_perfil);
    }

    public List<Funcionalidade> buscarListaDeFuncionalidadesDoPerfil() {
        try {
            if (perfil_selecionado.getFuncionalidadeList().isEmpty()) {
                this.buscarListaDeFuncionalidades();
            } else {
                this.buscarListaDeFuncionalidades();

                for (int i = 0; i < perfil_selecionado.getFuncionalidadeList().size(); i++) {
                    for (int j = 0; j < this.lista_funcionalidade.size(); j++) {
                        if (Objects.equals(this.lista_funcionalidade.get(j), perfil_selecionado.getFuncionalidadeList().get(i))) {
                            this.lista_funcionalidade.remove(j);
                        }
                    }
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return this.lista_funcionalidade;
    }

    public void salvarAlterarFuncionalidadesDoPerfil(String nome_perfil, DefaultListModel model) {
        try {
            this.buscarPerfilSelecionado(nome_perfil);
            this.buscarListaDeFuncionalidades();

            List<Funcionalidade> lista_funcionalidadesDoPerfil = new ArrayList<>();
            for (int i = 0; i < this.lista_funcionalidade.size(); i++) {
                for (int j = 0; j < model.getSize(); j++) {
                    if (this.lista_funcionalidade.get(i).getNome().equals(model.get(j).toString())) {
                        lista_funcionalidadesDoPerfil.add(this.lista_funcionalidade.get(i));
                    }
                }
            }

            this.perfil_selecionado.setFuncionalidadeList(lista_funcionalidadesDoPerfil);

            facadeJpa.getPerfilJpa().edit(this.perfil_selecionado);

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CtrlPermissoesDePerfil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CtrlPermissoesDePerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
