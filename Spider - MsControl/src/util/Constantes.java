package util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bleno Vale
 */
public class Constantes {

    // TIPOS DE REGISTROS
    public static final int CADASTRO = 0;
    public static final int EDICAO = 1;
    public static final int ANALISE_DE_APROVACAO = 2;

    // STATUS DE UM PROJETO
    public static final int ATIVO = 0;
    public static final int INATIVO = 1;
    public static final int FINALIZADO = 2;
    
    // APROVACAO DE UM INDICADOR
    public static final int APROVADO = 0;
    public static final int NAO_APROVADO = 1;
    public static final int NAO_ANALISADO = 2;
    
    // LISTA DE GRÁFICOS    
    public static List<String> preencherListaGraficos(){
        List<String> listaGraficos = new ArrayList<>();
        
        listaGraficos.add("Pizza");
        listaGraficos.add("Linha");
        
        return listaGraficos;
    }
    
    //LISTA DE PERIODICIDADES
    public static List<String> preencherListaPeriodicidade(){
        List<String> listaPeriodicidade = new ArrayList<>();
        
        listaPeriodicidade.add("Diário");
        listaPeriodicidade.add("Semanal");
        listaPeriodicidade.add("Mensal");
        listaPeriodicidade.add("Bimestral");
        listaPeriodicidade.add("Trimestral");
        listaPeriodicidade.add("Semestral");
        listaPeriodicidade.add("Anual");
        
        return listaPeriodicidade;
    }
    
    //LISTA DE MEIO
    public static List<String> preencherListaMeio(){
        List<String> listaMeio = new ArrayList<>();
        
        listaMeio.add("E-mail");
        listaMeio.add("Telefone");
        
        return listaMeio;
    } 
    
    //LISTA DE PERFIS
    public static List<String> preencherListaPerfis(){
        List<String> listaPerfis = new ArrayList<>();
        
        listaPerfis.add("Administrador");
        listaPerfis.add("Alta Administração");
        listaPerfis.add("Analista");
        listaPerfis.add("Gerente");
        
        return listaPerfis;
    } 
}