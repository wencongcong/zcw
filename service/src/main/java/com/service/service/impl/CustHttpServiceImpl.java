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
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("custHttpService")
public class CustHttpServiceImpl implements CustHttpService {

    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private OrderHstoryMapper orderHstoryMapper;
    @Resource
    private WorkMapper workMapper;
    @Resource
    private OrderdetailMapper orderdetailMapper;
    @Resource
    private CustMapper custMapper;
    @Resource
    private MarketerMapper marketerMapper;
    @Resource
    private CustomerMapper customerMapper;

    OrderInfo order = new OrderInfo();//订单基本信息
    MarketerInfo mark = new MarketerInfo();//营销人信息表
    CustomerInfo custo = new CustomerInfo();//客户信息表
    Orderdetails orderdeta = new Orderdetails();//订单详情表
    OrderHstory orderHstory = new OrderHstory();

    //数据反刷接口
    public static String url = "http://192.168.0.253:2018/order/bss7";
    Map<String, Object> map = new HashMap<>();
    SimpleDateFormat qsfs = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    //@Scheduled(cron = "0 0 9,11,13,15,17,19,21,23,1,3,5,7 * * ? ")
    @Scheduled(cron = "0 */5 * * * ?")
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
        Date date1 = sfd.parse(ssd);
        Date date2 = sfd.parse("9:00");
        Date dates = sfd.parse("18:00");
        Date date3 = sfd.parse("22:00");
        Map<String, Object> maps = new HashMap<>();
        Map<String, Object> mapm = new HashMap<>();
        List<Map<String, String>> lists = null;
        String worid = null;
        String ss = null;
        synchronized (this) {
            try {
                for (ArrarEnum arr:ArrarEnum.values()){
                    String tel =arr.getName();
                if (date1.getTime() > date2.getTime() && date1.getTime() < dates.getTime()) {
                    lists = orderInfoMapper.chaOrderNo(qsfs.format(date), null);
                } else {
                    lists = orderInfoMapper.chaOrderNo("2021-01-27", null);
                }
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
                    }else if(Integer.parseInt(data.getString("code"))==511){
                        continue;
                    } else {
                        datas = data.getJSONObject("data");
                        saleoreder = datas.getJSONObject("saleoreder");//解析获得saleoreder
                        orderDetail = datas.getJSONObject("orderDetail");//解析获得orderDetail
                        prostatus = datas.getJSONObject("processStatus");//解析获得prostatus
                        baseorder = datas.getJSONObject("baseOrderMessage");//解析获得baseorder
                        String orderno = saleoreder.getString("custOrderNbr");//获得工单号
                        String sale = saleoreder.toString();
                        String orderd = orderDetail.toString();
                        String prost = prostatus.toString();
                        String base = baseorder.toString();

                        orderHstoryMapper.insertOneOrder(orderHstory);//进行一个历史记录遗存

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
                            JSONObject cpd = orderProd.getJSONObject(0);
                            if (orderProd.size() > 1) {
                                for (int i = 0; i < orderProd.size(); i++) {
                                    JSONArray jsonArray = prostatus.getJSONArray("orderProdItemVos");
                                    for (int j = i; j < orderProd.size() - i; j++) {
                                        JSONObject jst = (JSONObject) jsonArray.get(i);
                                        JSONObject jste = (JSONObject) jsonArray.get(j);
                                        if (jst.getString("statusName").equals(jste.getString("statusName"))) {
                                            order.setAbnormal(0);
                                        } else {
                                            order.setAbnormal(1);
                                        }
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
                                int oid = order.getId();
                                worid = workMapper.chaWorkid(oid);
                                if (worid==null){
                                    continue;
                                }
                                orderHstory.setOrderID(orderno);
                                orderHstory.setSaleorder(sale);
                                orderHstory.setOrderDetail(orderd);
                                orderHstory.setProcessStatus(prost);
                                orderHstory.setBaseOrderMessage(base);
                                orderHstory.setUplogintime(sj);
                                orderHstory.setBiaoji(0);
                                String statos = mess.getString("statusCd");
                                switch (statos) {
                                    case "已收费":
                                    case "已提交":
                                    case "开通中":
                                    case "受理中":
                                        workMapper.Ustatosoid("已提交","已提交", oid, String.valueOf(worid));
                                        break;
                                    case "质检回退":
                                    case "已撤单":
                                    case "已取消":
                                    case "受理组回退":
                                    case "提交集团失败":
                                        workMapper.Ustatosoid("处理失败", "处理失败",oid, String.valueOf(worid));
                                        break;
                                    case "已归档":
                                    case "已完成":
                                        workMapper.updatesettle("待结算_未发起结算", "未发放", sj, String.valueOf(worid));
                                        workMapper.Ustatosoid("成功订单","成功订单", oid, String.valueOf(worid));
                                        break;
                                    case "质检中":
                                    case "新建":
                                    case "待收费":
                                    case "提交集团中":
                                        workMapper.Ustatosoid("待处理","待处理", oid, String.valueOf(worid));
                                        break;
                                    default:
                                        workMapper.Ustatosoid("其他","其他", oid, String.valueOf(worid));
                                        break;
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
}