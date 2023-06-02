package org.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.example.reggie.common.R;
import org.example.reggie.entity.Employee;
import org.example.reggie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //1. 讲页面提交的密码password进行MD5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2. 查询出数据库是否有该员工
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(lqw);

        //3. 判断员工是否在数据库中
        if (emp == null){
            return R.error("登录失败");
        }

        //4. 判断员工密码是否正确
        if (!emp.getPassword().equals(password)){
            return R.error("登录失败！");
        }

        //5. 判断员工是否被冻结 0被冻结
        if (emp.getStatus() == 0){
            return R.error("用户被冻结！");
        }

        //6 登录成功，将员工id存入Session并返回登录成功结果
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
        // 清除session中保存的当前员工的ID
        request.getSession().removeAttribute("employee");
        return R.success("退出成功！");
    }

    /**
     * 新增员工
     * @param request
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工：{}",employee.toString());

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//
//        //获得当前登录用户ID
//        Long empId = (Long) request.getSession().getAttribute("employee");
//
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);

        // INSERT INTO employee ( id, name, username, password, phone, sex, id_number, create_time, update_time, create_user, update_user )
        // VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )
        employeeService.save(employee);

        return R.success("新增员工成功");
    }

    /**
     * 员工分页
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> pageR(int page, int pageSize, String name){
        log.info("page = {} , pageSize = {} , name = {}",page,pageSize,name);

        // 分页构造器
        Page pageInfo = new Page(page, pageSize);

        // 构造条件构造器
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
        lqw.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        lqw.orderByDesc(Employee::getUpdateTime);

        // SELECT id,name,username,password,phone,sex,id_number,status,create_time,update_time,create_user,update_user
        // FROM employee
        // ORDER BY update_time DESC LIMIT ?
        employeeService.page(pageInfo,lqw);
        return R.success(pageInfo);
    }

    /**
     * 修改员工信息
     * @param request
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> updateById(HttpServletRequest request,@RequestBody Employee employee){
        log.info("需要修改的ID {}",employee);
        long id = Thread.currentThread().getId();
        log.info("EmployeeController的线程ID为 {}",id);
//        Long empId = (Long) request.getSession().getAttribute("employee");
//
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empId);

        // UPDATE employee SET name=?, username=?, password=?, phone=?, sex=?, id_number=?, status=?, create_time=?, update_time=?, create_user=?, update_user=?
        // WHERE id=?
        employeeService.updateById(employee);

        return R.success("员工信息修改成功");
    }

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("用户员工ID {}",id);

        Employee employee = employeeService.getById(id);

        if (employee != null){
            return R.success(employee);
        }

        return R.error("没有查询到对应员工的信息");
    }

}
