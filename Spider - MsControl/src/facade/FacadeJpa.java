package facade;

import jpa.AcessaJpaController;
import jpa.MedidaJpaController;
import jpa.ObjetivodemedicaoJpaController;
import jpa.ObjetivodequestaoJpaController;
import jpa.ParticipanteseInteressadosJpaController;
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
import jpa.RelatoriosJpaController;
import jpa.extensao.AnaliseJpa;
import jpa.extensao.DatasProcedimentoColetaJpa;
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
import jpa.extensao.RelatoriosJpa;
import jpa.extensao.ResultadosJpa;
import jpa.extensao.ValorIndicadorJpa;
import jpa.extensao.ValorMedidaJpa;
import util.Conexao;
import util.Texto;

/**
 * Classe criada para servir de interface com os controladores JPA A classe eh
 * singleton
 *
 * @author DAN JHONATAN
 */
public class FacadeJpa {

    private static FacadeJpa instance = null;

    public static void setInstance(FacadeJpa instance) {
        FacadeJpa.instance = instance;
    }

    private AcessaJpaController acessaJpa;
    private AnaliseJpa analiseJpa;
    private FuncionalidadeJpa funcionalidadeJpa;
    private MedidaJpaController medidaJpaController;
    private MedidaJpa medidaJpa; //@paulo
    private ObjetivodemedicaoJpaController objetivodemedicacao;
    private ObjetivodequestaoJpaController objetivodequestaoJpa;
    private PerfilJpa perfilJpa;
    private ProcedimentoDeAnaliseJpa procedimentodeanaliseJpa;
    private ProcedimentodecoletaJpaController ProcedimentodecoletaJpaController;
    private ProjetoJpa projetoJpa;
    private UsuarioJpa usuarioJpa;
    private ObjetivoDeMedicaoJpa objetivoDeMedicaoJpa;
    private ObjetivoDeQuestaoJpa objetivoDeQuestaoJpa;
    private RegistroMedidaJpa registroMedidaJpa;
    private RegistroobjetivomedicaoJpa registroObjetivoMedicaoJpa;
    private RegistroObjetivoQuestaoJpa registroObjetivoQuestaoJpa;
    private IndicadorJpa indicadorJpa;
    private RegistroindicadorJpaController registroindicadorJpa;
    private RegistroprocedimentocoletaJpaController registroprocedimentocoletaJpaController;
    private RegistroProcedimentoColetaJpa registroProcedimentoColeta;
    private ProcedimentoColetaJpa procedimentoColetaJpa;
    private EntidadeMedidaJpa entidadeMedidaJpa;
    private ColetaJpa coletaJpa;
    private DatasProcedimentoColetaJpa datasProcedimentoColetaJpa;
    private ResultadosJpa resultadosJpa;
    private MeioComunicacaoJpa meioComunicacaoJpa;
    private PerfilInteressadoJpa perfilInteressadoJpa;
    private RegistroprocedimentoanaliseJpaController registroprocedimentoanaliseJpaController;
    private RegistroProcedimentoAnalise registroProcedimentoAnalise;
    private RegistroDataComunicacaoJpa registroDataComunicacaoJpa;
    private MeioComunicacaoProcedimentoAnaliseJpa meioComunicacaoProcedimentoAnaliseJpa;
    private PerfislInteressadoProcedimentoAnaliseJpa perfislInteressadoProcedimentoAnaliseJpa;
    private ValorMedidaJpa valorMedidaJpa;
    private ValorIndicadorJpa valorIndicadorJpa;
    private RelatoriosJpa relatoriosJpa;
    private RelatoriosJpaController relatoriosJpaController;
    private ParticipanteseInteressadosJpaController participanteseInteressadosJpaController;

    private FacadeJpa() {

    }

    public static FacadeJpa getInstance() {
        if (instance == null) {
            instance = new FacadeJpa();
        }
        return instance;
    }

    public AcessaJpaController getAcessaJpa() {
        acessaJpa = new AcessaJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        return acessaJpa;
    }

    public MedidaJpa getMedidaJpa() {
        medidaJpa = new MedidaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return medidaJpa;
    }

    public RegistroMedidaJpa getRegistroMedidaJpa() {
        registroMedidaJpa = new RegistroMedidaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return registroMedidaJpa;
    }

    public AnaliseJpa getAnaliseJpa() {
        analiseJpa = new AnaliseJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return analiseJpa;
    }

    public ColetaJpa getColetaJpa() {
        coletaJpa = new ColetaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return coletaJpa;
    }

    public FuncionalidadeJpa getFuncionalidadeJpa() {
        funcionalidadeJpa = new FuncionalidadeJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return funcionalidadeJpa;
    }

    public MedidaJpaController getMedidaJpaController() {
        medidaJpaController = new MedidaJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        return medidaJpaController;
    }

    public ObjetivodemedicaoJpaController getObjetivodemedicao() {
        objetivodemedicacao = new ObjetivodemedicaoJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        return objetivodemedicacao;
    }

    public ObjetivodequestaoJpaController getObjetivodequestaoJpa() {
        objetivodequestaoJpa = new ObjetivodequestaoJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        return objetivodequestaoJpa;
    }

    public PerfilJpa getPerfilJpa() {
        perfilJpa = new PerfilJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return perfilJpa;
    }

    public ProcedimentoDeAnaliseJpa getProcedimentodeanaliseJpa() {
        procedimentodeanaliseJpa = new ProcedimentoDeAnaliseJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return procedimentodeanaliseJpa;
    }

    public ProcedimentodecoletaJpaController getProcedimentodecoletaJpaController() {
        ProcedimentodecoletaJpaController = new ProcedimentodecoletaJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        return ProcedimentodecoletaJpaController;
    }

    public ProcedimentoColetaJpa getProcedimentoColetaJpa() {
        procedimentoColetaJpa = new ProcedimentoColetaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return procedimentoColetaJpa;
    }

    public RegistroprocedimentocoletaJpaController getRegistroprocedimentocoletaJpaController() {
        registroprocedimentocoletaJpaController = new RegistroprocedimentocoletaJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        return registroprocedimentocoletaJpaController;
    }

    public RegistroprocedimentoanaliseJpaController getRegistroprocedimentoanaliseJpaController() {
        registroprocedimentoanaliseJpaController = new RegistroprocedimentoanaliseJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        return registroprocedimentoanaliseJpaController;
    }

    public ProjetoJpa getProjetoJpa() {
        projetoJpa = new ProjetoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return projetoJpa;
    }

    public UsuarioJpa getUsuarioJpa() {
        usuarioJpa = new UsuarioJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return usuarioJpa;
    }

    public ObjetivoDeMedicaoJpa getObjetivoDeMedicaoJpa() {
        objetivoDeMedicaoJpa = new ObjetivoDeMedicaoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return objetivoDeMedicaoJpa;
    }

    public ObjetivoDeQuestaoJpa getObjetivoDeQuestaoJpa() {
        objetivoDeQuestaoJpa = new ObjetivoDeQuestaoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return objetivoDeQuestaoJpa;
    }

    public RegistroobjetivomedicaoJpa getRegistroObjetivoMedicaoJpa() {
        registroObjetivoMedicaoJpa = new RegistroobjetivomedicaoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return registroObjetivoMedicaoJpa;
    }

    public RegistroObjetivoQuestaoJpa getRegistroObjetivoQuestaoJpa() {
        registroObjetivoQuestaoJpa = new RegistroObjetivoQuestaoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return registroObjetivoQuestaoJpa;
    }

    public IndicadorJpa getIndicadorJpa() {
        indicadorJpa = new IndicadorJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return indicadorJpa;
    }

    public RegistroindicadorJpaController getRegistroIndicador() {
        registroindicadorJpa = new RegistroindicadorJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        return registroindicadorJpa;
    }

    public RegistroProcedimentoColetaJpa getRegistroProcedimentoColeta() {
        registroProcedimentoColeta = new RegistroProcedimentoColetaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return registroProcedimentoColeta;
    }

    public EntidadeMedidaJpa getEntidadeMedidaJpa() {
        entidadeMedidaJpa = new EntidadeMedidaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return entidadeMedidaJpa;
    }

    public DatasProcedimentoColetaJpa getDatasprocedimentoColetaJpa() {
        datasProcedimentoColetaJpa = new DatasProcedimentoColetaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return datasProcedimentoColetaJpa;
    }

    public ResultadosJpa getResultadosJpa() {
        resultadosJpa = new ResultadosJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return resultadosJpa;
    }

    public MeioComunicacaoJpa getMeioComunicacaoJpa() {
        meioComunicacaoJpa = new MeioComunicacaoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return meioComunicacaoJpa;
    }

    public PerfilInteressadoJpa getPerfilInteressadoJpa() {
        perfilInteressadoJpa = new PerfilInteressadoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return perfilInteressadoJpa;
    }

    public RegistroProcedimentoAnalise getRegistroProcedimentoAnalise() {
        registroProcedimentoAnalise = new RegistroProcedimentoAnalise(Conexao.URLdoBanco(Texto.lerTXT()));
        return registroProcedimentoAnalise;
    }

    public RegistroDataComunicacaoJpa getRegistroDataComunicacaoJpa() {
        registroDataComunicacaoJpa = new RegistroDataComunicacaoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return registroDataComunicacaoJpa;
    }

    public MeioComunicacaoProcedimentoAnaliseJpa getMeioComunicacaoProcedimentoAnaliseJpa() {
        meioComunicacaoProcedimentoAnaliseJpa = new MeioComunicacaoProcedimentoAnaliseJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return meioComunicacaoProcedimentoAnaliseJpa;
    }

    public PerfislInteressadoProcedimentoAnaliseJpa getPerfislInteressadoProcedimentoAnaliseJpa() {
        perfislInteressadoProcedimentoAnaliseJpa = new PerfislInteressadoProcedimentoAnaliseJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return perfislInteressadoProcedimentoAnaliseJpa;
    }

    public ValorMedidaJpa getValorMedidaJpa() {
        valorMedidaJpa = new ValorMedidaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return valorMedidaJpa;
    }

    public ValorIndicadorJpa getValorIndicadorJpa() {
        valorIndicadorJpa = new ValorIndicadorJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return valorIndicadorJpa;
    }

    public RelatoriosJpa getRelatoriosJpa() {
        relatoriosJpa = new RelatoriosJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        return relatoriosJpa;
    }

    public RelatoriosJpaController getRelatoriosJpaController() {
        relatoriosJpaController = new RelatoriosJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        return relatoriosJpaController;
    }

    public ParticipanteseInteressadosJpaController getParticipanteseInteressadosJpa() {
        participanteseInteressadosJpaController = new ParticipanteseInteressadosJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        return participanteseInteressadosJpaController;
    }
}
