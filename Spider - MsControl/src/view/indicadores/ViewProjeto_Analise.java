package view.indicadores;

import controller.CtrlAnalise;
import controller.CtrlIndicador;
import controller.CtrlValores;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.Analise;
import model.Indicador;
import model.Procedimentodeanalise;
import model.Valorindicador;
import org.jfree.chart.ChartPanel;
import util.Copia;
import util.Grafico;
import util.Internal;
import util.MyDefaultTableModel;

/**
 *
 * @author BlenoVale
 */
public class ViewProjeto_Analise extends javax.swing.JInternalFrame {

    private DefaultTableModel defaultTableModel;
    private List<Indicador> listaIndicadores;
    List<Valorindicador> listaValoresIndicador = new ArrayList<>();
    private Indicador indicadorSelecionado;
    private final CtrlIndicador ctrlIndicador = new CtrlIndicador();
    private final CtrlValores ctrlValores = new CtrlValores();
    private DefaultComboBoxModel comboBoxModel;
    private MyDefaultTableModel tableModel;

    private double metaCritico;
    private double metaAlerta;
    private double metaOK;

    public ViewProjeto_Analise() {
        initComponents();

        jPanelGrafico.setVisible(false);
        Internal.retiraBorda(this);
    }

    public void showAnalise() {
        populaComboboxIndicadores();
        limparAnalise();
    }

    private void limparAnalise() {
        jTextFieldMeta.setText("");
        jTextFieldMeta.setBackground(Color.white);
        jTextFieldCriterio.setText("");
        jTextFieldCriterio.setBackground(Color.white);
        jLabelValorAtual.setText("<html><b>Valor Atual<br> do Indicador:</b></html>");

        tableModel = new MyDefaultTableModel(new String[]{"ID", "Valor", "Status", "Ponto de Vista", "Data"}, 0, false);
        jTableValorIndicador.setModel(tableModel);
        jTableValorIndicador.getColumnModel().getColumn(0).setPreferredWidth(3);

        dateFieldDe.setValue(new Date());
        dateFieldAte.setValue(new Date());

        jButtonInformacao.setEnabled(false);
        jPanelGrafico.setVisible(false);

    }

    private void getListadeIndicadoresComProcAnalise() {
        listaIndicadores = new ArrayList<>();
        listaIndicadores = ctrlIndicador.getIndicadoresDoProjeto(Copia.getProjetoSelecionado().getId());
    }

    private void populaComboboxIndicadores() {
        comboBoxModel = new DefaultComboBoxModel();
        comboBoxModel.addElement("--Selecione um Indicador--");

        getListadeIndicadoresComProcAnalise();
        for (int i = 0; i < listaIndicadores.size(); i++) {
            if (!listaIndicadores.get(i).getProcedimentodeanaliseList().isEmpty()) {
                comboBoxModel.addElement(listaIndicadores.get(i).getNome());
            }
        }
        jComboBoxIndicadores.setModel(comboBoxModel);
    }

    private void pegaIndicadorSelecionado() {
        indicadorSelecionado = new Indicador();
        indicadorSelecionado = ctrlIndicador.buscarIndicadorPeloNome(jComboBoxIndicadores.getSelectedItem().toString(), Copia.getProjetoSelecionado().getId());
    }

    private void statusDoIndicador() {
        Valorindicador valorindicador = ctrlValores.buscaUltimoValorIndicadorDoProjeto(indicadorSelecionado.getNome(), Copia.getProjetoSelecionado().getId());
        Procedimentodeanalise procedimentoAnalise = valorindicador.getIndicadorid().getProcedimentodeanaliseList().get(0);

        double ultimoValor = valorindicador.getValor();
        metaCritico = procedimentoAnalise.getMetaCritico();
        metaAlerta = procedimentoAnalise.getMetaAlerta();
        metaOK = procedimentoAnalise.getMetaOk();

        if (metaCritico < metaOK) {
            if (ultimoValor <= metaCritico) {
                jTextFieldMeta.setText("CRÍTICO");
                jTextFieldMeta.setBackground(Color.red);
                jTextFieldCriterio.setText("CRÍTICO");
                jTextFieldCriterio.setBackground(Color.red);

            } else if (ultimoValor > metaCritico && ultimoValor < metaOK) {
                jTextFieldMeta.setText("ALERTA");
                jTextFieldMeta.setBackground(Color.yellow);
                jTextFieldCriterio.setText("ALERTA");
                jTextFieldCriterio.setBackground(Color.yellow);

            } else if (ultimoValor >= metaOK) {
                jTextFieldMeta.setText("OK");
                jTextFieldMeta.setBackground(Color.green);
                jTextFieldCriterio.setText("OK");
                jTextFieldCriterio.setBackground(Color.green);

            }
        } else {
            if (ultimoValor <= metaOK) {
                jTextFieldMeta.setText("OK");
                jTextFieldMeta.setBackground(Color.green);
                jTextFieldCriterio.setText("OK");
                jTextFieldCriterio.setBackground(Color.green);

            } else if (ultimoValor > metaOK && ultimoValor < metaCritico) {
                jTextFieldMeta.setText("ALERTA");
                jTextFieldMeta.setBackground(Color.yellow);
                jTextFieldCriterio.setText("ALERTA");
                jTextFieldCriterio.setBackground(Color.yellow);

            } else if (ultimoValor >= metaCritico) {
                jTextFieldMeta.setText("CRÍTICO");
                jTextFieldMeta.setBackground(Color.red);
                jTextFieldCriterio.setText("CRÍTICO");
                jTextFieldCriterio.setBackground(Color.red);

            }
        }

        jTextFieldCriterio.setFont(new Font("Times New Roman", Font.BOLD, 14));
        jTextFieldCriterio.setHorizontalAlignment(SwingConstants.CENTER);
        jTextFieldMeta.setFont(new Font("Times New Roman", Font.BOLD, 14));
        jTextFieldMeta.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelValorAtual.setText("<html><b>Valor Atual<br> do Indicador:</b>  " + ultimoValor + "</html>");

    }

    private String statusNaTabela(double valor) {
        String status = new String();
        if (metaCritico < metaOK) {
            if (valor <= metaCritico) {
                status = "<html><b><font color=\"red\">CRÍTICO</font></b></html>";
            } else if (valor > metaCritico && valor < metaOK) {
                status = "<html><b><font color=\"dadc2f\">ALERTA</font></b></html>";
            } else {
                status = "<html><b><font color=\"green\">OK</font></b></html>";
            }
        } else {
            if (valor <= metaOK) {
                status = "<html><b><font color=\"green\">OK</font></b></html>";
            } else if (valor > metaOK && valor < metaCritico) {
                status = "<html><b><font color=\"dadc2f\">ALERTA</font></b></html>";
            } else {
                status = "<html><b><font color=\"red\">CRÍTICO</font></b></html>";
            }
        }
        return status;
    }

    private boolean verificaDatas() {
        Date data1 = (Date) dateFieldDe.getValue();
        Date data2 = (Date) dateFieldAte.getValue();
        if (data1.after(new Date())) {
            dateFieldDe.setValue(new Date());
            return false;
        } else if (data2.after(new Date())) {
            dateFieldAte.setValue(new Date());
            return false;
        } else {
            return true;
        }
    }

    private void preencherTabelaPelaData() {
        if (jComboBoxIndicadores.getSelectedItem() != "--Selecione um Indicador--") {
            listaValoresIndicador = new ArrayList<>();

            listaValoresIndicador = ctrlValores.buscarValorIndicadorPorDatas((Date) dateFieldDe.getValue(), (Date) dateFieldAte.getValue(), indicadorSelecionado.getId(), Copia.getProjetoSelecionado().getId());

            tableModel = new MyDefaultTableModel(new String[]{"ID", "Valor", "Status", "Ponto de Vista", "Data"}, 0, false);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            for (int i = 0; i < listaValoresIndicador.size(); i++) {
                String data = simpleDateFormat.format(listaValoresIndicador.get(i).getData());
                String[] linha = {
                    "<html>id <b>" + String.valueOf(i + 1) + "</b></html>",
                    String.valueOf(listaValoresIndicador.get(i).getValor()),
                    statusNaTabela(listaValoresIndicador.get(i).getValor()),
                    listaValoresIndicador.get(i).getIndicadorid().getPontoDeVista(),
                    data};
                tableModel.addRow(linha);
            }
            jTableValorIndicador.setModel(tableModel);
            jTableValorIndicador.getColumnModel().getColumn(0).setPreferredWidth(3);

        }
    }

    private void inicializaGraficoPizza() {
        ChartPanel chartPanel = new Grafico().geraGraficoPizza(listaValoresIndicador);

        jPanelPlot.removeAll();
        jPanelPlot.setLayout(new java.awt.BorderLayout());
        jPanelPlot.add(chartPanel, BorderLayout.CENTER);
        jPanelPlot.validate();

    }

    private void inicializaGraficoBarra() {
        ChartPanel chartPanel = new Grafico().geraGraficoBarra(listaValoresIndicador);

        jPanelPlot.removeAll();
        jPanelPlot.setLayout(new java.awt.BorderLayout());
        jPanelPlot.add(chartPanel, BorderLayout.CENTER);
        jPanelPlot.validate();
    }

    private void inicializaGraficoLinha() {
        ChartPanel chartPanel = new Grafico().geraGraficoLinha(listaValoresIndicador);

        jPanelPlot.removeAll();
        jPanelPlot.setLayout(new java.awt.BorderLayout());
        jPanelPlot.add(chartPanel, BorderLayout.CENTER);
        jPanelPlot.validate();
    }

    private void salvarAnalise() {
        if (jTextAreaAnalise.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo Análise é obrigatório.");
        } else {
            Analise analise = new Analise();
            analise.setAnalise(jTextAreaAnalise.getText());
            analise.setObservacao(jTextAreaObservacao.getText());
            analise.setDataCriacao(new Date());
            analise.setNomeUsuario(Copia.getUsuarioLogado().getNome());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime((Date) dateFieldDe.getValue());
            analise.setAnaliseDE(calendar.getTime());
            calendar.setTime((Date) dateFieldAte.getValue());
            analise.setAnaliseATE(calendar.getTime());
            analise.setIndicadorid(indicadorSelecionado);

            int indice = indicadorSelecionado.getValorindicadorList().size() - 1;
            double valor = ctrlValores.buscaUltimoValorIndicadorDoProjeto(indicadorSelecionado.getNome(), Copia.getProjetoSelecionado().getId()).getValor();
            String valorAtual = valor + "- " + jTextFieldMeta.getText();
            analise.setValorAtualdoIndicador(valorAtual);

            CtrlAnalise ctrlAnalise = new CtrlAnalise();
            ctrlAnalise.cadastrarAnalise(analise);

            jTextAreaAnalise.setText(" ");
            jTextAreaObservacao.setText(" ");
            jPanelGrafico.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        defaultDateSelectionModel1 = new net.sf.nachocalendar.model.DefaultDateSelectionModel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxIndicadores = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldCriterio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldMeta = new javax.swing.JTextField();
        jButtonInformacao = new javax.swing.JButton();
        jLabelValorAtual = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableValorIndicador = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateFieldDe = new net.sf.nachocalendar.components.DateField();
        dateFieldAte = new net.sf.nachocalendar.components.DateField();
        jLabel4 = new javax.swing.JLabel();
        jButtonGeraGrafico = new javax.swing.JButton();
        jPanelGrafico = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaAnalise = new javax.swing.JTextArea();
        jButtonSalvar = new javax.swing.JButton();
        jPanelPlot = new javax.swing.JPanel();
        jComboBoxGrafico = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jButtonCancelar = new javax.swing.JButton();

        setTitle("Análise do Indicador");

        jPanel2.setBackground(new java.awt.Color(140, 166, 217));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("<html><b>Indicador:</b></html> ");

        jComboBoxIndicadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIndicadoresActionPerformed(evt);
            }
        });

        jLabel5.setText("<html><b>Critério  de Análise:</b></html>");

        jTextFieldCriterio.setEditable(false);
        jTextFieldCriterio.setToolTipText("<html>\n<font color=\"Green\"><b>OK: </b></font>O PAV está de acordo com as metas estabelecidas/esperadas.<br> Ação: Não é necessária tomar nenhuma.<br><br>\n\n<font color=\"orange\"><b>Alerta: </b></font>O PAV está abaixo da faixa esperada (Bom ou Excelente),<br> porém dentro da faixa de atenção (Regular). Ação: Analisar a(s) causa(s), registrar  reultado da análise<br><br>\n\n<font color=\"red\"><b>Crítico: </b></font>O PAV não está sendo satisfatório. Ação:<br> Analisar a(s) causa(s), registrar tarefa de ação corretiva e acompanhar <br>até sua conclusão.<br>\n</html>");

        jLabel6.setText("<html><b>Meta do Indicador:</b></html>");

        jTextFieldMeta.setEditable(false);
        jTextFieldMeta.setToolTipText("<html> <font color=\"green\"><b> OK </b></font>=  Maior que 70%<br><br>\n<font color=\"orange\"><b> ALERTA </b></font>=  entre 40% e 70%<br><br>\n<font color=\"red\"><b> CRÍTICO </b></font>= abaixo de 40%<br><br>\n</html>");

        jButtonInformacao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonInformacao.setText("?");
        jButtonInformacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInformacaoActionPerformed(evt);
            }
        });

        jLabelValorAtual.setText("Valor Atual do Indicador:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelValorAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxIndicadores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMeta, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonInformacao)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldMeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxIndicadores, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelValorAtual)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButtonInformacao)))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "<html><b>Dados Coletados</b></html>"));

        jTableValorIndicador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Autor", "Valor do indicador"
            }
        ));
        jScrollPane1.setViewportView(jTableValorIndicador);

        jPanel4.setBackground(new java.awt.Color(140, 166, 217));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("De:");

        jLabel2.setText("Até:");

        dateFieldDe.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dateFieldDeStateChanged(evt);
            }
        });

        dateFieldAte.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dateFieldAteStateChanged(evt);
            }
        });

        jLabel4.setText("     ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateFieldDe, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateFieldAte, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(4, 4, 4))
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(dateFieldAte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateFieldDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jButtonGeraGrafico.setText("Gerar Gráfico");
        jButtonGeraGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGeraGraficoActionPerformed(evt);
            }
        });

        jPanelGrafico.setBackground(new java.awt.Color(204, 204, 204));
        jPanelGrafico.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Análise:");

        jTextAreaAnalise.setColumns(20);
        jTextAreaAnalise.setRows(5);
        jScrollPane3.setViewportView(jTextAreaAnalise);

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jPanelPlot.setAutoscrolls(true);

        javax.swing.GroupLayout jPanelPlotLayout = new javax.swing.GroupLayout(jPanelPlot);
        jPanelPlot.setLayout(jPanelPlotLayout);
        jPanelPlotLayout.setHorizontalGroup(
            jPanelPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelPlotLayout.setVerticalGroup(
            jPanelPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        jComboBoxGrafico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pizza", "Barra", "Linha" }));
        jComboBoxGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGraficoActionPerformed(evt);
            }
        });

        jLabel8.setText("Tipo:");

        jLabel9.setText("Observação:");

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setRows(5);
        jScrollPane4.setViewportView(jTextAreaObservacao);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGraficoLayout = new javax.swing.GroupLayout(jPanelGrafico);
        jPanelGrafico.setLayout(jPanelGraficoLayout);
        jPanelGraficoLayout.setHorizontalGroup(
            jPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGraficoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanelGraficoLayout.createSequentialGroup()
                        .addGroup(jPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanelGraficoLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGraficoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar)))
                .addContainerGap())
        );
        jPanelGraficoLayout.setVerticalGroup(
            jPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGraficoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelPlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonGeraGrafico))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGeraGrafico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGeraGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGeraGraficoActionPerformed
        if (!listaValoresIndicador.isEmpty()) {
            jComboBoxGrafico.setSelectedItem(indicadorSelecionado.getProcedimentodeanaliseList().get(0).getGraficoNome());
        } else {
            JOptionPane.showMessageDialog(null, "Não há valores de indicador para gerar o gráfico.");
        }
    }//GEN-LAST:event_jButtonGeraGraficoActionPerformed

    private void jComboBoxIndicadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIndicadoresActionPerformed
        if (jComboBoxIndicadores.getSelectedItem() != "--Selecione um Indicador--") {
            pegaIndicadorSelecionado();
            jPanelGrafico.setVisible(false);

            if (!indicadorSelecionado.getValorindicadorList().isEmpty()) {
                statusDoIndicador();
                preencherTabelaPelaData();
                jButtonInformacao.setEnabled(true);
            } else {
                limparAnalise();
            }
        } else {
            limparAnalise();
        }
    }//GEN-LAST:event_jComboBoxIndicadoresActionPerformed

    private void jComboBoxGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGraficoActionPerformed
        if (jComboBoxGrafico.getSelectedItem() == "Barra") {
            inicializaGraficoBarra();
        } else if (jComboBoxGrafico.getSelectedItem() == "Pizza") {
            inicializaGraficoPizza();
        } else {
            inicializaGraficoLinha();
        }
        jPanelGrafico.setVisible(true);
    }//GEN-LAST:event_jComboBoxGraficoActionPerformed

    private void dateFieldDeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateFieldDeStateChanged
        if (verificaDatas()) {
            preencherTabelaPelaData();
        } else {
            JOptionPane.showMessageDialog(null, "Data não pode ser maior que data atual.");
        }
    }//GEN-LAST:event_dateFieldDeStateChanged

    private void dateFieldAteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateFieldAteStateChanged
        if (verificaDatas()) {
            preencherTabelaPelaData();
        } else {
            JOptionPane.showMessageDialog(null, "Data não pode ser maior que data atual.");
        }
    }//GEN-LAST:event_dateFieldAteStateChanged

    private void jButtonInformacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInformacaoActionPerformed
        View_InformacaoDialog view_InformacaoDialog = new View_InformacaoDialog(null, true);
        if (metaCritico < metaOK) {
            view_InformacaoDialog.preencheCamposInfoCrescente(indicadorSelecionado.getProcedimentodeanaliseList().get(0));
        } else {
            view_InformacaoDialog.preencheCamposInfoDecrescente(indicadorSelecionado.getProcedimentodeanaliseList().get(0));
        }
        view_InformacaoDialog.setVisible(true);
    }//GEN-LAST:event_jButtonInformacaoActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        salvarAnalise();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jTextAreaAnalise.setText(" ");
        jTextAreaObservacao.setText(" ");
        jPanelGrafico.setVisible(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private net.sf.nachocalendar.components.DateField dateFieldAte;
    private net.sf.nachocalendar.components.DateField dateFieldDe;
    private net.sf.nachocalendar.model.DefaultDateSelectionModel defaultDateSelectionModel1;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGeraGrafico;
    private javax.swing.JButton jButtonInformacao;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox jComboBoxGrafico;
    private javax.swing.JComboBox jComboBoxIndicadores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelValorAtual;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelGrafico;
    private javax.swing.JPanel jPanelPlot;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableValorIndicador;
    private javax.swing.JTextArea jTextAreaAnalise;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldCriterio;
    private javax.swing.JTextField jTextFieldMeta;
    // End of variables declaration//GEN-END:variables
}
