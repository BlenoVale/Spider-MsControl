package view.indicadores;

import model.Procedimentodeanalise;

/**
 *
 * @author Bleno Vale
 */
public class View_InformacaoDialog extends javax.swing.JDialog {

    public View_InformacaoDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocationRelativeTo(null);
    }

    public void preencheCamposInfo(Procedimentodeanalise procedimentodeanalise) {
        jLabelMetaOK.setText("<html>OK: igual ou maior que <b>" + procedimentodeanalise.getMetaOk() + "</b></html>");
        jLabelMetaALERTA.setText("<html>ALERTA: igual ou maior que <b>" + procedimentodeanalise.getMetaAlerta() + "</b> e menor que <b>" + procedimentodeanalise.getMetaOk() + "</b></html>");
        jLabelMetaCRITICO.setText("<html>CRÍTICO: igual ou menor que <b>" + procedimentodeanalise.getMetaCritico() + "</b></html>");

        jTextAreaCriterioOK.setText("OK:  \n" + procedimentodeanalise.getCriterioOk()
                + "\n\nAÇÃO:  \n" + procedimentodeanalise.getAcoesOk());
        jTextAreaCriterioALERTA.setText("ALERTA:  \n" + procedimentodeanalise.getCriterioAlerta()
                + "\n\nAÇÃO:  \n" + procedimentodeanalise.getAcoesAlerta());
        jTextAreaCriterioCRITICO.setText("CRÍTICO:  \n" + procedimentodeanalise.getCriterioCritico()
                + "\n\nAÇÃO:  \n" + procedimentodeanalise.getAcoesCritico());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelMetaOK = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabelMetaALERTA = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabelMetaCRITICO = new javax.swing.JLabel();
        jLabelM = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaCriterioOK = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaCriterioALERTA = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaCriterioCRITICO = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informações");

        jPanel2.setBackground(new java.awt.Color(182, 224, 182));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelMetaOK.setText("OK:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMetaOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelMetaOK, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jPanel4.setBackground(new java.awt.Color(234, 234, 188));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelMetaALERTA.setText("ALERTA:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMetaALERTA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelMetaALERTA, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jPanel5.setBackground(new java.awt.Color(239, 191, 191));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelMetaCRITICO.setText("CRÍTICO:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMetaCRITICO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelMetaCRITICO, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jLabelM.setText("Metas");

        jTextAreaCriterioOK.setEditable(false);
        jTextAreaCriterioOK.setBackground(new java.awt.Color(182, 224, 182));
        jTextAreaCriterioOK.setColumns(20);
        jTextAreaCriterioOK.setLineWrap(true);
        jTextAreaCriterioOK.setRows(2);
        jTextAreaCriterioOK.setText("OK:");
        jTextAreaCriterioOK.setBorder(null);
        jTextAreaCriterioOK.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(jTextAreaCriterioOK);

        jLabel5.setText("Críterios de análise");

        jTextAreaCriterioALERTA.setEditable(false);
        jTextAreaCriterioALERTA.setBackground(new java.awt.Color(234, 234, 188));
        jTextAreaCriterioALERTA.setColumns(20);
        jTextAreaCriterioALERTA.setLineWrap(true);
        jTextAreaCriterioALERTA.setRows(2);
        jTextAreaCriterioALERTA.setText("ALERTA:");
        jTextAreaCriterioALERTA.setBorder(null);
        jTextAreaCriterioALERTA.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(jTextAreaCriterioALERTA);

        jTextAreaCriterioCRITICO.setEditable(false);
        jTextAreaCriterioCRITICO.setBackground(new java.awt.Color(239, 191, 191));
        jTextAreaCriterioCRITICO.setColumns(20);
        jTextAreaCriterioCRITICO.setLineWrap(true);
        jTextAreaCriterioCRITICO.setRows(2);
        jTextAreaCriterioCRITICO.setText("CRÍTICO:");
        jTextAreaCriterioCRITICO.setBorder(null);
        jTextAreaCriterioCRITICO.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(jTextAreaCriterioCRITICO);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelM)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelM;
    private javax.swing.JLabel jLabelMetaALERTA;
    private javax.swing.JLabel jLabelMetaCRITICO;
    private javax.swing.JLabel jLabelMetaOK;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextAreaCriterioALERTA;
    private javax.swing.JTextArea jTextAreaCriterioCRITICO;
    private javax.swing.JTextArea jTextAreaCriterioOK;
    // End of variables declaration//GEN-END:variables
}
