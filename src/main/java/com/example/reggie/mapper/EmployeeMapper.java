package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 董成鹏
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
