package view;

import controller.CtrlUsuario;
import facade.FacadeJpa;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import jpa.exceptions.NonexistentEntityException;
import model.Acessa;
import model.AcessaPK;
import model.Perfil;
import model.Projeto;
import model.Usuario;
import util.MyDefaultTableModel;

public class ViewEspecificacoesDeUsuario extends javax.swing.JDialog {

    private MyDefaultTableModel tableModel;
    private final FacadeJpa jpa = FacadeJpa.getInstance();
    private final List<Acessa> acessoListRemover = new ArrayList<>();
    
    private final Usuario usuario;
    private final CtrlUsuario ctrlUsuario = new CtrlUsuario();

    public ViewEspecificacoesDeUsuario(java.awt.Frame parent, boolean modal, Usuario usuario) {
        super(parent, modal);
        this.usuario = usuario;

        initComponents();

        jLabelNomeDoUsuario.setText(usuario.getNome());

        iniciarTabela();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void iniciarTabela() {

        List<Acessa> acessoList = usuario.getAcessaList();

        int linhas = acessoList.size();
        String colunas[] = new String[]{"Nome do projeto", "Perfil no projeto", "Data de entrada"};

        tableModel = new MyDefaultTableModel(colunas, linhas, false);
        jTable.setModel(tableModel);
        for (int i = 0; i < linhas; i++) {
            jTable.setValueAt(acessoList.get(i).getProjeto().getNome(), i, 0);
            jTable.setValueAt(acessoList.get(i).getPerfil().getNome(), i, 1);
            jTable.setValueAt(formataData(acessoList.get(i).getDataDeInicio()), i, 2);
        }
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

    private void removerLinhaTabela(int numLinha) {
        tableModel.removeRow(numLinha);
        jTable.setModel(tableModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButtonRetirarPerfilDeUsuario = new javax.swing.JButton();
        jButtonAlocarUsuario = new javax.swing.JButton();
        jLabelNomeDoUsuario = new javax.swing.JLabel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Especificações de usuário");

        jLabel1.setText("Nome:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Projetos que o usuário faz parte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome do projeto", "Perfil no projeto", "Data de alocação"
            }
        ));
        jScrollPane1.setViewportView(jTable);

        jButtonRetirarPerfilDeUsuario.setText("Retirar perfil de projeto");
        jButtonRetirarPerfilDeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRetirarPerfilDeUsuarioActionPerformed(evt);
            }
        });

        jButtonAlocarUsuario.setText("Alocar este usuario a um projeto");
        jButtonAlocarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlocarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonRetirarPerfilDeUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jButtonAlocarUsuario)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRetirarPerfilDeUsuario)
                    .addComponent(jButtonAlocarUsuario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelNomeDoUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelNomeDoUsuario.setText("Fulano de tal Ciclano");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNomeDoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelNomeDoUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAlocarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlocarUsuarioActionPerformed
        ViewAlocacaoDeUsuarioAProjeto viewAlocacaoDeUsuarioAProjeto = new ViewAlocacaoDeUsuarioAProjeto(null, rootPaneCheckingEnabled);
        String projetoPerfil[] = viewAlocacaoDeUsuarioAProjeto.showDialog();
        
        if(projetoPerfil != null)
            this.addLinhaTabela(projetoPerfil[0], projetoPerfil[1]);
    }//GEN-LAST:event_jButtonAlocarUsuarioActionPerformed

    private void jButtonRetirarPerfilDeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRetirarPerfilDeUsuarioActionPerformed
        if (jTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Selecione um perfil para remover");
            return;
        } else if (tableModel.getRowCount() == 1) {
            JOptionPane.showMessageDialog(rootPane, "Você não pode remover todos os perfis de um usuário");
            return;
        }
        adicionarAcessoNaListaDeRemocao();
        removerLinhaTabela(jTable.getSelectedRow());
    }//GEN-LAST:event_jButtonRetirarPerfilDeUsuarioActionPerformed

    /*
     Os acessos saoh adicionado em uma lista pois soh devem ser destruidos
     quando o usuario apertar salvar
     */
    private void adicionarAcessoNaListaDeRemocao() {

        Perfil perfil = jpa.getPerfilJpa().findByNome(jTable.getValueAt(jTable.getSelectedRow(), 1).toString());
        Projeto projeto = jpa.getProjetoJpa().findByNome(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());

        Acessa acessoParaRemover = new Acessa();
        acessoParaRemover.setAcessaPK(new AcessaPK(projeto.getId(), perfil.getId(), usuario.getId()));

        acessoListRemover.add(acessoParaRemover);
    }

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        alocarUsuarioAProjeto();
        removerAcessosDuplicados();
        this.dispose();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void alocarUsuarioAProjeto() {
        String[] nomeProjeto = new String[jTable.getRowCount()];
        String[] nomePerfil = new String[jTable.getRowCount()];

        // pega os dados que tem na tabela 
        for (int i = 0; i < jTable.getRowCount(); i++) {
            nomeProjeto[i] = jTable.getValueAt(i, 0).toString();
            nomePerfil[i] = jTable.getValueAt(i, 1).toString();
        }
        ctrlUsuario.alocarUsuarioAVariosProjetos(usuario, nomeProjeto, nomePerfil);
    }

    private void removerAcessosDuplicados() {
        for (Acessa acessoParaRemover : acessoListRemover) {
            try {
                jpa.getAcessaJpa().destroy(acessoParaRemover.getAcessaPK());
            } catch (NonexistentEntityException ex) {
                System.out.println("Acesso destruido");
                //Logger.getLogger(ViewEspecificacoesDeUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlocarUsuario;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonRetirarPerfilDeUsuario;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelNomeDoUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables

    public String formataData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("E dd / MM / yyyy");
        return sdf.format(data);
    }
}
