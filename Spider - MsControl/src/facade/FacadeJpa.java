package facade;

import jpa.AcessaJpaController;
import jpa.AnaliseJpaController;
import jpa.AprovacaoJpaController;
import jpa.ColetaJpaController;
import jpa.ComposicaoJpaController;
import jpa.DefinicaoJpaController;
import jpa.MedidaJpaController;
import jpa.ObjetivodemedicacaoJpaController;
import jpa.ObjetivodequestaoJpaController;
import jpa.ProcedimentodeanaliseJpaController;
import jpa.ProcedimentodecoletaJpaController;
import jpa.extensao.FuncionalidadeJpa;
import jpa.extensao.PerfilJpa;
import jpa.extensao.ProjetoJpa;
import jpa.extensao.UsuarioJpa;
import util.Conexao;

/**
 * Classe criada para servir de interface com os controladores JPA A classe eh
 * singleton
 *
 * @author DAN JHONATAN
 */
public class FacadeJpa {

    private static FacadeJpa instance = null;

    private final AcessaJpaController acessaJpa;
    private final AnaliseJpaController analiseJpa;
    private final AprovacaoJpaController aprovacaoJpa;
    private final ColetaJpaController coletaJpa;
    private final ComposicaoJpaController composicaoJpa;
    private final DefinicaoJpaController definicaoJpa;
    private final FuncionalidadeJpa funcionalidadeJpa;
    private final MedidaJpaController medidaJpa;
    private final ObjetivodemedicacaoJpaController objetivodemedicacao;
    private final ObjetivodequestaoJpaController objetivodequestaoJpa;
    private final PerfilJpa perfilJpa;
    private final ProcedimentodeanaliseJpaController procedimentodeanaliseJpa;
    private final ProcedimentodecoletaJpaController procedimentodecoletaJpa;
    private final ProjetoJpa projetoJpa;
    private final UsuarioJpa usuarioJpa;

    public FacadeJpa() {
        acessaJpa = new AcessaJpaController(Conexao.conectar());
        analiseJpa = new AnaliseJpaController(Conexao.conectar());
        aprovacaoJpa = new AprovacaoJpaController(Conexao.conectar());
        coletaJpa = new ColetaJpaController(Conexao.conectar());
        composicaoJpa = new ComposicaoJpaController(Conexao.conectar());
        definicaoJpa = new DefinicaoJpaController(Conexao.conectar());
        funcionalidadeJpa = new FuncionalidadeJpa();
        medidaJpa = new MedidaJpaController(Conexao.conectar());
        objetivodemedicacao = new ObjetivodemedicacaoJpaController(Conexao.conectar());
        objetivodequestaoJpa = new ObjetivodequestaoJpaController(Conexao.conectar());
        perfilJpa = new PerfilJpa();
        procedimentodeanaliseJpa = new ProcedimentodeanaliseJpaController(Conexao.conectar());
        procedimentodecoletaJpa = new ProcedimentodecoletaJpaController(Conexao.conectar());
        projetoJpa = new ProjetoJpa();
        usuarioJpa = new UsuarioJpa();
    }

    public static FacadeJpa getInstance() {
        if (instance == null)
            instance = new FacadeJpa();
        return instance;
    }

    public AcessaJpaController getAcessaJpa() {
        return acessaJpa;
    }

    public AnaliseJpaController getAnaliseJpa() {
        return analiseJpa;
    }

    public AprovacaoJpaController getAprovacaoJpa() {
        return aprovacaoJpa;
    }

    public ColetaJpaController getColetaJpa() {
        return coletaJpa;
    }

    public ComposicaoJpaController getComposicaoJpa() {
        return composicaoJpa;
    }

    public DefinicaoJpaController getDefinicaoJpa() {
        return definicaoJpa;
    }

    public FuncionalidadeJpa getFuncionalidadeJpa() {
        return funcionalidadeJpa;
    }

    public MedidaJpaController getMedidaJpa() {
        return medidaJpa;
    }

    public ObjetivodemedicacaoJpaController getObjetivodemedicacao() {
        return objetivodemedicacao;
    }

    public ObjetivodequestaoJpaController getObjetivodequestaoJpa() {
        return objetivodequestaoJpa;
    }

    public PerfilJpa getPerfilJpa() {
        return perfilJpa;
    }

    public ProcedimentodeanaliseJpaController getProcedimentodeanaliseJpa() {
        return procedimentodeanaliseJpa;
    }

    public ProcedimentodecoletaJpaController getProcedimentodecoletaJpa() {
        return procedimentodecoletaJpa;
    }

    public ProjetoJpa getProjetoJpa() {
        return projetoJpa;
    }

    public UsuarioJpa getUsuarioJpa() {
        return usuarioJpa;
    }
}
