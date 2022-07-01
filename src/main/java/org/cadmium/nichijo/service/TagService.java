package org.cadmium.nichijo.service;

import com.github.pagehelper.PageInfo;
import org.cadmium.nichijo.entity.Tag;

public interface TagService {
    
    Tag get(Integer id);

    boolean save(Tag tag);

    boolean update(Tag tag);

    boolean delete(Integer id);

    PageInfo<Tag> tagPage(Integer pageNum);

}
