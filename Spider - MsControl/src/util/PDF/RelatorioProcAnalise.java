package util.PDF;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import controller.CtrlIndicador;
import controller.CtrlProcedimentoDeAnalise;
import facade.FacadeJpa;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import model.Indicador;
import model.Procedimentodeanalise;
import util.Copia;

/**
 *
 * @author Géssica
 */
public class RelatorioProcAnalise {
    
    private CtrlProcedimentoDeAnalise ctrlProcedimentoDeAnalise = new CtrlProcedimentoDeAnalise();
    private CtrlIndicador ctrlIndicador = new CtrlIndicador();
    private List<Procedimentodeanalise> lista_ProcedimentoAnalise;
    private FacadeJpa jpa = FacadeJpa.getInstance();
    
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
            outputStream = new FileOutputStream("teste2.pdf");

            //associa a stream de saída ao
            PdfWriter.getInstance(document, outputStream);

            //abre o documento
            document.open();
            
            //Título
            Paragraph p1 = new Paragraph("Spider Ms-Control", fonte1);
            document.add(p1);
            p1.setAlignment(Element.ALIGN_CENTER);
            
            //Subtitulo
            Paragraph p2 = new Paragraph("Plano de Análise", fonte2);
            document.add(p2);
            p2.setAlignment(Element.ALIGN_CENTER);
            p2.setSpacingAfter(20); 
            
            List<Indicador> listaIndicador = ctrlIndicador.getIndicadoresDoProjeto(Copia.getProjetoSelecionado().getId());
            for (int i = 0; i < listaIndicador.size(); i++) {
                Paragraph p3 = new Paragraph("Nome: " + listaIndicador.get(i).getNome(), fonte4);
                document.add(p3);
                p3.setAlignment(Element.ALIGN_LEFT);
                Paragraph p4 = new Paragraph("Mnemônico: " + listaIndicador.get(i).getMnemonico(), fonte4);
                document.add(p4);
                p4.setAlignment(Element.ALIGN_LEFT);
                Paragraph p5 = new Paragraph("Descrição: " + listaIndicador.get(i).getDescricao(), fonte4);
                document.add(p5);
                p5.setAlignment(Element.ALIGN_LEFT);
                
                for (int j = 0; j < lista_ProcedimentoAnalise.size(); j++) {
                    Paragraph p6 = new Paragraph("Responsável pela Análise: " + lista_ProcedimentoAnalise.get(i).getResponsavel(), fonte4);
                    document.add(p6);
                    p6.setAlignment(Element.ALIGN_LEFT);
                    Paragraph p7 = new Paragraph("Composição: " + lista_ProcedimentoAnalise.get(i).getComposicao(), fonte4);
                    document.add(p7);
                    p7.setAlignment(Element.ALIGN_LEFT);
                    Paragraph p8 = new Paragraph("Periodicidade: " + lista_ProcedimentoAnalise.get(i).getPeriodicidade(), fonte4);
                    document.add(p8);
                    p8.setAlignment(Element.ALIGN_LEFT);
                    Paragraph p9 = new Paragraph("Frequência: " + lista_ProcedimentoAnalise.get(i).getFrequencia(), fonte4);
                    document.add(p9);
                    p9.setAlignment(Element.ALIGN_LEFT);
                    Paragraph p10 = new Paragraph("Tipo de Gráfico: " + lista_ProcedimentoAnalise.get(i).getGraficoNome(), fonte4);
                    document.add(p10);
                    p10.setAlignment(Element.ALIGN_LEFT);
                }
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
        
