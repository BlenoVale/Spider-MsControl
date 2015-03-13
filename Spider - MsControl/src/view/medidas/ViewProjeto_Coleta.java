package view.medidas;

import controller.CtrlColeta;
import controller.CtrlMedida;
import facade.FacadeJpa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.Coleta;
import model.Medida;
import util.Copia;
import util.Internal;
import util.MyDefaultTableModel;

/**
 *
 * @author Géssica
 */
public class ViewProjeto_Coleta extends javax.swing.JInternalFrame {

    private DefaultTableModel defaultTableModel;
    private final CtrlColeta ctrlColeta = new CtrlColeta();
    private final CtrlMedida ctrlMedida = new CtrlMedida();
    private DefaultListModel modelJlist = new DefaultListModel();
    private MyDefaultTableModel tableModel;
    private List<Medida> listMedida;
    private List<Coleta> listaColeta;
    private final FacadeJpa jpa = FacadeJpa.getInstance();
    private Medida medidaSelecionada = new Medida();

    private Coleta coleta;
    private boolean ehNovaColeta;

    public ViewProjeto_Coleta() {
        initComponents();
        Internal.retiraBotao(this);
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

        listMedida = new ArrayList<Medida>();
        listMedida = ctrlMedida.getMedidaDoProjeto(idDoProjeto);
    }

    public void preencherTabelaMedidaDoProjeto() {
        atualizaListaMedidaDoProjeto();
        preencherTabela(listMedida);
    }

    private void pegarMedidaSelecionada() {
        int idDoProjeto = Copia.getProjetoSelecionado().getId();
        medidaSelecionada = ctrlMedida.buscarMedidaPeloNome(jTableMedidas.getValueAt(jTableMedidas.getSelectedRow(), 0).toString(), idDoProjeto);
    }

    //Tabela de Coletas:
    public void preencherTabelaColeta(List<Coleta> listcoleta) {
        tableModel = new MyDefaultTableModel(new String[]{"Coleta"}, 0, false);
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

        if (numero.length() == 0)
            return numero;

        String numeroQuebrado[] = numero.split(",");

        if (numero.substring(numero.length() - 1, numero.length()).equals(","))
            return numero;

        if (numeroQuebrado.length == 2)
            numero = numeroQuebrado[0] + "." + numeroQuebrado[1];

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
        pegarMedidaSelecionada();
        Coleta coletaAux = new Coleta();
        boolean passou = true;
        for (int i = 0; i < jListColetasASalvar.getModel().getSize(); i++) {
            coletaAux.setData(new Date());
            coletaAux.setMedidaid(medidaSelecionada);
            coletaAux.setValorDaColeta(Double.parseDouble(jListColetasASalvar.getModel().getElementAt(i).toString()));
            System.out.println("Coleta: " + coletaAux.getValorDaColeta());
            passou = ctrlColeta.cadastrarColeta(coletaAux);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMedidas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableColetas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldValorColeta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cadastro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel1.setText("Tipo de Coleta:");

        jLabel2.setText("Valor da Coleta:");

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

        jLabel3.setText("Manual");

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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldValorColeta, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonImporta))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButtonRemover)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCancelar)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonRemover))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldValorColeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
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
        if (jTextFieldValorColeta.getText().equals("")) {
            return;
        }

        modelJlist.addElement(jTextFieldValorColeta.getText());
        jTextFieldValorColeta.setText("");
        jListColetasASalvar.setModel(modelJlist);

    }//GEN-LAST:event_jTextFieldValorColetaActionPerformed

    private void jTableMedidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMedidasMouseClicked
        pegarMedidaSelecionada();
        listaColeta = medidaSelecionada.getColetaList();
        preencherTabelaColeta(listaColeta);
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
        listaColeta = medidaSelecionada.getColetaList();
        preencherTabelaColeta(listaColeta);
        modelJlist = new DefaultListModel();
        jListColetasASalvar.setModel(modelJlist);
        
        jButtonRemover.setEnabled(false);
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        modelJlist.removeElementAt(jListColetasASalvar.getSelectedIndex());
        jListColetasASalvar.setModel(modelJlist);

        jButtonRemover.setEnabled(false);
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
        
        for(Coleta coleta:listaDaColeta){
            modelJlist.addElement(coleta.getValorDaColeta());
        }
        jListColetasASalvar.setModel(modelJlist);
    }//GEN-LAST:event_jButtonImportaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonImporta;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jListColetasASalvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableColetas;
    private javax.swing.JTable jTableMedidas;
    private javax.swing.JTextField jTextFieldValorColeta;
    // End of variables declaration//GEN-END:variables
}
