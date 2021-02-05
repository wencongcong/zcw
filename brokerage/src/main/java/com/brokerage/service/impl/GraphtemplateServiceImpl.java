package com.brokerage.service.impl;

import com.brokerage.entity.Graphtemplate;
import com.brokerage.mapper.GraphtemplateMapper;
import com.brokerage.result.Result;
import com.brokerage.service.GraphtemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("graphtemplateService")
public class GraphtemplateServiceImpl implements GraphtemplateService {

    @Resource
    private GraphtemplateMapper graphtemplateMapper;

    @Override
    public int insertOneGraphtemplate(Graphtemplate graphtemplate) {
        return graphtemplateMapper.insertOneGraphtemplate(graphtemplate);
    }

    @Override
    public Result queryGraphtemplate() {

        List<Graphtemplate> list= graphtemplateMapper.queryGraphtemplate();
        return Result.success(1,list);
    }

    @Override
    public Result updateGrap(Graphtemplate graphtemplate) {
        int result=graphtemplateMapper.updateGrap(graphtemplate);
        if (result>0){
            return Result.success(1,"修改成功");
        }else {
            return Result.success(0,"修改失败");
        }
    }
}
