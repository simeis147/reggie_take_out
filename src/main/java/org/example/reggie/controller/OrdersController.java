package org.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.example.reggie.common.R;
import org.example.reggie.entity.Orders;
import org.example.reggie.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据， {}",orders);
        ordersService.submit(orders);
        return R.success("下单成功！");
    }

    @GetMapping("/page")
    public R<Page> pageR(int page, int pageSize, String number){
        log.info("page : {}, pageSize : {}, number : {}",page,pageSize,number);

        // 分页构造器
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Orders> qw = new LambdaQueryWrapper<>();
        qw.like(StringUtils.isNotEmpty(number),Orders::getNumber,number);
        qw.orderByDesc(Orders::getOrderTime);

        ordersService.page(pageInfo,qw);

        return R.success(pageInfo);
    }
}
