package com.llc.cleancode.function.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.llc.cleancode.feign.aproject.AProjectFeignService;
import com.llc.cleancode.feign.aproject.dto.info.AProjectFunctionInfo;
import com.llc.cleancode.function.controller.req.FunctionReq;
import com.llc.cleancode.function.controller.req.FunctionSaveReq;
import com.llc.cleancode.function.controller.resp.FunctionResp;
import com.llc.cleancode.function.dto.FunctionQuery;
import com.llc.cleancode.function.dto.FunctionResult;
import com.llc.cleancode.function.repository.cache.FunctionCacheManger;
import com.llc.cleancode.function.repository.entity.FunctionEntity;
import com.llc.cleancode.function.repository.mapper.FunctionMapper;
import com.llc.cleancode.function.service.CleanCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lilichuan
 */
@Service
@Slf4j
public class CleanCodeServiceImpl implements CleanCodeService {

    @Resource
    private FunctionCacheManger cacheManger;
    @Resource
    private AProjectFeignService aProjectFeignService;
    @Resource
    private FunctionMapper functionMapper;

    @Override
    public FunctionResp queryFunctionInfo(FunctionReq query) {
        log.info("queryFunctionInfo,query:{}",query);

        this.checkQuery(query);

        FunctionQuery functionQuery = generateInfo(query);
        FunctionResult result = doQuery(functionQuery);
        log.info("queryFunctionInfo,result:{}",result);

        return this.buildResp(result);

    }

    @Override
    public void save(FunctionSaveReq req){
        FunctionEntity entity = this.buildFunctionEntity(req);
        int save = functionMapper.save(entity);
        if(1!=save){
            throw new RuntimeException("save error");
        }
    }


    private FunctionEntity buildFunctionEntity(FunctionSaveReq req) {
        return null;
    }

    private FunctionResp buildResp(FunctionResult result) {
        return null;
    }

    private FunctionQuery generateInfo(FunctionReq req) {
        AProjectFunctionInfo info = aProjectFeignService.getFunctionInfo("param");
        return BeanUtil.copyProperties(info,FunctionQuery.class);
    }

    private FunctionResult doQuery(FunctionQuery info) {
        FunctionResult result  = functionMapper.selectById(1L);
        AProjectFunctionInfo param = aProjectFeignService.getFunctionInfo("param");
        return null;
    }

    private void checkQuery(FunctionReq query) {
        checkParam(query);
        checkBusiness(query);
    }

    private void checkBusiness(FunctionReq query) {

    }

    private void checkParam(FunctionReq query) {

    }
}
