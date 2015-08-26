package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import model.ParticipanteseInteressados;
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

    public void cadastraResultado(Resultados resultado, List<ParticipanteseInteressados> lista) {
        Resultados resultadoAux = buscarResultadoPeloTitulo(resultado.getTitulo(), Copia.getProjetoSelecionado().getId());
        if (resultadoAux != null) {
            JOptionPane.showMessageDialog(null, "Nome de Resultado já existe.");
            return;
        }

        try {
            facadeJpa.getResultadosJpa().create(resultado);
            resultado = buscarResultadoPeloTitulo(resultado.getTitulo(), Copia.getProjetoSelecionado().getId());
            for (int i = 0; i < lista.size(); i++) {
                lista.get(i).setResultadosid(resultado);
                facadeJpa.getParticipanteseInteressadosJpa().create(lista.get(i));
            }
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Erro inesperado.");
        }
    }

}
