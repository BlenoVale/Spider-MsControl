package view.procedimentos;

/**
 *
 * @author Spider
 */
public class Calendario extends javax.swing.JDialog {

    public Calendario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void showCalendarioDiarioDialog() {
        this.setLocationRelativeTo(null);

        jTextFieldfrequencia.setText("0");
        setDiarioEnable(true);
        setSemanalEnable(false);
        datePanel.setEnabled(false);
        this.setVisible(true);
    }

    private void setDiarioEnable(boolean aux) {
        jCheckBoxSegunda.setEnabled(aux);
        jCheckBoxTerca.setEnabled(aux);
        jCheckBoxQuarta.setEnabled(aux);
        jCheckBoxQuinta.setEnabled(aux);
        jCheckBoxSexta.setEnabled(aux);
        jCheckBoxSabado.setEnabled(aux);
        jCheckBoxDomingo.setEnabled(aux);
    }

    public void showCalendarioSemanalDialog() {
        this.setLocationRelativeTo(null);

        jTextFieldfrequencia.setText("1");
        setDiarioEnable(false);
        setSemanalEnable(true);
        datePanel.setEnabled(false);
        this.setVisible(true);
    }

    private void setSemanalEnable(boolean aux) {
        jRadioButtonSegunda.setEnabled(aux);
        jRadioButtonTerca.setEnabled(aux);
        jRadioButtonQuarta.setEnabled(aux);
        jRadioButtonQuinta.setEnabled(aux);
        jRadioButtonSexta.setEnabled(aux);
        jRadioButtonSabado.setEnabled(aux);
        jRadioButtonDomingo.setEnabled(aux);
    }

    public void showCalendarioMensalDialog() {
        this.setLocationRelativeTo(null);
        jTextFieldfrequencia.setText("1");
        setDiarioEnable(false);
        setSemanalEnable(false);
        datePanel.setEnabled(true);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelDiario = new javax.swing.JPanel();
        jCheckBoxSegunda = new javax.swing.JCheckBox();
        jCheckBoxTerca = new javax.swing.JCheckBox();
        jCheckBoxQuarta = new javax.swing.JCheckBox();
        jCheckBoxQuinta = new javax.swing.JCheckBox();
        jCheckBoxSexta = new javax.swing.JCheckBox();
        jCheckBoxSabado = new javax.swing.JCheckBox();
        jCheckBoxDomingo = new javax.swing.JCheckBox();
        jPanelSemanal = new javax.swing.JPanel();
        jRadioButtonSegunda = new javax.swing.JRadioButton();
        jRadioButtonTerca = new javax.swing.JRadioButton();
        jRadioButtonQuarta = new javax.swing.JRadioButton();
        jRadioButtonQuinta = new javax.swing.JRadioButton();
        jRadioButtonSexta = new javax.swing.JRadioButton();
        jRadioButtonSabado = new javax.swing.JRadioButton();
        jRadioButtonDomingo = new javax.swing.JRadioButton();
        datePanel = new net.sf.nachocalendar.components.DatePanel();
        jLabelObservacao = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabelDecricao = new javax.swing.JLabel();
        jTextFieldfrequencia = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelDiario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelDiario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelDiarioMouseClicked(evt);
            }
        });

        jCheckBoxSegunda.setText("Segunda-feira");

        jCheckBoxTerca.setText("Terça-feira");

        jCheckBoxQuarta.setText("Quarta-feira");

        jCheckBoxQuinta.setText("Quinta-feira");

        jCheckBoxSexta.setText("Sexta-feira");

        jCheckBoxSabado.setText("Sabádo");

        jCheckBoxDomingo.setText("Domingo");

        javax.swing.GroupLayout jPanelDiarioLayout = new javax.swing.GroupLayout(jPanelDiario);
        jPanelDiario.setLayout(jPanelDiarioLayout);
        jPanelDiarioLayout.setHorizontalGroup(
            jPanelDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDiarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxSegunda)
                    .addComponent(jCheckBoxDomingo)
                    .addComponent(jCheckBoxTerca)
                    .addComponent(jCheckBoxQuarta)
                    .addComponent(jCheckBoxQuinta)
                    .addComponent(jCheckBoxSexta)
                    .addComponent(jCheckBoxSabado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDiarioLayout.setVerticalGroup(
            jPanelDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDiarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBoxSegunda, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxTerca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxQuarta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxQuinta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxSexta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxSabado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxDomingo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSemanal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jRadioButtonSegunda.setText("Segunda-feira");

        jRadioButtonTerca.setText("Terça-feira");

        jRadioButtonQuarta.setText("Quarta-feira");

        jRadioButtonQuinta.setText("Quinta-feira");

        jRadioButtonSexta.setText("Sexta-feira");

        jRadioButtonSabado.setText("Sabádo");

        jRadioButtonDomingo.setText("Domingo");

        javax.swing.GroupLayout jPanelSemanalLayout = new javax.swing.GroupLayout(jPanelSemanal);
        jPanelSemanal.setLayout(jPanelSemanalLayout);
        jPanelSemanalLayout.setHorizontalGroup(
            jPanelSemanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSemanalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSemanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonSegunda)
                    .addComponent(jRadioButtonTerca)
                    .addComponent(jRadioButtonDomingo)
                    .addComponent(jRadioButtonQuarta)
                    .addComponent(jRadioButtonQuinta)
                    .addComponent(jRadioButtonSexta)
                    .addComponent(jRadioButtonSabado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSemanalLayout.setVerticalGroup(
            jPanelSemanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSemanalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonSegunda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonTerca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonQuarta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonQuinta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonSexta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonSabado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonDomingo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        datePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelObservacao.setText("jLabel1");

        jButton1.setText("Confirmar");

        jLabelDecricao.setText("Determine uma Frequêcia:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelDecricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldfrequencia, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelObservacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanelDiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanelSemanal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDecricao)
                    .addComponent(jTextFieldfrequencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(datePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDiario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSemanal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabelObservacao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelDiarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelDiarioMouseClicked

    }//GEN-LAST:event_jPanelDiarioMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private net.sf.nachocalendar.components.DatePanel datePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBoxDomingo;
    private javax.swing.JCheckBox jCheckBoxQuarta;
    private javax.swing.JCheckBox jCheckBoxQuinta;
    private javax.swing.JCheckBox jCheckBoxSabado;
    private javax.swing.JCheckBox jCheckBoxSegunda;
    private javax.swing.JCheckBox jCheckBoxSexta;
    private javax.swing.JCheckBox jCheckBoxTerca;
    private javax.swing.JLabel jLabelDecricao;
    private javax.swing.JLabel jLabelObservacao;
    private javax.swing.JPanel jPanelDiario;
    private javax.swing.JPanel jPanelSemanal;
    private javax.swing.JRadioButton jRadioButtonDomingo;
    private javax.swing.JRadioButton jRadioButtonQuarta;
    private javax.swing.JRadioButton jRadioButtonQuinta;
    private javax.swing.JRadioButton jRadioButtonSabado;
    private javax.swing.JRadioButton jRadioButtonSegunda;
    private javax.swing.JRadioButton jRadioButtonSexta;
    private javax.swing.JRadioButton jRadioButtonTerca;
    private javax.swing.JTextField jTextFieldfrequencia;
    // End of variables declaration//GEN-END:variables
}
