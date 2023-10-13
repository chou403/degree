package com.second.common.service.impl;

import com.second.common.domain.OperationLogDTO;
import com.second.common.service.OperationLogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

//import jakarta.annotation.Resource;
import javax.sql.DataSource;

/**
 * {@code @author} JSY
 * {@code @date} 2022/12/2
 * {@code @className} OperationLogServiceImpl
 * {@code @description} 操作记录
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Resource
    DataSource ds;

    /**
     * 记录保存
     *
     * @param dto
     */
    @Override
    public void saveLog(OperationLogDTO dto) {
//        TtBtnOperationLogPO po = new TtBtnOperationLogPO();
//        po.set("dealer_code", dto.getDealerCode());
//        po.set("title", dto.getTitle());
//        po.set("method", dto.getMethod());
//        po.set("business_type", dto.getBusinessType());
//        po.set("request_method", dto.getRequestMethod());
//        po.set("request_url", dto.getRequestUrl());
//        po.set("request_params", dto.getRequestParams());
//        po.set("special_param", dto.getSpecialParam());
//        po.set("operator_name", dto.getOperatorName());
//        po.set("operator_code", dto.getOperatorCode());
//        po.set("operate_date", new Date(System.currentTimeMillis()));
//        po.set("created_by", dto.getOperatorId());
//        po.set("updated_by", dto.getOperatorId());
//        po.saveIt();
    }
}
