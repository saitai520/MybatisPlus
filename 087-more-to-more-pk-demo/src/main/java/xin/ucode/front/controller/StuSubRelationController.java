package xin.ucode.front.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import xin.altitude.cms.common.entity.AjaxResult;
import java.util.List;
import xin.ucode.front.mapper.StuSubRelationMapper;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import xin.altitude.cms.common.entity.PageEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import xin.ucode.front.service.IStuSubRelationService;
import org.springframework.web.bind.annotation.GetMapping;
import xin.ucode.front.domain.StuSubRelation;
// @RestController
// @RequestMapping("/front/stu/sub/relation")
public class StuSubRelationController{
    @Autowired
    private IStuSubRelationService stuSubRelationService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,StuSubRelation stuSubRelation){
        return AjaxResult.success(stuSubRelationService.page(pageEntity.toPage(), Wrappers.lambdaQuery(stuSubRelation)));
    }
    @GetMapping("/list")
    public AjaxResult list(StuSubRelation stuSubRelation){
        return AjaxResult.success(stuSubRelationService.list(Wrappers.lambdaQuery(stuSubRelation)));
    }
    @PostMapping("/add")
    public AjaxResult add(@RequestBody StuSubRelation stuSubRelation) {
        return AjaxResult.success(stuSubRelationService.save(stuSubRelation));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody StuSubRelation stuSubRelation) {
        return AjaxResult.success(stuSubRelationService.updateById(stuSubRelation));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(stuSubRelationService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(stuSubRelationService.getById(id));
    }
}
