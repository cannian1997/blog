package com.evilve.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.evilve.blog.pojo.Blog;
import com.evilve.blog.pojo.Comment;
import com.evilve.blog.pojo.Type;
import com.evilve.blog.pojo.User;
import com.evilve.blog.service.BlogService;
import com.evilve.blog.service.CommentService;
import com.evilve.blog.service.TypeService;
import com.evilve.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Evilve
 * @since 2020-12-11
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT="admin/blogs-input";
    private static final String LIST="admin/blogs";
    private static final String REDIRECT_LIST="redirect:/admin/blogs";
    private static final Integer PAGE_SIZE=2;

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/blogs")
    public String blogs(Integer currentPage,BlogQuery blog, Model model){
        if (currentPage==null||currentPage==0){
            currentPage=1;
        }
        System.out.println(blog);
        List<Blog> blogs = blogService.queryBlog(blog, currentPage, PAGE_SIZE);
        int count = blogService.count();
        List<Type> types = typeService.list();
        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPage",count%PAGE_SIZE==0?count/PAGE_SIZE:count/PAGE_SIZE+1);
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(Integer currentPage,BlogQuery blog,Model model){
        if (currentPage==null||currentPage==0){
            currentPage=1;
        }
        System.out.println(blog);
        List<Blog> blogs = blogService.queryBlog(blog, currentPage, PAGE_SIZE);
        int count = blogService.count();
        List<Type> types = typeService.list();
        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPage",count%PAGE_SIZE==0?count/PAGE_SIZE:count/PAGE_SIZE+1);
        return "admin/blogs::blogList";
    }
    @GetMapping("/blogs/input")
    public String input(Model model){
        setType(model);
        model.addAttribute("blog",new Blog());
        return INPUT;
    }

    private void setType(Model model){
        List<Type> types = typeService.list();
        model.addAttribute("types",types);
    }

    @GetMapping("/blogs/{id}/input")
    public String editorInput(@PathVariable("id") Long id, Model model){
        setType(model);
        Blog blog = blogService.getById(id);
        model.addAttribute("blog",blog);
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        System.out.println(blog);
        blog.setTId(blog.getType().getId());
        User user = (User) session.getAttribute("user");
        blog.setUId(user.getId());
        boolean save = blogService.saveOrUpdate(blog);
        if (save){
            attributes.addFlashAttribute("message","操作成功");
        }else{
            attributes.addFlashAttribute("message","操作失败");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        boolean flag = blogService.removeById(id);
        commentService.remove(new QueryWrapper<Comment>().eq("b_id",id));
        if (flag) {
            attributes.addFlashAttribute("message","操作成功");
        }else {
            attributes.addFlashAttribute("message","操作失败");
        }
        return REDIRECT_LIST;
    }

}

