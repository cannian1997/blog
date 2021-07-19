package com.evilve.blog.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 异常日志表
 * </p>
 *
 * @author Evilve
 * @since 2021-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_exception_info")
@ApiModel(value="ExceptionInfo对象", description="异常日志表")
public class ExceptionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
      @TableId(value = "PK_ID", type = IdType.AUTO)
    private Integer pkId;

    @ApiModelProperty(value = "异常日志ID")
    @TableField("EXCEPTION_LOG_CODE")
    private String exceptionLogCode;

    @ApiModelProperty(value = "来源系统")
    @TableField("SYS_FROM")
    private String sysFrom;

    @ApiModelProperty(value = "状态（10：未处理 ，20：处理失败，30：已处理）")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "语言类型")
    @TableField("LANG_VER")
    private String langVer;

    @ApiModelProperty(value = "当前有效状态")
    @TableField("ALIVE_FLAG")
    private String aliveFlag;

    @ApiModelProperty(value = "扩展字段1")
    @TableField("EXTEND1")
    private String extend1;

    @ApiModelProperty(value = "扩展字段2")
    @TableField("EXTEND2")
    private String extend2;

    @ApiModelProperty(value = "扩展字段3")
    @TableField("EXTEND3")
    private String extend3;

    @ApiModelProperty(value = "扩展字段4")
    @TableField("EXTEND4")
    private String extend4;

    @ApiModelProperty(value = "扩展字段5")
    @TableField("EXTEND5")
    private String extend5;

    @ApiModelProperty(value = "创建者")
    @TableField("CREATE_USER")
    private String createUser;

    @ApiModelProperty(value = "创建者姓名")
    @TableField("CREATE_NAME")
    private String createName;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_DATE")
    private Date createDate;

    @ApiModelProperty(value = "修改者")
    @TableField("UPDATE_USER")
    private String updateUser;

    @ApiModelProperty(value = "修改者姓名")
    @TableField("UPDATE_NAME")
    private String updateName;

    @ApiModelProperty(value = "修改时间")
    @TableField("UPDATE_DATE")
    private Date updateDate;

    @ApiModelProperty(value = "版本号")
    @TableField("VERSION")
    private Integer version;

    @ApiModelProperty(value = "异常模块")
    @TableField("EXCEPTION_MODULE")
    private String exceptionModule;

    @ApiModelProperty(value = "异常接口路径")
    @TableField("EXCEPTION_URL")
    private String exceptionUrl;

    @ApiModelProperty(value = "请求参数")
    @TableField("REQUEST_PARAMETER")
    private String requestParameter;

    @ApiModelProperty(value = "异常码")
    @TableField("EXCEPTION_CODE")
    private String exceptionCode;

    @ApiModelProperty(value = "异常信息（缩略）")
    @TableField("EXCEPTION_MESSAGE")
    private String exceptionMessage;

    @ApiModelProperty(value = "异常详情")
    @TableField("EXCEPTION_DETAILS")
    private String exceptionDetails;

    @ApiModelProperty(value = "异常等级")
    @TableField("EXCEPTION_ESTATE")
    private String exceptionEstate;

    @ApiModelProperty(value = "异常类型（系统，job等）")
    @TableField("EXCEPTION_TYPE")
    private String exceptionType;

}
