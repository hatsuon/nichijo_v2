package org.cadmium.nichijo.entity.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 975457776934959061L;

    private String username;
    private String password;

}
