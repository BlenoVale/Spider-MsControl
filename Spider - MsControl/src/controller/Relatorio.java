package controller;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
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
import model.Relatorios;
import model.Resultados;
import model.Valorindicador;
import util.Copia;

/**
 *
 * @author Géssica
 */
public class Relatorio {

    private CtrlProjeto ctrlProjeto = new CtrlProjeto();
    private CtrlResultados ctrlResultados = new CtrlResultados();
    private CtrlRelatorios ctrlRelatorios = new CtrlRelatorios();
    private Relatorios relatorios;
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
            outputStream = new FileOutputStream("RelatórioDeMedição.pdf");

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
                    p10 = new Paragraph();
                    p10.add(new Chunk("Status do Projeto: " , fonte4));
                    p10.add(new Chunk("Ativo", fonte5));
                    p10.setIndentationLeft(15);
                    document.add(new Paragraph(p10));
                    break;
                case 1:
                    p10 = new Paragraph();
                    p10.add(new Chunk("Status do Projeto: " , fonte4));
                    p10.add(new Chunk("Inativo", fonte5));
                    p10.setIndentationLeft(15);
                    document.add(new Paragraph(p10));
                    break;
                case 2:
                    p10 = new Paragraph();
                    p10.add(new Chunk("Status do Projeto: " , fonte4));
                    p10.add(new Chunk("Finalizado", fonte5));
                    p10.setIndentationLeft(15);
                    document.add(new Paragraph(p10));
                    break;
            }

            Paragraph p11 = new Paragraph();
            p11.add(new Chunk("Descrição do Projeto: " , fonte4));
            p11.add(new Chunk(projeto.getDescricao(), fonte5));
            p11.setIndentationLeft(15);
            document.add(new Paragraph(p11));
            
            
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
                
                //document.newPage();
                
                Paragraph p14 = new Paragraph("3." + (i+1) + " " + listaResultados.get(i).getTitulo(), fonte4);
                p14.setSpacingAfter(10);
                p14.setIndentationLeft(15);
                document.add(p14);
                
                Paragraph p15 = new Paragraph("3." + (i+1) + ".1 DADOS GERAIS:", fonte4);
                p15.setIndentationLeft(15);
                document.add(p15);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String data = simpleDateFormat.format(listaResultados.get(i).getData());
                Paragraph p16 = new Paragraph();
                p16.add(new Chunk("Data: " , fonte4));
                p16.add(new Chunk(data, fonte5));
                p16.setIndentationLeft(15);
                document.add(new Paragraph(p16));
                
                Paragraph p17 = new Paragraph();
                p17.add(new Chunk("Gerado por: " , fonte4));
                p17.add(new Chunk(listaResultados.get(i).getNomeUsuario(), fonte5));
                p17.setIndentationLeft(15);
                document.add(new Paragraph(p17));
                
                Paragraph p18 = new Paragraph();
                p18.add(new Chunk("Interpretação: " , fonte4));
                p18.add(new Chunk(listaResultados.get(i).getInterpretacao(), fonte5));
                p18.setIndentationLeft(15);
                document.add(new Paragraph(p18));

                Paragraph p19 = new Paragraph();
                p19.add(new Chunk("Participantes da Interpretação: " , fonte4));
                p19.setIndentationLeft(15);
                for (int j = 0; j < listaResultados.get(i).getParticipanteseInteressadosList().size(); j++) {
                    if ("Participante".equals(listaResultados.get(i).getParticipanteseInteressadosList().get(j).getTipo())) {
                        p19.add(new Chunk(listaResultados.get(i).getParticipanteseInteressadosList().get(j).getParticipanteEInteressado() + " / ", fonte5));
                    }
                }
                document.add(new Paragraph(p19));

                Paragraph p21 = new Paragraph();
                p21.add(new Chunk("Tomada de Decisão: " , fonte4));
                p21.add(new Chunk(listaResultados.get(i).getTomadaDeDecisao(), fonte5));
                p21.setSpacingAfter(10);
                p21.setIndentationLeft(15);
                document.add(new Paragraph(p21));
                
                Paragraph p37 = new Paragraph("3." + (i+1) + ".2 ANÁLISES DOS INDICADORES REFERENCIADOS NO RESULTADO:", fonte4);
                p37.setIndentationLeft(15);
                document.add(p37);

                for (int k = 0; k < listaResultados.get(i).getAnaliseList().size(); k++) {
                    String nome = listaResultados.get(i).getAnaliseList().get(k).getIndicadorid().getNome();
                    Paragraph p22 = new Paragraph();
                    p22.add(new Chunk("Indicador: " , fonte4));
                    p22.add(new Chunk(nome, fonte5));
                    p22.setIndentationLeft(15);
                    document.add(new Paragraph(p22));

                    String dataDecriacao = simpleDateFormat.format(listaResultados.get(i).getAnaliseList().get(k).getDataCriação());
                    Paragraph p23 = new Paragraph();
                    p23.add(new Chunk("Data da Análise: ", fonte4));
                    p23.add(new Chunk(dataDecriacao, fonte5));
                    p23.setIndentationLeft(15);
                    document.add(new Paragraph(p23));

                    int ultimo = listaResultados.get(i).getAnaliseList().get(k).getIndicadorid().getValorindicadorList().size() - 1;
                    String valorMeta = String.valueOf(listaResultados.get(i).getAnaliseList().get(k).getIndicadorid().getValorindicadorList().get(ultimo).getValor());
                    Paragraph p24 = new Paragraph();
                    p24.add(new Chunk("Valor da Meta Durante a Análise: ", fonte4));
                    p24.add(new Chunk(valorMeta + "- OK", fonte5));
                    p24.setIndentationLeft(15);
                    document.add(new Paragraph(p24));

                    String periodo = simpleDateFormat.format(listaResultados.get(i).getAnaliseList().get(k).getAnaliseDE())
                            + " - " + simpleDateFormat.format(listaResultados.get(i).getAnaliseList().get(k).getAnaliseATE());
                    Paragraph p25 = new Paragraph();
                    p25.add(new Chunk("Período Analisado: ", fonte4));
                    p25.add(new Chunk(periodo, fonte5));
                    p25.setIndentationLeft(15);
                    document.add(new Paragraph(p25));

                    //Gráfico
                    Paragraph p = new Paragraph("Gráfico Analisado: ", fonte4);
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
                    imgGrafico.scaleToFit(300, 300);
                    document.add(imgGrafico);

                    Paragraph p41 = new Paragraph(" ");
                    document.add(p41);
                    
                    Paragraph p26 = new Paragraph();
                    p26.add(new Chunk("Análise: ", fonte4));
                    p26.add(new Chunk(listaResultados.get(i).getAnaliseList().get(k).getCriterioDeAnalise(), fonte5));
                    p26.setIndentationLeft(15);
                    document.add(new Paragraph(p26));

                    Paragraph p27 = new Paragraph();
                    p27.add(new Chunk("Observação: ", fonte4));
                    p27.add(new Chunk(listaResultados.get(i).getAnaliseList().get(k).getObservacao(), fonte5));
                    p27.setIndentationLeft(15);
                    document.add(new Paragraph(p27));
                    
                    Paragraph p49 = new Paragraph(" ");
                    document.add(p49);
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
