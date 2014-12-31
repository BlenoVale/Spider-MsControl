package view;

import controller.CtrlProjeto;
import controller.CtrlUsuario;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import model.Usuario;

public class ViewPrincipal extends javax.swing.JFrame {

    private Usuario usuario_logado = new Usuario();
    private final CtrlProjeto ctrlProjeto = new CtrlProjeto();
    private final CtrlUsuario ctrlUsuario = new CtrlUsuario();
    private DefaultComboBoxModel comboboxModel;
    private String perfil_selecionado;

    private final ViewGerenciarProjetos viewGerenciarProjetos = new ViewGerenciarProjetos();
    private final ViewGerenciarUsuarios viewGerenciarUsuarios = new ViewGerenciarUsuarios();
    private final ViewPermissoesDePerfis viewPermissoesDeUsuarios = new ViewPermissoesDePerfis();

    private ViewPrincipal() {

    }

    public ViewPrincipal(Usuario usuario_logado) {
        initComponents();

        this.usuario_logado = usuario_logado;
        jLabeBemVindo.setText("Bem vindo(a), " + usuario_logado.getLogin());
        popularComboboxDeProjetos();

        this.setLocationRelativeTo(null);
        this.iniciarTelas();
    }

    private void atualizaDadosDaTelaPrincipal() {
        this.usuario_logado = this.ctrlUsuario.buscarUsuarioPeloLogin(this.usuario_logado.getLogin());
        jLabeBemVindo.setText("Bem vindo(a), " + usuario_logado.getLogin());
        popularComboboxDeProjetos();
    }

    private void popularComboboxDeProjetos() {
        this.comboboxModel = new DefaultComboBoxModel();
        this.comboboxModel.addElement("--Selecione um Projeto--");
        List<String> lista_nomeProjetos = new ArrayList<>();
        lista_nomeProjetos = ctrlProjeto.buscarProjetosDoUsuario(this.usuario_logado.getId());
        for (int i = 0; i < lista_nomeProjetos.size(); i++) {
            this.comboboxModel.addElement(lista_nomeProjetos.get(i));
        }
        jComboBoxSelecaoDeProjeto.setModel(this.comboboxModel);
    }

    public Usuario getUsuarioLogado() {
        return this.usuario_logado;
    }

    private void eventosComboboxProjeto() {
        if (jComboBoxSelecaoDeProjeto.getSelectedItem() != "--Selecione um Projeto--") {
            List<String> perfis = new ArrayList<>();
            for (int i = 0; i < this.usuario_logado.getAcessaList().size(); i++) {
                if (this.usuario_logado.getAcessaList().get(i).getProjeto().getNome().equals(jComboBoxSelecaoDeProjeto.getSelectedItem().toString())) {
                    perfis.add(this.usuario_logado.getAcessaList().get(i).getPerfil().getNome());
                }
            }

            if (perfis.size() == 1) {
                this.perfil_selecionado = perfis.get(0);
                System.out.println("perfil selecionado: " + this.perfil_selecionado);
            } else {
                ViewSelecaoDePerfilAoEscolherProjeto viewSelecaoDePerfilAoEscolherProjeto = new ViewSelecaoDePerfilAoEscolherProjeto(this, rootPaneCheckingEnabled);
                viewSelecaoDePerfilAoEscolherProjeto.populaComboboxDePerfis(perfis);
                viewSelecaoDePerfilAoEscolherProjeto.setVisible(true);
                this.perfil_selecionado = viewSelecaoDePerfilAoEscolherProjeto.perfilEscolhido();
                System.out.println("perfil selecionado: " + this.perfil_selecionado);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabeBemVindo = new javax.swing.JLabel();
        jComboBoxSelecaoDeProjeto = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButtonNovoProjeto = new javax.swing.JButton();
        jButtonAtualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jDesktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemArquivoNovoProjeto = new javax.swing.JMenuItem();
        jMenuItemArquivoNovoUsuario = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemArquivoDesconectar = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemGerenciarProjetos = new javax.swing.JMenuItem();
        jMenuItemGerenciarUsuarios = new javax.swing.JMenuItem();
        jMenuItemGerenciarPermissoesDePerfil = new javax.swing.JMenuItem();
        jMenuItemGerenciarConta = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemSobreSpider = new javax.swing.JMenuItem();
        jMenuItemSobreAjuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SPIDER - MsControl");
        setMinimumSize(new java.awt.Dimension(1024, 600));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabeBemVindo.setText("Bem vindo");

        jComboBoxSelecaoDeProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSelecaoDeProjetoActionPerformed(evt);
            }
        });

        jLabel2.setText("Projeto");

        jButtonNovoProjeto.setText("Novo projeto");
        jButtonNovoProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoProjetoActionPerformed(evt);
            }
        });

        jButtonAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh3.png"))); // NOI18N
        jButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonNovoProjeto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAtualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 619, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabeBemVindo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxSelecaoDeProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabeBemVindo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxSelecaoDeProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonNovoProjeto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Nome do Projeto");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Objetivos");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Necessidade de informação");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Objetivo da Medição");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Medidas");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Acompanhamento");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Análise");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Aprovação");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Coleta");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Definição");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Procedimentos");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Análise");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Coleta");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Artefatos");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Plano de Medição");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Relatório");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Resultados");
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu3.setText("Arquivo");

        jMenuItemArquivoNovoProjeto.setText("Novo projeto");
        jMenuItemArquivoNovoProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemArquivoNovoProjetoActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemArquivoNovoProjeto);

        jMenuItemArquivoNovoUsuario.setText("Novo usuário");
        jMenuItemArquivoNovoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemArquivoNovoUsuarioActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemArquivoNovoUsuario);
        jMenu3.add(jSeparator1);

        jMenuItemArquivoDesconectar.setText("Desconectar");
        jMenuItemArquivoDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemArquivoDesconectarActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemArquivoDesconectar);

        jMenuBar1.add(jMenu3);

        jMenu1.setText("Gerenciar");

        jMenuItemGerenciarProjetos.setText("Projetos");
        jMenuItemGerenciarProjetos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGerenciarProjetosActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemGerenciarProjetos);

        jMenuItemGerenciarUsuarios.setText("Usuários");
        jMenuItemGerenciarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGerenciarUsuariosActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemGerenciarUsuarios);

        jMenuItemGerenciarPermissoesDePerfil.setText("Permissões de perfil");
        jMenuItemGerenciarPermissoesDePerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGerenciarPermissoesDePerfilActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemGerenciarPermissoesDePerfil);

        jMenuItemGerenciarConta.setText("Conta");
        jMenuItemGerenciarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGerenciarContaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemGerenciarConta);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sobre");

        jMenuItemSobreSpider.setText("Spider - MsControl");
        jMenu2.add(jMenuItemSobreSpider);

        jMenuItemSobreAjuda.setText("Ajuda");
        jMenu2.add(jMenuItemSobreAjuda);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemGerenciarProjetosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGerenciarProjetosActionPerformed
        trocaTelas(viewGerenciarProjetos);
    }//GEN-LAST:event_jMenuItemGerenciarProjetosActionPerformed

    private void jMenuItemGerenciarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGerenciarUsuariosActionPerformed
        trocaTelas(viewGerenciarUsuarios);
    }//GEN-LAST:event_jMenuItemGerenciarUsuariosActionPerformed

    private void jMenuItemGerenciarPermissoesDePerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGerenciarPermissoesDePerfilActionPerformed
        trocaTelas(viewPermissoesDeUsuarios);
    }//GEN-LAST:event_jMenuItemGerenciarPermissoesDePerfilActionPerformed

    private void jMenuItemArquivoNovoProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemArquivoNovoProjetoActionPerformed
        novoProjeto();
    }//GEN-LAST:event_jMenuItemArquivoNovoProjetoActionPerformed

    private void jButtonNovoProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoProjetoActionPerformed
        novoProjeto();
    }//GEN-LAST:event_jButtonNovoProjetoActionPerformed

    private void jMenuItemArquivoNovoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemArquivoNovoUsuarioActionPerformed
        novoUsuario();
    }//GEN-LAST:event_jMenuItemArquivoNovoUsuarioActionPerformed

    private void jComboBoxSelecaoDeProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSelecaoDeProjetoActionPerformed
        eventosComboboxProjeto();
    }//GEN-LAST:event_jComboBoxSelecaoDeProjetoActionPerformed

    private void jMenuItemGerenciarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGerenciarContaActionPerformed
        ViewGerenciarConta viewGerenciarConta = new ViewGerenciarConta(this, rootPaneCheckingEnabled, this.getUsuarioLogado());
    }//GEN-LAST:event_jMenuItemGerenciarContaActionPerformed

    private void jMenuItemArquivoDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemArquivoDesconectarActionPerformed
        ViewLogin viewLogin = new ViewLogin();
        viewLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemArquivoDesconectarActionPerformed

    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        atualizaDadosDaTelaPrincipal();
    }//GEN-LAST:event_jButtonAtualizarActionPerformed

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
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewPrincipal().setVisible(true);
            }
        });
    }

    private void novoProjeto() {
        ViewDadosDoProjeto novoProjeto = new ViewDadosDoProjeto(this, rootPaneCheckingEnabled);
        novoProjeto.setVisible(true);
        popularComboboxDeProjetos();
    }

    private void novoUsuario() {
        ViewNovoUsuario novoUsuario = new ViewNovoUsuario(this, rootPaneCheckingEnabled);
    }

    private void iniciarTelas() {
        jDesktopPane.add(viewGerenciarProjetos);
        jDesktopPane.add(viewGerenciarUsuarios);
        jDesktopPane.add(viewPermissoesDeUsuarios);

        try {
            viewGerenciarProjetos.setMaximum(true);
            viewGerenciarUsuarios.setMaximum(true);
            viewPermissoesDeUsuarios.setMaximum(true);
        } catch (PropertyVetoException e) {
            System.err.println(" Exception maximizar internal\n " + e);
        }
    }

    protected void trocaTelas(JInternalFrame tela) {

        viewGerenciarProjetos.setVisible(false);
        viewGerenciarUsuarios.setVisible(false);
        viewPermissoesDeUsuarios.setVisible(false);

        tela.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JButton jButtonNovoProjeto;
    private javax.swing.JComboBox jComboBoxSelecaoDeProjeto;
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JLabel jLabeBemVindo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemArquivoDesconectar;
    private javax.swing.JMenuItem jMenuItemArquivoNovoProjeto;
    private javax.swing.JMenuItem jMenuItemArquivoNovoUsuario;
    private javax.swing.JMenuItem jMenuItemGerenciarConta;
    private javax.swing.JMenuItem jMenuItemGerenciarPermissoesDePerfil;
    private javax.swing.JMenuItem jMenuItemGerenciarProjetos;
    private javax.swing.JMenuItem jMenuItemGerenciarUsuarios;
    private javax.swing.JMenuItem jMenuItemSobreAjuda;
    private javax.swing.JMenuItem jMenuItemSobreSpider;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

}
