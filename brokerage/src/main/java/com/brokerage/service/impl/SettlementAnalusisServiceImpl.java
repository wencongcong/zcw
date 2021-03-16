package com.brokerage.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.brokerage.entity.Commission;
import com.brokerage.entity.SettlementAnalusis;
import com.brokerage.entity.Workorderlist;
import com.brokerage.mapper.*;
import com.brokerage.result.Result;
import com.brokerage.service.SettlementAnalusisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("settlementAnalusisService")
public class SettlementAnalusisServiceImpl implements SettlementAnalusisService {

    @Resource
    private SettlementAnalusisMapper settlementAnalusisMapper;
    @Resource
    private WorkInfoMapper workInfoMapper;
    @Resource
    private WorkorderListMapper workorderListMapper;
    @Resource
    private CommissionMapper commissionMapper;
    @Resource
    private OrderdetailsInfoMapper orderdetailsInfoMapper;

    @Override
    public Result queryAll(Map map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<SettlementAnalusis> list=settlementAnalusisMapper.queryAll(map);
        PageInfo<SettlementAnalusis> pageInfo=new PageInfo(list);
        return Result.success(1,pageInfo);
    }

    @Override
    public Result settlement(Map map) {
        SettlementAnalusis settlementAnalusis=new SettlementAnalusis();
        List<Workorderlist> works= this.workorderListMapper.queryAllorderlist(map);
        for (Workorderlist workorderlist:works){
            List<Map<String,String>> list=orderdetailsInfoMapper.queryAllorder(workorderlist.getOrderdetailsid());
            for (Map st:list){
                List<Commission> listcomm=commissionMapper.queryOneassnumber(st.get("assetnumber").toString());
                if (!listcomm.isEmpty()){
                    for (Commission comm:listcomm){
                        settlementAnalusis.setAssetnumber(comm.getNumberphone());
                        settlementAnalusis.setMonths(comm.getPayment());
                        settlementAnalusis.setCommission(comm.getCommission());
                        settlementAnalusis.setChannl(workorderlist.getChannl());
                        settlementAnalusis.setOrderno(workorderlist.getOrderon().trim());
                        settlementAnalusis.setProductsName(workorderlist.getProdctsName());
                        settlementAnalusis.setAcceptthechannel(workorderlist.getAcceptchannel());
                        settlementAnalusis.setDevelopingperson(workorderlist.getFirstdeveloppeople());
                        settlementAnalusis.setTollcollectionmanner(workorderlist.getChargemanner());
                        settlementAnalusis.setPrefectural(comm.getArea());
                        settlementAnalusis.setShouldbecommission(workorderlist.getMarkid());
                        settlementAnalusis.setStatucts("待结算");
                        settlementAnalusis.setCommissionpart(comm.getCommission());
                        settlementAnalusis.setProducttype(comm.getProducttype());
                        settlementAnalusis.setBehavior(st.get("behavior").toString());
                        settlementAnalusis.setScenarioname(comm.getProjectname());
                        settlementAnalusis.setStrategyname(comm.getNameofstrategy());
                        settlementAnalusis.setCommissiontype(comm.getRemunerationtype());
                        settlementAnalusis.setNetworkname(comm.getNetworkname());
                        settlementAnalusis.setAdjustAccountReason(comm.getReconciliationreason());
                        settlementAnalusis.setChannels(workorderlist.getChannels());
                        if (settlementAnalusisMapper.queryOneassetnumberormonths(comm.getNumberphone(),comm.getPayment())==0){
                            settlementAnalusisMapper.insertSett(settlementAnalusis);
                        }else{
                            continue;
                        }
                    }
                }else{
                    continue;
                }
            }
        }
       return Result.success(1,"运行成功");
    }

    @Override
    @Transactional
    public int updateSettStatus(String ordern, String status) {
        return settlementAnalusisMapper.updateSettStatus(ordern, status);
    }


}
