package util;

import model.Projeto;
import model.Usuario;
import view.ViewPrincipal;

/**
 *
 * @author DAN JHONATAN
 */
public class Copia {

    private static ViewPrincipal viewPrincipal;

    public static ViewPrincipal getViewPrincipal() {
        return viewPrincipal;
    }

    public static void setViewPrincipal(ViewPrincipal viewPrincipal) {
        Copia.viewPrincipal = viewPrincipal;
    }

    public static Projeto getProjetoSelecionado() {
        return viewPrincipal.getProjeto_selecionado();
    }

    public static Usuario getUsuarioLogado() {
        return viewPrincipal.getUsuario_logado();
    }
}
