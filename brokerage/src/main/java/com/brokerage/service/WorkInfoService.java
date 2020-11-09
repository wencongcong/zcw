package com.brokerage.service;

import com.brokerage.entity.Work;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WorkInfoService {

    public Work queryAllstatue(Map map);
    //修改成功工单状态
    public int updatesettle(String settlementstatus,String hang,String workid);
    //条件查询
    public List<Work> queryAll(Map map);
    //修改佣金结算状态
    public int updatevery(String verify,String workserved,String workid);
}
