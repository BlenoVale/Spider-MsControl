package teste;

import facade.FacadeJpa;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author Dan
 */
public class Teste {

    public static void main(String[] args) {

        //
//        List<Funcionalidade> lista;
//        FuncionalidadeJpaController fucionalidadeJpaController = new FuncionalidadeJpaController(Conexao.conectar());
//
//        lista  = fucionalidadeJpaController.findFuncionalidadeEntities();
//
//        for (int i = 0;i< lista.size (); i++) {
//            System.out.println("Funcionalidade " + i + ": " + lista.get(i).getNome());
//        }
//        FacadeJpa jpa = FacadeJpa.getInstance();
//        Projeto projeto = new Projeto();
//        projeto.setNome("PROJETO 34");
//
//        projeto.setDataFim(new Date());
//        projeto.setDataInicio(new Date());
//        projeto.setStatus(0);
//
//        try {
//            jpa.getProjetoJpa().create(projeto);
//            System.out.println("Funfou");
//        } catch (Exception e) {
//            System.out.println("Não funfou");
//        }
//
//        FacadeJpa jpa2 = FacadeJpa.getInstance();
//        Projeto projeto2 = new Projeto();
//        projeto.setNome("PROJETO 35");
//
//        projeto.setDataFim(new Date());
//        projeto.setDataInicio(new Date());
//        projeto.setStatus(0);
//
//        try {
//            jpa2.getProjetoJpa().create(projeto);
//            System.out.println("Funfou");
//        } catch (Exception e) {
//            System.out.println("Não funfou");
//        }
      
    }
    public static String getData() {
        SimpleDateFormat formatador = new SimpleDateFormat("yy/MM/dd");
        Date data = new Date();

        String dataFormatada = formatador.format(data);
        return dataFormatada;
    }

}
