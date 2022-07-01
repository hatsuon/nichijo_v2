/*
 * Copyright (c) 2022
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cadmium.nichijo.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    private Md5Utils() {
        super();
    }


    public static String encrypt(String msg) {

        byte[] result = null;
        try {
            result = MessageDigest.getInstance("MD5").digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return new BigInteger(1, result).toString(16);
    }

}
