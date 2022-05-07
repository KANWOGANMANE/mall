package com.sjq.commonutils.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Kemp
 * @create 2022/4/13 11:14
 */
@Data
public class RegisterVo {
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
}
