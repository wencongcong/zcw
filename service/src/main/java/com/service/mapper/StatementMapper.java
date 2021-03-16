package com.service.mapper;

import com.service.domeVo.FishordVo;
import com.service.entity.Stream;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StatementMapper extends BaseMapper<FishordVo> {

    public FishordVo queryAll(Map map);

    public FishordVo queryAllvs(Map map);
    //业绩看板
    public List<FishordVo> queryService(Map map);
    //地市看板
    public List<Map<String, String>> queryAddressCount(Map map);
    //产品排行
    public List queryProd(Map map);

    //总工单数
    public int queryFiCount(Map map);

    public List<Map<String, String>> queryQuTu(Map map);


}
