package com.businness.controller;

import com.businness.entity.Commodity;
import com.businness.result.Result;
import com.businness.service.CommodityService;
import com.businness.service.FishordersDaoService;
import com.businness.service.WorkDaoService;
import org.apache.poi.hssf.record.SSTRecord;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/uploads")
public class UploadController {


    @Resource
    private CommodityService commodityService;
    @Resource
    private WorkDaoService workDaoService;


    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public Result upload(@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request, @RequestParam int wid, @RequestParam String uploadname) {
        Commodity commodity=new Commodity();
        Date date=new Date();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj=sfs.format(date);
        String realPath = "/www/wwwroot/CRM2/sourse/file";
        File dir = new File(realPath);
//        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
//            dir.mkdirs();
//        }
        try {
            String filename = uploadFile.getOriginalFilename();
            //服务端保存的文件对象
            File fileServer = new File(dir, filename);
            //2，实现上传
            uploadFile.transferTo(fileServer);
//            String filePath = request.getScheme() + "://" +
//                    request.getServerName() + ":"
//                    + request.getServerPort()
            String filePath="/file/"+filename;
            //3，返回可供访问的网络路径
            commodity.setCommodityt(filePath);
            commodity.setWid(wid);
            commodity.setUploadtime(sj);
            commodity.setUploadname(uploadname);
            int result=commodityService.insertOne(commodity);
            return Result.success(1,filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.fail(0,"运行结束");
    }

    @RequestMapping(value = "queryUpload",method = RequestMethod.POST)
    public Result queryAll(@RequestParam int wid){

        List<Commodity>list=commodityService.queryOne(wid);
        if (list.size()!=0){
            return Result.success(1,list);
        }else{
            return Result.fail(0,"查询失败");
        }
    }

    @RequestMapping(value = "/url",method = RequestMethod.POST)
    @ResponseBody
    public Result url(@RequestParam Map map){

        return Result.success(1,"success");
    }

    @RequestMapping(value = "/workcount",method = RequestMethod.POST)
    public Result workcount(@RequestParam Map map){
         Map maps=new HashMap();
         maps.put("workareacount", workDaoService.workcount(map));
         maps.put("workchannlcount",workDaoService.workchannlcunt(map));
         maps.put("workstatuscount",workDaoService.workstatuscunt(map));
         maps.put("worksernamecount",workDaoService.worksernamecunt(map));
         maps.put("chaworkcount",workDaoService.workcountsum(map));
        return Result.success(1,maps);
    }

}
