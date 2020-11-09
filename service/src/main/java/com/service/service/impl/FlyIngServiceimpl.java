package com.service.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.service.entity.Fishorders;
import com.service.enums.ErrorEnum;
import com.service.mapper.*;
import com.service.result.Result;
import com.service.service.FlyIngService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebListener
@Service("flyIngService")
public class FlyIngServiceimpl implements FlyIngService {


    private static final String URL = "https://feiyu.oceanengine.com/crm/v2/openapi/pull-clues/";
    // public static String signature_key = "V0JESjUzSzdBQjBD"; // 设置为自己的加密秘钥
    // public static String token = "5089ecd40260c8d0e1a56204a8bea5890e728896"; // 设置为自己的token

    @Resource
    private FishordersMapper fishordersMapper;

    @Resource
    private CustMapper custMapper;
    @Resource
    private WorkMapper workMapper;
    @Resource
    private ProdMapper prodMapper;
    @Resource
    private OrderInfoMapper orderInfoMapper;


    @Override
    public JSONObject queryFly(String signature_key, String token) throws URISyntaxException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); //得到前一天bai
        Date date1 = calendar.getTime();
        //使用广告主自己的信息生成实例
        PullClue pullClue = new PullClue(signature_key, token);
        String startTime = sd.format(date1);
        String endTime = sd.format(date);
        int page = 1;
        int pageSize = 100;
        //调用拉取线索方法获取线索数据，内部使用http请求调用crm服务
        String clues = pullClue.crmPullClues(startTime, endTime, page, pageSize);
        //返回结果解析成json
        JSONObject jobj = JSON.parseObject(clues);
        return jobj;
    }

    Timer timer = new Timer();

    @Override
    @Scheduled(cron = "0 */5 * * * ?")
    public Result queryFlyTime() throws Exception {

        Fishorders fishorders=new Fishorders();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd=null;
        synchronized (this){
            Thread ts=new Thread();
            ts.start();
            Map map = new HashMap();
            map.put("signature_key0", "V0JESjUzSzdBQjBD");
            map.put("token0", "5089ecd40260c8d0e1a56204a8bea5890e728896");
            map.put("signature_key1", "TDQ4RE0wMjFNTEZa");
            map.put("token1", "3f21fbc6a91efc02c285aea8e34de502cb649ed7");
            map.put("signature_key2", "RFJKVktERE1LSTBK");
            map.put("token2", "243e5fe408768b66b25c1e136d031aaf4e9bb637");
            map.put("signature_key3", "REc4TzlMVFM0NDc5");
            map.put("token3", "02b23ff0b80869e46dbdfcef5819f4899a0d1f57");
            map.put("signature_key4", "WThSN0tMVjUwSzZV");
            map.put("token4", "7e60886ab80aa7881789a6de6fb8879bcec5df92");
            map.put("signature_key5", "VEVJRUcwVzVBV0k0");
            map.put("token5", "a4f7f345aaaa816d78ce1272c71c96a13d41f103");
            map.put("signature_key6", "V0ZNVjNEQ1NPM1RI");
            map.put("token6", "3b6eafb8719412d5b55e663f6742901a29418656");

            for (int i = 0; i <=6; i++) {
                JSONObject rsult = queryFly(map.get("signature_key" + i).toString(), map
                        .get("token" + i).toString());
                JSONArray data = rsult.getJSONArray("data");
                if (data.equals("")||data==null) {
                    continue;
                } else {
                    JSONObject ss = null;
                    for (Object object : data) {
                        ss = ((JSONObject) object);
                        if (fishordersMapper.chaFid(ss.getString("clue_id"))>0){
                            continue;
                        }
                        fishorders.setWorkid(ss.getString("clue_id") == "" ? null : ss.getString("clue_id"));
                        fishorders.setChannel("信息流_飞鱼");
                        fishorders.setFlow(ss.getString("appname") == "" ? null : ss.getString("appname"));
                        fishorders.setPhone(ss.getString("telphone") == "" ? null : ss.getString("telphone"));
                        fishorders.setName(ss.getString("name") == "" ? null : ss.getString("name"));
                        fishorders.setIdcard("");
                        fishorders.setInterior(ss.getString("remark_dict") == "" ? null : ss.getString("remark_dict"));
                        fishorders.setAccept("");
                        fishorders.setServicename("");
                        fishorders.setUploginno("");
                        fishorders.setStatos("待外呼");
                        fishorders.setAmount("");
                        Long time=new Long(ss.getString("create_time") == "" ? null : ss.getString("create_time"));
                        String d=sfs.format(new Date(Long.valueOf(time+"000")));
                        fishorders.setOrdertime(d);
                        fishorders.setPayment("");
                        fishorders.setRemark(ss.getString("remark") == "" ? null : ss.getString("remark"));
                        fishorders.setToproomotelinks(ss.getString("external_url") == "" ? null : ss.getString("external_url"));
                        fishorders.setNameofadvertiser(ss.getString("adv_name") == "" ? null : ss.getString("adv_name"));
                        String address=ss.getString("location").toString();
                        if (address.indexOf("+")>0){
                            sd=address.substring(address.indexOf("+")+1,address.length());
                            fishorders.setAddress(sd+"市");
                        }else{
                            fishorders.setAddress(ss.getString("location") == "" ? null : ss.getString("location"));
                        }
                        int rs=fishordersMapper.insertOneFish(fishorders);
                        if (rs==0){
                            return Result.fail(0,"插入失败");
                        }
                    }

                }
            }
        }
        return Result.success(1,"运行成功");
    }

    @Override
    public int chaFid(String wordid) {
        return fishordersMapper.chaFid(wordid);
    }

    @Override
    public List<Fishorders> queryAll(Map map) {
        return fishordersMapper.queryAll(map);
    }

    @Override
    public List<Map<String, Object>> grouby(Map map) {
        return fishordersMapper.grouby(map);
    }

    @Override
    public int uploginno(String servicename, int id) {
        return fishordersMapper.uploginno(servicename, id);
    }

    @Override
    public int insertOneFish(Fishorders fishorders) {
        return fishordersMapper.insertOneFish(fishorders);
    }

    @Override
    public int chaOneTian(Map map) {
        return fishordersMapper.chaOneTian(map);
    }

    @Override
    public int reamrk(Map map) {
        return fishordersMapper.reamrk(map);
    }

    @Override
    @Transactional
    public Result sgcha(Map map) {
        Date date=new Date();
        int cid=0;
        int wid=0;
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj=sfs.format(date);
        Map maps=new HashMap();
        Map mapa=new HashMap();
        int count=custMapper.chacount();
        cid=count+1;
        if (custMapper.chawork(String.valueOf(cid))>1){
            return Result.fail(0, ErrorEnum.CHONG_FU);
        }else if(custMapper.chachongcount(map.get("custname").toString())>0){
            cid=custMapper.chachongname(map.get("custname").toString());
            int workcount=workMapper.chacountw();
            wid=workcount+1;
            if (workMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                mapa.put("workid", wid);
                mapa.put("custid", cid);
                int proid=prodMapper.chaAccept(map.get("accept").toString(),map.get("depaname").toString());
                mapa.put("acceptid", proid);
                if (map.get("orderno").toString()!=null&&map.get("orderno").toString()!=""){
                    orderInfoMapper.insertOne(map.get("orderno").toString());
                    int orderid=orderInfoMapper.chaorderId(map.get("orderno").toString());
                    mapa.put("orderid", orderid);
                }else{
                    mapa.put("orderid", 0);
                }
                mapa.put("serviceName", map.get("serviceName").toString());
                mapa.put("remark", map.get("remark").toString());
                mapa.put("status", map.get("status").toString());
                mapa.put("broadband", map.get("broadband") == "" ? null : map.get("broadband").toString());
                mapa.put("appointmenttime", map.get("appointmenttime").toString());
                mapa.put("paymentamount",map.get("paymentamount").toString());
                mapa.put("xdtime", sj);
                mapa.put("channl",map.get("channl").toString());
                mapa.put("workserved",map.get("workserved").toString());
                int wresult=workMapper.sgchar(mapa);
                if (wresult>0){
                    fishordersMapper.upstatos("营销成功",1,Integer.parseInt(map.get("id").toString()));
                    return Result.success(1,"营销成功");
                }else{
                    return Result.fail(0,ErrorEnum.FIAL_ERROR);
                }
            }
        }else {
            maps.put("cid", cid);
            maps.put("custname", map.get("custname").toString());
            maps.put("custidcard", map.get("custidcard") == "" ? null : map.get("custidcard").toString());
            maps.put("custphone", map.get("custphone").toString());
            maps.put("custaddress", map.get("custaddress").toString());
            maps.put("custarea", map.get("custarea").toString());
            maps.put("custremark", map.get("custremark").toString());
            maps.put("custcreater", map.get("custcreater").toString());
            maps.put("custcreatertime", sj);
            maps.put("custreserved", "");
            int workcount=workMapper.chacountw();
            wid=workcount+1;
            if (workMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                mapa.put("workid", wid);
                mapa.put("custid", cid);
                int proid=prodMapper.chaAccept(map.get("accept").toString(),map.get("depaname").toString());
                mapa.put("acceptid", proid);
                if (map.get("orderno").toString()!=null&&map.get("orderno").toString()!=""){
                    orderInfoMapper.insertOne(map.get("orderno").toString());
                    int orderid=orderInfoMapper.chaorderId(map.get("orderno").toString());
                    mapa.put("orderid", orderid);
                }else{
                    mapa.put("orderid", 0);
                }
                mapa.put("serviceName", map.get("serviceName").toString());
                mapa.put("remark", map.get("remark").toString());
                mapa.put("status", map.get("status").toString());
                mapa.put("broadband", map.get("broadband") == "" ? null : map.get("broadband").toString());
                mapa.put("appointmenttime", map.get("appointmenttime").toString());
                mapa.put("paymentamount",map.get("paymentamount").toString());
                mapa.put("xdtime", sj);
                mapa.put("channl",map.get("channl").toString());
                mapa.put("workserved",map.get("workserved").toString());
            }
            int cresult=custMapper.sgchars(maps);
            int wresult=workMapper.sgchar(mapa);
            if (wresult>0&&cresult>0){
                fishordersMapper.upstatos("营销成功",1,Integer.parseInt(map.get("id").toString()));
                return Result.success(1,"营销成功");
            }else{
                return Result.fail(0,ErrorEnum.FIAL_ERROR);
            }
        }
    }

//    @Override
//    @Scheduled(cron = "0 */5 * * * ?")
//    public List<Map<String, Object>> timeCha() {
//
//        return fishordersMapper.grouby();
//    }

    @Override
    public int insertOne(Fishorders fishorders) {
        return fishordersMapper.insertOne(fishorders);
    }

    @Override
    public int chastatos(String statos, int id) {
        return fishordersMapper.chastatos(statos, id);
    }

    @Override
    public int upstatos(String statos, int id, int ok) {
        return fishordersMapper.upstatos(statos, id, ok);
    }

    @Override
    public int chaOk(int id) {
        return fishordersMapper.chaOk(id);
    }

    @Override
    public String chaStotus(int id) {
        return fishordersMapper.chaStotus(id);
    }

    @Override
    public String chaServicename(int id) {
        return fishordersMapper.chaServicename(id);
    }


}
