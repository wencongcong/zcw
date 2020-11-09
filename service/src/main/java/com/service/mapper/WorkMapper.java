package com.service.mapper;

import com.service.entity.Work;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WorkMapper {
    //工单查询
    public List<Work> queryAll(Map map);
    //查询数量
    public List<Map<String ,String >> groupCount(Map map);
    //状态转换
    public int transition(Map map);
    //插入
    public int insertOneWork(Work work);
    //查重
    public int chaChong(@Param("workid")String workid);
    //查询工单ID是否重复
    public int chaw(@Param("wid") String wid);
    //查询数量
    public int chacountw();
    //转营销
    public int sgchar(Map map);

    public int Updateremark(Map map);

    public int chaorderno(@Param("id")int id);
    //根据7工单ID修改7工单状态
    public int Ustatos(@Param("statos") String statos, @Param("orderid") int orderid);

    public int Reminder(@Param("reminder") int reminder, @Param("workid") int workid);

    public int Ustatosoid(@Param("statos") String statos,@Param("orderid")int oid,@Param("workid") String workid);
    //分配修改
    public int updateassign(Map map);
    //根据ID查询状态
    public String chaStatus(@Param("workid")String workid);
    //修改备注
    public int uodateRemark(@Param("remark") String remark, @Param("workid") String workid);
    //修改orderID
    public int updateOrderId(@Param("orderId") int orderId, @Param("workid") String workid);
    //根据orderID查询工单ID
    public int chaWorkid(@Param("orderId") int orderId);

    public int updateStatue(@Param("status") String status, @Param("workid") String workid);

    public int updatesettle(@Param("settlementstatus")String settlementstatus,@Param("verify")String verify,@Param("completedtime")String completedtime,@Param("workid")String workid);

    public String chasttle(@Param("workid")String workid);
    //判断是否为空
    public String whetherisempty();
}
