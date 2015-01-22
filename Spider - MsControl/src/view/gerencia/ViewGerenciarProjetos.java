package view.gerencia;

import controller.CtrlProjeto;
import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import model.Projeto;
import util.Constantes;
import util.Internal;
import util.MyDefaultTableModel;
import util.Texto;
import static util.Texto.formataData;

/**
 *
 * @author DAN JHONATAN
 */
public class ViewGerenciarProjetos extends javax.swing.JInternalFrame {

    private final FacadeJpa jpa = FacadeJpa.getInstance();
    private final CtrlProjeto ctrlProjeto = new CtrlProjeto();

    public ViewGerenciarProjetos() {
        initComponents();

        atualizaTodasTabelas();

        Internal.retiraBotao(this);
    }

    private void atualizaTabelaAtivos() {
        String colunas[] = {"Nome do projeto", "Data de início"};

        List<Projeto> projetoList = jpa.getProjetoJpa().findTodosProjetosAtivosOrderByNome();

        MyDefaultTableModel model = new MyDefaultTableModel(colunas, projetoList.size(), false);
        jTableAtivos.setModel(model);

        for (int i = 0; i < projetoList.size(); i++) {
            jTableAtivos.setValueAt(projetoList.get(i).getNome(), i, 0);
            jTableAtivos.setValueAt(formataData(projetoList.get(i).getDataInicio()), i, 1);
        }
    }

    private void atualizaTabelaInativos() {
        String colunas[] = {"Nome do projeto", "Data de início", "Data de inatividade"};
        List<Projeto> projetoList = jpa.getProjetoJpa().findTodosProjetosInativos();

        MyDefaultTableModel model = new MyDefaultTableModel(colunas, projetoList.size(), false);
        jTableInativos.setModel(model);

        for (int i = 0; i < projetoList.size(); i++) {
            jTableInativos.setValueAt(projetoList.get(i).getNome(), i, 0);
            jTableInativos.setValueAt(formataData(projetoList.get(i).getDataInicio()), i, 1);
            jTableInativos.setValueAt(formataData(projetoList.get(i).getDataInatividade()), i, 2);
        }
    }

    private void atualizaTabelaFinalizados() {
        String colunas[] = {"Nome do projeto", "Data de início", "Data de conclusão"};
        List<Projeto> projetoList = jpa.getProjetoJpa().findTodosProjetosFinalizados();

        MyDefaultTableModel model = new MyDefaultTableModel(colunas, projetoList.size(), false);
        jTableFinalizados.setModel(model);

        for (int i = 0; i < projetoList.size(); i++) {
            jTableFinalizados.setValueAt(projetoList.get(i).getNome(), i, 0);
            jTableFinalizados.setValueAt(Texto.formataData(projetoList.get(i).getDataInicio()), i, 1);
            jTableFinalizados.setValueAt(Texto.formataData(projetoList.get(i).getDataFim()), i, 2);
        }
    }

    private void editarProjeto() {
        if (jTableAtivos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um projeto na tabela");
            return;
        }
        Projeto projeto = buscaProjetoSelecionado(jTableAtivos);
        ViewDadosDoProjeto viewNovoProjeto = new ViewDadosDoProjeto(null, true);
        viewNovoProjeto.showEditarProjetoDialog(projeto);

        atualizaTodasTabelas();
    }

    private Projeto buscaProjetoSelecionado(JTable table) {
        String nomeDoProjeto = table.getValueAt(table.getSelectedRow(), 0).toString();
        Projeto projeto = jpa.getProjetoJpa().findByNome(nomeDoProjeto);
        return projeto;
    }

    private void mudarStatusProjeto(JTable table) {
        String nomeDoProjeto = table.getValueAt(table.getSelectedRow(), 0).toString();
        Projeto projetoAtual = jpa.getProjetoJpa().findByNome(nomeDoProjeto);

        ViewStatusProjetoDialog statusProjetoDialog = new ViewStatusProjetoDialog(null, true);
        int status = statusProjetoDialog.showMudaStatusDialog(projetoAtual.getStatus());

        // Se houver diferenca entre status, entao edite
        if (projetoAtual.getStatus() != status) {
            projetoAtual.setStatus(status);

            if (status == Constantes.INATIVO)
                projetoAtual.setDataInatividade(new Date());
            if (status == Constantes.FINALIZADO)
                projetoAtual.setDataFim(new Date());

            ctrlProjeto.editarProjeto(projetoAtual);

            atualizaTodasTabelas();
        }
    }

    private void atualizaTodasTabelas() {
        atualizaTabelaAtivos();
        atualizaTabelaInativos();
        atualizaTabelaFinalizados();
    }

    private void reativarProjeto() {
        if (jTableInativos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um projeto na tabela");
            return;
        }

        String nomeDoProjeto = jTableInativos.getValueAt(jTableInativos.getSelectedRow(), 0).toString();
        ctrlProjeto.mudarStatusDoProjeto(nomeDoProjeto, Constantes.ATIVO);

        atualizaTabelaAtivos();
        atualizaTabelaInativos();
    }

    private void mostrarInformacoesDoProjeto(JTable table) {
        Projeto projeto = buscaProjetoSelecionado(table);
        ViewDetalhesProjetoDialog.showDetalhesDialog(projeto);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuProjetos = new javax.swing.JPopupMenu();
        jMenuItemVerInfo = new javax.swing.JMenuItem();
        jMenuItemEditarProjeto = new javax.swing.JMenuItem();
        jMenuItemMudarStatus = new javax.swing.JMenuItem();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAtivos = new javax.swing.JTable();
        jButtonEditarProjeto = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButtonInativar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableInativos = new javax.swing.JTable();
        jButtonReativarProjeto = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFinalizados = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jMenuItemVerInfo.setText("Ver informações");
        jMenuItemVerInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVerInfoActionPerformed(evt);
            }
        });
        jPopupMenuProjetos.add(jMenuItemVerInfo);

        jMenuItemEditarProjeto.setText("Editar projeto");
        jMenuItemEditarProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditarProjetoActionPerformed(evt);
            }
        });
        jPopupMenuProjetos.add(jMenuItemEditarProjeto);

        jMenuItemMudarStatus.setText("Mudar status");
        jMenuItemMudarStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMudarStatusActionPerformed(evt);
            }
        });
        jPopupMenuProjetos.add(jMenuItemMudarStatus);

        setTitle("Gerenciar projetos");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableAtivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome do projeto", "Data de início"
            }
        ));
        jTableAtivos.setToolTipText("");
        jTableAtivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAtivosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableAtivosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAtivos);

        jButtonEditarProjeto.setText("Editar projeto");
        jButtonEditarProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarProjetoActionPerformed(evt);
            }
        });

        jButton2.setText("Gerar artefato");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setText("Alocar usuário ao projeto");

        jButtonInativar.setText("Mudar status");
        jButtonInativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInativarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonEditarProjeto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonInativar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEditarProjeto)
                    .addComponent(jButton2)
                    .addComponent(jButton6)
                    .addComponent(jButtonInativar))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Projetos ativos", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableInativos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome do projeto", "Data de início"
            }
        ));
        jTableInativos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableInativosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableInativos);

        jButtonReativarProjeto.setText("reativar");
        jButtonReativarProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReativarProjetoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButtonReativarProjeto)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonReativarProjeto)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Projetos inativos", jPanel4);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableFinalizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome do Projeto", "Data de início", "Data de conclusão"
            }
        ));
        jTableFinalizados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFinalizadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableFinalizados);

        jButton4.setText("Gerar relatório");

        jButton5.setText("Gerar plano de medição");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Projetos finalizados", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEditarProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarProjetoActionPerformed
        editarProjeto();
    }//GEN-LAST:event_jButtonEditarProjetoActionPerformed

    private void jButtonInativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInativarActionPerformed
        if (jTableAtivos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um projeto na tabela");
            return;
        }

        mudarStatusProjeto(jTableAtivos);
    }//GEN-LAST:event_jButtonInativarActionPerformed

    private void jButtonReativarProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReativarProjetoActionPerformed
        if (jTableInativos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um projeto na tabela");
            return;
        }
        reativarProjeto();
        //mudarStatusProjeto(jTableInativos);
    }//GEN-LAST:event_jButtonReativarProjetoActionPerformed

    private void jTableAtivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAtivosMouseClicked
        if (evt.getClickCount() > 1)
            mostrarInformacoesDoProjeto(jTableAtivos);
    }//GEN-LAST:event_jTableAtivosMouseClicked

    private void jTableAtivosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAtivosMousePressed
        if (jTableAtivos.getSelectedRow() != -1)
            if (SwingUtilities.isRightMouseButton(evt)) {
                jPopupMenuProjetos.show(evt.getComponent(), evt.getX(), evt.getY());
            }
    }//GEN-LAST:event_jTableAtivosMousePressed

    private void jMenuItemEditarProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditarProjetoActionPerformed
        editarProjeto();
    }//GEN-LAST:event_jMenuItemEditarProjetoActionPerformed

    private void jMenuItemVerInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVerInfoActionPerformed
        mostrarInformacoesDoProjeto(jTableAtivos);
    }//GEN-LAST:event_jMenuItemVerInfoActionPerformed

    private void jMenuItemMudarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMudarStatusActionPerformed
        mudarStatusProjeto(jTableAtivos);
    }//GEN-LAST:event_jMenuItemMudarStatusActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ViewGerarArtefatoDialog gerarArtefatoDialog = new ViewGerarArtefatoDialog(null, true);
        int resp = gerarArtefatoDialog.showSelecionarArtefatoDialog();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTableInativosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableInativosMouseClicked
        if (evt.getClickCount() > 1)
            mostrarInformacoesDoProjeto(jTableInativos);
    }//GEN-LAST:event_jTableInativosMouseClicked

    private void jTableFinalizadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFinalizadosMouseClicked
        if (evt.getClickCount() > 1)
            mostrarInformacoesDoProjeto(jTableFinalizados);
    }//GEN-LAST:event_jTableFinalizadosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonEditarProjeto;
    private javax.swing.JButton jButtonInativar;
    private javax.swing.JButton jButtonReativarProjeto;
    private javax.swing.JMenuItem jMenuItemEditarProjeto;
    private javax.swing.JMenuItem jMenuItemMudarStatus;
    private javax.swing.JMenuItem jMenuItemVerInfo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenuProjetos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableAtivos;
    private javax.swing.JTable jTableFinalizados;
    private javax.swing.JTable jTableInativos;
    // End of variables declaration//GEN-END:variables
}
