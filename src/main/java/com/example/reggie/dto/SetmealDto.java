package com.example.reggie.dto;

import com.example.reggie.entity.Setmeal;
import com.example.reggie.entity.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * @author 董成鹏
 * @date 2023/05/06/ 10:54
 */
@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
