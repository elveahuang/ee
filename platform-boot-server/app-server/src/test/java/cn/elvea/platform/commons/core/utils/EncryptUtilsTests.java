package cn.elvea.platform.commons.core.utils;

import org.junit.jupiter.api.Test;

import java.security.KeyPair;

/**
 * @author elvea
 * @since 0.0.1
 */
public class EncryptUtilsTests {
    @Test
    public void test() {
        KeyPair keyPair = EncryptUtils.generateKeyPair();
        System.out.println(EncryptUtils.toPublicKeyString(keyPair));
        System.out.println(EncryptUtils.toPrivateKeyString(keyPair));
    }
}
