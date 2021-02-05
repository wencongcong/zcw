package com.brokerage.mapper;

import com.brokerage.entity.Distributor;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DistributorMapper extends BaseMapper<Distributor> {

    public int insertthedistribution(Distributor distrinutor);

    public List<Distributor> queryallinformation(Map map);

    public int deleteDistributor(@Param("id")int id);

    public int updateDistributor(Map map);
}
