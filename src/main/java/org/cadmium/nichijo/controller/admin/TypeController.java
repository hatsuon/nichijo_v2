package org.cadmium.nichijo.controller.admin;

import io.vavr.control.Try;
import org.cadmium.nichijo.common.model.Packet;
import org.cadmium.nichijo.entity.Type;
import org.cadmium.nichijo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;


    @GetMapping
    public Packet<List<Type>> page(@RequestParam("pageNum") Integer pageNum) {
        List<Type> result = typeService.typePage(pageNum);

        assert result != null;
        return Packet.ok(result);
    }


    @GetMapping("/{id}")
    public Packet<Type> get(@PathVariable("id") Integer id) {

        if (id == null) {
            return Packet.fail();
        }

        Type result = typeService.get(id);

        if (Objects.isNull(result)) {
            return Packet.fail("Type not found!");
        }
        return Packet.ok(result);

    }


    @PostMapping
    public Packet<Object> save(Type type) {

        if (type == null) {
            return Packet.fail();
        }

        Try.of(() -> typeService.save(type));
        return Packet.ok();
    }


    @PutMapping
    public Packet<Object> update(Type type) {

        if (type == null) {
            return Packet.fail();
        }

        Try.of(() -> typeService.update(type));
        return Packet.ok();
    }


    @DeleteMapping("/{id}")
    public Packet<Object> delete(@PathVariable("id") Integer id) {

        if (id <= 0) {
            return Packet.fail();
        }

        Try.of(() -> typeService.delete(id));
        return Packet.ok();
    }
}
