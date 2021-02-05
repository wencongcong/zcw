package com.businness.controller;

import com.businness.entity.CommissionEx;
import com.businness.result.Result;
import com.businness.service.CommissionExService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/miss")
public class ComController {

    @Resource
    private CommissionExService commissionExService;

    @RequestMapping(value = "/querycomm",method = RequestMethod.POST)
    public Result queryAllComm(@RequestParam Map map){
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<CommissionEx> lists=commissionExService.queryAllComm(map);
        PageInfo<CommissionEx> pageInfo = new PageInfo(lists);

        return Result.success(1,pageInfo);
    }
}
