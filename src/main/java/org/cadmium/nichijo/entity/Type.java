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
