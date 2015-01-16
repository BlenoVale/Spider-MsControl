package view.gerencia;

import facade.FacadeJpa;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.Perfil;
import model.Projeto;

/**
 * Classe usada para direcionar um usario a um projeto e lhe dar um perfil de
 * acesso dentro do projeto
 *
 * @author Dan Jhonatan
 */
public final class ViewAlocacaoDeUsuarioAProjeto extends javax.swing.JDialog {

    private final String escolha[] = new String[]{null, null};
    private final FacadeJpa jpa = FacadeJpa.getInstance();

    public ViewAlocacaoDeUsuarioAProjeto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);
        atualizaComboBox();
    }

    public String[] showDialog() {
        this.setVisible(true);
        return escolha;
    }

    public void atualizaComboBox() {
        List<Projeto> projetoList = jpa.getProjetoJpa().findProjetoEntities();
        List<Perfil> perfilList = jpa.getPerfilJpa().findPerfilEntities();

        String nomeProjeto[] = new String[projetoList.size()];
        String nomePerfil[] = new String[perfilList.size()];

        for (int i = 0; i < projetoList.size(); i++)
            nomeProjeto[i] = projetoList.get(i).getNome();
        for (int i = 0; i < perfilList.size(); i++)
            nomePerfil[i] = perfilList.get(i).getNome();

        DefaultComboBoxModel modelProjeto = new DefaultComboBoxModel(nomeProjeto);
        DefaultComboBoxModel modelPerfil = new DefaultComboBoxModel(nomePerfil);

        jComboBoxProjeto.setModel(modelProjeto);
        jComboBoxPerfil.setModel(modelPerfil);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxProjeto = new javax.swing.JComboBox();
        jComboBoxPerfil = new javax.swing.JComboBox();
        jButtonAlocar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alocar usuário a um projeto");
        setResizable(false);

        jComboBoxProjeto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonAlocar.setText("Alocar");
        jButtonAlocar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlocarActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAlocar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBoxProjeto, 0, 297, Short.MAX_VALUE)
                        .addComponent(jComboBoxPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlocar)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAlocarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlocarActionPerformed
        escolha[0] = jComboBoxProjeto.getSelectedItem().toString();
        escolha[1] = jComboBoxPerfil.getSelectedItem().toString();

        this.dispose();
    }//GEN-LAST:event_jButtonAlocarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAlocar;
    private javax.swing.JComboBox jComboBoxPerfil;
    private javax.swing.JComboBox jComboBoxProjeto;
    // End of variables declaration//GEN-END:variables
}
