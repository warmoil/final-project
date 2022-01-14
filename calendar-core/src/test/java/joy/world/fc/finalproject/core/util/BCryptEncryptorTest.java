package joy.world.fc.finalproject.core.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BCryptEncryptorTest {

    @Test
    void test() {
        final String origin = "pw";
        final Encryptor encryptor = new BCryptEncryptor();
        final String hash = encryptor.encrypt(origin);

        assertTrue(encryptor.isMatch(origin, hash));

        final String origin2 = "pw2";
        assertFalse(encryptor.isMatch(origin2, hash));

    }

}