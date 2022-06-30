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

    private Integer id;

    private Boolean type;

    private String cover;
    private String avatar;
    private String username;
    private String nickname;
    private String password;

}
