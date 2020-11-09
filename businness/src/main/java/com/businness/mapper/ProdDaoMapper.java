package com.businness.mapper;


import com.businness.entity.ProdEX;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProdDaoMapper extends BaseMapper<ProdEX> {

    public List<ProdEX> queryAll(Map map);

    public int chaAcceptName(@Param("productsName")String productsName,@Param("depaname")String depaname);
}
