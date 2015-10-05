package controller;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
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
import util.Copia;

/**
 *
 * @author Géssica
 */
public class RelatorioProcAnalise {

    private CtrlProcedimentoDeAnalise ctrlProcedimentoDeAnalise = new CtrlProcedimentoDeAnalise();
    private CtrlIndicador ctrlIndicador = new CtrlIndicador();
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
            outputStream = new FileOutputStream("PlanoDeAnálise.pdf");

            //associa a stream de saída ao
            PdfWriter.getInstance(document, outputStream);

            //abre o documento
            document.open();

            //Setar imagem da logo
            Image img = Image.getInstance("src\\image\\logoMSC.png");
            img.setAlignment(Element.ALIGN_CENTER);
            img.scaleToFit(300, 300);
            document.add(img);

            //Subtitulo
            Paragraph p1 = new Paragraph("PLANO DE ANÁLISE", fonte2);
            p1.setAlignment(Element.ALIGN_CENTER);
            p1.setSpacingAfter(20);
            document.add(p1);

            List<Indicador> listaIndicador = ctrlIndicador.getIndicadoresDoProjeto(Copia.getProjetoSelecionado().getId());
            for (int i = 0; i < listaIndicador.size(); i++) {
                Paragraph p2 = new Paragraph();
                p2.add(new Chunk("Indicador: " , fonte4));
                p2.add(new Chunk(listaIndicador.get(i).getNome(), fonte5));
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p2));
                
                Paragraph p4 = new Paragraph();
                p4.add(new Chunk("Mnemônico: " , fonte4));
                p4.add(new Chunk(listaIndicador.get(i).getMnemonico(), fonte5));
                p4.setIndentationLeft(15);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p4));
                
                Paragraph p5 = new Paragraph();
                p5.add(new Chunk("Descrição: ", fonte4));
                p5.add(new Chunk(listaIndicador.get(i).getDescricao(), fonte5));
                p5.setIndentationLeft(15);
                p5.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p5));

                Paragraph p6 = new Paragraph();
                p6.add(new Chunk("Responsável pela Análise: ", fonte4));
                p6.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getResponsavel(), fonte5));
                p6.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p6));
                
                Paragraph p7 = new Paragraph();
                p7.add(new Chunk("Composição: ", fonte4));
                p7.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getComposicao(), fonte5));
                p7.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p7));
                
                Paragraph p8 = new Paragraph();
                p8.add(new Chunk("Periodicidade: ", fonte4));
                p8.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getPeriodicidade(), fonte5));
                p8.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p8));
                
                Paragraph p9 = new Paragraph();
                p9.add(new Chunk("Frequência: ", fonte4));
                p9.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getFrequencia(), fonte5));
                p9.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p9));
                
                Paragraph p10 = new Paragraph();
                p10.add(new Chunk("Tipo de Gráfico: ", fonte4));
                p10.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getGraficoNome(), fonte5));
                p10.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p10));
                
                Paragraph p11 = new Paragraph();
                p11.add(new Chunk("Meta - OK: ", fonte4));
                p11.add(new Chunk(String.valueOf(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getMetaOk()), fonte5));
                p11.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p11));
                
                Paragraph p12 = new Paragraph();
                p12.add(new Chunk("Meta - ALERTA: ", fonte4));
                p12.add(new Chunk(String.valueOf(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getMetaAlerta()), fonte5));
                p12.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p12));
                
                Paragraph p13 = new Paragraph();
                p13.add(new Chunk("Meta - CRÍTICO: ", fonte4));
                p13.add(new Chunk(String.valueOf(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getMetaCritico()), fonte5));
                p13.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p13));
                
                Paragraph p14 = new Paragraph();
                p14.add(new Chunk("Critério - OK: ", fonte4));
                p14.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getCriterioOk(), fonte5));
                p14.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p14));
                
                Paragraph p15 = new Paragraph();
                p15.add(new Chunk("Critério - ALERTA: ", fonte4));
                p15.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getCriterioAlerta(), fonte5));
                p15.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p15));
                
                Paragraph p16 = new Paragraph();
                p16.add(new Chunk("Critério - CRÍTICO: ", fonte4));
                p16.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getCriterioCritico(), fonte5));
                p16.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p16));
                
                Paragraph p17 = new Paragraph();
                p17.add(new Chunk("Ações - OK: ", fonte4));
                p17.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getAcoesOk(), fonte5));
                p17.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p17));
                
                Paragraph p18 = new Paragraph();
                p18.add(new Chunk("Ações - ALERTA: ", fonte4));
                p18.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getAcoesAlerta(), fonte5));
                p18.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p18));
                
                Paragraph p19 = new Paragraph();
                p19.add(new Chunk("Ações - CRÍTICO: ", fonte4));
                p19.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getAcoesCritico(), fonte5));
                p19.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p19));
                
                Paragraph p20 = new Paragraph();
                p20.add(new Chunk("Fórmula: ", fonte4));
                p20.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getFormula(), fonte5));
                p20.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p20));
                
                Paragraph p21 = new Paragraph();
                p21.add(new Chunk("Observação: ", fonte4));
                p21.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getObservacao(), fonte5));
                p21.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p21));
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
