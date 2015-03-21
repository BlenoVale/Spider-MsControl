/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.procedimentos;

import controller.CtrlProcedimentos;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Procedimentodecoleta;
import facade.FacadeJpa;
import java.util.Date;
import model.Medida;
import util.Copia;
import util.Texto;

/**
 *
 * @author Paulo
 */
public class ViewProjeto_ProcedimentoColetaNovo extends javax.swing.JDialog {

    /**
     * Creates new form ViewProjeto_ProcedimentoColetaNovo
     */
    private DefaultComboBoxModel comboBoxModelMedida;
    private Procedimentodecoleta procedimentodecoleta = new Procedimentodecoleta();
    private FacadeJpa jpa = FacadeJpa.getInstance();
    private boolean novoProcedimento;
    
    public ViewProjeto_ProcedimentoColetaNovo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.pack();
        
    }
    
    public boolean validaCampos() {
        if (jComboBoxMedida.getSelectedItem() == "-Selecione uma medida-") {
            JOptionPane.showMessageDialog(null, "Você deve selecionar uma medida");
            return false;
        } else if (jComboBoxPeriodicidade.getSelectedItem() == "-Selecione uma periodicidade-") {
            JOptionPane.showMessageDialog(null, "Você deve selecionar uma periodicidade");
            return false;
        } else if (jTextFieldResponsavelColeta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Responsável pela coleta\" é obrigatório");
            return false;
        } else if (jTextAreaPassosColeta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Passos coleta \" é obrigatório");
            return false;
        } else if (jTextFieldFerramentaUtilizada.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Ferramenta utilizada\" é obrigatório");
            return false;
        } else if (jTextFieldMomento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Momento \" é obrigatório");
            return false;
        } else if (jTextFieldFrequencia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Frequência\" é obrigatório");
            return false;
        } else if (!validaRadio()) {
            JOptionPane.showMessageDialog(null, "Você deve escolher um \"Tipo de coleta\"");
            return false;
        }        
        return true;
    }

    public boolean validaRadio() {
        if (jRadioButtonManual.isSelected()) {
            jRadioButtonPlanilha.setSelected(false);
            return true;
        } else if (jRadioButtonPlanilha.isSelected()) {
            jRadioButtonManual.setSelected(false);
            return true;
        } else {
            return false;
        }
    }

    public void popularComboBoxMedida() {
        
        comboBoxModelMedida = new DefaultComboBoxModel();
        comboBoxModelMedida.addElement("-Selecione uma medida-");
        List<Medida> medida = jpa.getMedidaJpa().findByProjeto(Copia.getProjetoSelecionado().getId());
        for (int i = 0; i < medida.size(); i++) {
            comboBoxModelMedida.addElement(medida.get(i).getNome());
        }
        jComboBoxMedida.setModel(comboBoxModelMedida);
    }
    public void showDialogCadastrar(){
        popularComboBoxMedida();        
        this.setTitle("Cadastrar procedimento de coleta");
        jLabelUltimaEdicao.setVisible(false);
        jTextFieldUltimaEdicao.setVisible(false);
        jTextFieldCadastradoPor.setText(Copia.getUsuarioLogado().getNome() + " " + Texto.formataData(new Date()));
        novoProcedimento = true;
    }
     public void JTextFieldSomenteNumeros(java.awt.event.KeyEvent evt) {
        String caracteres = "987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }
     public String pegarRadioSelecionado(){
         if (jRadioButtonManual.isSelected()) {
             return jRadioButtonManual.getText();
         } else {
             return jRadioButtonPlanilha.getText();
         }
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jRadioButtonManual = new javax.swing.JRadioButton();
        jRadioButtonPlanilha = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldResponsavelColeta = new javax.swing.JTextField();
        jComboBoxPeriodicidade = new javax.swing.JComboBox();
        jTextFieldMomento = new javax.swing.JTextField();
        jComboBoxMedida = new javax.swing.JComboBox();
        jTextFieldFerramentaUtilizada = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jTextFieldFrequencia = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaPassosColeta = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldCadastradoPor = new javax.swing.JTextField();
        jLabelUltimaEdicao = new javax.swing.JLabel();
        jTextFieldUltimaEdicao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setText("Medida:");

        jLabel2.setText("Responsável pela coleta:");

        jLabel3.setText("Momento:");

        jLabel4.setText("Periodicidade:");

        jLabel5.setText("Frequência:");

        jLabel6.setText("Passos da coleta:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo Coleta"));

        jRadioButtonManual.setText("Manual");
        jRadioButtonManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonManualActionPerformed(evt);
            }
        });

        jRadioButtonPlanilha.setText("Planilha Eletrônica");
        jRadioButtonPlanilha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPlanilhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jRadioButtonManual)
                .addGap(100, 100, 100)
                .addComponent(jRadioButtonPlanilha)
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonManual)
                    .addComponent(jRadioButtonPlanilha))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("Ferramenta utilizada:");

        jLabel9.setText("Observação:");

        jTextFieldResponsavelColeta.setToolTipText("Quem será responsável por realizar as coletas das medidas.");

        jComboBoxPeriodicidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Selecione uma periodicidade-", "Diária", "Semanal", "Mensal", "Bimestral", "Trimestral", "Semestral", "Anual" }));
        jComboBoxPeriodicidade.setToolTipText("De quanto em quanto tempo é coletado.");

        jTextFieldMomento.setToolTipText("Em que fase do projeto (ex: Requisitos, Desenvolvimento, Teste).");

        jComboBoxMedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextFieldFerramentaUtilizada.setToolTipText("Qual a ferramenta utilizada para realizar a coleta? (ex: redmine, junit).");

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setRows(5);
        jScrollPane1.setViewportView(jTextAreaObservacao);

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

        jTextFieldFrequencia.setToolTipText("Quantas vezes a coleta é realizada dentro do periodo (ex: 5 vezes dentro de um periodo).");
        jTextFieldFrequencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFrequenciaKeyTyped(evt);
            }
        });

        jTextAreaPassosColeta.setColumns(20);
        jTextAreaPassosColeta.setRows(5);
        jTextAreaPassosColeta.setToolTipText("Como a coleta será realizada?");
        jScrollPane2.setViewportView(jTextAreaPassosColeta);

        jLabel8.setText("Cadastrado por:");

        jTextFieldCadastradoPor.setEnabled(false);

        jLabelUltimaEdicao.setText("Última edição:");

        jTextFieldUltimaEdicao.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonCancelar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabelUltimaEdicao))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jTextFieldResponsavelColeta)
                                    .addComponent(jComboBoxPeriodicidade, 0, 306, Short.MAX_VALUE)
                                    .addComponent(jTextFieldMomento)
                                    .addComponent(jComboBoxMedida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldFrequencia)
                                    .addComponent(jTextFieldCadastradoPor)
                                    .addComponent(jTextFieldUltimaEdicao)))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldFerramentaUtilizada)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldResponsavelColeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldCadastradoPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUltimaEdicao)
                    .addComponent(jTextFieldUltimaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldMomento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxPeriodicidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldFrequencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldFerramentaUtilizada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar)))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        
        if (!validaCampos()) {
            return;
        }
        boolean save = false;
        Medida medida = jpa.getMedidaJpa().findByNomeAndProjeto(jComboBoxMedida.getSelectedItem().toString(), Copia.getProjetoSelecionado().getId());
        
        procedimentodecoleta.setData(new Date());
        procedimentodecoleta.setFerramentasUtilizada(jTextFieldFerramentaUtilizada.getText());
        procedimentodecoleta.setFrequencia(Integer.parseInt(jTextFieldFrequencia.getText()));
        procedimentodecoleta.setMedidaid(medida);
        procedimentodecoleta.setMomento(jTextFieldMomento.getText());
        procedimentodecoleta.setObservacao(jTextAreaObservacao.getText());
        procedimentodecoleta.setPassosColeta(jTextAreaPassosColeta.getText());
        procedimentodecoleta.setPeriodicidade(jComboBoxPeriodicidade.getSelectedItem().toString());
        procedimentodecoleta.setResponsavelPelaColeta(jTextFieldResponsavelColeta.getText());
        procedimentodecoleta.setTipoDeColeta(pegarRadioSelecionado()); 
        
        if (novoProcedimento) {
            CtrlProcedimentos ctrlProcedimentos = new CtrlProcedimentos();
            save = ctrlProcedimentos.criarProcedimentoColeta(procedimentodecoleta);
        }else {
            
        }
        
        if (save) {
            this.dispose();
        }
        
        

    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTextFieldFrequenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFrequenciaKeyTyped
        // TODO add your handling code here:
        JTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldFrequenciaKeyTyped

    private void jRadioButtonPlanilhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPlanilhaActionPerformed

        if(jRadioButtonPlanilha.isSelected())
            jRadioButtonManual.setSelected(false);
    }//GEN-LAST:event_jRadioButtonPlanilhaActionPerformed

    private void jRadioButtonManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonManualActionPerformed

        if(jRadioButtonManual.isSelected())
            jRadioButtonPlanilha.setSelected(false);
    }//GEN-LAST:event_jRadioButtonManualActionPerformed

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
            java.util.logging.Logger.getLogger(ViewProjeto_ProcedimentoColetaNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_ProcedimentoColetaNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_ProcedimentoColetaNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_ProcedimentoColetaNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewProjeto_ProcedimentoColetaNovo dialog = new ViewProjeto_ProcedimentoColetaNovo(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox jComboBoxMedida;
    private javax.swing.JComboBox jComboBoxPeriodicidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelUltimaEdicao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonManual;
    private javax.swing.JRadioButton jRadioButtonPlanilha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextArea jTextAreaPassosColeta;
    private javax.swing.JTextField jTextFieldCadastradoPor;
    private javax.swing.JTextField jTextFieldFerramentaUtilizada;
    private javax.swing.JTextField jTextFieldFrequencia;
    private javax.swing.JTextField jTextFieldMomento;
    private javax.swing.JTextField jTextFieldResponsavelColeta;
    private javax.swing.JTextField jTextFieldUltimaEdicao;
    // End of variables declaration//GEN-END:variables
}
