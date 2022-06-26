package org.cadmium.nichijo.service;

import org.cadmium.nichijo.entity.Type;

import java.util.List;

public interface TypeService {

    Integer PAGE_SIZE = 4;

    int save(Type type);

    int delete(Integer id);

    int update(Type type);

    Type get(Integer id);
    List<Type> typePage(Integer pageNum);

}
