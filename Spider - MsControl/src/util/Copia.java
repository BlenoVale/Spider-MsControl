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
    private static Projeto projetoSelecionado;
    private static Usuario usuarioLogado;

    public static ViewPrincipal getViewPrincipal() {
        return viewPrincipal;
    }

    public static void setViewPrincipal(ViewPrincipal viewPrincipal) {
        Copia.viewPrincipal = viewPrincipal;

        projetoSelecionado = viewPrincipal.getProjeto_selecionado();
        usuarioLogado = viewPrincipal.getUsuario_logado();
    }

    public static Projeto getProjetoSelecionado() {
        return projetoSelecionado;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}
