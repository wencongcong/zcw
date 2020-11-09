package com.role.service.impl;

import com.role.entity.Inform;
import com.role.mapper.InformMapper;
import com.role.service.InformService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("informService")
public class InformServiceImpl implements InformService {

    @Resource
    private InformMapper informMapper;

    @Override
    public List<Inform> querAll(Map map) {
        return informMapper.querAll(map);
    }

    @Override
    public int insertOneInform(Inform inform) {
        return informMapper.insertOneInform(inform);
    }

    @Override
    public int updateOneInform(Map map) {
        return informMapper.updateOneInform(map);
    }

    @Override
    public int deleteOneInform(int id) {
        return informMapper.deleteOneInform(id);
    }
}
