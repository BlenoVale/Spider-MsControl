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
import java.util.ArrayList;
import java.util.List;
import model.Indicador;
import model.Relatorios;
import util.Copia;

/**
 *
 * @author Géssica
 */
public class RelatorioProcAnalise {

    private CtrlProcedimentoDeAnalise ctrlProcedimentoDeAnalise = new CtrlProcedimentoDeAnalise();
    private CtrlIndicador ctrlIndicador = new CtrlIndicador();
    private List<Relatorios> listRelatorios;
    private FacadeJpa jpa = FacadeJpa.getInstance();

    public void gerarRelatorio() throws IOException {
        Document document = null;
        OutputStream outputStream = null;

        try {
            //fontes
            Font fonte1 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Font fonte2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font fonte3 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

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
            Paragraph p1 = new Paragraph("PLANO DE ANÁLISE", fonte1);
            p1.setAlignment(Element.ALIGN_CENTER);
            p1.setSpacingAfter(20);
            document.add(p1);

            List<Indicador> listaIndicador = ctrlIndicador.getIndicadoresDoProjeto(Copia.getProjetoSelecionado().getId());
            for (int i = 0; i < listaIndicador.size(); i++) {
                Paragraph p2 = new Paragraph();
                p2.add(new Chunk("Indicador: " , fonte2));
                p2.add(new Chunk(listaIndicador.get(i).getNome(), fonte3));
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p2));
                
                Paragraph p4 = new Paragraph();
                p4.add(new Chunk("Mnemônico: " , fonte2));
                p4.add(new Chunk(listaIndicador.get(i).getMnemonico(), fonte3));
                p4.setIndentationLeft(15);
                p4.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p4));
                
                Paragraph p5 = new Paragraph();
                p5.add(new Chunk("Descrição: ", fonte2));
                p5.add(new Chunk(listaIndicador.get(i).getDescricao(), fonte3));
                p5.setIndentationLeft(15);
                p5.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p5));

                Paragraph p6 = new Paragraph();
                p6.add(new Chunk("Responsável pela Análise: ", fonte2));
                p6.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getResponsavel(), fonte3));
                p6.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p6));
                
                Paragraph p7 = new Paragraph();
                p7.add(new Chunk("Composição: ", fonte2));
                p7.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getComposicao(), fonte3));
                p7.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p7));
                
                Paragraph p8 = new Paragraph();
                p8.add(new Chunk("Periodicidade: ", fonte2));
                p8.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getPeriodicidade(), fonte3));
                p8.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p8));
                
                Paragraph p9 = new Paragraph();
                p9.add(new Chunk("Frequência: ", fonte2));
                p9.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getFrequencia(), fonte3));
                p9.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p9));
                
                Paragraph p10 = new Paragraph();
                p10.add(new Chunk("Tipo de Gráfico: ", fonte2));
                p10.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getGraficoNome(), fonte3));
                p10.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p10));
                
                Paragraph p11 = new Paragraph();
                p11.add(new Chunk("Meta - OK: ", fonte2));
                p11.add(new Chunk(String.valueOf(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getMetaOk()), fonte3));
                p11.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p11));
                
                Paragraph p12 = new Paragraph();
                p12.add(new Chunk("Meta - ALERTA: ", fonte2));
                p12.add(new Chunk(String.valueOf(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getMetaAlerta()), fonte3));
                p12.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p12));
                
                Paragraph p13 = new Paragraph();
                p13.add(new Chunk("Meta - CRÍTICO: ", fonte2));
                p13.add(new Chunk(String.valueOf(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getMetaCritico()), fonte3));
                p13.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p13));
                
                Paragraph p14 = new Paragraph();
                p14.add(new Chunk("Critério - OK: ", fonte2));
                p14.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getCriterioOk(), fonte3));
                p14.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p14));
                
                Paragraph p15 = new Paragraph();
                p15.add(new Chunk("Critério - ALERTA: ", fonte2));
                p15.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getCriterioAlerta(), fonte3));
                p15.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p15));
                
                Paragraph p16 = new Paragraph();
                p16.add(new Chunk("Critério - CRÍTICO: ", fonte2));
                p16.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getCriterioCritico(), fonte3));
                p16.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p16));
                
                Paragraph p17 = new Paragraph();
                p17.add(new Chunk("Ações - OK: ", fonte2));
                p17.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getAcoesOk(), fonte3));
                p17.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p17));
                
                Paragraph p18 = new Paragraph();
                p18.add(new Chunk("Ações - ALERTA: ", fonte2));
                p18.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getAcoesAlerta(), fonte3));
                p18.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p18));
                
                Paragraph p19 = new Paragraph();
                p19.add(new Chunk("Ações - CRÍTICO: ", fonte2));
                p19.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getAcoesCritico(), fonte3));
                p19.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p19));
                
                Paragraph p20 = new Paragraph();
                p20.add(new Chunk("Fórmula: ", fonte2));
                p20.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getFormula(), fonte3));
                p20.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p20));
                
                Paragraph p21 = new Paragraph();
                p21.add(new Chunk("Observação: ", fonte2));
                p21.add(new Chunk(listaIndicador.get(i).getProcedimentodeanaliseList().get(0).getObservacao(), fonte3));
                p21.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(new Paragraph(p21));
                
                Paragraph p22 = new Paragraph(" ");
                document.add(p22);
            }
            
            listRelatorios = new ArrayList<>();
            listRelatorios = jpa.getRelatoriosJpa().getListByProjeto(Copia.getProjetoSelecionado().getId());
            
            Paragraph p23 = new Paragraph();
            p23.add(new Chunk("Observação do Plano: ", fonte2));
            p23.add(new Chunk(listRelatorios.get(listRelatorios.size() - 1).getObservacao(), fonte3));
            document.add(new Paragraph(p23));

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
