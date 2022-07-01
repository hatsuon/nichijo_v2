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

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BasicEntity {

    @Serial
    private static final long serialVersionUID = 7012525950948954722L;


    private Boolean type;

    private String uid;
    private String cover;
    private String avatar;
    private String username;
    private String nickname;
    private String password;

}
