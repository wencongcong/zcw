package com.role.service;

import com.role.entity.Inform;

import java.util.List;
import java.util.Map;

public interface InformService {
    public List<Inform> querAll(Map map);

    public int insertOneInform(Inform inform);

    public int updateOneInform(Map map);

    public int deleteOneInform(int id);
}
