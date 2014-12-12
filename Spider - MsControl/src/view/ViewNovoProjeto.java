package view;

import controller.CtrlProjeto;
import javax.swing.JOptionPane;

public class ViewNovoProjeto extends javax.swing.JDialog {

    public ViewNovoProjeto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setLocationRelativeTo(null);
        setVisible(true);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvarProjeto)
                        .addGap(7, 7, 7)
                        .addComponent(jButtonCancelarProjeto))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNomeProjeto)
                            .addComponent(jLabelDescricaoProjeto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNomeProjeto, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                            .addComponent(jScrollPaneDescricaoProjeto))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeProjeto)
                    .addComponent(jTextFieldNomeProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneDescricaoProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDescricaoProjeto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvarProjeto)
                    .addComponent(jButtonCancelarProjeto))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarProjetoActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarProjetoActionPerformed

    private void jButtonSalvarProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarProjetoActionPerformed
       
        CtrlProjeto ctrlProjeto = new CtrlProjeto();
        String nomeProjeto = jTextFieldNomeProjeto.getText();
        String descricao = jTextAreaDescricaoProjeto.getText();
                
        if (nomeProjeto.equals("") || descricao.equals("") ) {
            JOptionPane.showMessageDialog(null, "Por favor, é obrigatório o preenchimentos de todos os campos.");
            return;
        }
        if((ctrlProjeto.inserirProjeto(nomeProjeto, descricao)) == true )
            this.dispose();
        
    }//GEN-LAST:event_jButtonSalvarProjetoActionPerformed

    private void jTextFieldNomeProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeProjetoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeProjetoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelarProjeto;
    private javax.swing.JButton jButtonSalvarProjeto;
    private javax.swing.JLabel jLabelDescricaoProjeto;
    private javax.swing.JLabel jLabelNomeProjeto;
    private javax.swing.JScrollPane jScrollPaneDescricaoProjeto;
    private javax.swing.JTextArea jTextAreaDescricaoProjeto;
    private javax.swing.JTextField jTextFieldNomeProjeto;
    // End of variables declaration//GEN-END:variables
}
