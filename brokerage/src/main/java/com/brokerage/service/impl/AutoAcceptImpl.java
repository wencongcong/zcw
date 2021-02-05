package com.brokerage.service.impl;
import com.brokerage.entity.Bssverify;
import com.brokerage.entity.Cust;
import com.brokerage.entity.Work;
import com.brokerage.enums.AreaEnum;
import com.brokerage.enums.ArrarEnum;
import com.brokerage.enums.ErrorEnum;
import com.brokerage.mapper.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.brokerage.result.Result;
import com.brokerage.service.AutoAcceptService;
import com.brokerage.service.TamllService;
import com.brokerage.util.ISaleHttpUtil;
import com.brokerage.util.ISaleHttpUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("autoAcceptService")
public  class AutoAcceptImpl implements AutoAcceptService {

    @Resource
    private WorkInfoMapper workInfoMapper;
    @Resource
    private CustInfoMapper custInfoMapper;
    @Resource
    private OrderInfosMapper orderInfosMapper;
    @Resource
    private ProdInMapper prodInMapper;
    @Resource
    private FishordersInfoMapper fishordersInfoMapper;
    @Resource
    private BssverifyMapper bssverifyMapper;
    @Resource
    private ZcdistributorMapper zcdistributorMapper;
    @Resource
    private TenthousandMapper tenthousandMapper;
    @Resource
    private TmallMapper tmallMapper;
    @Resource
    private TerminalMapper terminalMapper;



    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    public static String URL = "http://115.233.6.84:8001/";
    JSONObject jsonObject=null;
    @Override
    public Result autoaccept(ISaleHttpUtil iSaleHttpUtil,Map mapa) {
        String org=mapa.get("area").toString();
        int orgid= AreaEnum.getValueByName(org);
        String channelNbr=AreaEnum.getValueByCode(org);
        Map<String,Object> map=new HashMap();
        Map mape=new HashMap();
        List<Map> list=new LinkedList<>();
        Map maps=new HashMap();
        maps.put("deskId","6027");
        maps.put("orgId",orgid);
        list.add(maps);
        Map maps1=new HashMap();
        maps1.put("deskId","6006");
        maps1.put("orgId","800000000037");
        list.add(maps1);
        Map maps2=new HashMap();
        maps2.put("deskId","6049");
        maps2.put("orgId",orgid);
        list.add(maps2);
        Map maps3=new HashMap();
        maps3.put("deskId","1114409");
        maps3.put("orgId",orgid);
        list.add(maps3);
        map.put("choosenOrgs",list);
        String custid=null;
        /**
         * 切换地势
         * 需要改变请求头的信息
         * */
        synchronized (this){
            String stt= iSaleHttpUtil.postWithCookieFormData(URL+"chome-fc45d4cdf-htxbj/api/deskDetail",map);
            jsonObject= JSON.parseObject(stt);
            if (jsonObject.getString("code").equals("0000")&&jsonObject.getString("status").equals("200")) {
                String sts = iSaleHttpUtil.getWithCookie(URL + "index/changePosition-bss?channelNbr="+channelNbr+"&login=18072892408&orgid=" + orgid);
                jsonObject = JSON.parseObject(sts);
                if (jsonObject.getString("success").equals("true")) {
                    mape.put("accNum", "");
                    mape.put("certNum", mapa.get("idcred").toString());
                    mape.put("custName", mapa.get("custname").toString());
                    mape.put("password", "");
                    mape.put("isRead", "N");
                    //客户登录
                    String queryClient = iSaleHttpUtil.postWithCookieFormData(URL + "cordercore-74f4564b7-m47d4/api-custorder/customers", mape);
                    if (queryClient == null || queryClient.trim().isEmpty()) {
                        return Result.fail("查询资产信息失败");
                    } else {
                        jsonObject = JSON.parseObject(queryClient);
                        if (!jsonObject.getString("code").equals("0000"))
                            return Result.fail(0, jsonObject.getString("message"));
                        JSONArray data = jsonObject.getJSONArray("data");
                        String datas = data.getString(0);
                        jsonObject = JSON.parseObject(datas);
                        JSONArray customer = jsonObject.getJSONArray("customer");
                        jsonObject = JSON.parseObject(customer.getString(0));
                        custid = jsonObject.getString("custId");
                    }
                    mape.clear();
                    mape.put("accNum", "");
                    mape.put("certNum", mapa.get("idcred").toString());
                    mape.put("custName", mapa.get("custname").toString());
                    mape.put("procInstId", "");
                    mape.put("requestId", "");
                    mape.put("taskId", "");
                    mape.put("custId", custid);
                    //客户定位
                    String ClientPosition = iSaleHttpUtil.postWithCookieFormData(URL + "cordercore-74f4564b7-m47d4/api-custorder/customerPosition", mape);
                    if (ClientPosition == null || ClientPosition.trim().isEmpty()) {
                        return Result.fail(01, "信息失败");
                    }
                    //通过名字获取模板信息 batchTemplateId
                    String cordercore = iSaleHttpUtil.getWithCookie(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/order-template/offer-template?templateName=" + mapa.get("proname").toString() + "&pageIndex=1");
                    jsonObject = JSON.parseObject(cordercore);
                    JSONObject datase = jsonObject.getJSONObject("data");
                    JSONArray datalist = datase.getJSONArray("dataList");
                    JSONObject batchTemplate = JSON.parseObject(datalist.getString(0));
                    String batchTemplateId = batchTemplate.getString("batchTemplateId");
                    mape.clear();
                    //新建用户模板
                    String ordertemplate = iSaleHttpUtil.postWithCookieFormData(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/order-template/offer-template/" + batchTemplateId, mape);
                    jsonObject = JSON.parseObject(ordertemplate);
                    if (!jsonObject.getString("code").equals("0000")) {
                        return Result.fail(02, jsonObject.getString("message"));
                    }
                    //局向搜索
                    String addressselection = iSaleHttpUtil.getWithCookie(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/address-selection/query-addr-list?bureauNbr=" + mapa.get("bureauNbr").toString() + "&c3Name=" + mapa.get("area").toString() + "&c4Name=" + mapa.get("c4name").toString() + "&page=1&address=" + mapa.get("bureauaddre").toString());
                    jsonObject = JSON.parseObject(addressselection);
                    if (!jsonObject.getString("code").equals("0000"))
                        return Result.fail(03, jsonObject.getString("message"));
                    JSONArray dase = jsonObject.getJSONArray("data");
                    JSONObject cover = JSON.parseObject(dase.getString(0));
                    String coverId = cover.getString("coverId");
                    //获取局向详情信息
                    String addresordershopcar = iSaleHttpUtil.getWithCookie(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/address-selection/get-addr-detail?coverId=" + coverId);
                    jsonObject = JSON.parseObject(addresordershopcar);
                    if (!jsonObject.getString("code").equals("0000"))
                        return Result.fail(04, jsonObject.getString("message"));
                    JSONObject dates = jsonObject.getJSONObject("data");
                    JSONObject addrResCoverInfoVo = dates.getJSONObject("addrResCoverInfoVo");
                    String lineType = addrResCoverInfoVo.getString("lineType");
                    String wgbm = addrResCoverInfoVo.getString("wgbm");
                    //获取产品信息
                    String leftMenu = iSaleHttpUtil.getWithCookie(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/cart/leftMenu");
                    jsonObject = JSON.parseObject(leftMenu);
                    JSONArray ddse = jsonObject.getJSONArray("data");
                    JSONObject ordVoList = JSON.parseObject(ddse.getString(0));
                    String promOrderItemUUID = ordVoList.getString("promOrderItemUUID");
                    String c3RegionId = ordVoList.getString("c3RegionId");
                    String c4RegionId = ordVoList.getString("c4RegionId");
                    JSONArray orderItemVoList = ordVoList.getJSONArray("ordMainProdOrderItemVoList");
                    JSONObject ordMainProdOrderItemVoList = JSON.parseObject(orderItemVoList.getString(0));
                    String UUid = ordMainProdOrderItemVoList.getString("ordMainPordOrderItemUUID");
                    //选择C4地址
                    String regionc4 = iSaleHttpUtil.getWithCookie(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/address-selection/common-region-c4-list?regionId=8330100&c4RegionId=" + c4RegionId);
                    jsonObject = JSON.parseObject(regionc4);
                    if (!jsonObject.getString("code").equals("0000"))
                        return Result.fail(05, jsonObject.getString("message"));
                    //C3确认
                    String versionFlag = iSaleHttpUtil.getWithCookie(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/cart/versionFlag");
                    jsonObject = JSON.parseObject(versionFlag);
                    if (!jsonObject.getString("code").equals("0000"))
                        return Result.fail(06, jsonObject.getString("message"));
                    mape.clear();
                    mape.put("addr", "");
                    mape.put("addressId", coverId);
                    mape.put("c3Name", mapa.get("area").toString());
                    mape.put("c4Name", mapa.get("c4name").toString());
                    mape.put("idCardFlag", "0");
                    mape.put("preNumber", "");
                    mape.put("lineType", lineType);
                    mape.put("roomId", "");
                    mape.put("ordMainPordOrderItemUUID", UUid);
                    //复制局向
                    String copyresources = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/address-selection/copy-resources", mape);
                    jsonObject = JSON.parseObject(copyresources);
                    if (!jsonObject.getString("code").equals("0000"))
                        return Result.fail(07, jsonObject.getString("message"));
                    //对应产品的详情信息
                    String prodOrderItemDetail = iSaleHttpUtil.getWithCookie(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/cart/prodOrderItemDetail?ordMainPordOrderItemUUID=" + UUid + "&isAdd=N");
                    jsonObject = JSON.parseObject(prodOrderItemDetail);
                    JSONObject datapro = jsonObject.getJSONObject("data");
                    JSONArray subProdwith = datapro.getJSONArray("subProdwithAttrsOrderItemVoList");
                    String subProdOrderItemUUID = null;//修改家庭云电话所需要的数据
                    for (Object obj : subProdwith) {
                        JSONObject sub = (JSONObject) obj;
                        String prodName = sub.getString("prodName");
                        if (prodName.equals("家庭云")) {
                            subProdOrderItemUUID = sub.getString("subProdOrderItemUUID");
                        }
                    }
                    String ordsubProdInstAttrUUID = null;//修改家庭云电话所需要的数据
                    JSONArray defaultSubProdwith = datapro.getJSONArray("defaultSubProdwithAttrsOrderItemVoList");
                    for (Object obj : defaultSubProdwith) {
                        JSONObject defa = (JSONObject) obj;
                        String prodName = defa.getString("prodName");
                        if (prodName.equals("家庭云")) {
                            JSONArray otherOrd = defa.getJSONArray("otherOrdSubProdInstAttrsVoList");
                            for (Object obje : otherOrd) {
                                JSONObject other = (JSONObject) obje;
                                String attrName = other.getString("attrName");
                                if (attrName.equals("手机号码")) {
                                    ordsubProdInstAttrUUID = other.getString("ordMainProdInstAttrUUID");
                                }
                            }
                        }
                    }
                    // 新增合同号
                    mape.clear();
                    mape.put("orderItemUUID", promOrderItemUUID);
                    String createNewAccount = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/account/createNewAccount", mape);
                    jsonObject = JSON.parseObject(createNewAccount);
                    if (!jsonObject.getString("code").equals("0000"))
                        return Result.fail(00, jsonObject.getString("message"));
                    JSONObject dataNewAccount = jsonObject.getJSONObject("data");
                    String newAcctNum = dataNewAccount.getString("newAcctNum");
                    //确认新增合同号
                    mape.clear();
                    mape.put("accountNumber", newAcctNum);
                    String accountbalance = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/account/account-balance", mape);
                    jsonObject = JSON.parseObject(accountbalance);
                    if (!jsonObject.getString("code").equals("0000"))
                        return Result.fail(001, jsonObject.getString("message"));
                    mape.clear();
                    //客户购物车的信息
                    String shopCartOtherInfo = iSaleHttpUtil.getWithCookie(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/cart/shopCartOtherInfo");
                    jsonObject = JSON.parseObject(shopCartOtherInfo);
                    JSONObject datashop = jsonObject.getJSONObject("data");
                    String custOrderUUID = datashop.getString("custOrderUUID");
                    String serialNum = datashop.getString("serialNum");
                    String remark = datashop.getString("remark");
                    //修改家庭云手机号
                    mape.clear();
                    mape.put("subProdOrderItemUUID", subProdOrderItemUUID);
                    mape.put("attrValue", "18852211754");
                    mape.put("attrValueId", "");
                    mape.put("ordsubProdInstAttrUUID", ordsubProdInstAttrUUID);
                    mape.put("attrValueName", "手机号码");
                    mape.put("ordMainPordOrderItemUUID", UUid);
                    String modifyCacheSubProdInstAttr = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/cart/modifyCacheSubProdInstAttr", mape);
                    jsonObject = JSON.parseObject(modifyCacheSubProdInstAttr);
                    if (!jsonObject.getString("code").equals("0000")) {
                        String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+jsonObject.getString("message");
                        mapa.put("remarks",remarks);
                        mapa.put("custOrderNbr","");
                        Insertauser(mapa);
                        return Result.fail(002, jsonObject.getString("message"));
                    }
                    mape.clear();
                    mape.put("contactName", "");
                    mape.put("contactPhone", mapa.get("phone").toString());
                    mape.put("mainProdUUID", UUid);
                    mape.put("selectedBookingTime", mapa.get("selectedBookingTime").toString());
                    mape.put("selectedBookingTimeSlot", "08:00-10:00");
                    mape.put("installAddr", mapa.get("installAddr").toString());
                    //修改装机地址信息
                    String saveaddrotherinfo = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/address-selection/save-addr-other-info", mape);
                    jsonObject = JSON.parseObject(saveaddrotherinfo);
                    if (!jsonObject.getString("code").equals("0000")) {
                        String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+jsonObject.getString("message");
                        mapa.put("remarks",remarks);
                        mapa.put("custOrderNbr","");
                        Insertauser(mapa);
                        return Result.fail(003, jsonObject.getString("message"));
                    }
                    mape.clear();
                    mape.put("selectedBookingDate", mapa.get("selectedBookingTime").toString());
                    mape.put("selectedBookingTimeCode", "08:00-10:00");
                    mape.put("contactPhone", mapa.get("phone").toString());
                    mape.put("remark", remark);
                    //修改订单的备注信息
                    String shopCartOtherInfoDto = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/cart/shopCartOtherInfoDto", mape);
                    jsonObject = JSON.parseObject(shopCartOtherInfoDto);
                    if (!jsonObject.getString("code").equals("0000")) {
                        String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+jsonObject.getString("message");
                        mapa.put("remarks",remarks);
                        mapa.put("custOrderNbr","");
                        Insertauser(mapa);
                        return Result.fail(004, jsonObject.getString("message"));
                    }
                    mape.clear();
                    mape.put("offerOrderItemUUID", promOrderItemUUID);
                    mape.put("attrValue", mapa.get("payMethod").toString());
                    // 改变付费方式
                    String getbss = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/cart/selectPaymentMode", mape);
                    jsonObject = JSON.parseObject(getbss);
                    if (!jsonObject.getString("code").equals("0000")) {
                        String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+jsonObject.getString("message");
                        mapa.put("remarks",remarks);
                        mapa.put("custOrderNbr","");
                        Insertauser(mapa);
                        return Result.fail(005, jsonObject.getString("message"));
                    }
                    //获取购物车bss
//                    String getshopcartbss=iSaleHttpUtil.getWithCookie(URL+"shopping-cart/get-shopcart-bss");
//                    jsonObject = JSON.parseObject(getshopcartbss);
//                    if (jsonObject.getString("data")!="null") {
//                        return Result.fail(050, jsonObject.getString("message"));
//                    }
                    // 获取alert报错信息
                    mape.clear();
                    mape.put("type", "2");
                    String goAlerRule = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/shopcart-rule/goAlertRule", mape);
                    jsonObject = JSON.parseObject(goAlerRule);
                    if (!jsonObject.getString("code").equals("0000")) {
                        if (jsonObject.getString("code").equals("10008")) {
                            JSONObject ruleFail = jsonObject.getJSONObject("data");
                            JSONArray ruleFailList = ruleFail.getJSONArray("ruleFailList");
                            String rule = ruleFailList.getString(0);
                            JSONObject ruleNum = JSON.parseObject(rule);
                            String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+ ruleNum.getString("message");
                            mapa.put("remarks",remarks);
                            mapa.put("custOrderNbr","");
                            Insertauser(mapa);
                            return Result.success(007, ruleNum.getString("message"));
                        } else {
                            String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+jsonObject.getString("message");
                            mapa.put("remarks",remarks);
                            mapa.put("custOrderNbr","");
                            Insertauser(mapa);
                            return Result.fail(006, jsonObject.getString("message"));
                        }
                    }
                    //设置购物车规则
                    String goSetRule = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/shopcart-rule/goSetRule", mape);
                    jsonObject = JSON.parseObject(goSetRule);
                    if (!jsonObject.getString("code").equals("0000")) {
                        String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+jsonObject.getString("message");
                        mapa.put("remarks",remarks);
                        mapa.put("custOrderNbr","");
                        Insertauser(mapa);
                        return Result.fail(1, jsonObject.getString("message"));
                    }
                    // 购物车报错规则校验
                    String goValRule = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/shopcart-rule/goValRule", mape);
                    jsonObject = JSON.parseObject(goValRule);
                    if (!jsonObject.getString("code").equals("0000")) {
                        String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+jsonObject.getString("message");
                        mapa.put("remarks",remarks);
                        mapa.put("custOrderNbr","");
                        Insertauser(mapa);
                        return Result.fail(2, jsonObject.getString("message"));
                    }
                    //验证2.0
                    String goValidation = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/cart/goValidation", mape);
                    jsonObject = JSON.parseObject(goValidation);
                    if (!jsonObject.getString("code").equals("0000")) {
                        String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+jsonObject.getString("message");
                        mapa.put("remarks",remarks);
                        mapa.put("custOrderNbr","");
                        Insertauser(mapa);
                        return Result.fail(3, jsonObject.getString("message"));
                    }
                    //验证
                    mape.clear();
                    String validate = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/real-name/validate", mape);
                    jsonObject = JSON.parseObject(validate);
                    if (!jsonObject.getString("code").equals("0000")) {
                        String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+jsonObject.getString("message");
                        mapa.put("remarks",remarks);
                        mapa.put("custOrderNbr","");
                        Insertauser(mapa);
                        return Result.fail(4, jsonObject.getString("message"));
                    }
                    //验证路线来源
                    String routSouce = iSaleHttpUtil.getWithCookie(URL + "cordercore-74f4564b7-m47d4/api-orderprocess/shopcart-process/routSouce");
                    jsonObject = JSON.parseObject(routSouce);
                    if (!jsonObject.getString("code").equals("0000")) {
                        String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+jsonObject.getString("message");
                        mapa.put("remarks",remarks);
                        mapa.put("custOrderNbr","");
                        Insertauser(mapa);
                        return Result.fail(5, jsonObject.getString("message"));
                    }
                    //获取7单号
                    mape.clear();
                    mape.put("custOrderId", "");
                    mape.put("custNbr", "");
                    mape.put("cpc_token", "");
                    String orderDetail = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-orderprocess/my_performance/orderDetail", mape);
                    jsonObject = JSON.parseObject(orderDetail);
                    if (!jsonObject.getString("code").equals("0000")) {
                        String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+jsonObject.getString("message");
                        mapa.put("remarks",remarks);
                        mapa.put("custOrderNbr","");
                        Insertauser(mapa);
                        return Result.fail(6, jsonObject.getString("message"));
                    }
                    JSONObject orderDetaildata = jsonObject.getJSONObject("data");
                    String custOrderNbr = orderDetaildata.getString("custOrderNbr");
                    //获取订单的详情信息
                    mape.clear();
                    String orderOtherInfo = iSaleHttpUtil.getWithCookie(URL + "cordercore-74f4564b7-m47d4/api-orderprocess/my_performance/orderOtherInfo?custNbr="+custOrderNbr+"&cpc_token=");
                    jsonObject = JSON.parseObject(orderOtherInfo);
                    // 删除不必要的信息
//                    mape.clear();
//                    mape.put("custOrderIds", custOrderUUID);
//                    String delShopCartCache = iSaleHttpUtil.postWithCookieFormDatas(URL + "cordercore-74f4564b7-m47d4/api-ordershopcart/cart/delShopCartCache", mape);
//                    jsonObject = JSON.parseObject(delShopCartCache);
//                    if (!jsonObject.getString("code").equals("0000"))
//                        return Result.fail(8, jsonObject.getString("message"));
                    mape.clear();
                    //收银操作
                    String goCashier = iSaleHttpUtil.getWithCookie(URL + "cordercore-74f4564b7-m47d4/api-orderprocess/shopcart-process/goCashier");
                    jsonObject = JSON.parseObject(goCashier);
                    if (!jsonObject.getString("code").equals("0000")) {
                        String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+jsonObject.getString("message");
                        mapa.put("remarks",remarks);
                        mapa.put("custOrderNbr","");
                        Insertauser(mapa);
                        return Result.fail(9, jsonObject.getString("message"));
                    }
                    //oocp
                    String tossosystem = iSaleHttpUtil.getWithCookie(URL + "cordercore-74f4564b7-m47d4/api-orderprocess/oocp/to-sso-system?funcName=SYS_DAOGOU_B");
                    jsonObject = JSON.parseObject(tossosystem);
                    if (!jsonObject.getString("code").equals("0000")) {
                        String remarks=mapa.get("remark").toString()+"-"+serialNum+"-错误信息:"+jsonObject.getString("message");
                        mapa.put("remarks",remarks);
                        mapa.put("custOrderNbr","");
                        Insertauser(mapa);
                        return Result.fail(10, jsonObject.getString("message"));
                    }
                    //进行受理插入
                    Date date=new Date();
                    int cid=0;
                    int wid=0;
                    SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String sj=sfs.format(date);
                    Work work=new Work();
                    Cust cust=new Cust();
                    int count=custInfoMapper.chacount();
                    cid=count+1;
                    if (custInfoMapper.chawork(String.valueOf(cid))>1){
                        cid=cid+1;
                    }
                    if(custInfoMapper.chachongcount(mapa.get("custname").toString(),mapa.get("custphone").toString(),mapa.get("idcred").toString())>0){
                        cid=custInfoMapper.chachongname(mapa.get("custname").toString(),mapa.get("custphone").toString(),mapa.get("idcred").toString());
                        int workcount=workInfoMapper.chacountw();
                        wid=workcount+1;
                        if (workInfoMapper.chaw(String.valueOf(wid))>1){
                            return Result.fail(0, ErrorEnum.CHONG_FU);
                        }else {
                            work.setWorkid(String.valueOf(wid));
                            work.setCustid(String.valueOf(cid));
                            int proid=prodInMapper.chaAccept(mapa.get("proname").toString(),mapa.get("depaname").toString());
                            work.setAcceptid(String.valueOf(proid));
                            if (custOrderNbr!=null&&custOrderNbr!=""){
                                orderInfosMapper.insertOne(custOrderNbr);
                                int orderid=orderInfosMapper.chaorderId(custOrderNbr);
                                work.setOrderid(String.valueOf(orderid));
                            }else{
                                work.setOrderid(String.valueOf(0));
                            }
                            work.setServiceName(mapa.get("serviceName").toString());
                            work.setRemark(mapa.get("remark").toString());
                            work.setStatus(mapa.get("status").toString());
                            work.setBroadband( mapa.get("broadband") == "" ? null : mapa.get("broadband").toString());
                            work.setAppointmenttime(mapa.get("appointmenttime").toString());
                            work.setPaymentamount(mapa.get("paymentamount").toString());
                            work.setXdtime(sj);
                            work.setChannl(mapa.get("channl").toString());
                            work.setWorkserved(mapa.get("workserved").toString());
                            work.setPaymentstate("");
                            int wresult=workInfoMapper.Autocontrolledwork(work);
                            if (wresult>0){
                                fishordersInfoMapper.upstatos("营销成功",1,String.valueOf(work.getId()),Integer.parseInt(mapa.get("id").toString()));
                                return Result.success(1,"营销成功");
                            }else{
                                return Result.fail(0, ErrorEnum.FIAL_ERROR);
                            }
                        }
                    }else {
                        cust.setCid(String.valueOf(cid));
                        cust.setCustname(mapa.get("custname").toString());
                        cust.setCustidcard(mapa.get("idcred").toString() == "" ? null : mapa.get("idcred").toString());
                        cust.setCustphone(mapa.get("custphone").toString());
                        cust.setCustaddress(mapa.get("custaddress").toString());
                        cust.setCustarea(mapa.get("custarea").toString());
                        cust.setCustremark(mapa.get("custremark").toString());
                        cust.setCustcreater(mapa.get("custcreater").toString());
                        cust.setCustcreatertime(sj);
                        cust.setCustreserved("");
                        int cresult=custInfoMapper.Autocontrolledcust(cust);
                        int workcount=workInfoMapper.chacountw();
                        wid=workcount+1;
                        if (workInfoMapper.chaw(String.valueOf(wid))>1){
                            return Result.fail(0, ErrorEnum.CHONG_FU);
                        }else {
                            work.setWorkid(String.valueOf(wid));
                            work.setCustid(cust.getCid());
                            int proid=prodInMapper.chaAccept(mapa.get("proname").toString(),mapa.get("depaname").toString());
                            work.setAcceptid(String.valueOf(proid));
                            if (custOrderNbr!=null&&custOrderNbr!=""){
                                orderInfosMapper.insertOne(custOrderNbr);
                                int orderid=orderInfosMapper.chaorderId(custOrderNbr);
                                work.setOrderid(String.valueOf(orderid));
                            }else{
                                work.setOrderid(String.valueOf(0));
                            }
                            work.setServiceName(mapa.get("serviceName").toString());
                            work.setRemark(mapa.get("remark").toString());
                            work.setStatus(mapa.get("status").toString());
                            work.setBroadband( mapa.get("broadband") == "" ? null : map.get("broadband").toString());
                            work.setAppointmenttime(mapa.get("appointmenttime").toString());
                            work.setPaymentamount(mapa.get("paymentamount").toString());
                            work.setXdtime(sj);
                            work.setChannl(mapa.get("channl").toString());
                            work.setWorkserved(mapa.get("workserved").toString());
                            work.setPaymentstate("");
                        }
                        int wresult=workInfoMapper.Autocontrolledwork(work);
                        if (wresult>0&&cresult>0){
                            fishordersInfoMapper.upstatos("营销成功",1,String.valueOf(work.getId()),Integer.parseInt(mapa.get("id").toString()));
                            return Result.success(1,"营销成功");
                        }else{
                            return Result.fail(0,ErrorEnum.FIAL_ERROR);
                        }
                    }

                } else {
                    return Result.fail(0, "切换失败");
                }
            }else {
                return Result.fail(0,"切换失败");
            }
        }
    }

    @Override
    public Result autoaccepts(ISaleHttpUtil iSaleHttpUtil, Map mapa) throws Exception {
        String org=mapa.get("c3name").toString();
        int orgid= AreaEnum.getValueByName(org);
        String channelNbr=AreaEnum.getValueByCode(org);
        Map<String,Object> map=new HashMap();
        Map mape=new HashMap();
        List<Map> list=new LinkedList<>();
        Map maps=new HashMap();
        maps.put("deskId","6027");
        maps.put("orgId",orgid);
        list.add(maps);
        Map maps1=new HashMap();
        maps1.put("deskId","6006");
        maps1.put("orgId","800000000037");
        list.add(maps1);
        Map maps2=new HashMap();
        maps2.put("deskId","6049");
        maps2.put("orgId",orgid);
        list.add(maps2);
        Map maps3=new HashMap();
        maps3.put("deskId","1114409");
        maps3.put("orgId",orgid);
        list.add(maps3);
        map.put("choosenOrgs",list);
        String custid=null;
        /**
         * 切换地势
         * 需要改变请求头的信息
         * */
        String stt = iSaleHttpUtil.postWithCookieFormData(URL + "chome-fc45d4cdf-htxbj/api/deskDetail", map);
        String queryaddresslist=null;
        jsonObject = JSON.parseObject(stt);
        if (jsonObject.getString("code").equals("0000") && jsonObject.getString("status").equals("200")) {
            String sts = iSaleHttpUtil.getWithCookie(URL + "index/changePosition-bss?channelNbr="+channelNbr+"&login=18072892408&orgid="+orgid);
            jsonObject = JSON.parseObject(sts);
            if (!jsonObject.getString("success").equals("true")) {
                return Result.fail(0,"切换失败");
            }
            mape.put("address",mapa.get("address").toString());
            mape.put("jxbm","");
            mape.put("c4",mapa.get("c4name").toString());
            mape.put("code_number","");
            mape.put("page",mapa.get("page").toString());
            mape.put("page_size",10);
            queryaddresslist=iSaleHttpUtil.postWithCookieFormDatas(URL+"tools/query-address-list",mape);
            jsonObject=JSON.parseObject(queryaddresslist);
        }

        return Result.success(1,jsonObject);
    }

    @Override
    public String sss(ISaleHttpUtils iSaleHttpUtil){

        return "ss";
    }

    @Override
    public Result sc(Map map) {
        Date date=new Date();
        int cid=0;
        int wid=0;
        int csid=0;
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj=sfs.format(date);
        Map maps=new HashMap();
        Map mapa=new HashMap();
        Work work=new Work();
        Cust cust=new Cust();
        int count=custInfoMapper.queryCid();
        cid=count+1;
        if (custInfoMapper.chawork(String.valueOf(cid))>1){
            return Result.fail(0, ErrorEnum.CHONG_FU);
        }else if(custInfoMapper.chachongcount(map.get("custname").toString(),map.get("custphone").toString(),map.get("custidcard").toString())>0){
            csid=custInfoMapper.chachongname(map.get("custname").toString(),map.get("custphone").toString(),map.get("custidcard").toString());
            int wmid=workInfoMapper.queryId();
            wid=wmid+1;
            if (workInfoMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                work.setWorkid(String.valueOf(wid));
                work.setCustid(String.valueOf(csid));
                int proid=prodInMapper.chaAccept(map.get("accept").toString(),map.get("depaname").toString());
                work.setAcceptid(String.valueOf(proid));
                if (map.get("orderno").toString()!=null&&map.get("orderno").toString()!=""){
                    orderInfosMapper.insertOne(map.get("orderno").toString());
                    int orderid=orderInfosMapper.chaorderId(map.get("orderno").toString());
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
                int wresult=workInfoMapper.Autocontrolledwork(work);
                if (wresult>0){
                    zcdistributorMapper.updatewid(Integer.parseInt(map.get("id").toString()),work.getId());
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
            cust.setCustremark(map.get("custremark").toString());
            cust.setCustcreater(map.get("custcreater").toString());
            cust.setCustcreatertime(sj);
            cust.setCustreserved("");
            int cresult=custInfoMapper.Autocontrolledcust(cust);
            int wmid=workInfoMapper.queryId();
            wid=wmid+1;
            if (workInfoMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                work.setWorkid(String.valueOf(wid));
                work.setCustid(String.valueOf(cust.getId()));
                int proid=prodInMapper.chaAccept(map.get("accept").toString(),map.get("depaname").toString());
                work.setAcceptid(String.valueOf(proid));
                if (map.get("orderno").toString()!=null&&map.get("orderno").toString()!=""){
                    orderInfosMapper.insertOne(map.get("orderno").toString());
                    int orderid=orderInfosMapper.chaorderId(map.get("orderno").toString());
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
            }
            int wresult=workInfoMapper.Autocontrolledwork(work);
            if (wresult>0&&cresult>0){
                zcdistributorMapper.updatewid(Integer.parseInt(map.get("id").toString()),work.getId());
                return Result.success(1,"营销成功");
            }else{
                return Result.fail(0,ErrorEnum.FIAL_ERROR);
            }
        }
    }

    @Override
    public Result sctent(Map map) {
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
        int count=custInfoMapper.queryCid();
        cid=count+1;
        if (custInfoMapper.chawork(String.valueOf(cid))>1){
            return Result.fail(0, ErrorEnum.CHONG_FU);
        }else if(custInfoMapper.chachongcount(map.get("custname").toString(),map.get("custphone").toString(),map.get("custidcard").toString())>0){
            csid=custInfoMapper.chachongname(map.get("custname").toString(),map.get("custphone").toString(),map.get("custidcard").toString());
            int wmid=workInfoMapper.queryId();
            wid=wmid+1;
            if (workInfoMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                work.setWorkid(String.valueOf(wid));
                work.setCustid(String.valueOf(csid));
                int proid=prodInMapper.chaAccept(map.get("accept").toString(),map.get("depaname").toString());
                work.setAcceptid(String.valueOf(proid));
                if (map.get("orderno").toString()!=null&&map.get("orderno").toString()!=""){
                    orderInfosMapper.insertOne(map.get("orderno").toString());
                    int orderid=orderInfosMapper.chaorderId(map.get("orderno").toString());
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
                int wresult=workInfoMapper.Autocontrolledwork(work);
                if (wresult>0){
                    tenthousandMapper.updatewid(Integer.parseInt(map.get("id").toString()),work.getId());
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
            cust.setCustremark(map.get("custremark").toString());
            cust.setCustcreater(map.get("custcreater").toString());
            cust.setCustcreatertime(sj);
            cust.setCustreserved("");
            int cresult=custInfoMapper.Autocontrolledcust(cust);
            int wmid=workInfoMapper.queryId();
            wid=wmid+1;
            if (workInfoMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                work.setWorkid(String.valueOf(wid));
                work.setCustid(String.valueOf(cust.getId()));
                int proid=prodInMapper.chaAccept(map.get("accept").toString(),map.get("depaname").toString());
                work.setAcceptid(String.valueOf(proid));
                if (map.get("orderno").toString()!=null&&map.get("orderno").toString()!=""){
                    orderInfosMapper.insertOne(map.get("orderno").toString());
                    int orderid=orderInfosMapper.chaorderId(map.get("orderno").toString());
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
            }
            int wresult=workInfoMapper.Autocontrolledwork(work);
            if (wresult>0&&cresult>0){
                tenthousandMapper.updatewid(Integer.parseInt(map.get("id").toString()),work.getId());
                return Result.success(1,"营销成功");
            }else{
                return Result.fail(0,ErrorEnum.FIAL_ERROR);
            }
        }
    }

    @Override
    public Result sctamll(Map map) {
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
        int count=custInfoMapper.queryCid();
        cid=count+1;
        if (custInfoMapper.chawork(String.valueOf(cid))>1){
            return Result.fail(0, ErrorEnum.CHONG_FU);
        }else if(custInfoMapper.chachongcount(map.get("custname").toString(),map.get("custphone").toString(),map.get("custidcard").toString())>0){
            csid=custInfoMapper.chachongname(map.get("custname").toString(),map.get("custphone").toString(),map.get("custidcard").toString());
            int wmid=workInfoMapper.queryId();
            wid=wmid+1;
            if (workInfoMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                work.setWorkid(String.valueOf(wid));
                work.setCustid(String.valueOf(csid));
                int proid=prodInMapper.chaAccept(map.get("accept").toString(),map.get("depaname").toString());
                work.setAcceptid(String.valueOf(proid));
                if (map.get("orderno").toString()!=null&&map.get("orderno").toString()!=""){
                    orderInfosMapper.insertOne(map.get("orderno").toString());
                    int orderid=orderInfosMapper.chaorderId(map.get("orderno").toString());
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
                int wresult=workInfoMapper.Autocontrolledwork(work);
                if (wresult>0){
                    tmallMapper.updatewid(Integer.parseInt(map.get("id").toString()),work.getId());
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
            cust.setCustremark(map.get("custremark").toString());
            cust.setCustcreater(map.get("custcreater").toString());
            cust.setCustcreatertime(sj);
            cust.setCustreserved("");
            int cresult=custInfoMapper.Autocontrolledcust(cust);
            int wmid=workInfoMapper.queryId();
            wid=wmid+1;
            if (workInfoMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                work.setWorkid(String.valueOf(wid));
                work.setCustid(String.valueOf(cust.getId()));
                int proid=prodInMapper.chaAccept(map.get("accept").toString(),map.get("depaname").toString());
                work.setAcceptid(String.valueOf(proid));
                if (map.get("orderno").toString()!=null&&map.get("orderno").toString()!=""){
                    orderInfosMapper.insertOne(map.get("orderno").toString());
                    int orderid=orderInfosMapper.chaorderId(map.get("orderno").toString());
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
            }
            int wresult = workInfoMapper.Autocontrolledwork(work);
            if (wresult > 0 && cresult > 0) {
                tmallMapper.updatewid(Integer.parseInt(map.get("id").toString()), work.getId());
                return Result.success(1, "营销成功");
            } else {
                return Result.fail(0, ErrorEnum.FIAL_ERROR);
            }
        }
    }

    @Override
    public Result sctreminl(Map map) {
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
        int count=custInfoMapper.queryCid();
        cid=count+1;
        if (custInfoMapper.chawork(String.valueOf(cid))>1){
            return Result.fail(0, ErrorEnum.CHONG_FU);
        }else if(custInfoMapper.chachongcount(map.get("custname").toString(),map.get("custphone").toString(),map.get("custidcard").toString())>0){
            csid=custInfoMapper.chachongname(map.get("custname").toString(),map.get("custphone").toString(),map.get("custidcard").toString());
            int wmid=workInfoMapper.queryId();
            wid=wmid+1;
            if (workInfoMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                work.setWorkid(String.valueOf(wid));
                work.setCustid(String.valueOf(csid));
                int proid=prodInMapper.chaAccept(map.get("accept").toString(),map.get("depaname").toString());
                work.setAcceptid(String.valueOf(proid));
                if (map.get("orderno").toString()!=null&&map.get("orderno").toString()!=""){
                    orderInfosMapper.insertOne(map.get("orderno").toString());
                    int orderid=orderInfosMapper.chaorderId(map.get("orderno").toString());
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
                int wresult=workInfoMapper.Autocontrolledwork(work);
                if (wresult>0){
                    tenthousandMapper.updatewid(Integer.parseInt(map.get("id").toString()),work.getId());
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
            cust.setCustremark(map.get("custremark").toString());
            cust.setCustcreater(map.get("custcreater").toString());
            cust.setCustcreatertime(sj);
            cust.setCustreserved("");
            int cresult=custInfoMapper.Autocontrolledcust(cust);
            int wmid=workInfoMapper.queryId();
            wid=wmid+1;
            if (workInfoMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                work.setWorkid(String.valueOf(wid));
                work.setCustid(String.valueOf(cust.getId()));
                int proid=prodInMapper.chaAccept(map.get("accept").toString(),map.get("depaname").toString());
                work.setAcceptid(String.valueOf(proid));
                if (map.get("orderno").toString()!=null&&map.get("orderno").toString()!=""){
                    orderInfosMapper.insertOne(map.get("orderno").toString());
                    int orderid=orderInfosMapper.chaorderId(map.get("orderno").toString());
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
            }
            int wresult=workInfoMapper.Autocontrolledwork(work);
            if (wresult>0&&cresult>0){
                terminalMapper.updatewid(Integer.parseInt(map.get("id").toString()),work.getId());
                return Result.success(1,"营销成功");
            }else{
                return Result.fail(0,ErrorEnum.FIAL_ERROR);
            }
        }
    }

    @Override
    @Scheduled(cron = "0 */5 * * * ?")
    public String refresh() {
        ISaleHttpUtils iSaleHttpUtil=new ISaleHttpUtils();
        for (ArrarEnum arr:ArrarEnum.values()){
            String bssverify= bssverifyMapper.qerytoken(arr.getName());
            Map map=new HashMap();
            map.put("usercode",arr.getName());
            map.put("xAuthToken",bssverify);
            String shoiff=iSaleHttpUtil.getWithCookie(URL+"chome-fc45d4cdf-htxbj/api/staffInfo",map);
            map.clear();
        }
        return null;
    }

    public Result Insertauser(Map mapa){
        Date date=new Date();
        int cid=0;
        int wid=0;
        int csid=0;
        SimpleDateFormat sfs=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj=sfs.format(date);
        Work work=new Work();
        Cust cust=new Cust();
        int count=custInfoMapper.queryCid();
        cid=count+1;
        if (custInfoMapper.chawork(String.valueOf(cid))>1){
            return Result.fail(0, ErrorEnum.CHONG_FU);
        }else if(custInfoMapper.chachongcount(mapa.get("custname").toString(),mapa.get("custphone").toString(),mapa.get("idcred").toString())>0){
            csid=custInfoMapper.chachongname(mapa.get("custname").toString(),mapa.get("custphone").toString(),mapa.get("idcred").toString());
            int wmid=workInfoMapper.queryId();
            wid=wmid+1;
            if (workInfoMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                work.setWorkid(String.valueOf(wid));
                work.setCustid(String.valueOf(csid));
                int proid=prodInMapper.chaAccept(mapa.get("proname").toString(),mapa.get("depaname").toString());
                work.setAcceptid(String.valueOf(proid));
                if (mapa.get("custOrderNbr").toString()!=null&&mapa.get("custOrderNbr").toString()!=""){
                    orderInfosMapper.insertOne(mapa.get("custOrderNbr").toString());
                    int orderid=orderInfosMapper.chaorderId(mapa.get("custOrderNbr").toString());
                    work.setOrderid(String.valueOf(orderid));
                }else{
                    work.setOrderid(String.valueOf(0));
                }
                work.setServiceName(mapa.get("serviceName").toString());
                work.setRemark(mapa.get("remark").toString());
                work.setStatus(mapa.get("status").toString());
                work.setBroadband( mapa.get("broadband") == "" ? null : mapa.get("broadband").toString());
                work.setAppointmenttime(mapa.get("appointmenttime").toString());
                work.setPaymentamount(mapa.get("paymentamount").toString());
                work.setXdtime(sj);
                work.setChannl(mapa.get("channel").toString());
                work.setWorkserved(mapa.get("workserved").toString());
                work.setPaymentstate("");
                int wresult=workInfoMapper.Autocontrolledwork(work);
                if (wresult>0){
                    fishordersInfoMapper.upstatos("营销成功",1,String.valueOf(work.getId()),Integer.parseInt(mapa.get("id").toString()));
                    return Result.success(1,"营销成功");
                }else{
                    return Result.fail(0, ErrorEnum.FIAL_ERROR);
                }
            }
        }else {
            cust.setCid(String.valueOf(cid));
            cust.setCustname(mapa.get("custname").toString());
            cust.setCustidcard(mapa.get("idcred").toString() == "" ? null : mapa.get("idcred").toString());
            cust.setCustphone(mapa.get("custphone").toString());
            cust.setCustaddress(mapa.get("custaddress").toString());
            cust.setCustarea(mapa.get("custarea").toString());
            cust.setCustremark(mapa.get("custremark").toString());
            cust.setCustcreater(mapa.get("custcreater").toString());
            cust.setCustcreatertime(sj);
            cust.setCustreserved("");
            int cresult=custInfoMapper.Autocontrolledcust(cust);
            int wmid=workInfoMapper.queryId();
            wid=wmid+1;
            if (workInfoMapper.chaw(String.valueOf(wid))>1){
                return Result.fail(0, ErrorEnum.CHONG_FU);
            }else {
                work.setWorkid(String.valueOf(wid));
                work.setCustid(String.valueOf(cust.getId()));
                int proid=prodInMapper.chaAccept(mapa.get("proname").toString(),mapa.get("depaname").toString());
                work.setAcceptid(String.valueOf(proid));
                if (mapa.get("custOrderNbr").toString()!=null&&mapa.get("custOrderNbr").toString()!=""){
                    orderInfosMapper.insertOne(mapa.get("custOrderNbr").toString());
                    int orderid=orderInfosMapper.chaorderId(mapa.get("custOrderNbr").toString());
                    work.setOrderid(String.valueOf(orderid));
                }else{
                    work.setOrderid(String.valueOf(0));
                }
                work.setServiceName(mapa.get("serviceName").toString());
                work.setRemark(mapa.get("remarks").toString());
                work.setStatus(mapa.get("status").toString());
                work.setBroadband( mapa.get("broadband") == "" ? null : mapa.get("broadband").toString());
                work.setAppointmenttime(mapa.get("appointmenttime").toString());
                work.setPaymentamount(mapa.get("paymentamount").toString());
                work.setXdtime(sj);
                work.setChannl(mapa.get("channel").toString());
                work.setWorkserved(mapa.get("workserved").toString());
                work.setPaymentstate("");
            }
            int wresult=workInfoMapper.Autocontrolledwork(work);
            if (wresult>0&&cresult>0){
                fishordersInfoMapper.upstatos("营销成功",1,String.valueOf(work.getId()),Integer.parseInt(mapa.get("id").toString()));
                return Result.success(1,"营销成功");
            }else{
                return Result.fail(0,ErrorEnum.FIAL_ERROR);
            }
        }
    }

    public String custAccount(Map map,ISaleHttpUtil iSaleHttpUtil){
        Map<String,Object> mapa=new HashMap();
        Map mape=new HashMap();
        List<Map> list=new LinkedList<>();
        Map maps=new HashMap();
        maps.put("contactName",map.get("contactName").toString());
        maps.put("contactTypeVal","");
        maps.put("custAddress",map.get("custAddress").toString());
        maps.put("custIdAddress",map.get("custIdAddress").toString());
        maps.put("contactPhone",map.get("contactPhone").toString());
        maps.put("custName",map.get("custName").toString());
        maps.put("custType","公众客户");
        maps.put("idNum",map.get("idNum").toString());
        maps.put("idType","身份证");
        maps.put("lanName",map.get("lanName").toString());
        list.add(maps);
        mapa.put("customer",list);
        maps.clear();
        maps.put("billWay","不投递");
        maps.put("payMethod","现金");
        maps.put("accountName",map.get("contactName").toString());
        list.add(maps);
        mapa.put("account",list);
        String custAccount=iSaleHttpUtil.postWithCookieFormData(URL+"cordercore-74f4564b7-m47d4/api-custorder/custAccount",mapa);
        JSONObject jsondata=JSON.parseObject(custAccount);
        if (!jsondata.getString("code").equals("0000")){
            return "0001";
        }else{
            return "0000";
        }
    }
}
