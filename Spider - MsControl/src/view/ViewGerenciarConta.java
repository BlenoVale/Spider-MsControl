package view;

import controller.CtrlUsuario;
import java.util.Arrays;
import javax.swing.JOptionPane;
import model.Usuario;

public class ViewGerenciarConta extends javax.swing.JDialog {
    Usuario usuario_logado = new Usuario();
    CtrlUsuario ctrlUsuario = new CtrlUsuario();
    
    public ViewGerenciarConta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.usuario_logado.setNome("Bleno Vale");
        this.usuario_logado = ctrlUsuario.buscarUsuarioLogado(usuario_logado);
        
        this.PreencheCamposConta();
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private void PreencheCamposConta(){
        jTextFieldNome_completo.setText(usuario_logado.getNome());
        jTextFieldLogin.setText(usuario_logado.getLogin());
        jTextFieldEmail.setText(usuario_logado.getEmail());
    }
    
    private void alterarCamposConta (){
        int cont = 0;
        String mensagem = null;
        if (!jTextFieldLogin.getText().isEmpty()){
            usuario_logado.setLogin(jTextFieldLogin.getText());
        } else {
            cont = cont + 1;
            mensagem = "Campo Login não pode ser vazio.";
        }
        
        if (!jTextFieldEmail.getText().isEmpty()){
            usuario_logado.setEmail(jTextFieldEmail.getText());
        } else {
            cont = cont + 1;
            mensagem = "Campo Email não pode ser Vazio.";
        }
        
//        if (!Arrays.toString(jPasswordSenhaAtual.getPassword()).isEmpty()){
//        }
        if (cont == 0){
            JOptionPane.showMessageDialog(this, "Dados Salvos com sucesso.");
        } else if (cont == 1) {
            JOptionPane.showMessageDialog(this, mensagem);
        } else {
            JOptionPane.showMessageDialog(this, "Mais de um campo estão vazios.");
        }
        
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciar conta");

        jLabel1.setText("Nome completo:");

        jLabel2.setText("Login:");

        jLabel3.setText("E-mail:");

        jLabel4.setText("Senha atual:");

        jLabel5.setText("Senha:");

        jLabel6.setText("Confirmar senha:");

        jTextFieldNome_completo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldNome_completo.setEnabled(false);

        jButtonCancelarDadosConta.setText("Cancelar");

        jButtonSalvarDadosConta.setText("Salvar");
        jButtonSalvarDadosConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarDadosContaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordFieldConfirmaNovaSenha)
                            .addComponent(jPasswordNovaSenha)
                            .addComponent(jPasswordSenhaAtual)
                            .addComponent(jTextFieldEmail)
                            .addComponent(jTextFieldLogin)
                            .addComponent(jTextFieldNome_completo)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 236, Short.MAX_VALUE)
                        .addComponent(jButtonSalvarDadosConta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelarDadosConta)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jPasswordFieldConfirmaNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelarDadosConta)
                    .addComponent(jButtonSalvarDadosConta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarDadosContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarDadosContaActionPerformed
        // TODO add your handling code here:
        alterarCamposConta();
    }//GEN-LAST:event_jButtonSalvarDadosContaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelarDadosConta;
    private javax.swing.JButton jButtonSalvarDadosConta;
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
