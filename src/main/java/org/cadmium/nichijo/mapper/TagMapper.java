package org.cadmium.nichijo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cadmium.nichijo.entity.Tag;

import java.util.List;

@Mapper
public interface TagMapper {

    List<Tag> list();

    Tag selectByPrimary(Integer id);

    Integer deleteByPrimary(Integer id);

    Integer updateByPrimary(Tag tag);

    Integer insertOne(Tag tag);

}
