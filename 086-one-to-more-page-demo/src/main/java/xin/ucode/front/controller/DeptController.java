package xin.ucode.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.ucode.front.service.IDeptService;

@RestController
@RequestMapping("/front/dept")
public class DeptController {
    @Autowired
    private IDeptService deptService;
    
    @GetMapping("/page1")
    public AjaxResult page1() {
        return AjaxResult.success(deptService.selectDeptPage1());
    }
    
    
    @GetMapping("/page2")
    public AjaxResult page2() {
        return AjaxResult.success(deptService.selectDeptPage2());
    }
    
    
    @GetMapping("/page3")
    public AjaxResult page3() {
        return AjaxResult.success(deptService.selectDeptPage3());
    }
    
    
    @GetMapping("/page4")
    public AjaxResult page4() {
        return AjaxResult.success(deptService.selectDeptPage4());
    }
    
}
