package controller;

import facade.FacadeJpa;
import java.awt.HeadlessException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import jpa.exceptions.NonexistentEntityException;
import model.Entidademedida;
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

    public Indicador buscarIndicadorPeloNome(String nome, int idProjeto) {
        try {
            return facadeJpa.getIndicadorJpa().findBYNomeAndProjeto(nome, idProjeto);
        } catch (Exception error) {
            throw error;
        }
    }

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

    public boolean editarIndicadorCompleto(Indicador indicador) {
        Indicador indicadorAux = facadeJpa.getIndicadorJpa().findBYNomeAndProjeto(indicador.getNome(), Copia.getProjetoSelecionado().getId());
        if (indicadorAux != null) {
            if (!Objects.equals(indicadorAux.getId(), indicador.getId())) {
                JOptionPane.showMessageDialog(null, "Nome de indicador já existe.");
                return false;
            }
        }
        
        try {
            facadeJpa.getIndicadorJpa().edit(indicador);
            registraIndicador(indicador, Constantes.EDICAO);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            return true;
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Não foi possível editar", "ERRO DE EDIÇÃO", JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
            return false;
        }
    }

    public boolean criarNovoIndicador(Indicador indicador) {
        Indicador indicadorAux = facadeJpa.getIndicadorJpa().findBYNomeAndProjeto(indicador.getNome(), Copia.getProjetoSelecionado().getId());
        if (indicadorAux != null) {
            JOptionPane.showMessageDialog(null, "Nome de indicador já existe.");
            return false;
        }

        try {
            int qtdIndicadores = (int) facadeJpa.getIndicadorJpa().countIndicadoresByProjeto(Copia.getProjetoSelecionado().getId());
            indicador.setPrioridade(qtdIndicadores + 1);
            indicador.setAprovacao("Não analisado");
            facadeJpa.getIndicadorJpa().create(indicador);
            registraIndicador(indicador, Constantes.CADASTRO);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            return true;
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Não foi possível criar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
            return false;
        }
    }

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

    public void registrar(Indicador indicador, int tipoDeRegistro, String observacao) {
        try {
            // Confirmacao que existe um indicador no banco
            indicador = facadeJpa.getIndicadorJpa().findByNomeAndMnemonico(indicador.getNome(), indicador.getMnemonico(), Copia.getProjetoSelecionado().getId());

            Registroindicador novoRegistro = new Registroindicador();
            novoRegistro.setNomeUsuario(Copia.getUsuarioLogado().getNome());
            novoRegistro.setData(new Date());
            novoRegistro.setIndicadorid(indicador);
            novoRegistro.setTipo(tipoDeRegistro);
            novoRegistro.setDescricao(observacao);

            facadeJpa.getRegistroIndicador().create(novoRegistro);
            System.out.println("Registro de indicador criado com sucesso.");
        } catch (Exception error) {
            System.err.println("Não foi possivel fazer o registro do indicador");
            error.printStackTrace();
        }
    }

    public void editarIndicador(Indicador indicador) {
        try {
            FacadeJpa.getInstance().getIndicadorJpa().edit(indicador);
            System.out.println("Indicador editado com sucesso");
        } catch (Exception e) {
            System.err.println("Não foi possivel editar o indicador.");
        }
    }

    public Registroindicador buscarUltimoRegistroDoIndicador(Indicador indicador, int tipo) {
        List<Registroindicador> listaDeRegistro = FacadeJpa.getInstance().getIndicadorJpa().findRegistrosDoIndicadorByTipo(indicador.getId(), tipo);
        System.out.println("Lista = " + listaDeRegistro.size());
        if (listaDeRegistro.isEmpty()) {
            return null;
        } else {
            return listaDeRegistro.get(0);
        }
    }

    public List<Entidademedida> buscarListaEntidadeMedidas(int idProjeto) {
        try {
            return facadeJpa.getEntidadeMedidaJpa().findListaEntidadeMedida(idProjeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Entidademedida> buscaListaEntidadeMedidasCadastradas(int idProjeto) {
        try {
            return facadeJpa.getEntidadeMedidaJpa().findListaEntidadesMedidasCadastradas(idProjeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public boolean criarEntidadeMedida(String nome, int idProjeto) {
        Entidademedida entidademedida = facadeJpa.getEntidadeMedidaJpa().findEntidadeMedidaByNomeAndProjeto(nome, idProjeto);
        if (entidademedida != null) {
            JOptionPane.showMessageDialog(null, "Nome de Entidade Medida já existe.");
            return false;
        }

        try {
            entidademedida = new Entidademedida();
            entidademedida.setNome(nome);
            entidademedida.setProjetoid(Copia.getProjetoSelecionado());
            facadeJpa.getEntidadeMedidaJpa().create(entidademedida);
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso.");
            return true;
        } catch (Exception error) {
            error.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível criar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void excluirEntidadeMedida(String nome, int idProjeto) {
        boolean pode = true;
        Entidademedida entidademedida = facadeJpa.getEntidadeMedidaJpa().findEntidadeMedidaByNomeAndProjeto(nome, idProjeto);
        List<Indicador> listaIndicadores = facadeJpa.getIndicadorJpa().findListaIndicadoresByProjeto(Copia.getProjetoSelecionado().getId());
        for (int i = 0; i < listaIndicadores.size(); i++) {
            if (entidademedida.getNome().equals(listaIndicadores.get(i).getEntidadeDeMedida())) {
                pode = false;
                break;
            }
        }

        if (pode) {
            try {
                facadeJpa.getEntidadeMedidaJpa().destroy(entidademedida.getId());
                JOptionPane.showMessageDialog(null, "Excluido com sucesso.");
            } catch (NonexistentEntityException | HeadlessException error) {
                error.printStackTrace();
                JOptionPane.showMessageDialog(null, "Não foi possível excluir.", "ERRO AO DELETAR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Existem Indicadores relacionados a Essa Entidade medida."
                    + "\nPor favor, elimine essas relações antes de tentar excluir de novo.");
        }
    }
}
