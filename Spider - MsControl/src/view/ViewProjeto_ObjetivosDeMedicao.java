package view;

import controller.CtrlProjeto;
import java.util.List;
import util.MyDefaultTableModel;
import model.Objetivodemedicacao;
import util.Internal;
import facade.FacadeJpa;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import util.Copia;

/**
 *
 * @author BlenoVale
 */
public class ViewProjeto_ObjetivosDeMedicao extends javax.swing.JInternalFrame {

    private MyDefaultTableModel tableModel;
    private List<Objetivodemedicacao> listObjetivodemedicacaos;
    private final FacadeJpa jpa = FacadeJpa.getInstance();

    public ViewProjeto_ObjetivosDeMedicao() {
        initComponents();
        
        iniciarTabela();

        Internal.retiraBotao(this);
    }

    private void iniciarTabela() {
        tableModel = new MyDefaultTableModel(new String[]{"Objetivo de medição", "Nível"}, 0, false);
        jTableObjetivo.setModel(tableModel);
    }

    private void preencherTabela(List<Objetivodemedicacao> listObjetivodemedicacaos) {

        for (int i = 0; i < listObjetivodemedicacaos.size(); i++) {
            String[] linhas = new String[]{
                listObjetivodemedicacaos.get(i).getNome(),
                listObjetivodemedicacaos.get(i).getNivelObjetivo()
            };
            tableModel.addRow(linhas);
        }
        jTableObjetivo.setModel(tableModel);
    }

    private void preencherTabelaObjetivoDigitado() {

        iniciarTabela();

        String objetivoBuscado = jTextFieldBuscarObjetivo.getText();
        int idProjeto = Copia.getProjetoSelecionado().getId();

        listObjetivodemedicacaos = jpa.getObjetivoDeMedicaoJpa().findObjetivoMedicaoByPartNome(objetivoBuscado, idProjeto);
        preencherTabela(listObjetivodemedicacaos);
    }

    public void preencherTabelaRecarregar() {
        iniciarTabela();        
        int idProjeto = Copia.getProjetoSelecionado().getId();
        listObjetivodemedicacaos = jpa.getObjetivoDeMedicaoJpa().findObjetivoMedicaoByIdProjeto(idProjeto);
        preencherTabela(listObjetivodemedicacaos);
    }

    public void editarObjetivo() {
        checkLinhaSelecionada();
        Objetivodemedicacao objetivodemedicacao = buscarObjetivoSelcionado(jTableObjetivo);

        ViewProjeto_ObjetivosDeMedicao_Novo novoObjetivoMedicao = new ViewProjeto_ObjetivosDeMedicao_Novo(null, true);
        novoObjetivoMedicao.showEditarObjetivoDialog(objetivodemedicacao);

    }

    public Objetivodemedicacao buscarObjetivoSelcionado(JTable table) {
        String nomeObjetivo = table.getValueAt(table.getSelectedRow(), 0).toString();
        Objetivodemedicacao objetivodemedicacao = jpa.getObjetivoDeMedicaoJpa().findByNome(nomeObjetivo);
        return objetivodemedicacao;
    }

    public void excluirObjetivo() {

        //checkLinhaSelecionada();
        //Objetivodemedicacao objetivodemedicacao = buscarObjetivoSelcionado(jTableObjetivo);
        //jpa.getObjetivoDeMedicaoJpa().excluirObjetivo(objetivodemedicacao.getNome(), ctrlProjeto.getIdProjeto());
        //jpa.getObjetivoDeMedicaoJpa().destroy(objetivodemedicacao.getObjetivodemedicacaoPK().getId());
    }

    public void checkLinhaSelecionada() {
        if (jTableObjetivo.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um objetivo na tabela");
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
        jLabelBuscar = new javax.swing.JLabel();
        jTextFieldBuscarObjetivo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableObjetivo = new javax.swing.JTable();
        jButtonExcluir = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonNovoObjetivo = new javax.swing.JButton();
        jButtonRecarregar = new javax.swing.JButton();

        setTitle("Objetivos de medição");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelBuscar.setText("Buscar objetivo:");

        jTextFieldBuscarObjetivo.setToolTipText("Digite o nome de um objetivo");
        jTextFieldBuscarObjetivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarObjetivoActionPerformed(evt);
            }
        });

        jTableObjetivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Objetivo de medição", "Nível"
            }
        ));
        jTableObjetivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableObjetivoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableObjetivo);

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

        jButtonNovoObjetivo.setText("Novo");
        jButtonNovoObjetivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoObjetivoActionPerformed(evt);
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
                        .addComponent(jLabelBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBuscarObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRecarregar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonNovoObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jLabelBuscar)
                    .addComponent(jTextFieldBuscarObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRecarregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonEditar)
                    .addComponent(jButtonNovoObjetivo))
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

    private void jButtonNovoObjetivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoObjetivoActionPerformed

        int idProjeto = Copia.getProjetoSelecionado().getId();

        ViewProjeto_ObjetivosDeMedicao_Novo objetivosDeMedicao_Novo = new ViewProjeto_ObjetivosDeMedicao_Novo(null, true);
        objetivosDeMedicao_Novo.showNovoObjetivoDialog(jpa.getProjetoJpa().findProjeto(idProjeto));

        preencherTabelaRecarregar();
    }//GEN-LAST:event_jButtonNovoObjetivoActionPerformed

    private void jTextFieldBuscarObjetivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarObjetivoActionPerformed
        preencherTabelaObjetivoDigitado();
    }//GEN-LAST:event_jTextFieldBuscarObjetivoActionPerformed

    private void jButtonRecarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecarregarActionPerformed
        jTextFieldBuscarObjetivo.setText("");
        preencherTabelaRecarregar();
    }//GEN-LAST:event_jButtonRecarregarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        excluirObjetivo();
        preencherTabelaRecarregar();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jTableObjetivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableObjetivoMouseClicked
        if(evt.getClickCount() >= 2){
            ViewProjeto_ObjetivosDeMedicao_Novo objetivoMedicao = new ViewProjeto_ObjetivosDeMedicao_Novo(null, true);
            objetivoMedicao.showDetalhesDoObjetivoDialog(listObjetivodemedicacaos.get(jTableObjetivo.getSelectedRow()));
        }
    }//GEN-LAST:event_jTableObjetivoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovoObjetivo;
    private javax.swing.JButton jButtonRecarregar;
    private javax.swing.JLabel jLabelBuscar;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableObjetivo;
    private javax.swing.JTextField jTextFieldBuscarObjetivo;
    // End of variables declaration//GEN-END:variables
}
