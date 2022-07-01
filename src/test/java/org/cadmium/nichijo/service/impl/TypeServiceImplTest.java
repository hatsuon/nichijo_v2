package org.cadmium.nichijo.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.cadmium.nichijo.entity.Type;
import org.cadmium.nichijo.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TypeServiceImplTest {

    @Autowired
    private Gson gson;
    @Autowired
    private TypeService typeService;


    @Test
    void testTypePage() {
        PageInfo<Type> result = typeService.typePage(2);

        assert result != null;
        System.out.println(gson.toJson(result));
    }
}