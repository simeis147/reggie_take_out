package org.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.reggie.common.R;
import org.example.reggie.entity.Category;
import org.example.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增菜品
     * @param category
     * @return
     */
    @PostMapping
    public R<String> insertCategory(@RequestBody Category category){
        categoryService.save(category);
        if (category != null){
            return R.success("新增员工成功");
        }
        return R.error("新增员工失败");
    }

    /**
     * 分页菜品
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> pageR(int page, int pageSize){
        Page<Category> pageInfo = new Page<Category>(page, pageSize);

        LambdaQueryWrapper<Category> qw = new LambdaQueryWrapper<>();
        qw.orderByAsc(Category::getType).orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        categoryService.page(pageInfo,qw);
        return R.success(pageInfo);
    }

    /**
     * 删除菜品
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> deleteById(Long ids){
        log.info("删除的IDS {}",ids);
        categoryService.remove(ids);
        return R.success("删除成功！");
    }

    /**
     * 根据ID修改分类信息
     * @param category
     * @return
     */
    @PutMapping
    public R<String> updateById(@RequestBody Category category){
        log.info("更新菜品 {}",category.toString());
        categoryService.updateById(category);
        return R.success("更新菜品成功！");
    }

    /**
     * 菜品管理页面根据条件查询分页数据
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> categoryList(Category category){
        // 条件构造器
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 添加条件
        categoryLambdaQueryWrapper.eq(category.getType() != null,Category::getType,category.getType());
        // 添加排序条件
        categoryLambdaQueryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(categoryLambdaQueryWrapper);

        return R.success(list);
    }
}
