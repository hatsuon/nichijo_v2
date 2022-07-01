package org.cadmium.nichijo.utils;

import java.util.Objects;

public abstract class StringUtils {

    private StringUtils() {
        super();
    }

    public static boolean isEmpty(String str) {
        return Objects.isNull(str) || "null".equals(str) || "".equals(str);
    }

}
