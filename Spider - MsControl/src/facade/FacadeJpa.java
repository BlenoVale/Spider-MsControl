package facade;

import jpa.extensao.ProjetoJpa;

/**
 * Classe criada para servir de interface com os controladores JPA
 * A classe eh singleton
 *
 * @author DAN JHONATAN
 */
public class FacadeJpa {

    private static FacadeJpa INSTANCE = null;
    private ProjetoJpa projetoJpa;

    public FacadeJpa() {
        projetoJpa = new ProjetoJpa();
    }

    public static FacadeJpa getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FacadeJpa();
        return INSTANCE;
    }

    public ProjetoJpa getProjetoJpa() {
        return projetoJpa;
    }
}
