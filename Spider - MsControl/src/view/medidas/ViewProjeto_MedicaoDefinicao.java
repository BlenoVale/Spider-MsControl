package view.medidas;

import java.util.List;
import util.MyDefaultTableModel;

import util.Internal;
import facade.FacadeJpa;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Medida;
import util.Copia;

/**
 *
 * @author BlenoVale
 */
public class ViewProjeto_MedicaoDefinicao extends javax.swing.JInternalFrame {

    private MyDefaultTableModel tableModel;
    private List<Medida> listMedida;
    private final FacadeJpa jpa = FacadeJpa.getInstance();

    public ViewProjeto_MedicaoDefinicao() {
        initComponents();
        iniciarTabela();
        Internal.retiraBotao(this);
    }

    private void iniciarTabela() {
        tableModel = new MyDefaultTableModel(new String[]{"Medida", "Mnenônico", "Escala" , "Faixa"}, 0, false);
        jTableMedida.setModel(tableModel);
    }

    private void preencherTabela(List<Medida> listMedida) {

        for (int i = 0; i < listMedida.size(); i++) {
            String[] linhas = new String[]{
                listMedida.get(i).getNome(),
                listMedida.get(i).getMnemonico(),
                listMedida.get(i).getEscala(),
                String.valueOf(listMedida.get(i).getFaixaInicio()) + " - " +String.valueOf(listMedida.get(i).getFaixaFim())
            };
            tableModel.addRow(linhas);
        }
        jTableMedida.setModel(tableModel);
    }

    private void preencherTabelaObjetivoDigitado() {

        iniciarTabela();

        listMedida = jpa.getMedicaoJpa().findByNome(jTextFieldBuscarMedida.getText(), Copia.getProjetoSelecionado().getId());
        preencherTabela(listMedida);
    }

    public void preencherTabelaRecarregar() {
        iniciarTabela();        
        listMedida = jpa.getMedicaoJpa().findByProjeto(Copia.getProjetoSelecionado().getId());
        preencherTabela(listMedida);
        
    }

    public void editarObjetivo() {
        checkLinhaSelecionada();
        
    }

    public Medida buscarDefinicaoSelecionada(JTable table) {
        return null;
    }

    public void checkLinhaSelecionada() {
        if (jTableMedida.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma medida na tabela");
            return;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabelBuscarMedida = new javax.swing.JLabel();
        jTextFieldBuscarMedida = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMedida = new javax.swing.JTable();
        jButtonExcluir = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonNovaMedida = new javax.swing.JButton();
        jButtonRecarregar = new javax.swing.JButton();

        setTitle("Definição Medida");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelBuscarMedida.setText("Buscar Medida:");

        jTextFieldBuscarMedida.setToolTipText("Digite o nome de um objetivo");
        jTextFieldBuscarMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarMedidaActionPerformed(evt);
            }
        });

        jTableMedida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Medida", "Versão", "Data", "Autor", "Status"
            }
        ));
        jTableMedida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMedidaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMedida);

        jButtonExcluir.setText("Excluir ?");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonNovaMedida.setText("Novo");
        jButtonNovaMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovaMedidaActionPerformed(evt);
            }
        });

        jButtonRecarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh3.png"))); // NOI18N
        jButtonRecarregar.setToolTipText("Recarregar tabela");
        jButtonRecarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecarregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelBuscarMedida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBuscarMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRecarregar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonNovaMedida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonExcluir)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBuscarMedida)
                    .addComponent(jTextFieldBuscarMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRecarregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonEditar)
                    .addComponent(jButtonNovaMedida))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        editarObjetivo();
        preencherTabelaRecarregar();
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonNovaMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaMedidaActionPerformed

        ViewProjeto_MedicaoDefinicao_Novo definicaoMedicao_Novo1 = new ViewProjeto_MedicaoDefinicao_Novo(null, true);
        definicaoMedicao_Novo1.setVisible(true);
        preencherTabelaRecarregar();
    }//GEN-LAST:event_jButtonNovaMedidaActionPerformed

    private void jTextFieldBuscarMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarMedidaActionPerformed
        preencherTabelaObjetivoDigitado();
    }//GEN-LAST:event_jTextFieldBuscarMedidaActionPerformed

    private void jButtonRecarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecarregarActionPerformed
        jTextFieldBuscarMedida.setText("");
        preencherTabelaRecarregar();
    }//GEN-LAST:event_jButtonRecarregarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        
        preencherTabelaRecarregar();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jTableMedidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMedidaMouseClicked
        if(evt.getClickCount() >= 2){
            
          
        }
    }//GEN-LAST:event_jTableMedidaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovaMedida;
    private javax.swing.JButton jButtonRecarregar;
    private javax.swing.JLabel jLabelBuscarMedida;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMedida;
    private javax.swing.JTextField jTextFieldBuscarMedida;
    // End of variables declaration//GEN-END:variables
}
