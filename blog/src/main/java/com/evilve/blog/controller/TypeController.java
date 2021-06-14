package com.evilve.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.evilve.blog.pojo.Type;
import com.evilve.blog.service.TypeService;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yaml.snakeyaml.events.Event;

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
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(Integer currentPage,Model model){
        if (currentPage==null||currentPage==0){
            currentPage=1;
        }
        Integer pageSize=3;
        List<Type> types=typeService.leftTypes(currentPage,pageSize);
        int count = typeService.count();
        model.addAttribute("types",types);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPage",count%pageSize==0?count/pageSize:count/pageSize+1);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String addInput(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @RequestMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        Type type = typeService.getOne(new QueryWrapper<Type>().eq("id", id));
        model.addAttribute("type",type);
        return "admin/types-input";
    }


    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes,Model model){
        Type name = typeService.getOne(new QueryWrapper<Type>().eq("name", type.getName()),false);
        if (name!=null){
            model.addAttribute("message","类型名称重复");
            return "admin/types-input";
        }
        boolean save = typeService.save(type);
        if (save){
            attributes.addFlashAttribute("message","操作成功");
        }else{
            attributes.addFlashAttribute("message","操作失败");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String post(Type type,@PathVariable("id") Long id, RedirectAttributes attributes,Model model){
        Type name = typeService.getOne(new QueryWrapper<Type>().eq("name", type.getName()),false);
        if (name!=null){
            model.addAttribute("message","类型名称重复");
            return "admin/types-input";
        }
        type.setId(id);
        boolean save = typeService.update(type,new UpdateWrapper<Type>().eq("id",id));
        if (save){
            attributes.addFlashAttribute("message","更新成功");
        }else{
            attributes.addFlashAttribute("message","更新失败");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        typeService.removeById(id);
        attributes.addAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

}

