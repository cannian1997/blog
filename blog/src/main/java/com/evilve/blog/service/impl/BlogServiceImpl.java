package com.evilve.blog.service.impl;

import com.evilve.blog.pojo.Blog;
import com.evilve.blog.mapper.BlogMapper;
import com.evilve.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.evilve.blog.util.MarkdownUtils;
import com.evilve.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Evilve
 * @since 2020-12-11
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> queryBlog(BlogQuery blogQuery, Integer currentPage, Integer pageSize) {
        return blogMapper.queryBlog(blogQuery,(currentPage-1)*pageSize,pageSize);
    }

    @Override
    public List<Blog> blogPage(String query,Integer currentPage, Integer pageSize) {
        System.out.println(blogMapper != null);
        return blogMapper.blogPage(query,(currentPage-1)*pageSize,pageSize);
    }

    @Override
    public Blog blogById(Long id) {
        Blog blog = blogMapper.blogById(id);
        String content = blog.getContent();
        String s = MarkdownUtils.markdownToHtmlExtensions(content);
        blog.setContent(s);
        return blog;
    }

    @Override
    public List<Blog> blogByTypeId(Long id,Integer currentPage,Integer pageSize) {
        return blogMapper.blogByTypeId(id,currentPage,pageSize);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> date = blogMapper.findGroupDate();
        Map<String,List<Blog>> map=new HashMap<>();
        for (String s : date) {
            map.put(s,blogMapper.findByDate(s));
        }
        return map;
    }

}
