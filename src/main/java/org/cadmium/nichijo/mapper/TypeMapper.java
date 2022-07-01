package org.cadmium.nichijo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cadmium.nichijo.entity.Type;

import java.util.List;

@Mapper
public interface TypeMapper {

    int total();

    List<Type> list();
    int insertOne(@Param("name") String name);

    int updateByPrimary(Type type);

    int deleteByPrimary(@Param("id") Integer id);

    Type selectByPrimary(@Param("id") Integer id);

    Type selectByName(@Param("name") String name);

    List<Type> page(@Param("offset") Integer offset, @Param("count") Integer count);

}
