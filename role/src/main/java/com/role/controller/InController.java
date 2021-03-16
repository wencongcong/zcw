package com.role.controller;


import com.role.entity.Inform;
import com.role.entity.Terrain;
import com.role.enums.ErrorEnum;
import com.role.result.Result;
import com.role.service.ChannelService;
import com.role.service.InformService;
import com.role.service.TerrainService;
import com.role.service.ZcprefecturalService;
import org.apache.ibatis.annotations.Mapper;
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
    @Resource
    private TerrainService terrainService;

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

    @RequestMapping(value = "/tree",method = RequestMethod.POST)
    public Result treein(@RequestParam Map map){
        SimpleDateFormat sld=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String time=sld.format(date);
        Terrain terrain=new Terrain();
        terrain.setAreaname(map.get("areaname").toString());
        terrain.setCreatetime(time);
        terrain.setIsopen(1);
        int result=terrainService.additionTerrain(terrain);
        if (result>0){
            return Result.success(1,"添加成功");
        }else {
            return Result.fail(0,"添加失败");
        }
    }

    @RequestMapping(value = "/queryatree",method = RequestMethod.POST)
    public Result treeAll(@RequestParam Map map) {
        List<Terrain> list=terrainService.queryAll(map.get("areaname").toString());
        return Result.success(1,list);
    }

    @RequestMapping(value = "/updatetree",method = RequestMethod.POST)
    public Result updatetree(@RequestParam Map map) {
        Terrain terrain=new Terrain();
        terrain.setId(Integer.parseInt(map.get("id").toString()));
        terrain.setIsopen(Integer.parseInt(map.get("isopen").toString()));
        int result=terrainService.updateTerrain(terrain);
        if (result>0){
            return Result.success(1,"添加成功");
        }else {
            return Result.fail(0,"添加失败");
        }
    }

    @Resource
    private ZcprefecturalService zcprefecturalService;
    @RequestMapping(value = "/prefectural",method = RequestMethod.POST)
    public Result prefectural(@RequestParam Map map) {
        return Result.success(1,zcprefecturalService.queryAll());
    }
    @RequestMapping(value = "/prefecturalin",method = RequestMethod.POST)
    public Result prefecturalin(@RequestParam Map map) {
        return zcprefecturalService.insertZcpre(map);
    }

    @Resource
    private ChannelService channelService;
    @RequestMapping(value = "/channelquery",method = RequestMethod.POST)
    public Result channelquery(@RequestParam Map map) {
        return Result.success(1,channelService.queryAll());
    }

    @RequestMapping(value = "/channelinsert",method = RequestMethod.POST)
    public Result channelinsert(@RequestParam Map map) {
        return Result.success(1,channelService.insertChannel(map));
    }
    @RequestMapping(value = "/channeldelete",method = RequestMethod.POST)
    public Result channeldelete(@RequestParam(value = "id",defaultValue = "0")int id) {
        return Result.success(1,channelService.deleteChaneel(id));
    }

    @RequestMapping(value = "/zcpredelete",method = RequestMethod.POST)
    public Result zcpredelete(@RequestParam(value = "id",defaultValue = "0")int id) {
        return Result.success(1,zcprefecturalService.deleteZcpre(id));
    }

    @RequestMapping(value = "/zcpreupdate",method = RequestMethod.POST)
    public Result zcpreupdate(@RequestParam Map map) {
        return Result.success(1,zcprefecturalService.updateZcpre(map));
    }
}
