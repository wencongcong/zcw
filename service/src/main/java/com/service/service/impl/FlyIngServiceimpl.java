package com.service.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.service.entity.Cust;
import com.service.entity.Fishorders;
import com.service.entity.Work;
import com.service.enums.ArrarEnum;
import com.service.enums.ErrorEnum;
import com.service.mapper.*;
import com.service.result.Result;
import com.service.service.FlyIngService;
import com.service.util.HttpClientUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebListener
@Service("flyIngService")
public class FlyIngServiceimpl implements FlyIngService {


    private static final String URL = "https://feiyu.oceanengine.com/crm/v2/openapi/pull-clues/";

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
    @Transactional
   @Scheduled(cron=" 0 0/5 * * * ? ")
    public Result queryFlyTime() throws Exception {

        Fishorders fishorders=new Fishorders();
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd=null;
        synchronized (this) {
            Thread ts = new Thread();
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
            map.put("signature_key7", "RlozNEo5TDZDTzFJ");
            map.put("token7", "17a0c2a4197e12fab6a782dfb1403b487adc2b1e");
            map.put("signature_key8", "NVlZRFZES1lLTDZO");
            map.put("token8", "1a41aeaadbe17385522f0b7f2f73ceeaad5981f0");
            map.put("signature_key9","VkRJSjk4NDFMUVc5");
            map.put("token9", "203b1ab769ad85747e38aa41931a51799e61d451");
            map.put("signature_key10","UElEQ0daTUJJQU4x");
            map.put("token10", "59adfa827bf35a94dc20c8953804d4298d918336");
            map.put("signature_key11","OEFBTFJVSVBRR0pC");
            map.put("token11", "394a0cf90e7d7773374780dd5f60d0c41c7e4156");
            map.put("signature_key12","MlpMNkJWRVZHUjA0");
            map.put("token12", "c19aaa43d96cd796e23331cdf84994c816f61334");

            try {
                for (int i = 0; i < map.size(); i++) {
                    JSONObject rsult = queryFly(map.get("signature_key" + i).toString(), map
                            .get("token" + i).toString());
                    JSONArray data = rsult.getJSONArray("data");
                    if (data.equals("") || data ==null) {
                        continue;
                    } else {
                        JSONObject ss = null;
                        for (Object object : data) {
                            ss = ((JSONObject) object);
                            if (fishordersMapper.chaFid(ss.getString("clue_id"),ss.getString("telphone")) >= 1) {
                                continue;
                            } else {
                                fishorders.setWorkid(ss.getString("clue_id") == "" ? null : ss.getString("clue_id"));
                                fishorders.setChannels("信息流");
                                fishorders.setChannel("飞鱼");
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
                                Long time = new Long(ss.getString("create_time") == "" ? null : ss.getString("create_time"));
                                String d = sfs.format(new Date(Long.valueOf(time + "000")));
                                fishorders.setOrdertime(d);
                                fishorders.setPayment("");
                                fishorders.setRemark(ss.getString("remark") == "" ? null : ss.getString("remark"));
                                fishorders.setToproomotelinks(ss.getString("external_url") == "" ? null : ss.getString("external_url"));
                                fishorders.setNameofadvertiser(ss.getString("adv_name") == "" ? null : ss.getString("adv_name"));
                                String address = ss.getString("location").toString();
                                if (address.indexOf("+") > 0) {
                                    String areas=address.substring(0,address.indexOf("+"));
                                    sd = address.substring(address.indexOf("+") + 1, address.length());
                                    fishorders.setAddress(sd + "市");
                                    fishorders.setAreas(areas+"省");
                                } else {
                                    fishorders.setAddress(ss.getString("location") == "" ? null : ss.getString("location"));
                                }
                                if (i != 9) {
                                    if (fishordersMapper.whether(ss.getString("telphone").trim()) == 0) {
                                        fishorders.setWhethertorepeat(0);
                                        int rs = fishordersMapper.insertOneFish(fishorders);
                                        if (rs == 0) {
                                            return Result.fail(0, "插入失败");
                                        }else{
                                            continue;
                                        }
                                    } else if (fishordersMapper.whether(ss.getString("telphone").trim())>=1){
                                        fishorders.setWhethertorepeat(1);
                                        int rs = fishordersMapper.insertOneFish(fishorders);
                                        if (rs == 0) {
                                            return Result.fail(0, "插入失败");
                                        }else{
                                            fishorders.setWhethertorepeat(0);
                                        }
                                    }
                                } else if(i==9){
                                    fishorders.setServicename("");
                                    if (fishordersMapper.whether(ss.getString("telphone").trim()) == 0) {
                                        fishorders.setWhethertorepeat(0);
                                        int rs = fishordersMapper.insertOneFish(fishorders);
                                        if (rs == 0) {
                                            return Result.fail(0, "插入失败");
                                        }
                                    } else if (fishordersMapper.whether(ss.getString("telphone").trim())>=1){
                                        fishorders.setWhethertorepeat(1);
                                        int rs = fishordersMapper.insertOneFish(fishorders);
                                        if (rs == 0) {
                                            return Result.fail(0, "插入失败");
                                        }else{
                                            fishorders.setWhethertorepeat(0);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return Result.success(1,"运行成功");
    }

    @Override
    public int chaFid(String wordid,String phone) {
        return fishordersMapper.chaFid(wordid,phone);
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
        int csid=0;
        int wid=0;
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj=sfs.format(date);
        Map maps=new HashMap();
        Map mapa=new HashMap();
        Work work=new Work();
        Cust cust=new Cust();
        int count=custMapper.queryCid();
        cid=count+1;
        if (custMapper.chawork(String.valueOf(cid))>1){
            return Result.fail(0, ErrorEnum.CHONG_FU);
        }else if(custMapper.chachongcount(map.get("custname").toString(),map.get("custphone").toString(),map.get("custidcard").toString())>0){
            csid=custMapper.chachongname(map.get("custname").toString(),map.get("custphone").toString(),map.get("custidcard").toString());
            int wmid=workMapper.queryId();
            wid=wmid+1;
            if (workMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                work.setWorkid(String.valueOf(wid));
                work.setCustid(String.valueOf(csid));
                int proid=prodMapper.chaAccept(map.get("accept").toString(),map.get("depaname").toString());
                work.setAcceptid(String.valueOf(proid));
                if (map.get("orderno").toString()!=null&&map.get("orderno").toString()!=""){
                    orderInfoMapper.insertOne(map.get("orderno").toString());
                    int orderid=orderInfoMapper.chaorderId(map.get("orderno").toString());
                    work.setOrderid(String.valueOf(orderid));
                }else{
                    work.setOrderid(String.valueOf(0));
                }
                work.setServiceName(map.get("serviceName").toString());
                work.setRemark(map.get("remark").toString());
                work.setStatus(map.get("status").toString());
                work.setBroadband( map.get("broadband") == "" ? null : map.get("broadband").toString());
                work.setAppointmenttime(map.get("appointmenttime").toString());
                work.setPaymentamount(map.get("paymentamount").toString());
                work.setXdtime(sj);
                work.setChannl(map.get("channel").toString());
                work.setChannels(map.get("channels").toString());
                work.setWorkserved(map.get("workserved").toString());
                work.setPaymentstate(map.get("paymentstate").toString());
                work.setBusinessnumber(map.get("businessnumber").toString());
                work.setRegion(map.get("region").toString());
                work.setExistingPackageTypes(map.get("existingPackageTypes").toString());
                work.setExistingpreferential(map.get("existingpreferential").toString());
                work.setTerminaltype(map.get("terminaltype").toString());
                work.setChangedPackagetype(map.get("changedPackagetype").toString());
                work.setVicecardnumber(map.get("vicecardnumber").toString());
                work.setCustaddress(map.get("custaddress").toString());
                work.setProdctsName(map.get("prodctsName").toString());
                work.setProdacceptthemethod(map.get("prodacceptthemethod").toString());
                work.setTerminalseries(map.get("terminalseries").toString());
                work.setJsonstr(map.get("custremark").toString());
                int wresult=workMapper.sgchar(work);
                if (wresult>0){
                    fishordersMapper.upstatos("营销成功",1,String.valueOf(work.getId()),Integer.parseInt(map.get("id").toString()));
                    return Result.success(1,"营销成功");
                }else{
                    return Result.fail(0,ErrorEnum.FIAL_ERROR);
                }
            }
        }else {
            cust.setCid(String.valueOf(cid));
            cust.setCustname(map.get("custname").toString());
            cust.setCustidcard(map.get("custidcard") == "" ? null : map.get("custidcard").toString());
            cust.setCustphone(map.get("custphone").toString());
            cust.setCustaddress(map.get("custaddress").toString());
            cust.setCustarea(map.get("custarea").toString());
            cust.setCustareas(map.get("areas").toString());
            cust.setCustcreater(map.get("custcreater").toString());
            cust.setCustcreatertime(sj);
            cust.setCustreserved("");
            int cresult=custMapper.sgchars(cust);
            int wmid=workMapper.queryId();
            wid=wmid+1;
            if (workMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                work.setWorkid(String.valueOf(wid));
                work.setCustid(String.valueOf(cust.getId()));
                int proid=prodMapper.chaAccept(map.get("accept").toString(),map.get("depaname").toString());
                work.setAcceptid(String.valueOf(proid));
                if (map.get("orderno").toString()!=null&&map.get("orderno").toString()!=""){
                    orderInfoMapper.insertOne(map.get("orderno").toString());
                    int orderid=orderInfoMapper.chaorderId(map.get("orderno").toString());
                    work.setOrderid(String.valueOf(orderid));
                }else{
                    work.setOrderid(String.valueOf(0));
                }
                work.setServiceName(map.get("serviceName").toString());
                work.setRemark(map.get("remark").toString());
                work.setStatus(map.get("status").toString());
                work.setBroadband( map.get("broadband") == "" ? null : map.get("broadband").toString());
                work.setAppointmenttime(map.get("appointmenttime").toString());
                work.setPaymentamount(map.get("paymentamount").toString());
                work.setXdtime(sj);
                work.setChannl(map.get("channel").toString());
                work.setChannels(map.get("channels").toString());
                work.setWorkserved(map.get("workserved").toString());
                work.setPaymentstate(map.get("paymentstate").toString());
                work.setBusinessnumber(map.get("businessnumber").toString());
                work.setRegion(map.get("region").toString());
                work.setExistingPackageTypes(map.get("existingPackageTypes").toString());
                work.setExistingpreferential(map.get("existingpreferential").toString());
                work.setTerminaltype(map.get("terminaltype").toString());
                work.setChangedPackagetype(map.get("changedPackagetype").toString());
                work.setVicecardnumber(map.get("vicecardnumber").toString());
                work.setCustaddress(map.get("custaddress").toString());
                work.setProdctsName(map.get("prodctsName").toString());
                work.setProdacceptthemethod(map.get("prodacceptthemethod").toString());
                work.setTerminalseries(map.get("terminalseries").toString());
                work.setJsonstr(map.get("custremark").toString());
            }
            int wresult=workMapper.sgchar(work);
            if (wresult>0&&cresult>0){
                fishordersMapper.upstatos("营销成功",1,String.valueOf(work.getId()),Integer.parseInt(map.get("id").toString()));
                return Result.success(1,"营销成功");
            }else{
                return Result.fail(0,ErrorEnum.FIAL_ERROR);
            }
        }
    }

    @Override
    public int insertOne(Fishorders fishorders) {
        return fishordersMapper.insertOne(fishorders);
    }

    @Override
    public int chastatos(String statos, int id) {
        return fishordersMapper.chastatos(statos, id);
    }

    @Override
    public int upstatos(String statos, int id,String wid, int ok) {
        return fishordersMapper.upstatos(statos, id,wid, ok);
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

    @Override
    public Result timetoremind(String servicename) {
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, +6);// 6分钟之前的时间
        Date beforeD = beforeTime.getTime();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -5); //得到前五天
        Date date1 = calendar.getTime();

        String before5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beforeD);
        String before4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1);

        Map map=new HashMap();
        map.put("servicename",servicename);
        map.put("otime",before4);
        map.put("ptime",before5);

       List<Map<String,String>> maps=fishordersMapper.timetoremind(map);

        return Result.success(1,maps);
    }

    @Override
    public int whether(String phone) {
        return fishordersMapper.whether(phone);
    }

    @Override
    public List<Fishorders> queryAlls(Map map) {
        return fishordersMapper.queryAlls(map);
    }


}
