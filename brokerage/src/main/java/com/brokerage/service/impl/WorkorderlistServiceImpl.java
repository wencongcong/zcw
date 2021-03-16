package com.brokerage.service.impl;

import com.brokerage.entity.Work;
import com.brokerage.entity.Workorderlist;
import com.brokerage.entity.Zcdistributor;
import com.brokerage.mapper.OrderdetailsInfoMapper;
import com.brokerage.mapper.WorkInfoMapper;
import com.brokerage.mapper.WorkorderListMapper;
import com.brokerage.result.Result;
import com.brokerage.service.WorkorderlistService;
import com.brokerage.util.MathUtil;
import com.brokerage.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("workorderlistService")
public class WorkorderlistServiceImpl implements WorkorderlistService {

    @Resource
    private WorkInfoMapper workInfoMapper;
    @Resource
    private WorkorderListMapper workorderListMapper;
    @Resource
    private OrderdetailsInfoMapper orderdetailsInfoMapper;


    @Override
    @Scheduled(cron = "0 */30 * * * ?")
    public Result workorder() {
        Workorderlist workorderlist=new Workorderlist();
        List<Work> list= workInfoMapper.queryAlls();
        for (Work work:list){
            workorderlist.setAcceptid(work.getAcceptid());
            workorderlist.setChannl(work.getChannl());
            workorderlist.setOrderon(work.getOid().getOrderNo());
            workorderlist.setCustname(work.getCid().getCustname());
            workorderlist.setOrderstatus(work.getOid().getOstats());
            workorderlist.setChargemanner(work.getPaymentstate());
            workorderlist.setAcceptchannel(work.getOid().getAcceptchannal());
            workorderlist.setFirstdeveloppeople(work.getOid().getThefirstName());
            workorderlist.setFirstdeveloppeopleno(work.getOid().getThefirstNo());
            workorderlist.setOrderdetailsid(work.getOid().getOrderdetailsId());
            workorderlist.setProdctsName(work.getPid().getProductsName());
            workorderlist.setCreatetime(work.getOid().getAcceptancetime());
            workorderlist.setRemark(work.getRemark());
            workorderlist.setAutomatilmarking(work.getOid().getAutomatilmarking());
            workorderlist.setPaymoney(work.getPid().getRate());
            workorderlist.setChannels(work.getChannels());
            workorderlist.setAreas(work.getCid().getCustareas());
            String monety=MathUtil.add(work.getPid().getRate(),work.getPid().getIntegral());
            String st=MathUtil.multiply(monety,work.getPid().getSettlementratio(),2);
            workorderlist.setMarkid(st);
            if (workorderListMapper.oneOrderno(work.getOid().getOrderNo())==1){
                continue;
            }else{
                int result=workorderListMapper.insertWorkorderlist(workorderlist);
                if (result>0){
                    continue;
                }else{
                    return null;
                }
            }

        }
        return null;
    }

    @Override
    public Result queryAllWorkorder(Map map) {
//        PageUtil<Workorderlist> pageUtil = new PageUtil<Workorderlist>();
//        Map maps=new HashMap();
//        maps.put("channl",map.get("channl"));
//        maps.put("prodctsName",map.get("prodctsName"));
//        maps.put("orderon",map.get("orderon"));
//        maps.put("theassetnumber",map.get("theassetnumber"));
//        maps.put("orderstatus",map.get("orderstatus"));
//        maps.put("currentstatus",map.get("currentstatus"));
//        maps.put("PageSize",Integer.parseInt(map.get("pageSize").toString()));
//        int page =Integer.parseInt(map.get("pageSize").toString());
//        pageUtil.setTotalCount(workorderListMapper.queryAllordercount(maps));
//        pageUtil.setPageSize(page);
//        pageUtil.setCurrentPage(Integer.parseInt(map.get("currentPage").toString()));
//        int PageNow = pageUtil.getStartRow();
//        maps.put("PageNow",PageNow);
//        List<Workorderlist> listw=workorderListMapper.queryAllorderlist(maps);
//        pageUtil.setLists(listw);
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()),Integer.parseInt(map.get("pageSize").toString()));
        List<Workorderlist> works= this.workorderListMapper.queryAllorderlist(map);
        PageInfo<Workorderlist> pageInfo = new PageInfo(works);
        if (pageInfo!=null){
            return Result.success(1,pageInfo);
        }else{
            return Result.success(0,"无查询数据");
        }

    }
}
