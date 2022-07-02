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
import org.cadmium.nichijo.entity.Tag;

public interface TagService {
    
    boolean isExist(String tagName);
    Tag get(Integer id);

    int save(Tag tag);

    int update(Tag tag);

    int delete(Integer id);

    PageInfo<Tag> tagPage(Integer pageNum);

}
