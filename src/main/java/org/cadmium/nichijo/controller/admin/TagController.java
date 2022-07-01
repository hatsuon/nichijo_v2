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
