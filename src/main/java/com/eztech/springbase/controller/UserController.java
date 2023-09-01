package com.eztech.springbase.controller;

import com.eztech.springbase.annotation.Authorize;
import com.eztech.springbase.annotation.GuavaRateLimiter;
import com.eztech.springbase.dto.user.ListUserDto;
import com.eztech.springbase.dto.user.LoginDto;
import com.eztech.springbase.dto.user.SaveUserDto;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.mapper.UserMapper;
import com.eztech.springbase.service.UserService;
import com.eztech.springbase.utils.JwtUtils;
import com.eztech.springbase.validator.group.CreateGroup;
import com.eztech.springbase.validator.group.UpdateGroup;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.user.UserVo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户控制器
 *
 * @author chenqinru
 * @date 2023/07/22
 */
@RestController
@Api(tags = "用户")
@RequestMapping("/user")
@Validated
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户列表
     *
     * @param listUserDto 用户列表dto
     * @return {@link PageVo}<{@link UserVo}>
     */
    @GetMapping("/list")
    @ApiOperation("用户列表")
    @Authorize("admin:user:list")
    @GuavaRateLimiter
    public PageVo<UserVo> list(@Validated ListUserDto listUserDto) {
        return userService.list(listUserDto);
    }

    /**
     * 创建用户
     *
     * @param saveUserDto 用户dto
     */
    @PostMapping("/create")
    @ApiOperation("创建用户")
    @ApiOperationSupport(ignoreParameters = {"id"})
    @Authorize("admin:user:create")
    @GuavaRateLimiter
    public void create(@Validated({CreateGroup.class}) @RequestBody SaveUserDto saveUserDto) {
        userService.save(UserMapper.INSTANCE.saveUserDtoToUser(saveUserDto));
    }

    /**
     * 用户详情
     *
     * @param id id
     * @return {@link UserVo}
     * @throws CustomException 自定义异常
     */
    @GetMapping("/{id}")
    @ApiOperation("用户详情")
    @Authorize("admin:user:read")
    @GuavaRateLimiter
    public UserVo read(@PathVariable Integer id) throws CustomException {
        return UserMapper.INSTANCE.userToVo(userService.findById(id));
    }

    /**
     * 更新用户
     *
     * @param saveUserDto 用户dto
     */
    @PutMapping("/update")
    @ApiOperation("更新用户")
    @Authorize("admin:user:update")
    @GuavaRateLimiter
    public void update(@Validated({UpdateGroup.class}) @RequestBody SaveUserDto saveUserDto) {
        userService.updateAllById(UserMapper.INSTANCE.saveUserDtoToUser(saveUserDto));
    }

    /**
     * 单个或批量删除
     *
     * @param ids id列表
     */
    @DeleteMapping("/delete")
    @ApiOperation("单个或批量删除用户")
    @Authorize("admin:user:delete")
    @GuavaRateLimiter
    public void delete(@RequestBody List<Integer> ids) {
        userService.deleteAllById(ids);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public String login(@Validated @RequestBody LoginDto loginDto) {
        return JwtUtils.create(userService.login(loginDto));
    }
}
