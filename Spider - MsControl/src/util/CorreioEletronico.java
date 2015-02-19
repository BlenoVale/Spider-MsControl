package util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class CorreioEletronico {

    public void enviaEmail() {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.live.com");
            email.setSmtpPort(25);
            DefaultAuthenticator autentica = new DefaultAuthenticator("spiderMSC@outlook.com", "5p1d3rm5c");
            email.setAuthenticator(autentica);
            email.setSSLOnConnect(true);
            email.setFrom("blenofvale@gmail.com");
            email.setSubject("Teste");
            email.setMsg("Chegooooou!!!");
            email.send();
        } catch (Exception error) {
            error.printStackTrace();
            System.out.println("---Não foi!! :(");
        }
    }
}
