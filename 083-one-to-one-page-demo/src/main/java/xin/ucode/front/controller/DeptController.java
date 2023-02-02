package xin.ucode.front.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;
import xin.ucode.front.domain.Dept;
import xin.ucode.front.service.IDeptService;

import java.util.Arrays;

// @RestController
// @RequestMapping("/front/dept")
public class DeptController {
    @Autowired
    private IDeptService deptService;
    
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity, Dept dept) {
        return AjaxResult.success(deptService.page(pageEntity.toPage(), Wrappers.lambdaQuery(dept)));
    }
    
    @GetMapping("/list")
    public AjaxResult list(Dept dept) {
        return AjaxResult.success(deptService.list(Wrappers.lambdaQuery(dept)));
    }
    
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Dept dept) {
        return AjaxResult.success(deptService.save(dept));
    }
    
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Dept dept) {
        return AjaxResult.success(deptService.updateById(dept));
    }
    
    @DeleteMapping("/delete/{deptIds}")
    public AjaxResult delete(@PathVariable Long[] deptIds) {
        return AjaxResult.success(deptService.removeByIds(Arrays.asList(deptIds)));
    }
    
    @GetMapping(value = "/detail/{deptId}")
    public AjaxResult detail(@PathVariable("deptId") Long deptId) {
        return AjaxResult.success(deptService.getById(deptId));
    }
}
