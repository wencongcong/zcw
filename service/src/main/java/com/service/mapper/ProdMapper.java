package com.service.mapper;

import com.service.entity.Prod;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProdMapper extends BaseMapper<Prod> {

    //查询
    public List<Prod> queryAll(Map map);

    //添加
    public int insetOneProd(Prod prod);

    //修改
    public int updateOneProd(Prod prod);

    //删除
    public int delteOneProd(@Param("id") int id,@Param("productsName")String productsName);

    //查询产品处理方法
    public String querySlname(@Param("slname")String slname);

    //产品模糊查询
    public List<String> queryProd(Map map);

    //查询产品id
    public int chaAccept(@Param("productsName")String productsName,@Param("depaname")String depaname);

    //查询数量
    public int chaName(@Param("productsName")String productsName,@Param("depaname")String depaname);
}
