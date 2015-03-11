package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import model.Medida;
import model.Registromedida;
import util.Copia;

/**
 *
 * @author Paulo
 */
public class CtrlMedida {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public void criarNovaMedida(Medida medida) {
        try {
            facadeJpa.getMedidaJpaController().create(medida);
            System.out.println("Medida Criada");
        } catch (Exception e) {
            System.out.println("Erro cadastro Medida");
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
            throw error;
        }
    }

}
