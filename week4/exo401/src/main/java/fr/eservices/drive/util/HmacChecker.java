package fr.eservices.drive.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = AppConstants.HMAC_SHA1)
public class HmacChecker extends AbstractPasswordChecker {

    @Override
    protected byte[] processEncoding(String login, String password)
            throws NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException {
        try {
            Mac mac = Mac.getInstance(PasswordEncoderEnum.HMAC_SHA1.getAlgorithm());
            SecretKeySpec key = new SecretKeySpec(login.getBytes(AppConstants.ENCODED_CHARSET),
                    PasswordEncoderEnum.HMAC_SHA1.getAlgorithm());
            mac.init(key);
            return mac.doFinal(password.getBytes(AppConstants.ENCODED_CHARSET));
        } catch (InvalidKeyException e) {
            throw new RuntimeException("An error occured during init Mac encoding", e);
        }
    }

}
