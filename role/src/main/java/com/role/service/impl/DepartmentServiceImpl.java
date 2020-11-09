package com.role.service.impl;

import com.role.entity.Department;
import com.role.mapper.DepartmentMapper;
import com.role.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> queryAlldp() {
        return departmentMapper.queryAlldp();
    }

    @Override
    public int insertOneDepartment(Department department) {
        return departmentMapper.insertOneDepartment(department);
    }

    @Override
    public int updateOneDepartment(Map map) {
        return departmentMapper.updateOneDepartment(map);
    }

    @Override
    public int deleteOneDepartment(int id) {
        return departmentMapper.deleteOneDepartment(id);
    }

    @Override
    public int chadeid(String dname) {
        return departmentMapper.chadeid(dname);
    }
}
