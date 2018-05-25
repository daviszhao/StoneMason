package io.github.daviszhao.stonemason.jdjr.api.service.impls;

import io.github.daviszhao.stonemason.jdjr.api.service.config.JDJRConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Base64;

@Named
@Slf4j
public class SecurityService {
    private JDJRConfig jdConfig;

    public JDJRConfig getJdConfig() {
        return jdConfig;
    }

    @Inject
    public void setJdConfig(JDJRConfig jdConfig) {
        this.jdConfig = jdConfig;
    }

    public String generateSign(String toSign) throws UnsupportedEncodingException {

        String signType = jdConfig.getSignType();
        if ("CERT".equals(signType)) return certSign(toSign);
        return md5Sign(toSign);
    }

    private String md5Sign(String toSign) throws UnsupportedEncodingException {
        return DigestUtils.md5DigestAsHex((toSign + jdConfig.getSignKey()).getBytes(jdConfig.getCharSet()));
    }

    private String certSign(String toSign) {
        return toSign;
    }

    public String encrypt(String source) throws Exception {
        byte[] key = getEncryptKey();
        byte[] data = source.getBytes(jdConfig.getCharSet());
        return new String(Base64.getEncoder().encode(des3Encode(key, data)));
    }

    private byte[] getEncryptKey() {
        return Base64.getDecoder().decode(jdConfig.getJd3DesKey());
    }

    public String decrypt(String source) throws Exception {
        byte[] key = getEncryptKey();
        byte[] data = source.getBytes(jdConfig.getCharSet());
        return new String(des3Decode(key, data));
    }

    private byte[] des3Decode(byte[] key, byte[] data) throws GeneralSecurityException {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        Key deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        return cipher.doFinal(data);
    }

    private byte[] des3Encode(byte[] key, byte[] data) throws GeneralSecurityException {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        Key deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        return cipher.doFinal(data);
    }

    public boolean verifySign(String toSign, String sign) {
        if (toSign != null && sign != null) {
            try {
                return sign.equals(generateSign(toSign));
            } catch (UnsupportedEncodingException e) {
                log.error("generateSign failed", e);
                return false;
            }
        } else return false;
    }

}
