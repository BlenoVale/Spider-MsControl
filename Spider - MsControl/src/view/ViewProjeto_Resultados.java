package view;

import controller.CtrlAnalise;
import controller.CtrlIndicador;
import controller.CtrlMedida;
import controller.CtrlProjeto;
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
import model.Medida;
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
import view.indicadores.View_InformacaoDialog;

/**
 *
 * @author BlenoVale, Géssica
 */
public class ViewProjeto_Resultados extends javax.swing.JInternalFrame {

    private CheckDefaultTableModel checkmodel;
    private MyDefaultTableModel tableModel;
    private List<Resultados> listaResultados;
    private final CtrlResultados ctrlResultados = new CtrlResultados();
    private final CtrlProjeto ctrlProjeto = new CtrlProjeto();
    private final CtrlIndicador ctrlIndicador = new CtrlIndicador();
    private final CtrlMedida ctrlMedida = new CtrlMedida();
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

        listaAnalises = new ArrayList<>();
        listaAnalises = ctrlAnalise.buscarAnalisesDoProjeto(Copia.getProjetoSelecionado().getId());
        preencherTabelaAnaliseIndicador(listaAnalises);

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
    private void preencherTabelaAnaliseIndicador(List<Analise> lista) {
        checkModel = new CheckDefaultTableModel(new String[]{" ", "Indicador", "Data da Analise"}, 0, false);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lista.size(); i++) {
            String data = simpleDateFormat.format(lista.get(i).getDataCriação());
            Object[] linha = {
                false,
                lista.get(i).getIndicadorid().getNome(),
                data
            };
            checkModel.addRow(linha);
        }
        jTableResultadosAnaliseIndicador.setModel(checkModel);
        jTableResultadosAnaliseIndicador.getColumnModel().getColumn(0).setPreferredWidth(10);

        int aux = jTableResultadosAnaliseIndicador.getColumnModel().getColumn(1).getPreferredWidth();
        jTableResultadosAnaliseIndicador.getColumnModel().getColumn(0).setPreferredWidth(aux);
        jTableResultadosAnaliseIndicador.getColumnModel().getColumn(1).setPreferredWidth(aux * 7);
        jTableResultadosAnaliseIndicador.getColumnModel().getColumn(2).setPreferredWidth(aux * 3);
    }

    private boolean verificaDatas() {
        Date data1 = (Date) dateField_De.getValue();
        Date data2 = (Date) dateField_Ate.getValue();
        if (data1.after(new Date())) {
            dateField_De.setValue(new Date());
            return false;
        } else if (data2.after(new Date())) {
            dateField_Ate.setValue(new Date());
            return false;
        } else {
            return true;
        }
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

    private void pegarResultadoSelecionado() {
        if (jTableResultados.getSelectedRow() > -1) {
            int linhaSelecionada = jTableResultados.getSelectedRow();
            resultadoSelecionado = new Resultados();
            resultadoSelecionado = listaResultados.get(linhaSelecionada);
        }

    }

    public void preencherCamposResultados() {
        preencherTabelaAnaliseIndicador(resultadoSelecionado.getAnaliseList());
        for (int i = 0; i < jTableResultadosAnaliseIndicador.getModel().getRowCount(); i++) {
            jTableResultadosAnaliseIndicador.getModel().setValueAt(true, i, 0);
        }

        jTextFieldTitulo.setText(resultadoSelecionado.getTitulo());
        jTextAreaInterpretacao.setText(resultadoSelecionado.getInterpretacao());
        jTextAreaTomadaDeDecisao.setText(resultadoSelecionado.getTomadaDeDecisao());

        for (int i = 0; i < jTableParticipantes.getModel().getRowCount(); i++) {
            for (int j = 0; j < resultadoSelecionado.getParticipanteseInteressadosList().size(); j++) {
                if ("Participante".equals(resultadoSelecionado.getParticipanteseInteressadosList().get(j).getTipo())) {
                    String participante = jTableParticipantes.getModel().getValueAt(i, 1).toString();
                    if (participante.equals(resultadoSelecionado.getParticipanteseInteressadosList().get(j).getParticipanteEInteressado())) {
                        jTableParticipantes.getModel().setValueAt(true, i, 0);
                    }
                }
            }
        }

        for (int i = 0; i < jTableUsuariosInteressados.getModel().getRowCount(); i++) {
            for (int j = 0; j < resultadoSelecionado.getParticipanteseInteressadosList().size(); j++) {
                if ("Interessado".equals(resultadoSelecionado.getParticipanteseInteressadosList().get(j).getTipo())) {
                    String Interessado = jTableUsuariosInteressados.getModel().getValueAt(i, 1).toString();
                    if (Interessado.equals(resultadoSelecionado.getParticipanteseInteressadosList().get(j).getParticipanteEInteressado())) {
                        jTableUsuariosInteressados.getModel().setValueAt(true, i, 0);
                    }
                }
            }
        }
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
        Projeto projeto = ctrlProjeto.buscaProjetoPeloNome(Copia.getProjetoSelecionado().getNome());
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
        preencherTabelaInfoGerais(projeto);
        inicializaTabelaInformacoesGerais();
        preencherTabelaMedida();
        inicializarTabelaInfoMedida();
    }

    private void preencherTabelaInfoGerais(Projeto projeto) {
        tableModel = new MyDefaultTableModel(new String[]{"OBJETIVO ESTRATÉGICO", "NECESSIDADE DE INFORMAÇÃO", "INDICADOR", "PRIORIDADE"}, 0, false);
        for (int i = 0; i < projeto.getObjetivodemedicaoList().size(); i++) {

            if (!projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().isEmpty()) {

                for (int j = 0; j < projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().size(); j++) {

                    if (!projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().get(j).getIndicadorList().isEmpty()) {
                        String[] linha = {
                            projeto.getObjetivodemedicaoList().get(i).getNome() + " ",
                            projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().get(j).getNome() + " ",
                            projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().get(j).getIndicadorList().get(0).getNome() + " ",
                            String.valueOf(projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().get(j).getIndicadorList().get(0).getPrioridade() + " ")
                        };

                        tableModel.addRow(linha);

                    } else {
                        String[] linha = {
                            projeto.getObjetivodemedicaoList().get(i).getNome() + " ",
                            projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().get(j).getNome() + " ",
                            String.valueOf(" "),
                            String.valueOf(" ")
                        };

                        tableModel.addRow(linha);
                    }
                }
            } else {
                String[] linha = {
                    projeto.getObjetivodemedicaoList().get(i).getNome() + " ",
                    String.valueOf(" "),
                    String.valueOf(" "),
                    String.valueOf(" ")
                };

                tableModel.addRow(linha);
            }
        }
        jTableInfoGerais.setModel(tableModel);
    }

    private void inicializaTabelaInformacoesGerais() {
        for (int i = 0; i < jTableInfoGerais.getColumnCount(); i++) {
            TableColumn col = jTableInfoGerais.getColumnModel().getColumn(i);
            col.setCellRenderer(new TableTextAreaRenderer());
        }

        int tamnhoColuna = jTableInfoGerais.getColumnModel().getColumn(1).getPreferredWidth();
        jTableInfoGerais.getColumnModel().getColumn(0).setPreferredWidth(tamnhoColuna * 8);
        jTableInfoGerais.getColumnModel().getColumn(1).setPreferredWidth(tamnhoColuna * 7);
        jTableInfoGerais.getColumnModel().getColumn(2).setPreferredWidth(tamnhoColuna * 4);
        jTableInfoGerais.getColumnModel().getColumn(3).setPreferredWidth(tamnhoColuna * 3);

        //jTableInfoGerais.setRowHeight(45);
    }

    private void preencherTabelaMedida() {
        List<Medida> listaMedida = ctrlMedida.getMedidaDoProjeto(Copia.getProjetoSelecionado().getId());
        tableModel = new MyDefaultTableModel(new String[]{"Medida", "Mnemônico", "Periodicidade de Coleta"}, 0, false);
        for (int i = 0; i < listaMedida.size(); i++) {
            if (!listaMedida.get(i).getProcedimentodecoletaList().isEmpty()) {
                String[] linha = {
                    listaMedida.get(i).getNome(),
                    listaMedida.get(i).getMnemonico(),
                    listaMedida.get(i).getProcedimentodecoletaList().get(0).getPeriodicidade()
                };
                tableModel.addRow(linha);
            } else {
                String[] linha = {
                    listaMedida.get(i).getNome(),
                    listaMedida.get(i).getMnemonico(),
                    " "
                };
                tableModel.addRow(linha);
            }
        }
        jTableMedida.setModel(tableModel);
    }

    private void inicializarTabelaInfoMedida() {
        for (int i = 0; i < jTableMedida.getColumnCount(); i++) {
            TableColumn col = jTableMedida.getColumnModel().getColumn(i);
            col.setCellRenderer(new TableTextAreaRenderer());
        }

        int tamnhoColuna = jTableMedida.getColumnModel().getColumn(1).getPreferredWidth();
        jTableMedida.getColumnModel().getColumn(0).setPreferredWidth(tamnhoColuna * 8);
        jTableMedida.getColumnModel().getColumn(1).setPreferredWidth(tamnhoColuna * 7);
        jTableMedida.getColumnModel().getColumn(2).setPreferredWidth(tamnhoColuna * 4);

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
        jTextFieldAnalisadoPor.setText(analiseSelecioanda.getNomeUsuario() + ", Em: " + Texto.formataData(analiseSelecioanda.getDataCriação()));
        jTextFieldFormula.setText(analiseSelecioanda.getIndicadorid().getProcedimentodeanaliseList().get(0).getFormula());
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
        jPanel6 = new javax.swing.JPanel();
        jLabelProjeto = new javax.swing.JLabel();
        jLabelStatusProjeto = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextAreaDescriçãoProjeto = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTableInfoGerais = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTableMedida = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanelInformações = new javax.swing.JPanel();
        jPanelPlot = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaAnalise = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldFormula = new javax.swing.JTextField();
        jButtonInfoDoIndicador = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldAnalisadoPor = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        dateField_De = new net.sf.nachocalendar.components.DateField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        dateField_Ate = new net.sf.nachocalendar.components.DateField();
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
                .addGroup(jPanelAnaliseReferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE))
                    .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE))
                    .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAnaliseReferLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAnaliseReferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAnaliseReferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelAnaliseReferLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        jTabbedPane1.addTab("<html>Novo<br>Resultado</html>", jPanelAnaliseRefer);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelProjeto.setBackground(new java.awt.Color(204, 204, 204));
        jLabelProjeto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelProjeto.setText("Projeto");
        jLabelProjeto.setToolTipText("");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelProjeto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(145, 145, 145))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelProjeto)
        );

        jLabelStatusProjeto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelStatusProjeto.setText("Status do Projeto:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Descrição:");

        jTextAreaDescriçãoProjeto.setEditable(false);
        jTextAreaDescriçãoProjeto.setColumns(20);
        jTextAreaDescriçãoProjeto.setRows(2);
        jScrollPane12.setViewportView(jTextAreaDescriçãoProjeto);

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setBackground(new java.awt.Color(204, 204, 204));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Objetivos de Medição");
        jLabel15.setToolTipText("");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addGap(296, 296, 296))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15)
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
        jScrollPane15.setViewportView(jTableInfoGerais);

        jTableMedida.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane16.setViewportView(jTableMedida);

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Medidas");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(391, 391, 391))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout jPanelInfoGeraisLayout = new javax.swing.GroupLayout(jPanelInfoGerais);
        jPanelInfoGerais.setLayout(jPanelInfoGeraisLayout);
        jPanelInfoGeraisLayout.setHorizontalGroup(
            jPanelInfoGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoGeraisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInfoGeraisLayout.createSequentialGroup()
                        .addGroup(jPanelInfoGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelStatusProjeto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE))
                        .addGap(35, 35, 35))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane15)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane16)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane12))
                .addContainerGap())
        );
        jPanelInfoGeraisLayout.setVerticalGroup(
            jPanelInfoGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoGeraisLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelStatusProjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
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

        jTextAreaAnalise.setEditable(false);
        jTextAreaAnalise.setColumns(20);
        jTextAreaAnalise.setRows(5);
        jScrollPane5.setViewportView(jTextAreaAnalise);

        jTextAreaObservacao.setEditable(false);
        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setRows(5);
        jScrollPane8.setViewportView(jTextAreaObservacao);

        jLabel10.setText("<html>Formula utilizada para calcular os valores dos Indicadores:</html>");

        jButtonInfoDoIndicador.setText("Informações do Indicador");
        jButtonInfoDoIndicador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInfoDoIndicadorActionPerformed(evt);
            }
        });

        jLabel8.setText("Observação:");

        jLabel9.setText("Critério de Análise:");

        jLabel13.setText("Análisado por:");

        jTextFieldAnalisadoPor.setEditable(false);
        jTextFieldAnalisadoPor.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanelInformaçõesLayout = new javax.swing.GroupLayout(jPanelInformações);
        jPanelInformações.setLayout(jPanelInformaçõesLayout);
        jPanelInformaçõesLayout.setHorizontalGroup(
            jPanelInformaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformaçõesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInformaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                    .addComponent(jScrollPane8)
                    .addComponent(jTextFieldFormula)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInformaçõesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonInfoDoIndicador, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelInformaçõesLayout.createSequentialGroup()
                        .addGroup(jPanelInformaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextFieldAnalisadoPor))
                .addContainerGap())
        );
        jPanelInformaçõesLayout.setVerticalGroup(
            jPanelInformaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformaçõesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButtonInfoDoIndicador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelPlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldAnalisadoPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldFormula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("<html>Informações<br>da Análise</html>", jPanelInformações);

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateField_De.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dateField_DeStateChanged(evt);
            }
        });

        jLabel17.setText("                                          De:");

        jLabel18.setText("                                        Ate:");

        dateField_Ate.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dateField_AteStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateField_De, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateField_Ate, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(dateField_De, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(dateField_Ate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel18))))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addGap(11, 11, 11))
        );

        jTableResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título"
            }
        ));
        jTableResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableResultadosMouseClicked(evt);
            }
        });
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
                        .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(11, 11, 11))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        salvarResultado();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextFieldBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscaActionPerformed
        preencherTabelaResultadosPorBusca(jTextFieldBusca.getText());
    }//GEN-LAST:event_jTextFieldBuscaActionPerformed

    private void jButtonInfoDoIndicadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInfoDoIndicadorActionPerformed
        View_InformacaoDialog view_InformacaoDialog = new View_InformacaoDialog(null, false);
        double metaCritico = analiseSelecioanda.getIndicadorid().getProcedimentodeanaliseList().get(0).getMetaCritico();
        double metaOK = analiseSelecioanda.getIndicadorid().getProcedimentodeanaliseList().get(0).getMetaOk();
        if (metaCritico < metaOK) {
            view_InformacaoDialog.preencheCamposInfoCrescente(analiseSelecioanda.getIndicadorid().getProcedimentodeanaliseList().get(0));
        } else {
            view_InformacaoDialog.preencheCamposInfoDecrescente(analiseSelecioanda.getIndicadorid().getProcedimentodeanaliseList().get(0));
        }
        view_InformacaoDialog.setVisible(true);
    }//GEN-LAST:event_jButtonInfoDoIndicadorActionPerformed

    private void dateField_DeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateField_DeStateChanged
        if (verificaDatas()) {
            listaAnalises = new ArrayList<>();
            listaAnalises = ctrlAnalise.buscarAnaliseDoProjetoPorDatas((Date) dateField_De.getValue(), (Date) dateField_Ate.getValue(), Copia.getProjetoSelecionado().getId());
            preencherTabelaAnaliseIndicador(listaAnalises);
        } else {
            JOptionPane.showMessageDialog(null, "Data não pode ser maior que data atual.");
        }
    }//GEN-LAST:event_dateField_DeStateChanged

    private void dateField_AteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateField_AteStateChanged
        if (verificaDatas()) {
            listaAnalises = new ArrayList<>();
            listaAnalises = ctrlAnalise.buscarAnaliseDoProjetoPorDatas((Date) dateField_De.getValue(), (Date) dateField_Ate.getValue(), Copia.getProjetoSelecionado().getId());
            preencherTabelaAnaliseIndicador(listaAnalises);
        } else {
            JOptionPane.showMessageDialog(null, "Data não pode ser maior que data atual.");
        }
    }//GEN-LAST:event_dateField_AteStateChanged

    private void jTableResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableResultadosMouseClicked
        pegarResultadoSelecionado();
        preencherCamposResultados();
    }//GEN-LAST:event_jTableResultadosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private net.sf.nachocalendar.components.DateField dateField_Ate;
    private net.sf.nachocalendar.components.DateField dateField_De;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonInfoDoIndicador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAnaliseRefer;
    private javax.swing.JPanel jPanelInfoGerais;
    private javax.swing.JPanel jPanelInformações;
    private javax.swing.JPanel jPanelPlot;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableInfoGerais;
    private javax.swing.JTable jTableMedida;
    private javax.swing.JTable jTableParticipantes;
    private javax.swing.JTable jTableResultados;
    private javax.swing.JTable jTableResultadosAnaliseIndicador;
    private javax.swing.JTable jTableUsuariosInteressados;
    private javax.swing.JTextArea jTextAreaAnalise;
    private javax.swing.JTextArea jTextAreaDescriçãoProjeto;
    private javax.swing.JTextArea jTextAreaInterpretacao;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextArea jTextAreaTomadaDeDecisao;
    private javax.swing.JTextField jTextFieldAnalisadoPor;
    private javax.swing.JTextField jTextFieldBusca;
    private javax.swing.JTextField jTextFieldData;
    private javax.swing.JTextField jTextFieldFormula;
    private javax.swing.JTextField jTextFieldTitulo;
    // End of variables declaration//GEN-END:variables
}
