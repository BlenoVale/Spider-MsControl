package util.PDF;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import controller.CtrlMedida;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import model.Medida;
import util.Copia;

/**
 *
 * @author Géssica
 */
public class RelatorioProcColeta {
    
    private CtrlMedida ctrlMedida = new CtrlMedida();
    
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
            outputStream = new FileOutputStream("PlanoDeColeta.pdf");

            //associa a stream de saída ao
            PdfWriter.getInstance(document, outputStream);

            //abre o documento
            document.open();
            
            //Subtitulo
            Paragraph p1 = new Paragraph("PLANO DE COLETA", fonte2);
            p1.setAlignment(Element.ALIGN_CENTER);
            p1.setSpacingAfter(20); 
            document.add(p1);
            
            List<Medida> listaMedida = ctrlMedida.getMedidaDoProjeto(Copia.getProjetoSelecionado().getId());
            for (int i = 0; i < listaMedida.size(); i++) {
                Paragraph p2 = new Paragraph((i+1) + ". Medida: " + listaMedida.get(i).getNome(), fonte4);
                document.add(p2);
                
                Paragraph p3 = new Paragraph();
                p3.add(new Chunk("Mnemônico: ", fonte4));
                p3.add(new Chunk(listaMedida.get(i).getMnemonico(), fonte5));
                p3.setIndentationLeft(15);
                document.add(new Paragraph(p3));
                
                Paragraph p4 = new Paragraph();
                p4.add(new Chunk("Descrição: ", fonte4));
                p4.add(new Chunk(listaMedida.get(i).getDefinicao(), fonte5));
                p4.setIndentationLeft(15);
                document.add(new Paragraph(p4));
                
                Paragraph p5 = new Paragraph();
                p5.add(new Chunk("Responsável pela Coleta: ", fonte4));
                p5.add(new Chunk(listaMedida.get(i).getProcedimentodecoletaList().get(0).getResponsavelPelaColeta(), fonte5));
                document.add(new Paragraph(p5));
                
                Paragraph p6 = new Paragraph();
                p6.add(new Chunk("Tipo de Coleta: ", fonte4));
                p6.add(new Chunk(listaMedida.get(i).getProcedimentodecoletaList().get(0).getTipoDeColeta(), fonte5));
                document.add(new Paragraph(p6));
                
                Paragraph p7 = new Paragraph();
                p7.add(new Chunk("Periodicidade: ", fonte4));
                p7.add(new Chunk(listaMedida.get(i).getProcedimentodecoletaList().get(0).getPeriodicidade(), fonte5));
                document.add(new Paragraph(p7));
                
                Paragraph p8 = new Paragraph();
                p8.add(new Chunk("Frequência: ", fonte4));
                p8.add(new Chunk(String.valueOf(listaMedida.get(i).getProcedimentodecoletaList().get(0).getFrequencia()), fonte5));
                document.add(new Paragraph(p8));
                
                Paragraph p9 = new Paragraph();
                p9.add(new Chunk("Mínimo Coletas (%): ", fonte4));
                p9.add(new Chunk(String.valueOf(listaMedida.get(i).getProcedimentodecoletaList().get(0).getPorcentagem()), fonte5));
                document.add(new Paragraph(p9));
                
                Paragraph p10 = new Paragraph();
                p10.add(new Chunk("Cálculo: ", fonte4));
                p10.add(new Chunk(listaMedida.get(i).getProcedimentodecoletaList().get(0).getCalculo(), fonte5));
                document.add(new Paragraph(p10));
                
                
            }
            
            } catch (Exception error) {
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
