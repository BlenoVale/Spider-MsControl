package view.objetivos;

import controller.CtrlObjetivos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Objetivodequestao;
import util.Copia;
import util.Internal;
import util.MyDefaultTableModel;

/**
 *
 * @author BlenoVale
 */
public class ViewProjeto_ObjetivosQuestoes extends javax.swing.JInternalFrame {

    private final CtrlObjetivos ctrlObjetivos = new CtrlObjetivos();
    private Objetivodequestao objetivodequestao_selecionado = new Objetivodequestao();

    private MyDefaultTableModel tableModel;
    private List<Objetivodequestao> lista_questoes;

    public ViewProjeto_ObjetivosQuestoes() {
        initComponents();
        Internal.retiraBotao(this);
    }

    public void atualizaListaQuestoesDoProjeto() {
        int idDoProjeto = Copia.getProjetoSelecionado().getId();

        lista_questoes = new ArrayList<>();
        lista_questoes = ctrlObjetivos.getQuestoesDoProjeto(idDoProjeto);
    }

    public void preencherTabelaQuestoes() {

        atualizaListaQuestoesDoProjeto();

        String[] colunas = {"Prioridade", "Objetivo de Medição", "Necessidade de informação", "Indicador"};
        tableModel = new MyDefaultTableModel(colunas, 0, false);
        for (int i = 0; i < lista_questoes.size(); i++) {
            String linha[] = {
                String.valueOf(lista_questoes.get(i).getPrioridade()),
                lista_questoes.get(i).getObjetivodemedicao().getNome(),
                lista_questoes.get(i).getNome(),
                lista_questoes.get(i).getIndicador()
            };
            tableModel.addRow(linha);
        }
        jTable.setModel(tableModel);
    }

    public void preencheTabelaQuestoesPorParteDoNome(List<Objetivodequestao> questoes) {
        String[] colunas = {"Prioridade", "Objetivo de Medição", "Necessidade de informação", "Indicador"};
        tableModel = new MyDefaultTableModel(colunas, 0, false);

        for (int i = 0; i < questoes.size(); i++) {
            String linha[] = {
                String.valueOf(questoes.get(i).getPrioridade()),
                questoes.get(i).getObjetivodemedicao().getNome(),
                questoes.get(i).getNome(),
                questoes.get(i).getIndicador()
            };
            tableModel.addRow(linha);
        }
        jTable.setModel(tableModel);
    }

    private void pegaQuestaoSelecionada() {
        int idDoProjeto = Copia.getProjetoSelecionado().getId();
        objetivodequestao_selecionado = ctrlObjetivos.buscaObjetivoDeQuestaoDoProjeto(jTable.getValueAt(jTable.getSelectedRow(), 2).toString(), idDoProjeto);
    }

    private void editaQuestão() {
        String nomeUsuario_logado = Copia.getUsuarioLogado().getNome();

        ViewProjeto_ObjetivosQuestoes_Novo viewProjeto_ObjetivosQuestao_Novo = new ViewProjeto_ObjetivosQuestoes_Novo(null, true);

        pegaQuestaoSelecionada();
        viewProjeto_ObjetivosQuestao_Novo.showEditarQuestaoDialog(objetivodequestao_selecionado, nomeUsuario_logado);
    }

    private void aumentarPrioridade() {
        if (jTable.getSelectedRow() > 0) {
            int linha_selecionada = jTable.getSelectedRow();
            Objetivodequestao questao_selecionada = lista_questoes.get(linha_selecionada);

            lista_questoes.get(linha_selecionada).setPrioridade(Integer.parseInt(jTable.getValueAt(linha_selecionada - 1, 0).toString()));
            lista_questoes.get(linha_selecionada - 1).setPrioridade(Integer.parseInt(jTable.getValueAt(linha_selecionada, 0).toString()));

            lista_questoes.set(linha_selecionada, lista_questoes.get(linha_selecionada - 1));
            lista_questoes.set(linha_selecionada - 1, questao_selecionada);

            preencheTabelaQuestoesPorParteDoNome(lista_questoes);
            jTable.addRowSelectionInterval(linha_selecionada - 1, linha_selecionada - 1);
        } else if (jTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma Necessidade de informação na Tabela.");
        }
    }

    private void diminuirPrioridade() {
        if (jTable.getSelectedRow() < lista_questoes.size() - 1 && jTable.getSelectedRow() != -1) {
            int linha_selecionada = jTable.getSelectedRow();
            Objetivodequestao questao_selecionada = lista_questoes.get(linha_selecionada);

            lista_questoes.get(linha_selecionada).setPrioridade(Integer.parseInt(jTable.getValueAt(linha_selecionada + 1, 0).toString()));
            lista_questoes.get(linha_selecionada + 1).setPrioridade(Integer.parseInt(jTable.getValueAt(linha_selecionada, 0).toString()));

            lista_questoes.set(linha_selecionada, lista_questoes.get(linha_selecionada + 1));
            lista_questoes.set(linha_selecionada + 1, questao_selecionada);

            preencheTabelaQuestoesPorParteDoNome(lista_questoes);
            jTable.addRowSelectionInterval(linha_selecionada + 1, linha_selecionada + 1);
        } else if (jTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma Necessidade de informação na Tabela.");
        }
    }

    private void excluirQuestao() {
        pegaQuestaoSelecionada();
        ctrlObjetivos.exlcluiQuestao(objetivodequestao_selecionado, Copia.getProjetoSelecionado().getId());
        preencherTabelaQuestoes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButtonExcluir = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonNovoObjetivo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButtonAumentar = new javax.swing.JButton();
        jButtonDiminuir = new javax.swing.JButton();
        jButtonResetar = new javax.swing.JButton();
        jButtonConfirmar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setTitle("Necessidade de informações");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Buscar Necessidade de informação:");

        jTextFieldBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarActionPerformed(evt);
            }
        });

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Questões", "Indicador"
            }
        ));
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        jButtonExcluir.setText("Excluir ?");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonNovoObjetivo.setText("Novo");
        jButtonNovoObjetivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoObjetivoActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Prioridade"));

        jButtonAumentar.setText("Aumentar");
        jButtonAumentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAumentarActionPerformed(evt);
            }
        });

        jButtonDiminuir.setText("Diminuir");
        jButtonDiminuir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDiminuirActionPerformed(evt);
            }
        });

        jButtonResetar.setText("Resetar");
        jButtonResetar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetarActionPerformed(evt);
            }
        });

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAumentar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDiminuir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonResetar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAumentar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDiminuir)
                .addGap(27, 27, 27)
                .addComponent(jButtonResetar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonConfirmar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonNovoObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonExcluir)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonEditar)
                    .addComponent(jButtonNovoObjetivo))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        if (jTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma Necessidade de informação na Tabela.");
        } else {
            editaQuestão();
            preencherTabelaQuestoes();
        }
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonNovoObjetivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoObjetivoActionPerformed
        String nomeUsuario_logado = Copia.getUsuarioLogado().getNome();

        ViewProjeto_ObjetivosQuestoes_Novo novoObjetivoQuestao = new ViewProjeto_ObjetivosQuestoes_Novo(null, true);
        novoObjetivoQuestao.showNovaQuestaoDialog(Copia.getProjetoSelecionado(), nomeUsuario_logado);
        preencherTabelaQuestoes();
    }//GEN-LAST:event_jButtonNovoObjetivoActionPerformed

    private void jTextFieldBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarActionPerformed
        lista_questoes = ctrlObjetivos.buscaParteDoNomeQuestao(jTextFieldBuscar.getText(), Copia.getProjetoSelecionado().getId());
        preencheTabelaQuestoesPorParteDoNome(lista_questoes);
    }//GEN-LAST:event_jTextFieldBuscarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        if (jTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma Necessidade de informação na Tabela.");
        } else {
            excluirQuestao();
            preencherTabelaQuestoes();
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonAumentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAumentarActionPerformed
        aumentarPrioridade();
    }//GEN-LAST:event_jButtonAumentarActionPerformed

    private void jButtonDiminuirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDiminuirActionPerformed
        diminuirPrioridade();
    }//GEN-LAST:event_jButtonDiminuirActionPerformed

    private void jButtonResetarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetarActionPerformed
        preencherTabelaQuestoes();
    }//GEN-LAST:event_jButtonResetarActionPerformed

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        int idDoProjeto = Copia.getProjetoSelecionado().getId();
        List<Objetivodequestao> lista_antiga = ctrlObjetivos.getQuestoesDoProjeto(idDoProjeto);
        if ((!lista_questoes.equals(lista_antiga)) && lista_questoes.size() >= lista_antiga.size()) {
            ctrlObjetivos.editarPrioridadeDaListaDeQuestoes(lista_questoes, Copia.getProjetoSelecionado().getId());
            preencherTabelaQuestoes();
        } else {
            JOptionPane.showMessageDialog(null, "Não há modificações de Prioridades.");
        }
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        if (evt.getClickCount() >= 2) {
            editaQuestão();
            preencherTabelaQuestoes();
        }
    }//GEN-LAST:event_jTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAumentar;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonDiminuir;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovoObjetivo;
    private javax.swing.JButton jButtonResetar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
