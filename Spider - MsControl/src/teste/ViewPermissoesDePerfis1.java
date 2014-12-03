package teste;

import view.*;
import jpa.FuncionalidadeJpaController;
import jpa.PerfilJpaController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Funcionalidade;
import model.Perfil;
import util.Conexao;

public class ViewPermissoesDePerfis1 extends javax.swing.JInternalFrame {

    private DefaultListModel defaultListModel;

    private Funcionalidade funcionalidade_selecionada;

    private Funcionalidade funcionalidadeDoPerfil_selecionada;

    private List<Funcionalidade> lista_Funcionalidades = new FuncionalidadeJpaController(Conexao.conectar())
            .findFuncionalidadeEntities();
    //private List<Funcionalidade> lista_FucionalidadesDoPerfil;

    private List<Perfil> lista_perfil = new PerfilJpaController(Conexao.conectar()).findPerfilEntities();

    List<Funcionalidade> lista_FucionalidadesDoPerfil = new ArrayList<>();

    public ViewPermissoesDePerfis1() {
        initComponents();
        popularComboboxDePerfil();
        preencherListaDeFuncionalidades();
        definirEventosListaDeFuncionalidades();
        definirEventoListaDeFuncionalidadesDoPerfil();
    }

    private void popularComboboxDePerfil() {

        jComboBoxPerfil.addItem("--Selecione um Projeto--");
        for (int i = 0; i < lista_perfil.size(); i++) {
            jComboBoxPerfil.addItem(lista_perfil.get(i).getNome());
            System.out.println("perfil " + i + ": " + lista_perfil.get(i).getNome());
        }
    }

    private void preencherListaDeFuncionalidades() {

        defaultListModel = new DefaultListModel();
        for (int i = 0; i < lista_Funcionalidades.size(); i++) {

            defaultListModel.addElement(lista_Funcionalidades.get(i).getNome());
            System.out.println("Funcionalidade " + i + ": " + lista_Funcionalidades.get(i).getNome());
        }

        jListFucionalidades.setModel(defaultListModel);
    }

    private void preencherListaDeFuncionalidadesDoPerfil() {

        defaultListModel = new DefaultListModel();
        if (!lista_FucionalidadesDoPerfil.isEmpty()) {
            for (int i = 0; i < lista_FucionalidadesDoPerfil.size(); i++) {

                defaultListModel.addElement(lista_FucionalidadesDoPerfil.get(i).getNome());
                System.out.println("Funcionalidade " + i + ": " + lista_FucionalidadesDoPerfil.get(i).getNome());
            }
        }

        jListFuncionalidadesDoPerfil.setModel(defaultListModel);

    }

    private void definirEventosListaDeFuncionalidades() {
        jListFucionalidades.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evento) {
                if (evento.getClickCount() == 1) {
                    int index_selecionado = jListFucionalidades.getSelectedIndex();
                    String selecionado = jListFucionalidades.getModel().getElementAt(index_selecionado).toString();

                    for (int i = 0; i < lista_Funcionalidades.size(); i++) {
                        if (selecionado.equals(lista_Funcionalidades.get(i).getNome())) {
                            funcionalidade_selecionada = lista_Funcionalidades.get(i);
                            System.out.println(" >>Fucionalidade selecionada: " + funcionalidade_selecionada.getNome());
                        }
                    }
                }
            }
        });
    }

    private void definirEventoListaDeFuncionalidadesDoPerfil() {
        jListFuncionalidadesDoPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evento) {
                if (evento.getClickCount() == 1) {
                    int index_selecionado = jListFuncionalidadesDoPerfil.getSelectedIndex();
                    String selecionado = jListFuncionalidadesDoPerfil.getModel().getElementAt(index_selecionado).toString();

                    for (int i = 0; i < lista_FucionalidadesDoPerfil.size(); i++) {
                        if (selecionado.equals(lista_FucionalidadesDoPerfil.get(i).getNome())) {
                            funcionalidadeDoPerfil_selecionada = lista_FucionalidadesDoPerfil.get(i);
                            System.out.println(" >>Fucionalidade Do Perfil selecionada: " + funcionalidadeDoPerfil_selecionada.getNome());
                        }
                    }
                }
            }
        });
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
        try {
            if (jComboBoxPerfil.getSelectedItem() != "--Selecione um Projeto--") {
                if (funcionalidade_selecionada != null) {
                    lista_FucionalidadesDoPerfil.add(funcionalidade_selecionada);
                    lista_Funcionalidades.remove(funcionalidade_selecionada);
                } else {
                    JOptionPane.showMessageDialog(this, "Selecione uma Funcionalidade.");
                }
                funcionalidade_selecionada = null;
                preencherListaDeFuncionalidadesDoPerfil();
                preencherListaDeFuncionalidades();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um perfil no combobox.");
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if (jComboBoxPerfil.getSelectedItem() != "--Selecione um Projeto--") {
                if (funcionalidadeDoPerfil_selecionada != null) {
                    lista_Funcionalidades.add(funcionalidadeDoPerfil_selecionada);
                    lista_FucionalidadesDoPerfil.remove(funcionalidadeDoPerfil_selecionada);
                } else {
                    JOptionPane.showMessageDialog(this, "Selecione uma Funcionalidade do Perfil.");
                }
                funcionalidadeDoPerfil_selecionada = null;
                preencherListaDeFuncionalidades();
                preencherListaDeFuncionalidadesDoPerfil();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um perfil no combobox.");
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

    }//GEN-LAST:event_jButton2ActionPerformed

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
