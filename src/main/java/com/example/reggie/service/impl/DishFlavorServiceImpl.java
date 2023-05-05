package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie.entity.DishFlavor;
import com.example.reggie.mapper.DishFlavorMapper;
import com.example.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * @author 董成鹏
 * @date 2023/05/05/ 10:42
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
