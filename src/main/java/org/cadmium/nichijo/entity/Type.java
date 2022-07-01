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
import lombok.ToString;
import org.cadmium.nichijo.common.model.BasicEntity;

import java.io.Serial;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Type extends BasicEntity {

    @Serial
    private static final long serialVersionUID = -4116660032514567000L;

    private String userId;
    private String name;

}
