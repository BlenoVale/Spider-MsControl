package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Objetivodemedicao;
import model.Objetivodequestao;
import model.Registroobjetivomedicao;
import model.Registroobjetivoquestao;
import util.Constantes;
import util.Copia;
import view.objetivos.ViewProjeto_ObjetivosDeMedicao_Novo;

/**
 *
 * @author DAN JHONATAN, bleno vale
 */
public class CtrlObjetivos {

    private final FacadeJpa facadejpa = FacadeJpa.getInstance();

    /**
     * Cria um novo objetivo de medicao em um projeto.
     *
     * @param objetivo Objetivo que serah criado
     * @param idProjeto Projeto onde serah criado o objetivo
     * @return true caso seja criado um novo objetivo, false caso ja exista um
     * objetivo com mesmo nome.
     */
    public boolean criarNovoObjetivoMedicao(Objetivodemedicao objetivo, int idProjeto) {

        Objetivodemedicao objetivoAux = facadejpa.getObjetivoDeMedicaoJpa().findByNomeAndIdProjeto(objetivo.getNome(), idProjeto);
        if (objetivoAux != null) {
            JOptionPane.showMessageDialog(null, "Ja existe um objetivo de medição com este nome neste projeto");
            return false;
        }

        try {
            facadejpa.getObjetivodemedicao().create(objetivo);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível criar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CtrlObjetivos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Edita um objetivo de medicao em um projeto.
     *
     * @param objetivo Objetivo que serah editado
     * @param idProjeto Projeto onde serah editado o objetivo
     * @return true caso o objetivo seja editado, false caso ja exista um
     * objetivo com mesmo nome.
     */
    public boolean editarObjetivoMedicao(Objetivodemedicao objetivo, int idProjeto) {

        Objetivodemedicao objetivoAux = facadejpa.getObjetivoDeMedicaoJpa().findByNomeAndIdProjeto(objetivo.getNome(), idProjeto);
        if (objetivoAux != null) {
            if (objetivoAux.getId() != objetivo.getId()) {
                JOptionPane.showMessageDialog(null, "Ja existe um objetivo de medição com este nome neste projeto");
                return false;
            }
        }

        try {
            facadejpa.getObjetivodemedicao().edit(objetivo);
            JOptionPane.showMessageDialog(null, "Editado com sucesso");
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível editar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CtrlObjetivos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void excluirObjetivoMedicao(Objetivodemedicao objetivodemedicao) {
        try {
            if (objetivodemedicao.getObjetivodequestaoList().isEmpty()){
                for (int i = 0; i < objetivodemedicao.getRegistroobjetivomedicaoList().size(); i++ ){
                    facadejpa.getRegistroObjetivoMedicaoJpa().destroy(objetivodemedicao.getRegistroobjetivomedicaoList().get(i).getId());
                }
//                Objetivodemedicao objAux = facadejpa.getObjetivoDeMedicaoJpa().findObjetivo(objetivodemedicao.getObjetivodemedicaoPK().getId());
//                facadejpa.getObjetivoDeMedicaoJpa().destroy(objAux.getObjetivodemedicaoPK());
            } else {
                JOptionPane.showMessageDialog(null, "Objetivo de medição tem Questões relacioandas.");
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public boolean criarNovaQuestao(Objetivodequestao objetivodequestao) {
        try {
            facadejpa.getObjetivoDeQuestaoJpa().create(objetivodequestao);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            return true;
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
            return false;
        }
    }

    public boolean editarQuestao(Objetivodequestao objetivodequestao) {
        try {
            facadejpa.getObjetivoDeQuestaoJpa().edit(objetivodequestao);
            JOptionPane.showMessageDialog(null, "Editado om sucesso.");
            return true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível editar", "ERRO DE EDIÇÃO", JOptionPane.ERROR_MESSAGE);
            erro.printStackTrace();
            return false;
        }
    }

//    public void editarPrioridadeDaListaDeQuestoes(List<Objetivodequestao> lista_questao, int idProjeto) {
//        try {
//            int resposta = JOptionPane.showConfirmDialog(null, "Confirmar alterações?\n\nAo cofirmar as alterações de prioridades elas se tornaram permanentes.");
//
//            if (resposta == JOptionPane.YES_OPTION) {
//                List<Objetivodequestao> lista_BD = facadejpa.getObjetivoDeQuestaoJpa().ListQuestoesByProjeto(idProjeto);
//                for (int i = 0; i < lista_questao.size(); i++) {
//                    for (int j = 0; j < lista_BD.size(); j++) {
////                        if (lista_questao.get(i).getNome().equals(lista_BD.get(j).getNome()) && lista_questao.get(i).getPrioridade() != lista_BD.get(j).getPrioridade()) {
////                            facadejpa.getObjetivoDeQuestaoJpa().edit(lista_questao.get(i));
////
////                            registraQuestao(lista_questao.get(i), Constantes.EDICAO);
////                        }
//                    }
//                }
//                JOptionPane.showMessageDialog(null, "Alterações salvas com sucesso.");
//            }
//        } catch (Exception error) {
//            JOptionPane.showMessageDialog(null, "Erro inesperado.");
//            error.printStackTrace();
//        }
//    }

    public List<Objetivodequestao> buscaListaDeQuestoes() {
        try {
            return facadejpa.getObjetivoDeQuestaoJpa().findObjetivodequestaoEntities();
        } catch (Exception error) {
            throw error;
        }
    }

    public void exlcluiQuestao(Objetivodequestao objetivodequestao, int idProjeto) {
        try {
            int resposta = JOptionPane.showConfirmDialog(null, "Excluir Questão?");

            if (resposta == JOptionPane.YES_OPTION) {
                for (int i = 0; i < objetivodequestao.getRegistroobjetivoquestaoList().size(); i++) {
                    facadejpa.getRegistroObjetivoQuestaoJpa().destroy(objetivodequestao.getRegistroobjetivoquestaoList().get(i).getId());
                }
                facadejpa.getObjetivoDeQuestaoJpa().destroy(objetivodequestao.getId());
                JOptionPane.showMessageDialog(null, "Questão excluída com sucesso.");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Erro inesperado.");
            error.printStackTrace();
        }
    }

    public Objetivodemedicao buscaObjetivoDeMedicaoPeloNome(String nome, int idProjeto) {
        try {
            return facadejpa.getObjetivoDeMedicaoJpa().findByNomeAndIdProjeto(nome, idProjeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Objetivodequestao> getQuestoesDoProjeto(int id_projeto) {
        try {
            return facadejpa.getObjetivoDeQuestaoJpa().ListQuestoesByProjeto(id_projeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public Objetivodequestao buscaObjetivoDeQuestaoDoProjeto(String nome_questao, int id_projeto) {
        try {
            return facadejpa.getObjetivoDeQuestaoJpa().findQuestaoByNomeAndIdProjeto(nome_questao, id_projeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public Objetivodequestao buscaUltimaQuestao(String nome_questao) {
        try {
            List<Objetivodequestao> lista = facadejpa.getObjetivoDeQuestaoJpa().findLastQuestao(nome_questao);
            return lista.get(lista.size() - 1);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Objetivodequestao> buscaParteDoNomeQuestao(String nome_questao, int id_projeto) {
        try {
            return facadejpa.getObjetivoDeQuestaoJpa().findParteNomeQuestao(nome_questao, id_projeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public boolean buscaSeNomeQuestaoJaExiste(String nome, int id_projeto) {
        try {
            if (facadejpa.getObjetivoDeQuestaoJpa().findListQuestaoByNomeAndIdProejto(nome, id_projeto).isEmpty()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Nome de questão já existe.");
                return false;
            }
        } catch (Exception error) {
            throw error;
        }
    }

    /**
     * Criar um registro sobre objetivo de medicao
     *
     * @param objetivo Objetivo ao qual serah acrescentado um registro
     * @param tipo tipo de registro. Ex: CADASTRO, EDICAO ...
     */
    public void registrar(Objetivodemedicao objetivo, int tipo) {
        objetivo = facade.FacadeJpa.getInstance().getObjetivoDeMedicaoJpa().findByNomeAndIdProjeto(objetivo.getNome(), objetivo.getProjetoid().getId());

        Registroobjetivomedicao registro = new Registroobjetivomedicao();
        registro.setData(new Date());
        registro.setNomeUsuario(Copia.getUsuarioLogado().getNome());
        registro.setTipo(tipo);
        registro.setObjetivoDeMedicaoid(objetivo);
        try {
            FacadeJpa.getInstance().getRegistroObjetivoMedicaoJpa().create(registro);
            System.out.println("Registro criado");
        } catch (Exception ex) {
            Logger.getLogger(ViewProjeto_ObjetivosDeMedicao_Novo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registraQuestao(Objetivodequestao objetivoQuestao, int tipo) {
        try {
            objetivoQuestao = facadejpa.getObjetivoDeQuestaoJpa().findQuestaoByNomeAndIdProjeto(objetivoQuestao.getNome(), Copia.getProjetoSelecionado().getId());

            Registroobjetivoquestao novoRegistro = new Registroobjetivoquestao();
            novoRegistro.setNomeUsuario(Copia.getUsuarioLogado().getNome());
            novoRegistro.setData(new Date());
            novoRegistro.setObjetivoDeQuestaoid(objetivoQuestao);
            novoRegistro.setTipo(tipo);
//          novoRegistro.setDescricao(null);

            facadejpa.getRegistroObjetivoQuestaoJpa().create(novoRegistro);
            System.out.println("--Registro de questão criado");
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public List<Registroobjetivoquestao> buscaListaQuestaoPeloTipo(int tipo, int idQuestao) {
        try {
            return facadejpa.getRegistroObjetivoQuestaoJpa().findRegistroByTipoAndIdQuestao(tipo, idQuestao);
        } catch (Exception error) {
            throw error;
        }
    }
}
