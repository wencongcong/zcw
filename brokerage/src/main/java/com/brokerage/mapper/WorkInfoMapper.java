package com.brokerage.mapper;

import com.brokerage.entity.Work;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.ManagedBean;
import java.util.List;
import java.util.Map;

@Mapper
public interface WorkInfoMapper extends BaseMapper<Work> {

    public List<Work> queryAll(Map map);

    public Work queryAllstatue(Map map);
    //修改结算状态
    public int updatesettle(@Param("settlementstatus")String settlementstatus,@Param("hang")String hang,@Param("workid")String workid);
    //分组查询数量
    public List<Map<String,String>>queryStatus();

    //修改佣金结算状态
    public int updatevery(@Param("verify")String verify,@Param("workserved")String workserved,@Param("workid")String workid);
}
