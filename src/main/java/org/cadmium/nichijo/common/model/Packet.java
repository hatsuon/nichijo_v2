package org.cadmium.nichijo.common.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Packet<E> implements Serializable {

    @Serial
    private static final long serialVersionUID = -6039776504329320099L;

    private E date;
    private Integer code;
    private String message;
    private Boolean status;

    public static <E> Packet<E> ok(E date) {
        Packet<E> result = new Packet<>();
        result.setDate(date);
        result.setStatus(true);
        result.setMessage("OK");

        return result;
    }

    public static <E> Packet<E> ok () {
        Packet<E> result = new Packet<>();
        result.setDate(null);
        result.setStatus(true);
        result.setMessage("OK");

        return result;
    }

    public static <E> Packet<E> fail() {
        Packet<E> result = new Packet<>();
        result.setDate(null);
        result.setStatus(false);
        result.setMessage("Fail");

        return result;
    }

    public static <E> Packet<E> fail(String msg) {
        Packet<E> result = new Packet<>();
        result.setStatus(false);
        result.setMessage(msg);

        return result;
    }



}
