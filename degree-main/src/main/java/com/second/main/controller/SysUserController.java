package com.second.main.controller;

import com.second.common.bean.reponse.Result;
import com.second.main.domains.IdParam;
import com.second.main.domains.SysUserSaveParam;
import com.second.main.entity.SysUserEntity;
import com.second.main.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author chouchou
 * @since 2023-12-05
 */
@Tag(name = "用户")
@RestController
@RequestMapping("/sysUserEntity")
public class SysUserController {

    private final SysUserService userService;

    public SysUserController(SysUserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "用户列表")
    @PostMapping("/list")
    public Result<List<SysUserEntity>> list() throws SQLException {
        return Result.success(userService.list());
    }

    @Operation(summary = "根据id查找用户")
    @PostMapping("/detail")
    public Result<SysUserEntity> detail(@RequestBody @Validated IdParam param) {
        return Result.success(userService.getById(param.getId()));
    }

    @Operation(summary = "增加修改用户信息")
    @PostMapping("/save")
    public Result save(@RequestBody @Validated SysUserSaveParam param) {
        return userService.save(param);
    }

    @Operation(summary = "根据id删除用户")
    @PostMapping("/delete")
    public Result delete(@RequestBody @Validated IdParam param) {
        if (userService.removeById(param.getId())) {
            return Result.success();
        } else {
            return Result.error("删除失败，请稍后重试");
        }
    }
}
