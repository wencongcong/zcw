package com.role.service;

import com.role.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    public List<Department> queryAlldp();

    public int insertOneDepartment(Department department);

    public int updateOneDepartment(Map map);

    public int deleteOneDepartment(int id);

    public int chadeid(String dname);
}
