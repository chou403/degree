package com.second.main.controller;

import com.second.common.bean.reponse.Result;
import com.second.main.domains.dto.IdParamDTO;
import com.second.main.domains.dto.UserSaveParamDTO;
import com.second.main.entity.UserEntity;
import com.second.main.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * {@code @author} chou401
 * @since 2023-12-05
 */
@Slf4j
@Tag(name = "用户")
@RestController
@RequestMapping("/userEntity")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "用户列表")
    @PostMapping("/list")
    public Result<List<UserEntity>> list() throws SQLException {
        return Result.success(userService.list());
    }

    @Operation(summary = "根据id查找用户")
    @PostMapping("/detail")
    public Result<UserEntity> detail(@RequestBody @Validated IdParamDTO param) {
        return Result.success(userService.getById(param.getId()));
    }

    @Operation(summary = "增加修改用户信息")
    @PostMapping("/save")
    public Result<Object> save(@RequestBody @Validated UserSaveParamDTO param) {
        return userService.save(param);
    }

    @Operation(summary = "根据id删除用户")
    @PostMapping("/delete")
    public Result<Object> delete(@RequestBody @Validated IdParamDTO param) {
        if (userService.removeById(param.getId())) {
            return Result.success();
        } else {
            return Result.error("删除失败，请稍后重试");
        }
    }
}
