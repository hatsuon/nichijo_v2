package org.cadmium.nichijo.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class BasicEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -8075685442182196877L;

    private Integer id;

    private Date gmtCreated;
    private Date gmtUpdated;

}
