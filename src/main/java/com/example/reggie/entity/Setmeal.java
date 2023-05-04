package com.example.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 董成鹏
 * @date 2023/05/04/ 21:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Setmeal implements Serializable {
    private static final long serialVersionUID = 1;

    private Long id;

    private Long categoryId;

    private String name;

    private BigDecimal price;

    private Integer status;

    private String code;

    private String description;

    private String image;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    private Integer isDeleted;
}
