package view.procedimentos;

import controller.CtrlIndicador;
import controller.CtrlProcedimentoDeAnalise;
import facade.FacadeJpa;
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

    FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public ViewProjeto_ProcedimentoAnalise() {
        initComponents();
        Internal.retiraBorda(this);

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
            Object linha[] = {
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
        atualizaListaIndicadoresDoProjeto();
        lista_ProcedimentoAnalise = facadeJpa.getProcedimentodeanaliseJpa().findAllByProjeto(Copia.getProjetoSelecionado().getId());
        preencherTabelaProcedimentoAnalise(lista_ProcedimentoAnalise);
    }

    public void buscarProcedimentoindicador() {
        List<Indicador> listIndicador = new ArrayList<>();
        listIndicador = facadeJpa.getIndicadorJpa().findByParteNome(jTextFieldBuscar.getText(), Copia.getProjetoSelecionado().getId());

        String[] colunas = {"Indicador", "Composição", "Periodicidade", "Responsável"};
        defaultTableModel = new MyDefaultTableModel(colunas, 0, false);
        for (int i = 0; i < listIndicador.size(); i++) {
            Procedimentodeanalise lista = facadeJpa.getProcedimentodeanaliseJpa().findAllByIndicador(listIndicador.get(i).getId());
            Object linha[] = {
                String.valueOf(lista.getIndicadorid().getNome()),
                lista.getComposicao(),
                lista.getPeriodicidade(),
                lista.getResponsavel()
            };
            defaultTableModel.addRow(linha);
        }
        jTableProcedimentoAnalise.setModel(defaultTableModel);

    }

    private void pegarIndicadorSelecionado() {
        int idDoProjeto = Copia.getProjetoSelecionado().getId();
        indicadorSelecionado = ctrlIndicador.buscarIndicadorPeloNome(jTableProcedimentoAnalise.getValueAt(jTableProcedimentoAnalise.getSelectedRow(), 0).toString(), idDoProjeto);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldBuscar = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableProcedimentoAnalise = new javax.swing.JTable();
        jButtonProcedimento = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setTitle("Procedimentos de Análise");

        jLabel1.setText("Buscar Indicador:");

        jTextFieldBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarActionPerformed(evt);
            }
        });

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
        jTableProcedimentoAnalise.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProcedimentoAnaliseMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableProcedimentoAnalise);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonProcedimento)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
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
        preencherTabelaProcedimentoAnaliseDoProjeto();

    }//GEN-LAST:event_jButtonProcedimentoActionPerformed

    private void jTextFieldBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarActionPerformed
        buscarProcedimentoindicador();
    }//GEN-LAST:event_jTextFieldBuscarActionPerformed

    private void jTableProcedimentoAnaliseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProcedimentoAnaliseMouseClicked
        if (evt.getClickCount() >= 2) {
            if (jTableProcedimentoAnalise.getSelectedRow() != -1) {
                String nomeIndicador = jTableProcedimentoAnalise.getValueAt(jTableProcedimentoAnalise.getSelectedRow(), 0).toString();
                System.out.println(">>>" + nomeIndicador);
                Indicador indicador = facadeJpa.getIndicadorJpa().findBYNomeAndProjeto(nomeIndicador, Copia.getProjetoSelecionado().getId());

                Procedimentodeanalise procedimentodeanalise = facadeJpa.getProcedimentodeanaliseJpa().findAllByIndicador(indicador.getId());
                ViewProjeto_ProcedimentoAnaliseNovo viewProjeto_ProcedimentoAnaliseNovo = new ViewProjeto_ProcedimentoAnaliseNovo(null, true);
                viewProjeto_ProcedimentoAnaliseNovo.ShowEditarDialogProcedimentoAnalise(procedimentodeanalise);
                viewProjeto_ProcedimentoAnaliseNovo.setVisible(true);
            }
        }

        preencherTabelaProcedimentoAnaliseDoProjeto();

    }//GEN-LAST:event_jTableProcedimentoAnaliseMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        preencherTabelaProcedimentoAnaliseDoProjeto();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonProcedimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableProcedimentoAnalise;
    private javax.swing.JTextField jTextFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
