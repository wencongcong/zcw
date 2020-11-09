package com.brokerage.mapper;

import com.brokerage.entity.Employee;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface EmployeeInfoMapper extends BaseMapper<Employee> {

    public Employee queryAllDe(@Param("ename")String ename);
}
