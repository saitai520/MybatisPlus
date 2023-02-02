package xin.ucode.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;
import xin.ucode.front.domain.Dept;
import xin.ucode.front.domain.User;
import xin.ucode.front.entity.UserVo;
import xin.ucode.front.mapper.DeptMapper;
import xin.ucode.front.mapper.UserMapper;
import xin.ucode.front.service.IUserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private DeptMapper deptMapper;
    
    /**
     * 无条件查询用户表与部门表连接后的数据
     *
     * @return
     */
    @Override
    public List<UserVo> selectUserList1() {
        List<User> users = this.list();
        // 部门表中的属性存在 但是为空
        List<UserVo> userVos = EntityUtils.toList(users, UserVo::new);
        return userVos;
    }
    
    
    /**
     * 无条件查询用户表与部门表连接后的数据
     * 完成部门表空属性赋值
     *
     * @return
     */
    @Override
    public List<UserVo> selectUserList2() {
        List<User> users = this.list();
        // 部门表中的属性存在 但是为空
        List<UserVo> userVos = EntityUtils.toList(users, UserVo::new);
        Set<Long> deptIds = EntityUtils.toSet(userVos, UserVo::getDeptId);
        if (deptIds.size() > 0) {
            LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class).in(Dept::getDeptId, deptIds)
                    .select(Dept::getDeptId, Dept::getDeptName, Dept::getStaff, Dept::getTel);
            List<Dept> depts = deptMapper.selectList(wrapper);
            Map<Long, Dept> map = EntityUtils.toMap(depts, Dept::getDeptId, e -> e);
            
            for (UserVo userVo : userVos) {
                Dept dept = map.get(userVo.getDeptId());
                userVo.setDeptName(dept.getDeptName());
                userVo.setStaff(dept.getStaff());
                userVo.setTel(dept.getTel());
            }
        }
        
        return userVos;
    }
    
    
    /**
     * 有条件数据查询 条件在用户表（主表）中
     * 年龄大于1岁的用户
     *
     * @return
     */
    @Override
    public List<UserVo> selectUserList3() {
        // 构造主表查询条件 实现查询用户年龄大于1的用户数据
        LambdaQueryWrapper<User> gt = Wrappers.lambdaQuery(User.class).gt(User::getAge, 1);
        List<User> users = this.list(gt);
        // 部门表中的属性存在 但是为空
        List<UserVo> userVos = EntityUtils.toList(users, UserVo::new);
        Set<Long> deptIds = EntityUtils.toSet(userVos, UserVo::getDeptId);
        if (deptIds.size() > 0) {
            LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class).in(Dept::getDeptId, deptIds)
                    .select(Dept::getDeptId, Dept::getDeptName, Dept::getStaff, Dept::getTel);
            List<Dept> depts = deptMapper.selectList(wrapper);
            Map<Long, Dept> map = EntityUtils.toMap(depts, Dept::getDeptId, e -> e);
            
            for (UserVo userVo : userVos) {
                Dept dept = map.get(userVo.getDeptId());
                userVo.setDeptName(dept.getDeptName());
                userVo.setStaff(dept.getStaff());
                userVo.setTel(dept.getTel());
            }
        }
        
        return userVos;
    }
    
    
    /**
     * 有条件查询副表（部门表）中的数据
     * 查询部门名称为Tomcat的用户信息
     *
     * @return
     */
    @Override
    public List<UserVo> selectUserList4() {
        LambdaQueryWrapper<Dept> eq = Wrappers.lambdaQuery(Dept.class).eq(Dept::getDeptName, "Tomcat");
        List<Dept> depts = deptMapper.selectList(eq);
        Map<Long, Dept> map = EntityUtils.toMap(depts, Dept::getDeptId, e -> e);
        Set<Long> deptIds = EntityUtils.toSet(depts, Dept::getDeptId);
        
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class).in(User::getDeptId, deptIds);
        List<User> users = this.list(wrapper);
        List<UserVo> userVos = EntityUtils.toList(users, UserVo::new);
        for (UserVo userVo : userVos) {
            Dept dept = map.get(userVo.getDeptId());
            userVo.setDeptName(dept.getDeptName());
            userVo.setStaff(dept.getStaff());
            userVo.setTel(dept.getTel());
        }
        
        return userVos;
    }
    
    
    @Override
    public List<UserVo> selectUserList5() {
        LambdaQueryWrapper<Dept> eq = Wrappers.lambdaQuery(Dept.class).eq(Dept::getDeptName, "Tomcat");
        List<Dept> depts = deptMapper.selectList(eq);
        Map<Long, Dept> map = EntityUtils.toMap(depts, Dept::getDeptId, e -> e);
        Set<Long> deptIds = EntityUtils.toSet(depts, Dept::getDeptId);
        
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class).in(User::getDeptId, deptIds);
        List<User> users = this.list(wrapper);
        List<UserVo> userVos = EntityUtils.toList(users, UserVo::new);
        
        // 优化属性注入代码（完成代码复用）
        userVos.forEach(e -> e.addDeptInfo(map.get(e.getDeptId())));
        
        return userVos;
    }
}
