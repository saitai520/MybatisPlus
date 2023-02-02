package xin.ucode.front.entoty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xin.ucode.front.domain.Student;

import java.util.List;

/**
 * @author <a href="http://www.altitude.xin" target="_blank">Java知识图谱</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentVo extends Student {
    private List<SubjectBo> subjectBos;
    
    
    /**
     * 实体类转换：Student转换成StudentVo
     */
    public StudentVo(Student student) {
        super(student);
    }
}
