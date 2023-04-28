package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie.Common.R;
import com.example.reggie.entity.Employee;
import com.example.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author Dongcp
 * @date 2023/04/11/ 10:49
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(employeeLambdaQueryWrapper);

        if (emp == null){
            return R.error("登录失败");
        }

        if (!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }

        if (emp.getStatus() == 0){
            return R.error("账号已经禁用！");
        }

        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
   }

    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功！");
    }


    /**
     * 新增员工
     * @param request
     * @param employee
     * @return
     */
    @PostMapping()
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        log.info("新增员工信息：{}",employee.toString());

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        Long empId = (Long) request.getSession().getAttribute("employee");

        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        employeeService.save(employee);

        return R.success("新增员工成功！");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page = {} , pageSize = {}, name = {}",page,pageSize,name);

        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        employeeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee){
        log.info(employee.toString());

        Long empId = (Long) request.getSession().getAttribute("employee");

        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empId);

        employeeService.updateById(employee);

        return R.success("修改信息成功！");
    }

    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息...");
        Employee emp = employeeService.getById(id);

        if (emp != null){
            return R.success(emp);
        }
        return R.error("查无此人");
    }


}
