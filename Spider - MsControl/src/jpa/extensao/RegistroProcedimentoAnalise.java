/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;

import facade.FacadeJpa;
import jpa.RegistroprocedimentoanaliseJpaController;
import model.Procedimentodeanalise;
import util.Conexao;

/**
 *
 * @author Paulo
 */
public class RegistroProcedimentoAnalise extends RegistroprocedimentoanaliseJpaController{
    
    FacadeJpa facadeJpa = FacadeJpa.getInstance();    
    
    public RegistroProcedimentoAnalise(){
        super(Conexao.conectar());
    }
    
       
}
