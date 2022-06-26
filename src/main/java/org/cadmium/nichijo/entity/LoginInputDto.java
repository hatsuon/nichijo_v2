package org.cadmium.nichijo.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class LoginInputDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -5254342754528110568L;

    private String username;
    private String password;

}
