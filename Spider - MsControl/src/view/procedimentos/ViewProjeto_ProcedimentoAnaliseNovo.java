package view.procedimentos;

import facade.FacadeJpa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Indicador;
import model.Medida;
import model.Procedimentodeanalise;
import model.Projeto;
import model.Registroprocedimentoanalise;
import util.Constantes;
import util.Copia;
import util.MyDefaultTableModel;
import util.Texto;

/**
 *
 * @author Géssica
 */
public class ViewProjeto_ProcedimentoAnaliseNovo extends javax.swing.JDialog {

    private DefaultComboBoxModel comboboxModel;
    private Procedimentodeanalise procedimentodeanalise;
    private Projeto projeto_selecionado;
    private String nomeUsuario_logado;
    private DefaultListModel model_listaDePerfis;
    private DefaultListModel model_listaMeio;
    private List<Registroprocedimentoanalise> registro;
    private FacadeJpa jpa = FacadeJpa.getInstance();
    private MyDefaultTableModel tableModel;
    private List<Medida> listMedida;

    private boolean ehNovoProcedimentoAnalise;

    public ViewProjeto_ProcedimentoAnaliseNovo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        agruparBotoesRadio();
        this.setLocationRelativeTo(null);
        iniciarTabela();
        recarregarTabela();
    }

    public void iniciarTabela() {
        tableModel = new MyDefaultTableModel(new String[]{"Medida", "Mnemônico"}, 0, false);
        jTableMedida.setModel(tableModel);
    }

    public void preencherTabela(List<Medida> listMedida) {

        for (int i = 0; i < listMedida.size(); i++) {
            String linhas[] = new String[]{
                listMedida.get(i).getNome(), listMedida.get(i).getMnemonico()

            };
            tableModel.addRow(linhas);
        }
        jTableMedida.setModel(tableModel);
    }

    public void recarregarTabela() {
        iniciarTabela();
        listMedida = jpa.getMedidaJpa().findByProjeto(Copia.getProjetoSelecionado().getId());
        preencherTabela(listMedida);

    }

    public void showNovoProcedimentodeanalise() {

        this.setTitle("Cadastro de Novo Procedimento de Análise");
        procedimentodeanalise = new Procedimentodeanalise();

        ehNovoProcedimentoAnalise = true;

        this.jLabelUltimaEdicao.setVisible(false);
        this.jTextFieldUltimaEdicao.setVisible(false);
        popularComboBoxIndicador();
        popularComboboxTipoDeGrafico();
        popularComboboxPeriodicidade();
        popularComboboxComunicacaoPeriodicidade();
        popularListaMeio();
        popularListaPerfis();
        this.setVisible(true);
        jTextFieldCadastradoPor.setText(Copia.getUsuarioLogado().getNome() + " " + Texto.formataData(new Date()));
    }

    public void showEditarProcedimentoAnaliseDialog(Procedimentodeanalise procedimentoAnalise_selecionado, String nomeUsuario_logado) {
        this.setTitle("Editar Procedimento de Análise");
        this.projeto_selecionado = Copia.getProjetoSelecionado();
        this.nomeUsuario_logado = nomeUsuario_logado;
        procedimentodeanalise = procedimentoAnalise_selecionado;

        ehNovoProcedimentoAnalise = false;

        popularComboBoxIndicador();
        popularComboboxTipoDeGrafico();
        popularComboboxPeriodicidade();
        popularComboboxComunicacaoPeriodicidade();
        popularListaMeio();
        popularListaPerfis();
        preencherCampos();
        this.setVisible(true);
        jTextFieldCadastradoPor.setText(Copia.getUsuarioLogado().getNome() + " " + Texto.formataData(new Date()));
    }

    private void escondeRadioButton() {
        if (!jRadioButtonBase.isSelected()) {
            jRadioButtonBase.setVisible(false);
        }

        if (!jRadioButtonDerivada.isSelected()) {
            jRadioButtonDerivada.setVisible(false);
        }
    }

    private void preencherCampos() {
        jComboBoxIndicador.setSelectedItem(procedimentodeanalise.getIndicadorid().toString());

        registro = new ArrayList<>();
        jTextFieldCadastradoPor.setText(registro.get(0).getNomeUsuario() + ". Em: " + Texto.formataData(registro.get(0).getData()));

        registro = new ArrayList<>();
        if (registro.isEmpty()) {
            this.jLabelUltimaEdicao.setVisible(false);
            this.jTextFieldUltimaEdicao.setVisible(false);
        } else {
            jTextFieldUltimaEdicao.setText(registro.get(registro.size() - 1).getNomeUsuario() + ". Em: " + Texto.formataData(registro.get(registro.size() - 1).getData()));
        }

        jTextFieldResponsavel.setText(procedimentodeanalise.getResponsavel());
        selecionarRadio();
        jComboBoxPeriodicidade.setSelectedItem(procedimentodeanalise.getPeriodicidade());
        jComboBoxTipoGrafico.setSelectedItem(procedimentodeanalise.getGraficoNome());
        jTextAreaObservacao.setText(procedimentodeanalise.getObservacao());
        jTextFieldMetaOk.setText(procedimentodeanalise.getMetaOk());
        jTextFieldMetaAlerta.setText(procedimentodeanalise.getMetaAlerta());
        jTextFieldMetaCritico.setText(procedimentodeanalise.getMetaCritico());
        jTextAreaCriterioOk.setText(procedimentodeanalise.getCriterioOk());
        jTextAreaCriterioAlerta.setText(procedimentodeanalise.getCriterioAlerta());
        jTextAreaCriterioCritico.setText(procedimentodeanalise.getCriterioCritico());
        jTextAreaAcoesOk.setText(procedimentodeanalise.getAcoesOk());
        jTextAreaAcoesAlerta.setText(procedimentodeanalise.getAcoesAlerta());
        jTextAreaAcoesCritico.setText(procedimentodeanalise.getAcoesCritico());
        jComboBoxComunicacaoPeriodicidade.setSelectedItem(procedimentodeanalise.getPeriodicidadeComunicacao());
        jTextFieldFrequencia.setText(procedimentodeanalise.getFrequencia());
    }

    private boolean validarCampos() {
        int cont = 0;
        String mensagem = null;

        if (jComboBoxIndicador.getSelectedItem() == "-Selecione um Indicador-") {
            mensagem = "Campo \"Indicador\" não pode ser vazio.";
            cont++;
        }
        if (jTextFieldResponsavel.getText().isEmpty()) {
            mensagem = "Campo \"Responsável pela análise\" não pode ser vazio.";
            cont++;
        }
        if (getComposicao() == null) {
            mensagem = "É necessário selecionar uma \"Composição\".";
            cont++;
        }
        if (jComboBoxPeriodicidade.getSelectedItem() == "-Selecione uma Periodicidade-") {
            mensagem = "É necessário selecionar uma \"Periodicidade\" no Combobox.";
            cont++;
        }
        if (jComboBoxTipoGrafico.getSelectedItem() == "-Selecione um Tipo de Gráfico-") {
            mensagem = "É necessário selecionar um \"Tipo de Gráfico\" no Combobox.";
            cont++;
        }
        if (jTextFieldMetaOk.getText().isEmpty()) {
            mensagem = "Campo \"Meta\" não pode ser vazio.";
            cont++;
        }
        if (jTextFieldMetaAlerta.getText().isEmpty()) {
            mensagem = "Campo \"Meta\" não pode ser vazio.";
            cont++;
        }
        if (jTextFieldMetaCritico.getText().isEmpty()) {
            mensagem = "Campo \"Meta\" não pode ser vazio.";
            cont++;
        }
        if (jTextAreaCriterioOk.getText().isEmpty()) {
            mensagem = "Campo \"Critério de Análise\" não pode ser vazio.";
            cont++;
        }
        if (jTextAreaCriterioAlerta.getText().isEmpty()) {
            mensagem = "Campo \"Critério de Análise\" não pode ser vazio.";
            cont++;
        }
        if (jTextAreaCriterioCritico.getText().isEmpty()) {
            mensagem = "Campo \"Critério de Análise\" não pode ser vazio.";
            cont++;
        }
        if (jTextAreaAcoesOk.getText().isEmpty()) {
            mensagem = "Campo \"Ações\" não pode ser vazio.";
            cont++;
        }
        if (jTextAreaAcoesAlerta.getText().isEmpty()) {
            mensagem = "Campo \"Ações\" não pode ser vazio.";
            cont++;
        }
        if (jTextAreaAcoesCritico.getText().isEmpty()) {
            mensagem = "Campo \"Ações\" não pode ser vazio.";
            cont++;
        }
        if (jComboBoxComunicacaoPeriodicidade.getSelectedItem() == "-Selecione uma Periodicidade-") {
            mensagem = "É necessário selecionar uma \"Periodicidade\" no Combobox.";
            cont++;
        }
        if (jTextFieldFrequencia.getText().isEmpty()) {
            mensagem = "Campo \"Frequência\" não pode ser vazio.";
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

    private void selecionarRadio() {
        switch (procedimentodeanalise.getGraficoNome()) {
            case "Base":
                jRadioButtonBase.setSelected(true);
                break;
            case "Derivada":
                jRadioButtonDerivada.setSelected(true);
                break;
        }
    }

    private void agruparBotoesRadio() {
        buttonGroupComposicao.add(jRadioButtonBase);
        buttonGroupComposicao.add(jRadioButtonDerivada);
    }

    private String getComposicao() {
        String composicao = null;
        if (jRadioButtonBase.isSelected()) {
            composicao = "Base";
        } else if (jRadioButtonDerivada.isSelected()) {
            composicao = "Derivada";
        }
        return composicao;
    }

    public void popularComboBoxIndicador() {

        comboboxModel = new DefaultComboBoxModel();
        comboboxModel.addElement("-Selecione um Indicador-");
        List<Indicador> indicador = jpa.getIndicadorJpa().findListaIndicadoresByProjeto(Copia.getProjetoSelecionado().getId());
        for (int i = 0; i < indicador.size(); i++) {
            if(indicador.get(i).getProcedimentodeanaliseList().isEmpty()){
                comboboxModel.addElement(indicador.get(i).getNome());
            }
        }
        jComboBoxIndicador.setModel(comboboxModel);
    }

    private void popularComboboxTipoDeGrafico() {
        comboboxModel = new DefaultComboBoxModel();

        comboboxModel.addElement("-Selecione um Tipo de Gráfico-");
        List<String> listaGraficos = Constantes.preencherListaGraficos();

        for (int i = 0; i < listaGraficos.size(); i++) {
            comboboxModel.addElement(listaGraficos.get(i));
        }
        jComboBoxTipoGrafico.setModel(comboboxModel);
    }

    private void popularComboboxPeriodicidade() {
        comboboxModel = new DefaultComboBoxModel();

        comboboxModel.addElement("-Selecione uma Periodicidade-");
        List<String> listaPeriodicidade = Constantes.preencherListaPeriodicidade();

        for (int i = 0; i < listaPeriodicidade.size(); i++) {
            comboboxModel.addElement(listaPeriodicidade.get(i));
        }
        jComboBoxPeriodicidade.setModel(comboboxModel);
    }

    private void popularComboboxComunicacaoPeriodicidade() {
        comboboxModel = new DefaultComboBoxModel();

        comboboxModel.addElement("-Selecione uma Periodicidade-");
        List<String> listaPeriodicidade = Constantes.preencherListaPeriodicidade();

        for (int i = 0; i < listaPeriodicidade.size(); i++) {
            comboboxModel.addElement(listaPeriodicidade.get(i));
        }
        jComboBoxComunicacaoPeriodicidade.setModel(comboboxModel);
    }

    private void popularListaPerfis() {
        model_listaDePerfis = new DefaultListModel();
        List<String> listaPerfis = Constantes.preencherListaPerfis();

        for (int i = 0; i < listaPerfis.size(); i++) {
            model_listaDePerfis.addElement(listaPerfis.get(i));
        }
        jListPerfis.setModel(model_listaDePerfis);
    }

    private void popularListaMeio() {
        model_listaMeio = new DefaultListModel();
        List<String> listaMeio = Constantes.preencherListaMeio();

        for (int i = 0; i < listaMeio.size(); i++) {
            model_listaMeio.addElement(listaMeio.get(i));
        }
        jListMeio.setModel(model_listaMeio);
    }
    
    public void jTextFieldSomenteNumeros(java.awt.event.KeyEvent evt) {
        String caracteres = "987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupComposicao = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelCadastradoPor1 = new javax.swing.JLabel();
        jTextFieldCadastradoPor = new javax.swing.JTextField();
        jTextFieldUltimaEdicao = new javax.swing.JTextField();
        jLabelUltimaEdicao = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldResponsavel = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jRadioButtonBase = new javax.swing.JRadioButton();
        jRadioButtonDerivada = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxPeriodicidade = new javax.swing.JComboBox();
        jComboBoxTipoGrafico = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldFrequencia1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaCriterioOk = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextAreaCriterioAlerta = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextAreaCriterioCritico = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldMetaOk = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldMetaAlerta = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldMetaCritico = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaAcoesOk = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaAcoesAlerta = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaAcoesCritico = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jListMeio = new javax.swing.JList();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jComboBoxComunicacaoPeriodicidade = new javax.swing.JComboBox();
        jTextFieldFrequencia = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jListPerfis = new javax.swing.JList();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableMedida = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldFormula = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jComboBoxIndicador = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Indicador:");

        jLabelCadastradoPor1.setText("Cadastrado por:");

        jTextFieldCadastradoPor.setEditable(false);
        jTextFieldCadastradoPor.setBackground(new java.awt.Color(204, 204, 204));

        jTextFieldUltimaEdicao.setEditable(false);
        jTextFieldUltimaEdicao.setBackground(new java.awt.Color(204, 204, 204));

        jLabelUltimaEdicao.setText("Ultima Edição:");

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

        jLabel2.setText("Responsável pela Análise:");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Composição:"));

        jRadioButtonBase.setText("Base");

        jRadioButtonDerivada.setText("Derivada");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonBase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonDerivada)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonBase)
                    .addComponent(jRadioButtonDerivada))
                .addContainerGap())
        );

        jLabel5.setText("Periodicidade:");

        jComboBoxPeriodicidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Selecione a periodicidade-", "Diaria", "Semanal", "Mensal ", "Anual" }));
        jComboBoxPeriodicidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPeriodicidadeActionPerformed(evt);
            }
        });

        jComboBoxTipoGrafico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Tipo de Gráfico:");

        jLabel12.setText("Observação:");

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setRows(2);
        jScrollPane1.setViewportView(jTextAreaObservacao);

        jLabel19.setText("Frequência:");

        jTextFieldFrequencia1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFrequencia1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldResponsavel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jComboBoxTipoGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 271, Short.MAX_VALUE))
                                    .addComponent(jTextFieldFrequencia1)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jComboBoxPeriodicidade, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxPeriodicidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldFrequencia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxTipoGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados Gerais", jPanel2);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Criterio de Análise"));

        jLabel11.setText("OK:");

        jLabel13.setText("Alerta:");

        jLabel14.setText("Crítico:");

        jTextAreaCriterioOk.setColumns(20);
        jTextAreaCriterioOk.setLineWrap(true);
        jTextAreaCriterioOk.setRows(2);
        jTextAreaCriterioOk.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextAreaCriterioOk);

        jTextAreaCriterioAlerta.setColumns(20);
        jTextAreaCriterioAlerta.setLineWrap(true);
        jTextAreaCriterioAlerta.setRows(2);
        jTextAreaCriterioAlerta.setWrapStyleWord(true);
        jScrollPane11.setViewportView(jTextAreaCriterioAlerta);

        jTextAreaCriterioCritico.setColumns(20);
        jTextAreaCriterioCritico.setLineWrap(true);
        jTextAreaCriterioCritico.setRows(2);
        jTextAreaCriterioCritico.setWrapStyleWord(true);
        jScrollPane12.setViewportView(jTextAreaCriterioCritico);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Meta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel8.setText("OK:");

        jLabel9.setText("Alerta:");

        jLabel10.setText("Crítico:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldMetaOk, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldMetaAlerta, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldMetaCritico)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldMetaOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldMetaAlerta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldMetaCritico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Meta e critério de análise", jPanel3);

        jLabel3.setText("OK:");

        jLabel4.setText("Alerta:");

        jLabel6.setText("Crítico:");

        jTextAreaAcoesOk.setColumns(20);
        jTextAreaAcoesOk.setRows(5);
        jScrollPane3.setViewportView(jTextAreaAcoesOk);

        jTextAreaAcoesAlerta.setColumns(20);
        jTextAreaAcoesAlerta.setRows(5);
        jScrollPane4.setViewportView(jTextAreaAcoesAlerta);

        jTextAreaAcoesCritico.setColumns(20);
        jTextAreaAcoesCritico.setRows(5);
        jScrollPane5.setViewportView(jTextAreaAcoesCritico);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ações", jPanel8);

        jLabel15.setText("Frequência:");

        jLabel16.setText("Meio:");

        jListMeio.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(jListMeio);

        jLabel17.setText("<html>Perfis<br>interessados:</html>");

        jLabel18.setText("Periodicidade:");

        jComboBoxComunicacaoPeriodicidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextFieldFrequencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFrequenciaKeyTyped(evt);
            }
        });

        jListPerfis.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(jListPerfis);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxComunicacaoPeriodicidade, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                            .addComponent(jScrollPane8)
                            .addComponent(jTextFieldFrequencia))
                        .addGap(12, 12, 12))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jComboBoxComunicacaoPeriodicidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldFrequencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Comunicação", jPanel9);

        jTableMedida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Medida", "Mnemônico"
            }
        ));
        jScrollPane7.setViewportView(jTableMedida);

        jLabel21.setText("Fómula:");

        jTextFieldFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFormulaActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton7.setText("+");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton2.setText(".");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("0");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton13.setText("1");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton15.setText("2");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("3");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton8.setText("-");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("*");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton19.setText("6");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton18.setText("5");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton17.setText("4");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton20.setText("7");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("8");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setText("9");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton10.setText("/");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText(")");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("(");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton14.setText("C");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton23.setText("<-");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
                            .addComponent(jButton20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1, Short.MAX_VALUE))))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton12)
                    .addComponent(jButton14)
                    .addComponent(jButton23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton20)
                    .addComponent(jButton21)
                    .addComponent(jButton22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton17)
                    .addComponent(jButton18)
                    .addComponent(jButton19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton13)
                    .addComponent(jButton15)
                    .addComponent(jButton16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldFormula)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextFieldFormula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 128, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Fórmula", jPanel10);

        jComboBoxIndicador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCadastradoPor1)
                            .addComponent(jLabelUltimaEdicao)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUltimaEdicao)
                            .addComponent(jTextFieldCadastradoPor)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBoxIndicador, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxIndicador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCadastradoPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCadastradoPor1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUltimaEdicao)
                    .addComponent(jTextFieldUltimaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (!validarCampos()) {
            return;
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jComboBoxPeriodicidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPeriodicidadeActionPerformed
        if (jComboBoxPeriodicidade.getSelectedIndex() == 0) {
            return;
        }

        Calendario calendario = new Calendario(null, true);
        if (jComboBoxPeriodicidade.getSelectedIndex() == 1) {
            calendario.showCalendarioDiarioDialog();
        } else if (jComboBoxPeriodicidade.getSelectedIndex() == 2) {
            calendario.showCalendarioSemanalDialog();
        } else if (jComboBoxPeriodicidade.getSelectedIndex() == 3) {
            calendario.showCalendarioOutrosPeriodosDialog("Mensal");
        } else if (jComboBoxPeriodicidade.getSelectedIndex() == 4) {
            calendario.showCalendarioOutrosPeriodosDialog("Bimestral");
        } else if (jComboBoxPeriodicidade.getSelectedIndex() == 5) {
            calendario.showCalendarioOutrosPeriodosDialog("Trimestral");
        } else if (jComboBoxPeriodicidade.getSelectedIndex() == 6) {
            calendario.showCalendarioOutrosPeriodosDialog("Semestral");
        } else if (jComboBoxPeriodicidade.getSelectedIndex() == 7) {
            calendario.showCalendarioOutrosPeriodosDialog("Anual");
        }
    }//GEN-LAST:event_jComboBoxPeriodicidadeActionPerformed

    //Botões da calculadora abaixo
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "+");

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + ".");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "0");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "1");

    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "2");

    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "3");

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "-");

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "*");

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "6");

    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "5");

    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "4");

    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "7");

    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "8");

    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "9");
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "/");

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + ")");

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        jTextFieldFormula.setText(jTextFieldFormula.getText() + "(");

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        jTextFieldFormula.setText("");
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        excluirUltimaLetra(jTextFieldFormula.getText());
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jTextFieldFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFormulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFormulaActionPerformed

    private void jTextFieldFrequencia1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFrequencia1KeyTyped
        jTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldFrequencia1KeyTyped

    private void jTextFieldFrequenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFrequenciaKeyTyped
        jTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldFrequenciaKeyTyped

    public String excluirUltimaLetra(String texto) {
        if (!texto.isEmpty()) {
            int length = texto.length();
            texto = texto.substring(0, length - 1);
            jTextFieldFormula.setText(texto);
            return texto;
        }
        return null;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupComposicao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox jComboBoxComunicacaoPeriodicidade;
    private javax.swing.JComboBox jComboBoxIndicador;
    private javax.swing.JComboBox jComboBoxPeriodicidade;
    private javax.swing.JComboBox jComboBoxTipoGrafico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCadastradoPor1;
    private javax.swing.JLabel jLabelUltimaEdicao;
    private javax.swing.JList jListMeio;
    private javax.swing.JList jListPerfis;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButtonBase;
    private javax.swing.JRadioButton jRadioButtonDerivada;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableMedida;
    private javax.swing.JTextArea jTextAreaAcoesAlerta;
    private javax.swing.JTextArea jTextAreaAcoesCritico;
    private javax.swing.JTextArea jTextAreaAcoesOk;
    private javax.swing.JTextArea jTextAreaCriterioAlerta;
    private javax.swing.JTextArea jTextAreaCriterioCritico;
    private javax.swing.JTextArea jTextAreaCriterioOk;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldCadastradoPor;
    private javax.swing.JTextField jTextFieldFormula;
    private javax.swing.JTextField jTextFieldFrequencia;
    private javax.swing.JTextField jTextFieldFrequencia1;
    private javax.swing.JTextField jTextFieldMetaAlerta;
    private javax.swing.JTextField jTextFieldMetaCritico;
    private javax.swing.JTextField jTextFieldMetaOk;
    private javax.swing.JTextField jTextFieldResponsavel;
    private javax.swing.JTextField jTextFieldUltimaEdicao;
    // End of variables declaration//GEN-END:variables

}
