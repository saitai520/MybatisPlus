package xin.ucode.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.ucode.front.service.IUserService;

@RestController
@RequestMapping("/front/user")
public class UserController {
    @Autowired
    private IUserService userService;
    
    @GetMapping("/page1")
    public AjaxResult page1() {
        return AjaxResult.success(userService.selectUserPage1());
    }
    
    @GetMapping("/page2")
    public AjaxResult page2() {
        return AjaxResult.success(userService.selectUserPage2());
    }
    
    @GetMapping("/page3")
    public AjaxResult page3() {
        return AjaxResult.success(userService.selectUserPage3());
    }
    
    @GetMapping("/page4")
    public AjaxResult page4() {
        return AjaxResult.success(userService.selectUserPage4());
    }
    
    
    @GetMapping("/page5")
    public AjaxResult page5() {
        return AjaxResult.success(userService.selectUserPage5());
    }
    
}
