package com.businness.mapper;

import com.businness.entity.CommissionEx;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommissionExMapper extends BaseMapper<CommissionEx> {

    public int insertAllComm(CommissionEx commissionEx);

    public List<CommissionEx> queryAllComm(Map map);
}
