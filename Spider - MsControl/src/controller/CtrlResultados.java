package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import model.Registroresultados;
import model.Resultados;
import util.Constantes;
import util.Copia;

/**
 *
 * @author Géssica
 */
public class CtrlResultados {
    
    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public List<Resultados> getResultadosDoProjeto(int idDoProjeto) {
        try {
            return facadeJpa.getResultadosJpa().findListaResultadosByProjeto(idDoProjeto);
        } catch (Exception error) {
            throw error;
        }
    }

    public Resultados buscarResultadoPeloTitulo(String titulo, int idProjeto) {
        try {
            return facadeJpa.getResultadosJpa().findBYTituloAndProjeto(titulo, idProjeto);
        } catch (Exception error) {
            throw error;
        }
    }
    
     public List<Resultados> buscarParteDoTituloResultado(String titulo, int id_projeto) {
        try {
            return facadeJpa.getResultadosJpa().findResultadoByParteTitulo(titulo, id_projeto);
        } catch (Exception error) {
            throw error;
        }
    }
    
    public boolean editarResultadoCompleto(Resultados resultados) {
        Resultados resultadosAux = facadeJpa.getResultadosJpa().findBYTituloAndProjeto(resultados.getTitulo(), Copia.getProjetoSelecionado().getId());
        if (resultadosAux != null) {
            if (!Objects.equals(resultadosAux.getId(), resultados.getId())) {
                JOptionPane.showMessageDialog(null, "Título de resultado já existe.");
                return false;
            }
        }
    
        try {
            facadeJpa.getResultadosJpa().edit(resultados);
            registraResultados(resultados, Constantes.EDICAO);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            return true;
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Não foi possível editar", "ERRO DE EDIÇÃO", JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
            return false;
        }
    }

    
    public boolean criarNovoResultado(Resultados resultados) {
        Resultados resultadosAux = facadeJpa.getResultadosJpa().findBYTituloAndProjeto(resultados.getTitulo(), Copia.getProjetoSelecionado().getId());
        if (resultadosAux != null) {
            JOptionPane.showMessageDialog(null, "Título de resultado já existe.");
            return false;
        }

        try {
            int qtdResultados = (int) facadeJpa.getResultadosJpa().countResultadosByProjeto(Copia.getProjetoSelecionado().getId());
            facadeJpa.getResultadosJpa().create(resultados);
            registraResultados(resultados, Constantes.CADASTRO);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            return true;
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Não foi possível criar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
            return false;
        }
    }
    
    private void registraResultados(Resultados resultados, int tipo) {
        try {
            resultados = facadeJpa.getResultadosJpa().findResultados(resultados.getId());

            Registroresultados novoRegistro = new Registroresultados();
            novoRegistro.setNomeUsuario(Copia.getUsuarioLogado().getNome());
            novoRegistro.setData(new Date());
            novoRegistro.setResultadosid(resultados);
            novoRegistro.setTipo(tipo);

            facadeJpa.getRegistroresultados().create(novoRegistro);
            System.out.println("--Registro de resultado criado.");
        } catch (Exception error) {
        }
    }
    
    public void registrar(Resultados resultados, int tipoDeRegistro) {
        try {
            // Confirmacao que existe um resultado no banco
            resultados = facadeJpa.getResultadosJpa().findByTitulo(resultados.getTitulo(), Copia.getProjetoSelecionado().getId());

            Registroresultados novoRegistro = new Registroresultados();
            novoRegistro.setNomeUsuario(Copia.getUsuarioLogado().getNome());
            novoRegistro.setData(new Date());
            novoRegistro.setResultadosid(resultados);
            novoRegistro.setTipo(tipoDeRegistro);

            facadeJpa.getRegistroresultados().create(novoRegistro);
            System.out.println("Registro de resultado criado com sucesso.");
        } catch (Exception error) {
            System.err.println("Não foi possivel fazer o registro do resultado");
            error.printStackTrace();
        }
    }
    
    public void editarResultados(Resultados resultados) {
        try {
            FacadeJpa.getInstance().getResultadosJpa().edit(resultados);
            System.out.println("Resultado editado com sucesso");
        } catch (Exception e) {
            System.err.println("Não foi possivel editar o Resultado.");
        }
    }
    
    public Registroresultados buscarUltimoRegistroDoResultado(Resultados resultados, int tipo) {
        List<Registroresultados> listaDeRegistro = FacadeJpa.getInstance().getResultadosJpa().findRegistrosDoResultadoByTipo(resultados.getId(), tipo);
        System.out.println("Lista = " + listaDeRegistro.size());
        if (listaDeRegistro.isEmpty()) {
            return null;
        } else {
            return listaDeRegistro.get(0);
        }
    }
    
    
}
