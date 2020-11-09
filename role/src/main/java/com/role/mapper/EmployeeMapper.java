package com.role.mapper;

import com.role.entity.Employee;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    public List<Employee> queryAllem();

    public int insertOneEmployee(Employee employee);

    public int updateOneEmployee(Map map);

    public int deleteOneEmployee(@Param("id")int id);

    //登录
    public Employee Login(@Param("ephone")String ephone,@Param("epwd")String epwd);

    public int chaOneEmplyee(@Param("ephone")String ephone,@Param("oldpwd")String oldpwd);

    public List<Employee> queryAllDepa(@Param("ename")String ename);
}
