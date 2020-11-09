package com.role.service;

import com.role.entity.Rights;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RightsService {

    public List<Rights> queryAllr();

    public int insertOneRight(Rights rights);

    public int updateOneRight(Map map);

    public int deleteOneRight(int id);
}
