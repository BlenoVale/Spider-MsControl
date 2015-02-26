package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Indicador;
import model.Registroindicador;
import util.Constantes;
import util.Copia;

/**
 *
 * @author Dan
 */
public class CtrlIndicador {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public List<Indicador> findByParteNome(String nome, int idProjeto) {
        return facadeJpa.getIndicadorJpa().findByParteNome(nome, idProjeto);
    }

    public List<Indicador> buscarParteDoNomeIndicador(String nome, int id_projeto) {
        try {
            return facadeJpa.getIndicadorJpa().findIndicadorByParteNome(nome, id_projeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Indicador> getIndicadoresDoProjeto(int idDoProjeto) {
        try {
            return facadeJpa.getIndicadorJpa().findListaIndicadoresByProjeto(idDoProjeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public void editarPrioridadeDaListaDeIndicadores(List<Indicador> listaIndicadores, int idProjeto) {
        try {
            int resposta = JOptionPane.showConfirmDialog(null, "Confirmar alterações?\n\nAo cofirmar as alterações de prioridades elas se tornaram permanentes.");

            if (resposta == JOptionPane.YES_OPTION) {
                List<Indicador> listaBD = facadeJpa.getIndicadorJpa().findListaIndicadoresByProjeto(idProjeto);

                for (int i = 0; i < listaIndicadores.size(); i++) {
                    for (int j = 0; j < listaBD.size(); j++) {
                        if (listaIndicadores.get(i).getNome().equals(listaBD.get(j).getNome()) && listaIndicadores.get(i).getPrioridade() != listaBD.get(j).getPrioridade()) {
                            facadeJpa.getIndicadorJpa().edit(listaIndicadores.get(i));

                            registraIndicador(listaIndicadores.get(i), Constantes.EDICAO);
                            
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Prioridades dos indicadores editadas com sucesso.");
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

//    public boolean criarNovoIndicador(Indicador indicador){
//        
//    }
    
    private void registraIndicador(Indicador indicador, int tipo) {
        try {
            indicador = facadeJpa.getIndicadorJpa().findIndicador(indicador.getId());

            Registroindicador novoRegistro = new Registroindicador();
            novoRegistro.setNomeUsuario(Copia.getUsuarioLogado().getNome());
            novoRegistro.setData(new Date());
            novoRegistro.setIndicadorid(indicador);
            novoRegistro.setTipo(tipo);
//            novoRegistro.setDescricao(null);

            facadeJpa.getRegistroIndicador().create(novoRegistro);
            System.out.println("--Registro de indicador criado.");
        } catch (Exception error) {
        }
    }
}
