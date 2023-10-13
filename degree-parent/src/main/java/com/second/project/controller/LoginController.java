package com.second.project.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson2.JSON;
import com.second.common.bean.reponse.ResponseBean;
import com.second.project.domains.dto.CommonLoginDTO;
import com.second.project.entity.UserToken;
import com.second.project.mapper.UserTokenMapper;
import com.second.project.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * {@code @author}  JSY
 * {@code @date} 2023/7/14
 * {@code @description} 登录
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Resource
    private UserTokenMapper loginMapper;

    @GetMapping("/selectList")
    public String selectList() {
        List<UserToken> list = loginMapper.selectList(null);
        return JSON.toJSONString(list);
    }

    @PostMapping("/commonLogin")
    public Object loginCommon(@RequestBody  CommonLoginDTO dto) {
        ResponseBean responseBean = new ResponseBean();

        try {
            responseBean.setData(loginService.loginCommon(dto));
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setCode(500);
            responseBean.setMessage(e.getMessage());
        }

        return responseBean;
    }

    @PostMapping("/getLoginQr")
    public void createCodeImg(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");

        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        try {
            //这里没啥操作 就是生成一个UUID插入 数据库的表里
            String uuid = loginService.createQrImg();
            response.setHeader("uuid", uuid);
            // 这里是开源工具类 hutool里的QrCodeUtil
            // 网址：http://hutool.mydoc.io/

            log.info("uuid:{}", uuid);
            QrCodeUtil.generate(uuid, 300, 300, "jpg", response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 确认身份接口：确定身份以及判断是否二维码过期等
     *
     * @param token
     * @param userId
     * @return
     */
    @GetMapping("/bindUserIdAndToken")
    @ResponseBody
    public Object bindUserIdAndToken(@RequestParam("token") String token,
                                     @RequestParam("userId") Integer userId,
                                     @RequestParam(required = false, value = "projId") Integer projId) {
        ResponseBean responseBean = new ResponseBean();
        try {
            responseBean.setData(loginService.bindUserIdAndToken(userId, token, projId));
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setCode(500);
            responseBean.setMessage(e.getMessage());
        }

        return responseBean;
    }
}