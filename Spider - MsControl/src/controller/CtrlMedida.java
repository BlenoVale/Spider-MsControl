package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import model.Medida;
import model.Registromedida;
import util.Copia;
import util.Constantes;
import controller.CtrlMedida;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author Paulo
 */
public class CtrlMedida {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public boolean criarNovaMedida(Medida medida) {
        try {
            facadeJpa.getMedidaJpa().create(medida);
            registrarMedida(medida, Constantes.CADASTRO);
            System.out.println("Medida Criada");
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
            return  true;
        } catch (Exception e) {
            System.out.println("Erro cadastro Medida");
            return false;
        }

    }
    public boolean editarMedida(Medida medida) {
        
        Medida  medida1 = facadeJpa.getMedidaJpa().findByNomeAndProjetoDiferente(medida.getNome(), Copia.getProjetoSelecionado().getId(), medida.getId());
        Medida medida2 = facadeJpa.getMedidaJpa().findByMnemonicoAndProjetoDiferente(medida.getMnemonico(), Copia.getProjetoSelecionado().getId(), medida.getId());
       
        if (medida1 != null) {
            JOptionPane.showMessageDialog(null, "Já existe uma medida com esse nome no projeto, escolha outro nome.", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }else if (medida2 != null){
            JOptionPane.showMessageDialog(null, "Já existe um mnemônico com esse nome no projeto, escolha outro mnemônico.", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }
           
        try {
            facadeJpa.getMedidaJpa().edit(medida);
            registrarMedida(medida, Constantes.EDICAO);
            System.out.println("Medida Editada");
            JOptionPane.showMessageDialog(null, "Editado com sucesso.");
            return  true;
        } catch (Exception e) {
            System.out.println("Erro ao editar Medida " +e);
            e.printStackTrace();
            return  false;
        }

    }

    public void registrarMedida(Medida medida, int tipo) {
        medida = facadeJpa.getMedidaJpa().findByNomeAndProjeto(medida.getNome(), medida.getProjetoId());
        Registromedida registro = new Registromedida();

        registro.setData(new Date());
        registro.setNomeUsuario(Copia.getUsuarioLogado().getNome());
        registro.setMedidaid(medida);
        registro.setTipo(tipo);
        try {
            FacadeJpa.getInstance().getRegistroMedidaJpa().create(registro);
            System.out.println("Registro Medida Criado");
        } catch (Exception e) {
            System.err.println("Erro a registrar medida.");
        }
    }

    public boolean checkNomeMedida(String nomeMedida) {
        Medida medida = new Medida();
        medida = facadeJpa.getMedidaJpa().findByNomeAndProjeto(nomeMedida, Copia.getProjetoSelecionado().getId());
        if (medida != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkNomeMnemonico(String nomeMnemonico) {
        Medida medida = new Medida();
        medida = facadeJpa.getMedidaJpa().findByMnemonicoAndProjeto(nomeMnemonico, Copia.getProjetoSelecionado().getId());

        if (medida != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public List<Medida> getMedidaDoProjeto(int idDoProjeto){
        try{
            return facadeJpa.getMedidaJpa().findByProjeto(idDoProjeto);
        }catch(Exception error){
            throw(error); 
        }
    }
    
     public Medida buscarMedidaPeloNome(String nome, int idProjeto) {
        try {
            return facadeJpa.getMedidaJpa().findByNomeAndProjeto(nome, idProjeto);
        } catch (Exception error) {
            error.printStackTrace();
            throw error;
        }
    }
     
     public List<Registromedida> buscarRegistroMedidaPeloIdMedida(int tipo, int idMedida){
         try {
             return facadeJpa.getRegistroMedidaJpa().findRegistroByIdMedida(tipo, idMedida);
         } catch (Exception error) {
             throw (error);
         }
     }
     public String buscarNome(int idMedida, int idProjeto){
         try {
             return facadeJpa.getMedidaJpa().findNomeByProjeto(idMedida, idProjeto);
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
     }

}
