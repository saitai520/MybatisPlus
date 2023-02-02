package xin.ucode.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
     * 场景一：无任何筛选条件分页查询用户表关联数据
     *
     * @return
     */
    @Override
    public IPage<UserVo> selectUserPage1() {
        Page<User> page = new Page<>(1, 5);
        Page<User> userPage = this.page(page);
        IPage<UserVo> userVoIPage = EntityUtils.toPage(userPage, UserVo::new);
        return userVoIPage;
    }
    
    
    /**
     * 场景二：无任何筛选条件分页查询用户表关联数据(对部门表中的空属性完成赋值)
     *
     * @return
     */
    @Override
    public IPage<UserVo> selectUserPage2() {
        Page<User> page = new Page<>(1, 5);
        Page<User> userPage = this.page(page);
        IPage<UserVo> userVoIPage = EntityUtils.toPage(userPage, UserVo::new);
        Set<Long> deptIds = EntityUtils.toSet(userVoIPage.getRecords(), UserVo::getDeptId);
        if (deptIds.size() > 0) {
            LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class).in(Dept::getDeptId, deptIds);
            List<Dept> deptList = deptMapper.selectList(wrapper);
            Map<Long, Dept> map = EntityUtils.toMap(deptList, Dept::getDeptId, e -> e);
            for (UserVo userVo : userVoIPage.getRecords()) {
                Dept dept = map.get(userVo.getDeptId());
                userVo.setDeptName(dept.getDeptName());
                userVo.setStaff(dept.getStaff());
                userVo.setTel(dept.getTel());
            }
        }
        return userVoIPage;
    }
    
    
    /**
     * 场景三：主表（用户表）中添加查询条件 筛选条件分页查询用户表关联数据
     *
     * @return
     */
    @Override
    public IPage<UserVo> selectUserPage3() {
        Page<User> page = new Page<>(1, 5);
        LambdaQueryWrapper<User> gt = Wrappers.lambdaQuery(User.class).gt(User::getAge, 20);
        // 主表有条件分页查询数据
        Page<User> userPage = this.page(page, gt);
        
        IPage<UserVo> userVoIPage = EntityUtils.toPage(userPage, UserVo::new);
        Set<Long> deptIds = EntityUtils.toSet(userVoIPage.getRecords(), UserVo::getDeptId);
        if (deptIds.size() > 0) {
            LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class).in(Dept::getDeptId, deptIds);
            List<Dept> deptList = deptMapper.selectList(wrapper);
            Map<Long, Dept> map = EntityUtils.toMap(deptList, Dept::getDeptId, e -> e);
            for (UserVo userVo : userVoIPage.getRecords()) {
                Dept dept = map.get(userVo.getDeptId());
                userVo.setDeptName(dept.getDeptName());
                userVo.setStaff(dept.getStaff());
                userVo.setTel(dept.getTel());
            }
        }
        return userVoIPage;
    }
    
    
    /**
     * 场景四：副表（部门表）中添加查询条件 筛选条件分页查询用户表关联数据
     *
     * @return
     */
    @Override
    public IPage<UserVo> selectUserPage4() {
        Page<User> page = new Page<>(1, 5);
        LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class).eq(Dept::getDeptName, "Tomcat");
        List<Dept> deptList = deptMapper.selectList(wrapper);
        Map<Long, Dept> map = EntityUtils.toMap(deptList, Dept::getDeptId, e -> e);
        Set<Long> deptIds = EntityUtils.toSet(deptList, Dept::getDeptId);
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class).in(User::getDeptId, deptIds);
        Page<User> userPage = this.page(page, queryWrapper);
        IPage<UserVo> userVoIPage = EntityUtils.toPage(userPage, UserVo::new);
        for (UserVo userVo : userVoIPage.getRecords()) {
            Dept dept = map.get(userVo.getDeptId());
            userVo.setDeptName(dept.getDeptName());
            userVo.setStaff(dept.getStaff());
            userVo.setTel(dept.getTel());
        }
        return userVoIPage;
    }
    
    
    /**
     * 代码优化
     *
     * @return
     */
    @Override
    public IPage<UserVo> selectUserPage5() {
        Page<User> page = new Page<>(1, 5);
        LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class).eq(Dept::getDeptName, "Tomcat");
        List<Dept> deptList = deptMapper.selectList(wrapper);
        Map<Long, Dept> map = EntityUtils.toMap(deptList, Dept::getDeptId, e -> e);
        Set<Long> deptIds = EntityUtils.toSet(deptList, Dept::getDeptId);
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class).in(User::getDeptId, deptIds);
        Page<User> userPage = this.page(page, queryWrapper);
        IPage<UserVo> userVoIPage = EntityUtils.toPage(userPage, UserVo::new);
        // for (UserVo userVo : userVoIPage.getRecords()) {
        //     userVo.addDeptInfo(map.get(userVo.getDeptId()));
        // }
        // 极致精简
        userVoIPage.getRecords().forEach(e -> e.addDeptInfo(map.get(e.getDeptId())));
        return userVoIPage;
    }
    
    

    
    
}
