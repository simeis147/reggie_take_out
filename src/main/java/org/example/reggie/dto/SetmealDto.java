package org.example.reggie.dto;

import lombok.Data;
import org.example.reggie.entity.Setmeal;
import org.example.reggie.entity.SetmealDish;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;//套餐关联的菜品集合

    private String categoryName;//分类名称
}
