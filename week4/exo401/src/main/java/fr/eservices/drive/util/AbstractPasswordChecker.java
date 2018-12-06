package fr.eservices.drive.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

abstract class AbstractPasswordChecker implements PasswordChecker {

    @Override
    public String encode(String login, String password) {
        try {
            byte[] hash;
            hash = this.processEncoding(login, password);
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException("An error occured during the encoding", e);
        }
    }

    abstract protected byte[] processEncoding(String login, String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException;

}
