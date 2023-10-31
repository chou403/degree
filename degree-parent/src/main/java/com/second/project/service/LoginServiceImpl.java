package com.second.project.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.second.common.util.CommonUtil;
import com.second.common.util.DateUtils;
import com.second.common.util.StringUtils;
import com.second.project.config.WebSocketServer;
import com.second.project.domains.dto.CommonLoginDTO;
import com.second.project.entity.User;
import com.second.project.entity.UserToken;
import com.second.project.mapper.UserMapper;
import com.second.project.mapper.UserTokenMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/7/14
 * {@code @description} login service impl
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService{

    @Resource
    private UserTokenMapper userTokenMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 账号登录
     * {@code @author} chouchou
     * {@code @date} 2023/7/15
     *
     * @param dto
     */
    @Override
    public String loginCommon(CommonLoginDTO dto) throws Exception{
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .eq("user_code", dto.getUserCode())
                .eq("password",dto.getPassword());

        User user = userMapper.selectOne(userQueryWrapper);

        if (StringUtils.isNullOrEmpty(user)) {
            throw new Exception("账号不存在，请重新输入");
        }

        return null;
    }

    @Override
    public String bindUserIdAndToken(Integer userId, String token,Integer projId) throws Exception {

        QueryWrapper<UserToken> userTokenQueryWrapper = new QueryWrapper();
        userTokenQueryWrapper.eq("uuid", token);
//        userTokenQueryWrapper.eq("user_id", userId);

        UserToken userToken = new UserToken();
        userToken.setUuid(token);
        userToken = userTokenMapper.selectOne(userTokenQueryWrapper);

        if(null == userToken){
            throw new Exception("错误的请求！");
        }
        Date createDate = DateUtils.getDateAfterTime(userToken.getCreateTime(), 1, Calendar.MINUTE);
        log.info("获取一分钟后的时间：{}", createDate);
        Date nowDate = new Date();
        if(nowDate.getTime() > createDate.getTime()){//当前时间大于校验时间

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",500);
            jsonObject.put("msg","二维码失效！");
            WebSocketServer.sendInfo(jsonObject.toJSONString(),token);

            throw  new Exception("二维码失效！");
        }

        userToken.setLoginTime(new Date());
        userToken.setUserId(userId);

        int i = userTokenMapper.update(userToken, userTokenQueryWrapper);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("msg","ok");
        jsonObject.put("userId",userId);
        if(StringUtils.isNotNullOrEmpty(projId)){
            jsonObject.put("projId",projId);
        }
        WebSocketServer.sendInfo(jsonObject.toJSONString(),token);

        if(i > 0 ){
            return null;
        }else{
            throw  new Exception("服务器异常！");
        }
    }

    @Override
    public String createQrImg() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        id = id.replace("-", "");//替换掉中间的那个横杠

        UserToken userToken = new UserToken();
        userToken.setUuid(id);
        userToken.setState(1);
        userToken.setCreateTime(new Date());

        userTokenMapper.insert(userToken);
        return id;
    }
}
