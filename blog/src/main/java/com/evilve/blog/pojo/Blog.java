package com.evilve.blog.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Evilve
 * @since 2020-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_blog")
@ApiModel(value="Blog对象", description="")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "首图")
    @TableField("firstPicture")
    private String firstPicture;

    @ApiModelProperty(value = "标记")
    private String flag;

    @ApiModelProperty(value = "浏览次数")
    private Integer views;

    @ApiModelProperty(value = "赞赏是否开启")
    private Boolean appreciation;

    @ApiModelProperty(value = "转载声明是否开启")
    @TableField("shareStatement")
    private Boolean shareStatement;

    @ApiModelProperty(value = "评论是否开启")
    private Boolean commentabled;

    @ApiModelProperty(value = "是否发布")
    private Boolean published;

    @ApiModelProperty(value = "是否推荐")
    private Boolean recommend;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "updateTime",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField("u_id")
    private Long uId;

    @TableField("t_id")
    private Long tId;

    @ApiModelProperty(value = "类型")
    @TableField(exist = false)
    private Type type;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private List<Comment> comments;

}
