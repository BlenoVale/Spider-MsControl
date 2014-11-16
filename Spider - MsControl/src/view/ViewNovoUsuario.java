package view;

import controller.AcessaJpaController;
import controller.ProjetoJpaController;
import controller.UsuarioJpaController;
import controller.extensao.PerfilJpa;
import controller.extensao.ProjetoJpa;
import controller.extensao.UsuarioJpa;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.Acessa;
import model.Perfil;
import model.Projeto;
import model.Usuario;
import util.Conexao;
import util.MyDefaultTableModel;

public class ViewNovoUsuario extends javax.swing.JDialog {

    private MyDefaultTableModel tableModel;

    public ViewNovoUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        iniciaTabela();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void iniciaTabela() {
        tableModel = new MyDefaultTableModel(new String[]{"Projeto", "Perfil"}, 0, false);
        jTable.setModel(tableModel);
    }

    protected void addLinhaTabela(String projeto, String perfil) {
        // Se ja contem na tabela, nao adiciona uma nova linha.
        for (int i = 0; i < jTable.getRowCount(); i++)
            if (jTable.getValueAt(i, 0).toString().equals(projeto) && jTable.getValueAt(i, 1).toString().equals(perfil)) {
                JOptionPane.showMessageDialog(rootPane, "O perfil \"" + perfil + "\" ja foi alocado para o projeto \"" + projeto + "\"", "Info", JOptionPane.ERROR_MESSAGE);
                return;
            }

        tableModel.addRow(new String[]{projeto, perfil});
        jTable.setModel(tableModel);
    }

    private void removeLinhaTabela(int numLinha) {
        tableModel.removeRow(numLinha);
        jTable.setModel(tableModel);
    }

    /**
     * Checagem se todos os campos foram digitados corretamente e se o Login ja
     * existe no banco
     *
     * @return true se nao ha impedimentos para proceguir, false caso contrario
     */
    private boolean checaDadosDigitados() {
        if (jTextFieldNome.getText().equals("") || jTextFieldLogin.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Os campos \"Nome completo\" e \"Login de acesso\" devem ser preenchidos", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            Usuario u = null;
            u = new UsuarioJpa().findByLogin(jTextFieldLogin.getText().toString());

            if (u != null) {
                JOptionPane.showMessageDialog(rootPane, "Este login ja existe");
                return false;
            } 
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        jMenuItemRemover = new javax.swing.JMenuItem();
        jLabelNome = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jPanelAlocacao = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButtonAdicionarUsuarioAProjeto = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jLabelLogin = new javax.swing.JLabel();
        jTextFieldLogin = new javax.swing.JTextField();

        jMenuItemRemover.setText("Remove linha");
        jMenuItemRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRemoverActionPerformed(evt);
            }
        });
        jPopupMenu.add(jMenuItemRemover);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de novo usuário");

        jLabelNome.setText("Nome completo: ");

        jPanelAlocacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Alocação"));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Projeto", "Perfil"
            }
        ));
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        jButtonAdicionarUsuarioAProjeto.setText("Adicionar este usuário a um projeto");
        jButtonAdicionarUsuarioAProjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarUsuarioAProjetoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAlocacaoLayout = new javax.swing.GroupLayout(jPanelAlocacao);
        jPanelAlocacao.setLayout(jPanelAlocacaoLayout);
        jPanelAlocacaoLayout.setHorizontalGroup(
            jPanelAlocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlocacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAlocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAlocacaoLayout.createSequentialGroup()
                        .addGap(0, 172, Short.MAX_VALUE)
                        .addComponent(jButtonAdicionarUsuarioAProjeto)))
                .addContainerGap())
        );
        jPanelAlocacaoLayout.setVerticalGroup(
            jPanelAlocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlocacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdicionarUsuarioAProjeto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jLabelLogin.setText("Login de acesso: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAlocacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNome)
                    .addComponent(jLabelLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNome)
                    .addComponent(jTextFieldLogin))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLogin)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelAlocacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButtonAdicionarUsuarioAProjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarUsuarioAProjetoActionPerformed
        if (new ProjetoJpaController(Conexao.conectar()).getProjetoCount() == 0) {
            int resp = JOptionPane.showConfirmDialog(rootPane, "Não há projetos para selecionar. Você deve primeiro criar um novo projeto.\nDeseja criar um projeto agora?");
            if (resp == 0) {
                this.dispose();
                new ViewNovoProjeto(null, true);
            }
            return;
        }
        ViewAlocacaoDeUsuarioAProjeto viewAlocacaoDeUsuarioAoProjeto = new ViewAlocacaoDeUsuarioAProjeto(null, rootPaneCheckingEnabled, this);
    }//GEN-LAST:event_jButtonAdicionarUsuarioAProjetoActionPerformed

    private void jTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMousePressed
        if (SwingUtilities.isRightMouseButton(evt)) {
            if (jTable.getSelectedRow() != -1)
                jPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTableMousePressed

    private void jMenuItemRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRemoverActionPerformed
        removeLinhaTabela(jTable.getSelectedRow());
    }//GEN-LAST:event_jMenuItemRemoverActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (!checaDadosDigitados())
            return; // Se os danos não tiver Ok, retorne

        // Checando se o novo usuario ja foi alocado a algum projeto
        if (jTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Você deve alocar este usuário a um projeto", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!criaUsuario(jTextFieldNome.getText(), jTextFieldLogin.getText(), null, (int) (Math.random() * 100000) + "@Spider.com"))
            return;

        alocaUsuarioAProjeto();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void alocaUsuarioAProjeto() {
        Acessa acessa;
        List<Acessa> acessaList = new ArrayList<>();

        ProjetoJpa projetoJpa = new ProjetoJpa();
        UsuarioJpa usuarioJpa = new UsuarioJpa();
        PerfilJpa perfilJpa = new PerfilJpa();

        Usuario user = usuarioJpa.findByNomeELogin(jTextFieldNome.getText(), jTextFieldLogin.getText());
        Perfil perfil;
        Projeto projeto;

        for (int i = 0; i < jTable.getRowCount(); i++) {
            acessa = new Acessa();
            projeto = projetoJpa.findByNome(jTable.getValueAt(i, 0).toString());
            perfil = perfilJpa.findByNome(jTable.getValueAt(i, 1).toString());

            acessa.setProjeto(projeto);
            acessa.setPerfil(perfil);
            acessa.setUsuario(user);

            try {
                new AcessaJpaController(Conexao.conectar()).create(acessa);;
            } catch (Exception ex) {
                Logger.getLogger(ViewNovoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            acessaList.add(acessa);
        }

        this.dispose();
    }

    private boolean criaUsuario(String nome, String login, String senha, String email) {
        Usuario user = new Usuario();

        user.setNome(nome);
        user.setLogin(login);
        user.setSenha(senha);
        user.setEmail(email);

        try {
            new UsuarioJpaController(Conexao.conectar()).create(user);
            JOptionPane.showMessageDialog(rootPane, "Usuário criado com sucesso");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Não foi possível criar o novo usuário", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
            return false;
        }
    }

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionarUsuarioAProjeto;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JMenuItem jMenuItemRemover;
    private javax.swing.JPanel jPanelAlocacao;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
