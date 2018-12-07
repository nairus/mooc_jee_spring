package fr.eservices.drive.util;

public enum PasswordEncoderEnum {
    MD2("MD2"), MD5("MD5"), SHA1("SHA-1"), SHA256("SHA-256"), SHA384("SHA-384"), SHA512("SHA-512"), HMAC_MD5("HmacMD5"),
    HMAC_SHA1("HmacSHA1"), HMAC_SH256("HmacSHA256");

    private String algorithm;

    private PasswordEncoderEnum(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String toString() {
        return algorithm;
    }

}
