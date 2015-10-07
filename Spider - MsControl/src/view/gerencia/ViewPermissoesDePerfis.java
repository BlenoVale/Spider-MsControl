package view.gerencia;

import controller.CtrlPerfil;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Funcionalidade;
import model.Perfil;
import util.Internal;

/**
 * @author Bleno Vale
 */
public class ViewPermissoesDePerfis extends javax.swing.JInternalFrame {

    CtrlPerfil ctrlPermissoesDePerfil = new CtrlPerfil();

    private DefaultListModel model_listaDeFucionalidades;
    private DefaultListModel model_listaDeFuncionalidadesDoPerfil = new DefaultListModel();

    public ViewPermissoesDePerfis() {
        initComponents();
        populaComboboxDePerfis();
        preencherListaDeFuncionalidades();
        initModels();
        Internal.retiraBorda(this);
    }

    private void populaComboboxDePerfis() {
        jComboBoxPerfil.addItem("--Selecione um Perfil--");
        List<Perfil> lista_perfil = ctrlPermissoesDePerfil.buscaListaDePerfil();
        for (int i = 0; i < lista_perfil.size(); i++) {
            jComboBoxPerfil.addItem(lista_perfil.get(i).getNome());
        }
    }

    private void preencherListaDeFuncionalidades() {
        model_listaDeFucionalidades = new DefaultListModel();
        List<Funcionalidade> lista_Funcionalidades = ctrlPermissoesDePerfil.buscarListaDeFuncionalidades();
        for (int i = 0; i < lista_Funcionalidades.size(); i++) {
            model_listaDeFucionalidades.addElement(lista_Funcionalidades.get(i).getNome());
        }
    }

    private void preencherListaDeFuncionalidadesJaExistentes(String nome_perfil) {

        model_listaDeFucionalidades = new DefaultListModel();
        Perfil perfil_selecionado = ctrlPermissoesDePerfil.buscarPerfilSelecionado(nome_perfil);
        List<Funcionalidade> lista_funcionalidadesDoPerfil = ctrlPermissoesDePerfil.buscarListaDeFuncionalidadesDoPerfil();

        for (int i = 0; i < lista_funcionalidadesDoPerfil.size(); i++) {
            model_listaDeFucionalidades.addElement(lista_funcionalidadesDoPerfil.get(i).getNome());
        }

        model_listaDeFuncionalidadesDoPerfil = new DefaultListModel();
        for (int i = 0; i < perfil_selecionado.getFuncionalidadeList().size(); i++) {
            model_listaDeFuncionalidadesDoPerfil.addElement(perfil_selecionado.getFuncionalidadeList().get(i).getNome());
        }
        initModels();

    }

    private void initModels() {
        jListFucionalidades.setModel(model_listaDeFucionalidades);
        jListFuncionalidadesDoPerfil.setModel(model_listaDeFuncionalidadesDoPerfil);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBoxPerfil = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jButtonIncluirFuncionalidade = new javax.swing.JButton();
        jButtonRetirarFuncionalidade = new javax.swing.JButton();
        jButtonSalvarAlterarFuncionalidade = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListFucionalidades = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListFuncionalidadesDoPerfil = new javax.swing.JList();
        jLabelFuncionalidades = new javax.swing.JLabel();
        jLabelFuncionalidadesDoPerfil = new javax.swing.JLabel();

        setTitle("Permissões de Perfil");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Lista de Permissões"));

        jComboBoxPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPerfilActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonIncluirFuncionalidade.setText("Incluir");
        jButtonIncluirFuncionalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirFuncionalidadeActionPerformed(evt);
            }
        });

        jButtonRetirarFuncionalidade.setText("Retirar");
        jButtonRetirarFuncionalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRetirarFuncionalidadeActionPerformed(evt);
            }
        });

        jButtonSalvarAlterarFuncionalidade.setText("Salvar Alterações");
        jButtonSalvarAlterarFuncionalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarAlterarFuncionalidadeActionPerformed(evt);
            }
        });

        jListFucionalidades.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jListFucionalidades);

        jListFuncionalidadesDoPerfil.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jListFuncionalidadesDoPerfil);

        jLabelFuncionalidades.setText("Funcionalidades:");

        jLabelFuncionalidadesDoPerfil.setText("Funcionalidades do Perfil:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvarAlterarFuncionalidade))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonIncluirFuncionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonRetirarFuncionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabelFuncionalidades))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelFuncionalidadesDoPerfil)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jButtonIncluirFuncionalidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRetirarFuncionalidade))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFuncionalidadesDoPerfil)
                            .addComponent(jLabelFuncionalidades, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSalvarAlterarFuncionalidade)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBoxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIncluirFuncionalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirFuncionalidadeActionPerformed
        // TODO add your handling code here:
        try {
            if (jComboBoxPerfil.getSelectedItem() != "--Selecione um Perfil--") {
                int index = jListFucionalidades.getSelectedIndex();

                model_listaDeFuncionalidadesDoPerfil.addElement(model_listaDeFucionalidades.getElementAt(index));
                model_listaDeFucionalidades.remove(index);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um perfil no combobox.");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Selecione uma funcionalidade.");
        }
    }//GEN-LAST:event_jButtonIncluirFuncionalidadeActionPerformed

    @SuppressWarnings("empty-statement")
    private void jButtonRetirarFuncionalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRetirarFuncionalidadeActionPerformed
        // TODO add your handling code here:
        try {
            if (jComboBoxPerfil.getSelectedItem() != "--Selecione um Perfil--") {
                int index = jListFuncionalidadesDoPerfil.getSelectedIndex();

                model_listaDeFucionalidades.addElement(model_listaDeFuncionalidadesDoPerfil.getElementAt(index));
                model_listaDeFuncionalidadesDoPerfil.remove(index);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um perfil no combobox.");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Selecione uma funcionalidade do perfil.");
        }
    }//GEN-LAST:event_jButtonRetirarFuncionalidadeActionPerformed

    private void jButtonSalvarAlterarFuncionalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarAlterarFuncionalidadeActionPerformed
        // TODO add your handling code here:
        try {
            if (jComboBoxPerfil.getSelectedItem() != "--Selecione um Perfil--") {
                ctrlPermissoesDePerfil.salvarAlterarFuncionalidadesDoPerfil(jComboBoxPerfil.getSelectedItem().toString(), model_listaDeFuncionalidadesDoPerfil);
                JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um perfil no combobox.");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Não foi possivel salvar.");
            error.printStackTrace();
        }
    }//GEN-LAST:event_jButtonSalvarAlterarFuncionalidadeActionPerformed

    private void jComboBoxPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPerfilActionPerformed
        // TODO add your handling code here:
        if (jComboBoxPerfil.getSelectedItem() != "--Selecione um Perfil--") {
            preencherListaDeFuncionalidadesJaExistentes(jComboBoxPerfil.getSelectedItem().toString());
        }
    }//GEN-LAST:event_jComboBoxPerfilActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIncluirFuncionalidade;
    private javax.swing.JButton jButtonRetirarFuncionalidade;
    private javax.swing.JButton jButtonSalvarAlterarFuncionalidade;
    private javax.swing.JComboBox jComboBoxPerfil;
    private javax.swing.JLabel jLabelFuncionalidades;
    private javax.swing.JLabel jLabelFuncionalidadesDoPerfil;
    private javax.swing.JList jListFucionalidades;
    private javax.swing.JList jListFuncionalidadesDoPerfil;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
