package xin.ucode.front.service;
import com.baomidou.mybatisplus.extension.service.IService;
import xin.ucode.front.domain.Student;
import xin.ucode.front.entoty.StudentVo;

public interface IStudentService extends IService<Student>{
    StudentVo getStudent1(Integer stuId);
    
    StudentVo getStudent2(Integer stuId);
    
    /**
     * 演示三
     *
     * @param stuId
     * @return
     */
    StudentVo getStudent3(Integer stuId);
}
