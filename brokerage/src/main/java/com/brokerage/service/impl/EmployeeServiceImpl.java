package com.brokerage.service.impl;

import com.brokerage.entity.Employee;
import com.brokerage.mapper.EmployeeInfoMapper;
import com.brokerage.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("emplService")
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeInfoMapper employeeInfoMapper;

    @Override
    public Employee queryAllDe(String ename) {
        return employeeInfoMapper.queryAllDe(ename);
    }
}
