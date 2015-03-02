/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.extensao;
import jpa.RegistromedidaJpaController;
import util.Conexao;

/**
 *
 * @author Paulo
 */
    public class RegistroMedidaJpa extends RegistromedidaJpaController {
    
        public RegistroMedidaJpa(){
            super(Conexao.conectar());
        }
}
