package util.PDF;

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
            outputStream = new FileOutputStream("teste3.pdf");

            //associa a stream de saída ao
            PdfWriter.getInstance(document, outputStream);

            //abre o documento
            document.open();
            
            //Título
            Paragraph p1 = new Paragraph("Spider Ms-Control", fonte1);
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            
            //Subtitulo
            Paragraph p2 = new Paragraph("Plano de Coleta", fonte2);
            p2.setAlignment(Element.ALIGN_CENTER);
            p2.setSpacingAfter(20); 
            document.add(p2);
            
            List<Medida> listaMedida = ctrlMedida.getMedidaDoProjeto(Copia.getProjetoSelecionado().getId());
            for (int i = 0; i < listaMedida.size(); i++) {
                Paragraph p3 = new Paragraph("Nome: " + listaMedida.get(i).getNome(), fonte4);
                document.add(p3);
                p3.setAlignment(Element.ALIGN_LEFT);
                Paragraph p4 = new Paragraph("Mnemônico: " + listaMedida.get(i).getMnemonico(), fonte4);
                document.add(p4);
                p4.setAlignment(Element.ALIGN_LEFT);
                Paragraph p5 = new Paragraph("Descrição: " + listaMedida.get(i).getDefinicao(), fonte4);
                document.add(p5);
                p5.setAlignment(Element.ALIGN_LEFT);
                
                Paragraph p6 = new Paragraph("Responsável pela Coleta: " + listaMedida.get(i).getProcedimentodecoletaList().get(0).getResponsavelPelaColeta(), fonte4);
                document.add(p6);
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
