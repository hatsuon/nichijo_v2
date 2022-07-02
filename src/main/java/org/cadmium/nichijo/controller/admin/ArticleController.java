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
import org.cadmium.nichijo.entity.Article;
import org.cadmium.nichijo.entity.Type;
import org.cadmium.nichijo.service.ArticleService;
import org.cadmium.nichijo.service.TagService;
import org.cadmium.nichijo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/articles")
public class ArticleController {
    
    private final String ADMIN_ARTICLES = "/admin/articles";
    
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private ArticleService articleService;
    
    
    @GetMapping
    public String articles(@RequestParam(defaultValue = "1") Integer pageNum, Article article, Model model) {
    
        List<Type> result = typeService.list();
        if (result != null) {
            model.addAttribute("types", result);
        }
    
        PageInfo<Article> resultArticle = articleService.articlePage(pageNum);
        if (resultArticle != null) {
            model.addAttribute("page", resultArticle);
        }
        
        return ADMIN_ARTICLES;
    }
    
    
    @PostMapping("/search")
    public String search(@RequestParam(defaultValue = "1") Integer pageNum, Article article, Model model) {
    
        return null;
    }
}
