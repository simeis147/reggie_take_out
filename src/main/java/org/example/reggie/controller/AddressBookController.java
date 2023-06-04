package org.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.reggie.common.BaseContext;
import org.example.reggie.common.R;
import org.example.reggie.entity.AddressBook;
import org.example.reggie.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/addressBook")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增址
     * @param addressBook
     * @return
     */
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook){
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook: {}",addressBook);
        addressBookService.save(addressBook);
        return R.success(addressBook);
    }

    /**
     * 设置默认地址
     * @param addressBook
     * @return
     */
    @PutMapping("default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook){
        log.info("addressBook : {}", addressBook);
        LambdaUpdateWrapper<AddressBook> qw = new LambdaUpdateWrapper<>();
        qw.eq(AddressBook::getUserId, BaseContext.getCurrentId());
        qw.set(AddressBook::getIsDefault,0);
        // sql : update address_book set is_default = 0 where user_id = ?
        addressBookService.update(qw);

        addressBook.setIsDefault(1);
        addressBookService.updateById(addressBook);

        return R.success(addressBook);
    }

    /**
     * 根据ID查询地址
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<AddressBook> get(@PathVariable Long id){
        AddressBook addressBook = addressBookService.getById(id);
        if (addressBook!=null){
           return R.success(addressBook);
        }else {
           return R.error("没有找到该对象！");
        }
    }

    /**
     * 查询默认地址
     * @return
     */
    @GetMapping("default")
    public R<AddressBook> getDefault(){
        LambdaQueryWrapper<AddressBook> qw = new LambdaQueryWrapper<>();
        qw.eq(AddressBook::getUserId,BaseContext.getCurrentId());
        qw.eq(AddressBook::getIsDefault,1);

        AddressBook addressBook = addressBookService.getOne(qw);

        if (addressBook == null){
            return R.error("找不到对象");
        }else {
            return R.success(addressBook);
        }
    }

    @GetMapping("/list")
    public R<List<AddressBook>> listR(AddressBook addressBook){
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook: {}",addressBook);

        //条件构造器
        LambdaQueryWrapper<AddressBook> qw = new LambdaQueryWrapper<>();
        qw.eq(AddressBook::getUserId,addressBook.getUserId());
        qw.orderByDesc(AddressBook::getUpdateTime);

        List<AddressBook> list = addressBookService.list(qw);
        //SQL:select * from address_book where user_id = ? order by update_time desc
        return R.success(list);
    }

    @PutMapping
    public R<AddressBook> update(@RequestBody AddressBook addressBook){
        log.info("addressBook : {}",addressBook);
        addressBookService.updateById(addressBook);
        return R.success(addressBook);
    }
}
