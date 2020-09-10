package org.jeecg.modules.user.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Generated;
import org.apache.ibatis.annotations.Options;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 用户模块
 * @Author: jeecg-boot
 * @Date:   2020-09-09
 * @Version: V1.0
 */
@Data
@TableName("users")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="users对象", description="用户模块")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
    @ApiModelProperty(value = "用户ID")
    private java.lang.Integer userid;
	/**openid*/
	@Excel(name = "openid", width = 15)
    @ApiModelProperty(value = "openid")
    private java.lang.String openid;
	/**unionid*/
	@Excel(name = "unionid", width = 15)
    @ApiModelProperty(value = "unionid")
    private java.lang.String unionid;
	/**昵称*/
	@Excel(name = "昵称", width = 15)
    @ApiModelProperty(value = "昵称")
    private java.lang.String username;
	/**头像*/
	@Excel(name = "头像", width = 15)
    @ApiModelProperty(value = "头像")
    private java.lang.String avatar;
	/**挑战计数*/
	@Excel(name = "挑战计数", width = 15)
    @ApiModelProperty(value = "挑战计数")
    private java.lang.Integer challengecount;
	/**PK计数*/
	@Excel(name = "PK计数", width = 15)
    @ApiModelProperty(value = "PK计数")
    private java.lang.Integer pkcount;
	/**抖音计数*/
	@Excel(name = "抖音计数", width = 15)
    @ApiModelProperty(value = "抖音计数")
    private java.lang.Integer shortcount;
	/**余额单位：分*/
	@Excel(name = "余额单位：分", width = 15)
    @ApiModelProperty(value = "余额单位：分")
    private java.lang.Integer balance;
}
