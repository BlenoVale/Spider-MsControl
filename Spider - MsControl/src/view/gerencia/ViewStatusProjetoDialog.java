package view.gerencia;

import javax.swing.JOptionPane;
import model.Projeto;
import util.Constantes;

/**
 *
 * @author DAN JHONATAN
 */
public class ViewStatusProjetoDialog extends javax.swing.JDialog {

    private int status = -1;

    public ViewStatusProjetoDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        agruparBotoes();
        this.setLocationRelativeTo(null);
    }

    private void agruparBotoes() {
        buttonGroup.add(jCheckBoxAtivo);
        buttonGroup.add(jCheckBoxInativo);
        buttonGroup.add(jCheckBoxFinalizado);
    }

    public int showMudaStatusDialog(int statusAtual) {
        status = statusAtual;

        if (statusAtual == Constantes.ATIVO)
            jCheckBoxAtivo.setVisible(false);
        else if (statusAtual == Constantes.INATIVO)
            jCheckBoxInativo.setVisible(false);
        else
            jCheckBoxFinalizado.setVisible(false);

        this.setVisible(true);

        return status;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jCheckBoxAtivo = new javax.swing.JCheckBox();
        jCheckBoxInativo = new javax.swing.JCheckBox();
        jCheckBoxFinalizado = new javax.swing.JCheckBox();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jCheckBoxAtivo.setText("Ativo");

        jCheckBoxInativo.setText("Inativo");

        jCheckBoxFinalizado.setText("Finalizado");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCancelar))
                            .addComponent(jCheckBoxAtivo)
                            .addComponent(jCheckBoxInativo)
                            .addComponent(jCheckBoxFinalizado))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBoxAtivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxInativo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxFinalizado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (jCheckBoxAtivo.isSelected())
            status = Constantes.ATIVO;
        else if (jCheckBoxInativo.isSelected())
            status = Constantes.INATIVO;
        else if (jCheckBoxFinalizado.isSelected()) {
            int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente finalizar este projeto? \n\nAo finalizar um projeto, este mesmo não poderá mais ser alterado");
            if (resp == JOptionPane.YES_OPTION)
                status = Constantes.FINALIZADO;
            else
                return;
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma das opções");
            return;
        }
        this.dispose();

    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JCheckBox jCheckBoxAtivo;
    private javax.swing.JCheckBox jCheckBoxFinalizado;
    private javax.swing.JCheckBox jCheckBoxInativo;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
