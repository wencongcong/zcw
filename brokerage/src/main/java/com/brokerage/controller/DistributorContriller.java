package com.brokerage.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.brokerage.entity.Distributor;
import com.brokerage.entity.Graphtemplate;
import com.brokerage.result.Result;
import com.brokerage.service.AutoAcceptService;
import com.brokerage.service.DistributorService;
import com.brokerage.service.GraphtemplateService;
import com.brokerage.service.ZcdistributorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/dist")
public class DistributorContriller {


    @Resource
    private DistributorService distributorService;
    @Resource
    private ZcdistributorService zcdistributorService;
    @Resource
    private GraphtemplateService graphtemplateService;
    @Resource
    private AutoAcceptService autoAcceptService;

    @RequestMapping(value = "/distsquery", method = RequestMethod.POST)
    public Result distributoQuery(@RequestParam Map map) {
        List<Distributor> list = distributorService.queryallinformation(map);
        return Result.success(1, list);
    }

    @RequestMapping(value = "/distsinsert", method = RequestMethod.POST)
    public Result distributoinsert(@RequestParam Map map) {
        int result = distributorService.insertthedistribution(map);
        if (result > 0) {
            return Result.success(1, "添加成功");
        } else {
            return Result.success(0, "添加失败");
        }
    }

    @RequestMapping(value = "/distsbaidu", method = RequestMethod.POST)
    public Result distsbaidu(@RequestParam Map map) {
        int result = zcdistributorService.insertOneDistributor(map);
        if (result > 0) {
            return Result.success(1, "添加成功");
        } else {
            return Result.success(0, "添加失败");
        }
    }

    @RequestMapping(value = "/distsupdate", method = RequestMethod.POST)
    public Result distsupdate(@RequestParam Map map) {
        int result = distributorService.updateDistributor(map);
        if (result > 0) {
            return Result.success(1, "修改成功");
        } else {
            return Result.success(0, "修改失败");
        }
    }

    @RequestMapping(value = "/distsdele", method = RequestMethod.POST)
    @ResponseBody
    public Result distributodele(@RequestParam Map map) {

        JSONObject jsonObject = JSON.parseObject(map.get("data").toString());
        JSONObject date = jsonObject.getJSONObject("data");
        String length = jsonObject.get("length").toString();
        for (int i = 0; i < Integer.parseInt(length); i++) {
            distributorService.deleteDistributor(Integer.parseInt(date.get(i).toString()));
        }
        return Result.success(1, "运行结束");
    }

    @RequestMapping(value = "/querydist", method = RequestMethod.POST)
    public Result querydist(@RequestParam Map map) {

        return zcdistributorService.queryAllZcdis(map);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result upload(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("uploadFiles") MultipartFile uploadFiles, HttpServletRequest request, @RequestParam(defaultValue = "宽带", value = "uploadname") String uploadname) {
        Graphtemplate graphtemplate = new Graphtemplate();
        Date date = new Date();
        SimpleDateFormat sfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj = sfs.format(date);
        String realPath = "/www/wwwroot/CRM2/sourse/file";
        File dir = new File(realPath);
//        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
//            dir.mkdirs();
//        }
        try {

            String filename = uploadFile.getOriginalFilename();
            String filenames = uploadFiles.getOriginalFilename();
            //服务端保存的文件对象
            File fileServer = new File(dir, filename);
            File fileServers = new File(dir, filenames);
            //2，实现上传
            uploadFile.transferTo(fileServer);
            uploadFiles.transferTo(fileServers);
            String filePath = request.getScheme() + "://" +
                    request.getServerName() + ":"
                    + request.getServerPort();
            String filePathe = "/file/" + filename;
            String filePaths = "/file/" + filenames;
            //返回可供访问的网络路径
            graphtemplate.setBottomimg(filePaths);
            graphtemplate.setTopimg(filePathe);
            graphtemplate.setPicturename(uploadname);
            int result = graphtemplateService.insertOneGraphtemplate(graphtemplate);
            return Result.success(1, "上传成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail(0, "运行结束");
    }

    @RequestMapping(value = "/transferlist", method = RequestMethod.POST)
    public Result transferlist(@RequestParam Map map) {
        return autoAcceptService.sc(map);
    }

    @RequestMapping(value = "/disxiu", method = RequestMethod.POST)
    public Result disxiu(@RequestParam Map map) {
        return zcdistributorService.reamrk(map);
    }

    @RequestMapping(value = "/girquery", method = RequestMethod.POST)
    public Result girquery() {
        return graphtemplateService.queryGraphtemplate();
    }

    @RequestMapping(value = "/girxiu", method = RequestMethod.POST)
    @ResponseBody
    public Result girxiu(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("uploadFiles") MultipartFile uploadFiles, HttpServletRequest request, @RequestParam(defaultValue = "宽带", value = "uploadname") String uploadname, @RequestParam(defaultValue = "0", value = "id") int id) {
        Graphtemplate graphtemplate = new Graphtemplate();
        Date date = new Date();
        SimpleDateFormat sfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj = sfs.format(date);
        String realPath = "/www/wwwroot/CRM2/sourse/file";
        File dir = new File(realPath);
//        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
//            dir.mkdirs();
//        }
        try {

            String filename = uploadFile.getOriginalFilename();
            String filenames = uploadFiles.getOriginalFilename();
            //服务端保存的文件对象
            File fileServer = new File(dir, filename);
            File fileServers = new File(dir, filenames);
            //2，实现上传
            uploadFile.transferTo(fileServer);
            uploadFiles.transferTo(fileServers);
            String filePath = request.getScheme() + "://" +
                    request.getServerName() + ":"
                    + request.getServerPort();
            String filePathe = "/file/" + filename;
            String filePaths = "/file/" + filenames;
            //返回可供访问的网络路径
            graphtemplate.setBottomimg(filePaths);
            graphtemplate.setTopimg(filePathe);
            graphtemplate.setPicturename(uploadname);
            graphtemplate.setId(id);
            return graphtemplateService.updateGrap(graphtemplate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(1,"运行结束");
    }
}
