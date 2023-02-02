package xin.ucode.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Table;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.common.util.TableUtils;
import xin.ucode.front.domain.StuSubRelation;
import xin.ucode.front.domain.Student;
import xin.ucode.front.domain.Subject;
import xin.ucode.front.entoty.StudentVo;
import xin.ucode.front.entoty.SubjectBo;
import xin.ucode.front.mapper.StuSubRelationMapper;
import xin.ucode.front.mapper.StudentMapper;
import xin.ucode.front.mapper.SubjectMapper;
import xin.ucode.front.service.IStudentService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Resource
    private StuSubRelationMapper stuSubRelationMapper;
    
    @Resource
    private SubjectMapper subjectMapper;
    
    /**
     * 演示一
     *
     * @param stuId
     * @return
     */
    @Override
    public StudentVo getStudent1(Integer stuId) {
        Student student = this.getById(stuId);
        if (student != null) {
            StudentVo studentVo = EntityUtils.toObj(student, StudentVo::new);
            return studentVo;
        }
        return null;
    }
    
    /**
     * 演示二
     *
     * @param stuId
     * @return
     */
    @Override
    public StudentVo getStudent2(Integer stuId) {
        Student student = this.getById(stuId);
        if (student != null) {
            StudentVo studentVo = EntityUtils.toObj(student, StudentVo::new);
            LambdaQueryWrapper<StuSubRelation> wrapper = Wrappers.lambdaQuery(StuSubRelation.class)
                    .eq(StuSubRelation::getStuId, student.getStuId());
            List<StuSubRelation> relationList = stuSubRelationMapper.selectList(wrapper);
            // 取出课程ID集合（去重）
            Set<Integer> subIds = EntityUtils.toSet(relationList, StuSubRelation::getSubId);
            // 构造table实例Map的Map结构
            Table<Integer, Integer, Integer> table = TableUtils.createHashTable(relationList);
            if (!subIds.isEmpty()) {
                List<Subject> subjectList = subjectMapper.selectBatchIds(subIds);
                List<SubjectBo> subjectBoList = EntityUtils.toList(subjectList, SubjectBo::new);
                studentVo.setSubjectBos(subjectBoList);
                return studentVo;
            }
        }
        return null;
    }
    
    
    /**
     * 演示三
     *
     * @param stuId
     * @return
     */
    @Override
    public StudentVo getStudent3(Integer stuId) {
        Student student = this.getById(stuId);
        if (student != null) {
            StudentVo studentVo = EntityUtils.toObj(student, StudentVo::new);
            LambdaQueryWrapper<StuSubRelation> wrapper = Wrappers.lambdaQuery(StuSubRelation.class)
                    .eq(StuSubRelation::getStuId, student.getStuId());
            List<StuSubRelation> relationList = stuSubRelationMapper.selectList(wrapper);
            // 取出课程ID集合（去重）
            Set<Integer> subIds = EntityUtils.toSet(relationList, StuSubRelation::getSubId);
            // 构造table实例Map的Map结构
            Table<Integer, Integer, Integer> table = TableUtils.createHashTable(relationList);
            if (!subIds.isEmpty()) {
                List<Subject> subjectList = subjectMapper.selectBatchIds(subIds);
                List<SubjectBo> subjectBoList = EntityUtils.toList(subjectList, SubjectBo::new);
                
                // 完成score赋值
                for (SubjectBo subjectBo : subjectBoList) {
                    subjectBo.setScore(table.get(student.getStuId(), subjectBo.getSubId()));
                }
                
                studentVo.setSubjectBos(subjectBoList);
                return studentVo;
            }
        }
        return null;
    }
}
