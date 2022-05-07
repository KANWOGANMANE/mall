package com.sjq.edu.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author kemp
 * @since 2022-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EduUpload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上传id
     */
    private String id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    /**
     * 逻辑删除，1删除，0未删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 名称
     */
    private String fname;

    /**
     *  别名
     */
    private String alias;

    /**
     *  全路径
     */
    private String fullpath;

    /**
     * 类型
     */
    private String ftype;

    /**
     * 排序
     */
    private Integer orderId;

    /**
     * 组名
     */
    private String fgroup;

    /**
     * 路径
     */
    private String fpath;


}
