package view;

import controller.CtrlResultados;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Registroresultados;
import model.Resultados;
import util.CheckDefaultTableModel;
import util.Constantes;
import util.Copia;
import util.Texto;

/**
 *
 * @author Géssica
 */
public class ViewProjeto_ResultadosNovo extends javax.swing.JDialog {

    private CheckDefaultTableModel checkModel;
    private DefaultListModel model_listaDeParticipantes;
    private DefaultListModel model_listaDeUsuariosInteressados;
    private Registroresultados registro;
    private final CtrlResultados ctrlResultados = new CtrlResultados();
    
    private Resultados resultados;
    private boolean ehNovoResultado;
    
    public ViewProjeto_ResultadosNovo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public void showNovoResultadoDialog() {
        this.setTitle("Resultados");
        resultados = new Resultados();

        ehNovoResultado = true;

        this.jLabelUltimaEdicao.setVisible(false);
        this.jTextFieldUltimaEdicao.setVisible(false);
        popularListaParticipantes();
        popularListaUsuariosInteressados();
        jTextFieldData.setText(Copia.getUsuarioLogado().getNome() + ". Em: " + Texto.formataData(new Date()));
        this.setVisible(true);
    }
    
    public void showEditarResultadoDialog(Resultados resultados){
        this.setTitle("Editar Resultado");
        this.resultados= new Resultados();
        this.resultados = resultados;
        
        ehNovoResultado = false;
        
        popularListaParticipantes();
        popularListaUsuariosInteressados();
        preencherCampos();
        this.setVisible(true);
    }

    private void preencherCampos(){
        jTextFieldTitulo.setText(resultados.getTitulo());
        jTextAreaInterpretacao.setText(resultados.getInterpretacao());
        jTextAreaTomadaDecisao.setText(resultados.getTomadaDeDecisao());
        
        registro = new Registroresultados();
        registro = ctrlResultados.buscarUltimoRegistroDoResultado(resultados, Constantes.CADASTRO);
        jTextFieldData.setText(registro.getNomeUsuario() + " Em: " + Texto.formataData(registro.getData()));
        
        registro = new Registroresultados();
        registro = ctrlResultados.buscarUltimoRegistroDoResultado(resultados, Constantes.EDICAO);
        if(registro != null){
            jTextFieldUltimaEdicao.setText(registro.getNomeUsuario() + " Em: " + Texto.formataData(registro.getData()));
        } else {
            jLabelUltimaEdicao.setVisible(false);
            jTextFieldUltimaEdicao.setVisible(false);
        }
    }
    
    private void popularListaParticipantes() {
        model_listaDeParticipantes = new DefaultListModel();
        List<String> listaPerfis = Constantes.preencherListaPerfis();

        checkModel = new CheckDefaultTableModel(new String[]{"selecionar", "Participantes da Interpretação"}, 0, false);

        for (int i = 0; i < listaPerfis.size(); i++) {
            Object[] linha = {
                false,
                listaPerfis.get(i)
            };
            checkModel.addRow(linha);
        }
        jTableParticipantes.setModel(checkModel);
        jTableParticipantes.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableParticipantes.getColumnModel().getColumn(1).setPreferredWidth(500);
    }
    
    private void popularListaUsuariosInteressados() {
        model_listaDeUsuariosInteressados = new DefaultListModel();
        List<String> listaMeio = Constantes.preencherListaMeio();

        checkModel = new CheckDefaultTableModel(new String[]{"selecionar", "Usuários Interessados no Resultado"}, 0, false);

        for (int i = 0; i < listaMeio.size(); i++) {
            Object[] linha = {
                false,
                listaMeio.get(i)
            };
            checkModel.addRow(linha);
        }
        jTableUsuariosInteressados.setModel(checkModel);
        jTableUsuariosInteressados.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableUsuariosInteressados.getColumnModel().getColumn(1).setPreferredWidth(500);
    }
    
    public boolean validaCampos() {
        if (jTextFieldTitulo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Título\" não pode ser vazio.");
            return false;
        } else if (jTextAreaInterpretacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Interpretação\" não pode ser vazio.");
            return false;
        } else if (jTextAreaTomadaDecisao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Tomada de Decisão\" não pode ser vazio.");
            return false;
        }    
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableIndicadorAnalisado = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldTitulo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaTomadaDecisao = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaInterpretacao = new javax.swing.JTextArea();
        jTextFieldData = new javax.swing.JTextField();
        jLabelUltimaEdicao = new javax.swing.JLabel();
        jTextFieldUltimaEdicao = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableParticipantes = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableUsuariosInteressados = new javax.swing.JTable();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableIndicadorAnalisado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Pontos que Agregam Valor", "20/05/2015"},
                {"Variação da velodicade na Sprint", "20/05/2015"},
                {"Íindice de Retrabalho", "20/05/2015"},
                {"Índices de Tarefas Rejeitadas", "20/05/2015"}
            },
            new String [] {
                "Indicador", "Data"
            }
        ));
        jScrollPane1.setViewportView(jTableIndicadorAnalisado);

        jLabel1.setText("Título:");

        jLabel2.setText("Interpretação:");

        jLabel3.setText("Tomada de Decisão:");

        jLabel4.setText("Data:");

        jTextAreaTomadaDecisao.setColumns(20);
        jTextAreaTomadaDecisao.setLineWrap(true);
        jTextAreaTomadaDecisao.setRows(2);
        jTextAreaTomadaDecisao.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextAreaTomadaDecisao);

        jTextAreaInterpretacao.setColumns(20);
        jTextAreaInterpretacao.setLineWrap(true);
        jTextAreaInterpretacao.setRows(2);
        jTextAreaInterpretacao.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextAreaInterpretacao);

        jTextFieldData.setEditable(false);
        jTextFieldData.setBackground(new java.awt.Color(204, 204, 204));

        jLabelUltimaEdicao.setText("Última Edição:");

        jTextFieldUltimaEdicao.setEditable(false);
        jTextFieldUltimaEdicao.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldData)
                            .addComponent(jLabelUltimaEdicao)
                            .addComponent(jTextFieldUltimaEdicao))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(1, 1, 1)
                .addComponent(jTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelUltimaEdicao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldUltimaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Análise Referenciadas no Resultado", jPanel2);

        jTableParticipantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Selecionar", "Participantes da Interpretação"
            }
        ));
        jScrollPane8.setViewportView(jTableParticipantes);

        jTableUsuariosInteressados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Selecionar", "Usuários Interessados no Resultado"
            }
        ));
        jScrollPane6.setViewportView(jTableUsuariosInteressados);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Recursos Humanos", jPanel3);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jTabbedPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar))
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

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (!validaCampos()) {
            return;
        }
        
        resultados.setTitulo(jTextFieldTitulo.getText());
        resultados.setInterpretacao(jTextAreaInterpretacao.getText());
        resultados.setTomadaDeDecisao(jTextAreaTomadaDecisao.getText());

        boolean feito = false;
        if (ehNovoResultado) {
            feito = ctrlResultados.criarNovoResultado(resultados);
        } else {
            feito = ctrlResultados.editarResultadoCompleto(resultados);
        }
        if (feito) {
            this.dispose();
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_ResultadosNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_ResultadosNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_ResultadosNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_ResultadosNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewProjeto_ResultadosNovo dialog = new ViewProjeto_ResultadosNovo(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelUltimaEdicao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableIndicadorAnalisado;
    private javax.swing.JTable jTableParticipantes;
    private javax.swing.JTable jTableUsuariosInteressados;
    private javax.swing.JTextArea jTextAreaInterpretacao;
    private javax.swing.JTextArea jTextAreaTomadaDecisao;
    private javax.swing.JTextField jTextFieldData;
    private javax.swing.JTextField jTextFieldTitulo;
    private javax.swing.JTextField jTextFieldUltimaEdicao;
    // End of variables declaration//GEN-END:variables
}
