package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import model.Coleta;
import model.Registrocoleta;
import util.Copia;

/**
 *
 * @author Géssica
 */
public class CtrlColeta {
    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();
    
    public List<Coleta> getColetaDoProjeto(int idDoProjeto) {
        try {
            return facadeJpa.getColetaJpa().findListaColetaByProjeto(idDoProjeto);
        } catch (Exception error) {
            throw error;
        }
    }
//    private void registraColeta(Coleta coleta, int tipo) {
//        try {
//            coleta = facadeJpa.getColetaJpa().findColeta(coleta.getId());
//
//            Registrocoleta novoRegistro = new Registrocoleta();
//            novoRegistro.setNomeUsuario(Copia.getUsuarioLogado().getNome());
//            novoRegistro.setData(new Date());
//            novoRegistro.setColetaid(coleta);
//            novoRegistro.setTipo(tipo);
////            novoRegistro.setDescricao(null);
//
//            facadeJpa.getRegistroColeta().create(novoRegistro);
//            System.out.println("--Registro de coleta criado.");
//        } catch (Exception error) {
//        }
//    }
//    
//    public void registrar(Coleta coleta, int tipoDeRegistro, String observacao) {
//        try {
//            // Confirmacao que existe uma coleta no banco
//            coleta = facadeJpa.getColetaJpa().findByNomeAndMnemonico(indicador.getNome(), indicador.getMnemonico(), Copia.getProjetoSelecionado().getId());
//
//            Registrocoleta novoRegistro = new Registrocoleta();
//            novoRegistro.setNomeUsuario(Copia.getUsuarioLogado().getNome());
//            novoRegistro.setData(new Date());
//            novoRegistro.setColetaid(coleta);
//            novoRegistro.setTipo(tipoDeRegistro);
//            novoRegistro.setDescricao(observacao);
//
//            facadeJpa.getRegistroColeta().create(novoRegistro);
//            System.out.println("Registro de Coleta criado com sucesso.");
//        } catch (Exception error) {
//            System.err.println("Não foi possivel fazer o registro da Coleta");
//            error.printStackTrace();
//        }
//    }
    
}
