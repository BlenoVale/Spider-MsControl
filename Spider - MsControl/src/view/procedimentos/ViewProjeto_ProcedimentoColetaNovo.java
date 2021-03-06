package view.procedimentos;

import controller.CtrlProcedimentosColeta;
import facade.FacadeJpa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Datasprocedimentocoleta;
import model.Medida;
import model.Procedimentodecoleta;
import model.Registroprocedimentocoleta;
import util.Constantes;
import util.Copia;
import util.Texto;

/**
 *
 * @author Paulo, Géssica
 */
public class ViewProjeto_ProcedimentoColetaNovo extends javax.swing.JDialog {

    private DefaultComboBoxModel comboBoxModelMedida;
    private Procedimentodecoleta procedimentodecoleta = new Procedimentodecoleta();
    private List<Registroprocedimentocoleta> registroprocedimentocoletas;
    private Datasprocedimentocoleta datasprocedimentocoletas;
    List<Datasprocedimentocoleta> listaDatas = new ArrayList<>();
    private FacadeJpa jpa = FacadeJpa.getInstance();
    private boolean novoProcedimento = false;
    private boolean aux = false;

    public ViewProjeto_ProcedimentoColetaNovo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.pack();
    }

    public boolean validaCampos() {
        if (jComboBoxMedida.getSelectedItem() == "-Selecione uma Medida-") {
            JOptionPane.showMessageDialog(null, "Você deve selecionar uma \"Medida\"");
            return false;
        } else if (jComboBoxPeriodicidade.getSelectedItem() == "-Selecione uma Periodicidade-") {
            JOptionPane.showMessageDialog(null, "Você deve selecionar uma \"Periodicidade\"");
            return false;
        } else if (jComboBoxCalculo.getSelectedItem() == "-Selecione um Cálculo-") {
            JOptionPane.showMessageDialog(null, "Você deve selecionar um \"Cálculo\"");
            return false;
        } else if (jTextFieldResponsavelColeta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Responsável pela Coleta\" é obrigatório");
            return false;
        } else if (jTextAreaPassosColeta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Passos da Coleta \" é obrigatório");
            return false;
        } else if (jTextFieldFerramentaUtilizada.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Ferramenta Utilizada\" é obrigatório");
            return false;
        } else if (jComboBoxMomento.getSelectedItem() == "-Selecione um Momento-") {
            JOptionPane.showMessageDialog(null, "O campo \"Momento\" é obrigatório");
            return false;
        } else if (jTextFieldFrequencia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Frequência\" é obrigatório");
            return false;
        } else if ((jComboBoxCalculo.getSelectedItem() == "Sem Cálculo") && (!"1".equals(jTextFieldFrequencia.getText()))) {
            JOptionPane.showMessageDialog(null, "O campo \"Sem Cálculo\" só pode ser selecionado se o campo \"Frequência\" for 1");
            return false;
        } else if (!validaRadio()) {
            JOptionPane.showMessageDialog(null, "Você deve escolher um \"Tipo de Coleta\"");
            return false;
        } else if (!jTextFieldMinimoDeColeta.getText().isEmpty()) {
            double teste = Double.parseDouble(jTextFieldMinimoDeColeta.getText());
            if (teste == 0 || teste > 100) {
                JOptionPane.showMessageDialog(null, "O campo \"Mínimo de coletas\" só permite números entre 1 e 100.");
                return false;
            }
        } else if (jTextFieldMinimoDeColeta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo \"Mínimo de coletas\" é obrigatório");
            return false;
        }

        return true;
    }

    public boolean validaRadio() {
        if (jRadioButtonManual.isSelected()) {
            jRadioButtonPlanilha.setSelected(false);
            return true;
        } else if (jRadioButtonPlanilha.isSelected()) {
            jRadioButtonManual.setSelected(false);
            return true;
        } else {
            return false;
        }
    }

    public void popularComboBoxMedidaCadastrar() {

        comboBoxModelMedida = new DefaultComboBoxModel();
        comboBoxModelMedida.addElement("-Selecione uma medida-");
        List<Medida> medida = jpa.getMedidaJpa().findByProjeto(Copia.getProjetoSelecionado().getId());
        for (int i = 0; i < medida.size(); i++) {
            if (medida.get(i).getProcedimentodecoletaList().isEmpty()) {
                comboBoxModelMedida.addElement(medida.get(i).getNome());
            }
        }
        jComboBoxMedida.setModel(comboBoxModelMedida);
    }

    private void popularComboboxPeriodicidade() {
        comboBoxModelMedida = new DefaultComboBoxModel();

        comboBoxModelMedida.addElement("-Selecione uma Periodicidade-");
        List<String> listaPeriodicidade = Constantes.preencherListaPeriodicidade();

        for (int i = 0; i < listaPeriodicidade.size(); i++) {
            comboBoxModelMedida.addElement(listaPeriodicidade.get(i));
        }
        jComboBoxPeriodicidade.setModel(comboBoxModelMedida);
    }

    private void popularComboboxCalculo() {
        comboBoxModelMedida = new DefaultComboBoxModel();

        comboBoxModelMedida.addElement("-Selecione um Cálculo-");
        List<String> listaCalculo = Constantes.preencherListaCalculo();

        for (int i = 0; i < listaCalculo.size(); i++) {
            comboBoxModelMedida.addElement(listaCalculo.get(i));
        }
        jComboBoxCalculo.setModel(comboBoxModelMedida);
    }

    private void popularComboboxMomento() {
        comboBoxModelMedida = new DefaultComboBoxModel();

        comboBoxModelMedida.addElement("-Selecione um Momento-");
        List<String> listaMomento = Constantes.preencherListaMomento();

        for (int i = 0; i < listaMomento.size(); i++) {
            comboBoxModelMedida.addElement(listaMomento.get(i));
        }
        jComboBoxMomento.setModel(comboBoxModelMedida);
    }

    public void showDialogCadastrar() {

        popularComboboxCalculo();
        popularComboboxMomento();
        popularComboboxPeriodicidade();
        popularComboBoxMedidaCadastrar();
        this.setTitle("Cadastrar Procedimento de Coleta");
        jLabelUltimaEdicao.setVisible(false);
        jTextFieldUltimaEdicao.setVisible(false);
        jTextFieldCadastradoPor.setText(Copia.getUsuarioLogado().getNome() + " " + Texto.formataData(new Date()));
        novoProcedimento = true;
        aux= true;
    }

    public void showDialogEditar(Medida medida) {

        popularComboboxCalculo();
        popularComboboxMomento();
        popularComboboxPeriodicidade();
        popularComboBoxEditarMedida(medida);
        this.setTitle("Editar Procedimento de Coleta");

        jTextFieldCadastradoPor.setText(Copia.getUsuarioLogado().getNome() + " " + Texto.formataData(new Date()));
        preencherCamposEditar(medida);

        procedimentodecoleta = FacadeJpa.getInstance().getProcedimentoColetaJpa().findByProjeto(medida.getId(), Copia.getProjetoSelecionado().getId());
        popularRegistroEditar(procedimentodecoleta);
        novoProcedimento = false;
        
    }

    public void popularComboBoxEditarMedida(Medida medida) {
        comboBoxModelMedida = new DefaultComboBoxModel();
        comboBoxModelMedida.addElement(medida.getNome());
        jComboBoxMedida.setModel(comboBoxModelMedida);
        jComboBoxMedida.setEnabled(false);

    }

    public void preencherCamposEditar(Medida medida) {
        procedimentodecoleta = jpa.getProcedimentoColetaJpa().findByProjeto(medida.getId(), Copia.getProjetoSelecionado().getId());
        jTextFieldResponsavelColeta.setText(procedimentodecoleta.getResponsavelPelaColeta());
        jComboBoxMomento.setSelectedItem(procedimentodecoleta.getMomento());

        jComboBoxPeriodicidade.setSelectedItem(procedimentodecoleta.getPeriodicidade());

        jComboBoxCalculo.setSelectedItem(procedimentodecoleta.getCalculo());
        jTextFieldFrequencia.setText(String.valueOf(procedimentodecoleta.getFrequencia()));
        jTextFieldMinimoDeColeta.setText(String.valueOf(procedimentodecoleta.getPorcentagem() * 100));
        jTextAreaPassosColeta.setText(procedimentodecoleta.getPassosColeta());
        preencherRadioEditar(procedimentodecoleta.getTipoDeColeta());
        jTextFieldFerramentaUtilizada.setText(procedimentodecoleta.getFerramentasUtilizada());
        jTextAreaObservacao.setText(procedimentodecoleta.getObservacao());

    }

    public void popularRegistroEditar(Procedimentodecoleta procedimentodecoleta) {

        registroprocedimentocoletas = new ArrayList<>();

        System.out.println(procedimentodecoleta.getId());
        registroprocedimentocoletas = jpa.getRegistroProcedimentoColeta().findByIdProcedimento(procedimentodecoleta.getId(), Constantes.CADASTRO);

        jTextFieldCadastradoPor.setText(registroprocedimentocoletas.get(0).getNomeUsuario() + " Em " + Texto.formataData(registroprocedimentocoletas.get(0).getData()));

        registroprocedimentocoletas = new ArrayList<>();
        registroprocedimentocoletas = jpa.getRegistroProcedimentoColeta().findByIdProcedimento(procedimentodecoleta.getId(), Constantes.EDICAO);

        if (registroprocedimentocoletas.isEmpty()) {

            jTextFieldUltimaEdicao.setVisible(false);
            jLabelUltimaEdicao.setVisible(false);
        } else {
            jTextFieldUltimaEdicao.setText(registroprocedimentocoletas.get(registroprocedimentocoletas.size() - 1).getNomeUsuario() + " Em " + Texto.formataData(registroprocedimentocoletas.get(registroprocedimentocoletas.size() - 1).getData()));
        }
    }

    public void preencherRadioEditar(String valorName) {
        if (valorName.equals("Manual")) {
            jRadioButtonManual.setSelected(true);
        } else {
            jRadioButtonPlanilha.setSelected(true);
        }
    }

    public void jTextFieldSomenteNumeros(java.awt.event.KeyEvent evt) {
        String caracteres = "9876543210";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }

    public String pegarRadioSelecionado() {
        if (jRadioButtonManual.isSelected()) {
            return jRadioButtonManual.getText();
        } else {
            return jRadioButtonPlanilha.getText();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
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
        jTextFieldResponsavelColeta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jRadioButtonManual = new javax.swing.JRadioButton();
        jRadioButtonPlanilha = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxPeriodicidade = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldFrequencia = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldFerramentaUtilizada = new javax.swing.JTextField();
        jComboBoxCalculo = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldMinimoDeColeta = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxMomento = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaPassosColeta = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jComboBoxMedida = new javax.swing.JComboBox();

        jRadioButton1.setText("jRadioButton1");

        jLabel8.setText("jLabel8");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Medida:");

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

        jTextFieldResponsavelColeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldResponsavelColetaActionPerformed(evt);
            }
        });

        jLabel2.setText("Responsável pela Coleta:");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo de Coleta:"));

        jRadioButtonManual.setText("Manual");
        jRadioButtonManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonManualActionPerformed(evt);
            }
        });

        jRadioButtonPlanilha.setText("Planilha Eletrônica");
        jRadioButtonPlanilha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPlanilhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonManual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonPlanilha)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonManual)
                    .addComponent(jRadioButtonPlanilha))
                .addContainerGap())
        );

        jLabel5.setText("Periodicidade:");

        jComboBoxPeriodicidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBoxPeriodicidadeMousePressed(evt);
            }
        });
        jComboBoxPeriodicidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPeriodicidadeActionPerformed(evt);
            }
        });

        jLabel7.setText("Frequência:");

        jLabel12.setText("Cálculo:");

        jTextFieldFrequencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldFrequenciaKeyTyped(evt);
            }
        });

        jLabel15.setText("<html>  Ferramenta <br> Utilizada: </html>");

        jComboBoxCalculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("<html> Mínimo de<br>coletas (%): </html>");

        jTextFieldMinimoDeColeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldMinimoDeColetaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldFerramentaUtilizada)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBoxCalculo, 0, 219, Short.MAX_VALUE)
                                    .addComponent(jComboBoxPeriodicidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextFieldFrequencia, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldMinimoDeColeta))))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldResponsavelColeta, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldResponsavelColeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxPeriodicidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jComboBoxCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMinimoDeColeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldFrequencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFerramentaUtilizada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Dados Gerais", jPanel2);

        jLabel3.setText("Momento:");

        jComboBoxMomento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Passos da Coleta:");

        jTextAreaPassosColeta.setColumns(20);
        jTextAreaPassosColeta.setRows(5);
        jScrollPane3.setViewportView(jTextAreaPassosColeta);

        jLabel6.setText("Observação:");

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setRows(5);
        jScrollPane4.setViewportView(jTextAreaObservacao);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxMomento, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 217, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxMomento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Procedimento", jPanel3);

        jComboBoxMedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(jComboBoxMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCadastradoPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCadastradoPor1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUltimaEdicao)
                    .addComponent(jTextFieldUltimaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        CtrlProcedimentosColeta ctrlProcedimentosColeta = new CtrlProcedimentosColeta();

        if (!validaCampos()) {
            return;
        }

        Medida medida = jpa.getMedidaJpa().findByNomeAndProjeto(jComboBoxMedida.getSelectedItem().toString(), Copia.getProjetoSelecionado().getId());

        procedimentodecoleta.setData(new Date());
        procedimentodecoleta.setFerramentasUtilizada(jTextFieldFerramentaUtilizada.getText());
        procedimentodecoleta.setFrequencia(Integer.parseInt(jTextFieldFrequencia.getText()));
        procedimentodecoleta.setMedidaid(medida);
        procedimentodecoleta.setMomento(jComboBoxMomento.getSelectedItem().toString());
        procedimentodecoleta.setObservacao(jTextAreaObservacao.getText());
        procedimentodecoleta.setPassosColeta(jTextAreaPassosColeta.getText());
        procedimentodecoleta.setResponsavelPelaColeta(jTextFieldResponsavelColeta.getText());
        procedimentodecoleta.setTipoDeColeta(pegarRadioSelecionado());
        procedimentodecoleta.setProjetoId(Copia.getProjetoSelecionado().getId());
        procedimentodecoleta.setCalculo(jComboBoxCalculo.getSelectedItem().toString());
        double prctgm = Double.parseDouble(jTextFieldMinimoDeColeta.getText());
        procedimentodecoleta.setPorcentagem(prctgm / 100);
        procedimentodecoleta.setContadorColeta(0);

        boolean feito = false;
        if (novoProcedimento) {
            procedimentodecoleta.setPeriodicidade(jComboBoxPeriodicidade.getSelectedItem().toString());
            feito = ctrlProcedimentosColeta.criarProcedimentoColeta(procedimentodecoleta, listaDatas);
        } else {
            procedimentodecoleta.setProximaPeriodicidade(jComboBoxPeriodicidade.getSelectedItem().toString());
            feito = ctrlProcedimentosColeta.editarProcedimetoDeColetaEhData(procedimentodecoleta, listaDatas);
        }
        if (feito) {
            this.dispose();
        }

    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTextFieldFrequenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFrequenciaKeyTyped
        jTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldFrequenciaKeyTyped

    private void jRadioButtonManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonManualActionPerformed
        if (jRadioButtonManual.isSelected()) {
            jRadioButtonPlanilha.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButtonManualActionPerformed

    private void jRadioButtonPlanilhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPlanilhaActionPerformed
        if (jRadioButtonPlanilha.isSelected()) {
            jRadioButtonManual.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButtonPlanilhaActionPerformed

    private void jComboBoxPeriodicidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPeriodicidadeActionPerformed
        if (aux) {
            if (jComboBoxPeriodicidade.getSelectedIndex() == 0) {
                return;
            }

            Calendario calendario = new Calendario(null, true);
            if (jComboBoxPeriodicidade.getSelectedIndex() == 1) {
                calendario.showCalendarioDiarioDialog("Coleta");
            } else if (jComboBoxPeriodicidade.getSelectedIndex() == 2) {
                calendario.showCalendarioSemanalDialog("Coleta");
            } else if (jComboBoxPeriodicidade.getSelectedIndex() == 3) {
                calendario.showCalendarioOutrosPeriodosDialog("Quinzenal", "Coleta");
            } else if (jComboBoxPeriodicidade.getSelectedIndex() == 4) {
                calendario.showCalendarioOutrosPeriodosDialog("Mensal", "Coleta");
            } else if (jComboBoxPeriodicidade.getSelectedIndex() == 5) {
                calendario.showCalendarioOutrosPeriodosDialog("Bimestral", "Coleta");
            } else if (jComboBoxPeriodicidade.getSelectedIndex() == 6) {
                calendario.showCalendarioOutrosPeriodosDialog("Trimestral", "Coleta");
            } else if (jComboBoxPeriodicidade.getSelectedIndex() == 7) {
                calendario.showCalendarioOutrosPeriodosDialog("Semestral", "Coleta");
            } else if (jComboBoxPeriodicidade.getSelectedIndex() == 8) {
                calendario.showCalendarioOutrosPeriodosDialog("Anual", "Coleta");
            }

            listaDatas = new ArrayList<>();
            listaDatas = calendario.getListaDataProcedimentoColeta();
            if (listaDatas.isEmpty()) {
                jComboBoxPeriodicidade.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_jComboBoxPeriodicidadeActionPerformed

    private void jTextFieldResponsavelColetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldResponsavelColetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldResponsavelColetaActionPerformed

    private void jTextFieldMinimoDeColetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMinimoDeColetaKeyTyped
        jTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldMinimoDeColetaKeyTyped

    private void jComboBoxPeriodicidadeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxPeriodicidadeMousePressed
        aux = true;
    }//GEN-LAST:event_jComboBoxPeriodicidadeMousePressed

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
            java.util.logging.Logger.getLogger(ViewProjeto_ProcedimentoColetaNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_ProcedimentoColetaNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_ProcedimentoColetaNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProjeto_ProcedimentoColetaNovo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewProjeto_ProcedimentoColetaNovo dialog = new ViewProjeto_ProcedimentoColetaNovo(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox jComboBoxCalculo;
    private javax.swing.JComboBox jComboBoxMedida;
    private javax.swing.JComboBox jComboBoxMomento;
    private javax.swing.JComboBox jComboBoxPeriodicidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCadastradoPor1;
    private javax.swing.JLabel jLabelUltimaEdicao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButtonManual;
    private javax.swing.JRadioButton jRadioButtonPlanilha;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextArea jTextAreaPassosColeta;
    private javax.swing.JTextField jTextFieldCadastradoPor;
    private javax.swing.JTextField jTextFieldFerramentaUtilizada;
    private javax.swing.JTextField jTextFieldFrequencia;
    private javax.swing.JTextField jTextFieldMinimoDeColeta;
    private javax.swing.JTextField jTextFieldResponsavelColeta;
    private javax.swing.JTextField jTextFieldUltimaEdicao;
    // End of variables declaration//GEN-END:variables
}
