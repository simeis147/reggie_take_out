package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 董成鹏
 * @date 2023/05/04/ 21:47
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
