package view;

import controller.CtrlUsuario;
import java.util.Arrays;
import javax.swing.JOptionPane;
import model.Usuario;
import util.Criptografia;

/**
 *
 * @author BlenoVale
 */
public class ViewGerenciarConta extends javax.swing.JDialog {

    Usuario usuario_logado = new Usuario();
    CtrlUsuario ctrlUsuario = new CtrlUsuario();
    Criptografia criptografia = new Criptografia();

    public ViewGerenciarConta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.usuario_logado.setNome("Bleno Vale");
        this.usuario_logado = ctrlUsuario.buscarUsuarioPeloNome(usuario_logado);

        this.habilitarCamposDeNovaSenha(false);
        this.PreencheCamposConta();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void PreencheCamposConta() {
        jTextFieldNome_completo.setText(usuario_logado.getNome());
        jTextFieldLogin.setText(usuario_logado.getLogin());
        jTextFieldEmail.setText(usuario_logado.getEmail());
    }

    private void alterarCamposConta(int cont, String mensagem) {
        if (cont == 0) {
            if (ctrlUsuario.ComparaSenhaDigitadaComAdoBD(usuario_logado, new String(jPasswordSenhaAtual.getPassword()))) {
                if (jCheckBoxAlterarSenha.isSelected()) {
                    this.usuario_logado.setSenha(criptografia.criptografaMensagem(new String(jPasswordNovaSenha.getPassword())));
                }
                this.ctrlUsuario.editaUsuario(usuario_logado);
                this.dispose();
            }
        } else if (cont == 1) {
            JOptionPane.showMessageDialog(this, mensagem);
        } else {
            JOptionPane.showMessageDialog(this, "Mais de um campo estão vazios ou invalidos em seu tamanho.");
        }

    }

    public void habilitarCamposDeNovaSenha(boolean opcao) {
        jPasswordNovaSenha.setEnabled(opcao);
        jPasswordFieldConfirmaNovaSenha.setEnabled(opcao);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPasswordFieldConfirmaNovaSenha = new javax.swing.JPasswordField();
        jPasswordNovaSenha = new javax.swing.JPasswordField();
        jPasswordSenhaAtual = new javax.swing.JPasswordField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldLogin = new javax.swing.JTextField();
        jTextFieldNome_completo = new javax.swing.JTextField();
        jButtonCancelarDadosConta = new javax.swing.JButton();
        jButtonSalvarDadosConta = new javax.swing.JButton();
        jCheckBoxAlterarSenha = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciar conta");

        jLabel1.setText("Nome completo:");

        jLabel2.setText("Login:");

        jLabel3.setText("E-mail:");

        jLabel4.setText("Senha atual:");

        jLabel5.setText("Nova senha:");

        jLabel6.setText("Confirmar senha:");

        jTextFieldNome_completo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldNome_completo.setEnabled(false);

        jButtonCancelarDadosConta.setText("Cancelar");
        jButtonCancelarDadosConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarDadosContaActionPerformed(evt);
            }
        });

        jButtonSalvarDadosConta.setText("Salvar");
        jButtonSalvarDadosConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarDadosContaActionPerformed(evt);
            }
        });

        jCheckBoxAlterarSenha.setText("Alterar senha");
        jCheckBoxAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAlterarSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvarDadosConta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelarDadosConta)
                        .addGap(1, 1, 1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBoxAlterarSenha)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPasswordSenhaAtual)
                            .addComponent(jTextFieldEmail)
                            .addComponent(jTextFieldLogin)
                            .addComponent(jTextFieldNome_completo)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordNovaSenha)
                            .addComponent(jPasswordFieldConfirmaNovaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNome_completo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jPasswordSenhaAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxAlterarSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jPasswordFieldConfirmaNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvarDadosConta)
                    .addComponent(jButtonCancelarDadosConta))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarDadosContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarDadosContaActionPerformed
        int cont = 0;
        String mensagem = null;

        if (!jTextFieldLogin.getText().isEmpty()) {
            this.usuario_logado.setLogin(jTextFieldLogin.getText());
        } else {
            mensagem = "Campo Login não pode ser vazio.";
            cont++;
        }

        if (!jTextFieldEmail.getText().isEmpty()) {
            this.usuario_logado.setEmail(jTextFieldEmail.getText());
        } else {
            mensagem = "Campo Email não pode ser Vazio.";
            cont++;
        }

        if (jPasswordSenhaAtual.getPassword().length < 6) {
            mensagem = "Campo Senha Atual deve ter pelo menos seis caracteres";
            cont++;
        }

        if (jCheckBoxAlterarSenha.isSelected()) {

            if (jPasswordNovaSenha.getPassword().length < 6) {
                mensagem = "Campo Nova Senha deve ter pelo menos seis caracteres";
                cont++;
            } else if (!Arrays.equals(jPasswordNovaSenha.getPassword(), jPasswordFieldConfirmaNovaSenha.getPassword())) {
                mensagem = "Campos nova senha e Confirma senha não correspondem.";
                cont++;
            }
  
        }

        this.alterarCamposConta(cont, mensagem);
    }//GEN-LAST:event_jButtonSalvarDadosContaActionPerformed

    private void jButtonCancelarDadosContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarDadosContaActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarDadosContaActionPerformed

    private void jCheckBoxAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAlterarSenhaActionPerformed
        if (jCheckBoxAlterarSenha.isSelected()) {
            habilitarCamposDeNovaSenha(true);
        } else {
            habilitarCamposDeNovaSenha(false);
        }
    }//GEN-LAST:event_jCheckBoxAlterarSenhaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelarDadosConta;
    private javax.swing.JButton jButtonSalvarDadosConta;
    private javax.swing.JCheckBox jCheckBoxAlterarSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordFieldConfirmaNovaSenha;
    private javax.swing.JPasswordField jPasswordNovaSenha;
    private javax.swing.JPasswordField jPasswordSenhaAtual;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldNome_completo;
    // End of variables declaration//GEN-END:variables
}
