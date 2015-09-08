package view.artefatos;

import controller.CtrlAnalise;
import controller.CtrlValores;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Analise;
import model.Valorindicador;
import util.Copia;
import util.MyDefaultTableModel;
import util.PDF.ConexaoPDF;
import util.Texto;

/**
 *
 * @author Géssica
 */
public class ViewProjeto_Relatorio extends javax.swing.JInternalFrame {

    public String[] colunas = {"Data", "Autor"};
    public DefaultTableModel defaultTableModel = new MyDefaultTableModel(colunas, 0, false);
    public List<String> lista = new ArrayList<>();
    private final CtrlAnalise ctrlAnalise = new CtrlAnalise();
    private final CtrlValores ctrlValores = new CtrlValores();
    private List<Analise> listaAnalises;
    private List<Valorindicador> listaValoresIndicador;


    public ViewProjeto_Relatorio() {
        initComponents();
    }

    public void showInformaçõesPlanoMedicao() {
        jTextFieldAutor.setText(Copia.getUsuarioLogado().getNome());
        jTextFieldData.setText(Texto.formataData(new Date()));
    }

    private void gerarLinhaNaTabela() {
        String linha[] = {
            jTextFieldData.getText(),
            jTextFieldAutor.getText()
        };
        defaultTableModel.addRow(linha);
        jTableRelatoriosGerados.setModel(defaultTableModel);
    }

    public void buscaAnaliseDoProjeto(){
        listaAnalises = new ArrayList<>();
        listaAnalises = ctrlAnalise.buscarAnalisesDoProjeto(Copia.getProjetoSelecionado().getId());
        
    }
    
    public void buscarValoresDoindicador(Date dataDe, Date dataAte, int idIndicador){
        listaValoresIndicador = ctrlValores.buscarValorIndicadorPorDatas(dataDe, dataAte, idIndicador, Copia.getProjetoSelecionado().getId());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRelatoriosGerados = new javax.swing.JTable();
        jButtonGerar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldAutor = new javax.swing.JTextField();
        jTextFieldData = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setTitle("Relatório");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableRelatoriosGerados.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableRelatoriosGerados);

        jButtonGerar.setText("Gerar");
        jButtonGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGerarActionPerformed(evt);
            }
        });

        jLabel1.setText("Autor:");

        jLabel2.setText("Data:");

        jTextFieldAutor.setEditable(false);
        jTextFieldAutor.setBackground(new java.awt.Color(204, 204, 204));

        jTextFieldData.setEditable(false);
        jTextFieldData.setBackground(new java.awt.Color(204, 204, 204));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel3.setText("Observação:");

        jButton1.setText("Cancelar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldAutor)
                                    .addComponent(jTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButtonGerar))
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
        gerarLinhaNaTabela();
        buscaAnaliseDoProjeto();
        buscarValoresDoindicador(listaAnalises.get(0).getAnaliseDE(), listaAnalises.get(0).getAnaliseATE(), listaAnalises.get(0).getIndicadorid().getId());
        ConexaoPDF conexaoPDF = new ConexaoPDF();
        conexaoPDF.gerarPDF_Geral(listaValoresIndicador, listaAnalises.get(0).getIndicadorid().getProcedimentodeanaliseList().get(0).getGraficoNome());
    }//GEN-LAST:event_jButtonGerarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonGerar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableRelatoriosGerados;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldAutor;
    private javax.swing.JTextField jTextFieldData;
    // End of variables declaration//GEN-END:variables
}
