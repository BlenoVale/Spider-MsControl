package view.objetivos;

import controller.CtrlObjetivos;
import facade.FacadeJpa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Objetivodemedicao;
import model.Projeto;
import model.Registroobjetivomedicao;
import util.Constantes;
import util.Texto;

/**
 *
 * @author BlenoVale, DAN JHONATAN
 */
public class ViewProjeto_ObjetivosDeMedicao_Novo extends javax.swing.JDialog {

    private Projeto projeto;
    private Objetivodemedicao objetivo;
    private List<Registroobjetivomedicao> registros;

    private boolean ehNovoObjetivo;
    private boolean objetivoCriadoEditado = false;

    public ViewProjeto_ObjetivosDeMedicao_Novo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        agruparBotoes();
        jRadioButtonOrganizacional.setSelected(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * Criar um novo objetivo de medicao.
     *
     * @param projeto O projeto que o objetivo vai pertencer.
     * @return true caso o objetivo seja criado com sucesso, false caso.
     * contrario
     */
    public boolean showNovoObjetivoDialog(Projeto projeto) {

        this.setTitle("Cadastro de novo objetivo de medição");
        jTextFieldLevantamento.setEditable(true);
        jTextFieldEditadoPor.setVisible(false);
        jLabelEditadoPor.setVisible(false);

        jLabelCadastradoPor.setVisible(false);
        jTextFieldCadastradoPor.setVisible(false);

        this.projeto = projeto;
        objetivo = new Objetivodemedicao();
        ehNovoObjetivo = true;

        
        this.pack();
        this.setVisible(true);
        return objetivoCriadoEditado;
    }

    /**
     * Edicao dos dados do objetivo de medicao.
     *
     * @param objetivo O objetivo que serah editado
     * @return true caso o objetivo seja editado com sucesso, false caso
     * contrario.
     */
    public boolean showEditarObjetivoDialog(Objetivodemedicao objetivo) {

        this.setTitle("Edição de objetivo de medição");
        jTextFieldEditadoPor.setVisible(true);
        jLabelEditadoPor.setVisible(true);

        jLabelCadastradoPor.setVisible(true);
        jTextFieldCadastradoPor.setVisible(true);

        registros = new ArrayList<>();
        registros = FacadeJpa.getInstance().getRegistroObjetivoMedicaoJpa().findRegistroMedicaoByTipo(Constantes.EDICAO, objetivo.getObjetivodemedicaoPK().getProjetoid(), objetivo.getObjetivodemedicaoPK().getId());
        if (registros.isEmpty()) {
            jTextFieldEditadoPor.setVisible(false);
            jLabelEditadoPor.setVisible(false);
        } else {
            jTextFieldEditadoPor.setText(registros.get(registros.size() - 1).getNomeUsuario() + ". Em: " + Texto.formataData(registros.get(registros.size() - 1).getData()));

            jTextFieldEditadoPor.setVisible(true);
            jLabelEditadoPor.setVisible(true);
        }

        registros = new ArrayList<>();
        registros = FacadeJpa.getInstance().getRegistroObjetivoMedicaoJpa().findRegistroMedicaoByTipo(Constantes.CADASTRO, objetivo.getObjetivodemedicaoPK().getProjetoid(), objetivo.getObjetivodemedicaoPK().getId());
        if (!registros.isEmpty()) {
            jTextFieldCadastradoPor.setText(registros.get(0).getNomeUsuario() + ". Em: " + Texto.formataData(registros.get(0).getData()));
        }
        this.projeto = objetivo.getProjeto();
        this.objetivo = objetivo;
        ehNovoObjetivo = false;

        preencherCampos();

        this.pack();
        this.setVisible(true);
        return objetivoCriadoEditado;
    }

    /**
     * Mostra os dados do objetivo de medicao, sem poder altera-lo
     *
     * @param objetivo O objetivo que terah os dados detalhados
     */
    public void showDetalhesDoObjetivoDialog(Objetivodemedicao objetivo) {
        this.setTitle("Detalhes");

        jTextFieldEditadoPor.setVisible(true);
        jLabelEditadoPor.setVisible(true);

        jLabelCadastradoPor.setVisible(true);
        jTextFieldCadastradoPor.setVisible(true);

        jTextFieldLevantamento.setText(objetivo.getPontoDeVista());

        registros = new ArrayList<>();
        registros = FacadeJpa.getInstance().getRegistroObjetivoMedicaoJpa().findRegistroMedicaoByTipo(Constantes.EDICAO, objetivo.getObjetivodemedicaoPK().getProjetoid(), objetivo.getObjetivodemedicaoPK().getId());
        if (registros.isEmpty()) {
            jTextFieldEditadoPor.setVisible(false);
            jLabelEditadoPor.setVisible(false);
        } else {
            jTextFieldEditadoPor.setText(registros.get(registros.size() - 1).getNomeUsuario() + ". Em: " + Texto.formataData(registros.get(registros.size() - 1).getData()));
            jTextFieldEditadoPor.setVisible(true);
            jLabelEditadoPor.setVisible(true);
        }

        registros = new ArrayList<>();
        registros = FacadeJpa.getInstance().getRegistroObjetivoMedicaoJpa().findRegistroMedicaoByTipo(Constantes.CADASTRO, objetivo.getObjetivodemedicaoPK().getProjetoid(), objetivo.getObjetivodemedicaoPK().getId());
        if (!registros.isEmpty()) {
            jTextFieldCadastradoPor.setText(registros.get(0).getNomeUsuario() + ". Em: " + Texto.formataData(registros.get(0).getData()));
        }
        this.objetivo = objetivo;

        preencherCampos();
        bloquearCampos();

        jButtonSalvar.setVisible(false);
        jButtonCancelar.setText("OK");

        this.pack();
        this.setVisible(true);
    }

    private void preencherCampos() {
        jTextFieldNomeObjetivo.setText(objetivo.getNome());
        jTextFieldLevantamento.setText(objetivo.getPontoDeVista());
        jTextAreaProposito.setText(objetivo.getProposito());
        jTextAreaFoco.setText(objetivo.getFoco());
        jTextAreaAmbiente.setText(objetivo.getAmbiente());
        jTextAreaObservacao.setText(objetivo.getObservacao());
        selecionarCheckBox();
    }

    private void selecionarCheckBox() {
        if (objetivo.getNivelObjetivo().equals("Organizacional"))
            jRadioButtonOrganizacional.setSelected(true);
        else
            jRadioButtonDeProjeto.setSelected(true);
    }

    private String getSelectedNivelObjetivo() {
        if (jRadioButtonDeProjeto.isSelected())
            return "De Projeto";
        else
            return "Organizacional";
    }

    public boolean checarCampos() {
        if (jTextFieldNomeObjetivo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo \"Nome do objetivo\" não pode ser vazio");
            return false;
        } else if (jTextFieldLevantamento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite quem levantou este objetivo");
            return false;
        } else if (jTextAreaProposito.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "A descrição do propósito não pode ser vazio");
            return false;
        } else if (jTextAreaFoco.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "A descrição do foco não pode ser vazio");
            return false;
        } else if (jTextAreaAmbiente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "A descrição do ambiente não pode ser vazio");
            return false;
        } else
            return true;
    }

    private void agruparBotoes() {
        buttonGroup1.add(jRadioButtonDeProjeto);
        buttonGroup1.add(jRadioButtonOrganizacional);
    }

    private void bloquearCampos() {
        jTextFieldNomeObjetivo.setEditable(false);
        jTextAreaProposito.setEditable(false);
        jTextAreaFoco.setEditable(false);
        jTextAreaAmbiente.setEditable(false);
        jTextAreaObservacao.setEditable(false);
        selecionarCheckBox();

        if (jRadioButtonDeProjeto.isSelected()) {
            jRadioButtonDeProjeto.setVisible(true);
            jRadioButtonOrganizacional.setVisible(false);
        } else {
            jRadioButtonDeProjeto.setVisible(false);
            jRadioButtonOrganizacional.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaAmbiente = new javax.swing.JTextArea();
        jTextFieldLevantamento = new javax.swing.JTextField();
        jLabelLevantamento = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelCadastradoPor = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jTextFieldEditadoPor = new javax.swing.JTextField();
        jButtonCancelar = new javax.swing.JButton();
        jLabelEditadoPor = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jTextFieldCadastradoPor = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaFoco = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNomeObjetivo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jRadioButtonOrganizacional = new javax.swing.JRadioButton();
        jRadioButtonDeProjeto = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextAreaProposito = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro novo objetivo de medição");
        setResizable(false);

        jLabel4.setText("Ambiente");

        jTextAreaAmbiente.setColumns(20);
        jTextAreaAmbiente.setLineWrap(true);
        jTextAreaAmbiente.setRows(3);
        jTextAreaAmbiente.setWrapStyleWord(true);
        jScrollPane5.setViewportView(jTextAreaAmbiente);

        jTextFieldLevantamento.setEditable(false);

        jLabelLevantamento.setText("Levantado por:");

        jLabel5.setText("Observações:");

        jLabelCadastradoPor.setText("Cadastrado por:");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jTextFieldEditadoPor.setEditable(false);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabelEditadoPor.setText("Ultima edição:");

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setLineWrap(true);
        jTextAreaObservacao.setRows(3);
        jTextAreaObservacao.setWrapStyleWord(true);
        jScrollPane7.setViewportView(jTextAreaObservacao);

        jTextFieldCadastradoPor.setEditable(false);

        jTextAreaFoco.setColumns(20);
        jTextAreaFoco.setLineWrap(true);
        jTextAreaFoco.setRows(3);
        jTextAreaFoco.setWrapStyleWord(true);
        jScrollPane8.setViewportView(jTextAreaFoco);

        jLabel1.setText("Nome do objetivo:");

        jTextFieldNomeObjetivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Objetivo a nível"));

        jRadioButtonOrganizacional.setText("Organizacional");

        jRadioButtonDeProjeto.setText("De Projeto");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonOrganizacional)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonDeProjeto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonOrganizacional)
                    .addComponent(jRadioButtonDeProjeto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Propósito");

        jLabel3.setText("Foco");

        jTextAreaProposito.setColumns(20);
        jTextAreaProposito.setLineWrap(true);
        jTextAreaProposito.setRows(3);
        jTextAreaProposito.setWrapStyleWord(true);
        jScrollPane9.setViewportView(jTextAreaProposito);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane8)
                            .addComponent(jScrollPane9)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelEditadoPor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelCadastradoPor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelLevantamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldEditadoPor)
                            .addComponent(jTextFieldNomeObjetivo)
                            .addComponent(jTextFieldLevantamento)
                            .addComponent(jTextFieldCadastradoPor))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNomeObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLevantamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLevantamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCadastradoPor)
                    .addComponent(jTextFieldCadastradoPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEditadoPor)
                    .addComponent(jTextFieldEditadoPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        if (!checarCampos())
        return;

        objetivo.setProjeto(projeto);
        objetivo.setNome(jTextFieldNomeObjetivo.getText());
        objetivo.setPontoDeVista(jTextFieldLevantamento.getText());
        objetivo.setNivelObjetivo(getSelectedNivelObjetivo());
        objetivo.setProposito(jTextAreaProposito.getText());
        objetivo.setFoco(jTextAreaFoco.getText());
        objetivo.setAmbiente(jTextAreaAmbiente.getText());
        objetivo.setObservacao(jTextAreaObservacao.getText());

        CtrlObjetivos ctrlObjetivos = new CtrlObjetivos();
        if (ehNovoObjetivo) {
            objetivoCriadoEditado = ctrlObjetivos.criarNovoObjetivoMedicao(objetivo, projeto.getId());
            ctrlObjetivos.registrar(objetivo, Constantes.CADASTRO);
        } else {
            objetivoCriadoEditado = ctrlObjetivos.editarObjetivoMedicao(objetivo, projeto.getId());
            ctrlObjetivos.registrar(objetivo, Constantes.EDICAO);
        }
        if (objetivoCriadoEditado)
        this.dispose();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelCadastradoPor;
    private javax.swing.JLabel jLabelEditadoPor;
    private javax.swing.JLabel jLabelLevantamento;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButtonDeProjeto;
    private javax.swing.JRadioButton jRadioButtonOrganizacional;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextAreaAmbiente;
    private javax.swing.JTextArea jTextAreaFoco;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextArea jTextAreaProposito;
    private javax.swing.JTextField jTextFieldCadastradoPor;
    private javax.swing.JTextField jTextFieldEditadoPor;
    private javax.swing.JTextField jTextFieldLevantamento;
    private javax.swing.JTextField jTextFieldNomeObjetivo;
    // End of variables declaration//GEN-END:variables
}
