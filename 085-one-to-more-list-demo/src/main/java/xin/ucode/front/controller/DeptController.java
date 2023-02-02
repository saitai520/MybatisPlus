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
    
    @GetMapping("/list1")
    public AjaxResult list1() {
        return AjaxResult.success(deptService.selectDeptList1(12L));
    }
    
    @GetMapping("/list2")
    public AjaxResult list2() {
        return AjaxResult.success(deptService.selectDeptList2(12L));
    }
    
    @GetMapping("/list3")
    public AjaxResult list3() {
        return AjaxResult.success(deptService.selectDeptList3());
    }
    
    @GetMapping("/list4")
    public AjaxResult list4() {
        return AjaxResult.success(deptService.selectDeptList4());
    }
    
    @GetMapping("/list5")
    public AjaxResult list5() {
        return AjaxResult.success(deptService.selectDeptList5());
    }
}
