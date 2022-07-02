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

package org.cadmium.nichijo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cadmium.nichijo.common.model.BasicEntity;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Article extends BasicEntity {
    
    @Serial
    private static final long serialVersionUID = 7100746899756920233L;
    
    private String cover;
    private String title;
    private String userId;
    private String tagIds;
    private String content;
    private String typeName;
    private String description;
    
    private Integer typeId;
    private Integer reading;
    
    private Boolean isDraft;
    private Boolean isPublic;
    private Boolean isComment;
    private Boolean isTransport;
    private Boolean isRecommend;
    
    List<Tag> tags = new ArrayList<>();
    
}
