/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.medidas;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import controller.Excel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.Coleta;
import util.FixedColumnTable;

/**
 *
 * @author Spider
 */
public class viewExcelDialog extends javax.swing.JDialog {

    private int linhaInicio = 0;
    private int linhaFim = 0;
    private int ultimaLinha = 0;
    private  List<Coleta> listaColeta;

    public viewExcelDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void iniciarTabela() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"<html><b>Spider - MsControl</b></html>"}, 0);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setModel(model);
    }

    public List<Coleta> showExcelDialog(String file) {

        listaColeta = new ArrayList<>();
        if (file.endsWith(".xlsx")) {
            jTextField1.setText(file);

            new Excel().processAll(jTable1, file);

            fixaColunaTabela(jTable1, jScrollPane1, 1);
            ultimaLinha = jTable1.getRowCount();

            jPanel1.setVisible(true);
            this.pack();
        } else {
            jTextField1.setText("");
            iniciarTabela();
            jPanel1.setVisible(false);
            this.pack();
        }

        popularCombobox();
        
        this.setVisible(true);
        return listaColeta;
    }

    private void popularCombobox() {
        String nomesDasColunas[] = new String[jTable1.getColumnCount() + 1];
        TableColumnModel modeloColuna = jTable1.getColumnModel();
        nomesDasColunas[0] = "-Colunas-";
        for (int i = 1; i < nomesDasColunas.length; i++) {
            nomesDasColunas[i] = modeloColuna.getColumn(i - 1).getHeaderValue().toString();
        }
        DefaultComboBoxModel boxModel = new DefaultComboBoxModel(nomesDasColunas);
        jComboBoxColunas.setModel(boxModel);
    }

    public void fixaColunaTabela(JTable table, JScrollPane scrollPane, int colunasFixas) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(75);
        FixedColumnTable fct = new FixedColumnTable(colunasFixas, scrollPane);
    }
    
    private boolean validarCampos() {
        int cont = 0;
        String mensagem = null;

        if (jTextFieldLinhaInicial.getText().isEmpty()) {
            cont++;
            mensagem = "Campo \"Da linha\" não pode ser vazio.";
        } else if (Integer.parseInt(jTextFieldLinhaInicial.getText()) == 0) {
            cont++;
            mensagem = "Campo \"Da linha\" não pode ser Zero.";
        } else {
            linhaInicio = Integer.parseInt(jTextFieldLinhaInicial.getText()) - 1;
        }

        if (jTextFieldLinhaFianal.getText().isEmpty()) {
            cont++;
            mensagem = "Campo \"Até a linha\" não pode ser vazio.";
        } else if (Integer.parseInt(jTextFieldLinhaFianal.getText()) == 0) {
            cont++;
            mensagem = "Campo \"Até a linha\" não pode ser Zero.";
        } else {
            linhaFim = Integer.parseInt(jTextFieldLinhaFianal.getText()) - 1;
        }

        if (ultimaLinha <= linhaInicio || ultimaLinha <= linhaFim) {
            cont++;
            mensagem = "Linha digitada não existe na tabela.";
        } else if (linhaInicio > linhaFim) {
            cont++;
            mensagem = "A linha inicial não poder ser maior que a linha final.";
        }

        if (jComboBoxColunas.getSelectedItem() == "-Colunas-") {
            cont++;
            mensagem = "Selecione uma coluna no combobox.";
        }

        if (cont == 0) {
            return true;
        } else if (cont == 1) {
            JOptionPane.showMessageDialog(this, mensagem);
        } else {
            JOptionPane.showMessageDialog(this, "Mais de um campo estão inválidos.");
        }
        return false;
    }

    private boolean validarValoresSelecionados() {
        int cont = linhaInicio;
        int colunaSelecionada = jComboBoxColunas.getSelectedIndex() - 1;
        System.out.println("Combobox selecionado: " + jComboBoxColunas.getSelectedItem().toString());
        boolean passou = true;

       listaColeta = new ArrayList<>();
        while (cont <= linhaFim) {
            try {
                if (jTable1.getValueAt(cont, colunaSelecionada) != null) {
                    Coleta coleta = new Coleta();
                    coleta.setValorDaColeta(Double.parseDouble(jTable1.getValueAt(cont, colunaSelecionada).toString()));
                    listaColeta.add(coleta);
                }
                cont++;
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, "Existem caracteres invalidos no Excel,\nApenas valores numericos são aceitos.");
                passou = false;
                break;
            }
        }
        // apenas para testes
        for (int i = 0; i < listaColeta.size(); i++) {
            System.out.println(i+"---->Coletas selecionadas: " + listaColeta.get(i).getValorDaColeta());
        }
        return passou;
    }

    private void validaJtexts(java.awt.event.KeyEvent evt) {
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxColunas = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldLinhaInicial = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldLinhaFianal = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Leitor de Excel");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField1.setEnabled(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Selecione uma coluna:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Do intervalo");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Linha inicial:");

        jTextFieldLinhaInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldLinhaInicialKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Linha final:");

        jTextFieldLinhaFianal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldLinhaFianalKeyTyped(evt);
            }
        });

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxColunas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLinhaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLinhaFianal, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxColunas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldLinhaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldLinhaFianal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSalvar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextFieldLinhaInicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLinhaInicialKeyTyped
        validaJtexts(evt);
    }//GEN-LAST:event_jTextFieldLinhaInicialKeyTyped

    private void jTextFieldLinhaFianalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLinhaFianalKeyTyped
        validaJtexts(evt);
    }//GEN-LAST:event_jTextFieldLinhaFianalKeyTyped

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (!validarCampos()) {
            return;
        } else if (!validarValoresSelecionados()) {
            return;
        }
        this.dispose();
    }//GEN-LAST:event_jButtonSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox jComboBoxColunas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldLinhaFianal;
    private javax.swing.JTextField jTextFieldLinhaInicial;
    // End of variables declaration//GEN-END:variables
}
