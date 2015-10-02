package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.imageio.ImageIO;
import model.Projeto;
import model.Resultados;
import model.Valorindicador;
import util.Copia;
import util.PDF.GraficoPDF;

/**
 *
 * @author Géssica
 */
public class Relatorio {

    private CtrlProjeto ctrlProjeto = new CtrlProjeto();
    private CtrlResultados ctrlResultados = new CtrlResultados();
    private Projeto projeto;
    private CtrlValores ctrlValores = new CtrlValores();
    private GraficoPDF graficoPDF = new GraficoPDF();

    public void gerarRelatorio() throws IOException {
        Document document = null;
        OutputStream outputStream = null;

        try {
            //fontes
            Font fonte1 = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
            Font fonte2 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Font fonte3 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
            Font fonte4 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font fonte5 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

            //cria o documento tamanho A4, margens de 2,54cm
            document = new Document(PageSize.A4, 40, 40, 72, 72);
            

            //cria a stream de saída
            outputStream = new FileOutputStream("teste1.pdf");

            //associa a stream de saída ao
            PdfWriter.getInstance(document, outputStream);

            //abre o documento
            document.open();

            //Setar imagem da logo
            Image img = Image.getInstance("src\\image\\spider.png");
            img.setAlignment(Element.ALIGN_CENTER);
            //img.scaleToFit(300, 300);
            document.add(img);

            //Título
            Paragraph p1 = new Paragraph("SPIDER MS-CONTROL", fonte1);
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            //Subtitulo
            Paragraph p2 = new Paragraph("RELATÓRIO DE MEDIÇÃO", fonte2);
            p2.setAlignment(Element.ALIGN_CENTER);
            p2.setSpacingAfter(20);
            document.add(p2);
            
            //espaço de uma linha
            Paragraph p30 = new Paragraph("");
            document.add(p30);

            projeto = new Projeto();
            projeto = ctrlProjeto.buscaProjetoPeloNome(Copia.getProjetoSelecionado().getNome());
            Paragraph p4 = new Paragraph("1. PROJETO: " + projeto.getNome(), fonte3);
            document.add(p4);

            Paragraph p10;
            switch (projeto.getStatus()) {
                case 0:
                    p10 = new Paragraph("STATUS DO PROJETO: Ativo", fonte5);
                    p10.setIndentationLeft(15);
                    document.add(p10);
                    break;
                case 1:
                    p10 = new Paragraph("STATUS DO PROJETO: Inativo", fonte5);
                    p10.setIndentationLeft(15);
                    document.add(p10);
                    break;
                case 2:
                    p10 = new Paragraph("STATUS DO PROJETO: Finalizado", fonte5);
                    p10.setIndentationLeft(15);
                    document.add(p10);
                    break;
            }

            Paragraph p11 = new Paragraph("DESCRIÇÃO DO PROJETO: " + projeto.getDescricao(), fonte5);
            p11.setIndentationLeft(15);
            document.add(p11);
            
            Paragraph p31 = new Paragraph(" ");
            document.add(p31);

            Paragraph p12 = new Paragraph("2. OBJETIVOS DE MEDIÇÃO", fonte3);
            p12.setSpacingAfter(10);
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

            for (int i = 0; i < projeto.getObjetivodemedicaoList().size(); i++) {

                if (!projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().isEmpty()) {

                    for (int j = 0; j < projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().size(); j++) {

                        if (!projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().get(j).getIndicadorList().isEmpty()) {
                            t.addCell(projeto.getObjetivodemedicaoList().get(i).getNome());
                            t.addCell(projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().get(j).getNome());
                            t.addCell(projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().get(j).getIndicadorList().get(0).getNome());
                            t.addCell(String.valueOf(projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().get(j).getIndicadorList().get(0).getPrioridade()));

                        } else {
                            t.addCell(projeto.getObjetivodemedicaoList().get(i).getNome());
                            t.addCell(projeto.getObjetivodemedicaoList().get(i).getObjetivodequestaoList().get(j).getNome());
                            t.addCell(" ");
                            t.addCell(" ");

                        }
                    }

                } else {
                    t.addCell(projeto.getObjetivodemedicaoList().get(i).getNome());
                    t.addCell(" ");
                    t.addCell(" ");
                    t.addCell(" ");
                }
            }

            document.add(t);

            Paragraph p80 = new Paragraph(" ");
            document.add(p80);
            
            Paragraph p13 = new Paragraph("3. RESULTADOS GERADOS", fonte3);
            p13.setSpacingAfter(10);
            document.add(p13);

            List<Resultados> listaResultados = ctrlResultados.getResultadosDoProjeto(Copia.getProjetoSelecionado().getId());
            for (int i = 0; i < listaResultados.size(); i++) {    
                
                document.newPage();
                
                Paragraph p14 = new Paragraph("– " + listaResultados.get(i).getTitulo(), fonte5);
                p14.setSpacingAfter(10);
                p14.setIndentationLeft(15);
                document.add(p14);
                
                Paragraph p15 = new Paragraph("DADOS GERAIS:", fonte4);
                p15.setIndentationLeft(15);
                document.add(p15);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String data = simpleDateFormat.format(listaResultados.get(i).getData());
                Paragraph p16 = new Paragraph("DATA: " + data, fonte5);
                p16.setIndentationLeft(15);
                document.add(p16);
                
                Paragraph p17 = new Paragraph("GERADO POR: " + listaResultados.get(i).getNomeUsuario(), fonte5);
                p17.setIndentationLeft(15);
                document.add(p17);
                
                Paragraph p18 = new Paragraph("INTERPRETAÇÃO: " + listaResultados.get(i).getInterpretacao(), fonte5);
                p18.setIndentationLeft(15);
                document.add(p18);

                Paragraph p19 = new Paragraph("PARTICIPANTES DA INTERPRETAÇÃO: ", fonte5);
                p19.setIndentationLeft(15);
                document.add(p19);
                for (int j = 0; j < listaResultados.get(i).getParticipanteseInteressadosList().size(); j++) {
                    if ("Participante".equals(listaResultados.get(i).getParticipanteseInteressadosList().get(j).getTipo())) {
                        Paragraph p20 = new Paragraph(listaResultados.get(i).getParticipanteseInteressadosList().get(j).getParticipanteEInteressado(), fonte5);
                        p20.setIndentationLeft(30);
                        document.add(p20);
                    }
                }

                Paragraph p21 = new Paragraph("TOMADA DE DECISÃO: " + listaResultados.get(i).getTomadaDeDecisao(), fonte5);
                p21.setSpacingAfter(10);
                p21.setIndentationLeft(15);
                document.add(p21);
                
                Paragraph p37 = new Paragraph("ANÁLISES DOS INDICADORES REFERENCIADOS NO RESULTADO:", fonte4);
                p37.setIndentationLeft(15);
                document.add(p37);

                for (int k = 0; k < listaResultados.get(i).getAnaliseList().size(); k++) {
                    String nome = listaResultados.get(i).getAnaliseList().get(k).getIndicadorid().getNome();
                    Paragraph p22 = new Paragraph("INDICADOR: " + nome, fonte5);
                    p22.setIndentationLeft(15);
                    document.add(p22);

                    String dataDecriacao = simpleDateFormat.format(listaResultados.get(i).getAnaliseList().get(k).getDataCriação());
                    Paragraph p23 = new Paragraph("DATA DA ANÁLISE: " + dataDecriacao, fonte5);
                    p23.setIndentationLeft(15);
                    document.add(p23);

                    int ultimo = listaResultados.get(i).getAnaliseList().get(k).getIndicadorid().getValorindicadorList().size() - 1;
                    String valorMeta = String.valueOf(listaResultados.get(i).getAnaliseList().get(k).getIndicadorid().getValorindicadorList().get(ultimo).getValor());
                    Paragraph p24 = new Paragraph("VALOR DA META DURANTE A ANÁLISE: " + valorMeta + " OK", fonte5);
                    p24.setIndentationLeft(15);
                    document.add(p24);

                    String periodo = simpleDateFormat.format(listaResultados.get(i).getAnaliseList().get(k).getAnaliseDE())
                            + " - " + simpleDateFormat.format(listaResultados.get(i).getAnaliseList().get(k).getAnaliseATE());
                    Paragraph p25 = new Paragraph("PERÍODO ANALISADO: " + periodo, fonte5);
                    p25.setIndentationLeft(15);
                    document.add(p25);

                    //Gráfico
                    Paragraph p = new Paragraph("GRÁFICO ANALISADO: ", fonte5);
                    p.setIndentationLeft(15);
                    document.add(p);
                    
                    Paragraph p40 = new Paragraph(" ");
                    document.add(p40);
                    
                    List<Valorindicador> listaValoresIndicador = ctrlValores.buscarValorIndicadorPorDatas(
                            listaResultados.get(i).getAnaliseList().get(k).getAnaliseDE(),
                            listaResultados.get(i).getAnaliseList().get(k).getAnaliseATE(),
                            listaResultados.get(i).getAnaliseList().get(k).getIndicadorid().getId(),
                            projeto.getId());

                    String tipoGrafico = listaResultados.get(i).getAnaliseList().get(k).getIndicadorid().getProcedimentodeanaliseList().get(0).getGraficoNome();
                    final BufferedImage bufferImage;
                    if ("Pizza".equals(tipoGrafico)) {
                        bufferImage = GraficoPDF.geraGraficoPizzaEmPNG(listaValoresIndicador);
                    } else if ("Barra".equals(tipoGrafico)) {
                        bufferImage = GraficoPDF.geraGraficoBarraEmPNG(listaValoresIndicador);
                    } else {
                        bufferImage = GraficoPDF.geraGraficoLinhaEmPNG(listaValoresIndicador);
                    }
                    File file = new File("src\\image\\grafico.png");
                    ImageIO.write(bufferImage, "png", file);

                    Image imgGrafico = Image.getInstance("src\\image\\grafico.png");
                    imgGrafico.setAlignment(Element.ALIGN_CENTER);
                    document.add(imgGrafico);

                    Paragraph p41 = new Paragraph(" ");
                    document.add(p41);
                    
                    Paragraph p26 = new Paragraph("ANÁLISE: " + listaResultados.get(i).getAnaliseList().get(k).getCriterioDeAnalise(), fonte5);
                    p26.setIndentationLeft(15);
                    document.add(p26);

                    Paragraph p27 = new Paragraph("OBSERVAÇÃO: " + listaResultados.get(i).getAnaliseList().get(k).getObservacao(), fonte5);
                    p27.setIndentationLeft(15);
                    document.add(p27);
                }
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
