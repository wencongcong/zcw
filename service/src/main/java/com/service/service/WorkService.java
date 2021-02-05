package com.service.service;

import com.service.entity.Work;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WorkService {

    //插入工单
    public int insertOneWork(Work work);
    //查重
    public int chaChong(String workid);
    //查询
    public List<Work> queryAll(Map map);
    //根据状态分类查询数量
    public List<Map<String ,String >> groupCount(Map map);
    //查询工单ID是否重复
    public int chaw(String wid);
    //查询数量
    public int chacountw();
    //转营销
    public int sgchar(Work work);
    //修改备注
    public int Updateremark(Map map);
    //查询7工单ID
    public int chaorderno(int id);
    //根据7工单ID修改7工单状态
    public int Ustatos(String statos,int orderid);
    //修改工单属性
    public int Reminder(int reminder,int workid);
    //根据工单ID修改7工单状态和关联ID
    public int Ustatosoid(String statos,String status,int oid,String workid);
    //分配修改
    public int updateassign(Map map);
    //根据ID查询状态
    public String chaStatus(String workid);
    //修改备注
    public int uodateRemark(String remark, String workid);
    //修改7工单ID
    public int updateOrderId(int orderId, String workid);
    //根据orderID查询工单ID
    public String  chaWorkid(int orderId);
    //人工修改状态
    public int updateStatue(String status,String workid);
    //修改结算状态
    public int updatesettle(String settlementstatus,String verify,String completedtime,String workid);
    //查询自动反刷状态状态
    public String chasttle(String workid);
    //查询结算状态
    public String whetherisempty();

    public int changeWork(Map map);

}
