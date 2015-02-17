package view.objetivos;

import controller.CtrlObjetivos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Objetivodemedicao;
import model.Objetivodequestao;
import model.Projeto;
import model.Registroobjetivoquestao;
import util.Constantes;
import util.Copia;
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
    private List<Registroobjetivoquestao> registro;
    private final CtrlObjetivos ctrlObjetivos = new CtrlObjetivos();

    public ViewProjeto_ObjetivosQuestoes_Novo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        agruparBotoesRadio();
        this.setLocationRelativeTo(null);
        this.getModalityType();
    }

    public void showNovaQuestaoDialog(Projeto projeto_selecionado, String nomeUsuario_logado) {
        this.setTitle("Cadastro de nova Questão");
        this.projeto_selecionado = projeto_selecionado;
        this.nomeUsuario_logado = nomeUsuario_logado;
        objetivo_questao = new Objetivodequestao();

        ehNovaQuestao = true;

        this.jLabelUltimaEdicao.setVisible(false);
        this.jTextFieldUltimaEdicao.setVisible(false);
        popularComboboxObjetivoRelacionado();
        jTextFieldCadastradoPor.setText(this.nomeUsuario_logado + ". Em: " + Texto.formataData(new Date()));
        this.setVisible(true);
    }

    public void showEditarQuestaoDialog(Objetivodequestao objetivo_selecionado, String nomeUsuario_logado) {
        this.setTitle("Editar Questão");
        this.projeto_selecionado = Copia.getProjetoSelecionado();
        this.nomeUsuario_logado = nomeUsuario_logado;
        objetivo_questao = objetivo_selecionado;

        ehNovaQuestao = false;

        popularComboboxObjetivoRelacionado();
        preencherCampos();
        this.setVisible(true);

    }

    private void preencherCampos() {
        jTextFieldNomeQuestao.setText(objetivo_questao.getNome());
        dateFieldDataDeLevantamento.setValue(objetivo_questao.getDataLevantamento());

        selecionarRadio();

        registro = new ArrayList<>();
        registro = ctrlObjetivos.buscaListaQuestaoPeloTipo(Constantes.CADASTRO, objetivo_questao.getId());
        jTextFieldCadastradoPor.setText(registro.get(0).getNomeUsuario() + ". Em: " + Texto.formataData(registro.get(0).getData()));

        registro = new ArrayList<>();
        registro = ctrlObjetivos.buscaListaQuestaoPeloTipo(Constantes.EDICAO, objetivo_questao.getId());
        if (registro.isEmpty()) {
            this.jLabelUltimaEdicao.setVisible(false);
            this.jTextFieldUltimaEdicao.setVisible(false);
        } else {
            jTextFieldUltimaEdicao.setText(registro.get(registro.size() - 1).getNomeUsuario() + ". Em: " + Texto.formataData(registro.get(registro.size() - 1).getData()));
        }

        jTextFieldPontoDeVista.setText(objetivo_questao.getPontoDeVista());
        jComboBoxObjRelacionado.setSelectedItem(objetivo_questao.getObjetivoDeMedicaoid().getNome());
        jTextAreaObservacao.setText(objetivo_questao.getObservacao());
    }

    private void selecionarRadio() {
        switch (objetivo_questao.getTipoDeDerivacao()) {
            case "Objetivo de Negócio":
                jRadioButtonObjetivoDeNegocio.setSelected(true);
                break;
            case "Legislação":
                jRadioButtonLegislacao.setSelected(true);
                break;
            case "Objetivo de Produto":
                jRadioButtonObjetivoDeProduto.setSelected(true);
                break;
            case "Objetivo de Processo":
                jRadioButtonObjetivoDeProcesso.setSelected(true);
                break;
        }
    }

    private void popularComboboxObjetivoRelacionado() {
        comboboxModel = new DefaultComboBoxModel();

        comboboxModel.addElement("-Selecione um Objetivo de Medição-");
        for (int i = 0; i < this.projeto_selecionado.getObjetivodemedicaoList().size(); i++) {
            comboboxModel.addElement(this.projeto_selecionado.getObjetivodemedicaoList().get(i).getNome());
        }
        jComboBoxObjRelacionado.setModel(comboboxModel);
    }

    private void agruparBotoesRadio() {
        buttonGroupTipoDeVariacao.add(jRadioButtonObjetivoDeNegocio);
        buttonGroupTipoDeVariacao.add(jRadioButtonLegislacao);
        buttonGroupTipoDeVariacao.add(jRadioButtonObjetivoDeProduto);
        buttonGroupTipoDeVariacao.add(jRadioButtonObjetivoDeProcesso);
    }

    private String getTipoDeVariacao() {
        String variacao = null;
        if (jRadioButtonObjetivoDeNegocio.isSelected()) {
            variacao = "Objetivo de Negócio";
        } else if (jRadioButtonLegislacao.isSelected()) {
            variacao = "Legislação";
        } else if (jRadioButtonObjetivoDeProduto.isSelected()) {
            variacao = "Objetivo de Produto";
        } else if (jRadioButtonObjetivoDeProcesso.isSelected()) {
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
        if (jTextFieldPontoDeVista.getText().isEmpty()) {
            mensagem = "Campo \"Nome do Levantador\" não pode ser vazio.";
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

        buttonGroupTipoDeVariacao = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelNomeUsuario = new javax.swing.JLabel();
        jTextFieldNomeQuestao = new javax.swing.JTextField();
        jTextFieldPontoDeVista = new javax.swing.JTextField();
        jPanelTipoDeVariacao = new javax.swing.JPanel();
        jRadioButtonObjetivoDeNegocio = new javax.swing.JRadioButton();
        jRadioButtonLegislacao = new javax.swing.JRadioButton();
        jRadioButtonObjetivoDeProduto = new javax.swing.JRadioButton();
        jRadioButtonObjetivoDeProcesso = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jScrollPaneObservacao = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jComboBoxObjRelacionado = new javax.swing.JComboBox();
        jTextFieldCadastradoPor = new javax.swing.JTextField();
        jLabelCadastradoPor = new javax.swing.JLabel();
        jTextFieldUltimaEdicao = new javax.swing.JTextField();
        jLabelUltimaEdicao = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateFieldDataDeLevantamento = new net.sf.nachocalendar.components.DateField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de nova Necessidade de informação");
        setResizable(false);

        jLabel1.setText("Necessidade de informação:");

        jLabelNomeUsuario.setText("Ponto de Vista:");

        jPanelTipoDeVariacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo de variação"));

        jRadioButtonObjetivoDeNegocio.setText("Objetivo de negócio");

        jRadioButtonLegislacao.setText("Legislação");

        jRadioButtonObjetivoDeProduto.setText("Objetivo de Produto");

        jRadioButtonObjetivoDeProcesso.setText("Objeivo de Processo");

        javax.swing.GroupLayout jPanelTipoDeVariacaoLayout = new javax.swing.GroupLayout(jPanelTipoDeVariacao);
        jPanelTipoDeVariacao.setLayout(jPanelTipoDeVariacaoLayout);
        jPanelTipoDeVariacaoLayout.setHorizontalGroup(
            jPanelTipoDeVariacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTipoDeVariacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTipoDeVariacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonObjetivoDeNegocio)
                    .addComponent(jRadioButtonLegislacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelTipoDeVariacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonObjetivoDeProcesso)
                    .addComponent(jRadioButtonObjetivoDeProduto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTipoDeVariacaoLayout.setVerticalGroup(
            jPanelTipoDeVariacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTipoDeVariacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTipoDeVariacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonObjetivoDeNegocio)
                    .addComponent(jRadioButtonObjetivoDeProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelTipoDeVariacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonLegislacao)
                    .addComponent(jRadioButtonObjetivoDeProcesso))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        jTextAreaObservacao.setLineWrap(true);
        jTextAreaObservacao.setRows(3);
        jTextAreaObservacao.setWrapStyleWord(true);
        jScrollPaneObservacao.setViewportView(jTextAreaObservacao);

        jTextFieldCadastradoPor.setEditable(false);
        jTextFieldCadastradoPor.setBackground(new java.awt.Color(204, 204, 204));

        jLabelCadastradoPor.setText("Cadastrado por:");

        jTextFieldUltimaEdicao.setEditable(false);
        jTextFieldUltimaEdicao.setBackground(new java.awt.Color(204, 204, 204));

        jLabelUltimaEdicao.setText("Ultima Edição:");

        jLabel2.setText("Data de levantamento");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabelNomeUsuario)
                    .addComponent(jLabel8)
                    .addComponent(jLabelCadastradoPor)
                    .addComponent(jLabel7)
                    .addComponent(jLabelUltimaEdicao)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneObservacao)
                    .addComponent(jTextFieldUltimaEdicao)
                    .addComponent(jTextFieldCadastradoPor)
                    .addComponent(jTextFieldNomeQuestao)
                    .addComponent(jTextFieldPontoDeVista)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxObjRelacionado, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateFieldDataDeLevantamento, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(442, Short.MAX_VALUE)
                .addComponent(jButtonSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanelTipoDeVariacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNomeQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeUsuario)
                    .addComponent(jTextFieldPontoDeVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(dateFieldDataDeLevantamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCadastradoPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCadastradoPor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUltimaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUltimaEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelTipoDeVariacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxObjRelacionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPaneObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jButtonSalvar)
                            .addComponent(jButtonCancelar)))
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (!validarCampos()) {
            return;
        }

        objetivo_questao.setNome(jTextFieldNomeQuestao.getText());
        objetivo_questao.setPontoDeVista(jTextFieldPontoDeVista.getText());
        objetivo_questao.setTipoDeDerivacao(getTipoDeVariacao());
        objetivo_questao.setObservacao(jTextAreaObservacao.getText());
        objetivo_questao.setDataLevantamento((Date) dateFieldDataDeLevantamento.getValue());

        String nomeObjetivo = jComboBoxObjRelacionado.getSelectedItem().toString();
        Objetivodemedicao objetivoMedicao = ctrlObjetivos.buscaObjetivoDeMedicaoPeloNome(nomeObjetivo, projeto_selecionado.getId());
        objetivo_questao.setObjetivoDeMedicaoid(objetivoMedicao);

        boolean feito = false;
        if (ehNovaQuestao) {
            feito = ctrlObjetivos.criarNovaQuestao(objetivo_questao);
        } else {
            feito = ctrlObjetivos.editarQuestao(objetivo_questao);
        }

        if (feito) {
            this.dispose();
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupTipoDeVariacao;
    private net.sf.nachocalendar.components.DateField dateFieldDataDeLevantamento;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox jComboBoxObjRelacionado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelCadastradoPor;
    private javax.swing.JLabel jLabelNomeUsuario;
    private javax.swing.JLabel jLabelUltimaEdicao;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelTipoDeVariacao;
    private javax.swing.JRadioButton jRadioButtonLegislacao;
    private javax.swing.JRadioButton jRadioButtonObjetivoDeNegocio;
    private javax.swing.JRadioButton jRadioButtonObjetivoDeProcesso;
    private javax.swing.JRadioButton jRadioButtonObjetivoDeProduto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneObservacao;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldCadastradoPor;
    private javax.swing.JTextField jTextFieldNomeQuestao;
    private javax.swing.JTextField jTextFieldPontoDeVista;
    private javax.swing.JTextField jTextFieldUltimaEdicao;
    // End of variables declaration//GEN-END:variables
}
