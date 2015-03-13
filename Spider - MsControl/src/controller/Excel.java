package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

    private DefaultTableModel tableModel;
    private FileInputStream fisPlanilha = null;

    public static void main(String[] args) {
        //new Excel().processAll("C:\\Users\\Dan\\Downloads\\Isust_avaliação_quadro resumo.xlsx.xlsx");
    }

    public void parseToJTable(JTable table, String caminho) {
        try {
            File file = new File(caminho);
            fisPlanilha = new FileInputStream(file);

            //cria um workbook = planilha toda com todas as abas
            XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha);

            //recuperamos apenas a primeira aba ou primeira planilha
            XSSFSheet sheet = workbook.getSheetAt(0);

            //retorna todas as linhas da planilha 0 (aba 1)
            Iterator<Row> rowIterator = sheet.iterator();

            int totalLinhas = sheet.getLastRowNum();
            int totalColunas = sheet.rowIterator().next().getLastCellNum();

            System.out.println("Linhas: " + totalLinhas + "\nColunas: " + totalColunas);

            DefaultTableModel tableModel = new DefaultTableModel(totalLinhas + 1, totalColunas + 1);
            table.setModel(tableModel);

            int linha = 0;
            int coluna = 0;
            //varre todas as linhas da planilha 0
            while (rowIterator.hasNext()) {

                //recebe cada linha da planilha
                Row row = rowIterator.next();

                //pegamos todas as celulas desta linha
                Iterator<Cell> cellIterator = row.iterator();
                System.out.println("row " + row.getRowNum());

                //varremos todas as celulas da linha atual
                while (cellIterator.hasNext()) {

                    //criamos uma celula
                    Cell cell = cellIterator.next();
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        table.setValueAt(getCellValue(cell), linha, coluna);
                    }

                    coluna++;

                    //System.out.println(getCellValue(cell));
                }
                coluna = 0;
                linha++;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fisPlanilha.close();
            } catch (IOException ex) {
                Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void metodo(String caminho) {

        try {
            File file = new File(caminho);
            fisPlanilha = new FileInputStream(file);

            //cria um workbook = planilha toda com todas as abas
            XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha);

            //recuperamos apenas a primeira aba ou primeira planilha
            XSSFSheet sheet = workbook.getSheetAt(0);

            //retorna todas as linhas da planilha 0 (aba 1)
            Iterator<Row> rowIterator = sheet.iterator();

            //varre todas as linhas da planilha 0
            while (rowIterator.hasNext()) {

                //recebe cada linha da planilha
                Row row = rowIterator.next();

                //pegamos todas as celulas desta linha
                Iterator<Cell> cellIterator = row.iterator();
                System.out.println("row " + row.getRowNum());

                //varremos todas as celulas da linha atual
                while (cellIterator.hasNext()) {

                    //criamos uma celula
                    Cell cell = cellIterator.next();
                    System.out.println("Col " + cell.getColumnIndex() + " max " + row.getLastCellNum());

                    //System.out.println(getCellValue(cell));
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fisPlanilha.close();
            } catch (IOException ex) {
                Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private String getCellValue(Cell cell) {

        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                return formatar(cell.getNumericCellValue());

            case Cell.CELL_TYPE_FORMULA:
                return formatar(cell.getNumericCellValue());// + " - " + cell.getCachedFormulaResultType()

            //return String.valueOf(cell.getCellFormula());
            default:
                return null;
        }
    }

    public String formatar(double numero) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.##", decimalFormatSymbols);
        return decimalFormat.format(numero);
    }

    public void processAll(JTable table, String caminho) {
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(new File(caminho));
            // Carregando workbook           
            XSSFWorkbook wb = new XSSFWorkbook(fileInput);
            // Selecionando a primeira aba           
            XSSFSheet sheet = wb.getSheetAt(0);
            // Caso queira pegar valor por referencia       
//            CellReference cellReference = new CellReference("C3");
//            Row row = sheet.getRow(cellReference.getRow());
//            Cell cell = row.getCell(cellReference.getCol());
//            System.out.println("Valor Refe:" + cell.getStringCellValue());

            int totalLinha = 0;
            int totalColuna = 0;

            Iterator<Row> linhaIterator = sheet.iterator();
            while (linhaIterator.hasNext()) {
                Row row = linhaIterator.next();
                totalLinha++;
                Iterator<Cell> cell = row.iterator();

                int cont = 1;
                while (cell.hasNext()) {
                    cont++;
                    if (cont > totalColuna) {
                        totalColuna = cont;
                    }
                    cell.next();
                }
            }

            System.out.println("Linhas: " + totalLinha
                    + "\nColunas: " + totalColuna);

            DefaultTableModel model = new DefaultTableModel(totalLinha, totalColuna + 1);
            
            table.setModel(model);

            for (int i = 0; i < totalLinha; i++)
                table.setValueAt("Linha " + (i + 1), i, 0);

            for (int i = 0; i < totalLinha; i++) {
                System.out.print("Linha " + i + ": ");
                for (int j = 0; j < totalColuna; j++) {
                    Row row = sheet.getRow(i);
                    try {
                        Cell celula = row.getCell(j);

                        table.setValueAt(getCellValue(celula), i, (j + 1));

                        System.out.print("\t" + getCellValue(celula));
                    } catch (Exception ex) {
                        System.out.print("\texception");
                    }

                }
                System.out.print("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInput.close();
            } catch (IOException ex) {
                Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
