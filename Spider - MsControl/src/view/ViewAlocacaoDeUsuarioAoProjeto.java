package view;

public class ViewAlocacaoDeUsuarioAoProjeto extends javax.swing.JDialog {

    public ViewAlocacaoDeUsuarioAoProjeto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelProjeto = new javax.swing.JLabel();
        jLabelPerfil = new javax.swing.JLabel();
        jComboBoxProjeto = new javax.swing.JComboBox();
        jComboBoxPerfil = new javax.swing.JComboBox();
        jButtonAlocar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alocação de perfil");

        jLabelProjeto.setText("Projeto");

        jLabelPerfil.setText("Perfil");

        jComboBoxProjeto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--- Selecione um projeto ---", "Spider - MsConfig", "Spider - RM" }));

        jComboBoxPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--- Selecione um perfil ---", "Administração", "Gerencia" }));

        jButtonAlocar.setText("Alocar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelProjeto)
                    .addComponent(jLabelPerfil))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxProjeto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jButtonAlocar)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProjeto)
                    .addComponent(jComboBoxProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPerfil)
                    .addComponent(jComboBoxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAlocar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlocar;
    private javax.swing.JComboBox jComboBoxPerfil;
    private javax.swing.JComboBox jComboBoxProjeto;
    private javax.swing.JLabel jLabelPerfil;
    private javax.swing.JLabel jLabelProjeto;
    // End of variables declaration//GEN-END:variables
}
