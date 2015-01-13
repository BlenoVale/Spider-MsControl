package view;

import controller.CtrlObjetivos;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import model.Objetivodequestao;
import model.Projeto;
import util.Texto;

/**
 *
 * @author BlenoVale
 */
public class ViewProjeto_ObjetivosQuestoes_Novo extends javax.swing.JDialog {

    private Objetivodequestao objetivo_questao;
    private Projeto projeto_selecionado;
    private String nomeUsuario_logado;
    private DefaultComboBoxModel comboboxModel;
    private boolean ehNovaQuestao;
    private final CtrlObjetivos ctrlObjetivos = new CtrlObjetivos();

    public ViewProjeto_ObjetivosQuestoes_Novo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);

        this.getModalityType();
    }

    public void casoNovaQuestao(Projeto projeto_selecionado, String nomeUsuario_logado) {
        this.setTitle("Cadastro de nova Questão");
        this.projeto_selecionado = projeto_selecionado;
        this.nomeUsuario_logado = nomeUsuario_logado;
        objetivo_questao = new Objetivodequestao();

        ehNovaQuestao = true;

        popularComboboxObjetivoRelacionado();
        jTextFieldNomeLevantador.setText(this.nomeUsuario_logado);
        jTextFieldDataDeLevantamento.setText(Texto.formataData(new Date()));
        this.setVisible(true);
    }

    public void casoEditarQuestao(Objetivodequestao objetivo_selecionado, String nomeUsuario_logado) {
        this.setTitle("Editar Questão");
        this.projeto_selecionado = objetivo_selecionado.getObjetivodemedicacao().getProjeto();
        this.nomeUsuario_logado = nomeUsuario_logado;
        objetivo_questao = objetivo_selecionado;

        ehNovaQuestao = false;

        popularComboboxObjetivoRelacionado();
        jLabelNomeUsuario.setText("Nome do Editor");
        jLabelData.setText("Data de Edição:");
        preencherCampos();
        this.setVisible(true);

    }

    private void preencherCampos() {
        jTextFieldNomeQuestao.setText(objetivo_questao.getNome());
        jTextFieldNomeIndicador.setText(objetivo_questao.getIndicador());
        jTextAreaDescricaoIndicador.setText(objetivo_questao.getDescricaoIndicador());
        selecionarCheckBox();
        jTextFieldNomeLevantador.setText(this.nomeUsuario_logado);
        jTextFieldDataDeLevantamento.setText(Texto.formataData(new Date()));
        jComboBoxObjRelacionado.setSelectedItem(objetivo_questao.getObjetivodemedicacao().getNome());
        jTextAreaObservacao.setText(objetivo_questao.getObservacao());
    }

    private void selecionarCheckBox() {
        switch (objetivo_questao.getTipoDeDerivacao()) {
            case "Objetivo de Negócio":
                jCheckBoxObjNegocio.setSelected(true);
                break;
            case "Legislação":
                jCheckBoxLegislacao.setSelected(true);
                break;
            case "Objetivo de Produto":
                jCheckBoxObjProduto.setSelected(true);
                break;
            case "Objetivo de Processo":
                jCheckBoxObjProcesso.setSelected(true);
                break;
        }
    }

    private void popularComboboxObjetivoRelacionado() {
        comboboxModel = new DefaultComboBoxModel();

        comboboxModel.addElement("-Selecione um Objetivo de Medição-");
        for (int i = 0; i < this.projeto_selecionado.getObjetivodemedicacaoList().size(); i++) {
            comboboxModel.addElement(this.projeto_selecionado.getObjetivodemedicacaoList().get(i).getNome());
        }
        jComboBoxObjRelacionado.setModel(comboboxModel);
    }

    private void setTipodeVariacao(JCheckBox jCheckBox) {
        jCheckBoxObjNegocio.setSelected(false);
        jCheckBoxLegislacao.setSelected(false);
        jCheckBoxObjProduto.setSelected(false);
        jCheckBoxObjProcesso.setSelected(false);

        jCheckBox.setSelected(true);
    }

    private String getTipoDeVariacao() {
        String variacao = null;
        if (jCheckBoxObjNegocio.isSelected()) {
            variacao = "Objetivo de Negócio";
        } else if (jCheckBoxLegislacao.isSelected()) {
            variacao = "Legislação";
        } else if (jCheckBoxObjProduto.isSelected()) {
            variacao = "Objetivo de Produto";
        } else if (jCheckBoxObjProcesso.isSelected()) {
            variacao = "Objetivo de Processo";
        }
        return variacao;
    }

    private boolean validarCampos() {
        int cont = 0;
        String mensagem = null;

        if (jTextFieldNomeQuestao.getText().isEmpty()) {
            mensagem = "Campo \"Nome da Questão\" não pode ser vazio.";
            cont++;
        }
        if (jTextFieldNomeLevantador.getText().isEmpty()) {
            mensagem = "Campo \"Nome do Levantador\" não pode ser vazio.";
            cont++;
        }
        if (jTextFieldNomeIndicador.getText().isEmpty()) {
            mensagem = "Campo \"Nome do Indicador\" não pode ser vazio.";
            cont++;
        }
        if (jTextAreaDescricaoIndicador.getText().isEmpty()) {
            mensagem = "Campo \"Descrição do Indicador\" não pode ser vazio.";
            cont++;
        }
        if (getTipoDeVariacao() == null) {
            mensagem = "É necessário selecionar um \"Tipo de variação\".";
            cont++;
        }
        if (jComboBoxObjRelacionado.getSelectedItem() == "-Selecione um Objetivo de Medição-") {
            mensagem = "É necessário selecionar um \"Objetivo\" no Combobox.";
            cont++;
        }

        if (cont == 0) {
            return true;
        } else if (cont == 1) {
            JOptionPane.showMessageDialog(null, mensagem);
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Mais de um campo estão vazios ou invalidos.");
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelNomeUsuario = new javax.swing.JLabel();
        jTextFieldNomeQuestao = new javax.swing.JTextField();
        jTextFieldNomeLevantador = new javax.swing.JTextField();
        jScrollPaneDescricaoIndicador = new javax.swing.JScrollPane();
        jTextAreaDescricaoIndicador = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNomeIndicador = new javax.swing.JTextField();
        jPanelTipoDeVariacao = new javax.swing.JPanel();
        jCheckBoxObjNegocio = new javax.swing.JCheckBox();
        jCheckBoxLegislacao = new javax.swing.JCheckBox();
        jCheckBoxObjProduto = new javax.swing.JCheckBox();
        jCheckBoxObjProcesso = new javax.swing.JCheckBox();
        jLabelData = new javax.swing.JLabel();
        jTextFieldDataDeLevantamento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jScrollPaneObservacao = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jComboBoxObjRelacionado = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de nova Questão");

        jLabel1.setText("Nome da Questão:");

        jLabelNomeUsuario.setText("Nome do levantador:");

        jTextFieldNomeLevantador.setEditable(false);
        jTextFieldNomeLevantador.setBackground(new java.awt.Color(204, 204, 204));

        jTextAreaDescricaoIndicador.setColumns(20);
        jTextAreaDescricaoIndicador.setRows(5);
        jScrollPaneDescricaoIndicador.setViewportView(jTextAreaDescricaoIndicador);

        jLabel4.setText("Descrição do Indicador:");

        jLabel3.setText("Nome do Indicador: ");

        jPanelTipoDeVariacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo de variação"));

        jCheckBoxObjNegocio.setText("Objetivo de Negócio");
        jCheckBoxObjNegocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxObjNegocioActionPerformed(evt);
            }
        });

        jCheckBoxLegislacao.setText("Legislação");
        jCheckBoxLegislacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxLegislacaoActionPerformed(evt);
            }
        });

        jCheckBoxObjProduto.setText("Objetivo de Produto");
        jCheckBoxObjProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxObjProdutoActionPerformed(evt);
            }
        });

        jCheckBoxObjProcesso.setText("Objetivo de Processo");
        jCheckBoxObjProcesso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxObjProcessoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTipoDeVariacaoLayout = new javax.swing.GroupLayout(jPanelTipoDeVariacao);
        jPanelTipoDeVariacao.setLayout(jPanelTipoDeVariacaoLayout);
        jPanelTipoDeVariacaoLayout.setHorizontalGroup(
            jPanelTipoDeVariacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTipoDeVariacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTipoDeVariacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxObjNegocio)
                    .addComponent(jCheckBoxLegislacao))
                .addGap(18, 18, 18)
                .addGroup(jPanelTipoDeVariacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxObjProcesso)
                    .addComponent(jCheckBoxObjProduto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTipoDeVariacaoLayout.setVerticalGroup(
            jPanelTipoDeVariacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTipoDeVariacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTipoDeVariacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxObjNegocio)
                    .addComponent(jCheckBoxObjProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelTipoDeVariacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxLegislacao)
                    .addComponent(jCheckBoxObjProcesso))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelData.setText("Data de levantamento:");

        jTextFieldDataDeLevantamento.setEditable(false);
        jTextFieldDataDeLevantamento.setBackground(new java.awt.Color(204, 204, 204));

        jLabel7.setText("Relacionada ao objetivo:");

        jLabel8.setText("Instruções/Observações:");

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setRows(5);
        jScrollPaneObservacao.setViewportView(jTextAreaObservacao);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabelNomeUsuario)
                            .addComponent(jLabel3))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldNomeLevantador, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNomeIndicador, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNomeQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 214, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPaneDescricaoIndicador, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelData)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPaneObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldDataDeLevantamento, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxObjRelacionado, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(jPanelTipoDeVariacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNomeQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeUsuario)
                    .addComponent(jTextFieldNomeLevantador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldNomeIndicador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPaneDescricaoIndicador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTipoDeVariacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelData)
                    .addComponent(jTextFieldDataDeLevantamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxObjRelacionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPaneObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCancelar)
                            .addComponent(jButtonSalvar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (validarCampos()) {
            if (ehNovaQuestao) {
                objetivo_questao.setNome(jTextFieldNomeQuestao.getText());
                objetivo_questao.setNomeDoUsuario(jTextFieldNomeLevantador.getText());
                objetivo_questao.setIndicador(jTextFieldNomeIndicador.getText());
                objetivo_questao.setDescricaoIndicador(jTextAreaDescricaoIndicador.getText());
                objetivo_questao.setTipoDeDerivacao(getTipoDeVariacao());
                objetivo_questao.setDataLevantamento(new Date());
                objetivo_questao.setObjetivodemedicacao(ctrlObjetivos.buscaObjetivoDeMedicaoPeloNome(jComboBoxObjRelacionado.getSelectedItem().toString()));
                objetivo_questao.setObservacao(jTextAreaObservacao.getText());
                objetivo_questao.setPrioridade(ctrlObjetivos.contaQuantidadeQuestoesPorProjeto(projeto_selecionado.getId()) + 1);

                ctrlObjetivos.criarNovaQuestao(objetivo_questao);
            } else {
                objetivo_questao.setNome(jTextFieldNomeQuestao.getText());
                objetivo_questao.setNomeDoUsuario(jTextFieldNomeLevantador.getText());
                objetivo_questao.setIndicador(jTextFieldNomeIndicador.getText());
                objetivo_questao.setDescricaoIndicador(jTextAreaDescricaoIndicador.getText());
                objetivo_questao.setTipoDeDerivacao(getTipoDeVariacao());
                objetivo_questao.setDataLevantamento(new Date());
                objetivo_questao.setObjetivodemedicacao(ctrlObjetivos.buscaObjetivoDeMedicaoPeloNome(jComboBoxObjRelacionado.getSelectedItem().toString()));
                objetivo_questao.setObservacao(jTextAreaObservacao.getText());
                objetivo_questao.setPrioridade(ctrlObjetivos.contaQuantidadeQuestoesPorProjeto(projeto_selecionado.getId()) + 1);
                
                ctrlObjetivos.editarQuestao(objetivo_questao);

            }
            this.dispose();
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jCheckBoxObjNegocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxObjNegocioActionPerformed
        setTipodeVariacao(jCheckBoxObjNegocio);
    }//GEN-LAST:event_jCheckBoxObjNegocioActionPerformed

    private void jCheckBoxLegislacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxLegislacaoActionPerformed
        setTipodeVariacao(jCheckBoxLegislacao);
    }//GEN-LAST:event_jCheckBoxLegislacaoActionPerformed

    private void jCheckBoxObjProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxObjProdutoActionPerformed
        setTipodeVariacao(jCheckBoxObjProduto);
    }//GEN-LAST:event_jCheckBoxObjProdutoActionPerformed

    private void jCheckBoxObjProcessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxObjProcessoActionPerformed
        setTipodeVariacao(jCheckBoxObjProcesso);
    }//GEN-LAST:event_jCheckBoxObjProcessoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JCheckBox jCheckBoxLegislacao;
    private javax.swing.JCheckBox jCheckBoxObjNegocio;
    private javax.swing.JCheckBox jCheckBoxObjProcesso;
    private javax.swing.JCheckBox jCheckBoxObjProduto;
    private javax.swing.JComboBox jComboBoxObjRelacionado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelNomeUsuario;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelTipoDeVariacao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneDescricaoIndicador;
    private javax.swing.JScrollPane jScrollPaneObservacao;
    private javax.swing.JTextArea jTextAreaDescricaoIndicador;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldDataDeLevantamento;
    private javax.swing.JTextField jTextFieldNomeIndicador;
    private javax.swing.JTextField jTextFieldNomeLevantador;
    private javax.swing.JTextField jTextFieldNomeQuestao;
    // End of variables declaration//GEN-END:variables
}
