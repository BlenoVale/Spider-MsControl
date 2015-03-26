package controller;

import facade.FacadeJpa;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Procedimentodecoleta;
import model.Registroprocedimentocoleta;
import util.Copia;
import util.Constantes;

/**
 *
 * @author Paulo
 */
public class CtrlProcedimentosColeta {
    
    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();
    
    public boolean criarProcedimentoColeta(Procedimentodecoleta procedimentodecoleta){
        try {
            facadeJpa.getProcedimentodecoletaJpa().create(procedimentodecoleta);
            registrarProcedimentoColeta(procedimentodecoleta, Constantes.CADASTRO);
            System.out.println("Procedimento coleta criado");
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void registrarProcedimentoColeta(Procedimentodecoleta procedimentodecoleta, int tipo){
        
        Registroprocedimentocoleta registroprocedimentocoleta = new Registroprocedimentocoleta();
        registroprocedimentocoleta.setData(new Date());
        registroprocedimentocoleta.setNomeUsuario(Copia.getUsuarioLogado().getNome());
        registroprocedimentocoleta.setProcedimentoDeColetaid(procedimentodecoleta);
        registroprocedimentocoleta.setTipo(tipo);
        
        try {
            facadeJpa.getRegistroprocedimentocoletaJpaController().create(registroprocedimentocoleta);
            System.out.println("registro procedimento coleta criado");
        } catch (Exception e) {
            System.out.println("erro registro procedimento coleta ");
            e.printStackTrace();
                    
        }
    }
}
