package view;

import controller.CtrlProjeto;
import javax.swing.JOptionPane;
import model.Projeto;

public class ViewDadosDoProjeto extends javax.swing.JDialog {

    private final CtrlProjeto ctrlProjeto = new CtrlProjeto();
    private Projeto projeto = null;

    public ViewDadosDoProjeto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNomeProjeto = new javax.swing.JLabel();
        jTextFieldNomeProjeto = new javax.swing.JTextField();
        jButtonCancelarProjeto = new javax.swing.JButton();
        jButtonSalvarProjeto = new javax.swing.JButton();
        jScrollPaneDescricaoProjeto = new javax.swing.JScrollPane();
        jTextAreaDescricaoProjeto = new javax.swing.JTextArea();
        jLabelDescricaoProjeto = new javax.swing.JLabel();
        jButtonSalvarEdicao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo projeto");

        jLabelNomeProjeto.setText("Nome do projeto: ");

        jTextFieldNomeProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeProjetoActionPerformed(evt);
            }
        });

        jButtonCancelarProjeto.setText("Cancelar");
        jButtonCancelarProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarProjetoActionPerformed(evt);
            }
        });

        jButtonSalvarProjeto.setText("Salvar");
        jButtonSalvarProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarProjetoActionPerformed(evt);
            }
        });

        jTextAreaDescricaoProjeto.setColumns(20);
        jTextAreaDescricaoProjeto.setRows(5);
        jScrollPaneDescricaoProjeto.setViewportView(jTextAreaDescricaoProjeto);

        jLabelDescricaoProjeto.setText("Descrição do Projeto:");

        jButtonSalvarEdicao.setText("Salvar edição do projeto");
        jButtonSalvarEdicao.setVisible(false);
        jButtonSalvarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarEdicaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNomeProjeto)
                            .addComponent(jLabelDescricaoProjeto))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNomeProjeto, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                            .addComponent(jScrollPaneDescricaoProjeto)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvarEdicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSalvarProjeto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelarProjeto)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeProjeto)
                    .addComponent(jTextFieldNomeProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneDescricaoProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDescricaoProjeto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelarProjeto)
                    .addComponent(jButtonSalvarProjeto)
                    .addComponent(jButtonSalvarEdicao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void showEditarProjetoDialog(Projeto projeto) {
        this.projeto = projeto;
        this.setTitle("Edição do projeto \"" + projeto.getNome() +"\"");
        
        jButtonSalvarEdicao.setVisible(true);
        jButtonSalvarProjeto.setVisible(false);

        preencherCampos(projeto);
        this.setVisible(true);
    }

    private void preencherCampos(Projeto projeto) {
        jTextFieldNomeProjeto.setText(projeto.getNome());
        jTextAreaDescricaoProjeto.setText(projeto.getDescricao());
    }

    private void jButtonCancelarProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarProjetoActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarProjetoActionPerformed

    private void jButtonSalvarProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarProjetoActionPerformed

        String nomeProjeto = jTextFieldNomeProjeto.getText();
        String descricao = jTextAreaDescricaoProjeto.getText();

        if (checarCamposDigitados())
            if ((ctrlProjeto.inserirProjeto(nomeProjeto, descricao)) == true)
                this.dispose();

    }//GEN-LAST:event_jButtonSalvarProjetoActionPerformed

    private boolean checarCamposDigitados() {
        String nomeProjeto = jTextFieldNomeProjeto.getText();
        String descricao = jTextAreaDescricaoProjeto.getText();

        if (nomeProjeto.equals("") || descricao.equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor, é obrigatório o preenchimentos de todos os campos.");
            return false;
        } else
            return true;
    }

    private void jTextFieldNomeProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeProjetoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeProjetoActionPerformed

    private void jButtonSalvarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarEdicaoActionPerformed
        String nomeProjeto = jTextFieldNomeProjeto.getText();
        String descricao = jTextAreaDescricaoProjeto.getText();

        if (checarCamposDigitados()) {
            projeto.setNome(nomeProjeto);
            projeto.setDescricao(descricao);
            
            ctrlProjeto.editarProjeto(projeto);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonSalvarEdicaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelarProjeto;
    private javax.swing.JButton jButtonSalvarEdicao;
    private javax.swing.JButton jButtonSalvarProjeto;
    private javax.swing.JLabel jLabelDescricaoProjeto;
    private javax.swing.JLabel jLabelNomeProjeto;
    private javax.swing.JScrollPane jScrollPaneDescricaoProjeto;
    private javax.swing.JTextArea jTextAreaDescricaoProjeto;
    private javax.swing.JTextField jTextFieldNomeProjeto;
    // End of variables declaration//GEN-END:variables
}
