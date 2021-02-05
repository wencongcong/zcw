package com.brokerage.mapper;

import com.brokerage.entity.Bssverify;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BssverifyMapper extends BaseMapper<Bssverify> {

    public String qerytoken(@Param("tel")String tel);
}
