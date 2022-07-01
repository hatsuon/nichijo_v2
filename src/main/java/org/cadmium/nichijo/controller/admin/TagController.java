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
import lombok.extern.slf4j.Slf4j;
import org.cadmium.nichijo.entity.Tag;
import org.cadmium.nichijo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/admin/tags")
public class TagController {
    
    @Autowired
    private TagService tagService;
    
    
    @GetMapping
    public String tagPage(@RequestParam(defaultValue = "1") Integer pageNum, Model model, RedirectAttributes attributes) {
        PageInfo<Tag> result = tagService.tagPage(pageNum);
        if (result != null) {
            model.addAttribute("page", result);
        }
        return "admin/tags";
    }
    
    
    @GetMapping("/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }
    
    
    @PostMapping
    public String save(Tag tag) {
        tagService.save(tag);
        return "admin/tags";
    }
    
    
    @GetMapping("/{id}/input")
    public String editor(@PathVariable("id") Integer id, Model model) {
    
        Tag result = tagService.get(id);
        if (result != null) {
            model.addAttribute("tag", result);
        }
        return "admin/tags-input";
    
    }
    
}
