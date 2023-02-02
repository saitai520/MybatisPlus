/*
 *
 * Copyright (c) 2020-2022, Java知识图谱 (http://www.altitude.xin).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package xin.altitude.spring.mybatisplus.joinquery.more2more.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.common.util.TableUtils;
import xin.altitude.spring.mybatisplus.joinquery.more2more.domain.StuSubRelation;
import xin.altitude.spring.mybatisplus.joinquery.more2more.domain.Student;
import xin.altitude.spring.mybatisplus.joinquery.more2more.domain.Subject;
import xin.altitude.spring.mybatisplus.joinquery.more2more.entity.bo.SubjectBo;
import xin.altitude.spring.mybatisplus.joinquery.more2more.entity.vo.StudentVo;
import xin.altitude.spring.mybatisplus.joinquery.more2more.mapper.StuSubRelationMapper;
import xin.altitude.spring.mybatisplus.joinquery.more2more.mapper.StudentMapper;
import xin.altitude.spring.mybatisplus.joinquery.more2more.mapper.SubjectMapper;
import xin.altitude.spring.mybatisplus.joinquery.more2more.service.IStudentService;
import xin.altitude.spring.mybatisplus.joinquery.more2more.service.ISubjectService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StuSubRelationMapper stuSubRelationMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    
    @Autowired
    private ISubjectService subjectService;
    
    /**
     * 查询单个学生的成绩
     *
     * @param stuId
     * @return
     */
    @Override
    public StudentVo getStudent(Integer stuId) {
        // 通过主键查询学生信息
        StudentVo studentVo = EntityUtils.toObj(getById(stuId), StudentVo::new);
        if (studentVo != null) {
            LambdaQueryWrapper<StuSubRelation> wrapper = Wrappers.lambdaQuery(StuSubRelation.class).eq(StuSubRelation::getStuId, stuId);
            // 查询匹配关系
            List<StuSubRelation> stuSubRelations = stuSubRelationMapper.selectList(wrapper);
            
            Set<Integer> subIds = stuSubRelations.stream().map(StuSubRelation::getSubId).collect(toSet());
            
            if (subIds.size() > 0) {
                List<SubjectBo> subBoList = EntityUtils.toList(subjectService.listByIds(subIds), SubjectBo::new);
                // Table<Integer, Integer, Integer> table = TableUtils.createHashTable(stuSubRelations, StuSubRelation::getStuId, StuSubRelation::getSubId, StuSubRelation::getScore);
                Table<Integer, Integer, Integer> table = TableUtils.createHashTable(stuSubRelations);
                
                subBoList.forEach(e -> e.setScore(table.get(stuId, e.getSubId())));
                studentVo.setSubList(subBoList);
            }
        }
        
        return studentVo;
    }
    
    @Override
    public List<StudentVo> getStudentList() {
        // 通过主键查询学生信息
        List<StudentVo> studentVoList = EntityUtils.toList(list(), StudentVo::new);
        // 批量查询学生ID
        Set<Integer> stuIds = EntityUtils.toSet(studentVoList, Student::getStuId);
        LambdaQueryWrapper<StuSubRelation> wrapper = Wrappers.lambdaQuery(StuSubRelation.class).in(StuSubRelation::getStuId, stuIds);
        List<StuSubRelation> stuSubRelations = stuSubRelationMapper.selectList(wrapper);
        // 批量查询课程ID
        Set<Integer> subIds = EntityUtils.toSet(stuSubRelations, StuSubRelation::getSubId);
        // 非常重要
        Map<Integer, List<Integer>> map = stuSubRelations.stream().collect(groupingBy(StuSubRelation::getStuId, mapping(StuSubRelation::getSubId, toList())));
        if (stuIds.size() > 0 && subIds.size() > 0) {
            Table<Integer, Integer, Integer> table = TableUtils.createHashTable(stuSubRelations, StuSubRelation::getStuId, StuSubRelation::getSubId, StuSubRelation::getScore);
            LambdaQueryWrapper<Subject> queryWrapper = Wrappers.lambdaQuery(Subject.class).in(Subject::getSubId, subIds);
            List<SubjectBo> subjectBoList = EntityUtils.toList(subjectService.list(queryWrapper), SubjectBo::new);
            for (StudentVo studentVo : studentVoList) {
                // 获取课程列表
                List<SubjectBo> list = subjectBoList.stream().filter(e -> map.get(studentVo.getStuId()) != null && map.get(studentVo.getStuId()).contains(e.getSubId())).collect(Collectors.toList());
                // 填充分数
                // list.forEach(e -> BeanCopyUtils.copyProperties(table.get(studentVo.getStuId(), e.getSubId()), e));
                list.forEach(e -> e.setScore(table.get(studentVo.getStuId(), e.getSubId())));
                studentVo.setSubList(list);
            }
            
        }
        return studentVoList;
    }
    
    private HashBasedTable<Integer, Integer, Integer> getHashBasedTable(List<StuSubRelation> stuSubRelations) {
        HashBasedTable<Integer, Integer, Integer> table = HashBasedTable.create();
        stuSubRelations.forEach(e -> table.put(e.getStuId(), e.getSubId(), e.getScore()));
        return table;
    }
    
    @Override
    public IPage<StudentVo> getStudentPage(IPage<Student> page) {
        // 通过主键查询学生信息
        
        IPage<StudentVo> studentVoPage = EntityUtils.toPage(page(page), StudentVo::new);
        // 批量查询学生ID
        Set<Integer> stuIds = studentVoPage.getRecords().stream().map(Student::getStuId).collect(toSet());
        LambdaQueryWrapper<StuSubRelation> wrapper = Wrappers.lambdaQuery(StuSubRelation.class).in(StuSubRelation::getStuId, stuIds);
        // 通过学生ID查询课程分数
        List<StuSubRelation> stuSubRelations = stuSubRelationMapper.selectList(wrapper);
        // 批量查询课程ID
        Set<Integer> subIds = stuSubRelations.stream().map(StuSubRelation::getSubId).collect(toSet());
        if (stuIds.size() > 0 && subIds.size() > 0) {
            HashBasedTable<Integer, Integer, Integer> table = getHashBasedTable(stuSubRelations);
            // 学生ID查询课程ID组
            Map<Integer, List<Integer>> map = stuSubRelations.stream().collect(groupingBy(StuSubRelation::getStuId, mapping(StuSubRelation::getSubId, toList())));
            
            List<Subject> subList = subjectMapper.selectList(Wrappers.lambdaQuery(Subject.class).in(Subject::getSubId, subIds));
            List<SubjectBo> subBoList = EntityUtils.toList(subList, SubjectBo::new);
            for (StudentVo studentVo : studentVoPage.getRecords()) {
                // 获取课程列表
                List<SubjectBo> list = subBoList.stream().filter(e -> map.get(studentVo.getStuId()) != null && map.get(studentVo.getStuId()).contains(e.getSubId())).collect(Collectors.toList());
                // 填充分数
                list.forEach(e -> e.setScore(table.get(studentVo.getStuId(), e.getSubId())));
                studentVo.setSubList(list);
            }
            
        }
        return studentVoPage;
    }
}
