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
    
    private final String ADMIN_TAGS = "/admin/tags";
    private final String ADMIN_TAGS_INPUT = "/admin/tags-input";
    private final String REDIRECT_ADMIN_TAGS = "redirect:/admin/tags";
    
    @Autowired
    private TagService tagService;
    
    
    @GetMapping
    public String tagPage(@RequestParam(defaultValue = "1") Integer pageNum, Model model, RedirectAttributes attributes) {
        PageInfo<Tag> result = tagService.tagPage(pageNum);
        if (result != null) {
            model.addAttribute("page", result);
        }
        return ADMIN_TAGS;
    }
    
    
    @GetMapping("/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return ADMIN_TAGS_INPUT;
    }
    
    
    @PostMapping
    public String save(Tag tag, RedirectAttributes attributes) {
        if (tag == null) {
            attributes.addFlashAttribute("message", "输入不能为空😠!");
            return ADMIN_TAGS;
        }
        
        if (!tagService.isExist(tag.getName())) {
            if (tagService.save(tag) > 0) {
                attributes.addFlashAttribute("message", "添加成功😄!");
            } else {
                attributes.addFlashAttribute("message", "添加失败😞!");
            }
        } else {
            attributes.addFlashAttribute("message", "项目已经存在😓!");
        }
        return REDIRECT_ADMIN_TAGS;
    }
    
    
    @PostMapping("/{id}")
    public String editor(@PathVariable("id") Integer id, Tag tag, RedirectAttributes attributes) {
    
        if (tag == null) {
            attributes.addFlashAttribute("message", "输入不能为空😠!");
            return REDIRECT_ADMIN_TAGS;
        }
        
        if (!tagService.isExist(tag.getName())) {
            if (tagService.update(tag) > 0) {
                attributes.addFlashAttribute("message", "添加成功😄!");
            } else {
                attributes.addFlashAttribute("message", "添加失败😞!");
            }
        } else {
            attributes.addFlashAttribute("message", "项目已经存在😓!");
        }
        
        return REDIRECT_ADMIN_TAGS;
    }
    
    
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        if (tagService.delete(id) > 0) {
            attributes.addFlashAttribute("message", "删除成功!");
        }
        return REDIRECT_ADMIN_TAGS;
    }
    
    
    @GetMapping("{id}/input")
    public String getInfo(@PathVariable("id") Integer id, Model model, RedirectAttributes attributes) {
    
        Tag result = tagService.get(id);
        if (result != null) {
            model.addAttribute("tag", result);
        } else {
            attributes.addFlashAttribute("message", "Tag标签不存在!");
        }
    
        return ADMIN_TAGS_INPUT;
    }
    
}
