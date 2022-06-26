package org.cadmium.nichijo.utils;

import org.cadmium.nichijo.entity.Type;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

class BeanUtilsTest {

    @Test
    void testToMap() {
        Type type = new Type();
        type.setName("Atom");


        HashMap<String, Object> result = BeanUtils.toMap(type);

        System.out.println(result);
    }

}