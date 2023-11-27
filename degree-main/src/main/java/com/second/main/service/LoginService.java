package com.second.main.service;

import com.second.main.domains.CommonLoginDTO;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/7/14
 * {@code @description} login service
 */
public interface LoginService {

    /**
     * 账号登录
     * {@code @author} chouchou
     * {@code @date} 2023/7/15
     */
    String loginCommon(CommonLoginDTO dto) throws Exception;

    /**
     * 校验二维码信息
     * {@code @author} chouchou
     * {@code @date} 2023/7/15
     */
    String bindUserIdAndToken(Integer userId, String token, Integer projId) throws Exception;

    /**
     * 新建 user token 信息
     * {@code @author} chouchou
     * {@code @date} 2023/7/15
     */
    String createQrImg();
}
