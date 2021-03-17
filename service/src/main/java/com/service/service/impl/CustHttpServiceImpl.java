package com.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.service.entity.*;
import com.service.enums.ArrarEnum;
import com.service.enums.ErrorEnum;
import com.service.mapper.*;
import com.service.result.Result;
import com.service.service.*;
import com.service.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("custHttpService")
public class CustHttpServiceImpl implements CustHttpService {

    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private WorkMapper workMapper;
    @Resource
    private OrderdetailMapper orderdetailMapper;
    @Resource
    private CustomerMapper customerMapper;

    OrderInfo order = new OrderInfo();//订单基本信息
    MarketerInfo mark = new MarketerInfo();//营销人信息表
    CustomerInfo custo = new CustomerInfo();//客户信息表
    Orderdetails orderdeta = new Orderdetails();//订单详情表
    OrderHstory orderHstory = new OrderHstory();

    //数据反刷接口
    public static String url = "http://127.0.0.1:2018/order/bss7";
    Map<String, Object> map = new HashMap<>();
    SimpleDateFormat qsfs = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    @Scheduled(cron = "0 0 10,12,14,16,18,20 * * ? ")
   // @Scheduled(cron=" 0 0/5 * * * ? ")
    public Result Timeupda() throws Exception {

        OrderHstory orderHstory = new OrderHstory();
        JSONObject saleoreder = null;
        JSONObject orderDetail = null;
        JSONObject prostatus = null;
        JSONObject baseorder = null;
        JSONObject datas = null;
        Date date = new Date();
        SimpleDateFormat sfd = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj = sfs.format(date);
        String ssd = sfd.format(date);
        Map<String, Object> maps = new HashMap<>();
        Map<String, Object> mapm = new HashMap<>();
        List<Map<String, String>> lists = null;
        String worid = null;
        String ss = null;
        synchronized (this) {
            try {
                for (ArrarEnum arr:ArrarEnum.values()){
                    String tel =arr.getName();
                    lists = orderInfoMapper.chaOrderNo(qsfs.format(date), null);
                if (lists.size() == 0) {
                    return Result.fail(1, "反刷无数据");
                }
                for (Map st : lists) {
                    String orderNo = st.get("orderNo").toString().trim();
                    int id = Integer.parseInt(st.get("id").toString());
                    map.put("orderId", orderNo);
                    map.put("tel", tel);
                    ss = HttpClientUtil.jsonPost(url, map);
                    JSONObject data = JSON.parseObject(ss);
                    if (ss == null) {
                        return Result.fail(ErrorEnum.NO_MESSAGE);
                    } else if (Integer.parseInt(data.getString("code")) != 0) {
                        return Result.fail(ErrorEnum.OATHER_MESSAGE);
                    } else {
                        datas = data.getJSONObject("data");
                        saleoreder = datas.getJSONObject("saleoreder");//解析获得saleoreder
                        orderDetail = datas.getJSONObject("orderDetail");//解析获得orderDetail
                        prostatus = datas.getJSONObject("processStatus");//解析获得prostatus
                        baseorder = datas.getJSONObject("baseOrderMessage");//解析获得baseorder
                        String orderno = saleoreder.getString("custOrderNbr");//获得工单号
                        //解析baseorder获得客户发展人信息
                        JSONObject cust = baseorder.getJSONObject("custDevStaffInfoVo");
                        order.setCreateName(cust.getString("createStaff"));
                        order.setCreateNo(cust.getString("createStaffCode"));
                        order.setCreatePhone(cust.getString("createStaffPhone"));
                        order.setThefirstName(cust.getString("devStaffFir"));
                        order.setThefirstNo(cust.getString("devStaffFirCode"));
                        order.setThefirstPhone(cust.getString("devStaffFirPhone"));
                        //解析baseorder获得客户信息
                        JSONObject ordC = baseorder.getJSONObject("ordCustomerMesVo");
                        custo.setContractnumber(ordC.getString("acctCd"));
                        custo.setCustmeraddress(ordC.getString("custAddr"));
                        custo.setCusttype(ordC.getString("custType"));
                        custo.setCustmerNo(ordC.getString("custNumber"));
                        custo.setAccountname(ordC.getString("acctName"));
                        custo.setDocuNumber(ordC.getString("custIdNumber"));
                        custo.setCustmername(ordC.getString("custName"));
                        custo.setCustlevel(null);
                        //获得7工单基本信息
                        JSONObject mess = baseorder.getJSONObject("custOrdMessVo");
                        order.setOrderNo(mess.getString("custOrderNbr"));
                        int result = 0;
                        if (orderInfoMapper.chaOne(mess.getString("custOrderNbr")) == 1) {
                            JSONArray otherInfoAttrVos = mess.getJSONArray("otherInfoAttrVos");
                            for (Object oss : otherInfoAttrVos) {
                                JSONObject jn = (JSONObject) oss;
                                if (jn.getString("name").equals("受理渠道类型")) {
                                    order.setAcceptancetype(jn.getString("attrValue"));
                                }
                                if (jn.getString("name").equals("订单子类型")) {
                                    order.setSubtype(jn.getString("attrValue"));
                                }
                                if (jn.getString("name").equals("批量单标记")) {
                                    order.setBatchmark(jn.getString("attrValue"));
                                }
                                if (jn.getString("name").equals("批量单标记")) {
                                    if (jn.getString("attrValue") != null) {
                                        order.setQiremarks(jn.getString("attrValue"));
                                    } else {
                                        order.setQiremarks("暂无");
                                    }
                                }
                            }
                            order.setOstats(mess.getString("statusCd"));
                            order.setRemarks(mess.getString("remark"));
                            order.setAcceptancetime(mess.getString("acceptDate"));
                            order.setOrdertype(mess.getString("custOrderType"));
                            order.setOrdersource(mess.getString("orderSource"));
                            order.setAcceptchannal(mess.getString("channelName"));
                            order.setAutomatilmarking(mess.getString("autoFlag"));
                            order.setChargemethod(mess.getString("ordPayMethod"));
                            JSONArray rela = orderDetail.getJSONArray("relationOrderDetailVoList");
                            JSONObject wang = null;
                            for (Object rs : rela) {
                                wang = (JSONObject) rs;
                                if (wang.getString("sysName").equals("固网施工单")) {
                                    order.setGuwangno(wang.getString("custOrderNbr"));
                                    order.setGuwangstatos(wang.getString("statusCdName"));
                                }else{
                                    order.setGuwangno("");
                                    order.setGuwangstatos("");
                                }
                                if (wang.getString("sysName").equals("C网施工单")) {
                                    order.setCwangno(wang.getString("custOrderNbr"));
                                    order.setCwangstatos(wang.getString("statusCdName"));
                                }else {
                                    order.setCwangno("");
                                    order.setCwangstatos("");
                                }
                                if (wang.getString("sysName").equals("原单")) {
                                    order.setYuandanno(wang.getString("custOrderNbr"));
                                    order.setYuandanstatos(wang.getString("statusCdName"));
                                }
                            }
                            //订单详情信息
                            JSONArray orderProd = null;
                            orderProd = prostatus.getJSONArray("orderProdItemVos");
                            if (orderProd.size() >= 1) {
                                for (int i = 0; i < orderProd.size(); i++) {
                                    JSONObject jst = (JSONObject) orderProd.get(i);
                                    if (jst.getString("statusName").equals("已退单")||jst.getString("statusName").equals("已撤单")) {
                                        order.setAbnormal(1);
                                        break;
                                    } else {
                                        order.setAbnormal(0);
                                        continue;
                                    }
                                }
                            }else{
                                order.setAbnormal(0);
                            }


                            JSONObject slt = null;
                            for (int i = 0; i < orderProd.size(); i++) {
                                slt = orderProd.getJSONObject(i);
                                orderdeta.setOrderinfoid(slt.get("custOrderId").toString());
                                orderdeta.setAssetnumber(slt.getString("accNum"));
                                orderdeta.setBehavior(slt.getString("serviceOfferName"));
                                orderdeta.setCurrentstate(slt.getString("statusName"));
                                orderdeta.setCurrentpeincharge(slt.getString("staffInfo"));
                                orderdeta.setOperation(slt.getString("cancelFlag") == "false" ? "" : slt.getString("cancelFlag"));
                                orderdeta.setOrderjson(orderDetail.getJSONArray("relationOrderDetailVoList").toJSONString());
                                orderdeta.setProdName(slt.getString("prodName"));
                                JSONObject status97 = slt.getJSONObject("97_status_data");
                                if (Integer.parseInt(status97.getString("code")) == 0) {
                                    JSONObject data1 = status97.getJSONObject("data");
                                    JSONArray data2 = data1.getJSONArray("statusList");
                                    //三元三目表达式  如果取到的是空字符串给它赋值null如果不是则赋值原值
                                    if (data2 != null) {
                                        JSONObject data3 = null;
                                        for (Object da : data2) {
                                            data3 = ((JSONObject) da);
                                            orderdeta.setProcessinglink(data3.getString("workPosition") == "" ? null : data3.getString("workPosition"));
                                            orderdeta.setWorkorderstatus(data3.getString("status2"));
                                            orderdeta.setProcessingtime(data3.getString("startDate"));
                                        }
                                    }
                                }
                                JSONObject prodo=null;
                                if (slt.getJSONObject("prodOrderItem").toString()==null||slt.getJSONObject("prodOrderItem").toString()==""){
                                    orderdeta.setPageNew("");
                                }else{
                                    prodo = slt.getJSONObject("prodOrderItem");
                                    String page = prodo.getString("orderSubPackVos");
                                    orderdeta.setPageNew(page);
                                }
                                if (orderdetailMapper.chaOne(prostatus.getString("custOrderId")) < orderProd.size()) {
                                    orderdetailMapper.insertOne(orderdeta);
                                }else{
                                    orderdetailMapper.updateAll(orderdeta);
                                }
                                order.setOrderdetailsId(slt.get("custOrderId").toString());
                            }
                            if (customerMapper.chaChong(ordC.getString("custNumber")) == 0) {
                                customerMapper.insertOne(custo);
                            }

                            order.setCustId(custo.getId());
                            order.setRowstate(orderProd.toJSONString());
                            order.setId(id);

                            result = orderInfoMapper.updateOneOrder(order);
                            if (result > 0) {
                                int oid = id;
                                worid = workMapper.chaWorkid(oid);
                                if (worid==null){
                                    continue;
                                }else {
                                    String statos = mess.getString("statusCd");
                                    switch (statos) {
                                        case "已收费":
                                        case "已提交":
                                        case "开通中":
                                        case "受理中":
                                            workMapper.Ustatosoid("已提交", "已提交", String.valueOf(worid));
                                            break;
                                        case "质检回退":
                                        case "已撤单":
                                        case "已取消":
                                        case "受理组回退":
                                        case "提交集团失败":
                                            workMapper.Ustatosoid("处理失败", "处理失败", String.valueOf(worid));
                                            break;
                                        case "已归档":
                                        case "已完成":
                                            workMapper.updatesettle("待结算_未发起结算", "未发放", sj, String.valueOf(worid));
                                            workMapper.Ustatosoid("成功订单", "成功订单", String.valueOf(worid));
                                            break;
                                        case "质检中":
                                        case "新建":
                                        case "待收费":
                                        case "提交集团中":
                                            workMapper.Ustatosoid("待处理", "待处理", String.valueOf(worid));
                                            break;
                                        default:
                                            workMapper.Ustatosoid("其他", "其他", String.valueOf(worid));
                                            break;
                                    }
                                }
                            }
                            JSONObject process = null;
                            process = datas.getJSONObject("processStatus");
                            JSONArray orderProds = null;
                            if (process != null) {
                                orderProds = process.getJSONArray("orderProdItemVos");
                                JSONObject slts = null;
                                for (Object ord : orderProds) {
                                    slts = ((JSONObject) ord);
                                    JSONObject stats = slts.getJSONObject("97_status_data");
                                    int reslut = stats.getInteger("code");
                                    if (reslut == 0) {
                                        JSONObject data1 = stats.getJSONObject("data");
                                        orderdetailMapper.updatepeo(data1.getString("contacts"), orderDetail.getString("relationOrderDetailVoList"), slts.getString("custOrderId"));
                                    }
                                    continue;
                                }
                            }
                        }
                    }
                    continue;
                }
                }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
        return Result.success();
    }

    @Override
    @Scheduled(cron = "0 0 7,13,15,17 * * ? ")
    //@Scheduled(cron=" 0 0/50 * * * ? ")
    public Result Timehistory() throws Exception {
        JSONObject saleoreder = null;
        JSONObject orderDetail = null;
        JSONObject prostatus = null;
        JSONObject baseorder = null;
        JSONObject datas = null;
        Date date = new Date();
        SimpleDateFormat sfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj = sfs.format(date);
        List<Map<String, String>> lists = null;
        String worid = null;
        String ss = null;
        synchronized (this) {
            try {
                        lists = orderInfoMapper.queryAllOrderNo();
                    for (Map st:lists) {
                        String orderNo = st.get("orderNo").toString().trim();
                        int id = Integer.parseInt(st.get("id").toString());
                        map.put("orderId", orderNo);
                        map.put("tel", "18072892408");
                        ss = HttpClientUtil.jsonPost(url, map);
                        JSONObject data = JSON.parseObject(ss);
                        if (ss == null) {
                            return Result.fail(ErrorEnum.NO_MESSAGE);
                        }else if(Integer.parseInt(data.getString("code")) != 0) {
                        }else {
                            datas = data.getJSONObject("data");
                            orderDetail = datas.getJSONObject("orderDetail");//解析获得orderDetail
                            prostatus = datas.getJSONObject("processStatus");//解析获得prostatus
                            baseorder = datas.getJSONObject("baseOrderMessage");//解析获得baseorder
                            //获得7工单基本信息
                            JSONObject mess = baseorder.getJSONObject("custOrdMessVo");
                            order.setOrderNo(mess.getString("custOrderNbr"));
                            int result = 0;
                            if (orderInfoMapper.chaOne(mess.getString("custOrderNbr")) == 1) {
                                order.setOstats(mess.getString("statusCd"));
                                order.setRemarks(mess.getString("remark"));
                                order.setAcceptancetime(mess.getString("acceptDate"));
                                order.setOrdertype(mess.getString("custOrderType"));
                                order.setOrdersource(mess.getString("orderSource"));
                                order.setAcceptchannal(mess.getString("channelName"));
                                order.setAutomatilmarking(mess.getString("autoFlag"));
                                order.setChargemethod(mess.getString("ordPayMethod"));
                                JSONArray rela = orderDetail.getJSONArray("relationOrderDetailVoList");
                                JSONObject wang = null;
                                for (Object rs : rela) {
                                    wang = (JSONObject) rs;
                                    if (wang.getString("sysName").equals("固网施工单")) {
                                        order.setGuwangno(wang.getString("custOrderNbr"));
                                        order.setGuwangstatos(wang.getString("statusCdName"));
                                    }else{
                                        order.setGuwangno("");
                                        order.setGuwangstatos("");
                                    }
                                    if (wang.getString("sysName").equals("C网施工单")) {
                                        order.setCwangno(wang.getString("custOrderNbr"));
                                        order.setCwangstatos(wang.getString("statusCdName"));
                                    }else {
                                        order.setCwangno("");
                                        order.setCwangstatos("");
                                    }
                                    if (wang.getString("sysName").equals("原单")) {
                                        order.setYuandanno(wang.getString("custOrderNbr"));
                                        order.setYuandanstatos(wang.getString("statusCdName"));
                                    }
                                }
                                //订单详情信息
                                JSONArray orderProd = null;
                                orderProd = prostatus.getJSONArray("orderProdItemVos");
                                if (orderProd.size() >= 1) {
                                    for (int j = 0; j < orderProd.size(); j++) {
                                        JSONObject jst = (JSONObject) orderProd.get(j);
                                        if (jst.getString("statusName").equals("已退单")||jst.getString("statusName").equals("已撤单")) {
                                            order.setAbnormal(1);
                                            break;
                                        } else {
                                            order.setAbnormal(0);
                                            continue;
                                        }
                                    }
                                }else{
                                    order.setAbnormal(0);
                                }
                                JSONObject slt = null;
                                for (int k = 0; k < orderProd.size(); k++) {
                                    slt = orderProd.getJSONObject(k);
                                    orderdeta.setOrderinfoid(slt.get("custOrderId").toString());
                                    orderdeta.setAssetnumber(slt.getString("accNum"));
                                    orderdeta.setBehavior(slt.getString("serviceOfferName"));
                                    orderdeta.setCurrentstate(slt.getString("statusName"));
                                    orderdeta.setCurrentpeincharge(slt.getString("staffInfo"));
                                    orderdeta.setOperation(slt.getString("cancelFlag") == "false" ? "" : slt.getString("cancelFlag"));
                                    orderdeta.setOrderjson(orderDetail.getJSONArray("relationOrderDetailVoList").toJSONString());
                                    orderdeta.setProdName(slt.getString("prodName"));
                                    JSONObject status97 = slt.getJSONObject("97_status_data");
                                    if (Integer.parseInt(status97.getString("code")) == 0) {
                                        JSONObject data1 = status97.getJSONObject("data");
                                        JSONArray data2 = data1.getJSONArray("statusList");
                                        //三元三目表达式  如果取到的是空字符串给它赋值null如果不是则赋值原值
                                        if (data2 != null) {
                                            JSONObject data3 = null;
                                            for (Object da : data2) {
                                                data3 = ((JSONObject) da);
                                                orderdeta.setProcessinglink(data3.getString("workPosition") == "" ? null : data3.getString("workPosition"));
                                                orderdeta.setWorkorderstatus(data3.getString("status2"));
                                                orderdeta.setProcessingtime(data3.getString("startDate"));
                                            }
                                        }
                                    }

                                    if (orderdetailMapper.chaOne(prostatus.getString("custOrderId")) < orderProd.size()) {
                                        orderdetailMapper.insertOne(orderdeta);
                                    }else{
                                        orderdetailMapper.updateAll(orderdeta);
                                    }
                                    order.setOrderdetailsId(slt.get("custOrderId").toString());
                                }

                                order.setRowstate(orderProd.toJSONString());
                                order.setId(id);
                                result = orderInfoMapper.updateOneOrder(order);
                                if (result > 0) {
                                    worid = workMapper.chaWorkid(id);
                                    if (worid==null){
                                        continue;
                                    }else {
                                        String statos = mess.getString("statusCd");
                                        switch (statos) {
                                            case "已收费":
                                            case "已提交":
                                            case "开通中":
                                            case "受理中":
                                                workMapper.Ustatosoid("已提交", "已提交", String.valueOf(worid));
                                                break;
                                            case "质检回退":
                                            case "已撤单":
                                            case "已取消":
                                            case "受理组回退":
                                            case "提交集团失败":
                                                workMapper.Ustatosoid("处理失败", "处理失败", String.valueOf(worid));
                                                break;
                                            case "已归档":
                                            case "已完成":
                                                workMapper.updatesettle("待结算_未发起结算", "未发放", sj, String.valueOf(worid));
                                                workMapper.Ustatosoid("成功订单", "成功订单", String.valueOf(worid));
                                                break;
                                            case "质检中":
                                            case "新建":
                                            case "待收费":
                                            case "提交集团中":
                                                workMapper.Ustatosoid("待处理", "待处理", String.valueOf(worid));
                                                break;
                                            default:
                                                workMapper.Ustatosoid("其他", "其他", String.valueOf(worid));
                                                break;
                                        }
                                    }
                                }
                                JSONObject process = null;
                                process = datas.getJSONObject("processStatus");
                                JSONArray orderProds = null;
                                if (process != null) {
                                    orderProds = process.getJSONArray("orderProdItemVos");
                                    JSONObject slts = null;
                                    for (Object ord : orderProds) {
                                        slts = ((JSONObject) ord);
                                        JSONObject stats = slts.getJSONObject("97_status_data");
                                        int reslut = stats.getInteger("code");
                                        if (reslut == 0) {
                                            JSONObject data1 = stats.getJSONObject("data");
                                            orderdetailMapper.updatepeo(data1.getString("contacts"), orderDetail.getString("relationOrderDetailVoList"), slts.getString("custOrderId"));
                                        }
                                        continue;
                                    }
                                }
                            }else{
                                continue;
                            }
                        }
                    }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return Result.success();
    }

}