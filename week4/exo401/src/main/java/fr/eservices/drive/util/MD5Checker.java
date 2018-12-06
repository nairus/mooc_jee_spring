package fr.eservices.drive.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Checker extends AbstractPasswordChecker {

    @Override
    protected byte[] processEncoding(String login, String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        sb.append(login).append(password);
        MessageDigest messageDigest = MessageDigest.getInstance(PasswordEncoderEnum.MD5.getAlgorithm());
        return messageDigest.digest(sb.toString().getBytes(AppConstants.ENCODED_CHARSET));
    }

}
