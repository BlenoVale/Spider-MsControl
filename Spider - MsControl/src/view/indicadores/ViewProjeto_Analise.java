package view.indicadores;

import controller.CtrlIndicador;
import controller.CtrlValores;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
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
        Internal.retiraBotao(this);
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
        jLabelValorAtual.setText("<html><b>Valor atual<br> do indicador:</b></html>");

        tableModel = new MyDefaultTableModel(new String[]{"Valor", "Ponto de vista", "Data"}, 0, false);
        jTableValorIndicador.setModel(tableModel);

        jButtonInformacao.setEnabled(false);

    }

    private void getListadeIndicadoresComProcAnalise() {
        listaIndicadores = new ArrayList<>();
        listaIndicadores = ctrlIndicador.getIndicadoresDoProjeto(Copia.getProjetoSelecionado().getId());
    }

    private void populaComboboxIndicadores() {
        comboBoxModel = new DefaultComboBoxModel();
        comboBoxModel.addElement("--Selecione um indicador--");

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

        if (ultimoValor <= metaCritico) {
            jTextFieldMeta.setText("CRÍTICO");
            jTextFieldMeta.setBackground(Color.red);
            jTextFieldMeta.setFont(new Font("Times New Roman", Font.BOLD, 14));
            jTextFieldMeta.setHorizontalAlignment(SwingConstants.CENTER);

            jTextFieldCriterio.setText("CRÍTICO");
            jTextFieldCriterio.setBackground(Color.red);
            jTextFieldCriterio.setFont(new Font("Times New Roman", Font.BOLD, 14));
            jTextFieldCriterio.setHorizontalAlignment(SwingConstants.CENTER);

        } else if (ultimoValor >= metaAlerta && ultimoValor < metaOK) {
            jTextFieldMeta.setText("ALERTA");
            jTextFieldMeta.setBackground(Color.yellow);
            jTextFieldMeta.setFont(new Font("Times New Roman", Font.BOLD, 14));
            jTextFieldMeta.setHorizontalAlignment(SwingConstants.CENTER);

            jTextFieldCriterio.setText("ALERTA");
            jTextFieldCriterio.setBackground(Color.yellow);
            jTextFieldCriterio.setFont(new Font("Times New Roman", Font.BOLD, 14));
            jTextFieldCriterio.setHorizontalAlignment(SwingConstants.CENTER);

        } else if (ultimoValor >= metaOK) {
            jTextFieldMeta.setText("OK");
            jTextFieldMeta.setBackground(Color.green);
            jTextFieldMeta.setFont(new Font("Times New Roman", Font.BOLD, 14));
            jTextFieldMeta.setHorizontalAlignment(SwingConstants.CENTER);

            jTextFieldCriterio.setText("OK");
            jTextFieldCriterio.setBackground(Color.green);
            jTextFieldCriterio.setFont(new Font("Times New Roman", Font.BOLD, 14));
            jTextFieldCriterio.setHorizontalAlignment(SwingConstants.CENTER);
        }

        jLabelValorAtual.setText("<html><b>Valor atual<br> do indicador:</b>  " + ultimoValor + "</html>");

    }

    private String statusNaTabela(double valor) {
        if (valor <= metaCritico) {
            return "<html><b><font color=\"red\">CRÍTICO</font></b></html>";
        } else if (valor >= metaAlerta && valor < metaOK) {
            return "<html><b><font color=\"dadc2f\">ALERTA</font></b></html>";
        } else {
            return "<html><b><font color=\"green\">OK</font></b></html>";
        }
    }

    private void preencherTabelaPelaData() {
        if (jComboBoxIndicadores.getSelectedItem() != "--Selecione um indicador--") {
            List<Valorindicador> listaValoresIndicador = new ArrayList<>();
            listaValoresIndicador = ctrlValores.buscarValorIndicadorPorDatas((Date) dateFieldDe.getValue(), (Date) dateFieldAte.getValue(), indicadorSelecionado.getId(), Copia.getProjetoSelecionado().getId());

            tableModel = new MyDefaultTableModel(new String[]{"Valor", "Status", "Ponto de vista", "Data"}, 0, false);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            for (int i = 0; i < listaValoresIndicador.size(); i++) {
                String data = simpleDateFormat.format(listaValoresIndicador.get(i).getData());
                String[] linha = {
                    String.valueOf(listaValoresIndicador.get(i).getValor()),
                    statusNaTabela(listaValoresIndicador.get(i).getValor()),
                    listaValoresIndicador.get(i).getIndicadorid().getPontoDeVista(),
                    data};
                tableModel.addRow(linha);
            }
            jTableValorIndicador.setModel(tableModel);
        }
    }

    private void gerarGrafico() {
        inicializaGraficoPizza();
        jPanelGrafico.setVisible(true);
    }

    private void inicializaGraficoPizza() {
        ChartPanel chartPanel = new Grafico().geraGraficoPizza("Pontos que agregam valor");

        jPanelPlot.removeAll();
        jPanelPlot.setLayout(new java.awt.BorderLayout());
        jPanelPlot.add(chartPanel, BorderLayout.CENTER);
        jPanelPlot.validate();

    }

    private void inicializaGraficoBarra() {
        ChartPanel chartPanel = new Grafico().geraGraficoBarra("Pontos que agregam valor", "Sprints", "PAV (%)");

        jPanelPlot.removeAll();
        jPanelPlot.setLayout(new java.awt.BorderLayout());
        jPanelPlot.add(chartPanel, BorderLayout.CENTER);
        jPanelPlot.validate();
    }

    private void inicializaGraficoLinha() {
        ChartPanel chartPanel = new Grafico().geraGraficoLinha("Pontos que agregam valor", "Sprints", "PAV (%)");

        jPanelPlot.removeAll();
        jPanelPlot.setLayout(new java.awt.BorderLayout());
        jPanelPlot.add(chartPanel, BorderLayout.CENTER);
        jPanelPlot.validate();
    }

    private void fake() {
        String[] colunas = {"Data", "Responsável", "Valor do indicador"};
        defaultTableModel = new MyDefaultTableModel(colunas, 0, false);

        String linha1[] = {
            "06/05/2015",
            "Gessica Pinheiro",
            "73"
        };
        defaultTableModel.addRow(linha1);
        jTableValorIndicador.setModel(defaultTableModel);

        String linha2[] = {
            "10/05/2015",
            "Gessica Pinheiro",
            "90"
        };
        defaultTableModel.addRow(linha2);
        jTableValorIndicador.setModel(defaultTableModel);

        String linha3[] = {
            "12/05/2015",
            "Gessica Pinheiro",
            "50"
        };
        defaultTableModel.addRow(linha3);
        jTableValorIndicador.setModel(defaultTableModel);

        String linha4[] = {
            "19/05/2015",
            "Gessica Pinheiro",
            "38"
        };
        defaultTableModel.addRow(linha4);
        jTableValorIndicador.setModel(defaultTableModel);

        String linha5[] = {
            "20/05/2015",
            "Gessica Pinheiro",
            "80"
        };
        defaultTableModel.addRow(linha5);
        jTableValorIndicador.setModel(defaultTableModel);
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
        jButton1 = new javax.swing.JButton();
        jPanelGrafico = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jPanelPlot = new javax.swing.JPanel();
        jComboBoxGrafico = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setTitle("Análise do Indicador");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("<html><b>Indicador:</b></html> ");

        jComboBoxIndicadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIndicadoresActionPerformed(evt);
            }
        });

        jLabel5.setText("<html><b>Critério  de análise:</b></html>");

        jTextFieldCriterio.setEditable(false);
        jTextFieldCriterio.setToolTipText("<html>\n<font color=\"Green\"><b>OK: </b></font>O PAV está de acordo com as metas estabelecidas/esperadas.<br> Ação: Não é necessária tomar nenhuma.<br><br>\n\n<font color=\"orange\"><b>Alerta: </b></font>O PAV está abaixo da faixa esperada (Bom ou Excelente),<br> porém dentro da faixa de atenção (Regular). Ação: Analisar a(s) causa(s), registrar  reultado da análise<br><br>\n\n<font color=\"red\"><b>Crítico: </b></font>O PAV não está sendo satisfatório. Ação:<br> Analisar a(s) causa(s), registrar tarefa de ação corretiva e acompanhar <br>até sua conclusão.<br>\n</html>");

        jLabel6.setText("<html><b>Meta do indicador:</b></html>");

        jTextFieldMeta.setEditable(false);
        jTextFieldMeta.setToolTipText("<html> <font color=\"green\"><b> OK </b></font>=  Maior que 70%<br><br>\n<font color=\"orange\"><b> ALERTA </b></font>=  entre 40% e 70%<br><br>\n<font color=\"red\"><b> CRÍTICO </b></font>= abaixo de 40%<br><br>\n</html>");

        jButtonInformacao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonInformacao.setText("?");
        jButtonInformacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInformacaoActionPerformed(evt);
            }
        });

        jLabelValorAtual.setText("Valor Atual do Indicado:");

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

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
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

        jButton1.setText("Gerar Gráfico");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanelGrafico.setBackground(new java.awt.Color(204, 204, 204));
        jPanelGrafico.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Análise:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jButton2.setText("Salvar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane4.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanelGraficoLayout = new javax.swing.GroupLayout(jPanelGrafico);
        jPanelGrafico.setLayout(jPanelGraficoLayout);
        jPanelGraficoLayout.setHorizontalGroup(
            jPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGraficoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGraficoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanelGraficoLayout.createSequentialGroup()
                        .addGroup(jPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanelGraficoLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addComponent(jButton2)
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
                        .addComponent(jButton1))
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
                .addComponent(jButton1)
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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        gerarGrafico();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxIndicadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIndicadoresActionPerformed
        if (jComboBoxIndicadores.getSelectedItem() != "--Selecione um indicador--") {
            pegaIndicadorSelecionado();
            statusDoIndicador();
            preencherTabelaPelaData();
            jButtonInformacao.setEnabled(true);
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
    }//GEN-LAST:event_jComboBoxGraficoActionPerformed

    private void dateFieldDeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateFieldDeStateChanged
        preencherTabelaPelaData();
    }//GEN-LAST:event_dateFieldDeStateChanged

    private void dateFieldAteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateFieldAteStateChanged
        preencherTabelaPelaData();
    }//GEN-LAST:event_dateFieldAteStateChanged

    private void jButtonInformacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInformacaoActionPerformed
        View_InformacaoDialog view_InformacaoDialog = new View_InformacaoDialog(null, true);
        view_InformacaoDialog.preencheCamposInfo(indicadorSelecionado.getProcedimentodeanaliseList().get(0));
        view_InformacaoDialog.setVisible(true);
    }//GEN-LAST:event_jButtonInformacaoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jPanelGrafico.setVisible(false);
        JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private net.sf.nachocalendar.components.DateField dateFieldAte;
    private net.sf.nachocalendar.components.DateField dateFieldDe;
    private net.sf.nachocalendar.model.DefaultDateSelectionModel defaultDateSelectionModel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonInformacao;
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
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextFieldCriterio;
    private javax.swing.JTextField jTextFieldMeta;
    // End of variables declaration//GEN-END:variables
}
