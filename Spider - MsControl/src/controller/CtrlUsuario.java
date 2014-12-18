package controller;

import facade.FacadeJpa;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Acessa;
import model.Perfil;
import model.Projeto;
import model.Usuario;
import util.Criptografia;

/**
 *
 * @author DAN JHONATAN, Géssica, BlenoVale
 */
public class CtrlUsuario {

    private final FacadeJpa facadeJpa = FacadeJpa.getInstance();
    private final Criptografia criptografia = new Criptografia();

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

    public void editaUsuario(Usuario usuario) {
        try {
            facadeJpa.getUsuarioJpa().edit(usuario);
            JOptionPane.showMessageDialog(null, "Dados Salvos com sucesso.");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Erro ao editar Usuário.");
            error.printStackTrace();
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

    /**
     * Busca usuario pelo atributo Nome.
     *
     * @param usuario objeto que possui o atributo para a busca
     * @return retorna usuario encontrado ou null caso contrario
     */
    public Usuario buscarUsuarioPeloNome(Usuario usuario) {
        try {
            return facadeJpa.getUsuarioJpa().findByNome(usuario.getNome());
        } catch (Exception error) {
            throw error;
        }

    }

    /**
     * Busca usuario pelo atributo login.
     *
     * @param usuario objeto que possui o atributo para a busca
     * @return retorna usuario encontrado ou null caso contrario
     */
    public Usuario buscarUsuarioPeloLogin(Usuario usuario) {
        try {
            return facadeJpa.getUsuarioJpa().findByLogin(usuario.getLogin());
        } catch (Exception error) {
            throw error;
        }
    }

    /**
     * Compara senha atual digitada com a já existente do usuario.
     *
     * @param usuario objeto que possui o atributo senha
     * @param senhaDigitada senha digitada.
     * @return true caso as senhas correspondem, false caso o contrário.
     */
    public boolean ComparaSenhaDigitadaComAdoBD(Usuario usuario, String senhaDigitada) {
        try {
            if (usuario.getSenha().equals(criptografia.criptografaMensagem(senhaDigitada))) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Senha incorreta.");
                return false;
            }
        } catch (Exception error) {
            throw error;
        }
    }
}
