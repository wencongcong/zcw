package com.role.controller;


import com.role.entity.Inform;
import com.role.enums.ErrorEnum;
import com.role.result.Result;
import com.role.service.InformService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/info")
public class InController {

    @Resource
    private InformService informService;

    @RequestMapping(value = "/chaxun",method = RequestMethod.POST)
    public Result querAll(@RequestParam Map map){

        List<Inform> listinfo=informService.querAll(map);
        return Result.success(1,listinfo);
    }

    @RequestMapping(value = "/chainfor",method = RequestMethod.POST)
    public Result insertOneInfor(@RequestParam Map map){

        Date date=new Date();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj=sfs.format(date);

        Inform inform=new Inform();
        inform.setName(map.get("name").toString());
        inform.setIsoffor(Integer.parseInt(map.get("isoffor").toString()));
        inform.setCtime(sj);
        inform.setDisno(Integer.parseInt(map.get("disno").toString()));
        int reslt=informService.insertOneInform(inform);
        if (reslt>0){
            return Result.success(1,reslt);
        }else{
            return Result.success(0, ErrorEnum.FIAL_ERROR);
        }
    }

    @RequestMapping(value = "/xiugai",method = RequestMethod.POST)
    public Result updateOneInfor(@RequestParam Map map){
        int result=informService.updateOneInform(map);
        if (result>0){
            return Result.success(1,result);
        }else{
            return Result.success(0, ErrorEnum.CHA_ERROR);
        }
    }

    @RequestMapping(value = "/dele",method = RequestMethod.POST)
    public Result deteleOneInfor(@RequestParam(defaultValue = "0",value = "id")String id){
        int result=informService.deleteOneInform(Integer.parseInt(id));
        if (result>0){
            return Result.success(1,result);
        }else{
            return Result.success(0, "删除失败");
        }
    }

}
