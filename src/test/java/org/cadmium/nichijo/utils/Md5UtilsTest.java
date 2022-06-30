package org.cadmium.nichijo.utils;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class Md5UtilsTest {


    @Test
    void testEncrypt() {
        System.out.println(Md5Utils.encrypt("0000"));
    }

    @Test
    void testRandomUUID() {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }

}