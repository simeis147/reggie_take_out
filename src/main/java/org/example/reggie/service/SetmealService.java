package org.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.reggie.dto.SetmealDto;
import org.example.reggie.entity.Setmeal;
import org.example.reggie.entity.SetmealDish;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时还要删除套餐和菜品关联的数据
     * @param ids
     */
    void removeWithDish(List<Long> ids);

    SetmealDto updateByIdWithDish(Long id);


    void updateSetmealWithDish(SetmealDto setmealDto);
}
