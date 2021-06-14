package com.evilve.blog.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("t_comment")
@ApiModel(value="Comment对象", description="")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private Blog blog;

    @TableField(exist = false)
    private List<Comment> replyComments;

    @TableField(exist = false)
    private Comment parentComment;

    @TableField(value = "parent_comment_id")
    private Long parentCommentId;

    @TableField(value = "b_id")
    private Long bId;

    @TableField(value = "adminComment")
    private boolean adminComment;
}
