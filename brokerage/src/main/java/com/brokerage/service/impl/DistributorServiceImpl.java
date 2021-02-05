package com.brokerage.service.impl;

import com.brokerage.entity.Distributor;
import com.brokerage.mapper.DistributorMapper;
import com.brokerage.service.DistributorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("distributorService")
public class DistributorServiceImpl implements DistributorService {

    @Resource
    private DistributorMapper distributorMapper;

    @Override
    public int insertthedistribution(Map map) {

        Date date=new Date();
        SimpleDateFormat sfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj = sfs.format(date);
        Distributor distributor=new Distributor();
        distributor.setTerrain(map.get("terrain").toString());
        distributor.setProductname(map.get("productname").toString());
        distributor.setPrice(map.get("price").toString());
        distributor.setAdjustment(map.get("abjustment").toString());
        distributor.setProductsname(map.get("productsname").toString());
        distributor.setCreatetime(sj);
        //distributor.setTopimg(map.get("topimg").toString());
        distributor.setBottomid(Integer.parseInt(map.get("bottomid").toString()));
        return distributorMapper.insertthedistribution(distributor);
    }

    @Override
    public List<Distributor> queryallinformation(Map map) {
        return distributorMapper.queryallinformation(map);
    }

    @Override
    public int deleteDistributor(int id) {
        return distributorMapper.deleteDistributor(id);
    }

    @Override
    public int updateDistributor(Map map) {
        return distributorMapper.updateDistributor(map);
    }
}
