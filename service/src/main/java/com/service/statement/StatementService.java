package com.service.statement;

import com.github.pagehelper.PageInfo;
import com.service.domeVo.FishordVo;
import com.service.domeVo.FishorderratioVo;

import java.util.List;
import java.util.Map;

public interface StatementService {

    public FishordVo queryAll(Map map);

    public FishorderratioVo queryAllRatio(Map map);

    public List<Map<String, String>> queryAddressCount(Map map);

    public PageInfo<FishorderratioVo> queryProd(Map map);

    public List<FishordVo> queryService(Map map);

    public List<Map<String, String>> queryQuTu(Map map);
}
