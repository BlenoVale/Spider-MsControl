package view.gerencia;

import controller.CtrlUsuario;
import facade.FacadeJpa;
import java.util.ArrayList;
import java.util.List;
import jpa.extensao.UsuarioJpa;
import javax.swing.JOptionPane;
import model.Usuario;
import util.Internal;
import util.MyDefaultTableModel;

public class ViewGerenciarUsuarios extends javax.swing.JInternalFrame {

    private final UsuarioJpa usuarioJpa = new UsuarioJpa();
    private MyDefaultTableModel tableModel;
    private final CtrlUsuario ctrlUsuario = new CtrlUsuario();
    private final FacadeJpa jpa = FacadeJpa.getInstance();
    private List<Usuario> listUsuario;

    public ViewGerenciarUsuarios() {
        initComponents();
        iniciarTable();
        preencherTabelaNomeDigitado();

        Internal.retiraBotao(this);
    }

    protected void iniciarTable() {
        tableModel = new MyDefaultTableModel(new String[]{"Nome", "Login", "Qtd de projetos", "Qtd de perfis"}, 0, false);
        jTable.setModel(tableModel);

    }

    private void preencherTabelaNomeDigitado() {
        String nomeDigitado = jTextFieldBuscar.getText();

        listUsuario = jpa.getUsuarioJpa().findByParteNome(nomeDigitado);
        preencherTable(listUsuario);
    }

    private void preencherTabelaRecarregar() {

        listUsuario = new ArrayList<>();
        listUsuario = jpa.getUsuarioJpa().findAllUsuarios();
        preencherTable(listUsuario);
    }

    private void preencherTable(List<Usuario> listUsuario) {
        iniciarTable();
        for (int i = 0; i < listUsuario.size(); i++) {
            String[] linhas = new String[]{listUsuario.get(i).getNome(),
                listUsuario.get(i).getLogin(),
                jpa.getUsuarioJpa().findCountProjetosByIdUsuario(listUsuario.get(i).getId()),
                jpa.getUsuarioJpa().findCountPerfilByIdUsuario(listUsuario.get(i).getId())

            };
            tableModel.addRow(linhas);
        }
        jTable.setModel(tableModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButtonEditar = new javax.swing.JButton();
        jButtonNovoUsuario = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jCheckBox1.setText("jCheckBox1");

        setTitle("Gerenciar usuários");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Buscar usuário:");

        jTextFieldBuscar.setToolTipText("Pressione Enter com o campo em branco para listar todos os usuários");
        jTextFieldBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarActionPerformed(evt);
            }
        });

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Login", "Qtd de projetos que participa", "Qtd de perfis"
            }
        ));
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonNovoUsuario.setText("Novo usuário");
        jButtonNovoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoUsuarioActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh3.png"))); // NOI18N
        jButton1.setToolTipText("Lista todos os usuários");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonNovoUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEditar)
                    .addComponent(jButtonNovoUsuario))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNovoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoUsuarioActionPerformed
        ViewNovoUsuario viewNovoUsuario = new ViewNovoUsuario(null, true);
        preencherTabelaRecarregar();
    }//GEN-LAST:event_jButtonNovoUsuarioActionPerformed


    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        if (jTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um usuário da tabela");
            return;
        }

        editarUsuario();
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void editarUsuario() {
        Usuario user = new Usuario();
        user = usuarioJpa.findByLogin(jTable.getValueAt(jTable.getSelectedRow(), 1).toString());
        ViewEspecificacoesDeUsuario viewEspecificacoesDeUsuario = new ViewEspecificacoesDeUsuario(null, true, user);

        iniciarTable();
        preencherTabelaRecarregar();
    }

    private void jTextFieldBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarActionPerformed
        preencherTabelaNomeDigitado();
    }//GEN-LAST:event_jTextFieldBuscarActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        if (evt.getClickCount() > 1) {
            int row = jTable.getSelectedRow();

            editarUsuario();
        }
    }//GEN-LAST:event_jTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTextFieldBuscar.setText("");
        preencherTabelaRecarregar();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonNovoUsuario;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
