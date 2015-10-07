
package view.indicadores;

import controller.CtrlIndicador;
import controller.CtrlObjetivos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Entidademedida;
import model.Indicador;
import model.Objetivodequestao;
import model.Registroindicador;
import util.Constantes;
import util.Copia;
import util.Texto;

/**
 *
 * @author Spider-02
 */
public class ViewProjeto_NovoIndicador extends javax.swing.JDialog {

    private DefaultComboBoxModel comboboxModel;
    private final CtrlIndicador ctrlIndicador = new CtrlIndicador();
    private final CtrlObjetivos ctrlObjetivos = new CtrlObjetivos();
    private Registroindicador registro;

    private Indicador indicador;
    private boolean ehNovoIndicador;

    public ViewProjeto_NovoIndicador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public void showNovoIndicadorDialog() {
        indicador = new Indicador();

        ehNovoIndicador = true;

        this.jLabelUltimaEdicao.setVisible(false);
        this.jTextFieldUltimaEdicao.setVisible(false);
        popularComboboxEntidadeMedida();
        popularComboboxRelacionadoANecesssidade();
        jTextFieldCadastradoPor.setText(Copia.getUsuarioLogado().getNome() + ". Em: " + Texto.formataData(new Date()));
        this.setVisible(true);
    }

    public void showEditarIndicadorDialog(Indicador indicador){
        this.setTitle("Editar Indicador");
        this.indicador = new Indicador();
        this.indicador = indicador;
        
        ehNovoIndicador = false;
        
        popularComboboxEntidadeMedida();
        popularComboboxRelacionadoANecesssidade();
        preencherCampos();
        this.setVisible(true);
        
    }
    
    private void preencherCampos(){
        jTextFieldIndicador.setText(indicador.getNome());
        jTextAreaDescricao.setText(indicador.getDescricao());
        jTextFieldPontoDeVista.setText(indicador.getPontoDeVista());
        
        registro = new Registroindicador();
        registro = ctrlIndicador.buscarUltimoRegistroDoIndicador(indicador, Constantes.CADASTRO);
        jTextFieldCadastradoPor.setText(registro.getNomeUsuario() + " Em: " + Texto.formataData(registro.getData()));
        
        registro = new Registroindicador();
        registro = ctrlIndicador.buscarUltimoRegistroDoIndicador(indicador, Constantes.EDICAO);
        if(registro != null){
            jTextFieldUltimaEdicao.setText(registro.getNomeUsuario() + " Em: " + Texto.formataData(registro.getData()));
        } else {
            jLabelUltimaEdicao.setVisible(false);
            jTextFieldUltimaEdicao.setVisible(false);
        }
        
        jTextFieldMnemonico.setText(indicador.getMnemonico());
        jComboBoxEntidade.setSelectedItem(indicador.getEntidadeDeMedida());
        jTextFieldPropriedade.setText(indicador.getPropriedadeDeMedidade());
        jComboBoxRelacionado.setSelectedItem(indicador.getObjetivoDeQuestaoid().getNome());
        jTextAreaObservacao.setText(indicador.getObservacao());
    }
    
    private void popularComboboxRelacionadoANecesssidade() {
        comboboxModel = new DefaultComboBoxModel();

        comboboxModel.addElement("-Selecione uma Necessidade de Informação-");
        List<Objetivodequestao> listaQuestoes = ctrlObjetivos.getQuestoesDoProjeto(Copia.getProjetoSelecionado().getId());
        for (int i = 0; i < listaQuestoes.size(); i++) {
            if (listaQuestoes.get(i).getIndicadorList().isEmpty()){
                comboboxModel.addElement(listaQuestoes.get(i).getNome());
            } else if (!ehNovoIndicador) {
               comboboxModel.addElement(listaQuestoes.get(i).getNome()); 
            }
        }
        jComboBoxRelacionado.setModel(comboboxModel);
    }

    private void popularComboboxEntidadeMedida() {
        comboboxModel = new DefaultComboBoxModel();

        comboboxModel.addElement("-Selecione uma Entidade Medida-");
        List<Entidademedida> listaEntidadeMedida = ctrlIndicador.buscarListaEntidadeMedidas(Copia.getProjetoSelecionado().getId());
        for (int i = 0; i < listaEntidadeMedida.size(); i++) {
            comboboxModel.addElement(listaEntidadeMedida.get(i).getNome());
        }
        comboboxModel.addElement("Cadastrar um novo?");
        jComboBoxEntidade.setModel(comboboxModel);
    }

    private boolean validarCampos() {
        int cont = 0;
        String mensagem = null;

        if (jTextFieldIndicador.getText().isEmpty()) {
            mensagem = "Campo \"Indicador\" não pode ser vazio.";
            cont++;
        }
        if (jTextAreaDescricao.getText().isEmpty()) {
            mensagem = "Campo \"Descrição\" não pode ser vazio.";
            cont++;
        }
        if (jTextFieldPontoDeVista.getText().isEmpty()) {
            mensagem = "Campo \"Ponto de vista\" não pode ser vazio.";
            cont++;
        }
        if (jTextFieldMnemonico.getText().isEmpty()) {
            mensagem = "Campo \"Mnemônico\" não pode ser vazio.";
            cont++;
        }
        if (jTextFieldPropriedade.getText().isEmpty()) {
            mensagem = "Campo \"Propiedade\" não pode ser vazio.";
            cont++;
        }
        if (jComboBoxEntidade.getSelectedItem() == "-Selecione uma entidade medida-") {
            mensagem = "É necessário selecionar uma \"Entidade\" no Combobox.";
            cont++;
        }
        if (jComboBoxRelacionado.getSelectedItem() == "-Selecione uma Necessidade de Informação-") {
            mensagem = "É necessário selecionar uma \"Necessidade\" no Combobox.";
            cont++;
        }

        if (cont == 0) {
            return true;
        } else if (cont == 1) {
            JOptionPane.showMessageDialog(null, mensagem);
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Mais de um campo estão vazios ou inválidos.");
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldIndicador = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldPontoDeVista = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldMnemonico = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldPropriedade = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jComboBoxRelacionado = new javax.swing.JComboBox();
        jLabelCadastradoPor = new javax.swing.JLabel();
        jTextFieldCadastradoPor = new javax.swing.JTextField();
        jLabelUltimaEdicao = new javax.swing.JLabel();
        jTextFieldUltimaEdicao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jComboBoxEntidade = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Novo Indicador");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Indicador:");

        jLabel2.setText("Descrição:");

        jLabel3.setText("Ponto de Vista:");

        jLabel5.setText("Mnemônico:");

        jLabel6.setText("Entidade Medida:");

        jLabel7.setText("Propriedade Medida:");

        jLabel8.setText("<html> \nRelacionado a <br>\nNecessidade de Informação:\n</html>");

        jLabel9.setText("Observação:");

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

        jLabelCadastradoPor.setText("Cadastrado Por:");

        jTextFieldCadastradoPor.setEditable(false);
        jTextFieldCadastradoPor.setBackground(new java.awt.Color(204, 204, 204));

        jLabelUltimaEdicao.setText("Ultima Edição:");

        jTextFieldUltimaEdicao.setEditable(false);
        jTextFieldUltimaEdicao.setBackground(new java.awt.Color(204, 204, 204));

        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setLineWrap(true);
        jTextAreaDescricao.setRows(5);
        jTextAreaDescricao.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextAreaDescricao);

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setLineWrap(true);
        jTextAreaObservacao.setRows(5);
        jTextAreaObservacao.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextAreaObservacao);

        jComboBoxEntidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEntidadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabelUltimaEdicao)
                            .addComponent(jLabelCadastradoPor)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jTextFieldMnemonico)
                                    .addComponent(jTextFieldUltimaEdicao)
                                    .addComponent(jTextFieldCadastradoPor)
                                    .addComponent(jTextFieldPontoDeVista)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                                    .addComponent(jTextFieldIndicador)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldPropriedade, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBoxRelacionado, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 2, Short.MAX_VALUE)))
                                .addGap(11, 11, 11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBoxEntidade, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCancelar)
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldIndicador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPontoDeVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCadastradoPor)
                    .addComponent(jTextFieldCadastradoPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUltimaEdicao)
                    .addComponent(jTextFieldUltimaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldMnemonico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxEntidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPropriedade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxRelacionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (!validarCampos()) {
            return;
        }

        indicador.setNome(jTextFieldIndicador.getText());
        indicador.setDescricao(jTextAreaDescricao.getText());
        indicador.setPontoDeVista(jTextFieldPontoDeVista.getText());
        indicador.setMnemonico(jTextFieldMnemonico.getText());
        indicador.setEntidadeDeMedida(jComboBoxEntidade.getSelectedItem().toString());
        indicador.setPropriedadeDeMedidade(jTextFieldPropriedade.getText());
        Objetivodequestao objetivodequestao = ctrlObjetivos.buscaObjetivoDeQuestaoDoProjeto(jComboBoxRelacionado.getSelectedItem().toString(), Copia.getProjetoSelecionado().getId());
        indicador.setObjetivoDeQuestaoid(objetivodequestao);
        indicador.setObservacao(jTextAreaObservacao.getText());

        boolean feito = false;
        if (ehNovoIndicador) {
            feito = ctrlIndicador.criarNovoIndicador(indicador);
        } else {
            feito = ctrlIndicador.editarIndicadorCompleto(indicador);
        }
        if (feito) {
            this.dispose();
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jComboBoxEntidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEntidadeActionPerformed
        if (jComboBoxEntidade.getSelectedItem() == "Cadastrar um novo?") {
            viewProjeto_EntidadeMedida viewProjeto_EntidadeMedida = new viewProjeto_EntidadeMedida(null, true);
            viewProjeto_EntidadeMedida.setVisible(true);
            popularComboboxEntidadeMedida();
        }
    }//GEN-LAST:event_jComboBoxEntidadeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_NovoIndicador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_NovoIndicador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_NovoIndicador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_NovoIndicador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewProjeto_NovoIndicador dialog = new ViewProjeto_NovoIndicador(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox jComboBoxEntidade;
    private javax.swing.JComboBox jComboBoxRelacionado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCadastradoPor;
    private javax.swing.JLabel jLabelUltimaEdicao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldCadastradoPor;
    private javax.swing.JTextField jTextFieldIndicador;
    private javax.swing.JTextField jTextFieldMnemonico;
    private javax.swing.JTextField jTextFieldPontoDeVista;
    private javax.swing.JTextField jTextFieldPropriedade;
    private javax.swing.JTextField jTextFieldUltimaEdicao;
    // End of variables declaration//GEN-END:variables
}
