package com.role.service.impl;

import com.role.entity.Rights;
import com.role.mapper.RightsMapper;
import com.role.service.RightsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("rightsService")
public class RightsServiceImpl implements RightsService {

    @Resource
    private RightsMapper rightsMapper;

    @Override
    public List<Rights> queryAllr() {
        return rightsMapper.queryAllr();
    }

    @Override
    public int insertOneRight(Rights rights) {
        return rightsMapper.insertOneRight(rights);
    }

    @Override
    public int updateOneRight(Map map) {
        return rightsMapper.updateOneRight(map);
    }

    @Override
    public int deleteOneRight(int id) {
        return rightsMapper.deleteOneRight(id);
    }
}
