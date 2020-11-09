package com.role.service;

import com.role.entity.Employee;
import com.role.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public List<Employee> queryAllem();

    public int insertOneEmployee(Employee employee);

    public int updateOneEmployee(Map map);

    public int deleteOneEmployee(int id);

    public Result Login(String ephone, String epwd);

    public int chaOneEmplyee(String ephone,String oldpwd);

    public List<Employee> queryAllDepa(String ename);

}
