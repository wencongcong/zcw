package com.role.service.impl;

import com.role.entity.Employee;
import com.role.mapper.EmployeeMapper;
import com.role.result.Result;
import com.role.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> queryAllem() {
        return employeeMapper.queryAllem();
    }

    @Override
    public int insertOneEmployee(Employee employee) {
        return employeeMapper.insertOneEmployee(employee);
    }

    @Override
    public int updateOneEmployee(Map map) {
        return employeeMapper.updateOneEmployee(map);
    }

    @Override
    public int deleteOneEmployee(int id) {
        return employeeMapper.deleteOneEmployee(id);
    }

    @Override
    public Result Login(String ephone, String epwd) {
        Employee employee= employeeMapper.Login(ephone, epwd);
        if (employee!=null){
            return Result.success(1,employee);
        }
        return Result.fail(0,"登录失败");
    }

    @Override
    public int chaOneEmplyee(String ephone,String oldpwd) {
        return employeeMapper.chaOneEmplyee(ephone,oldpwd);
    }

    @Override
    public List<Employee> queryAllDepa(String ename) {
        return employeeMapper.queryAllDepa(ename);
    }
}
