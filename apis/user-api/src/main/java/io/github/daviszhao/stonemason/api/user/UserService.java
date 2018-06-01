package io.github.daviszhao.stonemason.api.user;

import io.github.daviszhao.stonemason.api.base.PageData;
import io.github.daviszhao.stonemason.models.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@FeignClient("user-service")
@Api
public interface UserService {

    @ApiOperation("获取所有用户")
    @RequestMapping(value = "/allUsers", method = GET)
    List<User> queryAllUsers(@ApiParam(value = "用户名关键字") @RequestParam(value = "keyword", required = false) String keyword,
                             @ApiParam(value = "锁定状态") @RequestParam(value = "locked", required = false) Boolean locked);

    @ApiOperation("分页查询所有用户")
    @RequestMapping(value = "/users", method = GET)
    PageData<User> queryUsers(@ApiParam(value = "用户名关键字") @RequestParam(value = "keyword", required = false) String keyword,
                              @ApiParam(value = "锁定状态") @RequestParam(value = "locked", required = false) Boolean locked,
                              @ApiParam(value = "每页数据量,默认10", defaultValue = "10") @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                              @ApiParam(value = "页码（从1开始）", defaultValue = "1") @RequestParam(value = "page", defaultValue = "1") int page
    );

    @ApiOperation("切换锁定状态")
    @RequestMapping(value = "/switchLock", method = POST)
    void switchLock(@ApiParam(value = "用户名", required = true) @RequestParam("userName") String userName,
                    @ApiParam(value = "目标状态", required = true) @RequestParam("toLock") boolean toLock);

    @ApiOperation("用户注册")
    @RequestMapping(value = "/create", method = POST)
    public void create(@ApiParam(value = "用户名", required = true) @RequestParam("userName") String userName,
                       @ApiParam(value = "密码明文", required = true) @RequestParam("password") String password);

    @ApiOperation("修改密码")
    @RequestMapping(value = "/changePassword", method = {PUT, PATCH})
    public void changePassword(@ApiParam(value = "用户名", required = true) @RequestParam("userName") String userName,
                               @ApiParam(value = "密码明文", required = true) @RequestParam("password") String password);

    @ApiOperation("按用户名获取用户")
    @RequestMapping(value = "/userByName", method = {GET})
    User getUser(@ApiParam(value = "用户名", required = true) @RequestParam("userName") String userName);

    @ApiOperation("按用户ID获取用户")
    @RequestMapping(value = "/userByID", method = {GET})
    User getUser(@ApiParam(value = "用户ID", required = true) @RequestParam("userName") int userID);

    @ApiOperation("按用户名删除用户")
    @RequestMapping(value = "/userByName", method = {DELETE})
    void deleteUser(@ApiParam(value = "用户名", required = true) String userName);

    @ApiOperation("按用户ID删除用户")
    @RequestMapping(value = "/userByID", method = {DELETE})
    void deleteUser(@ApiParam(value = "用户ID", required = true) int userID);

    @ApiOperation("测试事件")
    @RequestMapping(value = "/testEvent", method = GET)
    void testEvent();
}
