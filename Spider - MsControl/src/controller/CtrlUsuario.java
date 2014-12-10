package controller;

import facade.FacadeJpa;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Acessa;
import model.Perfil;
import model.Projeto;
import model.Usuario;

/**
 *
 * @author DAN JHONATAN
 */
public class CtrlUsuario {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();

    public boolean criarUsuario(String nome, String login, String senha, String email) {
        Usuario user = new Usuario();

        user.setNome(nome);
        user.setLogin(login);
        user.setSenha(senha);
        user.setEmail(email);

        try {
            facadeJpa.getUsuarioJpa().create(user);
            JOptionPane.showMessageDialog(null, "Usuário criado com sucesso");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível criar o novo usuário", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
            return false;
        }
    }

    public void alocarUsuarioAVariosProjetos(Usuario usuario, String[] nomeProjeto, String[] nomePerfil) {

        Projeto projeto;
        Perfil perfil;

        // pega os dados que tem na tabela e transforma em acesso
        for (int i = 0; i < nomeProjeto.length; i++) {
            projeto = facadeJpa.getProjetoJpa().findByNome(nomeProjeto[i]);
            perfil = facadeJpa.getPerfilJpa().findByNome(nomePerfil[i]);

            alocarUsuarioAProjeto(usuario, projeto, perfil);
        }
    }

    public void alocarUsuarioAProjeto(Usuario user, Projeto projeto, Perfil perfil) {
        Acessa acessa = new Acessa();

        acessa.setProjeto(projeto);
        acessa.setPerfil(perfil);
        acessa.setUsuario(user);
        acessa.setDataDeInicio(new Date());

        try {
            facadeJpa.getAcessaJpa().create(acessa);
        } catch (Exception ex) {
            // Se o perfil de usuario ja existir no projeto, ele nao vai criar
            System.out.println("Ja existe");
        }
    }
}
