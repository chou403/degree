package com.second.main.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.second.common.bean.reponse.Result;
import com.second.common.utils.JsonHelper;
import com.second.main.entity.UserToken;
import com.second.main.mapper.UserTokenMapper;
import com.second.main.domains.CommonLoginDTO;
import com.second.main.service.LoginService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@code @author}  chouchou
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
        return JsonHelper.parseToJson(list);
    }

    @PostMapping("/commonLogin")
    public Object loginCommon(@RequestBody CommonLoginDTO dto) {
        Result result = Result.success();

        try {
            result.setData(loginService.loginCommon(dto));
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg(e.getMessage());
        }

        return result;
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
        Result result = Result.success();
        try {
            result.setData(loginService.bindUserIdAndToken(userId, token, projId));
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg(e.getMessage());
        }

        return result;
    }

}