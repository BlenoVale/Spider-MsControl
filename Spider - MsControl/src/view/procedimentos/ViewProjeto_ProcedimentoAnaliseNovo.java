package view.procedimentos;

import controller.CtrlIndicador;
import controller.CtrlProcedimentoDeAnalise;
import facade.FacadeJpa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.Indicador;
import model.Medida;
import model.Meioscomunicacao;
import model.Meiosprocedimentoanalise;
import model.Perfilinteressado;
import model.Perfisinteressadosprocedimentoanalise;
import model.Procedimentodeanalise;
import model.Projeto;
import model.Registroprocedimentoanalise;
import util.CheckDefaultTableModel;
import util.Constantes;
import util.Copia;
import util.MyDefaultTableModel;
import util.StringUtils;
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
    private CheckDefaultTableModel checkModel;
    private List<Medida> listMedida;
    private List<Medida> listMedidaRelacionada;
    private DefaultComboBoxModel comboBoxModelMedidaRelacionada;

    String[] numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] sinals = new String[]{"/", "+", "*", "-", ".", "("};
    String[] sinalsOperador = new String[]{"/", "+", "*", "-", ".", "("};
    String[] sinaisParenteseAberto = new String[]{"/", "+", "*", "-", "("};
    String[] sinalsMnemonico = new String[]{"/", "+", "*", "-", ".", "("};

    ArrayList<String> mnemonico = new ArrayList<>();
    ArrayList<String> insercaoFormula = new ArrayList<>();

    private boolean ehNovoProcedimentoAnalise;

    private int idProjeto = Copia.getProjetoSelecionado().getId();
    private String nomeUsuario = Copia.getUsuarioLogado().getNome();

    FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public ViewProjeto_ProcedimentoAnaliseNovo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        agruparBotoesRadio();
        this.setLocationRelativeTo(null);
        popularComboBoxMedidaRelacionada();
        ocultarMedidaRelacionada();
        iniciarTabela();
        recarregarTabela();
        this.pack();

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

    public void verifyButtonCadastrarMeioComunicacao() {

        if (jTabbedPane1.getSelectedIndex() == 4) {
            jButtonCadastrarMeioComunicacao.setVisible(true);
        } else {
            jButtonCadastrarMeioComunicacao.setVisible(false);
        }
    }

    public void ShowEditarDialogProcedimentoAnalise(Procedimentodeanalise procedimentodeanaliseUsuario) {

        ehNovoProcedimentoAnalise = false;
        procedimentodeanalise = procedimentodeanaliseUsuario;

        setTitle("Editar Procedimento de Análise");

        jTextAreaAcoesAlerta.setText(procedimentodeanaliseUsuario.getAcoesAlerta());
        jTextAreaAcoesCritico.setText(procedimentodeanaliseUsuario.getAcoesCritico());
        jTextAreaAcoesOk.setText(procedimentodeanaliseUsuario.getAcoesOk());

        jTextAreaCriterioAlerta.setText(procedimentodeanaliseUsuario.getCriterioAlerta());
        jTextAreaCriterioCritico.setText(procedimentodeanaliseUsuario.getCriterioCritico());
        jTextAreaCriterioOk.setText(procedimentodeanaliseUsuario.getCriterioOk());

//        jTextFieldMetaAlerta.setText(String.valueOf(procedimentodeanaliseUsuario.getMetaAlerta()));
        jTextFieldMetaCritico.setText(String.valueOf(procedimentodeanaliseUsuario.getMetaCritico()));
        jTextFieldMetaOk.setText(String.valueOf(procedimentodeanaliseUsuario.getMetaOk()));

        jTextFieldResponsavel.setText(procedimentodeanaliseUsuario.getResponsavel());
        jTextFieldFrequencia1.setText(procedimentodeanaliseUsuario.getFrequencia());
        dateField.setValue(procedimentodeanaliseUsuario.getDataComunicacao());
        jTextAreaObservacao.setText(procedimentodeanaliseUsuario.getObservacao());

        jTextFieldCadastradoPor.setText(Copia.getUsuarioLogado().getNome() + " " + Texto.formataData(new Date()));

        editarRadioComposicao(procedimentodeanaliseUsuario);
        editarFormula(procedimentodeanaliseUsuario);
        editarGrafico(procedimentodeanaliseUsuario);
        editarPeriodicidade(procedimentodeanaliseUsuario);
        editarIndicador(procedimentodeanaliseUsuario);
        popularUltimaEdicao(procedimentodeanaliseUsuario);

        popularListaMeio();
        popularListaPerfis();
        editarCheckJTableMeios(procedimentodeanalise);
        editarCheckJTablePerfilInteressados(procedimentodeanalise);
    }

    public void editarRadioComposicao(Procedimentodeanalise procedimentodeanalise) {
        if (procedimentodeanalise.getComposicao().equals("Base")) {
            jRadioButtonBase.setSelected(true);
            jComboBoxMedidaRelacionada.setVisible(true);
            jTextFieldFormula.setText(procedimentodeanalise.getFormula());
            bloquearAbaFormula();
        } else {
            jRadioButtonDerivada.setSelected(true);
        }
    }

    public void editarFormula(Procedimentodeanalise procedimentodeanalise) {
        if (jRadioButtonBase.isSelected()) {
            Medida medida = facadeJpa.getMedidaJpa().findByMnemonicoAndProjeto(procedimentodeanalise.getFormula(), Copia.getProjetoSelecionado().getId());
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
            comboBoxModel.addElement(medida.getNome());
            listMedidaRelacionada = facadeJpa.getMedidaJpa().findByProjeto(Copia.getProjetoSelecionado().getId());
            for (int i = 0; i < listMedidaRelacionada.size(); i++) {
                if (!medida.getNome().equals(listMedidaRelacionada.get(i).getNome())) {
                    comboBoxModel.addElement(listMedidaRelacionada.get(i).getNome());
                }
            }
            jComboBoxMedidaRelacionada.setModel(comboBoxModel);
        } else {
            jTextFieldFormula.setText(procedimentodeanalise.getFormula());
        }
    }

    public void editarGrafico(Procedimentodeanalise procedimentodeanalise) {

        comboboxModel = new DefaultComboBoxModel();
        comboboxModel.addElement(procedimentodeanalise.getGraficoNome());

        List<String> list = Constantes.preencherListaGraficos();

        for (int i = 0; i < list.size(); i++) {
            if (!procedimentodeanalise.getGraficoNome().equals(list.get(i))) {
                comboboxModel.addElement(list.get(i));
            }
        }
        jComboBoxTipoGrafico.setModel(comboboxModel);

    }

    public void editarPeriodicidade(Procedimentodeanalise procedimentodeanalise) {
        comboboxModel = new DefaultComboBoxModel();
        comboboxModel.addElement(procedimentodeanalise.getPeriodicidade());

        List<String> list = Constantes.preencherListaPeriodicidade();
        for (int i = 0; i < list.size(); i++) {
            if (!procedimentodeanalise.getPeriodicidade().equals(list.get(i))) {
                comboboxModel.addElement(list.get(i));
            }

        }
        jComboBoxPeriodicidade.setModel(comboboxModel);

    }

    public void editarIndicador(Procedimentodeanalise procedimentodeanalise) {
        comboboxModel = new DefaultComboBoxModel();
        Indicador indicador = facadeJpa.getIndicadorJpa().findIndicador(procedimentodeanalise.getIndicadorid().getId());
        comboboxModel.addElement(indicador.getNome());

        List<Indicador> indicadors = jpa.getIndicadorJpa().findListaIndicadoresByProjeto(Copia.getProjetoSelecionado().getId());
        for (int i = 0; i < indicadors.size(); i++) {
            if (!indicador.getNome().equals(indicadors.get(i).getNome()) && indicadors.get(i).getProcedimentodeanaliseList().isEmpty()) {
                comboboxModel.addElement(indicadors.get(i).getNome());
            }
        }
        jComboBoxIndicador.setModel(comboboxModel);
        jComboBoxIndicador.setEnabled(false);
    }

    public void popularUltimaEdicao(Procedimentodeanalise procedimentodeanalise) {
        List<Registroprocedimentoanalise> registroprocedimentoanalises = jpa.getRegistroProcedimentoAnalise().findAllRegistros(procedimentodeanalise.getId(), Constantes.EDICAO);
        if (registroprocedimentoanalises.isEmpty()) {
            jTextFieldUltimaEdicao.setVisible(false);
            jLabelUltimaEdicao.setVisible(false);
        } else {
            jTextFieldUltimaEdicao.setText(registroprocedimentoanalises.get(registroprocedimentoanalises.size() - 1).getNomeUsuario() + " EM " + Texto.formataData(registroprocedimentoanalises.get(registroprocedimentoanalises.size() - 1).getData()));

        }
    }

    public List<String> listMeioComunicacaoIsCheked() {
        int TAM = jTableMeios.getRowCount();
        List<String> listMeiosComunicacao = new ArrayList<>();

        for (int i = 0; i < TAM; i++) {

            if ((boolean) jTableMeios.getModel().getValueAt(i, 0) == true) {
                listMeiosComunicacao.add(jTableMeios.getModel().getValueAt(i, 1).toString());
            }
        }

        return listMeiosComunicacao;

    }

    public List<String> listPerfilInteressadoIsCheked() {
        int TAM = jTablePerfisInteressados.getRowCount();
        List<String> listMeiosComunicacao = new ArrayList<>();

        for (int i = 0; i < TAM; i++) {

            if ((boolean) jTablePerfisInteressados.getModel().getValueAt(i, 0) == true) {
                listMeiosComunicacao.add(jTablePerfisInteressados.getModel().getValueAt(i, 1).toString());
            }
        }

        return listMeiosComunicacao;

    }

    public void editarCheckJTableMeios(Procedimentodeanalise procedimentodeanalise) {

        List<String> listMeiosComunicacao = new ArrayList<>();
        List<Meiosprocedimentoanalise> meiosprocedimentoanalises = facadeJpa.getMeioComunicacaoProcedimentoAnaliseJpa().findByProcedimentoAnalise(procedimentodeanalise.getId());
        int TAM = jTableMeios.getRowCount();
        int size = meiosprocedimentoanalises.size();

        for (int i = 0; i < size; i++) {
            Meioscomunicacao meioscomunicacao = facadeJpa.getMeioComunicacaoJpa().findById(meiosprocedimentoanalises.get(i).getIdmeioComunicacao());
            for (int j = 0; j < TAM; j++) {
                if (jTableMeios.getModel().getValueAt(j, 1).equals(meioscomunicacao.getNome())) {
                    jTableMeios.getModel().setValueAt(true, j, 0);
                }
            }
        }

    }

    public void editarCheckJTablePerfilInteressados(Procedimentodeanalise procedimentodeanalise) {

        List<String> listMeiosComunicacao = new ArrayList<>();
        List<Perfisinteressadosprocedimentoanalise> perfilinteressados = facadeJpa.getPerfislInteressadoProcedimentoAnaliseJpa().findByIdProcedimentoAnalise(procedimentodeanalise.getId());
        int TAM = jTablePerfisInteressados.getRowCount();
        int size = perfilinteressados.size();

        for (int i = 0; i < size; i++) {

            Perfilinteressado perfilinteressado = facadeJpa.getPerfilInteressadoJpa().findPerfilinteressado(perfilinteressados.get(i).getIdperfilInteressado());
            for (int j = 0; j < TAM; j++) {
                if (jTablePerfisInteressados.getModel().getValueAt(j, 1).equals(perfilinteressado.getNome())) {
                    jTablePerfisInteressados.getModel().setValueAt(true, j, 0);
                }
            }
        }

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
        jTextFieldCadastradoPor.setText(Copia.getUsuarioLogado().getNome() + " " + Texto.formataData(new Date()));
        this.setVisible(true);
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

    public void pegaSelecionadoTableMeios() {
        for (int i = 0; i < jTableMeios.getRowCount(); i++) {
            jTableMeios.getValueAt(i, 0);

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
        jTextFieldMetaOk.setText(String.valueOf(procedimentodeanalise.getMetaOk()));
//        jTextFieldMetaAlerta.setText(String.valueOf(procedimentodeanalise.getMetaAlerta()));
        jTextFieldMetaCritico.setText(String.valueOf(procedimentodeanalise.getMetaCritico()));
        jTextAreaCriterioOk.setText(procedimentodeanalise.getCriterioOk());
        jTextAreaCriterioAlerta.setText(procedimentodeanalise.getCriterioAlerta());
        jTextAreaCriterioCritico.setText(procedimentodeanalise.getCriterioCritico());
        jTextAreaAcoesOk.setText(procedimentodeanalise.getAcoesOk());
        jTextAreaAcoesAlerta.setText(procedimentodeanalise.getAcoesAlerta());
        jTextAreaAcoesCritico.setText(procedimentodeanalise.getAcoesCritico());

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

        if (jRadioButtonBase.isSelected()) {
            if (jComboBoxMedidaRelacionada.getSelectedItem().equals("--Selecione uma Medida--")) {
                mensagem = "É necessário selecionar uma \"Medida Relacionada\".";
                cont++;
            }
        }
        if (jTextFieldFrequencia1.getText().isEmpty()) {
            mensagem = "Campo \"Frequência\" não pode ser vazio.";
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
//        if (jTextFieldMetaAlerta.getText().isEmpty()) {
//            mensagem = "Campo \"Meta\" não pode ser vazio.";
//            cont++;
//        }
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
        if (jRadioButtonDerivada.isSelected()) {
            if (jTextFieldFormula.getText().isEmpty()) {
                mensagem = "Campo \"Fórmula\" não pode ser vazio.";
                cont++;
            }

            if (verificaUltimaLetraDupla() == false) {
                mensagem = "Campo \"Formula\" incorreto, sua fórmula não deve ser finalizada \ncom final \"0.\", \"01\", \"02\", \"03\", \"04\", \"05\", \"06\", \"07\",\"08\", \"09\" ";
                cont++;
            }

            if (verificaUltimaLetraIndividual() == false) {
                mensagem = "Campo \"Formula\" incorreto, sua fórmula não pode ser finalizada \ncom esses caracteres \"0\", \"/\", \"-\", \"*\", \"+\", \"(\", \".\" ";
                cont++;
            }
            if (!campoUnico()) {
                mensagem = "Campo \"Formula\" incorreto, verifique sua fórmula de campo único";
                cont++;
            }

            if (!validaCountParenteses()) {
                mensagem = "Campo \"Formula\" incorreto, verifique ausência de um parêntese de fechamento.";
                cont++;
            }

            if (isZeroEntreSinais()) {
                mensagem = "Campo \"Formula\" incorreto, verifique zero entre sinais.";
                cont++;
            }

        }
        if (!validaDataComunicacao()) {
            mensagem = "A \"Data de Comunicação\" deve ser maior que a data atual.";
            cont++;
        }

        if (listMeioComunicacaoIsCheked().isEmpty()) {
            mensagem = "Campo \"Meios de Comunicação\" deve ser marcado pelo menos um";
            cont++;
        }

        if (listPerfilInteressadoIsCheked().isEmpty()) {
            mensagem = "Campo \"Perfil Interessado\" deve ser marcado pelo menos um";
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

    public boolean validaDataComunicacao() {
        Date dateCalendario = (Date) dateField.getValue();

        if (dateCalendario.before(new Date()) && dateCalendario.getDay() != new Date().getDate()) {
            return false;
        }
        return true;
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

    public void ocultarMedidaRelacionada() {
        jButtonCadastrarMeioComunicacao.setVisible(false);
        jTextFieldFormula.setEditable(false);
        jLabelMedidaRelacionada.setVisible(false);
        jComboBoxMedidaRelacionada.setVisible(false);
    }

    public void habilitarMedidaRelacionada() {

        if (jRadioButtonBase.isSelected()) {
            jLabelMedidaRelacionada.setVisible(true);
            jComboBoxMedidaRelacionada.setVisible(true);
        } else {
            ocultarMedidaRelacionada();
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
        List<Indicador> indicador = jpa.getIndicadorJpa().findListaIndicadoresByProjetoAndAprovacao(Copia.getProjetoSelecionado().getId());
        for (int i = 0; i < indicador.size(); i++) {
            if (indicador.get(i).getProcedimentodeanaliseList().isEmpty()) {
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

    }

    private void popularListaPerfis() {
        model_listaDePerfis = new DefaultListModel();
        List<Perfilinteressado> perfilinteressados = jpa.getPerfilInteressadoJpa().findAll();

        checkModel = new CheckDefaultTableModel(new String[]{"selecionar", "Perfis interessados"}, 0, false);

        for (int i = 0; i < perfilinteressados.size(); i++) {
            Object[] linha = {
                false,
                perfilinteressados.get(i).getNome()
            };
            checkModel.addRow(linha);
        }
        jTablePerfisInteressados.setModel(checkModel);
        jTablePerfisInteressados.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTablePerfisInteressados.getColumnModel().getColumn(1).setPreferredWidth(500);
    }

    private void popularListaMeio() {
        model_listaMeio = new DefaultListModel();
        List<Meioscomunicacao> meioscomunicacaos = jpa.getMeioComunicacaoJpa().findAll();
        checkModel = new CheckDefaultTableModel(new String[]{"selecionar", "Meios"}, 0, false);

        for (int i = 0; i < meioscomunicacaos.size(); i++) {
            Object[] linha = {
                false,
                meioscomunicacaos.get(i).getNome()
            };
            checkModel.addRow(linha);
        }
        jTableMeios.setModel(checkModel);
        jTableMeios.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableMeios.getColumnModel().getColumn(1).setPreferredWidth(500);
    }

    public void popularComboBoxMedidaRelacionada() {
        comboBoxModelMedidaRelacionada = new DefaultComboBoxModel();
        comboBoxModelMedidaRelacionada.addElement("--Selecione uma Medida--");
        listMedidaRelacionada = jpa.getMedidaJpa().findByProjeto(Copia.getProjetoSelecionado().getId());
        for (int i = 0; i < listMedidaRelacionada.size(); i++) {
            comboBoxModelMedidaRelacionada.addElement(listMedidaRelacionada.get(i).getNome());
        }
        jComboBoxMedidaRelacionada.setModel(comboBoxModelMedidaRelacionada);

    }

    public void bloquearAbaFormula() {
        if (jRadioButtonBase.isSelected() == true) {
            jTabbedPane1.setEnabledAt(3, false);
            jTextFieldFormula.setText("");
        } else {
            jTabbedPane1.setEnabledAt(3, true);
        }

        habilitarMedidaRelacionada();
        this.pack();
    }

    public String nomeRadioSelecionado() {
        if (jRadioButtonBase.isSelected()) {
            return "Base";
        } else {
            return "Derivada";
        }
    }

    public String selectFormula() {
        if (jRadioButtonBase.isSelected()) {
            Medida medida = facadeJpa.getMedidaJpa().findByNomeAndProjeto(jComboBoxMedidaRelacionada.getSelectedItem().toString(), Copia.getProjetoSelecionado().getId());
            return medida.getMnemonico().toString();
        } else {
            return jTextFieldFormula.getText();
        }
    }

    public void jTextFieldSomenteNumeros(java.awt.event.KeyEvent evt) {
        String caracteres = "9876543210";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }

    public void inserirMnemonico(java.awt.event.MouseEvent event) {
        if (event.getClickCount() >= 2) {
            String mnemonico = jTableMedida.getValueAt(jTableMedida.getSelectedRow(), 1).toString();
            jTextFieldFormula.setText(jTextFieldFormula.getText() + mnemonico + " ");
        }
    }

    private String removerEspacoEmBranco() {

        String formula = jTextFieldFormula.getText().toString();
        boolean isNumber = false;
        boolean espacoAntes = false;
        String charA = null;
        String charB = null;

        if (formula.isEmpty() == false) {

            charA = String.valueOf(formula.charAt(formula.length() - 1));
            if (formula.length() >= 2) {
                charB = String.valueOf(formula.charAt(formula.length() - 2));
            } else {
                charB = "";
            }

            for (int i = 0; i < numbers.length; i++) {
                if (charA.equals(numbers[i]) || charB.equals(numbers[i])) {
                    isNumber = true;
                }

            }
        }
        if (isNumber) {
            return formula.trim();
        }

        return formula;
    }

    private String adicionarEspacoEmBrancoAntesNumero() {

        String formula = jTextFieldFormula.getText().toString();
        boolean isNumber = false;
        boolean espacoAntes = false;
        String charA = null;
        String charB = null;

        if (formula.isEmpty() == false) {

            charA = String.valueOf(formula.charAt(formula.length() - 1));
            if (formula.length() >= 2) {
                charB = String.valueOf(formula.charAt(formula.length() - 2));
            } else {
                charB = "";
            }

            for (int i = 0; i < numbers.length; i++) {
                if (charA.equals(numbers[i]) || charB.equals(numbers[i])) {
                    isNumber = true;
                }

                if (!charA.equals(numbers[i]) && !charA.equals(" ")) {
                    espacoAntes = true;
                }
            }
        }

        if (espacoAntes = true) {
            return formula.trim() + " ";
        }
        return formula;
    }

    private boolean isSinalUltimo() {

        String formula = jTextFieldFormula.getText().toString();
        boolean isSinal = false;
        String charA = null;
        String charB = null;

        if (formula.isEmpty() == false) {

            charA = String.valueOf(formula.charAt(formula.length() - 1));
            if (formula.length() >= 2) {
                charB = String.valueOf(formula.charAt(formula.length() - 2));
            } else {
                charB = "";
            }

            for (int i = 0; i < sinals.length; i++) {
                if (charA.equals(sinals[i]) || charB.equals(sinals[i])) {
                    isSinal = true;
                }
            }
        }
        if (isSinal) {
            return true;
        }
        return false;
    }

    private boolean isSinalUltimoOperador() {

        String formula = jTextFieldFormula.getText().toString();
        boolean isSinal = false;
        String charA = null;
        String charB = null;

        if (formula.isEmpty() == false) {

            charA = String.valueOf(formula.charAt(formula.length() - 1));
            if (formula.length() >= 2) {
                charB = String.valueOf(formula.charAt(formula.length() - 2));
            } else {
                charB = "";
            }

            for (int i = 0; i < sinalsOperador.length; i++) {
                if (charA.equals(sinalsOperador[i]) || charB.equals(sinalsOperador[i])) {
                    isSinal = true;
                }
            }
        }
        if (isSinal) {
            return true;
        }
        return false;
    }

    private boolean isSinalUltimoByMnemonico() {

        String formula = jTextFieldFormula.getText();
        boolean isSinal = false;
        String charA = null;
        String charB = null;

        if (!formula.isEmpty()) {

            charA = String.valueOf(formula.charAt(formula.length() - 1));
            charB = String.valueOf(formula.charAt(formula.length() - 2));

            for (int i = 0; i < sinalsMnemonico.length; i++) {
                if (charA.equals(sinalsMnemonico[i]) || charB.equals(sinalsMnemonico[i])) {
                    isSinal = true;
                }
            }
        }
        if (isSinal) {
            return true;
        }
        return false;
    }

    private void preencherArrayMnemonico() {
        List<Medida> medidas = jpa.getMedidaJpa().findByProjeto(idProjeto);
        for (int i = 0; i < medidas.size(); i++) {
            mnemonico.add(medidas.get(i).getMnemonico());
        }
    }

    private boolean isSinalUltimoParenteAberto() {

        String formula = jTextFieldFormula.getText().toString();
        boolean isSinal = false;
        String charA = null;
        String charB = null;

        if (formula.isEmpty() == false) {

            charA = String.valueOf(formula.charAt(formula.length() - 1));
            charB = String.valueOf(formula.charAt(formula.length() - 2));

            for (int i = 0; i < sinaisParenteseAberto.length; i++) {
                if (charA.equals(sinaisParenteseAberto[i]) || charB.equals(sinaisParenteseAberto[i]) || charA.equals("(") || charB.equals("(")) {
                    isSinal = true;
                }
            }
        }
        if (isSinal) {
            return true;
        }
        return false;
    }

    public boolean verificaUltimaLetraDupla() {
        String formula = jTextFieldFormula.getText().trim();
        String letraA = null;
        String letraB = null;

        String[] naopode = new String[]{"0.", "01", "02", "03", "04", "05", "06", "07", "08", "09"};

        if (!formula.isEmpty()) {
            letraA = String.valueOf(formula.charAt(formula.length() - 1));
            if (formula.length() >= 2) {
                letraB = String.valueOf(formula.charAt(formula.length() - 2));
            } else {
                letraB = "";
            }

            String letras = letraB + letraA;

            for (int i = 0; i < naopode.length; i++) {
                if (letras.equals(naopode[i])) {
                    return false;
                }
            }
        }

        return true;

    }

    public boolean verificaUltimaLetraIndividual() {
        String formula = jTextFieldFormula.getText().trim();

        String[] naopode = new String[]{"/", "-", "*", "+", "(", "."};

        if (!formula.isEmpty()) {
            String letraA = String.valueOf(formula.charAt(formula.length() - 1));
            
            for (int i = 0; i < naopode.length; i++) {
                if (letraA.equals(naopode[i])) {
                    return false;
                }
            }
        }

        return true;

    }

    private boolean verificaInsercaoParenteseFechado() {

        String formula = jTextFieldFormula.getText().toString();
        int contA = 0;
        int contB = 0;
        String parenteseAberto = "(";
        String parenteseFechado = ")";
        if (!formula.isEmpty()) {
            for (int i = 0; i < formula.length(); i++) {
                String letra = String.valueOf(formula.charAt(i));
                if (letra.equals(parenteseAberto)) {
                    contA++;
                } else if (letra.equals(parenteseFechado)) {
                    contB++;
                }
            }
        }

        if (contA > contB) {
            return true;
        }
        return false;

    }

    private boolean validaCountParenteses() {

        String formula = jTextFieldFormula.getText().trim();
        int contA = 0;
        int contB = 0;
        String parenteseAberto = "(";
        String parenteseFechado = ")";
        if (!formula.isEmpty()) {
            for (int i = 0; i < formula.length(); i++) {
                String letra = String.valueOf(formula.charAt(i));
                if (letra.equals(parenteseAberto)) {
                    contA++;
                } else if (letra.equals(parenteseFechado)) {
                    contB++;
                }
            }
        }

        if (contA == contB) {
            return true;
        }
        return false;

    }
    //@TODO teste para adicionar nova virgula

    private boolean verificaInsercaoVirgulaTeste() {

        String formula = jTextFieldFormula.getText().trim();
        int size = formula.length();

        String virgula = ".";
        String letra = null;
        String substring = null;

        int fim = size;
        int inicio = 0;

        if (!formula.isEmpty()) {

            for (int i = size - 1; i >= 0; i--) {
                letra = String.valueOf(formula.charAt(i));

                if (letra.trim().equals("")) {
                    inicio = i;
                    break;
                }

            }

            substring = formula.substring(inicio, formula.length());

            for (int i = 0; i < substring.length(); i++) {
                if (String.valueOf(substring.charAt(i)).equals(virgula)) {
                    return false;
                }
            }

        }
        return true;

    }

    private boolean isMnemonico() {

        String formula = jTextFieldFormula.getText().trim();
        int size = formula.length();

        String letra = null;
        String substring = null;
        int inicio = 0;

        if (!formula.isEmpty()) {

            for (int i = size - 1; i >= 0; i--) {
                letra = String.valueOf(formula.charAt(i));

                if (letra.trim().equals("")) {
                    inicio = i;
                    break;
                }

            }

            substring = formula.substring(inicio, formula.length());

            for (int i = 0; i < substring.length(); i++) {
                if (Character.isLetter(substring.charAt(i))) {
                    return true;
                }
            }

        }
        return false;

    }

    private boolean campoUnico() {
        String formula = jTextFieldFormula.getText().trim();

        if (!formula.isEmpty()) {

            for (int i = 0; i < formula.length(); i++) {
                for (int j = 0; j < sinalsOperador.length; j++) {
                    if (String.valueOf(formula.charAt(i)).equals(sinalsOperador[j])) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

    private boolean isZeroEntreSinais() {
        String formula = jTextFieldFormula.getText().trim();

        if (!formula.isEmpty()) {
            if (formula.contains(" 0 ")) {
                return true;
            }
        }
        return false;
    }

    private void excluirUltimaLetra() {

        String formula = jTextFieldFormula.getText().toString();
        if (!formula.isEmpty()) {

            int length = formula.length();
            if (String.valueOf(formula.charAt(length - 1)).equals(" ")) {
                formula = formula.substring(0, length - 2);
            } else {
                formula = formula.substring(0, length - 1);
            }
            jTextFieldFormula.setText(formula);

        }

    }

    private void excluirUltimaLetraTeste() {

        String formula = jTextFieldFormula.getText().trim();
        jTextFieldFormula.setText(" " + formula);
        String partFormula = null;
        int fim = 0;
        boolean isLetter = false;
        if (!formula.isEmpty()) {

            int size = formula.length();

            for (int i = size - 1; i >= 0; i--) {

                boolean equals = StringUtils.isNullOrBlank(String.valueOf(formula.charAt(i)));

                if (equals) {

                    fim = i;
                    break;
                }
            }

            partFormula = formula.substring(fim, size);
            size = formula.substring(fim, size).length();

            for (int i = 0; i < size; i++) {

                isLetter = Character.isLetter(partFormula.charAt(i));

                if (isLetter) {
                    formula = formula.substring(0, fim);
                    break;
                }
            }

            if (!isLetter) {
                formula = formula.substring(0, formula.length() - 1);
            }

            jTextFieldFormula.setText(" " + formula + " ");

        }
    }

    public boolean verificaPontoDepoisParenteses(String formula) {
        String caractere = "";
        if (!formula.isEmpty()) {
            caractere = String.valueOf(formula.charAt(formula.length() - 1));
        }
        if (caractere.equals(".")) {
            return true;
        }

        return false;
    }

    public boolean verificaInsercaoParenteseFechado(String formula) {

        int contA = 0;
        int contB = 0;
        char caractereA = '(';
        char caractereB = ')';
        if (!formula.isEmpty()) {
            for (int i = 0; i < formula.length(); i++) {
                if (formula.charAt(i) == caractereA) {
                    contA++;
                } else if (formula.charAt(i) == caractereB) {
                    contB++;
                }
            }
        }

        if (contB >= contA) {
            return true;
        }
        return false;

    }

    public boolean verificaSomenteOperandosParenteseFechado(String formula) {
        String caractere = "";
        if (!formula.isEmpty()) {
            caractere = String.valueOf(formula.charAt(formula.length() - 1));
        }
        if (caractere.equals("*") || caractere.equals("-") || caractere.equals("/") || caractere.equals("+") || caractere.equals("")) {
            return true;
        }

        return false;
    }

    public boolean isParenteseFechadoUltimo() {

        String formula = jTextFieldFormula.getText().toString();
        String caractereA = "";
        String caractereB = "";
        if (!formula.isEmpty()) {
            caractereA = String.valueOf(formula.charAt(formula.length() - 1));
            //@TODO VERIFICAR ERRO DA VIRGULA
            if (formula.length() >= 2) {
                caractereB = String.valueOf(formula.charAt(formula.length() - 2));
            } else {
                caractereB = "";
            }
        }
        if (caractereA.equals(")") || caractereB.equals(")")) {
            return true;
        }
        return false;
    }

    private boolean validacaoUsoVirgula() {
        return !isParenteseFechadoUltimo() && !isSinalUltimo() && !jTextFieldFormula.getText().isEmpty() && verificaInsercaoVirgulaTeste();
    }

    public boolean permitirSinaisAposParenteseFechado(String formula) {
        String caractere = "";
        if (!formula.isEmpty()) {
            caractere = String.valueOf(formula.charAt(formula.length() - 1));
        }
        if (caractere.equals(")")) {
            return true;
        }
        return false;

    }

    public boolean verificaPontoAntesParenteses(String formula) {
        String caractere = "";
        if (!formula.isEmpty()) {
            caractere = String.valueOf(formula.charAt(formula.length() - 1));
        }
        if (caractere.equals(")") || caractere.equals("(")) {
            return true;
        }

        return false;
    }

    public boolean verificaOperandos(String texto) {
        int length = texto.length();

        if (texto.charAt(length - 1) == '('
                || texto.charAt(length - 1) == ')'
                || texto.charAt(length - 1) == '.'
                || texto.charAt(length - 1) == '+'
                || texto.charAt(length - 1) == '-'
                || texto.charAt(length - 1) == '*'
                || texto.charAt(length - 1) == '/'
                || texto.charAt(length - 1) == '/'
                || texto.charAt(length - 1) == '0'
                || texto.charAt(length - 1) == '1'
                || texto.charAt(length - 1) == '2'
                || texto.charAt(length - 1) == '3'
                || texto.charAt(length - 1) == '4'
                || texto.charAt(length - 1) == '5'
                || texto.charAt(length - 1) == '6'
                || texto.charAt(length - 1) == '7'
                || texto.charAt(length - 1) == '8'
                || texto.charAt(length - 1) == '9') {
            return true;
        }
        return false;

    }

    private void acessaCalendario(JComboBox combobox, String tipo) {
        Calendario calendario = new Calendario(null, true);
        if (combobox.getSelectedIndex() == 1) {
            calendario.showCalendarioDiarioDialog(tipo);
        } else if (combobox.getSelectedIndex() == 2) {
            calendario.showCalendarioSemanalDialog(tipo);
        } else if (combobox.getSelectedIndex() == 3) {
            calendario.showCalendarioOutrosPeriodosDialog("Quinzenal", tipo);
        } else if (combobox.getSelectedIndex() == 4) {
            calendario.showCalendarioOutrosPeriodosDialog("Mensal", tipo);
        } else if (combobox.getSelectedIndex() == 5) {
            calendario.showCalendarioOutrosPeriodosDialog("Bimestral", tipo);
        } else if (combobox.getSelectedIndex() == 6) {
            calendario.showCalendarioOutrosPeriodosDialog("Trimestral", tipo);
        } else if (combobox.getSelectedIndex() == 7) {
            calendario.showCalendarioOutrosPeriodosDialog("Semestral", tipo);
        } else if (combobox.getSelectedIndex() == 8) {
            calendario.showCalendarioOutrosPeriodosDialog("Anual", tipo);
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
        jLabelMedidaRelacionada = new javax.swing.JLabel();
        jComboBoxMedidaRelacionada = new javax.swing.JComboBox();
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
        jLabel10 = new javax.swing.JLabel();
        jTextFieldMetaCritico = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
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
        jPanel10 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableMedida = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldFormula = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jButtonAdicao = new javax.swing.JButton();
        jButtonVirgula = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButtonSubtracao = new javax.swing.JButton();
        jButtonMultiplicar = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButtonDividir = new javax.swing.JButton();
        jButtonParenteseFechado = new javax.swing.JButton();
        jButton1ParenteseAberto = new javax.swing.JButton();
        jButtonApagarTudo = new javax.swing.JButton();
        jButtonApagarUltimo = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTablePerfisInteressados = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableMeios = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        dateField = new net.sf.nachocalendar.components.DateField();
        jComboBoxIndicador = new javax.swing.JComboBox();
        jButtonCadastrarMeioComunicacao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Indicador:");

        jLabelCadastradoPor1.setText("Cadastrado por:");

        jTextFieldCadastradoPor.setEditable(false);
        jTextFieldCadastradoPor.setBackground(new java.awt.Color(204, 204, 204));

        jTextFieldUltimaEdicao.setEditable(false);
        jTextFieldUltimaEdicao.setBackground(new java.awt.Color(204, 204, 204));

        jLabelUltimaEdicao.setText("Última Edição:");

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

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel2.setText("Responsável pela Análise:");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Composição:"));

        jRadioButtonBase.setText("Base");
        jRadioButtonBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonBaseActionPerformed(evt);
            }
        });

        jRadioButtonDerivada.setText("Derivada");
        jRadioButtonDerivada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDerivadaActionPerformed(evt);
            }
        });

        jLabelMedidaRelacionada.setText("Medida Relacionada:");

        jComboBoxMedidaRelacionada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jRadioButtonBase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonDerivada)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelMedidaRelacionada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxMedidaRelacionada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonDerivada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButtonBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabelMedidaRelacionada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jComboBoxMedidaRelacionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel5.setText("Periodicidade:");

        jComboBoxPeriodicidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Selecione a periodicidade-", "Diaria", "Semanal", "Mensal ", "Anual" }));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxPeriodicidade, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel19))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldFrequencia1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBoxTipoGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 226, Short.MAX_VALUE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldResponsavel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTextFieldResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(jComboBoxPeriodicidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(jTextFieldFrequencia1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jComboBoxTipoGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Dados Gerais", jPanel2);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Critério de Análise"));

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
                    .addComponent(jScrollPane11)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(337, 337, 337))
                    .addComponent(jScrollPane12)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Meta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel8.setText("OK:");

        jTextFieldMetaOk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldMetaOkKeyTyped(evt);
            }
        });

        jLabel10.setText("Crítico:");

        jTextFieldMetaCritico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldMetaCriticoKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("<html>*A faixa de ALERTA será os valores<br>contidos entre as faixas CRÍTICO e OK.</html>");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldMetaCritico, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldMetaOk, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jTextFieldMetaCritico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jTextFieldMetaOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Meta e Critério de Análise", jPanel3);

        jLabel3.setText(" OK:");

        jLabel4.setText(" Alerta:");

        jLabel6.setText(" Crítico:");

        jTextAreaAcoesOk.setColumns(20);
        jTextAreaAcoesOk.setRows(2);
        jScrollPane3.setViewportView(jTextAreaAcoesOk);

        jTextAreaAcoesAlerta.setColumns(20);
        jTextAreaAcoesAlerta.setRows(2);
        jScrollPane4.setViewportView(jTextAreaAcoesAlerta);

        jTextAreaAcoesCritico.setColumns(20);
        jTextAreaAcoesCritico.setRows(2);
        jScrollPane5.setViewportView(jTextAreaAcoesCritico);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        jTabbedPane1.addTab("Ações", jPanel8);

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
        jTableMedida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMedidaMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTableMedida);

        jLabel21.setText("Fórmula:");

        jTextFieldFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(140, 166, 217));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonAdicao.setText("+");
        jButtonAdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicaoActionPerformed(evt);
            }
        });

        jButtonVirgula.setText(".");
        jButtonVirgula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVirgulaActionPerformed(evt);
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

        jButtonSubtracao.setText("-");
        jButtonSubtracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubtracaoActionPerformed(evt);
            }
        });

        jButtonMultiplicar.setText("*");
        jButtonMultiplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMultiplicarActionPerformed(evt);
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

        jButtonDividir.setText("/");
        jButtonDividir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDividirActionPerformed(evt);
            }
        });

        jButtonParenteseFechado.setText(")");
        jButtonParenteseFechado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonParenteseFechadoActionPerformed(evt);
            }
        });

        jButton1ParenteseAberto.setText("(");
        jButton1ParenteseAberto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ParenteseAbertoActionPerformed(evt);
            }
        });

        jButtonApagarTudo.setText("C");
        jButtonApagarTudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApagarTudoActionPerformed(evt);
            }
        });

        jButtonApagarUltimo.setText("<-");
        jButtonApagarUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApagarUltimoActionPerformed(evt);
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
                            .addComponent(jButtonApagarUltimo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
                            .addComponent(jButton20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jButtonApagarTudo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1, Short.MAX_VALUE))))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonVirgula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1ParenteseAberto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDividir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSubtracao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAdicao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonParenteseFechado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonMultiplicar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonParenteseFechado)
                    .addComponent(jButton1ParenteseAberto)
                    .addComponent(jButtonApagarTudo)
                    .addComponent(jButtonApagarUltimo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDividir)
                    .addComponent(jButton20)
                    .addComponent(jButton21)
                    .addComponent(jButton22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMultiplicar)
                    .addComponent(jButton17)
                    .addComponent(jButton18)
                    .addComponent(jButton19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSubtracao)
                    .addComponent(jButton13)
                    .addComponent(jButton15)
                    .addComponent(jButton16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButtonVirgula)
                    .addComponent(jButtonAdicao))
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
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Fórmula", jPanel10);

        jTablePerfisInteressados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Selecionar", "Perfis interessados"
            }
        ));
        jScrollPane6.setViewportView(jTablePerfisInteressados);

        jTableMeios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Selecionar", "Meios"
            }
        ));
        jTableMeios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMeiosMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTableMeios);

        jLabel15.setText("Data de Comunicação:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Comunicação", jPanel9);

        jComboBoxIndicador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxIndicador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIndicadorActionPerformed(evt);
            }
        });

        jButtonCadastrarMeioComunicacao.setText("Cadastrar Meio de Comunicação");
        jButtonCadastrarMeioComunicacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarMeioComunicacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonCadastrarMeioComunicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE))
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
                                .addComponent(jComboBoxIndicador, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxIndicador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCadastradoPor)
                    .addComponent(jLabelCadastradoPor1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUltimaEdicao)
                    .addComponent(jTextFieldUltimaEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrarMeioComunicacao)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        CtrlIndicador ctrlIndicador = new CtrlIndicador();
        CtrlProcedimentoDeAnalise ctrlProcedimentoDeAnalise = new CtrlProcedimentoDeAnalise();
        boolean save = false;

        procedimentodeanalise.setAcoesAlerta(jTextAreaAcoesAlerta.getText());
        procedimentodeanalise.setAcoesCritico(jTextAreaAcoesCritico.getText());
        procedimentodeanalise.setAcoesOk(jTextAreaAcoesOk.getText());

        procedimentodeanalise.setCriterioAlerta(jTextAreaCriterioAlerta.getText());
        procedimentodeanalise.setCriterioCritico(jTextAreaCriterioCritico.getText());
        procedimentodeanalise.setCriterioOk(jTextAreaCriterioOk.getText());

        procedimentodeanalise.setMetaAlerta(Double.parseDouble(jTextFieldMetaCritico.getText()) + 1);
        procedimentodeanalise.setMetaCritico(Double.parseDouble(jTextFieldMetaCritico.getText()));
        procedimentodeanalise.setMetaOk(Double.parseDouble(jTextFieldMetaOk.getText()));

        procedimentodeanalise.setResponsavel(jTextFieldResponsavel.getText());
        procedimentodeanalise.setComposicao(nomeRadioSelecionado());
        procedimentodeanalise.setPeriodicidade(jComboBoxPeriodicidade.getSelectedItem().toString());
        procedimentodeanalise.setFrequencia(jTextFieldFrequencia1.getText());
        procedimentodeanalise.setGraficoNome(jComboBoxTipoGrafico.getSelectedItem().toString());
        procedimentodeanalise.setObservacao(jTextAreaObservacao.getText());

        procedimentodeanalise.setFormula(selectFormula());
        procedimentodeanalise.setDataComunicacao((Date) dateField.getValue());
        procedimentodeanalise.setProjetoId(Copia.getProjetoSelecionado().getId());

        procedimentodeanalise.setIndicadorid(ctrlIndicador.buscarIndicadorPeloNome(jComboBoxIndicador.getSelectedItem().toString(), Copia.getProjetoSelecionado().getId()));

        if (ehNovoProcedimentoAnalise) {
            save = ctrlProcedimentoDeAnalise.criarNovoProcedimentoAnalise(procedimentodeanalise, listMeioComunicacaoIsCheked(), listPerfilInteressadoIsCheked());
            if (save) {
                JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao Salvar.");
            }
        } else {
            save = ctrlProcedimentoDeAnalise.editarProcedimentoAnalise(procedimentodeanalise, listMeioComunicacaoIsCheked(), listPerfilInteressadoIsCheked());
            if (save) {
                JOptionPane.showMessageDialog(null, "Editado com sucesso.");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao Editar.");
            }
        }

    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTabbedPane1MouseClicked
    {//GEN-HEADEREND:event_jTabbedPane1MouseClicked
        verifyButtonCadastrarMeioComunicacao();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButtonApagarUltimoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonApagarUltimoActionPerformed
    {//GEN-HEADEREND:event_jButtonApagarUltimoActionPerformed
        excluirUltimaLetraTeste();
    }//GEN-LAST:event_jButtonApagarUltimoActionPerformed

    private void jButtonApagarTudoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonApagarTudoActionPerformed
    {//GEN-HEADEREND:event_jButtonApagarTudoActionPerformed
        jTextFieldFormula.setText("");
    }//GEN-LAST:event_jButtonApagarTudoActionPerformed

    private void jButton1ParenteseAbertoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ParenteseAbertoActionPerformed
    {//GEN-HEADEREND:event_jButton1ParenteseAbertoActionPerformed

        if (isSinalUltimoParenteAberto() || jTextFieldFormula.getText().isEmpty()) {
            jTextFieldFormula.setText(jTextFieldFormula.getText() + "( ");
        }

    }//GEN-LAST:event_jButton1ParenteseAbertoActionPerformed

    private void jButtonParenteseFechadoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonParenteseFechadoActionPerformed
    {//GEN-HEADEREND:event_jButtonParenteseFechadoActionPerformed
        if (verificaInsercaoParenteseFechado() && !isSinalUltimo()) {
            jTextFieldFormula.setText(jTextFieldFormula.getText() + ") ");
        }

    }//GEN-LAST:event_jButtonParenteseFechadoActionPerformed

    private void jButtonDividirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDividirActionPerformed
    {//GEN-HEADEREND:event_jButtonDividirActionPerformed
        if (!isSinalUltimoOperador() && !jTextFieldFormula.getText().isEmpty()) {
            jTextFieldFormula.setText(adicionarEspacoEmBrancoAntesNumero() + "/ ");
        }
    }//GEN-LAST:event_jButtonDividirActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton22ActionPerformed
    {//GEN-HEADEREND:event_jButton22ActionPerformed
        if (!isParenteseFechadoUltimo() && !isMnemonico()) {
            jTextFieldFormula.setText(removerEspacoEmBranco() + "9 ");
        }

    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton21ActionPerformed
    {//GEN-HEADEREND:event_jButton21ActionPerformed

        if (!isParenteseFechadoUltimo() && !isMnemonico()) {
            jTextFieldFormula.setText(removerEspacoEmBranco() + "8 ");
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton20ActionPerformed
    {//GEN-HEADEREND:event_jButton20ActionPerformed

        if (!isParenteseFechadoUltimo() && !isMnemonico()) {
            jTextFieldFormula.setText(removerEspacoEmBranco() + "7 ");
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton17ActionPerformed
    {//GEN-HEADEREND:event_jButton17ActionPerformed

        if (!isParenteseFechadoUltimo() && !isMnemonico()) {
            jTextFieldFormula.setText(removerEspacoEmBranco() + "4 ");
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton18ActionPerformed
    {//GEN-HEADEREND:event_jButton18ActionPerformed
        if (!isParenteseFechadoUltimo() && !isMnemonico()) {
            jTextFieldFormula.setText(removerEspacoEmBranco() + "5 ");
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton19ActionPerformed
    {//GEN-HEADEREND:event_jButton19ActionPerformed
        if (!isParenteseFechadoUltimo() && !isMnemonico()) {
            jTextFieldFormula.setText(removerEspacoEmBranco() + "6 ");
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButtonMultiplicarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonMultiplicarActionPerformed
    {//GEN-HEADEREND:event_jButtonMultiplicarActionPerformed
        if (!isSinalUltimoOperador() && !jTextFieldFormula.getText().isEmpty()) {
            jTextFieldFormula.setText(adicionarEspacoEmBrancoAntesNumero() + "* ");
        }
    }//GEN-LAST:event_jButtonMultiplicarActionPerformed

    private void jButtonSubtracaoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSubtracaoActionPerformed
    {//GEN-HEADEREND:event_jButtonSubtracaoActionPerformed
        if (!isSinalUltimoOperador() & !jTextFieldFormula.getText().isEmpty()) {
            jTextFieldFormula.setText(adicionarEspacoEmBrancoAntesNumero() + "- ");
        }
    }//GEN-LAST:event_jButtonSubtracaoActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton16ActionPerformed
    {//GEN-HEADEREND:event_jButton16ActionPerformed
        if (!isParenteseFechadoUltimo() && !isMnemonico()) {
            jTextFieldFormula.setText(removerEspacoEmBranco() + "3 ");
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton15ActionPerformed
    {//GEN-HEADEREND:event_jButton15ActionPerformed
        if (!isParenteseFechadoUltimo() && !isMnemonico()) {
            jTextFieldFormula.setText(removerEspacoEmBranco() + "2 ");
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton13ActionPerformed
    {//GEN-HEADEREND:event_jButton13ActionPerformed

        if (!isParenteseFechadoUltimo() && !isMnemonico()) {
            jTextFieldFormula.setText(removerEspacoEmBranco() + "1 ");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        if (!isParenteseFechadoUltimo() && !isMnemonico()) {
            jTextFieldFormula.setText(removerEspacoEmBranco() + "0 ");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonVirgulaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonVirgulaActionPerformed
    {//GEN-HEADEREND:event_jButtonVirgulaActionPerformed
        if (validacaoUsoVirgula() && !isMnemonico()) {
            jTextFieldFormula.setText(removerEspacoEmBranco() + ".");
        }
    }//GEN-LAST:event_jButtonVirgulaActionPerformed

    private void jButtonAdicaoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAdicaoActionPerformed
    {//GEN-HEADEREND:event_jButtonAdicaoActionPerformed
        if (!isSinalUltimoOperador() && !jTextFieldFormula.getText().isEmpty()) {
            jTextFieldFormula.setText(adicionarEspacoEmBrancoAntesNumero() + "+ ");
        }
    }//GEN-LAST:event_jButtonAdicaoActionPerformed

    private void n(java.awt.event.ActionEvent evt)//GEN-FIRST:event_n
    {//GEN-HEADEREND:event_n
        // TODO add your handling code here:
    }//GEN-LAST:event_n

    private void jTableMedidaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableMedidaMouseClicked
    {//GEN-HEADEREND:event_jTableMedidaMouseClicked

        if (jTextFieldFormula.getText().isEmpty() || isSinalUltimoByMnemonico()) {
            if (evt.getClickCount() >= 2) {
                String mnemonico = jTableMedida.getValueAt(jTableMedida.getSelectedRow(), 1).toString();
                jTextFieldFormula.setText(jTextFieldFormula.getText() + mnemonico + " ");
            }
        }

    }//GEN-LAST:event_jTableMedidaMouseClicked

    private void jTextFieldFrequencia1KeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldFrequencia1KeyTyped
    {//GEN-HEADEREND:event_jTextFieldFrequencia1KeyTyped
        jTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldFrequencia1KeyTyped

    private void jRadioButtonDerivadaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButtonDerivadaActionPerformed
    {//GEN-HEADEREND:event_jRadioButtonDerivadaActionPerformed
        bloquearAbaFormula();
    }//GEN-LAST:event_jRadioButtonDerivadaActionPerformed

    private void jRadioButtonBaseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButtonBaseActionPerformed
    {//GEN-HEADEREND:event_jRadioButtonBaseActionPerformed
        bloquearAbaFormula();
    }//GEN-LAST:event_jRadioButtonBaseActionPerformed

    private void jButtonCadastrarMeioComunicacaoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCadastrarMeioComunicacaoActionPerformed
    {//GEN-HEADEREND:event_jButtonCadastrarMeioComunicacaoActionPerformed
        ViewProjeto_MeioComunicacao viewProjeto_MeioComunicacao = new ViewProjeto_MeioComunicacao(null, true);
        viewProjeto_MeioComunicacao.setVisible(true);

        popularListaMeio();
    }//GEN-LAST:event_jButtonCadastrarMeioComunicacaoActionPerformed

    private void jTableMeiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMeiosMouseClicked

    }//GEN-LAST:event_jTableMeiosMouseClicked

    private void jTextFieldMetaOkKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMetaOkKeyTyped
        jTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldMetaOkKeyTyped

    private void jTextFieldMetaCriticoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMetaCriticoKeyTyped
        jTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldMetaCriticoKeyTyped

    private void jComboBoxIndicadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIndicadorActionPerformed
        // TODO add your  handling code here:
    }//GEN-LAST:event_jComboBoxIndicadorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupComposicao;
    private net.sf.nachocalendar.components.DateField dateField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton1ParenteseAberto;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButtonAdicao;
    private javax.swing.JButton jButtonApagarTudo;
    private javax.swing.JButton jButtonApagarUltimo;
    private javax.swing.JButton jButtonCadastrarMeioComunicacao;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonDividir;
    private javax.swing.JButton jButtonMultiplicar;
    private javax.swing.JButton jButtonParenteseFechado;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonSubtracao;
    private javax.swing.JButton jButtonVirgula;
    private javax.swing.JComboBox jComboBoxIndicador;
    private javax.swing.JComboBox jComboBoxMedidaRelacionada;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelCadastradoPor1;
    private javax.swing.JLabel jLabelMedidaRelacionada;
    private javax.swing.JLabel jLabelUltimaEdicao;
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
    private javax.swing.JTable jTableMeios;
    private javax.swing.JTable jTablePerfisInteressados;
    private javax.swing.JTextArea jTextAreaAcoesAlerta;
    private javax.swing.JTextArea jTextAreaAcoesCritico;
    private javax.swing.JTextArea jTextAreaAcoesOk;
    private javax.swing.JTextArea jTextAreaCriterioAlerta;
    private javax.swing.JTextArea jTextAreaCriterioCritico;
    private javax.swing.JTextArea jTextAreaCriterioOk;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldCadastradoPor;
    private javax.swing.JTextField jTextFieldFormula;
    private javax.swing.JTextField jTextFieldFrequencia1;
    private javax.swing.JTextField jTextFieldMetaCritico;
    private javax.swing.JTextField jTextFieldMetaOk;
    private javax.swing.JTextField jTextFieldResponsavel;
    private javax.swing.JTextField jTextFieldUltimaEdicao;
    // End of variables declaration//GEN-END:variables

}
