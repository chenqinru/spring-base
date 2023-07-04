package com.eztech.springbase.controller;

import com.eztech.springbase.dto.user.ListUserDto;
import com.eztech.springbase.dto.user.SaveUserDto;
import com.eztech.springbase.enums.ResultEnum;
import com.eztech.springbase.exception.CustomException;
import com.eztech.springbase.service.IUserService;
import com.eztech.springbase.utils.MethodUtil;
import com.eztech.springbase.validation.CreateGroup;
import com.eztech.springbase.validation.UpdateGroup;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.user.UserVo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author CQR
 */
@RestController
@Api(tags = "用户")
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 用户列表
     *
     * @param listUserDto 用户列表dto
     * @return {@link PageVo}<{@link UserVo}>
     */
    @GetMapping("/list")
    @ApiOperation("用户列表")
    public PageVo<UserVo> list(@Validated ListUserDto listUserDto) {
        //throw new CustomException(ResultEnum.GET_ERROR, MethodUtil.getLineInfo());
        return userService.list(listUserDto);
    }


    /**
     * 创建用户
     *
     * @param userDto 用户dto
     * @return {@link Boolean}
     */
    @PostMapping("/create")
    @ApiOperation("创建用户")
    @ApiOperationSupport(ignoreParameters = {"id"})
    public Boolean create(@Validated({CreateGroup.class}) @RequestBody SaveUserDto userDto) {
        return userService.save(userDto.buildEntity());
    }


    /**
     * 用户详情
     *
     * @param id id
     * @return {@link UserVo}
     */
    @GetMapping("/{id}")
    @ApiOperation("用户详情")
    public UserVo read(@PathVariable Integer id) throws CustomException {
        return Optional.ofNullable(userService.getById(id)).orElseThrow(() -> new CustomException(ResultEnum.GET_ERROR, MethodUtil.getLineInfo())).buildVo(new UserVo());
    }


    /**
     * 更新用户
     *
     * @param userDto 用户dto
     * @return {@link Boolean}
     */
    @PutMapping("/update")
    @ApiOperation("更新用户")
    public Boolean update(@Validated({UpdateGroup.class}) @RequestBody SaveUserDto userDto) {
        return userService.updateById(userDto.buildEntity());
    }


    /**
     * 单个或批量删除
     *
     * @param ids id列表
     * @return {@link Boolean}
     */
    @DeleteMapping("/delete")
    @ApiOperation("单个/批量删除用户")
    public Boolean delete(@RequestBody List<Integer> ids) {
        return userService.removeByIds(ids);
    }
}
