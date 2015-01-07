package controller;

import facade.FacadeJpa;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Objetivodemedicacao;

/**
 *
 * @author DAN JHONATAN
 */
public class CtrlObjetivos {
    
    private final FacadeJpa jpa = FacadeJpa.getInstance();
    
    public boolean criarNovoObjetivoMedicao(Objetivodemedicacao objetivo){
        try {
            jpa.getObjetivodemedicacao().create(objetivo);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível criar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CtrlObjetivos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean editarObjetivoMedicao(Objetivodemedicacao objetivo){
        try {
            jpa.getObjetivodemedicacao().edit(objetivo);
            JOptionPane.showMessageDialog(null, "Editado com sucesso");
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível editar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CtrlObjetivos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
