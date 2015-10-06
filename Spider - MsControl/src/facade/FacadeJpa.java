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

    private final AcessaJpaController acessaJpa;
    private final AnaliseJpa analiseJpa;
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
    private final DatasProcedimentoColetaJpa datasProcedimentoColetaJpa;
    private final ResultadosJpa resultadosJpa;
    private final MeioComunicacaoJpa meioComunicacaoJpa;
    private final PerfilInteressadoJpa perfilInteressadoJpa;
    private final RegistroprocedimentoanaliseJpaController registroprocedimentoanaliseJpaController;
    private final RegistroProcedimentoAnalise registroProcedimentoAnalise;
    private final RegistroDataComunicacaoJpa registroDataComunicacaoJpa;
    private final MeioComunicacaoProcedimentoAnaliseJpa meioComunicacaoProcedimentoAnaliseJpa;
    private final PerfislInteressadoProcedimentoAnaliseJpa perfislInteressadoProcedimentoAnaliseJpa;
    private final ValorMedidaJpa valorMedidaJpa;
    private final ValorIndicadorJpa valorIndicadorJpa;
    private final RelatoriosJpa relatoriosJpa;
    private final RelatoriosJpaController relatoriosJpaController;
    private final ParticipanteseInteressadosJpaController participanteseInteressadosJpaController;

    
    private FacadeJpa() {
        acessaJpa = new AcessaJpaController(Conexao.conectar());
        analiseJpa = new AnaliseJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        coletaJpa = new ColetaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        funcionalidadeJpa = new FuncionalidadeJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        medidaJpaController = new MedidaJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        medidaJpa = new MedidaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        objetivodemedicacao = new ObjetivodemedicaoJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        objetivodequestaoJpa = new ObjetivodequestaoJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        perfilJpa = new PerfilJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        procedimentodeanaliseJpa = new ProcedimentoDeAnaliseJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        ProcedimentodecoletaJpaController = new ProcedimentodecoletaJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        procedimentoColetaJpa = new ProcedimentoColetaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        projetoJpa = new ProjetoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        usuarioJpa = new UsuarioJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        objetivoDeMedicaoJpa = new ObjetivoDeMedicaoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        objetivoDeQuestaoJpa = new ObjetivoDeQuestaoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        registroMedidaJpa = new RegistroMedidaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        registroObjetivoMedicaoJpa = new RegistroobjetivomedicaoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        registroObjetivoQuestaoJpa = new RegistroObjetivoQuestaoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        indicadorJpa = new IndicadorJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        registroindicadorJpa = new RegistroindicadorJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        registroprocedimentocoletaJpaController = new RegistroprocedimentocoletaJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        registroProcedimentoColeta = new RegistroProcedimentoColetaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        entidadeMedidaJpa = new EntidadeMedidaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        datasProcedimentoColetaJpa = new DatasProcedimentoColetaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        resultadosJpa = new ResultadosJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        meioComunicacaoJpa = new MeioComunicacaoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        perfilInteressadoJpa = new PerfilInteressadoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        registroprocedimentoanaliseJpaController = new RegistroprocedimentoanaliseJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        registroProcedimentoAnalise = new RegistroProcedimentoAnalise(Conexao.URLdoBanco(Texto.lerTXT()));
        registroDataComunicacaoJpa = new RegistroDataComunicacaoJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        meioComunicacaoProcedimentoAnaliseJpa = new MeioComunicacaoProcedimentoAnaliseJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        perfislInteressadoProcedimentoAnaliseJpa = new PerfislInteressadoProcedimentoAnaliseJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        valorMedidaJpa = new ValorMedidaJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        valorIndicadorJpa = new ValorIndicadorJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        relatoriosJpa = new RelatoriosJpa(Conexao.URLdoBanco(Texto.lerTXT()));
        relatoriosJpaController = new RelatoriosJpaController(Conexao.URLdoBanco(Texto.lerTXT()));
        participanteseInteressadosJpaController = new ParticipanteseInteressadosJpaController(Conexao.URLdoBanco(Texto.lerTXT())); 
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

    public AnaliseJpa getAnaliseJpa() {
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

    public DatasProcedimentoColetaJpa getDatasprocedimentoColetaJpa() {
        return datasProcedimentoColetaJpa;
    }

    public ResultadosJpa getResultadosJpa() {
        return resultadosJpa;
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

    public ValorMedidaJpa getValorMedidaJpa() {
        return valorMedidaJpa;
    }

    public ValorIndicadorJpa getValorIndicadorJpa() {
        return valorIndicadorJpa;
    }

    public RelatoriosJpa getRelatoriosJpa() {
        return relatoriosJpa;
    }

    public RelatoriosJpaController getRelatoriosJpaController() {
        return relatoriosJpaController;
    }
    
    public ParticipanteseInteressadosJpaController getParticipanteseInteressadosJpa(){
        return participanteseInteressadosJpaController;
    }
}
