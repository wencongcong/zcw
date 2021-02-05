package com.brokerage.service;

import com.brokerage.entity.Graphtemplate;
import com.brokerage.result.Result;

import java.util.Map;

public interface GraphtemplateService {

    public int insertOneGraphtemplate(Graphtemplate graphtemplate);

    public Result queryGraphtemplate();

    public Result updateGrap(Graphtemplate graphtemplate);

}
