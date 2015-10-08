package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import model.Funcionalidade;

/**
 *
 * @author Bleno Vale
 */
public class ArvoreDinamica {

    private DefaultMutableTreeNode projeto;
    private String nomeDoNode;
    private List<String> listaNodes;

    public DefaultMutableTreeNode criaArvore(String nomeProjeto, List<Funcionalidade> listaFuncionalidades) {
        projeto = new DefaultMutableTreeNode(nomeProjeto);
        listaNodes = new ArrayList<>();
        for (int i = 0; i < listaFuncionalidades.size(); i++) {
            quebraString(listaFuncionalidades.get(i).getNome());
        }

        checaRepetidoLista();
        checaRepetidoLista();

        for (int i = 0; i < listaNodes.size(); i++) {
            nomeDoNode = listaNodes.get(i);
            if (listaNodes.get(i).equals("Objetivos")) {
                projeto.add(criaSubArvore(listaFuncionalidades));
            } else if (listaNodes.get(i).equals("Indicadores")) {
                projeto.add(criaSubArvore(listaFuncionalidades));
            } else if (listaNodes.get(i).equals("Medidas")) {
                projeto.add(criaSubArvore(listaFuncionalidades));
            } else if (listaNodes.get(i).equals("Procedimentos")) {
                projeto.add(criaSubArvore(listaFuncionalidades));
            } else if (listaNodes.get(i).equals("Artefatos")) {
                projeto.add(criaSubArvore(listaFuncionalidades));
            } else if (listaNodes.get(i).equals("Resultados")) {
                projeto.add(new DefaultMutableTreeNode(nomeDoNode));
            }
        }
        return projeto;
    }

    public DefaultMutableTreeNode criaSubArvore(List<Funcionalidade> lista) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(nomeDoNode);
        for (int k = 0; k < lista.size(); k++) {
            if (lista.get(k).getNome().equals("Objetivos - Objetivo da Medição") && nomeDoNode.equals("Objetivos")) {
                node.add(new DefaultMutableTreeNode("Objetivo da Medição"));

            } else if (lista.get(k).getNome().equals("Objetivos - Necessidade de Informações") && nomeDoNode.equals("Objetivos")) {
                node.add(new DefaultMutableTreeNode("Necessidade de Informações"));

            } else if (lista.get(k).getNome().equals("Indicadores - Definição") && nomeDoNode.equals("Indicadores")) {
                node.add(new DefaultMutableTreeNode("Definição"));

            } else if (lista.get(k).getNome().equals("Indicadores - Aprovação") && nomeDoNode.equals("Indicadores")) {
                node.add(new DefaultMutableTreeNode("Aprovação"));

            } else if (lista.get(k).getNome().equals("Indicadores - Valor") && nomeDoNode.equals("Indicadores")) {
                node.add(new DefaultMutableTreeNode("Valor"));

            } else if (lista.get(k).getNome().equals("Indicadores - Análise") && nomeDoNode.equals("Indicadores")) {
                node.add(new DefaultMutableTreeNode("Análise"));

            } else if (lista.get(k).getNome().equals("Medidas - Definição") && nomeDoNode.equals("Medidas")) {
                node.add(new DefaultMutableTreeNode("Definição"));

            } else if (lista.get(k).getNome().equals("Medidas - Coleta") && nomeDoNode.equals("Medidas")) {
                node.add(new DefaultMutableTreeNode("Coleta"));

            } else if (lista.get(k).getNome().equals("Procedimentos - Coleta") && nomeDoNode.equals("Procedimentos")) {
                node.add(new DefaultMutableTreeNode("Coleta"));

            } else if (lista.get(k).getNome().equals("Procedimentos - Análise") && nomeDoNode.equals("Procedimentos")) {
                node.add(new DefaultMutableTreeNode("Análise"));

            } else if (lista.get(k).getNome().equals("Artefatos - Plano de Medição") && nomeDoNode.equals("Artefatos")) {
                node.add(new DefaultMutableTreeNode("Plano de Medição"));

            } else if (lista.get(k).getNome().equals("Artefatos - Relatório") && nomeDoNode.equals("Artefatos")) {
                node.add(new DefaultMutableTreeNode("Relatório"));
            }
        }

        return node;
    }

    public void quebraString(String palavra) {
        String[] array = palavra.split(" ");
        listaNodes.add(array[0]);
    }

    private void checaRepetidoLista() {
        for (int i = 0; i < listaNodes.size(); i++) {
            for (int j = i + 1; j < listaNodes.size(); j++) {
                if (listaNodes.get(i).equals(listaNodes.get(j))) {
                    listaNodes.remove(j);
                }
            }
        }
    }
}
