package view.medidas;

import model.Medida;
import controller.CtrlMedida;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.Constantes;
import util.Copia;
import model.Registromedida;

import javax.swing.JOptionPane;
import util.Texto;

/**
 *
 * @author BlenoVale, DAN JHONATAN, paulo
 */
public class ViewProjeto_MedicaoDefinicao_Novo extends javax.swing.JDialog {

    private Medida medida = new Medida();
    private boolean novaMedida;
    private List<Registromedida> registromedida;

    public ViewProjeto_MedicaoDefinicao_Novo(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public boolean verificaCampos() {

        if (jTextFieldNomeMedida.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Medida\" é obrigatório");
            return false;
        } else if (jTextAreaDefinicao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Definição\" é obrigatório");
            return false;
        } else if (jTextFieldResponsavel.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Responsável pela definição\" é obrigatório");
            return false;
        } else if (jTextFieldMnemonico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Mnemônico\" é obrigatório");
            return false;
        } else if (jTextFieldEscala.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Escala\" é obrigatório");
            return false;
        } else if (jTextFieldFaixaInicio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Faixa\" é obrigatório");
            return false;
        } else if (jTextFieldFaixaFim.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Faixa\" é obrigatório");
            return false;
        } else {
            return true;
        }
    }

    public void JTextFieldSomenteNumeros(java.awt.event.KeyEvent evt) {
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }

    public boolean validaFaixa() {
        double numInicio = Double.parseDouble(jTextFieldFaixaInicio.getText());
        double numFim = Double.parseDouble(jTextFieldFaixaFim.getText());

        if (numFim < numInicio) {
            return false;
        } else {
            return true;
        }
    }

    public void showCadastrarDialog() {
        this.setTitle("Cadastrar definição medida");
        this.jLabelUltimaEdicao.setVisible(false);
        this.jTextFieldUltimaEdicao.setVisible(false);
        this.jTextFieldCadastradoPor.setText(Copia.getUsuarioLogado().getNome() + " " + Texto.formataData(new Date()));

        novaMedida = true;
    }

    public void showEditarDialog(Medida medidaSelecionada) {

        this.setTitle("Editar definição medida");
        popularCamposEditarMedida(medidaSelecionada);
        popularCamposEditarMedidaRegistro(medidaSelecionada);

        medida = medidaSelecionada;

        novaMedida = false;

    }

    public void popularCamposEditarMedida(Medida medida) {

        jTextFieldNomeMedida.setText(medida.getNome());
        jTextAreaDefinicao.setText(medida.getDefinicao());
        jTextFieldResponsavel.setText(medida.getPontoDeVista());
        jTextFieldMnemonico.setText(medida.getMnemonico());
        jTextFieldEscala.setText(medida.getEscala());
        jTextFieldFaixaInicio.setText(String.valueOf(medida.getFaixaInicio()));
        jTextFieldFaixaFim.setText(String.valueOf(medida.getFaixaFim()));
        jTextAreaObservacoes.setText(medida.getObservacao());

    }

    public void popularCamposEditarMedidaRegistro(Medida medida) {

        registromedida = new ArrayList<>();
        registromedida = facade.FacadeJpa.getInstance().getRegistroMedidaJpa().findRegistroByIdMedida(Constantes.CADASTRO, medida.getId());
        jTextFieldCadastradoPor.setText(registromedida.get(0).getNomeUsuario() + " Em " + Texto.formataData(registromedida.get(0).getData()));

        registromedida = new ArrayList<>();
        registromedida = facade.FacadeJpa.getInstance().getRegistroMedidaJpa().findRegistroByIdMedida(Constantes.EDICAO, medida.getId());

        if (registromedida.isEmpty()) {
            jTextFieldUltimaEdicao.setVisible(false);
            jLabelUltimaEdicao.setVisible(false);
        } else {
            jTextFieldUltimaEdicao.setText(registromedida.get(registromedida.size() - 1).getNomeUsuario() + " Em " + Texto.formataData(registromedida.get(registromedida.size() - 1).getData()));
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldNomeMedida = new javax.swing.JTextField();
        jLabelDefinicao = new javax.swing.JLabel();
        jLabelResponsavel = new javax.swing.JLabel();
        jLabel4CadastradoPor = new javax.swing.JLabel();
        jLabelUltimaEdicao = new javax.swing.JLabel();
        jLabelMnemonico = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelFaixa = new javax.swing.JLabel();
        jLabelObservacoes = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaObservacoes = new javax.swing.JTextArea();
        jLabelDe = new javax.swing.JLabel();
        jTextFieldFaixaInicio = new javax.swing.JTextField();
        jLabelAte = new javax.swing.JLabel();
        jTextFieldFaixaFim = new javax.swing.JTextField();
        jTextFieldEscala = new javax.swing.JTextField();
        jTextFieldMnemonico = new javax.swing.JTextField();
        jTextFieldUltimaEdicao = new javax.swing.JTextField();
        jTextFieldCadastradoPor = new javax.swing.JTextField();
        jLabelNomeMedida = new javax.swing.JLabel();
        jTextFieldResponsavel = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaDefinicao = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro nova definição medida");
        setPreferredSize(new java.awt.Dimension(622, 500));
        setResizable(false);

        jTextFieldNomeMedida.setToolTipText("Nome da Medida a ser cadastrada (ex: Tarefas Planejadas, Horas Trabalhadas, Nº de bugs, ...).");

        jLabelDefinicao.setText("Definição:");

        jLabelResponsavel.setText("Responsável pela definição:");

        jLabel4CadastradoPor.setText("Cadastrado por:");

        jLabelUltimaEdicao.setText("Última edição:");

        jLabelMnemonico.setText("Mnemônico:");

        jLabel1.setText("Escala:");

        jLabelFaixa.setText("Faixa:");

        jLabelObservacoes.setText("Observações:");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jTextAreaObservacoes.setColumns(20);
        jTextAreaObservacoes.setRows(5);
        jScrollPane3.setViewportView(jTextAreaObservacoes);

        jLabelDe.setText("De:");

        jTextFieldFaixaInicio.setToolTipText("Limites de valores da escala definida de acordo com dados históricos ou com metas estabelecidas (ex: [0, 10], ...).");
        jTextFieldFaixaInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFaixaInicioKeyTyped(evt);
            }
        });

        jLabelAte.setText("Até:");

        jTextFieldFaixaFim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFaixaFimKeyTyped(evt);
            }
        });

        jTextFieldEscala.setToolTipText("Valores que podem ser atribuídos à medida (ex: número inteiros, números reais positivos, ...).");

        jTextFieldMnemonico.setToolTipText("Sigla que representa a medida (ex: THT - Total de Horas Trabalhadas, NG - Número de Bugs, ...).");

        jTextFieldUltimaEdicao.setEnabled(false);

        jTextFieldCadastradoPor.setEnabled(false);

        jLabelNomeMedida.setText("Medida:");

        jTextFieldResponsavel.setToolTipText("Pessoa que definiu a medida que está sendo cadastrada.");

        jTextAreaDefinicao.setColumns(20);
        jTextAreaDefinicao.setRows(5);
        jTextAreaDefinicao.setToolTipText("Uma breve definição e/ou descrição sobre a medida que está sendo cadastrada.");
        jScrollPane4.setViewportView(jTextAreaDefinicao);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCancelar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabelResponsavel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelNomeMedida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelDefinicao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelMnemonico, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4CadastradoPor)
                            .addComponent(jLabelUltimaEdicao)
                            .addComponent(jLabel1)
                            .addComponent(jLabelObservacoes)
                            .addComponent(jLabelFaixa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addComponent(jTextFieldResponsavel)
                            .addComponent(jTextFieldMnemonico)
                            .addComponent(jTextFieldUltimaEdicao)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelDe)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldFaixaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelAte)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldFaixaFim, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 145, Short.MAX_VALUE))
                            .addComponent(jTextFieldEscala)
                            .addComponent(jTextFieldCadastradoPor)
                            .addComponent(jTextFieldNomeMedida, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeMedida)
                    .addComponent(jTextFieldNomeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabelDefinicao))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelResponsavel)
                    .addComponent(jTextFieldResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCadastradoPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4CadastradoPor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUltimaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUltimaEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMnemonico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMnemonico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFaixaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFaixaFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDe)
                    .addComponent(jLabelFaixa)
                    .addComponent(jLabelAte))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabelObservacoes))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancelar))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldFaixaFimKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFaixaFimKeyTyped
        JTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldFaixaFimKeyTyped

    private void jTextFieldFaixaInicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFaixaInicioKeyTyped
        JTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldFaixaInicioKeyTyped

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        CtrlMedida ctrlMedida = new CtrlMedida();
        boolean save;

        if (!verificaCampos()) {
            return;
        }

        if (!validaFaixa()) {
            JOptionPane.showMessageDialog(null, "A faixa de inicio não deve ser maior que a de fim.", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        medida.setProjetoId(Copia.getProjetoSelecionado().getId());
        medida.setNome(jTextFieldNomeMedida.getText());
        medida.setDefinicao(jTextAreaDefinicao.getText());
        medida.setPontoDeVista(jTextFieldResponsavel.getText());
        medida.setMnemonico(jTextFieldMnemonico.getText());
        medida.setEscala(jTextFieldEscala.getText());
        medida.setFaixaInicio(Double.parseDouble(jTextFieldFaixaInicio.getText()));
        medida.setFaixaFim(Double.parseDouble(jTextFieldFaixaFim.getText()));
        medida.setObservacao(jTextAreaObservacoes.getText());
        medida.setData(new Date());

        if (novaMedida) {

            if (ctrlMedida.checkNomeMedida(jTextFieldNomeMedida.getText())) {
                JOptionPane.showMessageDialog(null, "Já existe uma medida com esse nome, escolha outro nome.", "", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (ctrlMedida.checkNomeMnemonico(jTextFieldMnemonico.getText())) {
                JOptionPane.showMessageDialog(null, "Já existe um mnemônico com esse nome, escolha outro nome.", "", JOptionPane.ERROR_MESSAGE);
                return;
            }

            save = ctrlMedida.criarNovaMedida(medida);
        } else 
            save = ctrlMedida.editarMedida(medida);
        
        if (save) 
            this.dispose();
        

    }//GEN-LAST:event_jButtonSalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4CadastradoPor;
    private javax.swing.JLabel jLabelAte;
    private javax.swing.JLabel jLabelDe;
    private javax.swing.JLabel jLabelDefinicao;
    private javax.swing.JLabel jLabelFaixa;
    private javax.swing.JLabel jLabelMnemonico;
    private javax.swing.JLabel jLabelNomeMedida;
    private javax.swing.JLabel jLabelObservacoes;
    private javax.swing.JLabel jLabelResponsavel;
    private javax.swing.JLabel jLabelUltimaEdicao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextAreaDefinicao;
    private javax.swing.JTextArea jTextAreaObservacoes;
    private javax.swing.JTextField jTextFieldCadastradoPor;
    private javax.swing.JTextField jTextFieldEscala;
    private javax.swing.JTextField jTextFieldFaixaFim;
    private javax.swing.JTextField jTextFieldFaixaInicio;
    private javax.swing.JTextField jTextFieldMnemonico;
    private javax.swing.JTextField jTextFieldNomeMedida;
    private javax.swing.JTextField jTextFieldResponsavel;
    private javax.swing.JTextField jTextFieldUltimaEdicao;
    // End of variables declaration//GEN-END:variables
}
