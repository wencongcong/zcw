package com.brokerage.mapper;

import com.brokerage.entity.OrderdetailsInfo;
import com.brokerage.entity.Workorderlist;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WorkorderListMapper extends BaseMapper<Workorderlist> {

    public List<Workorderlist> queryAllorderlist(Map map);

    public int insertWorkorderlist(Workorderlist workorderlist);

    public int oneOrderno(@Param("orderon")String orderon);

    public int queryAllordercount(Map map);

    public List<OrderdetailsInfo> queryOrderdetails();

}
