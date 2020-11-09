package com.service.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.service.entity.Cust;
import com.service.entity.Work;
import com.service.mapper.WorkMapper;
import com.service.service.WorkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("workService")
public class WorkServiceImpl implements WorkService {

    @Resource
    private WorkMapper workMapper;

    @Override
    public int insertOneWork(Work work) {
        return workMapper.insertOneWork(work);
    }

    @Override
    public int chaChong(String workid) {
        return workMapper.chaChong(workid);
    }

    @Override
    public List<Work> queryAll(Map map) {
        String address=null;
        String idcard=null;
        Work ss=null;
        List<Work> list=new ArrayList<>();
        List<Work> lists=new ArrayList<>();
        try {
        if(map.get("random").toString().equals("1")){
            list=workMapper.queryAll(map);
            for (int i=0;i<list.size();i++) {
                ss=list.get(i);
                Cust cs = (Cust) ss.getCid();
                address=cs.getCustaddress();
                idcard=cs.getCustidcard();
                String inoNumber = null;
                String caddress=null;
                if (!StringUtils.isEmpty(idcard) ||!StringUtils.isEmpty(address)) {
                    if (idcard.length()<15){
                        inoNumber=idcard;
                    }else{
                        inoNumber = idcard.substring(0, 3) + "*****" + idcard.substring(idcard.length()-4,
                                idcard.length());
                    }
                    if (address.length()<=4){
                        caddress=address;
                    }else{
                        caddress=address.substring(0,3)+"*****"+address.substring(address.length()-5,address.length());
                    }
                    cs.setCustidcard(inoNumber);
                    cs.setCustaddress(caddress);
                } else {
                    cs.setCustidcard("");
                    cs.setCustaddress("");
                }
                ss.setCid(cs);
                lists.add(ss);
            }
        }else {
            lists=workMapper.queryAll(map);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    public List<Map<String ,String >> groupCount(Map map) {

        return workMapper.groupCount(map);
    }

    @Override
    public int chaw(String wid) {
        return workMapper.chaw(wid);
    }

    @Override
    public int chacountw() {
        return workMapper.chacountw();
    }

    @Override
    public int sgchar(Map map) {
        return workMapper.sgchar(map);
    }

    @Override
    public int Updateremark(Map map) {
        return workMapper.Updateremark(map);
    }

    @Override
    public int chaorderno(int id) {
        return workMapper.chaorderno(id);
    }

    @Override
    public int Ustatos(String statos, int orderid) {
        return workMapper.Ustatos(statos, orderid);
    }

    @Override
    public int Reminder(int reminder, int workid) {
        return workMapper.Reminder(reminder, workid);
    }

    @Override
    public int Ustatosoid(String statos, int oid, String workid) {
        return workMapper.Ustatosoid(statos, oid, workid);
    }

    @Override
    public int updateassign(Map map) {
        return workMapper.updateassign(map);
    }

    @Override
    public String chaStatus(String workid) {
        return workMapper.chaStatus(workid);
    }

    @Override
    public int uodateRemark(String remark, String workid) {
        return workMapper.uodateRemark(remark, workid);
    }

    @Override
    public int updateOrderId(int orderId, String workid) {
        return workMapper.updateOrderId(orderId, workid);
    }

    @Override
    public int chaWorkid(int orderId) {
        return workMapper.chaWorkid(orderId);
    }

    @Override
    public int updateStatue(String status, String workid) {
        return workMapper.updateStatue(status, workid);
    }

    @Override
    public int updatesettle(String settlementstatus,String verify,String completedtime, String workid) {
        return workMapper.updatesettle(settlementstatus,verify,completedtime, workid);
    }

    @Override
    public String chasttle(String workid) {
        return workMapper.chasttle(workid);
    }

    @Override
    public String whetherisempty() {
        return workMapper.whetherisempty();
    }

}