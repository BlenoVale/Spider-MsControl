package view;

import facade.FacadeJpa;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jpa.exceptions.NonexistentEntityException;
import model.Projeto;
import util.MyDefaultTableModel;

public class ViewGerenciarProjetos extends javax.swing.JInternalFrame {

    private FacadeJpa jpa = FacadeJpa.getInstance();

    public ViewGerenciarProjetos() {
        initComponents();

        atualizaTabelaAtivos();
        atualizaTabelaInativos();
        atualizaTabelaFinalizados();
    }

    private void atualizaTabelaAtivos() {
        String colunas[] = {"Nome do projeto", "Data de início"};
        List<Projeto> projetoList = jpa.getProjetoJpa().findTodosProjetosAtivos();

        MyDefaultTableModel model = new MyDefaultTableModel(colunas, projetoList.size(), false);
        jTableAtivos.setModel(model);

        for (int i = 0; i < projetoList.size(); i++) {
            jTableAtivos.setValueAt(projetoList.get(i).getNome(), i, 0);
            jTableAtivos.setValueAt(formataData(projetoList.get(i).getDataInicio()), i, 1);
        }
    }

    private void atualizaTabelaInativos() {
        String colunas[] = {"Nome do projeto", "Data de início"};
        List<Projeto> projetoList = jpa.getProjetoJpa().findTodosProjetosInativos();

        MyDefaultTableModel model = new MyDefaultTableModel(colunas, projetoList.size(), false);
        jTableInativos.setModel(model);

        for (int i = 0; i < projetoList.size(); i++) {
            jTableInativos.setValueAt(projetoList.get(i).getNome(), i, 0);
            jTableInativos.setValueAt(formataData(projetoList.get(i).getDataInicio()), i, 1);
        }
    }

    private void atualizaTabelaFinalizados() {
        String colunas[] = {"Nome do projeto", "Data de início", "Data de conclusão"};
        List<Projeto> projetoList = jpa.getProjetoJpa().findTodosProjetosFinalizados();

        MyDefaultTableModel model = new MyDefaultTableModel(colunas, projetoList.size(), false);
        jTableFinalizados.setModel(model);

        for (int i = 0; i < projetoList.size(); i++) {
            jTableFinalizados.setValueAt(projetoList.get(i).getNome(), i, 0);
            jTableFinalizados.setValueAt(formataData(projetoList.get(i).getDataInicio()), i, 1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAtivos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableInativos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFinalizados = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

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
        jScrollPane1.setViewportView(jTableAtivos);

        jButton1.setText("Alterar nome do projeto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Gerar relatório");

        jButton3.setText("Gerar plano de medição");

        jButton6.setText("Alocar usuário ao projeto");

        jButton7.setText("Excluir");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
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
        jScrollPane3.setViewportView(jTableInativos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addGap(40, 40, 40))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nomeDoProjeto = jTableAtivos.getValueAt(jTableAtivos.getSelectedRow(), 0).toString();
        Projeto projeto = jpa.getProjetoJpa().findByNome(nomeDoProjeto);

        nomeDoProjeto = JOptionPane.showInputDialog("Digite o novo nome do projeto");
        if (nomeDoProjeto.equals(""))
            JOptionPane.showMessageDialog(rootPane, "Digite um nome para o projeto");
        else {
            projeto.setNome(nomeDoProjeto);

            try {
                jpa.getProjetoJpa().edit(projeto);
                atualizaTabelaAtivos();
            } catch (NonexistentEntityException ex) {
                JOptionPane.showMessageDialog(rootPane, "Não foi possível salvar", "ERRO", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Não foi possível salvar", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableAtivos;
    private javax.swing.JTable jTableFinalizados;
    private javax.swing.JTable jTableInativos;
    // End of variables declaration//GEN-END:variables

    public String formataData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("E dd / MM / yyyy");
        return sdf.format(data);
    }
}
