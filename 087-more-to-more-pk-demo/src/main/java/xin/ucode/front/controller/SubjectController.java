package xin.ucode.front.controller;
import xin.ucode.front.service.ISubjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import xin.ucode.front.domain.Subject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import xin.ucode.front.mapper.SubjectMapper;
import xin.altitude.cms.common.entity.AjaxResult;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import xin.altitude.cms.common.entity.PageEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// @RestController
// @RequestMapping("/front/subject")
public class SubjectController{
    @Autowired
    private ISubjectService subjectService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,Subject subject){
        return AjaxResult.success(subjectService.page(pageEntity.toPage(), Wrappers.lambdaQuery(subject)));
    }
    @GetMapping("/list")
    public AjaxResult list(Subject subject){
        return AjaxResult.success(subjectService.list(Wrappers.lambdaQuery(subject)));
    }
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Subject subject) {
        return AjaxResult.success(subjectService.save(subject));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody Subject subject) {
        return AjaxResult.success(subjectService.updateById(subject));
    }
    @DeleteMapping("/delete/{subIds}")
    public AjaxResult delete(@PathVariable Integer[] subIds) {
        return AjaxResult.success(subjectService.removeByIds(Arrays.asList(subIds)));
    }
    @GetMapping(value = "/detail/{subId}")
    public AjaxResult detail(@PathVariable("subId") Integer subId) {
        return AjaxResult.success(subjectService.getById(subId));
    }
}
