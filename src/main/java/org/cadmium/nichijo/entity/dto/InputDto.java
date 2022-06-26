package org.cadmium.nichijo.entity.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class InputDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8085832576708592859L;

    private Integer id;
    private String name;

}
