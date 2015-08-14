package view.indicadores;

import bsh.EvalError;
import bsh.Interpreter;
import controller.CtrlIndicador;
import controller.CtrlValores;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Indicador;
import model.Valorindicador;
import model.Valormedida;
import util.CheckDefaultTableModel;
import util.Copia;

/**
 *
 * @author Bleno Vale
 */
public class ViewProjeto_ValorIndicador extends javax.swing.JInternalFrame {

    private DefaultListModel modelJlist;
    private CheckDefaultTableModel checkModel;
    private final CtrlIndicador ctrlIndicador = new CtrlIndicador();
    private final CtrlValores ctrlValores = new CtrlValores();
    private DefaultComboBoxModel comboBoxModel;
    private List<Indicador> listaIndicadores;
    private Indicador indicadorSelecionado;
    private String[] arrayFormulaQuebrada;
    private List<String> listaVariaveis;
    private int contadorMarcados;

    public ViewProjeto_ValorIndicador() {
        initComponents();
    }

    public void showValorIndicador() {
        populaComboboxIndicadores();
        limparCampos();
    }

    public void limparCampos() {
        arrayFormulaQuebrada = null;
        jTextFieldFormula.setText("");
        jLabelMedidaSelecionada.setText("Medida selecionada:");

        modelJlist = new DefaultListModel();
        jListValorIndicador.setModel(modelJlist);

        checkModel = new CheckDefaultTableModel(new String[]{" ", "Mnemônico", "Valor Medida", "Periodicidade", "Gerado"}, 0, false);
        jTableValormedida.setModel(checkModel);
        jTableValormedida.getColumnModel().getColumn(0).setPreferredWidth(20);

        contadorMarcados = 0;

        dateFieldDe.setValue(new Date());
        dateFieldAte.setValue(new Date()); 
        
        jButtonRemover.setEnabled(false);
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

    private void quebraFormula(String formula) {
        arrayFormulaQuebrada = formula.split(" ");
    }

    private double formataDecimal(double valor){
        BigDecimal bigDecimal = new BigDecimal(valor).setScale(3, RoundingMode.HALF_EVEN);
        return bigDecimal.doubleValue();
    } 
    
    private void preemcherTabelaMnemonicoPorDatas(String mnemonico, Date dataInicio, Date dataFim) {
        List<Valormedida> listaValorMedida = new ArrayList<>();
        listaValorMedida = ctrlValores.buscaValorMedidaPorDatas(mnemonico, dataInicio, dataFim, Copia.getProjetoSelecionado().getId());

        for (int i = 0; i < listaValorMedida.size(); i++) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String data = simpleDateFormat.format(listaValorMedida.get(i).getData());
            Object[] linha = {
                false,
                listaValorMedida.get(i).getMedidaid().getMnemonico(),
                formataDecimal(listaValorMedida.get(i).getValor()),
                listaValorMedida.get(i).getMedidaid().getProcedimentodecoletaList().get(0).getPeriodicidade(),
                data};
            checkModel.addRow(linha);
        }
        jTableValormedida.setModel(checkModel);
        jTableValormedida.getColumnModel().getColumn(0).setPreferredWidth(20);
    }

    private void preencheTabelaMinimonicosDaFomula(String minimonico) {
        List<Valormedida> listaValorMedida = new ArrayList<>();
        listaValorMedida = ctrlValores.buscarListaValorMedidaPorNomeEIdProjeto(minimonico, Copia.getProjetoSelecionado().getId());

        for (int i = 0; i < listaValorMedida.size(); i++) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String data = simpleDateFormat.format(listaValorMedida.get(i).getData());
            Object[] linha = {
                false,
                listaValorMedida.get(i).getMedidaid().getMnemonico(),
                formataDecimal(listaValorMedida.get(i).getValor()),
                listaValorMedida.get(i).getMedidaid().getProcedimentodecoletaList().get(0).getPeriodicidade(),
                data};
            checkModel.addRow(linha);
        }
        jTableValormedida.setModel(checkModel);
        jTableValormedida.getColumnModel().getColumn(0).setPreferredWidth(20);
    }

    private void preencheTabelaPelaData() {
        if (jComboBoxIndicadores.getSelectedItem() != "--Selecione um indicador--") {
            pegaIndicadorSelecionado();
            listaVariaveis = new ArrayList<>();

            checkModel = new CheckDefaultTableModel(new String[]{" ", "Mnemônico", "Valor Medida", "Periodicidade", "Gerado"}, 0, false);
            quebraFormula(indicadorSelecionado.getProcedimentodeanaliseList().get(0).getFormula());

            for (int i = 0; i < arrayFormulaQuebrada.length; i++) {
                System.out.println("parte" + i + ":" + arrayFormulaQuebrada[i]);
                if (contemLetra(arrayFormulaQuebrada[i])) {
                    System.out.println(arrayFormulaQuebrada[i] + " contem letra!!");
                    listaVariaveis.add(arrayFormulaQuebrada[i]);
                }
            }

            checaRepetidoLista();
            for (int j = 0; j < listaVariaveis.size(); j++) {
                preemcherTabelaMnemonicoPorDatas(listaVariaveis.get(j), (Date) dateFieldDe.getValue(), (Date) dateFieldAte.getValue());
            }
            jLabelMedidaSelecionada.setText("Medida selecionada:");
        } else {
            limparCampos();
        }
    }

    private void calcularValorIndicador() {
        try {
            quebraFormula(indicadorSelecionado.getProcedimentodeanaliseList().get(0).getFormula());
            Interpreter interpreter = new Interpreter();
            int numLinhas = jTableValormedida.getRowCount();
            String result = new String();

            for (int i = 0; i < arrayFormulaQuebrada.length; i++) {
                if (contemLetra(arrayFormulaQuebrada[i])) {
                    for (int j = 0; j < numLinhas; j++) {
                        if ((boolean) jTableValormedida.getModel().getValueAt(j, 0) == true) {
                            if (jTableValormedida.getModel().getValueAt(j, 1).toString().equals(arrayFormulaQuebrada[i])) {
                                arrayFormulaQuebrada[i] = jTableValormedida.getModel().getValueAt(j, 2).toString();
                                result = result + arrayFormulaQuebrada[i];
                                break;
                            }
                        }
                    }
                } else {
                    result = result + arrayFormulaQuebrada[i];
                }
            }

            modelJlist.addElement(result + " = " + interpreter.eval("Resultado = " + result));
            jListValorIndicador.setModel(modelJlist);
        } catch (EvalError error) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um valor Para cada\n variável da fórmula na tabela.");
        }
    }

    private void checaRepetidoLista() {
        for (int i = 0; i < listaVariaveis.size(); i++) {
            for (int j = i + 1; j < listaVariaveis.size(); j++) {
                if (listaVariaveis.get(i).equals(listaVariaveis.get(j))) {
                    listaVariaveis.remove(j);
                }
            }
        }
    }

    private boolean contemLetra(String texto) {
        // Expressão regurlar pra saber se texto tem letra e número ou só letra.
        return texto.matches("[a-zA-Z]+[0-9]+|[a-zA-Z]+");
    }

    private void salvarValoresIndicador() {
        boolean passou = false;
        for (int i = 0; i < jListValorIndicador.getModel().getSize(); i++) {
            Valorindicador valorindicador = new Valorindicador();
            String[] resultado = jListValorIndicador.getModel().getElementAt(i).toString().split("=");
            valorindicador.setIndicadorid(indicadorSelecionado);
            valorindicador.setValor(Double.parseDouble(resultado[resultado.length - 1]));
            valorindicador.setData(new Date());

            passou = ctrlValores.cadastraValorIndicador(valorindicador);
        }

        if (passou == true) {
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Erro inesperado.\nPor favor, verifique se o procedimento para\ngerar o valor do indicador foi feito corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        modelJlist = new DefaultListModel();
        jListValorIndicador.setModel(modelJlist);
        jButtonRemover.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxIndicadores = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldFormula = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateFieldDe = new net.sf.nachocalendar.components.DateField();
        dateFieldAte = new net.sf.nachocalendar.components.DateField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonGerar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListValorIndicador = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableValormedida = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabelMedidaSelecionada = new javax.swing.JLabel();

        setTitle("Valor Indicador");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("<html><b>Indicador:<b></html>");

        jComboBoxIndicadores.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Selecione um Indicador -- " }));
        jComboBoxIndicadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIndicadoresActionPerformed(evt);
            }
        });

        jLabel2.setText("<html><b>Formula:<b></html> ");

        jTextFieldFormula.setEditable(false);
        jTextFieldFormula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFormulaActionPerformed(evt);
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxIndicadores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldFormula)))
                .addGap(340, 340, 340))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxIndicadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFormula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("De:");

        jLabel4.setText("Até:");

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

        jLabel5.setText("     ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateFieldDe, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateFieldAte, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(dateFieldAte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateFieldDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(4, 4, 4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("<html>É necessário selecionar o Valor da Medida dos Minimônicos referentes a fórmula para gerar o resultado.<hrml>");

        jButtonGerar.setText("Gerar");
        jButtonGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGerarActionPerformed(evt);
            }
        });

        jButtonRemover.setText("Remover");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jListValorIndicador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListValorIndicadorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListValorIndicador);

        jTableValormedida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mnemônico", "Valor Medida", "Periodicidade", "Data de geração"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableValormedida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableValormedidaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableValormedida);

        jButton2.setText("Salvar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabelMedidaSelecionada.setText("Medida selecionada:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMedidaSelecionada)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRemover)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelMedidaSelecionada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButtonRemover)
                            .addComponent(jButtonGerar)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
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

    private void jTextFieldFormulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFormulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFormulaActionPerformed

    private void jButtonGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGerarActionPerformed
        if (jComboBoxIndicadores.getSelectedItem() != "--Selecione um indicador--") {
            calcularValorIndicador();
        } else {
            JOptionPane.showMessageDialog(null, "Escolha um indicador no combobox.");
        }
    }//GEN-LAST:event_jButtonGerarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        modelJlist.removeElementAt(jListValorIndicador.getSelectedIndex());
        jListValorIndicador.setModel(modelJlist);

        if (modelJlist.isEmpty()) {
            jButtonRemover.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jComboBoxIndicadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIndicadoresActionPerformed
        if (jComboBoxIndicadores.getSelectedItem() != "--Selecione um indicador--") {
            pegaIndicadorSelecionado();
            jTextFieldFormula.setText(indicadorSelecionado.getProcedimentodeanaliseList().get(0).getFormula());
            listaVariaveis = new ArrayList<>();

            checkModel = new CheckDefaultTableModel(new String[]{" ", "Mnemônico", "Valor Medida", "Periodicidade", "Gerado"}, 0, false);
            quebraFormula(indicadorSelecionado.getProcedimentodeanaliseList().get(0).getFormula());

            for (int i = 0; i < arrayFormulaQuebrada.length; i++) {
                System.out.println("parte" + i + ":" + arrayFormulaQuebrada[i]);
                if (contemLetra(arrayFormulaQuebrada[i])) {
                    System.out.println(arrayFormulaQuebrada[i] + " contem letra!!");
                    listaVariaveis.add(arrayFormulaQuebrada[i]);
                }
            }

            checaRepetidoLista();
            for (int j = 0; j < listaVariaveis.size(); j++) {
                preencheTabelaMinimonicosDaFomula(listaVariaveis.get(j));
            }
        } else {
            limparCampos();
        }
    }//GEN-LAST:event_jComboBoxIndicadoresActionPerformed

    private void jTableValormedidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableValormedidaMouseClicked
        if (evt.getClickCount() >= 1) {
            int linhaSelecioada = jTableValormedida.getSelectedRow();
            //System.out.println("------->>linhaSelecioada = " + linhaSelecioada);

            Valormedida valormedida = ctrlValores.buscarValorMedidaSelecionado(jTableValormedida.getModel().getValueAt(linhaSelecioada, 1).toString(), Copia.getProjetoSelecionado().getId());
            jLabelMedidaSelecionada.setText("<html>Medida selecionada: <b>" + valormedida.getMedidaid().getNome() + "</b></html>");

            if ((boolean) jTableValormedida.getModel().getValueAt(linhaSelecioada, 0) == true) {
                int numLinhas = jTableValormedida.getRowCount();
                for (int i = 0; i < numLinhas; i++) {
                    if (jTableValormedida.getModel().getValueAt(i, 1).toString().equals(jTableValormedida.getModel().getValueAt(linhaSelecioada, 1).toString())) {
                        if (((boolean) jTableValormedida.getModel().getValueAt(i, 0) == true) && i != linhaSelecioada) {
                            jTableValormedida.getModel().setValueAt(false, i, 0);
                            contadorMarcados--;
                        }
                    }
                }

                contadorMarcados++;
            }
        }

    }//GEN-LAST:event_jTableValormedidaMouseClicked

    private void jListValorIndicadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListValorIndicadorMouseClicked
        if (jListValorIndicador.getModel().getSize() > 0) {
            jButtonRemover.setEnabled(true);
        }
    }//GEN-LAST:event_jListValorIndicadorMouseClicked

    private void dateFieldAteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateFieldAteStateChanged
        preencheTabelaPelaData();
    }//GEN-LAST:event_dateFieldAteStateChanged

    private void dateFieldDeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateFieldDeStateChanged
        preencheTabelaPelaData();
    }//GEN-LAST:event_dateFieldDeStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        salvarValoresIndicador();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private net.sf.nachocalendar.components.DateField dateFieldAte;
    private net.sf.nachocalendar.components.DateField dateFieldDe;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonGerar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JComboBox jComboBoxIndicadores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelMedidaSelecionada;
    private javax.swing.JList jListValorIndicador;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableValormedida;
    private javax.swing.JTextField jTextFieldFormula;
    // End of variables declaration//GEN-END:variables
}
