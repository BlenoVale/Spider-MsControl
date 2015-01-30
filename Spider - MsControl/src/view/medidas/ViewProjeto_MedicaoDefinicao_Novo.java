package view.medidas;

import javax.swing.DefaultComboBoxModel;


/**
 *
 * @author BlenoVale, DAN JHONATAN, paulo
 */
public class ViewProjeto_MedicaoDefinicao_Novo extends javax.swing.JDialog {

    private DefaultComboBoxModel comboboxModel;
    

    public ViewProjeto_MedicaoDefinicao_Novo(java.awt.Frame parent, boolean modal) {
        
        initComponents();
        ocultarEntidadeMedidaCadastrarNova();

        this.setLocationRelativeTo(null);
    }

  
    
    protected void popularComboBoxEntidadeMedida(){
                
        comboboxModel = new DefaultComboBoxModel();
        comboboxModel.addElement("Organização");
        comboboxModel.addElement("Projeto");
        comboboxModel.addElement("Processo");
        comboboxModel.addElement("Atividade");
        comboboxModel.addElement("Recurso Humano");
        comboboxModel.addElement("Recurso de Hardware");
        comboboxModel.addElement("Recurso de Software");
        comboboxModel.addElement("Artefato");
        
        jComboBoxEntidadeMedida.setModel(comboboxModel);
    }
    private void ocultarFormulaRadioBase(){
        if(jRadioButtonBase.isSelected()){
            jLabelFormula.setVisible(false);
            jTextFieldFormula.setVisible(false);
        }
    }
    private void showFormulaRadioDerivada(){
        if(jRadioButtonDerivada.isSelected()){
            jLabelFormula.setVisible(true);
            jTextFieldFormula.setVisible(true);
        }else{
            jLabelFormula.setVisible(false);
            jTextFieldFormula.setVisible(false);
        }
    }
    private void trocarRadioSelecionadoBase(){
        if(jRadioButtonBase.isSelected()){
         jRadioButtonDerivada.setSelected(false);
        }  
    }   
    private void trocarRadioSelecionadoDerivada(){
        if(jRadioButtonDerivada.isSelected()){
            jRadioButtonBase.setSelected(false);
            jRadioButtonDerivada.setSelected(true);
        }
    }
    private void showEntidadeMedidaCadastrarNova(){
        if(jRadioButtonEntidadeMedida.isSelected()){
            jComboBoxEntidadeMedida.setVisible(false);
            jLabelEntidadeMedida.setVisible(true);
            jTextFieldEntidadeMedida.setVisible(true);
        }
    }
    private void ocultarEntidadeMedidaCadastrarNova(){
        if(!jRadioButtonEntidadeMedida.isSelected()){
            jLabelEntidadeMedida.setVisible(false);
            jTextFieldEntidadeMedida.setVisible(false);
            jComboBoxEntidadeMedida.setVisible(true);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextFieldEntidademedida = new javax.swing.JPanel();
        jLabelNomeMedida = new javax.swing.JLabel();
        jTextFieldNomeMedida = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jComboBoxEntidadeMedida = new javax.swing.JComboBox();
        jTextFieldEntidadeMedida = new javax.swing.JTextField();
        jLabelEntidadeMedida = new javax.swing.JLabel();
        jRadioButtonEntidadeMedida = new javax.swing.JRadioButton();
        jLabelPropriedadeMedida = new javax.swing.JLabel();
        jLabelUnidadeMedida = new javax.swing.JLabel();
        jLabelEscala = new javax.swing.JLabel();
        jLabelDefinicao = new javax.swing.JLabel();
        jLabelResponsavel = new javax.swing.JLabel();
        jLabelMnemonico = new javax.swing.JLabel();
        jTextFieldResponsavel = new javax.swing.JTextField();
        jTextFieldMnemonico = new javax.swing.JTextField();
        jTextFieldPropriedadeMedida = new javax.swing.JTextField();
        jTextFieldUnidadeMedida = new javax.swing.JTextField();
        jTextFieldEscala = new javax.swing.JTextField();
        jLabelFaixa = new javax.swing.JLabel();
        jTextFieldNomeObjetivoFaixa = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jRadioButtonBase = new javax.swing.JRadioButton();
        jRadioButtonDerivada = new javax.swing.JRadioButton();
        jLabelFormula = new javax.swing.JLabel();
        jTextFieldFormula = new javax.swing.JTextField();
        jLabelNecessidadeInformacao = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaObservacoes = new javax.swing.JTextArea();
        jLabelObservacoes = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaDefinicao = new javax.swing.JTextArea();
        jComboBoxNecessidadeInformacao = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro nova definição medida");
        setPreferredSize(new java.awt.Dimension(621, 640));

        jTextFieldEntidademedida.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldEntidademedida.setPreferredSize(new java.awt.Dimension(453, 750));

        jLabelNomeMedida.setText("Medida:");

        jTextFieldNomeMedida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Entidade Medida"));
        jPanel2.setName("Entidade Medida"); // NOI18N

        jComboBoxEntidadeMedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxEntidadeMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEntidadeMedidaActionPerformed(evt);
            }
        });

        jLabelEntidadeMedida.setText("Entidade Medida:");

        jRadioButtonEntidadeMedida.setText("Cadastrar entidade medida");
        jRadioButtonEntidadeMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEntidadeMedidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelEntidadeMedida)
                        .addGap(67, 67, 67)
                        .addComponent(jTextFieldEntidadeMedida))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jComboBoxEntidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194)
                        .addComponent(jRadioButtonEntidadeMedida)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxEntidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonEntidadeMedida))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEntidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEntidadeMedida))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabelPropriedadeMedida.setText("Propriedade medida:");

        jLabelUnidadeMedida.setText("Unidade medida:");

        jLabelEscala.setText("Escala:");

        jLabelDefinicao.setText("Definição:");

        jLabelResponsavel.setText("Responsável:");

        jLabelMnemonico.setText("Mnemônico:");

        jTextFieldResponsavel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jTextFieldMnemonico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jTextFieldPropriedadeMedida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jTextFieldUnidadeMedida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jTextFieldEscala.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabelFaixa.setText("Faixa:");

        jTextFieldNomeObjetivoFaixa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Composição"));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jRadioButtonBase, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButtonDerivada)
                .addGap(226, 226, 226))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButtonDerivada))
                .addContainerGap())
        );

        jLabelFormula.setText("Fórmula:");

        jTextFieldFormula.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabelNecessidadeInformacao.setText("<html> Relacionada a Necessidade<br> de Informação: </html>");

        jTextAreaObservacoes.setColumns(20);
        jTextAreaObservacoes.setRows(5);
        jScrollPane3.setViewportView(jTextAreaObservacoes);

        jLabelObservacoes.setText("Observações:");

        jButtonSalvar.setText("Salvar");

        jButtonCancelar.setText("Cancelar");

        jTextAreaDefinicao.setColumns(20);
        jTextAreaDefinicao.setRows(5);
        jScrollPane4.setViewportView(jTextAreaDefinicao);

        jComboBoxNecessidadeInformacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jTextFieldEntidademedidaLayout = new javax.swing.GroupLayout(jTextFieldEntidademedida);
        jTextFieldEntidademedida.setLayout(jTextFieldEntidademedidaLayout);
        jTextFieldEntidademedidaLayout.setHorizontalGroup(
            jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jTextFieldEntidademedidaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelMnemonico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jTextFieldEntidademedidaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelResponsavel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelNomeMedida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jTextFieldEntidademedidaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelDefinicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                        .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNomeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldMnemonico, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                        .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                                .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelPropriedadeMedida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelUnidadeMedida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelEscala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabelFaixa))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldEscala, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                    .addComponent(jTextFieldUnidadeMedida, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldPropriedadeMedida, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNomeObjetivoFaixa)))
                            .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                                .addComponent(jLabelNecessidadeInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxNecessidadeInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(240, Short.MAX_VALUE))
                    .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                        .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelFormula)
                            .addComponent(jLabelObservacoes))
                        .addGap(73, 73, 73)
                        .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                                .addComponent(jTextFieldFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addComponent(jScrollPane3)))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTextFieldEntidademedidaLayout.createSequentialGroup()
                .addGap(384, 384, 384)
                .addComponent(jButtonSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jButtonCancelar)
                .addGap(30, 30, 30))
        );
        jTextFieldEntidademedidaLayout.setVerticalGroup(
            jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeMedida)
                    .addComponent(jTextFieldNomeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabelDefinicao))
                    .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelResponsavel)
                    .addComponent(jTextFieldResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMnemonico)
                    .addComponent(jTextFieldMnemonico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jTextFieldEntidademedidaLayout.createSequentialGroup()
                        .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPropriedadeMedida)
                            .addComponent(jTextFieldPropriedadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelUnidadeMedida)
                            .addComponent(jTextFieldUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelEscala)
                            .addComponent(jTextFieldEscala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelFaixa)
                            .addComponent(jTextFieldNomeObjetivoFaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFormula)
                        .addGap(5, 5, 5))
                    .addComponent(jTextFieldFormula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelNecessidadeInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNecessidadeInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabelObservacoes))
                    .addGroup(jTextFieldEntidademedidaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jTextFieldEntidademedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancelar))
                .addGap(100, 100, 100))
        );

        jScrollPane1.setViewportView(jTextFieldEntidademedida);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonDerivadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDerivadaActionPerformed
        trocarRadioSelecionadoDerivada();
        showFormulaRadioDerivada();
        ocultarFormulaRadioBase();
    }//GEN-LAST:event_jRadioButtonDerivadaActionPerformed

    private void jRadioButtonBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonBaseActionPerformed
        trocarRadioSelecionadoBase();
        ocultarFormulaRadioBase();
    }//GEN-LAST:event_jRadioButtonBaseActionPerformed

    private void jRadioButtonEntidadeMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEntidadeMedidaActionPerformed

        showEntidadeMedidaCadastrarNova();
        ocultarEntidadeMedidaCadastrarNova();
    }//GEN-LAST:event_jRadioButtonEntidadeMedidaActionPerformed

    private void jComboBoxEntidadeMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEntidadeMedidaActionPerformed

    }//GEN-LAST:event_jComboBoxEntidadeMedidaActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox jComboBoxEntidadeMedida;
    private javax.swing.JComboBox jComboBoxNecessidadeInformacao;
    private javax.swing.JLabel jLabelDefinicao;
    private javax.swing.JLabel jLabelEntidadeMedida;
    private javax.swing.JLabel jLabelEscala;
    private javax.swing.JLabel jLabelFaixa;
    private javax.swing.JLabel jLabelFormula;
    private javax.swing.JLabel jLabelMnemonico;
    private javax.swing.JLabel jLabelNecessidadeInformacao;
    private javax.swing.JLabel jLabelNomeMedida;
    private javax.swing.JLabel jLabelObservacoes;
    private javax.swing.JLabel jLabelPropriedadeMedida;
    private javax.swing.JLabel jLabelResponsavel;
    private javax.swing.JLabel jLabelUnidadeMedida;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButtonBase;
    private javax.swing.JRadioButton jRadioButtonDerivada;
    private javax.swing.JRadioButton jRadioButtonEntidadeMedida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextAreaDefinicao;
    private javax.swing.JTextArea jTextAreaObservacoes;
    private javax.swing.JTextField jTextFieldEntidadeMedida;
    private javax.swing.JPanel jTextFieldEntidademedida;
    private javax.swing.JTextField jTextFieldEscala;
    private javax.swing.JTextField jTextFieldFormula;
    private javax.swing.JTextField jTextFieldMnemonico;
    private javax.swing.JTextField jTextFieldNomeMedida;
    private javax.swing.JTextField jTextFieldNomeObjetivoFaixa;
    private javax.swing.JTextField jTextFieldPropriedadeMedida;
    private javax.swing.JTextField jTextFieldResponsavel;
    private javax.swing.JTextField jTextFieldUnidadeMedida;
    // End of variables declaration//GEN-END:variables
}
