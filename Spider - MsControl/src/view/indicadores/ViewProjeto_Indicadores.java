package view.indicadores;

import controller.CtrlIndicador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Indicador;
import util.Copia;
import util.Internal;
import util.MyDefaultTableModel;

/**
 *
 * @author Géssica, BlenoVale
 */
public class ViewProjeto_Indicadores extends javax.swing.JInternalFrame {

    private List<Indicador> lista_indicadores;
    private DefaultTableModel defaultTableModel;
    private CtrlIndicador ctrlIndicador = new CtrlIndicador();
    private Indicador indicadorSelecionado = new Indicador();

    public ViewProjeto_Indicadores() {
        initComponents();
        
        Internal.retiraBorda(this);
    }

    private void atualizaListaIndicadoresDoProjeto() {
        int idDoProjeto = Copia.getProjetoSelecionado().getId();

        lista_indicadores = new ArrayList<>();
        lista_indicadores = ctrlIndicador.getIndicadoresDoProjeto(idDoProjeto);
    }

    /**
     * Preenche tabela de indicadores a partir do projeto selecionado
     *
     * @param lista
     */
    private void preencherTabelaIndicadores(List<Indicador> lista) {
        String[] colunas = {"Prioridade", "Indicador", "Necessidade de Informação"};
        defaultTableModel = new MyDefaultTableModel(colunas, 0, false);
        for (int i = 0; i < lista.size(); i++) {
            String linha[] = {
                String.valueOf(lista.get(i).getPrioridade()),
                lista.get(i).getNome(),
                lista.get(i).getObjetivoDeQuestaoid().getNome()
            };
            defaultTableModel.addRow(linha);
        }
        jTableIndicadores.setModel(defaultTableModel);
    }

    public void preencherTabelaIndicadoresDoProjeto() {
        atualizaListaIndicadoresDoProjeto();
        preencherTabelaIndicadores(lista_indicadores);
    }

    private void aumentarPrioridade() {
        if (jTableIndicadores.getSelectedRow() > 0) {
            int linhaSelecionada = jTableIndicadores.getSelectedRow();

            lista_indicadores.get(linhaSelecionada).setPrioridade(Integer.parseInt(jTableIndicadores.getValueAt(linhaSelecionada - 1, 0).toString()));
            lista_indicadores.get(linhaSelecionada - 1).setPrioridade(Integer.parseInt(jTableIndicadores.getValueAt(linhaSelecionada, 0).toString()));

            Indicador indicador_Selecionado = lista_indicadores.get(linhaSelecionada);
            lista_indicadores.set(linhaSelecionada, lista_indicadores.get(linhaSelecionada - 1));
            lista_indicadores.set(linhaSelecionada - 1, indicador_Selecionado);

            preencherTabelaIndicadores(lista_indicadores);
            jTableIndicadores.addRowSelectionInterval(linhaSelecionada - 1, linhaSelecionada - 1);
        } else if (jTableIndicadores.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um Indicador na Tabela.");
        }
    }

    private void diminuirPrioridade() {
        if (jTableIndicadores.getSelectedRow() < lista_indicadores.size() - 1 && jTableIndicadores.getSelectedRow() != -1) {
            int linhaSelecionada = jTableIndicadores.getSelectedRow();

            lista_indicadores.get(linhaSelecionada).setPrioridade(Integer.parseInt(jTableIndicadores.getValueAt(linhaSelecionada + 1, 0).toString()));
            lista_indicadores.get(linhaSelecionada + 1).setPrioridade(Integer.parseInt(jTableIndicadores.getValueAt(linhaSelecionada, 0).toString()));

            Indicador indicador_Selecionado = lista_indicadores.get(linhaSelecionada);
            lista_indicadores.set(linhaSelecionada, lista_indicadores.get(linhaSelecionada + 1));
            lista_indicadores.set(linhaSelecionada + 1, indicador_Selecionado);

            preencherTabelaIndicadores(lista_indicadores);
            jTableIndicadores.addRowSelectionInterval(linhaSelecionada + 1, linhaSelecionada + 1);
        } else if (jTableIndicadores.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um Indicador na Tabela.");
        }
    }

    private void ConfirmarAuteraçãoDePrioridade() {
        List<Indicador> listaAntiga = ctrlIndicador.getIndicadoresDoProjeto(Copia.getProjetoSelecionado().getId());

        if ((!lista_indicadores.equals(listaAntiga)) && lista_indicadores.size() >= listaAntiga.size()) {
            ctrlIndicador.editarPrioridadeDaListaDeIndicadores(lista_indicadores, Copia.getProjetoSelecionado().getId());
        } else {
            JOptionPane.showMessageDialog(null, "Não há modificações de Prioridades.");
        }
    }

    private void editarIndicador() {
        ViewProjeto_NovoIndicador viewProjeto_NovoIndicador = new ViewProjeto_NovoIndicador(null, true);
        pegarIndicadorSelecionado();
        viewProjeto_NovoIndicador.showEditarIndicadorDialog(indicadorSelecionado);
    }
    
    private void pegarIndicadorSelecionado(){
        int idDoProjeto = Copia.getProjetoSelecionado().getId();
        indicadorSelecionado = ctrlIndicador.buscarIndicadorPeloNome(jTableIndicadores.getValueAt(jTableIndicadores.getSelectedRow(), 1).toString(), idDoProjeto);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableIndicadores = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();

        setTitle("Definição de Indicadores");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Buscar Indicador:");

        jTextFieldBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarActionPerformed(evt);
            }
        });

        jTableIndicadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prioridade", "Indicador", "Necessidade de Informação"
            }
        ));
        jScrollPane1.setViewportView(jTableIndicadores);

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Novo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Prioridade"));

        jButton4.setText("Aumentar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Diminuir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Resetar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Confirmar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh3.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ViewProjeto_NovoIndicador viewProjeto_NovoIndicador = new ViewProjeto_NovoIndicador(null, true);
        viewProjeto_NovoIndicador.showNovoIndicadorDialog();
        preencherTabelaIndicadoresDoProjeto();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextFieldBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarActionPerformed
        lista_indicadores = new ArrayList<>();
        lista_indicadores = ctrlIndicador.buscarParteDoNomeIndicador(jTextFieldBuscar.getText(), Copia.getProjetoSelecionado().getId());
        preencherTabelaIndicadores(lista_indicadores);
    }//GEN-LAST:event_jTextFieldBuscarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        aumentarPrioridade();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        diminuirPrioridade();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        preencherTabelaIndicadoresDoProjeto();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ConfirmarAuteraçãoDePrioridade();
        preencherTabelaIndicadoresDoProjeto();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jTableIndicadores.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um Indicador na Tabela.");
        } else {
            editarIndicador();
            preencherTabelaIndicadoresDoProjeto();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        preencherTabelaIndicadoresDoProjeto();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableIndicadores;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
