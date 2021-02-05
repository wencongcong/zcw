package com.brokerage.service;

import com.brokerage.result.Result;
import com.brokerage.util.ISaleHttpUtil;
import com.brokerage.util.ISaleHttpUtils;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface AutoAcceptService {

    public Result autoaccept(ISaleHttpUtil iSaleHttpUtil, Map map) throws Exception;

    public Result autoaccepts(ISaleHttpUtil iSaleHttpUtil, Map map) throws Exception;

    public String sss(ISaleHttpUtils iSaleHttpUtil);

    public Result sc(Map map);

    public Result sctent(Map map);

    public Result sctamll(Map map);

    public Result sctreminl(Map map);

    public String refresh();
}
