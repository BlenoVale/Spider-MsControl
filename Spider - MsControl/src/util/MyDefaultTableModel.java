/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.table.DefaultTableModel;

/**
 * Classe criada para facilita a criacao de tabelas editavais e tabelas nao
 * editaveis
 *
 * @author Dan Jhonatan
 */
public class MyDefaultTableModel extends DefaultTableModel {

    private boolean isEditable;

    /**
     * Cria um modelo para a JTable
     *
     * @param columnNames Nomes das colunas da tabela
     * @param rowCount Numero de linhas na tabela
     * @param isEditable true se as celulas sao editaveis, caso contrario false;
     */
    public MyDefaultTableModel(Object[] columnNames, int rowCount, boolean isEditable) {
        super(columnNames, rowCount);
        this.isEditable = isEditable;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return isEditable;
    }
}
