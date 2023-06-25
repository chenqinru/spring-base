package com.eztech.springbase.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eztech.springbase.dto.user.ListUserDto;
import com.eztech.springbase.dto.user.SaveUserDto;
import com.eztech.springbase.validation.CreateGroup;
import com.eztech.springbase.validation.UpdateGroup;
import com.eztech.springbase.entity.User;
import com.eztech.springbase.service.IUserService;
import com.eztech.springbase.utils.ResultVoUtil;
import com.eztech.springbase.vo.PageVo;
import com.eztech.springbase.vo.ResultVo;
import com.eztech.springbase.vo.user.UserVo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CQR
 */
@RestController
@Api(tags = "用户")
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    @ApiOperation("用户列表page")
    public PageVo<UserVo> page(ListUserDto listUserDto) {
        //封装查询对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(listUserDto.buildEntity());
//        queryWrapper.orderBy();
        //分页查询
        Page<User> page = userService.page(new Page<>(listUserDto.getCurrent(), listUserDto.getSize()), queryWrapper);
        List<UserVo> list = page.getRecords().stream().map(user -> user.buildVo(new UserVo())).collect(Collectors.toList());
        //封装返回体
        PageVo<UserVo> pageVo = new PageVo<>();
        BeanUtils.copyProperties(page, pageVo);
        pageVo.setRecords(list);

        return pageVo;

    }

    /**
     * 用户列表
     */
    @GetMapping("/list")
    @ApiOperation("用户列表")
    public List<UserVo> list() {
        return userService.list().stream().map(user -> user.buildVo(new UserVo())).collect(Collectors.toList());
    }

    /**
     * 新增
     *
     * @param userDto 数据
     * @return 成功
     */
    @PostMapping("/create")
    @ApiOperation("添加用户")
    @ApiOperationSupport(ignoreParameters = {"id"})
    public Boolean create(@Validated({CreateGroup.class}) @RequestBody SaveUserDto userDto) {
        return userService.save(userDto.buildEntity());
    }


    /**
     * 更新
     *
     * @param userDto 数据
     * @return 成功
     */
    @PutMapping("/update")
    @ApiOperation("更新用户")
    public Boolean update(@Validated({UpdateGroup.class}) @RequestBody SaveUserDto userDto) {
        return userService.updateById(userDto.buildEntity());
    }

    /**
     * 用户详情
     *
     * @param id 主键
     * @return 用户详情
     */
    @GetMapping("/{id}")
    @ApiOperation("用户详情")
    public UserVo read(@PathVariable Integer id) {
        return userService.getById(id).buildVo(new UserVo());
    }


    /**
     * 单个/批量删除
     *
     * @param ids 主键列表
     * @return 成功或者失败
     */
    @DeleteMapping("/delete")
    @ApiOperation("单个/批量删除用户")
    public Boolean delete(@RequestBody List<Integer> ids) {
        return userService.removeByIds(ids);
    }


//    /**
//     * 添加用户
//     *
//     * @param userDto 表单数据
//     * @return 成功或者失败
//     */
//    @ApiOperation("添加用户")
//    @PostMapping("/addUser")
//    public ResultVo addUser(@Validated @RequestBody SaveUserDto userDto) {
//        if (userService.addUser(userDto)) {
//            return ResultVoUtil.ok();
//        } else {
//            return ResultVoUtil.fail(ResultEnum.ADD_ERROR);
//        }
//    }
//

    /**
     * 获取用户列表
     *
     * @param listUserDto 表单数据
     * @return 用户列表
     */
    @ApiOperation("获取用户列表")
    @GetMapping("/listUser")
    @ApiResponses(
            @ApiResponse(code = 200, message = "ok", response = UserVo.class)
    )
    public ResultVo listUser(@Validated ListUserDto listUserDto) {
        return ResultVoUtil.ok(userService.listUser(listUserDto));
    }
//
//    /**
//     * 删除用户
//     *
//     * @param id 用户编号
//     * @return 成功或者失败
//     */
//    @ApiOperation("删除用户")
//    @DeleteMapping("/deleteUser/{id}")
//    public ResultVo deleteUser(@PathVariable("id") String id) {
//        userService.deleteUser(id);
//        return ResultVoUtil.ok();
//    }
}
