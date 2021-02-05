package com.brokerage.mapper;

import com.brokerage.entity.Graphtemplate;
import com.util.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GraphtemplateMapper extends BaseMapper<Graphtemplate> {

    public int insertOneGraphtemplate(Graphtemplate graphtemplate);

    public List<Graphtemplate> queryGraphtemplate();

    public int updateGrap(Graphtemplate graphtemplate);
}
