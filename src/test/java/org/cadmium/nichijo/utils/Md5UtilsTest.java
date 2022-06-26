package org.cadmium.nichijo.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Md5UtilsTest {


    @Test
    void testEncrypt() {
        System.out.println(Md5Utils.encrypt("0000"));
    }
}