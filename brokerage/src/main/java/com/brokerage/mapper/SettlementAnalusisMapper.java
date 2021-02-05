package com.brokerage.mapper;

import com.brokerage.entity.SettlementAnalusis;
import com.brokerage.result.Result;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SettlementAnalusisMapper extends BaseMapper<SettlementAnalusis> {

    public List<SettlementAnalusis> queryAll(Map map);


    public int insertSett(SettlementAnalusis settlementAnalusis);

    public int queryOneassetnumberormonths(@Param("assetnumber")String assetnumber,@Param("months")String months);

    public int updateSettStatus(@Param("orderno")String ordern,@Param("status")String status);

    public int querySettStatusCount(@Param("orderno")String ordern,@Param("status")String status);
}
