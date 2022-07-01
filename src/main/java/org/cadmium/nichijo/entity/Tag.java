package org.cadmium.nichijo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cadmium.nichijo.common.model.BasicEntity;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
public class Tag extends BasicEntity {

    @Serial
    private static final long serialVersionUID = -3470688086625110266L;

    private String name;
    private String userId;

}
