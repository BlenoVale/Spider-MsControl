package view.artefatos;

import controller.CtrlRelatorios;
import controller.Relatorio;
import facade.FacadeJpa;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Relatorios;
import util.Copia;
import util.Internal;
import util.MyDefaultTableModel;
import util.Texto;

/**
 *
 * @author Géssica
 */
public class ViewProjeto_Relatorio extends javax.swing.JInternalFrame {

    private List<Relatorios> listRelatorios;
    private CtrlRelatorios ctrlRelatorios = new CtrlRelatorios();
    private MyDefaultTableModel tableModel;
    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public ViewProjeto_Relatorio() {
        initComponents();
        Internal.retiraBorda(this);
    }

    private void atualizaListaRelatorioGeralDoProjeto() {
        int idDoProjeto = Copia.getProjetoSelecionado().getId();

        listRelatorios = new ArrayList<>();
        listRelatorios = ctrlRelatorios.getRelatoriosDoProjeto(idDoProjeto);
    }

    public void preencherTabela(List<Relatorios> relatoriosProjeto) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String[] colunas = {"Autor", "Data"};
        tableModel = new MyDefaultTableModel(colunas, 0, false);

        for (int i = 0; i < relatoriosProjeto.size(); i++) {
            String data = simpleDateFormat.format(relatoriosProjeto.get(i).getData());
            String tipoRelatorio = relatoriosProjeto.get(i).getTipoRelatorio();
            if ("Relatório de Medição".equals(tipoRelatorio)) {
                String linhas[] = new String[]{
                      relatoriosProjeto.get(i).getAutor(),
                      data
                };
                tableModel.addRow(linhas);
            }    
        }
        jTableRelatoriosGerados.setModel(tableModel);
    }

    public void recarregarTabela() {
        listRelatorios = facadeJpa.getRelatoriosJpa().getListByProjeto(Copia.getProjetoSelecionado().getId());
        preencherTabela(listRelatorios);
    }

    public void showInformaçõesPlanoMedicao() {
        jTextFieldAutor.setText(Copia.getUsuarioLogado().getNome());
        jTextFieldData.setText(Texto.formataData(new Date()));
        atualizaListaRelatorioGeralDoProjeto();
        recarregarTabela();
        preencherCampos();
    }

    private void preencherCampos() {
        jTextFieldAutor.setText(Copia.getUsuarioLogado().getNome());
        jTextFieldData.setText(Texto.formataData(new Date()));
    }

    private void cadastraRelatorio() {

        boolean passou = false;
        Relatorios relatorioAux = new Relatorios();

        relatorioAux.setProjetoid(Copia.getProjetoSelecionado());
        relatorioAux.setAutor(Copia.getUsuarioLogado().getNome());
        relatorioAux.setData(new Date());
        relatorioAux.setTipoRelatorio("Relatório de Medição");
        relatorioAux.setObservacao(jTextAreaObservacao.getText());

        passou = ctrlRelatorios.cadastrarRelatorio(relatorioAux);

        if (passou == true) {
            System.out.println("Cadastrado com sucesso.");
            atualizaListaRelatorioGeralDoProjeto();
            preencherTabela(listRelatorios);
        } else {
            System.out.println("Erro ao cadastrar");
        }
    }
    
    //Abrir o PDF na tela usando o Runtime.exec para chamar o executável do AcrobatReader
    public final void openPDF() {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler  " + "RelatórioDeMedição.pdf");
        }catch (Exception e) {
            System.out.println("Failed to open file ");
        }

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
        jTextAreaObservacao = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

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

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setRows(5);
        jScrollPane2.setViewportView(jTextAreaObservacao);

        jLabel3.setText("Observação:");

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
                        .addComponent(jButtonGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
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
        cadastraRelatorio();

        Relatorio relatorio = new Relatorio();
        try {
            relatorio.gerarRelatorio();
        } catch (IOException ex) {
            Logger.getLogger(ViewProjeto_Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        openPDF();
        
        jTextAreaObservacao.setText("");
        
    }//GEN-LAST:event_jButtonGerarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGerar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableRelatoriosGerados;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldAutor;
    private javax.swing.JTextField jTextFieldData;
    // End of variables declaration//GEN-END:variables
}
