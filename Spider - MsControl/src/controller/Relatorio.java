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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            
            //Subtitulo
            Paragraph p2 = new Paragraph("Relatório de Medição", fonte2);
            p2.setAlignment(Element.ALIGN_CENTER);
            p2.setSpacingAfter(20); 
            document.add(p2); 
            
            Paragraph p3 = new Paragraph("________________________________________________________", fonte3);
            p3.setAlignment(Element.ALIGN_LEFT);
            p3.setSpacingBefore(20);
            document.add(p3);
            
            projeto = new Projeto();
            projeto = ctrlProjeto.buscaProjetoPeloNome(Copia.getProjetoSelecionado().getNome());
            Paragraph p4 = new Paragraph("1. Projeto: " + projeto.getNome(), fonte3);
            p4.setAlignment(Element.ALIGN_LEFT);
            p4.setSpacingBefore(0);
            document.add(p4);
            
            Paragraph p9 = new Paragraph("________________________________________________________", fonte3);
            p9.setAlignment(Element.ALIGN_LEFT);
            document.add(p9);
            
            Paragraph p10 = new Paragraph("Status do Projeto: " + projeto.getStatus(), fonte5);
            p10.setAlignment(Element.ALIGN_LEFT);
            document.add(p10);
            
            Paragraph p11 = new Paragraph("Descrição do Projeto: " + projeto.getDescricao(), fonte5);
            p11.setAlignment(Element.ALIGN_LEFT);
            p11.setSpacingAfter(20);
            document.add(p11);
           
            Paragraph p12 = new Paragraph("2. Objetivos de Medição" ,fonte3);
            p12.setAlignment(Element.ALIGN_LEFT);
            p12.setSpacingAfter(20);
            document.add(p12);
            
            PdfPTable t = new PdfPTable(4);
            PdfPCell header = new PdfPCell(new Paragraph("Objetivo de Medição", fonte4));
            PdfPCell header2 = new PdfPCell(new Paragraph("Necessidade de Informação", fonte4));
            PdfPCell header3 = new PdfPCell(new Paragraph("Indicador", fonte4));
            PdfPCell header4 = new PdfPCell(new Paragraph("Prioridade", fonte4));
            t.addCell(header);
            t.addCell(header2);
            t.addCell(header3);
            t.addCell(header4);
            t.addCell("aaaaaa"); 
            document.add(t);
            
            Paragraph p13 = new Paragraph("3. Resultados Gerados" ,fonte3);
            p13.setAlignment(Element.ALIGN_LEFT);
            p13.setSpacingBefore(20);
            document.add(p13);
            
            List<Resultados> listaResultados = ctrlResultados.getResultadosDoProjeto(Copia.getProjetoSelecionado().getId());
            for (int i = 0; i < listaResultados.size(); i++) {
                Paragraph p14 = new Paragraph(listaResultados.get(i).getTitulo(), fonte5);
                p14.setAlignment(Element.ALIGN_LEFT);
                document.add(p14);
                Paragraph p15 = new Paragraph("Dados Gerais:", fonte4);
                p15.setAlignment(Element.ALIGN_LEFT);
                document.add(p15);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String data = simpleDateFormat.format(listaResultados.get(i).getData());
                Paragraph p16 = new Paragraph("Data: " + data, fonte5);
                p16.setAlignment(Element.ALIGN_LEFT);
                document.add(p16);
                Paragraph p17 = new Paragraph("Gerado por: " + listaResultados.get(i).getNomeUsuario(), fonte5);
                p17.setAlignment(Element.ALIGN_LEFT);
                document.add(p17);
                Paragraph p18 = new Paragraph("Interpretação: " + listaResultados.get(i).getInterpretacao(), fonte5);
                p18.setAlignment(Element.ALIGN_LEFT);
                document.add(p18);
                Paragraph p19 = new Paragraph("Participantes da Interpretação: ", fonte5);
                p19.setAlignment(Element.ALIGN_LEFT);
                document.add(p19);
                Paragraph p20 = new Paragraph("Tomada de Decisão: " + listaResultados.get(i).getTomadaDeDecisao(), fonte5);
                p20.setAlignment(Element.ALIGN_LEFT);
                document.add(p20);
            }
         
            
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
