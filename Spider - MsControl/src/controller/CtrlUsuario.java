package controller;

import facade.FacadeJpa;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
     * @param nome_usuario
     * @return retorna usuario encontrado ou null caso contrario
     */
    public Usuario buscarUsuarioPeloNome(String nome_usuario) {
        try {
            return facadeJpa.getUsuarioJpa().findByNome(nome_usuario);
        } catch (Exception error) {
            throw error;
        }

    }

    /**
     * Busca usuario pelo atributo login.
     *
     * @param login_usuario
     * @return retorna usuario encontrado ou null caso contrario
     */
    public Usuario buscarUsuarioPeloLogin(String login_usuario) {
        try {
            return facadeJpa.getUsuarioJpa().findByLogin(login_usuario);
        } catch (Exception error) {
            throw error;
        }
    }

    /**
     * Compara senha atual digitada com a já existente do usuario.
     *
     * @param senha_usuario
     * @param senha_digitada
     * @return true caso as senhas correspondem, false caso o contrário.
     */
    public boolean ComparaSenhaDigitadaComAdoBD(String senha_usuario, String senha_digitada) {
        try {
            if (senha_usuario.equals(criptografia.criptografaMensagem(senha_digitada))) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Senha incorreta.");
                return false;
            }
        } catch (Exception error) {
            throw error;
        }
    }

    /**
     * Avalia se o email digitado é válido;
     *
     * @param email
     * @return true se o email for válido false caso contrário.
     */
    public boolean validaEmail(String email) {
        try {
            String expressao_regular = "[A-Za-z0-9\\._-]+@[A-Za-z0-9]+(\\.[A-Za-z]+)+(\\.[A-Za-z]+)*";
            return email.matches(expressao_regular);
        } catch (Exception error) {
            throw error;
        }
    }

    public List<Usuario> buscaListaDeUsuariosPeloEmail(String email) {
        try {
            return facadeJpa.getUsuarioJpa().findUsuarioByEmail(email);
        } catch (Exception error) {
            throw error;
        }
    }

    public boolean existeEmailCadastrado(String email) {
        try {
            List<Usuario> listaUsuario = facadeJpa.getUsuarioJpa().findUsuarioByEmail(email);
            if (!listaUsuario.isEmpty()) {
                geraNovaSenha(listaUsuario.get(0));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "O e-mail não Cadastrado.");
                return false;
            }
        } catch (Exception error) {
            throw error;
        }

    }

    public void geraNovaSenha(Usuario usuario) {
        try {
            //cria string aletória de 6 digitos.
            UUID uuid = UUID.randomUUID();
            String novaSenha = uuid.toString().substring(0, 6);

            String novaSenhaCrip = criptografia.criptografaMensagem(novaSenha);
            usuario.setSenha(novaSenhaCrip);
            facadeJpa.getUsuarioJpa().edit(usuario);
            JOptionPane.showMessageDialog(null, "Nova senha criada.\nPor favor cheque seu E-mail.");
        } catch (Exception error) {
            error.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro inesperado.");
        }
    }
}
