package com.llc.cleancode.function.repository.mapper;

import com.llc.cleancode.function.dto.FunctionResult;
import com.llc.cleancode.function.repository.entity.FunctionEntity;
import org.springframework.stereotype.Repository;

/**
 * @author lilichuan
 */
@Repository
public interface FunctionMapper {
    FunctionResult selectById(long l);

    int save(FunctionEntity entity);
}
