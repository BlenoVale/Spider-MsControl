package view.indicadores;

import controller.CtrlIndicador;
import javax.swing.JOptionPane;
import model.Indicador;
import model.Registroindicador;
import util.Constantes;
import util.MyDefaultTableModel;
import util.Texto;

/**
 *
 * @author DAN JHONATAN
 */
public class ViewNovaAprovacaoDialog extends javax.swing.JDialog {

    private final CtrlIndicador ctrlIndicador = new CtrlIndicador();
    private Indicador indicador;
    private boolean ehDetalhes;

    public ViewNovaAprovacaoDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        buttonGroup1.add(jRadioButtonAprovado);
        buttonGroup1.add(jRadioButtonNaoAprovado);

        this.setLocationRelativeTo(null);
    }

    public void showNovaAprovacaoDialog(Indicador indicador) {
        this.indicador = indicador;
        ehDetalhes = false;

        jLabelNomeIndicador.setText(indicador.getNome());
        if (indicador.getAprovacao() == Constantes.APROVADO)
            jRadioButtonAprovado.setSelected(true);
        else if (indicador.getAprovacao() == Constantes.NAO_APROVADO)
            jRadioButtonNaoAprovado.setSelected(true);

        MyDefaultTableModel model = new MyDefaultTableModel(new String[]{"Necessidade de informação", "Objetivo de medição"}, 0, false);
        model.addRow(new String[]{indicador.getObjetivoDeQuestaoid().getNome(), indicador.getObjetivoDeQuestaoid().getObjetivoDeMedicaoid().getNome()});
        jTableRelacionado.setModel(model);

        mostrarUltimaAnalise();
        this.pack();

        this.setVisible(true);
    }

    public void showDetalhesDoIndicadorDialog(Indicador indicador) {
        this.indicador = indicador;
        ehDetalhes = true;

        jLabelNomeIndicador.setText(indicador.getNome());
        if (indicador.getAprovacao() == Constantes.APROVADO) {
            jRadioButtonAprovado.setSelected(true);
            jRadioButtonNaoAprovado.setVisible(false);
        } else if (indicador.getAprovacao() == Constantes.NAO_APROVADO) {
            jRadioButtonAprovado.setVisible(false);
            jRadioButtonNaoAprovado.setSelected(true);
        } else {
            jRadioButtonAprovado.setVisible(false);
            jRadioButtonNaoAprovado.setVisible(false);
            jRadioButtonNaoAnalisado.setVisible(true);
        }

        MyDefaultTableModel model = new MyDefaultTableModel(new String[]{"Necessidade de informação", "Objetivo de medição"}, 0, false);
        model.addRow(new String[]{indicador.getObjetivoDeQuestaoid().getNome(), indicador.getObjetivoDeQuestaoid().getObjetivoDeMedicaoid().getNome()});
        jTableRelacionado.setModel(model);

        jTextAreaObservacao.setEditable(false);
        jButtonSalvar.setVisible(false);
        jButtonCancelar.setText("Ok");

        mostrarUltimaAnalise();
        this.pack();

        this.setVisible(true);
    }

    private void mostrarUltimaAnalise() {
        Registroindicador registro = ctrlIndicador.buscarUltimoRegistroDoIndicador(indicador, Constantes.ANALISE_DE_APROVACAO);
        System.out.println("Registro = " + registro);
        if (registro != null && indicador.getAprovacao() != Constantes.NAO_ANALISADO) {
            jPanelUltimaAnalise.setVisible(true);
            jTextFieldResponsavel.setText(registro.getNomeUsuario());
            jTextFieldData.setText(Texto.formataData(registro.getData()));
            jTextAreaObservacao.setText(registro.getDescricao());
        } else if (ehDetalhes) {
            jScrollPane2.setVisible(false);
            jLabelObservacao.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButtonAprovado = new javax.swing.JRadioButton();
        jRadioButtonNaoAprovado = new javax.swing.JRadioButton();
        jLabelObservacao = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableRelacionado = new javax.swing.JTable();
        jLabelNomeIndicador = new javax.swing.JLabel();
        jPanelUltimaAnalise = new javax.swing.JPanel();
        jPanelUltimaAnalise.setVisible(false);
        jLabel4 = new javax.swing.JLabel();
        jTextFieldResponsavel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldData = new javax.swing.JTextField();
        jRadioButtonNaoAnalisado = new javax.swing.JRadioButton();
        jRadioButtonNaoAnalisado.setVisible(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aprovação");
        setResizable(false);

        jLabel1.setText("Indicador:");

        jLabel2.setText("Objetivos Relacionados:");

        jLabel3.setText("Status:");

        jRadioButtonAprovado.setText("Aprovado");

        jRadioButtonNaoAprovado.setText("Não aprovado");

        jLabelObservacao.setText("Observação:");

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setLineWrap(true);
        jTextAreaObservacao.setRows(3);
        jTextAreaObservacao.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextAreaObservacao);

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

        jTableRelacionado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Questão", "Objetivo de Medição"
            }
        ));
        jTableRelacionado.setAutoscrolls(false);
        jTableRelacionado.setRowSelectionAllowed(false);
        jScrollPane3.setViewportView(jTableRelacionado);

        jLabelNomeIndicador.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelNomeIndicador.setText("Nome do indicador");

        jPanelUltimaAnalise.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ultima análise de aprovação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel4.setText("Responsável:");

        jTextFieldResponsavel.setEditable(false);
        jTextFieldResponsavel.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setText("Data:");

        jTextFieldData.setEditable(false);
        jTextFieldData.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanelUltimaAnaliseLayout = new javax.swing.GroupLayout(jPanelUltimaAnalise);
        jPanelUltimaAnalise.setLayout(jPanelUltimaAnaliseLayout);
        jPanelUltimaAnaliseLayout.setHorizontalGroup(
            jPanelUltimaAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUltimaAnaliseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUltimaAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUltimaAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldResponsavel)
                    .addComponent(jTextFieldData))
                .addContainerGap())
        );
        jPanelUltimaAnaliseLayout.setVerticalGroup(
            jPanelUltimaAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUltimaAnaliseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUltimaAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUltimaAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jRadioButtonNaoAnalisado.setSelected(true);
        jRadioButtonNaoAnalisado.setText("Não analisado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelNomeIndicador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelUltimaAnalise, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabelObservacao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButtonAprovado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonNaoAprovado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonNaoAnalisado)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar)
                        .addGap(10, 10, 10))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelNomeIndicador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jRadioButtonAprovado)
                    .addComponent(jRadioButtonNaoAprovado)
                    .addComponent(jRadioButtonNaoAnalisado))
                .addGap(18, 18, 18)
                .addComponent(jPanelUltimaAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObservacao)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        if (!jRadioButtonAprovado.isSelected() && !jRadioButtonNaoAprovado.isSelected()) {
            JOptionPane.showMessageDialog(this, "Você deve selecionar o status do indicador.");
            return;
        }

        if (jRadioButtonAprovado.isSelected())
            indicador.setAprovacao(Constantes.APROVADO);
        else if (jRadioButtonNaoAprovado.isSelected())
            indicador.setAprovacao(Constantes.NAO_APROVADO);

        if (jRadioButtonNaoAprovado.isSelected() && jTextAreaObservacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Você deve digitar uma observação.");
            return;
        }

        ctrlIndicador.editarIndicador(indicador);
        ctrlIndicador.registrar(indicador, Constantes.ANALISE_DE_APROVACAO, jTextAreaObservacao.getText());
        this.dispose();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelNomeIndicador;
    private javax.swing.JLabel jLabelObservacao;
    private javax.swing.JPanel jPanelUltimaAnalise;
    private javax.swing.JRadioButton jRadioButtonAprovado;
    private javax.swing.JRadioButton jRadioButtonNaoAnalisado;
    private javax.swing.JRadioButton jRadioButtonNaoAprovado;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableRelacionado;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldData;
    private javax.swing.JTextField jTextFieldResponsavel;
    // End of variables declaration//GEN-END:variables
}
