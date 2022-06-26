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
