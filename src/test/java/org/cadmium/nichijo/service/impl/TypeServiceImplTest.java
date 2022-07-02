package org.cadmium.nichijo.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.cadmium.nichijo.entity.Type;
import org.cadmium.nichijo.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    
    
    @Test
    void testExist() {
        boolean result = typeService.isExist("Java");
        System.out.println(result);
    }
    
    
    @Test
    void testTypeList() {
        List<Type> result = typeService.list();
        
        assert result != null;
        System.out.println(result);
    }
}