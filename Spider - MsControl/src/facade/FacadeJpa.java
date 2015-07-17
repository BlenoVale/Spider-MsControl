package facade;

import jpa.AcessaJpaController;
import jpa.AnaliseJpaController;
import jpa.DatasprocedimentocoletaJpaController;
import jpa.MedidaJpaController;
import jpa.ObjetivodemedicaoJpaController;
import jpa.ObjetivodequestaoJpaController;
import jpa.ProcedimentodecoletaJpaController;
import jpa.RegistroindicadorJpaController;
import jpa.RegistroprocedimentoanaliseJpaController;
import jpa.extensao.ColetaJpa;
import jpa.extensao.EntidadeMedidaJpa;
import jpa.extensao.FuncionalidadeJpa;
import jpa.extensao.IndicadorJpa;
import jpa.extensao.MedidaJpa;
import jpa.extensao.ObjetivoDeMedicaoJpa;
import jpa.extensao.ObjetivoDeQuestaoJpa;
import jpa.extensao.PerfilJpa;
import jpa.extensao.ProjetoJpa;
import jpa.extensao.RegistroObjetivoQuestaoJpa;
import jpa.extensao.RegistroobjetivomedicaoJpa;
import jpa.RegistroprocedimentocoletaJpaController;
import jpa.RegistroresultadosJpaController;
import jpa.ValormedidaJpaController;
import jpa.extensao.MeioComunicacaoJpa;
import jpa.extensao.MeioComunicacaoProcedimentoAnaliseJpa;
import jpa.extensao.PerfilInteressadoJpa;
import jpa.extensao.PerfislInteressadoProcedimentoAnaliseJpa;
import jpa.extensao.ProcedimentoColetaJpa;
import jpa.extensao.ProcedimentoDeAnaliseJpa;
import jpa.extensao.RegistroDataComunicacaoJpa;
import jpa.extensao.UsuarioJpa;
import jpa.extensao.RegistroMedidaJpa;
import jpa.extensao.RegistroProcedimentoAnalise;
import jpa.extensao.RegistroProcedimentoColetaJpa;
import jpa.extensao.ResultadosJpa;
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
    //private final ColetaJpaController coletaJpa;
    private final FuncionalidadeJpa funcionalidadeJpa;
    private final MedidaJpaController medidaJpaController;
    private final MedidaJpa medidaJpa; //@paulo
    private final ObjetivodemedicaoJpaController objetivodemedicacao;
    private final ObjetivodequestaoJpaController objetivodequestaoJpa;
    private final PerfilJpa perfilJpa;
    private final ProcedimentoDeAnaliseJpa procedimentodeanaliseJpa;
    private final ProcedimentodecoletaJpaController ProcedimentodecoletaJpaController;
    private final ProjetoJpa projetoJpa;
    private final UsuarioJpa usuarioJpa;
    private final ObjetivoDeMedicaoJpa objetivoDeMedicaoJpa;
    private final ObjetivoDeQuestaoJpa objetivoDeQuestaoJpa;
    private final RegistroMedidaJpa registroMedidaJpa;
    private final RegistroobjetivomedicaoJpa registroObjetivoMedicaoJpa;
    private final RegistroObjetivoQuestaoJpa registroObjetivoQuestaoJpa;
    private final IndicadorJpa indicadorJpa;
    private final RegistroindicadorJpaController registroindicadorJpa;
    private final RegistroprocedimentocoletaJpaController registroprocedimentocoletaJpaController;
    private final RegistroProcedimentoColetaJpa registroProcedimentoColeta;
    private final ProcedimentoColetaJpa procedimentoColetaJpa;
    private final EntidadeMedidaJpa entidadeMedidaJpa;
    private final ColetaJpa coletaJpa;
    private final DatasprocedimentocoletaJpaController datasprocedimentocoletaJpaController;
    private final ResultadosJpa resultadosJpa;
    private final RegistroresultadosJpaController registroResultadosJpa;
    private final ValormedidaJpaController valormedidaJpaController;
    private final MeioComunicacaoJpa meioComunicacaoJpa;
    private final PerfilInteressadoJpa perfilInteressadoJpa;
    private final RegistroprocedimentoanaliseJpaController registroprocedimentoanaliseJpaController;
    private final RegistroProcedimentoAnalise registroProcedimentoAnalise;
    private final RegistroDataComunicacaoJpa registroDataComunicacaoJpa;
    private final MeioComunicacaoProcedimentoAnaliseJpa meioComunicacaoProcedimentoAnaliseJpa;
    private final PerfislInteressadoProcedimentoAnaliseJpa perfislInteressadoProcedimentoAnaliseJpa;

    private FacadeJpa() {
        acessaJpa = new AcessaJpaController(Conexao.conectar());
        analiseJpa = new AnaliseJpaController(Conexao.conectar());
        coletaJpa = new ColetaJpa();
        funcionalidadeJpa = new FuncionalidadeJpa();
        medidaJpaController = new MedidaJpaController(Conexao.conectar());
        medidaJpa = new MedidaJpa();
        objetivodemedicacao = new ObjetivodemedicaoJpaController(Conexao.conectar());
        objetivodequestaoJpa = new ObjetivodequestaoJpaController(Conexao.conectar());
        perfilJpa = new PerfilJpa();
        procedimentodeanaliseJpa = new ProcedimentoDeAnaliseJpa();
        ProcedimentodecoletaJpaController = new ProcedimentodecoletaJpaController(Conexao.conectar());
        procedimentoColetaJpa = new ProcedimentoColetaJpa();
        projetoJpa = new ProjetoJpa();
        usuarioJpa = new UsuarioJpa();
        objetivoDeMedicaoJpa = new ObjetivoDeMedicaoJpa();
        objetivoDeQuestaoJpa = new ObjetivoDeQuestaoJpa();
        registroMedidaJpa = new RegistroMedidaJpa();
        registroObjetivoMedicaoJpa = new RegistroobjetivomedicaoJpa();
        registroObjetivoQuestaoJpa = new RegistroObjetivoQuestaoJpa();
        indicadorJpa = new IndicadorJpa();
        registroindicadorJpa = new RegistroindicadorJpaController(Conexao.conectar());
        registroprocedimentocoletaJpaController = new RegistroprocedimentocoletaJpaController(Conexao.conectar());
        registroProcedimentoColeta = new RegistroProcedimentoColetaJpa();
        entidadeMedidaJpa = new EntidadeMedidaJpa();
        datasprocedimentocoletaJpaController = new DatasprocedimentocoletaJpaController(Conexao.conectar());
        resultadosJpa = new ResultadosJpa();
        registroResultadosJpa = new RegistroresultadosJpaController(Conexao.conectar());
        valormedidaJpaController = new ValormedidaJpaController(Conexao.conectar());
        meioComunicacaoJpa = new MeioComunicacaoJpa();
        perfilInteressadoJpa = new PerfilInteressadoJpa();
        registroprocedimentoanaliseJpaController = new RegistroprocedimentoanaliseJpaController(Conexao.conectar());
        registroProcedimentoAnalise = new RegistroProcedimentoAnalise();
        registroDataComunicacaoJpa = new RegistroDataComunicacaoJpa();
        meioComunicacaoProcedimentoAnaliseJpa = new MeioComunicacaoProcedimentoAnaliseJpa();
        perfislInteressadoProcedimentoAnaliseJpa = new PerfislInteressadoProcedimentoAnaliseJpa();
    }

    public static FacadeJpa getInstance() {
        if (instance == null) {
            instance = new FacadeJpa();
        }
        return instance;
    }

    public AcessaJpaController getAcessaJpa() {
        return acessaJpa;
    }

    public MedidaJpa getMedidaJpa() {
        return medidaJpa;
    }

    public RegistroMedidaJpa getRegistroMedidaJpa() {
        return registroMedidaJpa;
    }

    public AnaliseJpaController getAnaliseJpa() {
        return analiseJpa;
    }

    public ColetaJpa getColetaJpa() {
        return coletaJpa;
    }

//    public RegistrocoletaJpaController getRegistroColeta() {
//        return registroColetaJpa;
//    }
//    }
    public FuncionalidadeJpa getFuncionalidadeJpa() {
        return funcionalidadeJpa;
    }

    public MedidaJpaController getMedidaJpaController() {
        return medidaJpaController;
    }

    public ObjetivodemedicaoJpaController getObjetivodemedicao() {
        return objetivodemedicacao;
    }

    public ObjetivodequestaoJpaController getObjetivodequestaoJpa() {
        return objetivodequestaoJpa;
    }

    public PerfilJpa getPerfilJpa() {
        return perfilJpa;
    }

    public ProcedimentoDeAnaliseJpa getProcedimentodeanaliseJpa() {
        return procedimentodeanaliseJpa;
    }

    public ProcedimentodecoletaJpaController getProcedimentodecoletaJpaController() {
        return ProcedimentodecoletaJpaController;
    }

    public ProcedimentoColetaJpa getProcedimentoColetaJpa() {
        return procedimentoColetaJpa;
    }

    public RegistroprocedimentocoletaJpaController getRegistroprocedimentocoletaJpaController() {
        return registroprocedimentocoletaJpaController;
    }

    public RegistroprocedimentoanaliseJpaController getRegistroprocedimentoanaliseJpaController() {
        return registroprocedimentoanaliseJpaController;
    }

    public ProjetoJpa getProjetoJpa() {
        return projetoJpa;
    }

    public UsuarioJpa getUsuarioJpa() {
        return usuarioJpa;
    }

    public ObjetivoDeMedicaoJpa getObjetivoDeMedicaoJpa() {
        return objetivoDeMedicaoJpa;
    }

    public ObjetivoDeQuestaoJpa getObjetivoDeQuestaoJpa() {
        return objetivoDeQuestaoJpa;
    }

    public RegistroobjetivomedicaoJpa getRegistroObjetivoMedicaoJpa() {
        return registroObjetivoMedicaoJpa;
    }

    public RegistroObjetivoQuestaoJpa getRegistroObjetivoQuestaoJpa() {
        return registroObjetivoQuestaoJpa;
    }

    public IndicadorJpa getIndicadorJpa() {
        return indicadorJpa;
    }

    public RegistroindicadorJpaController getRegistroIndicador() {
        return registroindicadorJpa;
    }

    public RegistroProcedimentoColetaJpa getRegistroProcedimentoColeta() {

        return registroProcedimentoColeta;
    }

    public EntidadeMedidaJpa getEntidadeMedidaJpa() {
        return entidadeMedidaJpa;
    }

    public DatasprocedimentocoletaJpaController getdDatasprocedimentocoletaJpaController() {
        return datasprocedimentocoletaJpaController;
    }

    public ResultadosJpa getResultadosJpa() {
        return resultadosJpa;
    }

    public RegistroresultadosJpaController getRegistroresultados() {
        return registroResultadosJpa;
    }

    public ValormedidaJpaController getValorMedidaJpa() {
        return valormedidaJpaController;
    }

    public MeioComunicacaoJpa getMeioComunicacaoJpa() {
        return meioComunicacaoJpa;
    }

    public PerfilInteressadoJpa getPerfilInteressadoJpa() {
        return perfilInteressadoJpa;
    }

    public RegistroProcedimentoAnalise getRegistroProcedimentoAnalise() {
        return registroProcedimentoAnalise;
    }

    public RegistroDataComunicacaoJpa getRegistroDataComunicacaoJpa() {
        return registroDataComunicacaoJpa;
    }

    public MeioComunicacaoProcedimentoAnaliseJpa getMeioComunicacaoProcedimentoAnaliseJpa() {
        return meioComunicacaoProcedimentoAnaliseJpa;
    }

    public PerfislInteressadoProcedimentoAnaliseJpa getPerfislInteressadoProcedimentoAnaliseJpa() {
        return perfislInteressadoProcedimentoAnaliseJpa;
    }
}
