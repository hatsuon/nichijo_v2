/*
 * Copyright (c) 2022
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cadmium.nichijo.controller.admin;

import com.github.pagehelper.PageInfo;
import io.vavr.control.Try;
import org.cadmium.nichijo.common.model.Packet;
import org.cadmium.nichijo.entity.Type;
import org.cadmium.nichijo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/types")
public class TypeController {

    @Autowired
    private TypeService typeService;


    @GetMapping
    public String type(@RequestParam(defaultValue = "1") Integer pageNum, Model model) {

        PageInfo<Type> result = typeService.typePage(pageNum);

        if (result != null) {
            model.addAttribute("page", result);
        }
        return "admin/types";
    }

    @GetMapping("/{id}/input")
    public String getInfo(@PathVariable("id") Integer typeId, Model model) {
        Type result = typeService.get(typeId);

        if (result != null) {
            model.addAttribute("type", result);
        }

        return "admin/types-input";
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer typeId) {
        int result = typeService.delete(typeId);
        return "redirect:/admin/types";
    }

    @GetMapping("/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @PostMapping
    public String save(Type type) {
        typeService.save(type);
        return "redirect:/admin/types";
    }


    @PostMapping("/{id}")
    public String editor(@PathVariable("id") Integer id, Type type) {
        typeService.update(type);
        return "redirect:/admin/types";
    }

}
