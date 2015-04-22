package view.procedimentos;

import controller.CtrlIndicador;
import controller.CtrlProcedimentoDeAnalise;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Indicador;
import model.Procedimentodeanalise;
import util.Copia;
import util.Internal;
import util.MyDefaultTableModel;

/**
 *
 * @author BlenoVale, Géssica
 */
public class ViewProjeto_ProcedimentoAnalise extends javax.swing.JInternalFrame {
    
    private List<Procedimentodeanalise> lista_ProcedimentoAnalise;
    private List<Indicador> lista_indicadores;
    private DefaultTableModel defaultTableModel;
    private CtrlProcedimentoDeAnalise ctrlProcedimentoDeAnalise = new CtrlProcedimentoDeAnalise();
    private Indicador indicadorSelecionado = new Indicador();
    private CtrlIndicador ctrlIndicador = new CtrlIndicador();

    public ViewProjeto_ProcedimentoAnalise() {
        initComponents();
        Internal.retiraBotao(this);
    }
    
    public void atualizaListaIndicadoresDoProjeto() {
        int idDoProjeto = Copia.getProjetoSelecionado().getId();

        lista_indicadores = new ArrayList<>();
        lista_indicadores = ctrlIndicador.getIndicadoresDoProjeto(idDoProjeto);
    }
    
    private void preencherTabelaProcedimentoAnalise(List<Procedimentodeanalise> lista) {
        String[] colunas = {"Indicador", "Composição", "Periodicidade", "Responsável"};
        defaultTableModel = new MyDefaultTableModel(colunas, 0, false);
        for (int i = 0; i < lista.size(); i++) {
            String linha[] = {
                String.valueOf(lista.get(i).getIndicadorid().getNome()),
                lista.get(i).getComposicao(),
                lista.get(i).getPeriodicidade(),
                lista.get(i).getResponsavel()
            };
            defaultTableModel.addRow(linha);
        }
        jTableProcedimentoAnalise.setModel(defaultTableModel);
    }
    
    public void preencherTabelaProcedimentoAnaliseDoProjeto() {
        preencherTabelaProcedimentoAnalise(lista_ProcedimentoAnalise);
    }
    
    private void pegarIndicadorSelecionado(){
        int idDoProjeto = Copia.getProjetoSelecionado().getId();
        indicadorSelecionado = ctrlIndicador.buscarIndicadorPeloNome(jTableProcedimentoAnalise.getValueAt(jTableProcedimentoAnalise.getSelectedRow(), 0).toString(), idDoProjeto);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableProcedimentoAnalise = new javax.swing.JTable();
        jButtonProcedimento = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setTitle("Procedimentos de Análise");

        jLabel1.setText("Buscar Indicador:");

        jTableProcedimentoAnalise.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Indicador", "Composição", "Periodicidade", "Responsável"
            }
        ));
        jScrollPane3.setViewportView(jTableProcedimentoAnalise);

        jButtonProcedimento.setText("Procedimento");
        jButtonProcedimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProcedimentoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 65, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonProcedimento))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonProcedimento)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonProcedimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProcedimentoActionPerformed
        ViewProjeto_ProcedimentoAnaliseNovo viewProjeto_ProcedimentoAnaliseNovo = new ViewProjeto_ProcedimentoAnaliseNovo(null, true);
        viewProjeto_ProcedimentoAnaliseNovo.showNovoProcedimentodeanalise();
        preencherTabelaProcedimentoAnalise(lista_ProcedimentoAnalise);
    }//GEN-LAST:event_jButtonProcedimentoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonProcedimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableProcedimentoAnalise;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
