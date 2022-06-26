package org.cadmium.nichijo.mapper;

import org.cadmium.nichijo.entity.Type;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Type Mapper File Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TypeMapperTest {

    private Type result = null;
    @Autowired
    private TypeMapper typeMapper;

    @Test
    @Order(0)
    void testInsertOne() {
        int result = typeMapper.insertOne("Hello");

        assert result > 0;
        System.out.println(result);
    }


    @Test
    @Order(1)
    void testSelectByName() {
        result = typeMapper.selectByName("Hello");

        assert "Hello".equals(result.getName());
        System.out.println(result);
    }


    @Test
    @Order(2)
    void testUpdateByPrimary() {
        result.setName("HelloWorld!");
        int result = typeMapper.updateByPrimary(this.result);

        assert result > 0;
        System.out.println(result);
    }

    @Test
    void testDeleteByPrimary() {
        int isDelete = typeMapper.deleteByPrimary(result.getId());

        assert isDelete > 0;
        System.out.println(isDelete);
    }

}