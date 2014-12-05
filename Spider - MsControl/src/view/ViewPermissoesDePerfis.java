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

    /**
     * @author Bleno Vale
    */
public class ViewPermissoesDePerfis extends javax.swing.JInternalFrame {
    
    private DefaultListModel model_listaFuncionalidades = new DefaultListModel();
    private DefaultListModel model_listaFuncionalidadesDoPerfil = new DefaultListModel();

    private List<Funcionalidade> lista_Funcionalidades;

    private List<Funcionalidade> lista_FuncionalidadesDoPerfil;

    private List<Perfil> lista_perfil = new PerfilJpaController(Conexao.conectar()).findPerfilEntities();

    private Perfil perfil = new Perfil();

    public ViewPermissoesDePerfis() {
        initComponents();
        popularComboboxDePerfil();
        buscaFuncionalidades();
        preencherListaDeFuncionalidades();
        initModel();
    }
    
    // método que busca todas as funcionalidades existentes no BD.
    private void buscaFuncionalidades() {
        lista_Funcionalidades = new FuncionalidadeJpaController(Conexao.conectar())
                .findFuncionalidadeEntities();
    }

    // método responsavel por popular o combobox de perfis com uma lista trazida do BD
    private void popularComboboxDePerfil() {

        jComboBoxPerfil.addItem("--Selecione um Perfil--");
        for (int i = 0; i < lista_perfil.size(); i++) {
            jComboBoxPerfil.addItem(lista_perfil.get(i).getNome());
            System.out.println("perfil " + i + ": " + lista_perfil.get(i).getNome());
        }
    }
    
    // inicia as jLists
    private void initModel() {
        jListFucionalidades.setModel(model_listaFuncionalidades);
        jListFuncionalidadesDoPerfil.setModel(model_listaFuncionalidadesDoPerfil);
    }
    
    // método responsavel por preencher a lista de funcionalidades
    private void preencherListaDeFuncionalidades() {

        model_listaFuncionalidades = new DefaultListModel();
        for (int i = 0; i < lista_Funcionalidades.size(); i++) {
            model_listaFuncionalidades.addElement(lista_Funcionalidades.get(i).getNome());
            System.out.println("Funcionalidade " + i + ": " + lista_Funcionalidades.get(i).getNome());
        }
    }
    
    // método responsavel por buscar o perfil selecionado no combobox
    private void buscaPerfil(String nome_perfil) {
        for (int i = 0; i < lista_perfil.size(); i++) {
            if (lista_perfil.get(i).getNome().equals(nome_perfil)) {
                perfil = new PerfilJpaController(Conexao.conectar()).findPerfil(lista_perfil.get(i).getId());
                break;
            }
        }
    }

    public void buscaFuncionalidadesJaExistentes(String nome_perfil) {

        buscaPerfil(nome_perfil);
        if (!perfil.getFuncionalidadeList().isEmpty()) {
            buscaFuncionalidades();
            for (int i = 0; i < perfil.getFuncionalidadeList().size(); i++) {
                for (int j = 0; j < lista_Funcionalidades.size(); j++) {
                    if (Objects.equals(lista_Funcionalidades.get(j), perfil.getFuncionalidadeList().get(i))) {
                        lista_Funcionalidades.remove(j);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Lista de permissões"));

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

        jButtonSalvarAlterarFuncionalidade.setText("Salvar alterações");
        jButtonSalvarAlterarFuncionalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarAlterarFuncionalidadeActionPerformed(evt);
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
                        .addComponent(jButtonSalvarAlterarFuncionalidade))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonIncluirFuncionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonRetirarFuncionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addGap(104, 104, 104)
                        .addComponent(jButtonIncluirFuncionalidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRetirarFuncionalidade))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFuncionalidadesDoPerfil)
                            .addComponent(jLabelFuncionalidades, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButtonSalvarAlterarFuncionalidade)
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
                .addContainerGap(39, Short.MAX_VALUE))
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

                buscaPerfil(jComboBoxPerfil.getSelectedItem().toString());
                lista_FuncionalidadesDoPerfil = perfil.getFuncionalidadeList();

                model_listaFuncionalidadesDoPerfil.addElement(model_listaFuncionalidades.getElementAt(index));
                model_listaFuncionalidades.remove(index);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um perfil no Combobox.");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "Selecione uma funcionalidade.");
        }

    }//GEN-LAST:event_jButtonIncluirFuncionalidadeActionPerformed

    @SuppressWarnings("empty-statement")
    private void jButtonRetirarFuncionalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRetirarFuncionalidadeActionPerformed
        // TODO add your handling code here:
        try {
            if (jComboBoxPerfil.getSelectedItem() != "--Selecione um Perfil--") {
                int index = jListFuncionalidadesDoPerfil.getSelectedIndex();

                for (int i = 0; i < perfil.getFuncionalidadeList().size(); i++) {
                    if (model_listaFuncionalidadesDoPerfil.getElementAt(index).toString().equals(perfil.getFuncionalidadeList().get(i).getNome())) {
                        perfil.getFuncionalidadeList().remove(i);
                        lista_FuncionalidadesDoPerfil = perfil.getFuncionalidadeList();
                        break;
                    }
                }

                model_listaFuncionalidades.addElement(model_listaFuncionalidadesDoPerfil.getElementAt(index));
                model_listaFuncionalidadesDoPerfil.remove(index);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um perfil no Combobox.");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, "Selecione uma funcionalidade do Perfil.");
        }

    }//GEN-LAST:event_jButtonRetirarFuncionalidadeActionPerformed

    private void jButtonSalvarAlterarFuncionalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarAlterarFuncionalidadeActionPerformed
        // TODO add your handling code here:
        PerfilJpa perfilJpa = new PerfilJpa();
        for (int i = 0; i < lista_perfil.size(); i++) {
            if (jComboBoxPerfil.getSelectedItem() == lista_perfil.get(i).getNome()) {
                perfil = lista_perfil.get(i);
                break;
            }
        }

        if (perfil.getFuncionalidadeList().isEmpty()) {
            lista_FuncionalidadesDoPerfil = new ArrayList<>();
        }

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
    }//GEN-LAST:event_jButtonSalvarAlterarFuncionalidadeActionPerformed

    private void jComboBoxPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPerfilActionPerformed
        // TODO add your handling code here:
        if (jComboBoxPerfil.getSelectedItem() != "--Selecione um Perfil--") {
            buscaFuncionalidadesJaExistentes(jComboBoxPerfil.getSelectedItem().toString());
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
