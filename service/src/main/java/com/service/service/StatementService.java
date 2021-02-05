package com.service.service;

import com.service.mapper.FishordersMapper;
import com.service.mapper.WorkMapper;
import com.service.result.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("statementService")
public class StatementService {

    @Resource
    private FishordersMapper fishordersMapper;
    @Resource
    private WorkMapper workMapper;



    public Result statement(){

        return Result.success();
    }

}
