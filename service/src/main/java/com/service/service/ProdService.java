package com.service.service;

import com.service.entity.Prod;
import com.service.result.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProdService {


    //查询
    public List<Prod> queryAll(Map map);

    //添加
    public int insetOneProd(Prod prod);

    //修改
    public int updateOneProd(Prod prod);

    //删除
    public int delteOneProd(int id,String productsName);

    //查询产品处理方法
    public String querySlname(String slname);

    //产品模糊查询
    public List<String> queryProd(Map map);

    //查询产品id
    public int chaAccept(String accept,String depaname);

    public Result queryAllinsert(MultipartFile file) throws Exception;

    public int chaName(String accept,String depaname);
}
