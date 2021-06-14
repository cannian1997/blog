package com.evilve.blog.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.evilve.blog.exception.NotFoundException;
import com.evilve.blog.pojo.Blog;
import com.evilve.blog.pojo.Type;
import com.evilve.blog.service.BlogService;
import com.evilve.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class indexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    private static final Integer PAGE_SIZE=8;

    @GetMapping("/")
    public String index(Integer currentPage, Model model){
//        int i=9/0;
//        String blog=null;
//        if (blog==null){
//            throw new NotFoundException("博客找不到");
//        }
        if (currentPage==null||currentPage==0){
            currentPage=1;
        }
        List<Blog> blogs = blogService.blogPage(null,currentPage, PAGE_SIZE);
        List<Type> types = typeService.getTypes(1,8);
        int count = blogService.count();
        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);
        model.addAttribute("count",count);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPage",count%PAGE_SIZE==0?count/PAGE_SIZE:count/PAGE_SIZE+1);
        System.out.println("-----------------index--------------");
        return "index";
    }




    @RequestMapping("/search")
    public String search(Integer currentPage,String query,Model model){
        if (currentPage==null ||currentPage==0){
            currentPage=1;
        }
        List<Blog> blogs = blogService.blogPage(query,currentPage, PAGE_SIZE);
        int count = blogService.count(new QueryWrapper<Blog>().like("title", query).or().like("content",query));
        model.addAttribute("blogs",blogs);
        model.addAttribute("count",count);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPage",count%PAGE_SIZE==0?count/PAGE_SIZE:count/PAGE_SIZE+1);
        model.addAttribute("query",query);
        return "search";
    }
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id")Long id,Model model){
        Blog blog = blogService.blogById(id);
        Blog b = new Blog();
        b.setId(blog.getId());
        b.setViews(blog.getViews()+1);
        blogService.updateById(b);
        model.addAttribute("blog",blog);
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newBlogs(Model model){
        List<Blog> blogs = blogService.blogPage(null, 1, 3);
        model.addAttribute("newBlogs",blogs);
        return "_fragments :: newblogList";
    }

}
