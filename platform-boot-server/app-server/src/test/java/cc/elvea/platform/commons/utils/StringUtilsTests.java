package cc.elvea.platform.commons.utils;

import org.junit.jupiter.api.Test;

import java.security.KeyPair;

/**
 * @author elvea
 */
public class StringUtilsTests {

    @Test
    public void test() {
        KeyPair keyPair = EncryptUtils.generateKeyPair();
        System.out.println(EncryptUtils.toPublicKeyString(keyPair));
        System.out.println(EncryptUtils.toPrivateKeyString(keyPair));
    }

}
