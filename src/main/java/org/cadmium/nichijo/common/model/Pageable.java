package org.cadmium.nichijo.common.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class Pageable<E> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1221815285645221140L;

    private List<E> list;
    private Integer total;
    private Integer current;

}
