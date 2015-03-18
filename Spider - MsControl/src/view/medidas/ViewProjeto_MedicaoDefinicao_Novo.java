package view.medidas;

import model.Medida;
import controller.CtrlMedida;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.Constantes;
import util.Copia;
import model.Registromedida;

import javax.swing.JOptionPane;
import util.Texto;

/**
 *
 * @author BlenoVale, DAN JHONATAN, paulo
 */
public class ViewProjeto_MedicaoDefinicao_Novo extends javax.swing.JDialog {

    private Medida medida = new Medida();
    private boolean novaMedida;
    private List<Registromedida> registromedida;
    
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
        } else if (jTextFieldFaixaInicio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Faixa\" é obrigatório");
            return false;
        } else if (jTextFieldFaixaFim.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Faixa\" é obrigatório");
            return false;
        } else {
            return true;
        }
    }

    public void JTextFieldSomenteNumeros(java.awt.event.KeyEvent evt) {
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }

    public boolean validaFaixa() {
        double numInicio = Double.parseDouble(jTextFieldFaixaInicio.getText());
        double numFim = Double.parseDouble(jTextFieldFaixaFim.getText());

        if (numFim < numInicio) {
            return false;
        } else {
            return true;
        }
    }
    public void showCadastrarDialog(){
        this.setTitle("Editar definição medida");
        this.jLabel4CadastradoPor.setVisible(false);
        this.jLabelUltimaEdicao.setVisible(false);
        this.jTextFieldCadastradoPor.setVisible(false);
        this.jTextFieldUltimaEdicao.setVisible(false);
        
        novaMedida = true;
    }
    public  void showEditarDialog(Medida medida){
        
        this.setTitle("Editar definição medida");
        popularCamposEditarMedida(medida);
        popularCamposEditarMedidaRegistro(medida);
        
        novaMedida = false;
        
        
        
       
    }
    public void popularCamposEditarMedida(Medida medida){
        
        jTextFieldNomeMedida.setText(medida.getNome());
        jTextAreaDefinicao.setText(medida.getDefinicao());
        jTextFieldResponsavel.setText(medida.getPontoDeVista());
        jTextFieldMnemonico.setText(medida.getMnemonico());
        jTextFieldEscala.setText(medida.getEscala());
        jTextFieldFaixaInicio.setText(String.valueOf(medida.getFaixaInicio()));
        jTextFieldFaixaFim.setText(String.valueOf(medida.getFaixaFim()));
        jTextAreaObservacoes.setText(medida.getObservacao());
        
    }
    public void popularCamposEditarMedidaRegistro(Medida medida){
        
        registromedida = new ArrayList<>();
        registromedida = facade.FacadeJpa.getInstance().getRegistroMedidaJpa().findRegistroByIdMedida(Constantes.CADASTRO, medida.getId());
        jTextFieldCadastradoPor.setText(registromedida.get(0).getNomeUsuario() + " Em " + Texto.formataData(registromedida.get(0).getData()));
        
        registromedida = new ArrayList<>();
        registromedida = facade.FacadeJpa.getInstance().getRegistroMedidaJpa().findRegistroByIdMedida(Constantes.EDICAO, medida.getId());
        
        if (registromedida.isEmpty()) {
            jTextFieldUltimaEdicao.setVisible(false);
            jLabelUltimaEdicao.setVisible(false);
        }else {
            jTextFieldUltimaEdicao.setText(registromedida.get(registromedida.size() -1).getNomeUsuario() + " Em " + Texto.formataData(registromedida.get(registromedida.size()-1).getData()));
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
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEscala = new javax.swing.JTextField();
        jTextFieldFaixaInicio = new javax.swing.JTextField();
        jTextFieldFaixaFim = new javax.swing.JTextField();
        jLabelDe = new javax.swing.JLabel();
        jLabelFaixa = new javax.swing.JLabel();
        jLabelAte = new javax.swing.JLabel();
        jLabel4CadastradoPor = new javax.swing.JLabel();
        jTextFieldCadastradoPor = new javax.swing.JTextField();
        jLabelUltimaEdicao = new javax.swing.JLabel();
        jTextFieldUltimaEdicao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro nova definição medida");
        setPreferredSize(new java.awt.Dimension(622, 500));
        setResizable(false);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(580, 470));

        Jpainel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Jpainel3.setPreferredSize(new java.awt.Dimension(500, 530));

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

        jLabel1.setText("Escala:");

        jTextFieldEscala.setToolTipText("Valores que podem ser atribuídos à medida (ex: número inteiros, números reais positivos, ...).");

        jTextFieldFaixaInicio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldFaixaInicio.setToolTipText("Limites de valores da escala definida de acordo com dados históricos ou com metas estabelecidas (ex: [0, 10], ...).");
        jTextFieldFaixaInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFaixaInicioKeyTyped(evt);
            }
        });

        jTextFieldFaixaFim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFaixaFimKeyTyped(evt);
            }
        });

        jLabelDe.setText("De:");

        jLabelFaixa.setText("Faixa:");

        jLabelAte.setText("Até:");

        jLabel4CadastradoPor.setText("Cadastrado por:");

        jTextFieldCadastradoPor.setEnabled(false);

        jLabelUltimaEdicao.setText("Última edição:");

        jTextFieldUltimaEdicao.setEnabled(false);

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
                            .addComponent(jLabelMnemonico, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4CadastradoPor)
                            .addComponent(jLabelUltimaEdicao)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldNomeMedida)
                        .addGap(142, 142, 142))
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpainel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldResponsavel)
                        .addGap(173, 173, 173))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpainel3Layout.createSequentialGroup()
                        .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldMnemonico, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUltimaEdicao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Jpainel3Layout.createSequentialGroup()
                                .addComponent(jLabelDe)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldFaixaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelAte)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldFaixaFim, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextFieldEscala, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCadastradoPor, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(173, 173, 173))))
            .addGroup(Jpainel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpainel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addComponent(jLabelObservacoes)
                        .addGap(71, 71, 71)
                        .addComponent(jScrollPane3))
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addComponent(jLabelFaixa)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(jTextFieldCadastradoPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4CadastradoPor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUltimaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUltimaEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMnemonico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMnemonico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFaixaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFaixaFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDe)
                    .addComponent(jLabelFaixa)
                    .addComponent(jLabelAte))
                .addGroup(Jpainel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Jpainel3Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabelObservacoes)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldFaixaFimKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFaixaFimKeyTyped
        JTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldFaixaFimKeyTyped

    private void jTextFieldFaixaInicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFaixaInicioKeyTyped
        JTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldFaixaInicioKeyTyped

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        CtrlMedida ctrlMedida = new CtrlMedida();

        if (!verificaCampos()) {
            return;
        }
        if (ctrlMedida.checkNomeMedida(jTextFieldNomeMedida.getText())) {
            JOptionPane.showMessageDialog(null, "Já existe uma medida com esse nome, escolha outro nome.", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (ctrlMedida.checkNomeMnemonico(jTextFieldMnemonico.getText())) {
            JOptionPane.showMessageDialog(null, "Já existe um mnemônico com esse nome, escolha outro nome.", "", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!validaFaixa()){
            JOptionPane.showMessageDialog(null, "A faixa de inicio não deve ser maior que a de fim.", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        medida.setProjetoId(Copia.getProjetoSelecionado().getId());
        medida.setNome(jTextFieldNomeMedida.getText());
        medida.setDefinicao(jTextAreaDefinicao.getText());
        medida.setPontoDeVista(jTextFieldResponsavel.getText());
        medida.setMnemonico(jTextFieldMnemonico.getText());
        medida.setEscala(jTextFieldEscala.getText());
        medida.setFaixaInicio(Double.parseDouble(jTextFieldFaixaInicio.getText()));
        medida.setFaixaFim(Double.parseDouble(jTextFieldFaixaFim.getText()));
        medida.setObservacao(jTextAreaObservacoes.getText());
        medida.setData(new Date());

        
        try {
            if (novaMedida) {
                ctrlMedida.criarNovaMedida(medida);
                ctrlMedida.registrarMedida(medida, Constantes.CADASTRO);
                JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
            }else {
                ctrlMedida.editarMedida(medida);
                ctrlMedida.registrarMedida(medida, Constantes.EDICAO);
                JOptionPane.showMessageDialog(null, "Editado com sucesso.");
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar");
        }
        this.dispose();
    }//GEN-LAST:event_jButtonSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpainel3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4CadastradoPor;
    private javax.swing.JLabel jLabelAte;
    private javax.swing.JLabel jLabelDe;
    private javax.swing.JLabel jLabelDefinicao;
    private javax.swing.JLabel jLabelFaixa;
    private javax.swing.JLabel jLabelMnemonico;
    private javax.swing.JLabel jLabelNomeMedida;
    private javax.swing.JLabel jLabelObservacoes;
    private javax.swing.JLabel jLabelResponsavel;
    private javax.swing.JLabel jLabelUltimaEdicao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextAreaDefinicao;
    private javax.swing.JTextArea jTextAreaObservacoes;
    private javax.swing.JTextField jTextFieldCadastradoPor;
    private javax.swing.JTextField jTextFieldEscala;
    private javax.swing.JTextField jTextFieldFaixaFim;
    private javax.swing.JTextField jTextFieldFaixaInicio;
    private javax.swing.JTextField jTextFieldMnemonico;
    private javax.swing.JTextField jTextFieldNomeMedida;
    private javax.swing.JTextField jTextFieldResponsavel;
    private javax.swing.JTextField jTextFieldUltimaEdicao;
    // End of variables declaration//GEN-END:variables
}
