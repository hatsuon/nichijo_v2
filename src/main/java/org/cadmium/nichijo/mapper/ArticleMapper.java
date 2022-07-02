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

package org.cadmium.nichijo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cadmium.nichijo.entity.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    
    List<Article> list();
    
    Article selectByPrimary(@Param("id") Integer id);
    
    int insertOne(Article article);
    
    int updateOne(Article article);
    
    int deleteByPrimary(Integer id);
}
