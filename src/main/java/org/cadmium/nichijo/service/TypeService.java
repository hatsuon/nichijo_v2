/*
 * Copyright (c) 2022
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cadmium.nichijo.service;

import com.github.pagehelper.PageInfo;
import org.cadmium.nichijo.entity.Article;
import org.cadmium.nichijo.entity.Type;

import java.util.List;

public interface TypeService {
    
    Integer PAGE_SIZE = 4;
    
    List<Type> list();
    
    int save(Type type);

    int delete(Integer id);

    int update(Type type);

    Type get(Integer id);
    
    boolean isExist(String typeName);
    
    PageInfo<Type> typePage(Integer pageNum);
    
    PageInfo<Type> typePage(Integer pageNum, Article article);
    
}
