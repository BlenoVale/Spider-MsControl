/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeJpa;
import java.util.List;
import model.Procedimentodeanalise;

/**
 *
 * @author Géssica
 */
public class CtrlProcedimentoDeAnalise {
    
    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();
    
    public List<Procedimentodeanalise> getIndicadoresDoProjeto(int idDoProjeto) {
        try {
            return facadeJpa.getProcedimentodeanaliseJpa().findListaIndicadoresByProjeto(idDoProjeto);
        } catch (Exception error) {
            throw error;
        }
    }
   
}