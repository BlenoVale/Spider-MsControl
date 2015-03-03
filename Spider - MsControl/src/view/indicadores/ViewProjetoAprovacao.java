package view.indicadores;

import controller.CtrlIndicador;
import java.util.List;
import javax.swing.JOptionPane;
import model.Indicador;
import util.Constantes;
import util.Copia;
import util.Internal;
import util.MyDefaultTableModel;

/**
 *
 * @author DAN JHONATAN
 */
public class ViewProjetoAprovacao extends javax.swing.JInternalFrame {

    private final CtrlIndicador ctrlIndicador = new CtrlIndicador();
    private MyDefaultTableModel modelIndicadores;

    public ViewProjetoAprovacao() {
        initComponents();

        iniciarTabela();

        Internal.retiraBotao(this);
    }

    private void pesquisarIndicadorEPreencherTabela(String nome) {

        iniciarTabela();

        List<Indicador> indicadorList = ctrlIndicador.findByParteNome(nome, Copia.getProjetoSelecionado().getId());
        System.out.println(indicadorList.size());

        if (jCheckBoxAprovado.isSelected() || jCheckBoxNaoAnalisado.isSelected() || jCheckBoxNaoAprovado.isSelected()) {
            if (!jCheckBoxAprovado.isSelected())
                retirarDaLista(indicadorList, Constantes.APROVADO);
            if (!jCheckBoxNaoAprovado.isSelected())
                retirarDaLista(indicadorList, Constantes.NAO_APROVADO);
            if (!jCheckBoxNaoAnalisado.isSelected())
                retirarDaLista(indicadorList, Constantes.NAO_ANALISADO);
        }

        preencherTabela(indicadorList);
    }

    private void iniciarTabela() {
        String nomeColunas[] = new String[]{"Indicador", "Aprovação"};
        modelIndicadores = new MyDefaultTableModel(nomeColunas, 0, false);
        jTableAprovacao.setModel(modelIndicadores);
    }

    private void retirarDaLista(List<Indicador> indicadorList, int filtro) {

        for (int i = 0; i < indicadorList.size(); i++)
            if (indicadorList.get(i).getAprovacao() == filtro) {
                indicadorList.remove(i);

                retirarDaLista(indicadorList, filtro);
                break;
            }
    }

    private void preencherTabela(List<Indicador> indicadorList) {

        for (int i = 0; i < indicadorList.size(); i++) {
            String linha[] = new String[]{
                indicadorList.get(i).getNome(),
                getAprovacao(indicadorList.get(i))};

            modelIndicadores.addRow(linha);
        }
        jTableAprovacao.setModel(modelIndicadores);
    }

    private String getAprovacao(Indicador indicador) {
        switch (indicador.getAprovacao()) {
            case 0:
                return "Aprovado";
            case 1:
                return "Não aprovado";
            case 2:
                return "Não analisado";
        }
        return "Não analisado";
    }

    public void buscarIndicador() {
        String nomeParaBuscar = jTextFieldBuscar.getText();
        pesquisarIndicadorEPreencherTabela(nomeParaBuscar);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAprovacao = new javax.swing.JTable();
        jButtonNovo = new javax.swing.JButton();
        jCheckBoxAprovado = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jCheckBoxNaoAnalisado = new javax.swing.JCheckBox();
        jCheckBoxNaoAprovado = new javax.swing.JCheckBox();

        setTitle("Aprovação");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Buscar medida:");

        jTextFieldBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarActionPerformed(evt);
            }
        });

        jTableAprovacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Título 1", "Título 2", "Título 3"
            }
        ));
        jTableAprovacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAprovacaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAprovacao);

        jButtonNovo.setText("Aprovação");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jCheckBoxAprovado.setText("Aprovados");
        jCheckBoxAprovado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAprovadoActionPerformed(evt);
            }
        });

        jLabel2.setText("Filtro:");

        jCheckBoxNaoAnalisado.setText("Não analisadas");
        jCheckBoxNaoAnalisado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxNaoAnalisadoActionPerformed(evt);
            }
        });

        jCheckBoxNaoAprovado.setText("Não aprovadas");
        jCheckBoxNaoAprovado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxNaoAprovadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonNovo))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxAprovado)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxNaoAprovado)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxNaoAnalisado))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxAprovado)
                    .addComponent(jLabel2)
                    .addComponent(jCheckBoxNaoAnalisado)
                    .addComponent(jCheckBoxNaoAprovado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNovo)
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

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed

        if (jTableAprovacao.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Você deve selecionar um indicador na tabela.");
            return;
        }

        Indicador indicadorAux = ctrlIndicador.findByParteNome(jTableAprovacao.getValueAt(jTableAprovacao.getSelectedRow(), 0).toString(), Copia.getProjetoSelecionado().getId()).get(0);

        ViewNovaAprovacaoDialog novaAprovacaoDialog = new ViewNovaAprovacaoDialog(null, true);
        novaAprovacaoDialog.showNovaAprovacaoDialog(indicadorAux);

        buscarIndicador();
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jTextFieldBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarActionPerformed
        buscarIndicador();

        //jTextFieldBuscar.setText("");
    }//GEN-LAST:event_jTextFieldBuscarActionPerformed

    private void jCheckBoxAprovadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAprovadoActionPerformed
        buscarIndicador();
    }//GEN-LAST:event_jCheckBoxAprovadoActionPerformed

    private void jCheckBoxNaoAprovadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxNaoAprovadoActionPerformed
        buscarIndicador();
    }//GEN-LAST:event_jCheckBoxNaoAprovadoActionPerformed

    private void jCheckBoxNaoAnalisadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxNaoAnalisadoActionPerformed
        buscarIndicador();
    }//GEN-LAST:event_jCheckBoxNaoAnalisadoActionPerformed

    private void jTableAprovacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAprovacaoMouseClicked
        if (evt.getClickCount() < 2)
            return;

        if (jTableAprovacao.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Você deve selecionar um indicador na tabela.");
            return;
        }

        Indicador indicadorAux = ctrlIndicador.findByParteNome(jTableAprovacao.getValueAt(jTableAprovacao.getSelectedRow(), 0).toString(), Copia.getProjetoSelecionado().getId()).get(0);

        ViewNovaAprovacaoDialog novaAprovacaoDialog = new ViewNovaAprovacaoDialog(null, true);
        novaAprovacaoDialog.showDetalhesDoIndicadorDialog(indicadorAux);

    }//GEN-LAST:event_jTableAprovacaoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JCheckBox jCheckBoxAprovado;
    private javax.swing.JCheckBox jCheckBoxNaoAnalisado;
    private javax.swing.JCheckBox jCheckBoxNaoAprovado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAprovacao;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
