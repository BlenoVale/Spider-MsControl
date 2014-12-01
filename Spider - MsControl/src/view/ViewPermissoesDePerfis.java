package view;

import controller.FuncionalidadeJpaController;
import controller.PerfilJpaController;
import controller.extensao.PerfilJpa;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Funcionalidade;
import model.Perfil;
import util.Conexao;

public class ViewPermissoesDePerfis extends javax.swing.JInternalFrame {

    private DefaultListModel model_listaFuncionalidades = new DefaultListModel();
    private DefaultListModel model_listaFuncionalidadesDoPerfil = new DefaultListModel();

    private List<Funcionalidade> lista_Funcionalidades;

    private List<Perfil> lista_perfil = new PerfilJpaController(Conexao.conectar()).findPerfilEntities();

    private Perfil perfil = new Perfil();

    public ViewPermissoesDePerfis() {
        initComponents();
        popularComboboxDePerfil();
        buscaFuncionalidades();
        preencherListaDeFuncionalidades();
        initModel();
    }

    private void buscaFuncionalidades() {
        lista_Funcionalidades = new FuncionalidadeJpaController(Conexao.conectar())
                .findFuncionalidadeEntities();
    }

    private void popularComboboxDePerfil() {

        jComboBoxPerfil.addItem("--Selecione um Perfil--");
        for (int i = 0; i < lista_perfil.size(); i++) {
            jComboBoxPerfil.addItem(lista_perfil.get(i).getNome());
            System.out.println("perfil " + i + ": " + lista_perfil.get(i).getNome());
        }
    }

    private void initModel() {
        jListFucionalidades.setModel(model_listaFuncionalidades);
        jListFuncionalidadesDoPerfil.setModel(model_listaFuncionalidadesDoPerfil);
    }

    private void preencherListaDeFuncionalidades() {

        model_listaFuncionalidades = new DefaultListModel();
        for (int i = 0; i < lista_Funcionalidades.size(); i++) {
            model_listaFuncionalidades.addElement(lista_Funcionalidades.get(i).getNome());
            System.out.println("Funcionalidade " + i + ": " + lista_Funcionalidades.get(i).getNome());
        }
    }

    public void buscaFuncionalidadesJaExistentes(String nome_perfil) {

        for (int i = 0; i < lista_perfil.size(); i++) {
            if (lista_perfil.get(i).getNome().equals(nome_perfil)) {
                perfil = new PerfilJpaController(Conexao.conectar()).findPerfil(lista_perfil.get(i).getId());
                break;
            }
        }

        if (!perfil.getFuncionalidadeList().isEmpty()) {
            buscaFuncionalidades();
            for (int i = 0; i < lista_Funcionalidades.size(); i++) {
                for (int j = 0; j < perfil.getFuncionalidadeList().size(); j++) {
                    if (Objects.equals(lista_Funcionalidades.get(i).getId(), perfil.getFuncionalidadeList().get(j).getId())) {
                        lista_Funcionalidades.remove(i);
                    }
                }
            }

            preencherListaDeFuncionalidades();
            model_listaFuncionalidadesDoPerfil = new DefaultListModel();
            for (int i = 0; i < perfil.getFuncionalidadeList().size(); i++) {
                model_listaFuncionalidadesDoPerfil.addElement(perfil.getFuncionalidadeList().get(i).getNome());
                System.out.println("---->>Funcionalidade do perfil: " + i + ": " + perfil.getFuncionalidadeList().get(i).getNome());
            }

            initModel();
        } else {
            model_listaFuncionalidadesDoPerfil = new DefaultListModel();
            buscaFuncionalidades();
            preencherListaDeFuncionalidades();
            initModel();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBoxPerfil = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListFucionalidades = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListFuncionalidadesDoPerfil = new javax.swing.JList();
        jLabelFuncionalidades = new javax.swing.JLabel();
        jLabelFuncionalidadesDoPerfil = new javax.swing.JLabel();

        setTitle("Permissões de Perfil");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Lista de permissões"));

        jComboBoxPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPerfilActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Incluir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Retirar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Salvar alterações");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jListFucionalidades);

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
                        .addComponent(jButton3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabelFuncionalidades))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelFuncionalidadesDoPerfil)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFuncionalidadesDoPerfil)
                            .addComponent(jLabelFuncionalidades, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
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
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jComboBoxPerfil.getSelectedItem() != "--Selecione um Perfil--") {
            int index = jListFucionalidades.getSelectedIndex();

            model_listaFuncionalidadesDoPerfil.addElement(model_listaFuncionalidades.getElementAt(index));
            model_listaFuncionalidades.remove(index);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma Funcionalidade.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (jComboBoxPerfil.getSelectedItem() != "--Selecione um Perfil--") {
            int index = jListFuncionalidadesDoPerfil.getSelectedIndex();

            model_listaFuncionalidades.addElement(model_listaFuncionalidadesDoPerfil.getElementAt(index));
            model_listaFuncionalidadesDoPerfil.remove(index);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma Funcionalidade.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        PerfilJpa perfilJpa = new PerfilJpa();
        for (int i = 0; i < lista_perfil.size(); i++) {
            if (jComboBoxPerfil.getSelectedItem() == lista_perfil.get(i).getNome()) {
                perfil = lista_perfil.get(i);
                break;
            }
        }

        List<Funcionalidade> lista_FuncionalidadesDoPerfil = new ArrayList<>();
        for (int i = 0; i < lista_Funcionalidades.size(); i++) {
            for (int j = 0; j < model_listaFuncionalidadesDoPerfil.getSize(); j++) {
                if (lista_Funcionalidades.get(i).getNome().equals(model_listaFuncionalidadesDoPerfil.get(j).toString())) {
                    lista_FuncionalidadesDoPerfil.add(lista_Funcionalidades.get(i));
                }
            }
        }
        perfil.setFuncionalidadeList(lista_FuncionalidadesDoPerfil);
        // sout apenas para teste
        System.out.println("---->> ID perfil:" + perfil.getId());
        perfilJpa.inserirFuncionalidadesNoPerfil(perfil);

        // for e sout apenas para teste
        for (int i = 0; i < perfil.getFuncionalidadeList().size(); i++) {
            System.out.println("---->> Funcionalidade do Perfil: " + perfil.getNome() + " " + perfil.getFuncionalidadeList().get(i).getNome());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBoxPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPerfilActionPerformed
        // TODO add your handling code here:
        if (jComboBoxPerfil.getSelectedItem() != "--Selecione um Perfil--") {
            buscaFuncionalidadesJaExistentes(jComboBoxPerfil.getSelectedItem().toString());
        }
    }//GEN-LAST:event_jComboBoxPerfilActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
