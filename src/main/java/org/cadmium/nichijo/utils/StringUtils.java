package org.cadmium.nichijo.utils;

public abstract class StringUtils {

    private StringUtils() {
        super();
    }

    public static boolean isEmpty(String str) {
        return str != null && !"null".equals(str) && !"".equals(str);
    }

}
