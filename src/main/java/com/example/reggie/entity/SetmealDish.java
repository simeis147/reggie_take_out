package com.example.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 董成鹏
 * @date 2023/05/06/ 10:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetmealDish implements Serializable {
    private static final long SerialVersionUID = 1L;

    private Long id;

    private Long categoryId;

    private String name;

    private Integer status;

    private String code;

    private String description;

    private String image;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long updateUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long createUser;

    private Integer isDeleted;
}
