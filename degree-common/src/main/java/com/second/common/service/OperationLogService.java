package com.second.common.service;

import com.second.common.domain.OperationLogDTO;

/**
 * {@code @author} JSY
 * {@code @date} 2022/12/2
 * {@code @className} OperationLogService
 * {@code @description} 操作记录
 */
public interface OperationLogService {

    /**
     * 记录保存
     */
    void saveLog(OperationLogDTO dto);
}
