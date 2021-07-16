package com.evilve.blog.controller;


import com.evilve.blog.pojo.Comment;
import com.evilve.blog.pojo.User;
import com.evilve.blog.service.BlogService;
import com.evilve.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
public class CommentController {
    @Autowired
    private CommentService  commentService;
    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable("blogId") Long blogId, Model model){
        List<Comment> comments = commentService.listCommentByBlogId(blogId,null);
        for (Comment comment : comments) {
            List<Comment> replyComments = commentService.listCommentByBlogId(null,comment.getId());
            if (replyComments!=null){
                for (Comment replyComment : replyComments) {
                    //循环迭代，找出子代，存放在tempReplys中
                    recursicely(replyComment);
                }
            }
            //修改顶级节点的replay集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys=new ArrayList<>();
        }
        model.addAttribute("comments",comments);
        return "blog :: commentList";
    }

    //存放迭代找出所有子代的集合
    private List<Comment> tempReplys=new ArrayList<>();

    /**
     * 递归迭代
     * @param comment
     */
    private void recursicely(Comment comment){
        comment.setParentComment(commentService.getById(comment.getParentCommentId()));
        tempReplys.add(comment);//顶级节点添加到临时存放集合
        if (commentService.listCommentByBlogId(null,comment.getId()).size()>0){
            List<Comment> replys=commentService.listCommentByBlogId(null,comment.getId());
            for (Comment reply : replys) {
                reply.setParentComment(commentService.getById(reply.getParentCommentId()));
                tempReplys.add(reply);
                if (commentService.listCommentByBlogId(null,reply.getId()).size()>0){
                    recursicely(reply);
                }
            }
        }

    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long blogId=comment.getBlog().getId();
        comment.setBId(blogId);
        User user= (User) session.getAttribute("user");
        if (user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(avatar);
        }
        if (comment.getParentCommentId()==-1){
            comment.setParentCommentId(null);
        }
        commentService.save(comment);
        return "redirect:/comments/"+comment.getBlog().getId();
    }
}

