package org.example.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Setmeal implements Serializable {
    private static final Long SerialVersionUID = 1L;

    public Long id;

    public Long categoryId;

    public String name;

    public BigDecimal price;

    public Integer status;

    public String code;

    public String description;

    public String image;

    @TableField(fill = FieldFill.INSERT)
    public LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    public LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    public Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    public Long updateUser;
}
