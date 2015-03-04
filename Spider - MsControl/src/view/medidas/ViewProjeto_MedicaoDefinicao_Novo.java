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
        } else if (!isSelectedRadio()) {
            JOptionPane.showMessageDialog(null, "O campo \"Escala\" é obrigatório");
            return false;
        } else if (jTextFieldNomeFaixaInicio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Faixa\" é obrigatório");
            return false;
        } else if (jTextFieldFaixaFim.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Faixa\" é obrigatório");
            return false;
        } else {
            return true;
        }
    }

    public boolean isSelectedRadio() {
        if (jRadioButtonAlta.isSelected()) {
            return true;
        } else if (jRadioButtonMedia.isSelected()) {
            return true;
        } else if (jRadioButtonBaixa.isSelected()) {
            return true;
        } else {
            return false;
        }
    }

    public String isSelectedRadioText() {
        if (jRadioButtonAlta.isSelected()) {
            return jRadioButtonAlta.getText();
        } else if (jRadioButtonMedia.isSelected()) {
            return jRadioButtonMedia.getText();
        } else {
            return jRadioButtonBaixa.getText();
        }

    }

    public void JTextFieldSomenteNumeros(java.awt.event.KeyEvent evt) {
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        Jpainel3 = new javax.swing.JPanel();
        jLabelNomeMedida = new javax.swing.JLabel();
        jTextFieldNomeMedida = new javax.swing.JTextField();
        jLabelDefinicao = new javax.swing.JLabel();
        jLabelResponsavel = new javax.swing.JLabel();
        jLabelMnemonico = new javax.swing.JLabel();
        jTextFieldResponsavel = new javax.swing.JTextField();
        jTextFieldMnemonico = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaObservacoes = new javax.swing.JTextArea();
        jLabelObservacoes = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaDefinicao = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jRadioButtonAlta = new javax.swing.JRadioButton();
        jRadioButtonMedia = new javax.swing.JRadioButton();
        jRadioButtonBaixa = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldNomeFaixaInicio = new javax.swing.JTextField();
        jLabelDe = new javax.swing.JLabel();
        jLabelAte = new javax.swing.JLabel();
        jTextFieldFaixaFim = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro nova definição medida");
        setPreferredSize(new java.awt.Dimension(650, 580));
        setResizable(false);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(580, 470));

        Jpainel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Jpainel3.setPreferredSize(new java.awt.Dimension(500, 460));

        jLabelNomeMedida.setText("Medida:");

        jTextFieldNomeMedida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldNomeMedida.setToolTipText("Nome da Medida a ser cadastrada (ex: Tarefas Planejadas, Horas Trabalhadas, Nº de bugs, ...).");

        jLabelDefinicao.setText("Definição:");

        jLabelResponsavel.setText("Responsável pela definição:");

        jLabelMnemonico.setText("Mnemônico:");

        jTextFieldResponsavel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldResponsavel.setToolTipText("Pessoa que definiu a medida que está sendo cadastrada.");

        jTextFieldMnemonico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldMnemonico.setToolTipText("Sigla que representa a medida (ex: THT - Total de Horas Trabalhadas, NG - Número de Bugs, ...).");

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
        jTextAreaDefinicao.setToolTipText("Uma breve definição e/ou descrição sobre a medida que está sendo cadastrada.");
        jScrollPane4.setViewportView(jTextAreaDefinicao);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Escala"));

        jRadioButtonAlta.setText("Alta");
        jRadioButtonAlta.setToolTipText("Valores que podem ser atribuídos à medida");

        jRadioButtonMedia.setText("Média");
        jRadioButtonMedia.setToolTipText("Valores que podem ser atribuídos à medida");

        jRadioButtonBaixa.setText("Baixa");
        jRadioButtonBaixa.setToolTipText("Valores que podem ser atribuídos à medida");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButtonAlta)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonMedia)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonBaixa)
                .addGap(214, 214, 214))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonAlta)
                    .addComponent(jRadioButtonMedia)
                    .addComponent(jRadioButtonBaixa))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Faixa"));

        jTextFieldNomeFaixaInicio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldNomeFaixaInicio.setToolTipText("Limites de valores da escala definida de acordo com dados históricos ou com metas estabelecidas (ex: [0, 10], ...).");
        jTextFieldNomeFaixaInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNomeFaixaInicioKeyTyped(evt);
            }
        });

        jLabelDe.setText("De:");

        jLabelAte.setText("Até:");

        jTextFieldFaixaFim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFaixaFimKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(jLabelDe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldNomeFaixaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelAte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldFaixaFim, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNomeFaixaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDe)
                    .addComponent(jLabelAte)
                    .addComponent(jTextFieldFaixaFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

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
                        .addComponent(jLabelMnemonico, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNomeMedida)
                            .addGroup(Jpainel3Layout.createSequentialGroup()
                                .addComponent(jTextFieldResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(142, 142, 142))
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldMnemonico, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(Jpainel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpainel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addComponent(jLabelObservacoes)
                        .addGap(71, 71, 71)
                        .addComponent(jScrollPane3))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMnemonico)
                    .addComponent(jTextFieldMnemonico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabelObservacoes))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("Escala");

        jScrollPane1.setViewportView(Jpainel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        if (!verificaCampos()) {
            return;
        }

        medida.setNome(jTextFieldNomeMedida.getText());
        medida.setDefinicao(jTextAreaDefinicao.getText());
        medida.setPontoDeVista(jTextFieldResponsavel.getText());
        medida.setMnemonico(jTextFieldMnemonico.getText());
        medida.setEscala(isSelectedRadioText());
        medida.setFaixa(jTextFieldNomeFaixaInicio.getText() + "/" + jTextFieldFaixaFim.getText());
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
         this.dispose();
    }//GEN-LAST:event_jButtonSalvarActionPerformed


    private void jTextFieldNomeFaixaInicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeFaixaInicioKeyTyped
        JTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldNomeFaixaInicioKeyTyped

    private void jTextFieldFaixaFimKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFaixaFimKeyTyped
        JTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldFaixaFimKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpainel3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabelAte;
    private javax.swing.JLabel jLabelDe;
    private javax.swing.JLabel jLabelDefinicao;
    private javax.swing.JLabel jLabelMnemonico;
    private javax.swing.JLabel jLabelNomeMedida;
    private javax.swing.JLabel jLabelObservacoes;
    private javax.swing.JLabel jLabelResponsavel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonAlta;
    private javax.swing.JRadioButton jRadioButtonBaixa;
    private javax.swing.JRadioButton jRadioButtonMedia;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextAreaDefinicao;
    private javax.swing.JTextArea jTextAreaObservacoes;
    private javax.swing.JTextField jTextFieldFaixaFim;
    private javax.swing.JTextField jTextFieldMnemonico;
    private javax.swing.JTextField jTextFieldNomeFaixaInicio;
    private javax.swing.JTextField jTextFieldNomeMedida;
    private javax.swing.JTextField jTextFieldResponsavel;
    // End of variables declaration//GEN-END:variables
}
