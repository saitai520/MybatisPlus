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
import xin.altitude.spring.mybatisplus.joinquery.one2more.entity.vo.DeptVo;
import xin.altitude.spring.mybatisplus.joinquery.one2more.mapper.DeptMapper;
import xin.altitude.spring.mybatisplus.joinquery.one2more.mapper.UserMapper;
import xin.altitude.spring.mybatisplus.joinquery.one2more.service.IDeptService;
import xin.altitude.spring.mybatisplus.joinquery.one2more.service.IUserService;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

/**
 * @author explore
 * @since 2021/05/24 11:09
 **/
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
    @Autowired
    private UserMapper userMapper;
    
    
    @Autowired
    private IUserService userService;
    
    /**
     * 查询单个部门（其中一个部门有多个用户）
     */
    @Override
    public DeptVo getOneDept(Integer deptId) {
        // 查询部门基础信息
        LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class).eq(Dept::getDeptId, deptId);
        DeptVo deptVo = EntityUtils.toObj(getOne(wrapper), DeptVo::new);
        ofNullable(deptVo).ifPresent(this::addUserInfo);
        return deptVo;
    }
    
    private void addUserInfo(DeptVo deptVo) {
        // 根据部门deptId查询学生列表
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class).eq(User::getDeptId, deptVo.getDeptId());
        List<User> users = userMapper.selectList(wrapper);
        deptVo.setUsers(users);
    }
    
    /**
     * 查询多个部门（其中一个部门有多个用户）
     */
    @Override
    public List<DeptVo> getDeptByList() {
        // 按条件查询部门信息
        List<DeptVo> deptVos = EntityUtils.toList(list(Wrappers.emptyWrapper()), DeptVo::new);
        if (deptVos.size() > 0) {
            addUserInfo(deptVos);
        }
        return deptVos;
    }
    
    private void addUserInfo(List<DeptVo> deptVos) {
        // 准备deptId方便批量查询用户信息
        Set<Integer> deptIds = deptVos.stream().map(Dept::getDeptId).collect(toSet());
        // 用批量deptId查询用户信息
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class).in(User::getDeptId, deptIds);
        Map<Integer, List<User>> hashMap = userService.list(wrapper).stream().collect(groupingBy(User::getDeptId));
        // 合并结果，构造Vo，添加集合列表
        deptVos.forEach(e -> e.setUsers(hashMap.get(e.getDeptId())));
    }
    
    /**
     * 分页查询部门信息（其中一个部门有多个用户）
     */
    @Override
    public IPage<DeptVo> getDeptByPage(Page<Dept> page) {
        // 按条件查询部门信息
        IPage<DeptVo> deptVoPage = EntityUtils.toPage(page(page, Wrappers.emptyWrapper()), DeptVo::new);
        if (deptVoPage.getRecords().size() > 0) {
            addUserInfo(deptVoPage);
        }
        return deptVoPage;
    }
    
    private void addUserInfo(IPage<DeptVo> deptVoPage) {
        // 准备deptId方便批量查询用户信息
        Set<Integer> deptIds = EntityUtils.collectList(deptVoPage.getRecords(), Dept::getDeptId, toSet());
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class).in(User::getDeptId, deptIds);
        Map<Integer, List<User>> hashMap = userService.list(wrapper).stream().collect(groupingBy(User::getDeptId));
        // 合并结果，构造Vo，添加集合列表
        deptVoPage.convert(e -> e.setUsers(hashMap.get(e.getDeptId())));
    }
}
