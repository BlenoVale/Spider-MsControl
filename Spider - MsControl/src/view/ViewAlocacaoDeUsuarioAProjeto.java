package view;

import jpa.PerfilJpaController;
import jpa.ProjetoJpaController;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.Perfil;
import model.Projeto;
import util.Conexao;

/**
 * Classe usada para direcionar um usario a um projeto e lhe dar um perfil de
 * acesso dentro do projeto
 *
 * @author Dan Jhonatan
 */
public final class ViewAlocacaoDeUsuarioAProjeto extends javax.swing.JDialog {

    private ViewNovoUsuario paiNovoUsuario = null;
    private ViewEspecificacoesDeUsuario paiEspecUsuario = null;
    private final ProjetoJpaController projetoJpa = new ProjetoJpaController(Conexao.conectar());
    private final PerfilJpaController perfilJpa = new PerfilJpaController(Conexao.conectar());

    public ViewAlocacaoDeUsuarioAProjeto(java.awt.Frame parent, boolean modal, ViewNovoUsuario pai) {
        super(parent, modal);
        initComponents();
        this.paiNovoUsuario = pai;

        this.setLocationRelativeTo(null);
        atualizaComboBox();
        this.setVisible(true);
    }

    public ViewAlocacaoDeUsuarioAProjeto(java.awt.Frame parent, boolean modal, ViewEspecificacoesDeUsuario pai) {
        super(parent, modal);
        initComponents();
        this.paiEspecUsuario = pai;

        this.setLocationRelativeTo(null);
        atualizaComboBox();

        this.setVisible(true);
    }

    public void atualizaComboBox() {
        List<Projeto> projetoList = projetoJpa.findProjetoEntities();
        List<Perfil> perfilList = perfilJpa.findPerfilEntities();

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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alocar usuário a um projeto");
        setResizable(false);

        jComboBoxProjeto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Alocar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (paiNovoUsuario != null)
            paiNovoUsuario.addLinhaTabela(jComboBoxProjeto.getSelectedItem().toString(), jComboBoxPerfil.getSelectedItem().toString());
        else if (paiEspecUsuario != null)
            paiEspecUsuario.addLinhaTabela(jComboBoxProjeto.getSelectedItem().toString(), jComboBoxPerfil.getSelectedItem().toString());

        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ViewAlocacaoDeUsuarioAProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ViewAlocacaoDeUsuarioAProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ViewAlocacaoDeUsuarioAProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ViewAlocacaoDeUsuarioAProjeto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ViewAlocacaoDeUsuarioAProjeto dialog = new ViewAlocacaoDeUsuarioAProjeto(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBoxPerfil;
    private javax.swing.JComboBox jComboBoxProjeto;
    // End of variables declaration//GEN-END:variables
}
