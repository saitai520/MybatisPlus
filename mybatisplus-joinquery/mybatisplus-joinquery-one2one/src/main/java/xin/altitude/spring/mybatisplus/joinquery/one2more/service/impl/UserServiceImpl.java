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

package xin.altitude.spring.mybatisplus.joinquery.one2more.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.spring.mybatisplus.joinquery.one2more.domain.Dept;
import xin.altitude.spring.mybatisplus.joinquery.one2more.domain.User;
import xin.altitude.spring.mybatisplus.joinquery.one2more.entity.vo.UserVo;
import xin.altitude.spring.mybatisplus.joinquery.one2more.mapper.UserMapper;
import xin.altitude.spring.mybatisplus.joinquery.one2more.service.IUserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

/**
 * 一个用户对应一个部门
 *
 * @author explore
 * @since 2021/05/24 11:09
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    
    @Autowired
    private DeptServiceImpl deptService;
    
    /**
     * 查询单个学生信息（一个学生对应一个部门）
     */
    @Override
    public UserVo getOneUser(Integer userId) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class)
                .eq(User::getUserId, userId);
        UserVo userVo = EntityUtils.toObj(getOne(wrapper), UserVo::new);
        
        // 从其它表查询信息再封装到Vo
        Optional.ofNullable(userVo).ifPresent(this::addDetpNameInfo);
        return userVo;
    }
    
    
    /**
     * 补充部门名称信息
     */
    private void addDetpNameInfo(UserVo userVo) {
        LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class).eq(Dept::getDeptId, userVo.getDeptId());
        Dept dept = deptService.getOne(wrapper);
        Optional.ofNullable(dept).ifPresent(e -> userVo.setDeptName(e.getDeptName()));
    }
    
    /**
     * 批量查询学生信息（一个学生对应一个部门）
     */
    @Override
    public List<UserVo> getUserByList() {
        // 先查询用户信息（表现形式为列表）
        List<User> user = list(Wrappers.emptyWrapper());
        List<UserVo> userVos = user.stream().map(UserVo::new).collect(toList());
        // 此步骤可以有多个
        addDeptNameInfo2(userVos);
        return userVos;
    }
    
    
    private void addDeptNameInfo(List<UserVo> userVos) {
        // 提取用户userId，方便批量查询
        Set<Integer> deptIds = userVos.stream().map(User::getDeptId).collect(toSet());
        // 根据deptId查询deptName（查询前，先做非空判断）
        List<Dept> dept = deptService.list(Wrappers.lambdaQuery(Dept.class).in(Dept::getDeptId, deptIds));
        // 构造映射关系，方便匹配deptId与deptName
        Map<Integer, String> hashMap = dept.stream().collect(toMap(Dept::getDeptId, Dept::getDeptName));
        // 封装Vo，并添加到集合中(关键内容)
        userVos.forEach(e -> e.setDeptName(hashMap.get(e.getDeptId())));
    }
    
    private void addDeptNameInfo2(List<UserVo> userVos) {
        // 提取用户userId，方便批量查询
        Set<Integer> deptIds = userVos.stream().map(User::getDeptId).collect(toSet());
        EntityUtils.toMap(deptService.listByIds(deptIds),Dept::getDeptId, Dept::getDeptName);
        Map<Integer, String> hashMap = EntityUtils.toMap(deptService.listByIds(deptIds),Dept::getDeptId, Dept::getDeptName);
        // 封装Vo，并添加到集合中(关键内容)
        userVos.forEach(e -> e.setDeptName(hashMap.get(e.getDeptId())));
    }
    
    
    /**
     * 分页查询学生信息（一个学生对应一个部门）
     */
    @Override
    public IPage<UserVo> getUserByPage(Page<User> page) {
        // 先查询用户信息
        IPage<User> xUserPage = page(page, Wrappers.emptyWrapper());
        // 初始化Vo
        IPage<UserVo> userVoPage = xUserPage.convert(UserVo::new);
        if (userVoPage.getRecords().size() > 0) {
            addDeptNameInfo(userVoPage);
        }
        return userVoPage;
    }
    
    
    private void addDeptNameInfo(IPage<UserVo> userVoPage) {
        // 提取用户userId，方便批量查询
        Set<Integer> deptIds = userVoPage.getRecords().stream().map(User::getDeptId).collect(toSet());
        // 根据deptId查询deptName
        List<Dept> dept = deptService.list(Wrappers.lambdaQuery(Dept.class).in(Dept::getDeptId, deptIds));
        // 构造映射关系，方便匹配deptId与deptName
        Map<Integer, String> hashMap = dept.stream().collect(toMap(Dept::getDeptId, Dept::getDeptName));
        // 将查询补充的信息添加到Vo中
        userVoPage.convert(e -> e.setDeptName(hashMap.get(e.getDeptId())));
    }
}
