package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfCell;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import model.Projeto;
import model.Resultados;
import util.Copia;

/**
 *
 * @author Géssica
 */
public class Relatorio {

    private CtrlProjeto ctrlProjeto = new CtrlProjeto();
    private CtrlResultados ctrlResultados = new CtrlResultados();
    private Projeto projeto;
    
    public void gerarRelatorio() throws IOException {
        Document document = null;
        OutputStream outputStream = null;

        try {
            //fontes
            Font fonte1 = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.BOLD);
            Font fonte2 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
            Font fonte3 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
            Font fonte4 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font fonte5 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

            //cria o documento tamanho A4, margens de 2,54cm
            document = new Document(PageSize.A4, 72, 72, 72, 72);

            //cria a stream de saída
            outputStream = new FileOutputStream("teste1.pdf");

            //associa a stream de saída ao
            PdfWriter.getInstance(document, outputStream);

            //abre o documento
            document.open();
            
            //Setar imagem da logo
            Image img = Image.getInstance("src\\image\\spider.png");
            img.setAlignment(Element.ALIGN_CENTER); 
            document.add(img);

            //Título
            Paragraph p1 = new Paragraph("Spider Ms-Control", fonte1);
            document.add(p1);
            p1.setAlignment(Element.ALIGN_CENTER);
            
            //Subtitulo
            Paragraph p2 = new Paragraph("Relatório de Medição", fonte2);
            document.add(p2);
            p2.setAlignment(Element.ALIGN_CENTER);
            p2.setSpacingAfter(20); 

            //Agenda item 1
            projeto = new Projeto();
            projeto = ctrlProjeto.buscaProjetoPeloNome(Copia.getProjetoSelecionado().getNome());
            Paragraph p3 = new Paragraph("1. Projeto: " + projeto.getNome(), fonte3);
            document.add(p3);
            p3.setAlignment(Element.ALIGN_LEFT);//alinhamento
            p3.setSpacingBefore(20); //espaçamento antes do paragrafo
            
            //Agenda item 2
            Paragraph p4 = new Paragraph("2. Objetivos de Medição", fonte3);
            document.add(p4);
            p4.setAlignment(Element.ALIGN_LEFT);
            
            //Agenda item 3
            Paragraph p5 = new Paragraph("3. Resultados Gerados", fonte3);
            document.add(p5);
            p5.setAlignment(Element.ALIGN_LEFT);

            //Agenda subitem 3
            List<Resultados> listaResultados = ctrlResultados.getResultadosDoProjeto(Copia.getProjetoSelecionado().getId());
            for (int i = 0; i < listaResultados.size(); i++) {
                Paragraph p6 = new Paragraph(listaResultados.get(i).getTitulo(), fonte2);
                document.add(p6);
                p6.setAlignment(Element.ALIGN_CENTER);
            }
            
            Paragraph p7 = new Paragraph("________________________________________________________", fonte3);
            document.add(p7);
            p7.setAlignment(Element.ALIGN_LEFT);
            p7.setSpacingBefore(20);
            
            Paragraph p8 = new Paragraph("1. Projeto: " + projeto.getNome(), fonte3);
            document.add(p8);
            p8.setAlignment(Element.ALIGN_LEFT);
            
            Paragraph p9 = new Paragraph("________________________________________________________", fonte3);
            document.add(p9);
            p9.setAlignment(Element.ALIGN_LEFT);
            
            Paragraph p10 = new Paragraph("Status do Projeto: " + projeto.getStatus(), fonte5);
            document.add(p10);
            p10.setAlignment(Element.ALIGN_LEFT);
            
            Paragraph p11 = new Paragraph("Descrição do Projeto: " + projeto.getDescricao(), fonte5);
            document.add(p11);
            p11.setAlignment(Element.ALIGN_LEFT);
            p11.setSpacingAfter(20);
           
            Paragraph p12 = new Paragraph("2. Objetivos de Medição" ,fonte3);
            document.add(p12);
            p12.setAlignment(Element.ALIGN_LEFT);
            p12.setSpacingAfter(20);
            
            PdfPTable t = new PdfPTable(4);
            PdfPCell header = new PdfPCell(new Paragraph("Objetivo de Medição"));
            PdfPCell header2 = new PdfPCell(new Paragraph("Necessidade de Informação"));
            PdfPCell header3 = new PdfPCell(new Paragraph("Indicador"));
            PdfPCell header4 = new PdfPCell(new Paragraph("Prioridade"));
            t.addCell(header);
            t.addCell(header2);
            t.addCell(header3);
            t.addCell(header4);
            t.addCell("aaaaaa"); 
            document.add(t);
            
            Paragraph p13 = new Paragraph("3. Resultados Gerados" ,fonte3);
            document.add(p13);
            p13.setAlignment(Element.ALIGN_LEFT);
            p13.setSpacingBefore(20);
            
            
         
            
        } catch (Exception error) {
            System.out.println("Não deu :(");
            error.printStackTrace();
        } finally {
            if (document != null) {
                //fechamento do documento
                document.close();
            }

            if (outputStream != null) {
                //fechamento da stream de saída
                outputStream.close();
            }
        }
    }

}
