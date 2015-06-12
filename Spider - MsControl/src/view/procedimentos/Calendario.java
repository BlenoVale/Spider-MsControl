package view.procedimentos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Datasprocedimentoanalise;
import model.Datasprocedimentocoleta;

/**
 *
 * @author Spider
 */
public class Calendario extends javax.swing.JDialog {

    private String tipo;
    private String procedimento;
    private Datasprocedimentocoleta dataProcedimentoColeta = null;
    private List<Datasprocedimentocoleta> listaDatasColetas;
    private Datasprocedimentoanalise dataProcedimentoAnalise = null;
    private List<Datasprocedimentoanalise> listaDatasAnalise;
    Date dataInicio;
    Date dataFim;

    public Calendario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        agruparBotoesSemanal();
    }

    public void showCalendarioDiarioDialog(String procedimento) {
        this.setLocationRelativeTo(null);

        tipo = "diario";
        this.procedimento = procedimento;
        setDiarioEnable(true);
        setSemanalEnable(false);
        datePanel.setEnabled(false);
        jLabelObservacao.setText("");
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

    public void showCalendarioSemanalDialog(String procedimento) {
        this.setLocationRelativeTo(null);

        tipo = "Semanal";
        this.procedimento = procedimento;
        setDiarioEnable(false);
        setSemanalEnable(true);
        datePanel.setEnabled(false);
        jLabelObservacao.setText("*" + this.procedimento + " semanal dura 7 dias, começando pelo dia selecionado.");
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

    public void showCalendarioOutrosPeriodosDialog(String tipo, String procedimento) {
        this.setLocationRelativeTo(null);

        this.tipo = tipo;
        this.procedimento = procedimento;
        switch (tipo) {
            case "Quinzenal":
                jLabelObservacao.setText("*" + this.procedimento + " Quinzenal começa aparti do dia selecionado.");
                break;
            case "Mensal":
                jLabelObservacao.setText("*" + this.procedimento + " mensal começa aparti do dia selecionado.");
                break;
            case "Bimestral":
                jLabelObservacao.setText("*" + this.procedimento + " Bimestral começa aparti do dia selecionado.");
                break;
            case "Trimestral":
                jLabelObservacao.setText("*" + this.procedimento + " Trimestal começa aparti do dia selecionado.");
                break;
            case "Semestral":
                jLabelObservacao.setText("*" + this.procedimento + " Semestral começa aparti do dia selecionado.");
                break;
            case "Anual":
                jLabelObservacao.setText("*" + this.procedimento + " Anual começa aparti do dia selecionado.");
                break;
        }
        setDiarioEnable(false);
        setSemanalEnable(false);
        datePanel.setEnabled(true);
        this.setVisible(true);
    }

    private void agruparBotoesSemanal() {
        buttonGroup1.add(jRadioButtonSegunda);
        buttonGroup1.add(jRadioButtonTerca);
        buttonGroup1.add(jRadioButtonQuarta);
        buttonGroup1.add(jRadioButtonQuinta);
        buttonGroup1.add(jRadioButtonSexta);
        buttonGroup1.add(jRadioButtonSabado);
        buttonGroup1.add(jRadioButtonDomingo);
    }

    private boolean validaOutrosPeriodos() {
        Date dataCalendario = datePanel.getDate();
        if (dataCalendario.before(new Date()) && dataCalendario.getDay() != new Date().getDay()) {
            JOptionPane.showMessageDialog(null, "Dia selecionado invalido.");
            return false;
        } else if (dataCalendario.getDate() > 28) {
            JOptionPane.showMessageDialog(null, "É permitido apenas selecionar dias entre o 1° e 28°.");
            return false;
        } else {
            return true;
        }
    }

    private void somaQuinzenal() {
        dataInicio = datePanel.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataInicio);
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        dataFim = calendar.getTime();
        System.out.println(">>>>>>>Data inicio:" + dataInicio);
        System.out.println(">>>>>>>Data fim:" + dataFim);
    }

    private void somaDiasNaData(int qtdMeses) {
        dataInicio = datePanel.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataInicio);
        calendar.add(Calendar.MONTH, qtdMeses);
        dataFim = calendar.getTime();
    }

    //--------DataProcedimentoColeta--------------------------------------------------------------------------------------------------------
    public List<Datasprocedimentocoleta> getListaDataProcedimentoColeta() {
        return listaDatasColetas;
    }

    private void pegaListaDataProcedimentoColeta_Diario() {
        listaDatasColetas = new ArrayList<>();
        if (jCheckBoxSegunda.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Segunda-feira");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
        if (jCheckBoxTerca.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Terça-feira");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
        if (jCheckBoxQuarta.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Quarta-feira");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
        if (jCheckBoxQuinta.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Quinta-feira");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
        if (jCheckBoxSexta.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Sexta-feira");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
        if (jCheckBoxSabado.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Sabádo");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
        if (jCheckBoxDomingo.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Domingo");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
    }

    private void pegaListaDataProcedimentoColeta_Semanal() {
        listaDatasColetas = new ArrayList<>();
        if (jRadioButtonSegunda.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Segunda-feira");
            listaDatasColetas.add(dataProcedimentoColeta);

            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Domingo");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
        if (jRadioButtonTerca.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Terça-feira");
            listaDatasColetas.add(dataProcedimentoColeta);

            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Segunda-feira");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
        if (jRadioButtonQuarta.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Quarta-feira");
            listaDatasColetas.add(dataProcedimentoColeta);

            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Terça-feira");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
        if (jRadioButtonQuinta.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Quinta-feira");
            listaDatasColetas.add(dataProcedimentoColeta);

            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Quarta-feira");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
        if (jRadioButtonSexta.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Sexta-feira");
            listaDatasColetas.add(dataProcedimentoColeta);

            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Quinta-feira");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
        if (jRadioButtonSabado.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Sabádo");
            listaDatasColetas.add(dataProcedimentoColeta);

            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Sexata-feira");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
        if (jRadioButtonDomingo.isSelected()) {
            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Domingo");
            listaDatasColetas.add(dataProcedimentoColeta);

            dataProcedimentoColeta = new Datasprocedimentocoleta();
            dataProcedimentoColeta.setDia("Sabádo");
            listaDatasColetas.add(dataProcedimentoColeta);
        }
    }

    private void pegaListaDataProcedimentoColeta_outrosPeriodos() {
        listaDatasColetas = new ArrayList<>();
        dataProcedimentoColeta = new Datasprocedimentocoleta();
        dataProcedimentoColeta.setDataInicio(dataInicio);
        dataProcedimentoColeta.setDataFim(dataFim);
        listaDatasColetas.add(dataProcedimentoColeta);
    }

    private void tipoProcedimentoColeta() {
        if ("diario".equals(tipo)) {
            pegaListaDataProcedimentoColeta_Diario();
        } else if ("Semanal".equals(tipo)) {
            pegaListaDataProcedimentoColeta_Semanal();
        } else {
            if (!validaOutrosPeriodos()) {
                return;
            }
            if ("Quinzenal".equals(tipo)) {
                somaQuinzenal();
            } else if ("Mensal".equals(tipo)) {
                somaDiasNaData(1);
            } else if ("Bimestral".equals(tipo)) {
                somaDiasNaData(2);
            } else if ("Trimestral".equals(tipo)) {
                somaDiasNaData(3);
            } else if ("Semestral".equals(tipo)) {
                somaDiasNaData(6);
            } else if ("Anual".equals(tipo)) {
                somaDiasNaData(12);
            }
            pegaListaDataProcedimentoColeta_outrosPeriodos();
        }
    }

    //--------DataProcedimentoAnálise--------------------------------------------------------------------------------------------------------
    public List<Datasprocedimentoanalise> getListaDataProcedimentoAnalise() {
        return listaDatasAnalise;
    }

    private void pegaListaDataProcedimentoAnalise_Diario() {
        listaDatasAnalise = new ArrayList<>();
        if (jCheckBoxSegunda.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Segunda-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
        if (jCheckBoxTerca.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Terça-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
        if (jCheckBoxQuarta.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Quarta-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
        if (jCheckBoxQuinta.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Quinta-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
        if (jCheckBoxSexta.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Sexta-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
        if (jCheckBoxSabado.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Sabádo");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
        if (jCheckBoxDomingo.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Domingo");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
    }

    private void pegaListaDataProcedimentoAnalise_Semanal() {
        listaDatasAnalise = new ArrayList<>();
        if (jRadioButtonSegunda.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Segunda-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);

            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Domingo");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
        if (jRadioButtonTerca.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Terça-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);

            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Segunda-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
        if (jRadioButtonQuarta.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Quarta-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);

            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Terça-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
        if (jRadioButtonQuinta.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Quinta-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);

            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Quarta-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
        if (jRadioButtonSexta.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Sexta-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);

            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Quinta-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
        if (jRadioButtonSabado.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Sabádo");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);

            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Sexata-feira");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
        if (jRadioButtonDomingo.isSelected()) {
            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Domingo");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);

            dataProcedimentoAnalise = new Datasprocedimentoanalise();
            dataProcedimentoAnalise.setDia("Sabádo");
            dataProcedimentoAnalise.setTipo(procedimento);
            listaDatasAnalise.add(dataProcedimentoAnalise);
        }
    }

    private void pegaListaDataProcedimentoAnalise_outrosPeriodos() {
        listaDatasAnalise = new ArrayList<>();
        dataProcedimentoAnalise = new Datasprocedimentoanalise();
        dataProcedimentoAnalise.setDataInicio(dataInicio);
        dataProcedimentoAnalise.setDataFim(dataFim);
        dataProcedimentoAnalise.setTipo(procedimento);
        listaDatasAnalise.add(dataProcedimentoAnalise);
    }

    private void tipoProcedimentoAnalise() {
        if ("diario".equals(tipo)) {
            pegaListaDataProcedimentoAnalise_Diario();
        } else if ("Semanal".equals(tipo)) {
            pegaListaDataProcedimentoAnalise_Semanal();
        } else {
            if (!validaOutrosPeriodos()) {
                return;
            }

            if ("Quinzenal".equals(tipo)) {
                somaQuinzenal();
            } else if ("Mensal".equals(tipo)) {
                somaDiasNaData(1);
            } else if ("Bimestral".equals(tipo)) {
                somaDiasNaData(2);
            } else if ("Trimestral".equals(tipo)) {
                somaDiasNaData(3);
            } else if ("Semestral".equals(tipo)) {
                somaDiasNaData(6);
            } else if ("Anual".equals(tipo)) {
                somaDiasNaData(12);
            }
            pegaListaDataProcedimentoAnalise_outrosPeriodos();
        }
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
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (procedimento.equals("Coleta")) {
            tipoProcedimentoColeta();
        } else {
            tipoProcedimentoAnalise();
            if (!listaDatasAnalise.isEmpty()) {
                for (int i = 0; i < listaDatasAnalise.size(); i++) {
                    System.out.println("---->Dias : " + listaDatasAnalise.get(i).getDia());
                    System.out.println("---->Data Inicio : " + listaDatasAnalise.get(i).getDataInicio());
                    System.out.println("---->Data Fim : " + listaDatasAnalise.get(i).getDataFim());
                    System.out.println("---->Data Tipo : " + listaDatasAnalise.get(i).getTipo() + "\n# #");
                }
            }
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
    // End of variables declaration//GEN-END:variables
}
