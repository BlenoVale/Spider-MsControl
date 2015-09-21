package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import model.Projeto;
import model.Resultados;
import util.Copia;

/**
 *
 * @author FACOMP
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

            //cria o documento tamanho A4, margens de 2,54cm
            document = new Document(PageSize.A4, 72, 72, 72, 72);

            //cria a stream de saída
            outputStream = new FileOutputStream("teste1.pdf");

            //associa a stream de saída ao
            PdfWriter.getInstance(document, outputStream);

            //abre o documento
            document.open();

            Image img = Image.getInstance("C:\\Users\\FACOMP\\Documents\\NetBeansProjects\\gitHub\\Spider-MsControl\\Spider - MsControl\\src\\image\\spider.png");
            img.setAlignment(Element.ALIGN_CENTER);
            document.add(img);

            projeto = new Projeto();
            projeto = ctrlProjeto.buscaProjetoPeloNome(Copia.getProjetoSelecionado().getNome());
            Paragraph p = new Paragraph(projeto.getNome(), fonte1);
            document.add(p);
            p.setAlignment(Element.ALIGN_CENTER);//alinhamento

            Paragraph p1 = new Paragraph("Relatório de Medição", fonte2);
            document.add(p1);
            p1.setAlignment(Element.ALIGN_CENTER);

            List<Resultados> listaResultados = ctrlResultados.getResultadosDoProjeto(Copia.getProjetoSelecionado().getId());
            for (int i = 0; i < listaResultados.size(); i++) {
                Paragraph p2 = new Paragraph(listaResultados.get(i).getTitulo(), fonte2);
                document.add(p2);
                p2.setAlignment(Element.ALIGN_CENTER);
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
