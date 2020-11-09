package com.service.service;

import com.service.entity.Work;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WorkService {

    public int insertOneWork(Work work);
    //查重
    public int chaChong(String workid);
    //查询
    public List<Work> queryAll(Map map);
    public List<Map<String ,String >> groupCount(Map map);
    //查询工单ID是否重复
    public int chaw(String wid);
    //查询数量
    public int chacountw();
    //转营销
    public int sgchar(Map map);

    public int Updateremark(Map map);

    public int chaorderno(int id);

    //根据7工单ID修改7工单状态
    public int Ustatos(String statos,int orderid);

    public int Reminder(int reminder,int workid);

    public int Ustatosoid(String statos,int oid,String workid);
    //分配修改
    public int updateassign(Map map);
    //根据ID查询状态
    public String chaStatus(String workid);
    //修改备注
    public int uodateRemark(String remark, String workid);

    public int updateOrderId(int orderId, String workid);
    //根据orderID查询工单ID
    public int chaWorkid(int orderId);

    public int updateStatue(String status,String workid);

    public int updatesettle(String settlementstatus,String verify,String completedtime,String workid);

    public String chasttle(String workid);

    public String whetherisempty();
}
