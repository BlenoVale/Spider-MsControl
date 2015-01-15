package util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author BlenoVale, Gessica
 */
public class Criptografia {

    public Criptografia() {
    }

    /**
     * criptografa String, EX: mensagem_criptografadas.
     * @param mensagem msg que será criptografada.
     * @return mensagem criptografada
     */
    public String criptografaMensagem(String mensagem) {
        
        try {
            System.out.println("Mensagem limpa: " + mensagem);
            // "Message Digest" = algoritmos de apenas uma via (cript. mas Ã± descript.).
            // "SHA-256" Ã© o algoritmo de hash utilizado para a criptografia.
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
            // Modo como os caracteres cript. serÃ£o armazenados. O "UTF-8" suporta qualquer caracter.
            algoritmo.update(mensagem.getBytes("UTF-8"));
            // Guarda os caracteres em um array de bytes.
            byte[] hash = algoritmo.digest();
            // Convete o hash para hexadecimal.
            BigInteger bigInteger = new BigInteger(1, hash);
            // converte a mensagem hexadecimal em String.
            String mensagem_criptografadas = bigInteger.toString(16);
            System.out.println("Senha Criptografada: " + mensagem_criptografadas);
            return mensagem_criptografadas;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException error) {
            return null;
        }

    }
}
