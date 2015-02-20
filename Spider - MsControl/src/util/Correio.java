package util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author DAN JHONATAN
 */
public class Correio {

    private String remetente;
    private String senha;
    private String host;
    private int porta;

    /**
     * Construtor padrao para o Spider-MsControl
     */
    public Correio() {
        remetente = "spidermsc@gmail.com";
        senha = "5p1d3rm5c";
        host = "smtp.gmail.com";
        porta = 465;
    }

    public Correio(String remetente, String senha, String host, int porta) {
        this.remetente = remetente;
        this.senha = senha;
        this.host = host;
        this.porta = porta;
    }

    /**
     * Envia um email com uma mensagem com texto simple
     *
     * @param destinatario email de quem receberah a mensagem.
     * @param assunto assunto do email.
     * @param corpoMsg Texto que compoe o corpo da mensagem
     * @return true caso o email seja enviado, false caso contraio.
     */
    public boolean enviarEmailSimples(String destinatario, String assunto, String corpoMsg) {
        try {

            SimpleEmail email = new SimpleEmail();

            email.setHostName(host);
            email.setSmtpPort(porta);

            email.addTo(destinatario);
            email.setFrom(remetente);

            email.setSubject(assunto);
            email.setMsg(corpoMsg);

            //Autenticacao
            email.setSSL(true);
            email.setAuthentication(remetente, senha);

            email.send();
            System.out.println("Email enviado com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("Não foi possível enviar");
            return false;
        }
    }

    /**
     * Envia uma mensagem cujo texto pode ser em formato HTML
     *
     * @param destinatario email de quem receberah a mensagem.
     * @param assunto assunto do email.
     * @param corpoMsg Texto que compoe o corpo da mensagem
     * @return true caso o email seja enviado, false caso contraio.
     */
    public boolean enviarEmailHtml(String destinatario, String assunto, String corpoMsg) {
        try {
            HtmlEmail email = new HtmlEmail();

            email.setHostName(host);
            email.setSmtpPort(porta);

            email.addTo(destinatario);
            email.setFrom(remetente);

            email.setSubject(assunto);
            email.setHtmlMsg(corpoMsg);

            //Autenticacao
            email.setAuthentication(remetente, senha);
            email.setSSL(true);

            email.send();

            System.out.println("Email enviado com sucesso!");
            return true;
        } catch (EmailException error) {
            System.out.println("Não foi possível enviar.");
            return false;
        }
    }

    /**
     * Envia um email com uma mensagem padraoh de recuperacaoh de senha.
     *
     * @param destinatario email de quem receberah a mensagem.
     * @param login Login (nome) que aparecerah no corpo da mensagem.
     * @param senha A nova senha de acesso do usuarioh.
     * @return true caso o email seja enviado, false caso contraio.
     */
    public boolean enviarEmailRecuperacaoDeSenha(String destinatario, String login, String senha) {

        String assunto = "Recuperação de senha Spider - MsControl";
        String texto = "<html><h1>Olá " + login + ",<br><br>"
                + "Sua nova senha de acesso a ferramenta é: \"" + senha + "\".</h1></html>";

        return enviarEmailHtml(destinatario, assunto, texto);
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

}
