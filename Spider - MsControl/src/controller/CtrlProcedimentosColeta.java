package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Datasprocedimentocoleta;
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
    
    public boolean criarProcedimentoColeta(Procedimentodecoleta procedimentodecoleta, List<Datasprocedimentocoleta> lista) {
        try {
            facadeJpa.getProcedimentodecoletaJpaController().create(procedimentodecoleta);
            for (int i = 0; i < lista.size(); i++) {
                lista.get(i).setProcedimentoDeColetaid(procedimentodecoleta);
                lista.get(i).setEmUso(Constantes.EM_USO);
                facadeJpa.getDatasprocedimentoColetaJpa().create(lista.get(i));
            }
            registrarProcedimentoColeta(procedimentodecoleta, Constantes.CADASTRO);
            System.out.println("Procedimento coleta criado");
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
            return true;
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Não foi possível criar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
            return false;
        }
    }
    
    public void registrarProcedimentoColeta(Procedimentodecoleta procedimentodecoleta, int tipo) {
        
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
    
    public List<Procedimentodecoleta> findByProjeto(int idProjeto) {
        try {
            return facadeJpa.getProcedimentoColetaJpa().getListByProjeto(idProjeto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Procedimentodecoleta> findByProjetoBuscar(int idProjeto, String nomeMedida) {
        
        Medida medida = new Medida();
        
        medida = facadeJpa.getMedidaJpa().findByNomeProjeto(nomeMedida, idProjeto);
        
        try {
            return (List<Procedimentodecoleta>) facadeJpa.getProcedimentoColetaJpa().findByProjeto(medida.getId(), idProjeto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Procedimentodecoleta> buscarParteDoNomeMedida(String nome, int id_projeto) {
        try {
            return facadeJpa.getMedidaJpa().findMedidaByParteNome(nome, id_projeto);
        } catch (Exception error) {
            throw error;
        }
    }
    
    public boolean editarProcedimentoColeta(Procedimentodecoleta procedimentodecoleta) {
        try {
            facadeJpa.getProcedimentoColetaJpa().edit(procedimentodecoleta);
            registrarProcedimentoColeta(procedimentodecoleta, Constantes.EDICAO);
            System.out.println("Procedimento coleta Editado");
            //JOptionPane.showMessageDialog(null, "Editado com sucesso.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void editaDataProcedimentoColeta(Datasprocedimentocoleta datasprocedimentocoleta) {
        try {
            facadeJpa.getDatasprocedimentoColetaJpa().edit(datasprocedimentocoleta);
            System.out.println("----------------->Foi<-----------------------");
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
    
    public boolean editarProcedimetoDeColetaEhData(Procedimentodecoleta procedimentodecoleta, List<Datasprocedimentocoleta> lista) {
        try {
            facadeJpa.getProcedimentoColetaJpa().edit(procedimentodecoleta);
            if (!lista.isEmpty()) {
                if (procedimentodecoleta.getDatasprocedimentocoletaList().size() > 1) {
                    for (int j = 0; j < procedimentodecoleta.getDatasprocedimentocoletaList().size(); j++) {
                        if (procedimentodecoleta.getDatasprocedimentocoletaList().get(j).getEmUso() == Constantes.NAO_USADO) {
                            facadeJpa.getDatasprocedimentoColetaJpa().destroy(procedimentodecoleta.getDatasprocedimentocoletaList().get(j).getId());
                        }
                    }
                }
                
                for (int i = 0; i < lista.size(); i++) {
                    lista.get(i).setProcedimentoDeColetaid(procedimentodecoleta);
                    lista.get(i).setEmUso(Constantes.NAO_USADO);
                    facadeJpa.getDatasprocedimentoColetaJpa().create(lista.get(i));
                }
            }
            registrarProcedimentoColeta(procedimentodecoleta, Constantes.EDICAO);
            System.out.println("Procedimento coleta Editado");
            JOptionPane.showMessageDialog(null, "Editado com sucesso.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void trocaPeriodicidade(Procedimentodecoleta procedimentodecoleta) {
        try {
            procedimentodecoleta.setPeriodicidade(procedimentodecoleta.getProximaPeriodicidade());
            procedimentodecoleta.setProximaPeriodicidade(null);
            facadeJpa.getProcedimentoColetaJpa().edit(procedimentodecoleta);
            
            if (procedimentodecoleta.getDatasprocedimentocoletaList().size() > 1) {
                for (int j = 0; j < procedimentodecoleta.getDatasprocedimentocoletaList().size(); j++) {
                    if (procedimentodecoleta.getDatasprocedimentocoletaList().get(j).getEmUso() == Constantes.EM_USO) {
                        facadeJpa.getDatasprocedimentoColetaJpa().destroy(procedimentodecoleta.getDatasprocedimentocoletaList().get(j).getId());
                    } else {
                        procedimentodecoleta.getDatasprocedimentocoletaList().get(j).setEmUso(Constantes.EM_USO);
                        facadeJpa.getDatasprocedimentoColetaJpa().edit(procedimentodecoleta.getDatasprocedimentocoletaList().get(j));
                    }
                }
            }
            
        } catch (Exception error) {
        }
    }
}
