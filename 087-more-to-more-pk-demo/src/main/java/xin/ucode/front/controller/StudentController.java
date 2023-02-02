package xin.ucode.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.ucode.front.service.IStudentService;

@RestController
@RequestMapping("/front/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    
    @GetMapping(value = "/detail1/{stuId}")
    public AjaxResult detail1(@PathVariable("stuId") Integer stuId) {
        return AjaxResult.success(studentService.getStudent1(stuId));
    }
    
    
    @GetMapping(value = "/detail2/{stuId}")
    public AjaxResult detail2(@PathVariable("stuId") Integer stuId) {
        return AjaxResult.success(studentService.getStudent2(stuId));
    }
    
    @GetMapping(value = "/detail3/{stuId}")
    public AjaxResult detail3(@PathVariable("stuId") Integer stuId) {
        return AjaxResult.success(studentService.getStudent3(stuId));
    }
}
