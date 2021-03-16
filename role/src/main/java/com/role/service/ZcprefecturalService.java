package com.role.service;

import com.role.entity.Zcprefectural;
import com.role.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZcprefecturalService {

    public List<Zcprefectural> queryAll();

    public Result insertZcpre(Map map);


    public int updateZcpre(Map map);

    public int deleteZcpre(int id);
}
