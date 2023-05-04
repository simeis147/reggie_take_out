package com.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie.entity.Category;
import org.springframework.stereotype.Service;

/**
 * @author 董成鹏
 * @date 2023/05/04/ 19:07
 */

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
