package util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Géssica
 */
public class Pdf {
    public static void main(String[] args) throws Exception {
        // Cria um novo documento com tamanho e margens definidas pelo
        // usuário
        // new Document(tamanho da página, margem esquerda, margem direita,
        // margem topo, margem rodapé);
        Document doc = new Document(PageSize.A4, 10, 10, 10, 10);
 
        // Criando o arquivo de saída.
        OutputStream os = new FileOutputStream("SpiderMsControl.pdf");
 
        // Associando o doc ao arquivo de saída.
        PdfWriter.getInstance(doc, os);
 
        // Abrindo o documento para a edição
        doc.open();
 
                //Não se esqueça de arrumar o caminho para a imagem de sua preferência
        Image logo = Image
                .getInstance("spider.png");
 
                //Alinhamento p/ a esquerda <<<
        logo.setAlignment(Image.ALIGN_LEFT);
 
        doc.add(logo);
         
        // Definindo uma fonte, com tamanho 20 e negrito
        Font f = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
        Font f1 = new Font(Font.FontFamily.COURIER, 12);
 
        // Adicionando um parágrafo ao PDF, com a fonte definida acima
        Paragraph p = new Paragraph("Relatório Spider Ms-Control", f);
        
        Paragraph p1 = new Paragraph("A Spider-MPlan é uma ferramenta para apoio ao processo "
                + "de Medição que permite a definição, coleta, análise e acompanhamento da medida. "
                + "Dessa forma, essa ferramenta apresenta funcionalidades que estão relacionadas "
                + "com a medição de software, e essas funcionalidades serão abordadas nesse documento.", f1);
        
        Paragraph p2 = new Paragraph("Além de apresentar as funcionalidades da ferramenta, esse documento "
                + "também apresenta: quais os termos usados na ferramenta e os seus respectivos significados; "
                + "um guia de como instalar e configurar a ferramenta; e uma descrição do propósito e dos "
                + "benefícios da ferramenta. Esse documento não deverá apresentar nenhuma descrição dos "
                + "componentes internos da ferramenta, e sim apenas dos componentes que estão "
                + "acessíveis aos usuários finais.", f1);
 
        // Setando o alinhamento p/ o centro
        p.setAlignment(Paragraph.ALIGN_CENTER);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        p2.setAlignment(Paragraph.ALIGN_LEFT);
 
        // Definindo
        p.setSpacingAfter(50);
        p1.setSpacingAfter(10);
        p2.setSpacingAfter(50);
        doc.add(p);
        doc.add(p1);
        doc.add(p2);
        
        // Criando uma tabela com 3 colunas
        PdfPTable table = new PdfPTable(3);
        // Título para a tabela
        Paragraph tableHeader = new Paragraph("Tabela simples");
 
        PdfPCell header = new PdfPCell(tableHeader);
        // Definindo que o header vai ocupar as 3 colunas
        header.setColspan(3);
        // Definindo alinhamento do header
        header.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
        // Adicionando o header à tabela
        table.addCell(header);
 
        List<String> list = new ArrayList<String>();
 
        list.add("Testando linha 1, coluna 1");
        list.add("Testando linha 1, coluna 2");
        list.add("Testando linha 1, coluna 3");
        list.add("Testando linha 2, coluna 1");
        list.add("Testando linha 2, coluna 2");
        list.add("Testando linha 2, coluna 3");
        list.add("Testando linha 3, coluna 1");
        list.add("Testando linha 3, coluna 2");
        list.add("Testando linha 3, coluna 3");
 
        for (String s : list) {
            table.addCell(s);
        }
 
        doc.add(table);
 
        doc.close();
    }
}