package com.evilve.blog.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.evilve.blog.pojo.Blog;
import com.evilve.blog.pojo.Type;
import com.evilve.blog.service.BlogService;
import com.evilve.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    private static final Integer PAGE_SIZE=10;

    @GetMapping("/types/{id}")
    public String types(Integer currentPage, @PathVariable("id") Long id, Model model){
        if (currentPage==null||currentPage==0){
            currentPage=1;
        }
        List<Type> types = typeService.getTypes(null, null);//查询所有分类
        if (id==-1){
            id=types.get(0).getId();
        }
        List<Blog> blogs = blogService.blogByTypeId(id,(currentPage-1)*PAGE_SIZE,PAGE_SIZE);
        int count = blogService.count(new QueryWrapper<Blog>().eq("t_id", id));
        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);
        model.addAttribute("totalPage",count%PAGE_SIZE==0?count/PAGE_SIZE:count/PAGE_SIZE+1);
        model.addAttribute("activeTypeId",id);
        model.addAttribute("currentPage",currentPage);
        return "types";
    }
}
