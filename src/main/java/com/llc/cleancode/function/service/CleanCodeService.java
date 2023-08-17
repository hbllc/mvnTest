package com.llc.cleancode.function.service;

import com.llc.cleancode.function.controller.req.FunctionReq;
import com.llc.cleancode.function.controller.req.FunctionSaveReq;
import com.llc.cleancode.function.controller.resp.FunctionResp;

/**
 * @author lilichuan
 */
public interface CleanCodeService {

    FunctionResp queryFunctionInfo(FunctionReq query);

    void save(FunctionSaveReq query);
}
