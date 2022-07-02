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

import java.io.*;
import java.util.Base64;

public abstract class ImgUtils {
    
    private ImgUtils() {
        super();
    }
    
    
    public static String toBase64(String imagePath) {
        
        byte[] result;
        try (InputStream in = new FileInputStream(imagePath);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            
            int len;
            byte[] buff = new byte[8 * 1024];
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            out.flush();
    
            result = Base64.getDecoder().decode(out.toByteArray());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(result);
    }
    
    public static File toImage(String base64) {
        return null;
    }
}
