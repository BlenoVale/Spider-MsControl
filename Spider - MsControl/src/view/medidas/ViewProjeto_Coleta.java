package view.medidas;

import controller.Calculo;
import controller.CtrlColeta;
import controller.CtrlMedida;
import controller.CtrlProcedimentosColeta;
import facade.FacadeJpa;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.Coleta;
import model.Datasprocedimentocoleta;
import model.Medida;
import model.Valormedida;
import util.Copia;
import util.Internal;
import util.MyDefaultTableModel;
import util.Texto;

/**
 *
 * @author Géssica
 */
public class ViewProjeto_Coleta extends javax.swing.JInternalFrame {

    private DefaultTableModel defaultTableModel;
    private final CtrlColeta ctrlColeta = new CtrlColeta();
    private final CtrlMedida ctrlMedida = new CtrlMedida();
    private final CtrlProcedimentosColeta ctrlProcedimentosColeta = new CtrlProcedimentosColeta();
    private DefaultListModel modelJlist = new DefaultListModel();
    private MyDefaultTableModel tableModel;
    private List<Medida> listaMedida;
    private List<Coleta> listaColeta;
    private final FacadeJpa jpa = FacadeJpa.getInstance();
    private Medida medidaSelecionada = new Medida();
    private int contador;

    private Coleta coleta;
    private boolean ehNovaColeta;

    public ViewProjeto_Coleta() {
        initComponents();
        Internal.retiraBotao(this);

        temProcedimento(null);
    }

    //Tabela de medidas:
    public void preencherTabela(List<Medida> listMedida) {
        tableModel = new MyDefaultTableModel(new String[]{"Medida"}, 0, false);
        jTableMedidas.setModel(tableModel);
        for (int i = 0; i < listMedida.size(); i++) {
            String[] linhas = new String[]{
                listMedida.get(i).getNome()
            };
            tableModel.addRow(linhas);
        }
        jTableMedidas.setModel(tableModel);
    }

    private void atualizaListaMedidaDoProjeto() {
        int idDoProjeto = Copia.getProjetoSelecionado().getId();

        listaMedida = new ArrayList<Medida>();
        listaMedida = ctrlMedida.getMedidaDoProjeto(idDoProjeto);
    }

    public void preencherTabelaMedidaDoProjeto() {
        atualizaListaMedidaDoProjeto();
        preencherTabela(listaMedida);
    }

    private void pegarMedidaSelecionada() {
        int idDoProjeto = Copia.getProjetoSelecionado().getId();
        medidaSelecionada = ctrlMedida.buscarMedidaPeloNome(jTableMedidas.getValueAt(jTableMedidas.getSelectedRow(), 0).toString(), idDoProjeto);

        modelJlist = new DefaultListModel();
        jListColetasASalvar.setModel(modelJlist);
    }

    //Tabela de Coletas:
    public void preencherTabelaColeta(List<Coleta> listcoleta) {
        tableModel = new MyDefaultTableModel(new String[]{"<html>Coletas da medida <b>" + medidaSelecionada.getNome() + "</html>"}, 0, false);
        jTableColetas.setModel(tableModel);
        for (int i = 0; i < listaColeta.size(); i++) {
            String[] linhas = new String[]{
                listaColeta.get(i).getValorDaColeta().toString()
            };
            tableModel.addRow(linhas);
        }
        jTableColetas.setModel(tableModel);
    }

    private void atualizaListaColetaDoProjeto() {
        int idDoProjeto = Copia.getProjetoSelecionado().getId();

        listaColeta = new ArrayList<Coleta>();
        listaColeta = ctrlColeta.getColetaDoProjeto(idDoProjeto);
    }

    public void preencherTabelaColetaDoProjeto() {
        atualizaListaColetaDoProjeto();
        preencherTabelaColeta(listaColeta);
    }

    public String checaTextoDigitadoEmFormatoDouble(String numero) {

        if (numero.length() == 0) {
            return numero;
        }

        String numeroQuebrado[] = numero.split(",");

        if (numero.substring(numero.length() - 1, numero.length()).equals(",")) {
            return numero;
        }

        if (numeroQuebrado.length == 2) {
            numero = numeroQuebrado[0] + "." + numeroQuebrado[1];
        }

        String ultimaStringValida = "";
        for (int i = 0; i < numero.length(); i++) {
            String letras = numero.substring(0, i + 1);
            try {
                Double.parseDouble(letras);
                ultimaStringValida = letras;
            } catch (Exception e) {
                break;
            }
        }

        return ultimaStringValida;

    }

    private void cadastraColeta() {

        medidaSelecionada.getProcedimentodecoletaList().get(0).setContadorColeta(contador);

        Coleta coletaAux = new Coleta();
        boolean passou = false;
        for (int i = 0; i < jListColetasASalvar.getModel().getSize(); i++) {
            coletaAux.setData(new Date());
            coletaAux.setMedidaid(medidaSelecionada);
            coletaAux.setValorDaColeta(Double.parseDouble(jListColetasASalvar.getModel().getElementAt(i).toString()));
            coletaAux.setUsado(false);
            System.out.println("Coleta: " + coletaAux.getValorDaColeta());

            passou = ctrlColeta.cadastrarColeta(coletaAux);
        }

        if (passou) {
            passou = ctrlProcedimentosColeta.editarProcedimentoColeta(medidaSelecionada.getProcedimentodecoletaList().get(0));
        }

        if (passou) {
            JOptionPane.showMessageDialog(null, "Cadastrado como sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        if (jListColetasASalvar.getModel().getSize() == 0) {
            JOptionPane.showMessageDialog(this, "Não há novas medidas a serem salvas.");
            return false;
        } else if (jTableMedidas.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma medida");
            return false;
        }
        return true;
    }

    private void temProcedimento(Medida medida) {
        contador = 0;
        if (medida == null) {
            jLabelTipo.setHorizontalAlignment(JLabel.CENTER);
            jLabelTipo.setText("<html>Selecione uma medida na tabela.<br>&nbsp;</html>");
            jLabelTipo.setForeground(Color.BLACK);
            jLabelTipoDescri.setVisible(false);
            jLabelValor.setVisible(false);
            jTextFieldValorColeta.setVisible(false);
            jButtonImporta.setVisible(false);
            jButtonSalvar.setEnabled(false);
            return;
        }
        if (medida.getProcedimentodecoletaList().isEmpty()) {
            jLabelTipo.setHorizontalAlignment(JLabel.CENTER);
            jLabelTipo.setText("<html>Esta medida não possui<br>procedimento de coleta. Você deve criar <br>um procedimento de coleta para poder<br>fazer a coleta de dados.<br>&nbsp;</html>");
            jLabelTipo.setForeground(Color.RED);
            jLabelTipoDescri.setVisible(false);
            jLabelValor.setVisible(false);
            jTextFieldValorColeta.setVisible(false);
            jButtonImporta.setVisible(false);
            jButtonSalvar.setEnabled(false);
            return;
        }
        List<Datasprocedimentocoleta> datas = new ArrayList<>();
        datas = medida.getProcedimentodecoletaList().get(0).getDatasprocedimentocoletaList();
        defaultTableModel = new MyDefaultTableModel(new String[]{"Período da coleta"}, 0, false);
        switch (medida.getProcedimentodecoletaList().get(0).getPeriodicidade()) {
            case "Diário":
                jLabelPeriodicidade.setText("<html><center>Periodicidade: <b>Diário</b></center></html>");
                for (Datasprocedimentocoleta data : datas) {
                    defaultTableModel.addRow(new String[]{"<html><center><b>" + data.getDia() + "</b></center></html>"});
                }
                jTablePeriodicidade.setModel(defaultTableModel);
                break;
            case "Semanal":
                jLabelPeriodicidade.setText("<html><center>Periodicidade: <b>Semanal</b></center></html>");
                defaultTableModel.addRow(new String[]{"<html><center>Dia Inicial: <b>" + datas.get(0).getDia() + "</b></center></html>"});
                defaultTableModel.addRow(new String[]{"<html><center>Dia Final: <b>" + datas.get(1).getDia() + "</b></center></html>"});
                jTablePeriodicidade.setModel(defaultTableModel);
                break;
            default:
                String periodicidade = medida.getProcedimentodecoletaList().get(0).getPeriodicidade();
                jLabelPeriodicidade.setText("<html><center>Periodicidade: <b>" + periodicidade + "</b></center></html>");
                defaultTableModel.addRow(new String[]{"<html><center>Data Inicial: <b>" + Texto.formataDataPraTabela(datas.get(0).getDataInicio()) + "</b></center></html>"});
                defaultTableModel.addRow(new String[]{"<html><center>Data Final: <b>" + Texto.formataDataPraTabela(datas.get(0).getDataFim()) + "</b></center></html>"});
                jTablePeriodicidade.setModel(defaultTableModel);
                break;
        }
        jLabelFrequencia.setText("<html>Frequência: <b>" + medida.getProcedimentodecoletaList().get(0).getFrequencia() + "</b></html>");
        contador = medidaSelecionada.getProcedimentodecoletaList().get(0).getContadorColeta();
        jLabelJaColetados.setText("<html>Já coletados: <b>" + contador + "</b></html>");

        checaLimitePeriodicidade(medida);
        checaLimiteFrequencia(medida);
    }

    private void tipoDeColeta(boolean passou, Medida medida, String mensagem) {
        if (passou) {
            switch (medida.getProcedimentodecoletaList().get(0).getTipoDeColeta()) {
                case "Manual":
                    jLabelTipo.setText("Manual");
                    jTextFieldValorColeta.setVisible(true);
                    jButtonImporta.setVisible(false);
                    break;
                case "Planilha Eletrônica":
                    jLabelTipo.setText("Planilha Eletrônica");
                    jTextFieldValorColeta.setVisible(false);
                    jButtonImporta.setVisible(true);
                    break;
            }
            jLabelTipo.setForeground(Color.BLACK);
            jLabelTipo.setHorizontalAlignment(JLabel.LEADING);
            jLabelValor.setVisible(true);
            jButtonSalvar.setEnabled(true);
            jLabelTipoDescri.setVisible(true);
        } else {
            jLabelTipo.setHorizontalAlignment(JLabel.CENTER);
            jLabelTipo.setText(mensagem);
            jLabelTipo.setForeground(Color.BLACK);
            jLabelTipoDescri.setVisible(false);
            jLabelValor.setVisible(false);
            jTextFieldValorColeta.setVisible(false);
            jButtonImporta.setVisible(false);
            jButtonSalvar.setEnabled(false);
            jLabelTipoDescri.setVisible(false);

        }
    }

    private void checaLimiteFrequencia(Medida medida) {
        int frequencia = medida.getProcedimentodecoletaList().get(0).getFrequencia();
        tipoDeColeta(frequencia > contador, medida, "<html>Limite de coletas já atingido.<br>&nbsp;</html>");
    }

    private void checaLimitePeriodicidade(Medida medida) {
        switch (medida.getProcedimentodecoletaList().get(0).getPeriodicidade()) {
            case "Diário":
                List<Datasprocedimentocoleta> datasProcedimento = medida.getProcedimentodecoletaList().get(0).getDatasprocedimentocoletaList();
                Date dataHojeDiario = new Date();
                boolean ehOdia = false;
                for (int i = 0; i < datasProcedimento.size(); i++) {
                    switch (datasProcedimento.get(i).getDia()) {
                        case "Domingo":
                            ehOdia = dataHojeDiario.getDay() == 0;
                            break;
                        case "Segunda-feira":
                            ehOdia = dataHojeDiario.getDay() == 1;
                            break;
                        case "Terça-feira":
                            ehOdia = dataHojeDiario.getDay() == 2;
                            break;
                        case "Quarta-feira":
                            ehOdia = dataHojeDiario.getDay() == 3;
                            break;
                        case "Quinta-feira":
                            ehOdia = dataHojeDiario.getDay() == 4;
                            break;
                        case "Sexta-feira":
                            ehOdia = dataHojeDiario.getDay() == 5;
                            break;
                        case "Sabádo":
                            ehOdia = dataHojeDiario.getDay() == 6;
                            break;
                    }
                    tipoDeColeta(ehOdia, medida, "<html>Fora do periodo de coleta.<br>&nbsp;</html>");
                    if (ehOdia) {
                        break;
                    }
                }
                break;
            case "Semanal":
                break;
            default:
                Date dataInicio = medida.getProcedimentodecoletaList().get(0).getDatasprocedimentocoletaList().get(0).getDataInicio();
                Date dataFim = medida.getProcedimentodecoletaList().get(0).getDatasprocedimentocoletaList().get(0).getDataFim();
                Date dataHojeOutros = new Date();
                boolean passou = (dataHojeOutros.compareTo(dataInicio) >= 0 && dataHojeOutros.compareTo(dataFim) < 1);
                tipoDeColeta(passou, medida, "<html>Fora do periodo de coleta.<br>&nbsp;</html>");
                break;
        }
    }

    public void calcularSePeriodoFoiAtingido() {
        boolean resposta = false;
        atualizaListaMedidaDoProjeto();
        Date dataHoje;
        for (int j = 0; j < listaMedida.size(); j++) {
            listaColeta = ctrlColeta.getColetaDaMedidaNaoUsadas(listaMedida.get(j).getId());
            switch (listaMedida.get(j).getProcedimentodecoletaList().get(0).getPeriodicidade()) {
                case "Diário":
                    List<Datasprocedimentocoleta> datasProcedimento = listaMedida.get(j).getProcedimentodecoletaList().get(0).getDatasprocedimentocoletaList();
                    dataHoje = new Date();
                    boolean diaApos = false;
                    for (int i = 0; i < datasProcedimento.size(); i++) {
                        switch (datasProcedimento.get(i).getDia()) {
                            case "Domingo":
                                diaApos = dataHoje.getDay() == 1;
                                break;
                            case "Segunda-feira":
                                diaApos = dataHoje.getDay() == 2;
                                break;
                            case "Terça-feira":
                                diaApos = dataHoje.getDay() == 3;
                                break;
                            case "Quarta-feira":
                                diaApos = dataHoje.getDay() == 4;
                                break;
                            case "Quinta-feira":
                                diaApos = dataHoje.getDay() == 5;
                                break;
                            case "Sexta-feira":
                                diaApos = dataHoje.getDay() == 6;
                                break;
                            case "Sabádo":
                                diaApos = dataHoje.getDay() == 0;
                                break;
                        }

                        if (diaApos && !listaColeta.isEmpty()) {
                            resposta = new Calculo().porcentagemMinimaAtingida(listaMedida.get(j).getProcedimentodecoletaList().get(0));
                            if (resposta) {
                                calcularValorColeta(listaMedida.get(j));
                            } else {

                            }
                            break;
                        }
                    }
                    break;
                case "Semanal":
                    String dataFimSemanal = listaMedida.get(j).getProcedimentodecoletaList().get(0).getDatasprocedimentocoletaList().get(1).getDia();
                    dataHoje = new Date();
                    boolean eHproxSemana = false;
                    switch (dataFimSemanal) {
                        case "Domingo":
                            eHproxSemana = (dataHoje.getDay() == 1 && !(listaMedida.get(j).getProcedimentodecoletaList().get(0).getData().equals(dataHoje)));
                            break;
                        case "Segunda-feira":
                            eHproxSemana = (dataHoje.getDay() == 2 && !(listaMedida.get(j).getProcedimentodecoletaList().get(0).getData().equals(dataHoje)));
                            break;
                        case "Terça-feira":
                            eHproxSemana = (dataHoje.getDay() == 3 && !(listaMedida.get(j).getProcedimentodecoletaList().get(0).getData().equals(dataHoje)));
                            break;
                        case "Quarta-feira":
                            eHproxSemana = (dataHoje.getDay() == 4 && !(listaMedida.get(j).getProcedimentodecoletaList().get(0).getData().equals(dataHoje)));
                            break;
                        case "Quinta-feira":
                            eHproxSemana = (dataHoje.getDay() == 5 && !(listaMedida.get(j).getProcedimentodecoletaList().get(0).getData().equals(dataHoje)));
                            break;
                        case "Sexta-feira":
                            eHproxSemana = (dataHoje.getDay() == 6 && !(listaMedida.get(j).getProcedimentodecoletaList().get(0).getData().equals(dataHoje)));
                            break;
                        case "Sabádo":
                            eHproxSemana = (dataHoje.getDay() == 0 && !(listaMedida.get(j).getProcedimentodecoletaList().get(0).getData().equals(dataHoje)));
                            break;
                    }

                    if (eHproxSemana && !listaColeta.isEmpty()) {
                        resposta = new Calculo().porcentagemMinimaAtingida(listaMedida.get(j).getProcedimentodecoletaList().get(0));
                        if (resposta) {
                            calcularValorColeta(listaMedida.get(j));
                        } else {

                        }
                        break;
                    }
                    break;
                default:
                    Date dataFim = listaMedida.get(j).getProcedimentodecoletaList().get(0).getDatasprocedimentocoletaList().get(0).getDataFim();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dataFim);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    Date dataHojeOutros = new Date();
                    boolean passou = (dataHojeOutros.equals(calendar.getTime()));
                    if (passou && !listaColeta.isEmpty()) {
                        resposta = new Calculo().porcentagemMinimaAtingida(listaMedida.get(j).getProcedimentodecoletaList().get(0));
                        if (resposta) {
                            calcularValorColeta(listaMedida.get(j));
                        } else {

                        }
                        break;
                    }
                    break;
            }
        }
    }

    private void calcularValorColeta(Medida medida) {
        Calculo calculo = new Calculo();
        Valormedida valormedida = new Valormedida();
        double valor = 0;
        listaColeta = new CtrlColeta().getColetaDaMedidaNaoUsadas(medida.getId());
        switch (medida.getProcedimentodecoletaList().get(0).getCalculo()) {
            case "Média":
                valor = calculo.media(listaColeta);
                System.out.println("Média: " + valor);
                break;
            case "Mediana":
                valor = calculo.mediana(listaColeta);
                System.out.println("Mediana: " + valor);
                break;
            case "Moda":
                valor = calculo.moda(listaColeta);
                System.out.println("Moda: " + valor);
                break;
            case "Soma":
                valor = calculo.soma(listaColeta);
                System.out.println("Soma: " + valor);
                break;
            case "Sem Cálculo": 
                valor = medida.getColetaList().get(0).getValorDaColeta();
                break;

        }
        valormedida.setMedidaid(medida);
        valormedida.setData(new Date());
        valormedida.setUsado(false);
        valormedida.setValor(valor);
        boolean passou = ctrlMedida.cadastraValorMedida(valormedida);
        if (passou) {
            for (int i = 0; i < listaColeta.size(); i++) {
                ctrlColeta.editarColeta(listaColeta.get(i));
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMedidas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableColetas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabelPeriodicidade = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablePeriodicidade = new javax.swing.JTable();
        jLabelFrequencia = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabelJaColetados = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelTipoDescri = new javax.swing.JLabel();
        jLabelValor = new javax.swing.JLabel();
        jTextFieldValorColeta = new javax.swing.JTextField();
        jLabelTipo = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonImporta = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListColetasASalvar = new javax.swing.JList();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonRemover = new javax.swing.JButton();

        setTitle("Coletas");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableMedidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medidas"
            }
        ));
        jTableMedidas.setName(""); // NOI18N
        jTableMedidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMedidasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMedidas);

        jTableColetas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Coletas"
            }
        ));
        jScrollPane2.setViewportView(jTableColetas);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelPeriodicidade.setText("Periodicidade:");

        jTablePeriodicidade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablePeriodicidade.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane4.setViewportView(jTablePeriodicidade);

        jLabelFrequencia.setText("Frequência:");

        jLabelJaColetados.setText("Já coletados:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelJaColetados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(jLabelPeriodicidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabelFrequencia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPeriodicidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabelFrequencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelJaColetados)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cadastro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabelTipoDescri.setText("Tipo de Coleta:");

        jLabelValor.setText("Valor da Coleta:");

        jTextFieldValorColeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldValorColetaActionPerformed(evt);
            }
        });
        jTextFieldValorColeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldValorColetaKeyReleased(evt);
            }
        });

        jLabelTipo.setText("Sem procedimento de coleta");

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

        jButtonImporta.setText("Importar");
        jButtonImporta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImportaActionPerformed(evt);
            }
        });

        jLabel4.setText("Coletas a salvar");

        jListColetasASalvar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListColetasASalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListColetasASalvarMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jListColetasASalvar);

        jButtonRemover.setText("Remover");
        jButtonRemover.setEnabled(false);
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelTipoDescri)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonImporta, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldValorColeta)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator1)
                .addGap(10, 10, 10))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(175, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonRemover))
                .addGap(23, 23, 23)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTipoDescri, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldValorColeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelValor)
                    .addComponent(jButtonImporta))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldValorColetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldValorColetaActionPerformed
        checaLimiteFrequencia(medidaSelecionada);
        if (jTextFieldValorColeta.getText().equals("")) {
            return;
        }
        modelJlist.addElement(jTextFieldValorColeta.getText());
        jTextFieldValorColeta.setText("");
        jListColetasASalvar.setModel(modelJlist);

        contador += 1;
        jLabelJaColetados.setText("<html>Já coletados: <b>" + contador + "</b></html>");
        checaLimiteFrequencia(medidaSelecionada);
        jButtonSalvar.setEnabled(true);
    }//GEN-LAST:event_jTextFieldValorColetaActionPerformed

    private void jTableMedidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMedidasMouseClicked
        pegarMedidaSelecionada();
        listaColeta = ctrlColeta.getColetaDaMedidaNaoUsadas(medidaSelecionada.getId());
        preencherTabelaColeta(listaColeta);

        temProcedimento(medidaSelecionada);
    }//GEN-LAST:event_jTableMedidasMouseClicked

    private void jListColetasASalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListColetasASalvarMouseClicked

        if (jListColetasASalvar.getSelectedIndex() == -1) {
            jButtonRemover.setEnabled(false);
        } else {
            jButtonRemover.setEnabled(true);
        }
    }//GEN-LAST:event_jListColetasASalvarMouseClicked

    private void jTextFieldValorColetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorColetaKeyReleased
        jTextFieldValorColeta.setText(checaTextoDigitadoEmFormatoDouble(jTextFieldValorColeta.getText()));
    }//GEN-LAST:event_jTextFieldValorColetaKeyReleased

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (!validarCampos()) {
            return;
        }
        cadastraColeta();
        pegarMedidaSelecionada();
        listaColeta = ctrlColeta.getColetaDaMedidaNaoUsadas(medidaSelecionada.getId());
        preencherTabelaColeta(listaColeta);
        modelJlist = new DefaultListModel();
        jListColetasASalvar.setModel(modelJlist);

        if (medidaSelecionada.getProcedimentodecoletaList().get(0).getFrequencia() == medidaSelecionada.getProcedimentodecoletaList().get(0).getContadorColeta()) {
            calcularValorColeta(medidaSelecionada);
        }

        checaLimiteFrequencia(medidaSelecionada);
        jButtonRemover.setEnabled(false);
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        modelJlist.removeElementAt(jListColetasASalvar.getSelectedIndex());
        jListColetasASalvar.setModel(modelJlist);

        contador -= 1;
        jLabelJaColetados.setText("<html>Já coletados: <b>" + contador + "</b></html>");
        jButtonRemover.setEnabled(false);
        checaLimiteFrequencia(medidaSelecionada);
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        modelJlist.removeAllElements();
        jListColetasASalvar.setModel(modelJlist);
        jButtonRemover.setEnabled(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonImportaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImportaActionPerformed
        JFileChooser chooser = new JFileChooser();
        //chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter(new FileNameExtensionFilter("Excel", "xls", "xlsx"));
        chooser.showOpenDialog(this);
        List<Coleta> listaDaColeta = new ArrayList<>();
        try {
            String file = chooser.getSelectedFile().getAbsolutePath();
            listaDaColeta = new viewExcelDialog(null, true).showExcelDialog(file);
        } catch (Exception ex) {
            return;
        }

        for (Coleta coleta : listaDaColeta) {
            modelJlist.addElement(coleta.getValorDaColeta());
        }
        jListColetasASalvar.setModel(modelJlist);

        contador += modelJlist.size();
        jLabelJaColetados.setText("<html>Já coletados: <b>" + contador + "</b></html>");
    }//GEN-LAST:event_jButtonImportaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonImporta;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelFrequencia;
    private javax.swing.JLabel jLabelJaColetados;
    private javax.swing.JLabel jLabelPeriodicidade;
    private javax.swing.JLabel jLabelTipo;
    private javax.swing.JLabel jLabelTipoDescri;
    private javax.swing.JLabel jLabelValor;
    private javax.swing.JList jListColetasASalvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTableColetas;
    private javax.swing.JTable jTableMedidas;
    private javax.swing.JTable jTablePeriodicidade;
    private javax.swing.JTextField jTextFieldValorColeta;
    // End of variables declaration//GEN-END:variables
}
