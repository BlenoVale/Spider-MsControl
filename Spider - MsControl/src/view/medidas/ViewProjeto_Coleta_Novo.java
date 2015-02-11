package view.medidas;

/**
 *
 * @author Paulo
 */
public class ViewProjeto_Coleta_Novo extends javax.swing.JDialog {

    public ViewProjeto_Coleta_Novo(java.awt.Frame parent, boolean  modal) {
        super(parent, modal);
        initComponents();
        
        this.setLocationRelativeTo(null);
    }

    public void showNovaColeta() {
        this.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelMedida = new javax.swing.JLabel();
        jComboBoxMedida = new javax.swing.JComboBox();
        jLabelTipoColeta = new javax.swing.JLabel();
        jTextFieldTipoColeta = new javax.swing.JTextField();
        jLabelObservacoes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObservacoes = new javax.swing.JTextArea();
        jButtonSalvarColeta = new javax.swing.JButton();
        jButtonCancelarColeta = new javax.swing.JButton();
        jLabelEntradaDados = new javax.swing.JLabel();
        jTextFieldEntradaDados = new javax.swing.JTextField();
        jLabelImportarDados = new javax.swing.JLabel();
        jTextFieldImportarDados = new javax.swing.JTextField();
        jButtonProcurarPlanilha = new javax.swing.JButton();

        setTitle("Cadastrar nova coleta");

        jLabelMedida.setText("Medida:");

        jComboBoxMedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelTipoColeta.setText("Tipo Coleta:");

        jTextFieldTipoColeta.setEnabled(false);

        jLabelObservacoes.setText("Observações:");

        jTextAreaObservacoes.setColumns(20);
        jTextAreaObservacoes.setRows(5);
        jScrollPane1.setViewportView(jTextAreaObservacoes);

        jButtonSalvarColeta.setText("Salvar");

        jButtonCancelarColeta.setText("Cancelar");
        jButtonCancelarColeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarColetaActionPerformed(evt);
            }
        });

        jLabelEntradaDados.setText("Entrada de dados:");

        jLabelImportarDados.setText("Importar dados:");

        jTextFieldImportarDados.setEnabled(false);
        jTextFieldImportarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldImportarDadosActionPerformed(evt);
            }
        });

        jButtonProcurarPlanilha.setText("Procurar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelImportarDados)
                                .addGap(31, 31, 31)
                                .addComponent(jTextFieldImportarDados))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelEntradaDados)
                                .addGap(20, 20, 20)
                                .addComponent(jTextFieldEntradaDados))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTipoColeta)
                                    .addComponent(jLabelMedida))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBoxMedida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(214, 214, 214))
                                    .addComponent(jTextFieldTipoColeta))
                                .addGap(2, 2, 2)))
                        .addGap(0, 0, 0)
                        .addComponent(jButtonProcurarPlanilha)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelObservacoes)
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSalvarColeta)
                .addGap(18, 18, 18)
                .addComponent(jButtonCancelarColeta)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMedida)
                    .addComponent(jComboBoxMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTipoColeta)
                    .addComponent(jTextFieldTipoColeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEntradaDados)
                    .addComponent(jTextFieldEntradaDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelImportarDados)
                    .addComponent(jTextFieldImportarDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonProcurarPlanilha))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabelObservacoes)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvarColeta)
                    .addComponent(jButtonCancelarColeta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
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

    private void jTextFieldImportarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldImportarDadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldImportarDadosActionPerformed

    private void jButtonCancelarColetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarColetaActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarColetaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelarColeta;
    private javax.swing.JButton jButtonProcurarPlanilha;
    private javax.swing.JButton jButtonSalvarColeta;
    private javax.swing.JComboBox jComboBoxMedida;
    private javax.swing.JLabel jLabelEntradaDados;
    private javax.swing.JLabel jLabelImportarDados;
    private javax.swing.JLabel jLabelMedida;
    private javax.swing.JLabel jLabelObservacoes;
    private javax.swing.JLabel jLabelTipoColeta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaObservacoes;
    private javax.swing.JTextField jTextFieldEntradaDados;
    private javax.swing.JTextField jTextFieldImportarDados;
    private javax.swing.JTextField jTextFieldTipoColeta;
    // End of variables declaration//GEN-END:variables
}
