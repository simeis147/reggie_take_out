package org.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.reggie.entity.Category;

public interface CategoryService extends IService<Category> {
    /**
     * 根据ID删除分类
     * @param ids
     */
    void remove(Long ids);
}
