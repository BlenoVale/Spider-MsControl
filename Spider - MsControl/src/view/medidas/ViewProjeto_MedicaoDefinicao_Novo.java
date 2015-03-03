package view.medidas;

import model.Medida;
import controller.CtrlMedida;
import util.Constantes;

import javax.swing.JOptionPane;

/**
 *
 * @author BlenoVale, DAN JHONATAN, paulo
 */
public class ViewProjeto_MedicaoDefinicao_Novo extends javax.swing.JDialog {

      private Medida medida = new Medida();

    public ViewProjeto_MedicaoDefinicao_Novo(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public boolean verificaCampos() {

        if (jTextFieldNomeMedida.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Medida\" é obrigatório");
            return false;
        } else if (jTextAreaDefinicao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Definição\" é obrigatório");
            return false;
        } else if (jTextFieldResponsavel.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Responsável pela definição\" é obrigatório");
            return false;
        } else if (jTextFieldMnemonico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Mnemônico\" é obrigatório");
            return false;
        } else if (jTextFieldEscala.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Escala\" é obrigatório");
            return false;
        } else if (jTextFieldNomeObjetivoFaixa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Faixa\" é obrigatório");
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jpainel3 = new javax.swing.JPanel();
        jLabelNomeMedida = new javax.swing.JLabel();
        jTextFieldNomeMedida = new javax.swing.JTextField();
        jLabelEscala = new javax.swing.JLabel();
        jLabelDefinicao = new javax.swing.JLabel();
        jLabelResponsavel = new javax.swing.JLabel();
        jLabelMnemonico = new javax.swing.JLabel();
        jTextFieldResponsavel = new javax.swing.JTextField();
        jTextFieldMnemonico = new javax.swing.JTextField();
        jTextFieldEscala = new javax.swing.JTextField();
        jLabelFaixa = new javax.swing.JLabel();
        jTextFieldNomeObjetivoFaixa = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaObservacoes = new javax.swing.JTextArea();
        jLabelObservacoes = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaDefinicao = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro nova definição medida");
        setPreferredSize(new java.awt.Dimension(600, 500));
        setResizable(false);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(580, 470));

        Jpainel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Jpainel3.setPreferredSize(new java.awt.Dimension(460, 450));

        jLabelNomeMedida.setText("Medida:");

        jTextFieldNomeMedida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabelEscala.setText("Escala:");

        jLabelDefinicao.setText("Definição:");

        jLabelResponsavel.setText("Responsável pela definição:");

        jLabelMnemonico.setText("Mnemônico:");

        jTextFieldResponsavel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jTextFieldMnemonico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jTextFieldEscala.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabelFaixa.setText("Faixa:");

        jTextFieldNomeObjetivoFaixa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jTextAreaObservacoes.setColumns(20);
        jTextAreaObservacoes.setRows(5);
        jScrollPane3.setViewportView(jTextAreaObservacoes);

        jLabelObservacoes.setText("Observações:");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jTextAreaDefinicao.setColumns(20);
        jTextAreaDefinicao.setRows(5);
        jScrollPane4.setViewportView(jTextAreaDefinicao);

        javax.swing.GroupLayout Jpainel3Layout = new javax.swing.GroupLayout(Jpainel3);
        Jpainel3.setLayout(Jpainel3Layout);
        Jpainel3Layout.setHorizontalGroup(
            Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpainel3Layout.createSequentialGroup()
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Jpainel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabelResponsavel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(Jpainel3Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabelNomeMedida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Jpainel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabelDefinicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelEscala, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMnemonico, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFaixa))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldNomeObjetivoFaixa, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                        .addGap(205, 205, 205))
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Jpainel3Layout.createSequentialGroup()
                                .addComponent(jTextFieldEscala)
                                .addGap(63, 63, 63))
                            .addComponent(jTextFieldNomeMedida)
                            .addGroup(Jpainel3Layout.createSequentialGroup()
                                .addComponent(jTextFieldResponsavel)
                                .addGap(1, 1, 1))
                            .addGroup(Jpainel3Layout.createSequentialGroup()
                                .addComponent(jTextFieldMnemonico)
                                .addGap(1, 1, 1)))
                        .addGap(142, 142, 142))
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())))
            .addGroup(Jpainel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addComponent(jLabelObservacoes)
                        .addGap(71, 71, 71)
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpainel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar)))
                .addContainerGap())
        );
        Jpainel3Layout.setVerticalGroup(
            Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpainel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeMedida)
                    .addComponent(jTextFieldNomeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabelDefinicao))
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelResponsavel)
                    .addComponent(jTextFieldResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMnemonico)
                    .addComponent(jTextFieldMnemonico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEscala))
                .addGap(4, 4, 4)
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNomeObjetivoFaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFaixa))
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabelObservacoes))
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(Jpainel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();

    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

          if(!verificaCampos())
            return;
    
          
        medida.setNome(jTextFieldNomeMedida.getText());
        medida.setDefinicao(jTextAreaDefinicao.getText());
        medida.setPontoDeVista(jTextFieldResponsavel.getText());
        medida.setMnemonico(jTextFieldMnemonico.getText());
        medida.setEscala(jTextFieldEscala.getText());
        medida.setFaixa(jTextFieldNomeObjetivoFaixa.getText());
        medida.setObservacao(jTextAreaObservacoes.getText());

        //@paulo excluir do bd unidadeMedida
        medida.setUnidadeMedida("excluir");
        CtrlMedida ctrlMedida = new CtrlMedida();
        try {
            ctrlMedida.criarNovaMedida(medida);
            ctrlMedida.registrarMedida(medida, Constantes.CADASTRO);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar");
        }


    }//GEN-LAST:event_jButtonSalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpainel3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabelDefinicao;
    private javax.swing.JLabel jLabelEscala;
    private javax.swing.JLabel jLabelFaixa;
    private javax.swing.JLabel jLabelMnemonico;
    private javax.swing.JLabel jLabelNomeMedida;
    private javax.swing.JLabel jLabelObservacoes;
    private javax.swing.JLabel jLabelResponsavel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextAreaDefinicao;
    private javax.swing.JTextArea jTextAreaObservacoes;
    private javax.swing.JTextField jTextFieldEscala;
    private javax.swing.JTextField jTextFieldMnemonico;
    private javax.swing.JTextField jTextFieldNomeMedida;
    private javax.swing.JTextField jTextFieldNomeObjetivoFaixa;
    private javax.swing.JTextField jTextFieldResponsavel;
    // End of variables declaration//GEN-END:variables
}
