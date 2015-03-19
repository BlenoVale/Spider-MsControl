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
}