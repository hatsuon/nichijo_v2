package org.cadmium.nichijo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cadmium.nichijo.entity.Tag;
import org.cadmium.nichijo.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class TagServiceImplTest {
    
    
    @Autowired
    private TagService tagService;
    
    
    @Test
    void testSelect() {
        
        List<Tag> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(tagService.get(i + 1));
        }
        
        assert result != null;
        System.out.println(result);
    }
}