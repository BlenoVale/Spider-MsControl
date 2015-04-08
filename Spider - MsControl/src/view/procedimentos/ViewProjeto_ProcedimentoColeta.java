/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.procedimentos;

import controller.CtrlMedida;
import util.MyDefaultTableModel;
import facade.FacadeJpa;
import java.util.List;
import model.Procedimentodecoleta;
import controller.CtrlProcedimentosColeta;
import java.sql.Array;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Medida;
import model.Registroprocedimentocoleta;
import util.Copia;

/**
 *
 * @author BlenoVale
 */
public class ViewProjeto_ProcedimentoColeta extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewProjeto_ProcedimentoColeta
     */
    private MyDefaultTableModel tableModel;
    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();
    private List<Procedimentodecoleta> listProcedimentoColeta;
    private Medida medidaSelecionada = new Medida();

    public ViewProjeto_ProcedimentoColeta() {
        initComponents();
        iniciarTabela();
        util.Internal.retiraBotao(this);

    }

    public void iniciarTabela() {
        tableModel = new MyDefaultTableModel(new String[]{"Medida", "Periodicidade", "Frequência", "Responsável pela coleta"}, 0, false);
        jTableProcedimentoColeta.setModel(tableModel);
    }

    public void preencherTabela(List<Procedimentodecoleta> procedimentodecoletas) {

        for (int i = 0; i < procedimentodecoletas.size(); i++) {

            String linhas[] = new String[]{
                facadeJpa.getMedidaJpa().findNomeByProjeto(procedimentodecoletas.get(i).getMedidaid().getId(), Copia.getProjetoSelecionado().getId()),
                procedimentodecoletas.get(i).getPeriodicidade(),
                String.valueOf(procedimentodecoletas.get(i).getFrequencia()),
                procedimentodecoletas.get(i).getResponsavelPelaColeta()
            };
            tableModel.addRow(linhas);
        }
        jTableProcedimentoColeta.setModel(tableModel);
    }

    public void recarregarTabela() {
        iniciarTabela();
        listProcedimentoColeta = facadeJpa.getProcedimentoColetaJpa().getListByProjeto(Copia.getProjetoSelecionado().getId());
        preencherTabela(listProcedimentoColeta);
    }

    public void recarregarTabelaBusca() {
        iniciarTabela();
        CtrlProcedimentosColeta ctrlProcedimentosColeta = new CtrlProcedimentosColeta();
        listProcedimentoColeta = ctrlProcedimentosColeta.findByProjetoBuscar(Copia.getProjetoSelecionado().getId(), jTextFieldBuscar.getText());
        preencherTabela(listProcedimentoColeta);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProcedimentoColeta = new javax.swing.JTable();
        jButtonProcedimento = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setTitle("Procedimentos de Coleta");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Buscar medida:");

        jTextFieldBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarActionPerformed(evt);
            }
        });

        jTableProcedimentoColeta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Medida", "Mnemônico", "Responsável pela Coleta", "Possui Procedimento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProcedimentoColeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProcedimentoColetaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableProcedimentoColeta);

        jButtonProcedimento.setText("Procedimento");
        jButtonProcedimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProcedimentoActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh3.png"))); // NOI18N
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonProcedimento)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonProcedimento)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        recarregarTabela();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonProcedimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProcedimentoActionPerformed

        ViewProjeto_ProcedimentoColetaNovo procedimentoColetaNovo = new ViewProjeto_ProcedimentoColetaNovo(null, true);
        procedimentoColetaNovo.showDialogCadastrar();
        procedimentoColetaNovo.setVisible(true);
        this.recarregarTabela();
    }//GEN-LAST:event_jButtonProcedimentoActionPerformed

    private void jTextFieldBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarActionPerformed
        recarregarTabelaBusca();
    }//GEN-LAST:event_jTextFieldBuscarActionPerformed

    private void jTableProcedimentoColetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProcedimentoColetaMouseClicked

        if (evt.getClickCount() >= 2) {
            editarProcedimentoColeta();
            recarregarTabela();            
        }
    }//GEN-LAST:event_jTableProcedimentoColetaMouseClicked

    private void editarProcedimentoColeta() {
        pegaMedida();
        ViewProjeto_ProcedimentoColetaNovo procedimentoColetaNovo = new ViewProjeto_ProcedimentoColetaNovo(null, true);
        procedimentoColetaNovo.showDialogEditar(medidaSelecionada);
        procedimentoColetaNovo.setVisible(true);
        
    }
     public String captureNomeLinhaTabela(){
        return jTableProcedimentoColeta.getValueAt(jTableProcedimentoColeta.getSelectedRow() , 0).toString();
    }
    public void pegaMedida(){
        CtrlMedida ctrlMedida = new CtrlMedida();
        medidaSelecionada =  ctrlMedida.buscarMedidaPeloNome(captureNomeLinhaTabela() , Copia.getProjetoSelecionado().getId());
    }
   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonProcedimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProcedimentoColeta;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables

    
}
