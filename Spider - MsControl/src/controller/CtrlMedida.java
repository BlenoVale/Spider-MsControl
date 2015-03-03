/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeJpa;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Medida;
import util.Constantes;
import model.Registromedida;
import util.Copia;
/**
 *
 * @author Paulo
 */
public class CtrlMedida {
    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();
    
    public void criarNovaMedida(Medida medida){
        try {
            facadeJpa.getMedidaJpa().create(medida);
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar");
        }
        
        
    }
    public void registrarMedida(Medida medida, int tipo){
        medida = facadeJpa.getMedicaoJpa().findByNomeSingle(medida.getNome());
        Registromedida registro = new Registromedida();
        
        registro.setData(new Date());
        registro.setNomeUsuario(Copia.getUsuarioLogado().getNome());
        registro.setMedidaid(medida);
        registro.setTipo(tipo);
        try {
            FacadeJpa.getInstance().getRegistroMedidaJpa().create(registro);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar registro medida.");
        }
    }
    
}
