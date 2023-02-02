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
    
    @GetMapping("/list1")
    public AjaxResult list1() {
        return AjaxResult.success(userService.selectUserList1());
    }
    
    @GetMapping("/list2")
    public AjaxResult list2() {
        return AjaxResult.success(userService.selectUserList2());
    }
    
    @GetMapping("/list3")
    public AjaxResult list3() {
        return AjaxResult.success(userService.selectUserList3());
    }
    
    @GetMapping("/list4")
    public AjaxResult list4() {
        return AjaxResult.success(userService.selectUserList4());
    }
    
    @GetMapping("/list5")
    public AjaxResult list5() {
        return AjaxResult.success(userService.selectUserList4());
    }
    
}
