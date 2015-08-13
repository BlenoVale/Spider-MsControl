package view.artefatos;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Procedimentodecoleta;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import util.Conexao;
import util.ConnectionFactory;
import util.Copia;
import util.MyDefaultTableModel;
import util.PDF.ConexaoPDF;
import util.Texto;

/**
 *
 * @author Géssica
 */
public class ViewProjeto_PlanoDeMedicao extends javax.swing.JInternalFrame {

    public String[] colunas = {"Data", "Autor"};
    public DefaultTableModel defaultTableModel = new MyDefaultTableModel(colunas, 0, false);
    public List<String> lista = new ArrayList<>();

    /**
     * Creates new form view_Projeto_PlanoDeMedicao
     */
    public ViewProjeto_PlanoDeMedicao() {
        initComponents();
    }
    
    private void tipoRelatorio() {
        
        if (jCheckBoxProcColeta.isSelected() || jCheckBoxProcAnalise.isSelected()) {
            ConexaoPDF conexaoPDF = new ConexaoPDF();
            
            if (jCheckBoxProcColeta.isSelected())
                conexaoPDF.gerarPDF_ProcColeta();
            if (jCheckBoxProcAnalise.isSelected())
                conexaoPDF.gerarPDF_ProcAnalise();
        }
    }
    
    public void showInformaçõesPlanoMedicao() {
        jTextFieldAutor.setText(Copia.getUsuarioLogado().getNome());
        jTextFieldData.setText(Texto.formataData(new Date()));
    }
    
    public boolean validaCampos() {
        if ((! jCheckBoxProcAnalise.isSelected()) && (! jCheckBoxProcColeta.isSelected())) {
            JOptionPane.showMessageDialog(null, "Você deve selecionar um Tipo de Relatório.");
            return false;
        }
        return true;
    }    

    public boolean validaCheckBox() {
        if (jCheckBoxProcAnalise.isSelected()) {
            jCheckBoxProcColeta.setSelected(false);
            return true;
        } else if (jCheckBoxProcColeta.isSelected()) {
            jCheckBoxProcAnalise.setSelected(false);
            return true;
        } else {
            return false;
        }
    }
    
    private void gerarLinhaNaTabela() {
        String linha[] = {
            jTextFieldData.getText(),
            jTextFieldAutor.getText()
        };
        defaultTableModel.addRow(linha);
        jTablePlanosGerados.setModel(defaultTableModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePlanosGerados = new javax.swing.JTable();
        jButtonGerar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldAutor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldData = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jCheckBoxProcColeta = new javax.swing.JCheckBox();
        jCheckBoxProcAnalise = new javax.swing.JCheckBox();

        jCheckBox1.setText("jCheckBox1");

        setTitle("Plano de Medição");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTablePlanosGerados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Data", "Autor"
            }
        ));
        jScrollPane1.setViewportView(jTablePlanosGerados);

        jButtonGerar.setText("Gerar");
        jButtonGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGerarActionPerformed(evt);
            }
        });

        jLabel1.setText("Autor:");

        jTextFieldAutor.setEditable(false);
        jTextFieldAutor.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Data:");

        jTextFieldData.setEditable(false);
        jTextFieldData.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setText("Observação:");

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setRows(5);
        jScrollPane2.setViewportView(jTextAreaObservacao);

        jButton1.setText("Cancelar");

        jLabel4.setText("Tipo de Relatório:");

        jCheckBoxProcColeta.setText("Procedimentos de Coleta");
        jCheckBoxProcColeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxProcColetaActionPerformed(evt);
            }
        });

        jCheckBoxProcAnalise.setText("Procedimentos de Análise");
        jCheckBoxProcAnalise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxProcAnaliseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(13, 13, 13))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxProcColeta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxProcAnalise))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldAutor)
                                    .addComponent(jTextFieldData, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxProcColeta)
                    .addComponent(jLabel4)
                    .addComponent(jCheckBoxProcAnalise))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGerar)
                    .addComponent(jButton1))
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

    private void jButtonGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGerarActionPerformed
        if (!validaCampos()) {
            return;
          }  
        
        tipoRelatorio();
        gerarLinhaNaTabela();
    }//GEN-LAST:event_jButtonGerarActionPerformed

    private void jCheckBoxProcColetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxProcColetaActionPerformed
      
    }//GEN-LAST:event_jCheckBoxProcColetaActionPerformed

    private void jCheckBoxProcAnaliseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxProcAnaliseActionPerformed
     
    }//GEN-LAST:event_jCheckBoxProcAnaliseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonGerar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxProcAnalise;
    private javax.swing.JCheckBox jCheckBoxProcColeta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablePlanosGerados;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldAutor;
    private javax.swing.JTextField jTextFieldData;
    // End of variables declaration//GEN-END:variables
}
