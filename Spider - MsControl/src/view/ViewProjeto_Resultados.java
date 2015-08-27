package view;

import controller.CtrlAnalise;
import controller.CtrlResultados;
import controller.CtrlValores;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import model.Analise;
import model.ParticipanteseInteressados;
import model.Projeto;
import model.Resultados;
import model.Valorindicador;
import org.jfree.chart.ChartPanel;
import util.CheckDefaultTableModel;
import util.Constantes;
import util.Copia;
import util.Grafico;
import util.MyDefaultTableModel;
import util.TableTextAreaRenderer;
import util.Texto;

/**
 *
 * @author BlenoVale, Géssica
 */
public class ViewProjeto_Resultados extends javax.swing.JInternalFrame {

    private CheckDefaultTableModel checkmodel;
    private MyDefaultTableModel tableModel;
    private List<Resultados> listaResultados;
    private final CtrlResultados ctrlResultados = new CtrlResultados();
    private Resultados resultadoSelecionado = new Resultados();
    private CheckDefaultTableModel checkModel;
    private final CtrlAnalise ctrlAnalise = new CtrlAnalise();
    private final CtrlValores ctrlValores = new CtrlValores();
    private Analise analiseSelecioanda;
    private List<Analise> listaAnalises;
    private List<Valorindicador> listaValoresIndicador;

    public ViewProjeto_Resultados() {
        initComponents();
        this.pack();
    }

    public void showResultados() {
        preencherTabelaResultados();
        preencherTabelaAnaliseIndicador();
        preencherTabelaParticipantes();
        preencherTabelaUsuariosInteressados();
        preencheCamposInfoGerais();

        jTextFieldData.setText(Copia.getUsuarioLogado().getNome() + ",  " + Texto.formataData(new Date()));
        jTextFieldTitulo.setText(null);
        jTextAreaInterpretacao.setText(null);
        jTextAreaTomadaDeDecisao.setText(null);
        jTabbedPane1.setEnabledAt(2, false);
    }

//##########NOVO RESULTADO####################################################################################################
    private void preencherTabelaAnaliseIndicador() {
        checkModel = new CheckDefaultTableModel(new String[]{" ", "Indicador", "periodo analisado"}, 0, false);
        listaAnalises = new ArrayList<>();

        listaAnalises = ctrlAnalise.buscarAnalisesDoProjeto(Copia.getProjetoSelecionado().getId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < listaAnalises.size(); i++) {
            String data1 = simpleDateFormat.format(listaAnalises.get(i).getAnaliseDE());
            String data2 = simpleDateFormat.format(listaAnalises.get(i).getAnaliseATE());
            Object[] linha = {
                false,
                listaAnalises.get(i).getIndicadorid().getNome(),
                data1 + "-" + data2

            };
            checkModel.addRow(linha);
        }
        jTableResultadosAnaliseIndicador.setModel(checkModel);
        jTableResultadosAnaliseIndicador.getColumnModel().getColumn(0).setPreferredWidth(10);

        int aux = jTableResultadosAnaliseIndicador.getColumnModel().getColumn(1).getPreferredWidth();
        jTableResultadosAnaliseIndicador.getColumnModel().getColumn(1).setPreferredWidth(aux * 4);
        jTableResultadosAnaliseIndicador.getColumnModel().getColumn(2).setPreferredWidth(aux * 4);
    }

    private void preencherTabelaParticipantes() {
        List<String> listaParticipantes = Constantes.preencherListaPerfis();

        checkModel = new CheckDefaultTableModel(new String[]{" ", "Participantes da Interpretação"}, 0, false);

        for (int i = 0; i < listaParticipantes.size(); i++) {
            Object[] linha = {
                false,
                listaParticipantes.get(i)
            };
            checkModel.addRow(linha);
        }
        jTableParticipantes.setModel(checkModel);
        jTableParticipantes.getColumnModel().getColumn(0).setPreferredWidth(10);
        int aux = jTableParticipantes.getColumnModel().getColumn(1).getPreferredWidth();
        jTableParticipantes.getColumnModel().getColumn(1).setPreferredWidth(aux * 8);
    }

    private void preencherTabelaUsuariosInteressados() {
        List<String> listaUsuarios = Constantes.preencherListaPerfis();

        checkModel = new CheckDefaultTableModel(new String[]{" ", "Usuários Interessados no Resultado"}, 0, false);

        for (int i = 0; i < listaUsuarios.size(); i++) {
            Object[] linha = {
                false,
                listaUsuarios.get(i)
            };
            checkModel.addRow(linha);
        }
        jTableUsuariosInteressados.setModel(checkModel);
        jTableUsuariosInteressados.getColumnModel().getColumn(0).setPreferredWidth(10);
        int aux = jTableUsuariosInteressados.getColumnModel().getColumn(1).getPreferredWidth();
        jTableUsuariosInteressados.getColumnModel().getColumn(1).setPreferredWidth(aux * 8);
    }

    private void pegaAnaliseDoIndicadorSelecionado() {
        if (jTableResultadosAnaliseIndicador.getSelectedRow() > -1) {
            int linhaSelecionada = jTableResultadosAnaliseIndicador.getSelectedRow();
            analiseSelecioanda = new Analise();
            analiseSelecioanda = listaAnalises.get(linhaSelecionada);
        }

    }

    private void preencherTabelaResultados() {
        tableModel = new MyDefaultTableModel(new String[]{"Título", "Autor", "Data"}, 0, false);
        listaResultados = ctrlResultados.getResultadosDoProjeto(Copia.getProjetoSelecionado().getId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < listaResultados.size(); i++) {
            String data = simpleDateFormat.format(listaResultados.get(i).getData());
            String[] linha = {
                listaResultados.get(i).getTitulo(),
                listaResultados.get(i).getNomeUsuario(),
                data
            };
            tableModel.addRow(linha);
        }
        jTableResultados.setModel(tableModel);
    }

    private void preencherTabelaResultadosPorBusca(String chave) {
        tableModel = new MyDefaultTableModel(new String[]{"Título", "Autor", "Data"}, 0, false);
        listaResultados = ctrlResultados.buscarParteDoTituloResultado(chave, Copia.getProjetoSelecionado().getId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < listaResultados.size(); i++) {
            String data = simpleDateFormat.format(listaResultados.get(i).getData());
            String[] linha = {
                listaResultados.get(i).getTitulo(),
                listaResultados.get(i).getNomeUsuario(),
                data
            };
            tableModel.addRow(linha);
        }
        jTableResultados.setModel(tableModel);

    }

    private boolean validaCampos(int contTabelas, String mensagemTabela) {
        int cont = contTabelas;
        String mensagem = mensagemTabela;
        if (jTextFieldTitulo.getText().isEmpty()) {
            mensagem = "Campo \"Título\" não pode ser vazio.";
            cont++;
        }
        if (jTextAreaInterpretacao.getText().isEmpty()) {
            mensagem = "Campo \"Interpretação\" não pode ser vazio.";
            cont++;
        }
        if (jTextAreaTomadaDeDecisao.getText().isEmpty()) {
            mensagem = "Campo \"Tomada de decisão\" não pode ser vazio.";
            cont++;
        }

        if (cont == 0) {
            return true;
        } else if (cont == 1) {
            JOptionPane.showMessageDialog(null, mensagem);
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Mais de um campo estão vazios ou inválidos.");
            return false;
        }

    }

    private void salvarResultado() {

        List<ParticipanteseInteressados> listaPI = new ArrayList<>();
        ParticipanteseInteressados participanteseInteressados;

        int contadorTabelas = 0;
        String mensagemTabela = null;
        int cont = 0;
        for (int i = 0; i < jTableParticipantes.getModel().getRowCount(); i++) {
            participanteseInteressados = new ParticipanteseInteressados();
            if ((boolean) jTableParticipantes.getModel().getValueAt(i, 0) == true) {
                participanteseInteressados.setParticipanteEInteressado(jTableParticipantes.getModel().getValueAt(i, 1).toString());
                participanteseInteressados.setTipo("Participante");
                listaPI.add(participanteseInteressados);
            } else {
                cont++;
            }
        }
        if (cont == jTableParticipantes.getModel().getRowCount()) {
            contadorTabelas++;
            mensagemTabela = "É necesario selecionar os Participantes da interpretação do resultado.";
        }

        cont = 0;
        for (int i = 0; i < jTableUsuariosInteressados.getModel().getRowCount(); i++) {
            participanteseInteressados = new ParticipanteseInteressados();
            if ((boolean) jTableUsuariosInteressados.getModel().getValueAt(i, 0) == true) {
                participanteseInteressados.setParticipanteEInteressado(jTableUsuariosInteressados.getModel().getValueAt(i, 1).toString());
                participanteseInteressados.setTipo("Interessado");
                listaPI.add(participanteseInteressados);
            } else {
                cont++;
            }
        }
        if (cont == jTableUsuariosInteressados.getModel().getRowCount()) {
            contadorTabelas++;
            mensagemTabela = "É necesario selecionar os Usuários Interessados no resultado.";
        }

        cont = 0;
        List<Analise> listaAnaliseSelecionadas = new ArrayList<>();
        for (int i = 0; i < jTableResultadosAnaliseIndicador.getModel().getRowCount(); i++) {
            if ((boolean) jTableResultadosAnaliseIndicador.getModel().getValueAt(i, 0) == true) {
                listaAnaliseSelecionadas.add(listaAnalises.get(i));
            } else {
                cont++;
            }
        }
        if (cont == jTableResultadosAnaliseIndicador.getModel().getRowCount()) {
            contadorTabelas++;
            mensagemTabela = "É necesario selecionar as análises que fazem parte do Resultado.";
        }

        if (!validaCampos(contadorTabelas, mensagemTabela)) {
            return;
        }

        Resultados resultados = new Resultados();
        resultados.setTitulo(jTextFieldTitulo.getText());
        resultados.setData(new Date());
        resultados.setNomeUsuario(Copia.getUsuarioLogado().getNome());
        resultados.setInterpretacao(jTextAreaInterpretacao.getText());
        resultados.setTomadaDeDecisao(jTextAreaTomadaDeDecisao.getText());
        resultados.setIdProjeto(Copia.getProjetoSelecionado().getId());

        resultados.setAnaliseList(listaAnaliseSelecionadas);
        ctrlResultados.cadastraResultado(resultados, listaPI);
        showResultados();
    }
//##########INFORMAÇÕES GERAISR############################################################################################

    private void preencheCamposInfoGerais() {
        Projeto projeto = Copia.getProjetoSelecionado();
        jLabelProjeto.setText("PROJETO: " + projeto.getNome());
        switch (projeto.getStatus()) {
            case 1:
                jLabelStatusProjeto.setText("Status do projeto: ATIVO");
                break;
            case 2:
                jLabelStatusProjeto.setText("Status do projeto: INATIVO");
                break;
            case 3:
                jLabelStatusProjeto.setText("Status do projeto: FINALIZADO");
                break;
        }
        jTextAreaDescriçãoProjeto.setText(projeto.getDescricao());

        inicializaTabelaInformacoesGerais(projeto);
    }

    private void inicializaTabelaInformacoesGerais(Projeto projeto) {
        tableModel = new MyDefaultTableModel(new String[]{"OBJETIVO ESTRATÉGICO", "NECESSIDADE DE INFORMAÇÃO", "INDICADOR", "PRIORIDADE"}, 0, false);
        for (int i = 0; i < projeto.getObjetivodemedicaoList().size(); i++) {
            String[] linha = {
                projeto.getObjetivodemedicaoList().get(i).getNome(),
                projeto.getObjetivodemedicaoList().get(i).getNome(),
                projeto.getObjetivodemedicaoList().get(i).getNome(),
                projeto.getObjetivodemedicaoList().get(i).getNome()
            };
            tableModel.addRow(linha);
        }
        jTableInfoGerais.setModel(tableModel);

        for (int i = 0; i < jTableInfoGerais.getColumnCount(); i++) {
            TableColumn col = jTableInfoGerais.getColumnModel().getColumn(i);
            col.setCellRenderer(new TableTextAreaRenderer());
        }

        int tamnhoColuna = jTableInfoGerais.getColumnModel().getColumn(1).getPreferredWidth();
        jTableInfoGerais.getColumnModel().getColumn(0).setPreferredWidth(tamnhoColuna * 8);
        jTableInfoGerais.getColumnModel().getColumn(1).setPreferredWidth(tamnhoColuna * 7);
        jTableInfoGerais.getColumnModel().getColumn(2).setPreferredWidth(tamnhoColuna * 3);
        jTableInfoGerais.getColumnModel().getColumn(3).setPreferredWidth(tamnhoColuna * 3);

        //jTableInfoGerais.setRowHeight(45);
    }

//##########INFORMAÇÕES DA ANÁLISE DO INDICADOR###########################################################################
    private void EscolheTipoDeGrafico(String tipoGrafico) {
        if ("Pizza".equals(tipoGrafico)) {
            inicializaGraficoPizza();
        } else if ("Barra".equals(tipoGrafico)) {
            inicializaGraficoBarra();
        } else {
            inicializaGraficoLinha();
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

    private void preencherCamposDaAnaliseDoIndicador() {
        jTextAreaAnalise.setText(analiseSelecioanda.getCriterioDeAnalise());
        jTextAreaObservacao.setText(analiseSelecioanda.getObservacao());
    }

    private void buscarListaValoresIndicador(Date dataDe, Date dataAte, int idIndicador) {
        listaValoresIndicador = ctrlValores.buscarValorIndicadorPorDatas(dataDe, dataAte, idIndicador, Copia.getProjetoSelecionado().getId());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldBusca = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableResultadosAnaliseIndicador = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelAnaliseRefer = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldData = new javax.swing.JTextField();
        jTextFieldTitulo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaTomadaDeDecisao = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaInterpretacao = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTableParticipantes = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableUsuariosInteressados = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelInfoGerais = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabelProjeto = new javax.swing.JLabel();
        jLabelStatusProjeto = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextAreaDescriçãoProjeto = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTableInfoGerais = new javax.swing.JTable();
        jPanelInformações = new javax.swing.JPanel();
        jPanelPlot = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaAnalise = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableResultados = new javax.swing.JTable();

        setTitle("Resultados");

        jLabel7.setText("Buscar por resultado: ");

        jTextFieldBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscaActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Análise Referenciada nos Resultados"));

        jTableResultadosAnaliseIndicador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "", "Indicador", "Período de Análise"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableResultadosAnaliseIndicador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableResultadosAnaliseIndicadorMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTableResultadosAnaliseIndicador);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel1.setText("Titulo: ");

        jLabel2.setText("Data:");

        jTextFieldData.setEditable(false);
        jTextFieldData.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setText("Interpretação:");

        jTextAreaTomadaDeDecisao.setColumns(20);
        jTextAreaTomadaDeDecisao.setRows(2);
        jScrollPane1.setViewportView(jTextAreaTomadaDeDecisao);

        jLabel5.setText("Tomada de decisão:");

        jTextAreaInterpretacao.setColumns(20);
        jTextAreaInterpretacao.setRows(2);
        jScrollPane3.setViewportView(jTextAreaInterpretacao);

        jTableParticipantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(jTableParticipantes);

        jButton4.setText("Salvar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTableUsuariosInteressados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTableUsuariosInteressados);

        jLabel6.setText("Recursos Humanos");

        javax.swing.GroupLayout jPanelAnaliseReferLayout = new javax.swing.GroupLayout(jPanelAnaliseRefer);
        jPanelAnaliseRefer.setLayout(jPanelAnaliseReferLayout);
        jPanelAnaliseReferLayout.setHorizontalGroup(
            jPanelAnaliseReferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAnaliseReferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(41, 41, 41)
                .addGroup(jPanelAnaliseReferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldData)
                    .addComponent(jTextFieldTitulo))
                .addContainerGap())
            .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1)
                .addGap(10, 10, 10))
            .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                .addGroup(jPanelAnaliseReferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
                    .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
                    .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanelAnaliseReferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAnaliseReferLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelAnaliseReferLayout.setVerticalGroup(
            jPanelAnaliseReferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelAnaliseReferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAnaliseReferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelAnaliseReferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        jTabbedPane1.addTab("<html>Novo<br>Resultado</html>", jPanelAnaliseRefer);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelProjeto.setBackground(new java.awt.Color(204, 204, 204));
        jLabelProjeto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelProjeto.setText("Projeto");
        jLabelProjeto.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelProjeto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelProjeto)
        );

        jLabelStatusProjeto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelStatusProjeto.setText("Status do Projeto:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Descrição:");

        jTextAreaDescriçãoProjeto.setEditable(false);
        jTextAreaDescriçãoProjeto.setColumns(20);
        jTextAreaDescriçãoProjeto.setRows(5);
        jScrollPane10.setViewportView(jTextAreaDescriçãoProjeto);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Objetivos de Medição");
        jLabel12.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12)
        );

        jTableInfoGerais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", "", null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "OBJETIVO ESTRATÉGICO ", "NECESSIDADE DE INFORMAÇÃO", "INDICADOR", "PRIORIDADE"
            }
        ));
        jScrollPane13.setViewportView(jTableInfoGerais);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelStatusProjeto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane10))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelStatusProjeto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        jScrollPane9.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanelInfoGeraisLayout = new javax.swing.GroupLayout(jPanelInfoGerais);
        jPanelInfoGerais.setLayout(jPanelInfoGeraisLayout);
        jPanelInfoGeraisLayout.setHorizontalGroup(
            jPanelInfoGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
        );
        jPanelInfoGeraisLayout.setVerticalGroup(
            jPanelInfoGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("<html>Informações<br>Gerais</html>", jPanelInfoGerais);

        jPanelPlot.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanelPlotLayout = new javax.swing.GroupLayout(jPanelPlot);
        jPanelPlot.setLayout(jPanelPlotLayout);
        jPanelPlotLayout.setHorizontalGroup(
            jPanelPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelPlotLayout.setVerticalGroup(
            jPanelPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 341, Short.MAX_VALUE)
        );

        jLabel8.setText("Análise:");

        jTextAreaAnalise.setEditable(false);
        jTextAreaAnalise.setColumns(20);
        jTextAreaAnalise.setRows(5);
        jScrollPane5.setViewportView(jTextAreaAnalise);

        jLabel9.setText("Observação:");

        jTextAreaObservacao.setEditable(false);
        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setRows(5);
        jScrollPane8.setViewportView(jTextAreaObservacao);

        javax.swing.GroupLayout jPanelInformaçõesLayout = new javax.swing.GroupLayout(jPanelInformações);
        jPanelInformações.setLayout(jPanelInformaçõesLayout);
        jPanelInformaçõesLayout.setHorizontalGroup(
            jPanelInformaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformaçõesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInformaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                    .addGroup(jPanelInformaçõesLayout.createSequentialGroup()
                        .addGroup(jPanelInformaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane8))
                .addContainerGap())
        );
        jPanelInformaçõesLayout.setVerticalGroup(
            jPanelInformaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformaçõesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("<html>Informações<br>da Análise</html>", jPanelInformações);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jTableResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título"
            }
        ));
        jScrollPane6.setViewportView(jTableResultados);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableResultadosAnaliseIndicadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableResultadosAnaliseIndicadorMouseClicked
        pegaAnaliseDoIndicadorSelecionado();
        buscarListaValoresIndicador(analiseSelecioanda.getAnaliseDE(), analiseSelecioanda.getAnaliseATE(), analiseSelecioanda.getIndicadorid().getId());
        String tipoGrafico = analiseSelecioanda.getIndicadorid().getProcedimentodeanaliseList().get(0).getGraficoNome();
        EscolheTipoDeGrafico(tipoGrafico);
        preencherCamposDaAnaliseDoIndicador();
        jTabbedPane1.setEnabledAt(2, true);
    }//GEN-LAST:event_jTableResultadosAnaliseIndicadorMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        salvarResultado();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextFieldBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscaActionPerformed
        preencherTabelaResultadosPorBusca(jTextFieldBusca.getText());
    }//GEN-LAST:event_jTextFieldBuscaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelProjeto;
    private javax.swing.JLabel jLabelStatusProjeto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelAnaliseRefer;
    private javax.swing.JPanel jPanelInfoGerais;
    private javax.swing.JPanel jPanelInformações;
    private javax.swing.JPanel jPanelPlot;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableInfoGerais;
    private javax.swing.JTable jTableParticipantes;
    private javax.swing.JTable jTableResultados;
    private javax.swing.JTable jTableResultadosAnaliseIndicador;
    private javax.swing.JTable jTableUsuariosInteressados;
    private javax.swing.JTextArea jTextAreaAnalise;
    private javax.swing.JTextArea jTextAreaDescriçãoProjeto;
    private javax.swing.JTextArea jTextAreaInterpretacao;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextArea jTextAreaTomadaDeDecisao;
    private javax.swing.JTextField jTextFieldBusca;
    private javax.swing.JTextField jTextFieldData;
    private javax.swing.JTextField jTextFieldTitulo;
    // End of variables declaration//GEN-END:variables
}
