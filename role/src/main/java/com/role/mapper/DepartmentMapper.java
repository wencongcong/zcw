package com.role.mapper;

import com.role.entity.Department;
import com.role.entity.Rights;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    public List<Department> queryAlldp();

    public int insertOneDepartment(Department department);

    public int updateOneDepartment(Map map);

    public int deleteOneDepartment(@Param("id")int id);

    //查id
    public int chadeid(@Param("dname")String dname);
}
