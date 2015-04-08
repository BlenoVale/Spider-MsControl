package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Medida;
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
            facadeJpa.getProcedimentodecoletaJpaController().create(procedimentodecoleta);
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
    public List<Procedimentodecoleta> findByProjeto(int idProjeto){
        try {
            return  facadeJpa.getProcedimentoColetaJpa().getListByProjeto(idProjeto);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
    public List<Procedimentodecoleta> findByProjetoBuscar(int idProjeto, String nomeMedida){
    
        Medida medida = new Medida();
        
        medida = facadeJpa.getMedidaJpa().findByNomeProjeto(nomeMedida, idProjeto);
        
        try {
            return  (List<Procedimentodecoleta>) facadeJpa.getProcedimentoColetaJpa().findByProjeto(medida.getId(), idProjeto);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    public boolean editarProcedimentoColeta(Procedimentodecoleta procedimentodecoleta) {
        try {
            facadeJpa.getProcedimentoColetaJpa().edit(procedimentodecoleta);
            registrarProcedimentoColeta(procedimentodecoleta, Constantes.EDICAO);
            System.out.println("Procedimento coleta Editado");
            JOptionPane.showMessageDialog(null, "Editado com sucesso.");
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
