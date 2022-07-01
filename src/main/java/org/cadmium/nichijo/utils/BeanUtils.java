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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public abstract class BeanUtils {

    private BeanUtils() {
        super();
    }


    public static <E> HashMap<String, Object> toMap(E obj) {
        HashMap<String, Object> result = new HashMap<>();

        try {
            for (Method m : obj.getClass().getMethods()) {
                String methodName = m.getName();
                if (methodName.contains("get")) {
                    Object value = m.invoke(obj);
                    result.put(methodName.substring(3), value);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return result;
    }
}
