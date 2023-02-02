package xin.ucode.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.ucode.front.domain.User;
import xin.ucode.front.mapper.UserMapper;
import xin.ucode.front.service.IUserService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/front/user")
public class UserController {
    @Autowired
    private IUserService userService;
    
    @Resource
    private UserMapper userMapper;
    
    /**
     * 演示一：Service无缓存（二选一）
     */
    @GetMapping(value = "/detail1/{userId}")
    public AjaxResult detail1(@PathVariable("userId") Long userId) {
        return AjaxResult.success(userService.getById(userId));
    }
    
    /**
     * 演示二：Service有缓存（二选一）
     */
    @GetMapping(value = "/detail2/{userId}")
    public AjaxResult detail2(@PathVariable("userId") Long userId) {
        return AjaxResult.success(userService.getById(userId, User.class));
    }
    
    /**
     * 演示三：Mapper无缓存（二选一）
     */
    @GetMapping(value = "/detail3/{userId}")
    public AjaxResult detail3(@PathVariable("userId") Long userId) {
        return AjaxResult.success(userMapper.selectById(userId));
    }
    
    /**
     * 演示四：Mapper有缓存（二选一）
     */
    @GetMapping(value = "/detail4/{userId}")
    public AjaxResult detail4(@PathVariable("userId") Long userId) {
        return AjaxResult.success(userMapper.selectById(userId,User.class));
    }
    
    /**
     * 演示五：Service 批量无缓存（二选一）
     */
    @GetMapping(value = "/detail5/{userId}")
    public AjaxResult detail5(@PathVariable("userId") Long[] userId) {
        List<Long> userIds = Arrays.asList(userId);
        return AjaxResult.success(userService.listByIds(userIds));
    }
    
    /**
     * 演示六：Service 批量有缓存（二选一）
     */
    @GetMapping(value = "/detail6/{userId}")
    public AjaxResult detail6(@PathVariable("userId") Long[] userId) {
        List<Long> userIds = Arrays.asList(userId);
        return AjaxResult.success(userService.listByIds(userIds,User.class));
    }
    
    /**
     * 演示七：Service 批量有缓存 过期时间（二选一）
     */
    @GetMapping(value = "/detail7/{userId}")
    public AjaxResult detail7(@PathVariable("userId") Long[] userId) {
        List<Long> userIds = Arrays.asList(userId);
        return AjaxResult.success(userService.listByIds(userIds,10000L));
    }
}
