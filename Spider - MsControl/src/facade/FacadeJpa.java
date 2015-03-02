package facade;

import jpa.AcessaJpaController;
import jpa.AnaliseJpaController;
import jpa.ColetaJpaController;
import jpa.MedidaJpaController;
import jpa.ObjetivodemedicaoJpaController;
import jpa.ObjetivodequestaoJpaController;
import jpa.ProcedimentodeanaliseJpaController;
import jpa.ProcedimentodecoletaJpaController;
import jpa.RegistroindicadorJpaController;
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
import jpa.extensao.UsuarioJpa;
import jpa.extensao.RegistroMedidaJpa;
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
    private final ColetaJpaController coletaJpa;
    private final FuncionalidadeJpa funcionalidadeJpa;
    private final MedidaJpaController medidaJpa;
    private final MedidaJpa medicaoJpa; //@paulo
    private final ObjetivodemedicaoJpaController objetivodemedicacao;
    private final ObjetivodequestaoJpaController objetivodequestaoJpa;
    private final PerfilJpa perfilJpa;
    private final ProcedimentodeanaliseJpaController procedimentodeanaliseJpa;
    private final ProcedimentodecoletaJpaController procedimentodecoletaJpa;
    private final ProjetoJpa projetoJpa;
    private final UsuarioJpa usuarioJpa;
    private final ObjetivoDeMedicaoJpa objetivoDeMedicaoJpa;
    private final ObjetivoDeQuestaoJpa objetivoDeQuestaoJpa;
    private final RegistroMedidaJpa registroMedidaJpa;
    private final RegistroobjetivomedicaoJpa registroObjetivoMedicaoJpa;
    private final RegistroObjetivoQuestaoJpa registroObjetivoQuestaoJpa;
    private final IndicadorJpa indicadorJpa;
    private final RegistroindicadorJpaController registroindicadorJpa;
    private final EntidadeMedidaJpa entidadeMedidaJpa;

    private FacadeJpa() {
        acessaJpa = new AcessaJpaController(Conexao.conectar());
        analiseJpa = new AnaliseJpaController(Conexao.conectar());
        coletaJpa = new ColetaJpaController(Conexao.conectar());
        funcionalidadeJpa = new FuncionalidadeJpa();
        medidaJpa = new MedidaJpaController(Conexao.conectar());
        medicaoJpa = new MedidaJpa(); 
        objetivodemedicacao = new ObjetivodemedicaoJpaController(Conexao.conectar());
        objetivodequestaoJpa = new ObjetivodequestaoJpaController(Conexao.conectar());
        perfilJpa = new PerfilJpa();
        procedimentodeanaliseJpa = new ProcedimentodeanaliseJpaController(Conexao.conectar());
        procedimentodecoletaJpa = new ProcedimentodecoletaJpaController(Conexao.conectar());
        projetoJpa = new ProjetoJpa();
        usuarioJpa = new UsuarioJpa();
        objetivoDeMedicaoJpa = new ObjetivoDeMedicaoJpa();
        objetivoDeQuestaoJpa = new ObjetivoDeQuestaoJpa();
        registroMedidaJpa = new RegistroMedidaJpa();
        registroObjetivoMedicaoJpa = new RegistroobjetivomedicaoJpa();
        registroObjetivoQuestaoJpa = new RegistroObjetivoQuestaoJpa();
        indicadorJpa = new IndicadorJpa();
        registroindicadorJpa = new RegistroindicadorJpaController(Conexao.conectar());
        entidadeMedidaJpa = new EntidadeMedidaJpa();
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
    public MedidaJpa getMedicaoJpa(){
        return medicaoJpa;
    }
    public RegistroMedidaJpa getRegistroMedidaJpa(){
        return registroMedidaJpa;
    }

    public AnaliseJpaController getAnaliseJpa() {
        return analiseJpa;
    }

    public ColetaJpaController getColetaJpa() {
        return coletaJpa;
    }

    public FuncionalidadeJpa getFuncionalidadeJpa() {
        return funcionalidadeJpa;
    }

    public MedidaJpaController getMedidaJpa() {
        return medidaJpa;
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

    public EntidadeMedidaJpa getEntidadeMedidaJpa() {
        return entidadeMedidaJpa;
    }
}
