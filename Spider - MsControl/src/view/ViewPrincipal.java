package view;

import view.gerencia.ViewPermissoesDePerfis;
import view.gerencia.ViewGerenciarProjetos;
import view.gerencia.ViewGerenciarConta;
import view.gerencia.ViewGerenciarUsuarios;
import view.gerencia.ViewDadosDoProjeto;
import view.gerencia.ViewNovoUsuario;
import view.procedimentos.ViewProjeto_ProcedimentoColeta;
import view.procedimentos.ViewProjeto_ProcedimentoAnalise;
import view.objetivos.ViewProjeto_ObjetivosQuestoes;
import view.objetivos.ViewProjeto_ObjetivosDeMedicao;
import controller.CtrlProjeto;
import controller.CtrlUsuario;
import facade.FacadeJpa;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import jpa.extensao.AcessaJpa;
import model.Projeto;
import model.Usuario;
import util.Copia;
import util.Observer;
import view.artefatos.ViewProjeto_PlanoDeMedicao;
import view.artefatos.ViewProjeto_Relatorio;
import view.medidas.ViewProjeto_MedicaoDefinicao;
import view.medidas.ViewProjeto_Analise;
import view.indicadores.ViewProjetoAprovacao;
import view.indicadores.ViewProjeto_Indicadores;
import view.medidas.ViewProjeto_Coleta;

public class ViewPrincipal extends javax.swing.JFrame {

    private Observer observer;

    private final CtrlProjeto ctrlProjeto = new CtrlProjeto();
    private Projeto projeto_selecionado;
    private final CtrlUsuario ctrlUsuario = new CtrlUsuario();
    private Usuario usuario_logado = new Usuario();
    private String perfil_selecionado;
    private DefaultComboBoxModel comboboxModel;

    // Barra de Menu
    private final ViewGerenciarProjetos viewGerenciarProjetos = new ViewGerenciarProjetos();
    private final ViewGerenciarUsuarios viewGerenciarUsuarios = new ViewGerenciarUsuarios();
    private final ViewPermissoesDePerfis viewPermissoesDeUsuarios = new ViewPermissoesDePerfis();

    // Árvore 
    // Objetivos
    private final ViewProjeto_ObjetivosDeMedicao viewProjeto_ObjetivosDeMedicao = new ViewProjeto_ObjetivosDeMedicao();
    private final ViewProjeto_ObjetivosQuestoes viewProjeto_ObjetivosQuestoes = new ViewProjeto_ObjetivosQuestoes();
    // Indicadores
    private final ViewProjeto_Indicadores viewProjeto_Indicadores = new ViewProjeto_Indicadores();
    // Procedimentos
    private final ViewProjeto_ProcedimentoAnalise viewProjeto_ProcedimentoAnalise = new ViewProjeto_ProcedimentoAnalise();
    private final ViewProjeto_ProcedimentoColeta viewProjeto_ProcedimentoColeta = new ViewProjeto_ProcedimentoColeta();
    // Medidas
    private final ViewProjetoAprovacao viewProjeto_Aprovacao = new ViewProjetoAprovacao();
    private final ViewProjeto_MedicaoDefinicao viewProjeto_MedicaoDefinicao = new ViewProjeto_MedicaoDefinicao();
    private final ViewProjeto_Analise viewProjeto_Analise = new ViewProjeto_Analise();
    private final ViewProjeto_Coleta viewProjeto_Coletas = new ViewProjeto_Coleta();
    // Artefatos
    private final ViewProjeto_PlanoDeMedicao viewProjeto_PlanoDeMedicao = new ViewProjeto_PlanoDeMedicao();
    private final ViewProjeto_Relatorio viewProjeto_Relatorio = new ViewProjeto_Relatorio();
    // Resultados
    private final ViewProjeto_Resultados viewProjeto_Resultados = new ViewProjeto_Resultados();

    // Construtor usado para teste da ferramenta
    private ViewPrincipal() {
        initComponents();

        usuario_logado = FacadeJpa.getInstance().getUsuarioJpa().findUsuarioEntities().get(0);
        observer = new Observer(usuario_logado);
        jLabeBemVindo.setText("Bem vindo(a), " + usuario_logado.getLogin());
        popularComboboxDeProjetos();

        Copia.setViewPrincipal(this);
        jTree.setEnabled(false);

        this.setLocationRelativeTo(null);
        this.iniciarTelas();

    }

    public ViewPrincipal(Usuario usuario_logado) {
        initComponents();

        this.usuario_logado = FacadeJpa.getInstance().getUsuarioJpa().findUsuario(usuario_logado.getId());
        observer = new Observer(usuario_logado);

        jLabeBemVindo.setText("Bem vindo(a), " + usuario_logado.getLogin());
        popularComboboxDeProjetos();
        jTree.setEnabled(false);

        Copia.setViewPrincipal(this);

        this.setLocationRelativeTo(null);
        this.iniciarTelas();

    }

    private void atualizaDadosDaTelaPrincipal() {
        this.usuario_logado = this.ctrlUsuario.buscarUsuarioPeloLogin(this.usuario_logado.getLogin());
        jLabeBemVindo.setText("Bem vindo(a), " + usuario_logado.getLogin());
        popularComboboxDeProjetos();
    }

    private void popularComboboxDeProjetos() {
        comboboxModel = new DefaultComboBoxModel();
        comboboxModel.addElement("--Selecione um Projeto--");

        List<String> lista_nomeProjetos = new ArrayList<>();
        lista_nomeProjetos = ctrlProjeto.buscarProjetosDoUsuario(this.usuario_logado.getId(), 0);
        for (String lista_nomeProjeto : lista_nomeProjetos) {
            this.comboboxModel.addElement(lista_nomeProjeto);
        }

        jComboBoxSelecaoDeProjeto.setModel(this.comboboxModel);
    }

    public Usuario getUsuarioLogado() {
        return this.usuario_logado;
    }

    private void eventosComboboxProjeto() {
        if (jComboBoxSelecaoDeProjeto.getSelectedItem() != "--Selecione um Projeto--") {
            this.projeto_selecionado = this.ctrlProjeto.buscaProjetoPeloNome(jComboBoxSelecaoDeProjeto.getSelectedItem().toString());
            System.out.println("--Projeto: " + this.projeto_selecionado.getNome());

            List<String> perfis = new ArrayList<>();
            for (int i = 0; i < new AcessaJpa().findAcessaByIdUsuario(usuario_logado.getId()).size(); i++) {
                if (this.usuario_logado.getAcessaList().get(i).getProjeto().getNome().equals(jComboBoxSelecaoDeProjeto.getSelectedItem().toString())) {
                    perfis.add(this.usuario_logado.getAcessaList().get(i).getPerfil().getNome());
                }
            }

            if (perfis.size() == 1) {
                this.perfil_selecionado = perfis.get(0);
                System.out.println("--perfil selecionado: " + this.perfil_selecionado);
            } else {
                ViewSelecaoDePerfilAoEscolherProjeto viewSelecaoDePerfilAoEscolherProjeto = new ViewSelecaoDePerfilAoEscolherProjeto(this, rootPaneCheckingEnabled);
                viewSelecaoDePerfilAoEscolherProjeto.populaComboboxDePerfis(perfis);
                viewSelecaoDePerfilAoEscolherProjeto.setVisible(true);
                this.perfil_selecionado = viewSelecaoDePerfilAoEscolherProjeto.perfilEscolhido();
                System.out.println("--perfil selecionado: " + this.perfil_selecionado);
            }
            jTree.setEnabled(true);
            Copia.setViewPrincipal(this);
        } else {
            jTree.setEnabled(false);
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
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree = new javax.swing.JTree();
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

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setDividerSize(10);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Projeto");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Objetivos");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Objetivo da Medição");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Necessidade de informações");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Indicadores");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Definição");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Aprovação");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Análise");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Medidas");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Definição");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Coleta");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Procedimentos");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Coleta");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Análise");
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
        jTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTree);

        jSplitPane1.setLeftComponent(jScrollPane1);

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jDesktopPane);

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
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1)
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
        deslogar();
    }//GEN-LAST:event_jMenuItemArquivoDesconectarActionPerformed

    public void deslogar() {
        ViewLogin viewLogin = new ViewLogin();
        viewLogin.setVisible(true);
        observer = null;
        this.dispose();
    }

    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        atualizaDadosDaTelaPrincipal();
    }//GEN-LAST:event_jButtonAtualizarActionPerformed

    private void jTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeMouseClicked
        if (jComboBoxSelecaoDeProjeto.getSelectedItem() != "--Selecione um Projeto--") {
            this.projeto_selecionado = this.ctrlProjeto.buscaProjetoPeloNome(jComboBoxSelecaoDeProjeto.getSelectedItem().toString());
            trocaDeTelasPelaArvore();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Projeto no combobox.");
        }
    }//GEN-LAST:event_jTreeMouseClicked

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
        jDesktopPane.add(viewProjeto_ObjetivosDeMedicao);
        jDesktopPane.add(viewProjeto_ObjetivosQuestoes);
        jDesktopPane.add(viewProjeto_ProcedimentoAnalise);
        jDesktopPane.add(viewProjeto_ProcedimentoColeta);
        jDesktopPane.add(viewProjeto_Resultados);
        jDesktopPane.add(viewProjeto_Aprovacao);
        jDesktopPane.add(viewProjeto_MedicaoDefinicao);
        jDesktopPane.add(viewProjeto_Analise);
        jDesktopPane.add(viewProjeto_Indicadores);
        jDesktopPane.add(viewProjeto_Coletas);
        jDesktopPane.add(viewProjeto_PlanoDeMedicao);
        jDesktopPane.add(viewProjeto_Relatorio);

        try {
            viewGerenciarProjetos.setMaximum(true);
            viewGerenciarUsuarios.setMaximum(true);
            viewPermissoesDeUsuarios.setMaximum(true);
            viewProjeto_ObjetivosDeMedicao.setMaximum(true);
            viewProjeto_ObjetivosQuestoes.setMaximum(true);
            viewProjeto_ProcedimentoAnalise.setMaximum(true);
            viewProjeto_ProcedimentoColeta.setMaximum(true);
            viewProjeto_Resultados.setMaximum(true);
            viewProjeto_Aprovacao.setMaximum(true);
            viewProjeto_MedicaoDefinicao.setMaximum(true);
            viewProjeto_Analise.setMaximum(true);
            viewProjeto_Indicadores.setMaximum(true);
            viewProjeto_Coletas.setMaximum(true);
            viewProjeto_PlanoDeMedicao.setMaximum(true);
            viewProjeto_Relatorio.setMaximum(true);

        } catch (PropertyVetoException e) {
            System.err.println(" Exception maximizar internal\n " + e);
        }
    }

    public void trocaTelas(JInternalFrame tela) {
        viewGerenciarProjetos.setVisible(false);
        viewGerenciarUsuarios.setVisible(false);
        viewProjeto_ObjetivosDeMedicao.setVisible(false);
        viewProjeto_ObjetivosQuestoes.setVisible(false);
        viewProjeto_ProcedimentoAnalise.setVisible(false);
        viewProjeto_ProcedimentoColeta.setVisible(false);
        viewProjeto_Resultados.setVisible(false);
        viewProjeto_Aprovacao.setVisible(false);
        viewProjeto_MedicaoDefinicao.setVisible(false);
        viewProjeto_Analise.setVisible(false);
        viewProjeto_Indicadores.setVisible(false);
        viewProjeto_Coletas.setVisible(false);
        viewProjeto_PlanoDeMedicao.setVisible(false);
        viewProjeto_Relatorio.setVisible(false);

        if (tela != null)
            tela.setVisible(true);
    }

    private void trocaDeTelasPelaArvore() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();

        if (node == null) {
            return;
        }
        if (!node.isLeaf()) {
            return;
        }

        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        String no_filho = node.toString();
        String no_pai = parent.toString();

        if (no_filho.equals("Necessidade de informações") && no_pai.endsWith("Objetivos")) {
            viewProjeto_ObjetivosQuestoes.preencherTabelaQuestoes();
            trocaTelas(viewProjeto_ObjetivosQuestoes);
        } else if (no_filho.equals("Objetivo da Medição") && no_pai.endsWith("Objetivos")) {
            viewProjeto_ObjetivosDeMedicao.preencherTabelaRecarregar();
            trocaTelas(viewProjeto_ObjetivosDeMedicao);
        } else if (no_filho.equals("Definição") && no_pai.endsWith("Indicadores")) {
            viewProjeto_Indicadores.preencherTabelaIndicadoresDoProjeto();
            trocaTelas(viewProjeto_Indicadores);
        } else if (no_filho.equals("Aprovação") && no_pai.endsWith("Indicadores")) {
            viewProjeto_Aprovacao.buscarIndicador();
            trocaTelas(viewProjeto_Aprovacao);
        } else if (no_filho.equals("Análise") && no_pai.endsWith("Indicadores")) {
            trocaTelas(viewProjeto_Analise);
        } else if (no_filho.equals("Acompanhamento") && no_pai.endsWith("Indicadores")) {

        } else if (no_filho.equals("Definição") && no_pai.endsWith("Medidas")) {
            trocaTelas(viewProjeto_MedicaoDefinicao);
            viewProjeto_MedicaoDefinicao.preencherTabelaRecarregar();
        } else if (no_filho.equals("Coleta") && no_pai.endsWith("Medidas")) {
            viewProjeto_Coletas.preencherTabelaMedidaDoProjeto();
            trocaTelas(viewProjeto_Coletas);
        } else if (no_filho.equals("Análise") && no_pai.endsWith("Procedimentos")) {
            //viewProjeto_ProcedimentoAnalise.preencherTabelaProcedimentoAnaliseDoProjeto();
            trocaTelas(viewProjeto_ProcedimentoAnalise);
        } else if (no_filho.equals("Coleta") && no_pai.endsWith("Procedimentos")) {
            viewProjeto_ProcedimentoColeta.recarregarTabela();
            trocaTelas(viewProjeto_ProcedimentoColeta);
        } else if (no_filho.equals("Plano de Medição") && no_pai.endsWith("Artefatos")) {
            viewProjeto_PlanoDeMedicao.showInformaçõesPlanoMedicao();
            trocaTelas(viewProjeto_PlanoDeMedicao);
        } else if (no_filho.equals("Relatório") && no_pai.endsWith("Artefatos")) {
            viewProjeto_Relatorio.showInformaçõesPlanoMedicao();
            trocaTelas(viewProjeto_Relatorio);
        } else if (no_filho.equals("Resultados")) {
            trocaTelas(viewProjeto_Resultados);
        }

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
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTree jTree;
    // End of variables declaration//GEN-END:variables

    public Usuario getUsuario_logado() {
        return usuario_logado;
    }

    public Projeto getProjeto_selecionado() {
        return projeto_selecionado;
    }

    public ViewProjeto_ProcedimentoAnalise getViewProjeto_ProcedimentoAnalise() {
        return viewProjeto_ProcedimentoAnalise;
    }

}
