package view.artefatos;

import controller.CtrlRelatorios;
import facade.FacadeJpa;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Relatorios;
import util.Copia;
import util.Internal;
import util.MyDefaultTableModel;
import controller.RelatorioProcAnalise;
import controller.RelatorioProcColeta;
import util.Texto;

/**
 *
 * @author Géssica
 */
public class ViewProjeto_PlanoDeMedicao extends javax.swing.JInternalFrame {

    private List<Relatorios> listRelatorios;
    private Relatorios relatorios = new Relatorios();
    private MyDefaultTableModel tableModel;
    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();
    private CtrlRelatorios ctrlRelatorios = new CtrlRelatorios();
    

    /**
     * Creates new form view_Projeto_PlanoDeMedicao
     */
    public ViewProjeto_PlanoDeMedicao() {
        initComponents();
        Internal.retiraBorda(this);
    }
    
    private void atualizaListaRelatoriosDoProjeto() {
        int idDoProjeto = Copia.getProjetoSelecionado().getId();

        listRelatorios = new ArrayList<>();
        listRelatorios = ctrlRelatorios.getRelatoriosDoProjeto(idDoProjeto);
    }
    
    public void preencherTabela(List<Relatorios> relatoriosProjeto) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        String[] colunas = {"Relatório", "Autor", "Data"};
        tableModel = new MyDefaultTableModel(colunas, 0, false);
        
        for (int i = 0; i < relatoriosProjeto.size(); i++) {
            String data = simpleDateFormat.format(relatoriosProjeto.get(i).getData());
            String tipoRelatorio = relatoriosProjeto.get(i).getTipoRelatorio();
            if (!"Relatório de Medição".equals(tipoRelatorio)) {
                String linhas[] = new String[]{
                   relatoriosProjeto.get(i).getTipoRelatorio(),
                   relatoriosProjeto.get(i).getAutor(),
                   data
                };
                tableModel.addRow(linhas);
            }
        }
        jTablePlanosGerados.setModel(tableModel);
    }
    
    public void recarregarTabela() {
        listRelatorios = facadeJpa.getRelatoriosJpa().getListByProjeto(Copia.getProjetoSelecionado().getId());
        preencherTabela(listRelatorios);
    }
    
    public void showInformaçõesPlanoMedicao() {
        jTextFieldAutor.setText(Copia.getUsuarioLogado().getNome());
        jTextFieldData.setText(Texto.formataData(new Date()));
        atualizaListaRelatoriosDoProjeto();
        recarregarTabela();
        preencherCampos();
    }
    
    private void tipoRelatorio() throws IOException {
        
        if (jCheckBoxProcColeta.isSelected() || jCheckBoxProcAnalise.isSelected()) {
            RelatorioProcAnalise relatorioProcAnalise = new RelatorioProcAnalise();
            RelatorioProcColeta relatorioProcColeta = new RelatorioProcColeta();
            
            if (jCheckBoxProcColeta.isSelected()){
                relatorioProcColeta.gerarRelatorio();
                try {
                    ////Abrir o PDF na tela usando o Runtime.exec para chamar o executável do AcrobatReader
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler  " + "PlanoDeColeta.pdf");
                }catch (Exception e) {
                    System.out.println("Failed to open file ");
                }
            }    
            if (jCheckBoxProcAnalise.isSelected()){
                relatorioProcAnalise.gerarRelatorio();
                try {
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler  " + "PlanoDeAnálise.pdf");
                }catch (Exception e) {
                    System.out.println("Failed to open file ");
                }
            }    
        }
    }
    
    public boolean validaCampos() {
        if (!validaCheckBox()) {
            JOptionPane.showMessageDialog(null, "Você deve escolher um \"Tipo de Relatório\".");
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
    
    public String pegarCheckSelecionado() {
        if (jCheckBoxProcAnalise.isSelected()) {
            return jCheckBoxProcAnalise.getText();
        } else {
            return jCheckBoxProcColeta.getText();
        }
    }
    
     private void preencherCampos(){
        jTextFieldAutor.setText(Copia.getUsuarioLogado().getNome());
        jTextFieldData.setText(Texto.formataData(new Date()));
     } 
     
      private void cadastraRelatorio() {

        boolean passou = false;  
        Relatorios relatorioAux = new Relatorios();
        
            relatorioAux.setProjetoid(Copia.getProjetoSelecionado());
            relatorioAux.setAutor(Copia.getUsuarioLogado().getNome());
            relatorioAux.setData(new Date());
            relatorioAux.setTipoRelatorio(pegarCheckSelecionado());
            relatorioAux.setObservacao(jTextAreaObservacao.getText()); 

            passou = ctrlRelatorios.cadastrarRelatorio(relatorioAux);

        if (passou == true) {
            System.out.println("Cadastrado com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar");
        }
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
        jLabel4 = new javax.swing.JLabel();
        jCheckBoxProcColeta = new javax.swing.JCheckBox();
        jCheckBoxProcAnalise = new javax.swing.JCheckBox();

        jCheckBox1.setText("jCheckBox1");

        setTitle("Plano de Medição");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTablePlanosGerados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Relatório", "Data", "Autor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        jLabel4.setText("Tipo de Relatório:");

        jCheckBoxProcColeta.setText("Plano de Coleta");
        jCheckBoxProcColeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxProcColetaActionPerformed(evt);
            }
        });

        jCheckBoxProcAnalise.setText("Plano de Análise");
        jCheckBoxProcAnalise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxProcAnaliseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGerar)
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
     
        cadastraRelatorio();
        
        try {
            tipoRelatorio();
        } catch (IOException ex) {
            Logger.getLogger(ViewProjeto_PlanoDeMedicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        showInformaçõesPlanoMedicao();
        
        jTextAreaObservacao.setText("");
        
    }//GEN-LAST:event_jButtonGerarActionPerformed

    private void jCheckBoxProcColetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxProcColetaActionPerformed
      if (jCheckBoxProcColeta.isSelected()) {
            jCheckBoxProcAnalise.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxProcColetaActionPerformed

    private void jCheckBoxProcAnaliseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxProcAnaliseActionPerformed
     if (jCheckBoxProcAnalise.isSelected()) {
            jCheckBoxProcColeta.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxProcAnaliseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
