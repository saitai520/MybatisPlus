package xin.ucode.front.controller;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.ucode.front.service.IUserService;

@RestController
@RequestMapping("/front/user")
public class UserController {
    RedissonClient redisson = Redisson.create();
    
    
    @Autowired
    private IUserService userService;
    
    /**
     * 无缓存
     */
    @GetMapping(value = "/detail1/{userId}")
    public AjaxResult detail1(@PathVariable("userId") Long userId) {
        return AjaxResult.success(userService.getById(userId));
    }
    
    /**
     * 无锁缓存
     */
    @GetMapping(value = "/detail2/{userId}")
    public AjaxResult detail2(@PathVariable("userId") Long userId) {
        return AjaxResult.success(userService.getById(userId, 10000L));
    }
    
    
    /**
     * 有锁缓存
     */
    @GetMapping(value = "/detail3/{userId}")
    public AjaxResult detail3(@PathVariable("userId") Long userId) {
        return AjaxResult.success(userService.getById(userId, 10000L, redisson));
    }
    
    
}
