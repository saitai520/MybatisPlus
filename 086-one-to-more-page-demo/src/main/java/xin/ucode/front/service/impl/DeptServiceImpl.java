package xin.ucode.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.common.util.FieldInjectUtils;
import xin.ucode.front.domain.Dept;
import xin.ucode.front.domain.User;
import xin.ucode.front.entity.DeptVo;
import xin.ucode.front.mapper.DeptMapper;
import xin.ucode.front.mapper.UserMapper;
import xin.ucode.front.service.IDeptService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
    @Resource
    private UserMapper userMapper;
    
    /**
     * 取出部门DO
     *
     * @return
     */
    @Override
    public Page<Dept> selectDeptPage1() {
        LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class);
        
        Page<Dept> deptPage = this.page(new Page<>(1, 3), wrapper);
        return deptPage;
    }
    
    
    @Override
    public IPage<DeptVo> selectDeptPage2() {
        LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class);
        Page<Dept> deptPage = this.page(new Page<>(1, 3), wrapper);
        IPage<DeptVo> deptVoPage = EntityUtils.toPage(deptPage, DeptVo::new);
        return deptVoPage;
    }
    
    @Override
    public IPage<DeptVo> selectDeptPage3() {
        LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class);
        Page<Dept> deptPage = this.page(new Page<>(1, 3), wrapper);
        IPage<DeptVo> deptVoPage = EntityUtils.toPage(deptPage, DeptVo::new);
        // 完成userList字段属性注入
        Set<Long> deptIds = EntityUtils.toSet(deptVoPage.getRecords(), DeptVo::getDeptId);
        if (deptIds.size() > 0) {
            List<User> userList = userMapper.selectList(Wrappers.lambdaQuery(User.class).in(User::getDeptId, deptIds));
            Map<Long, List<User>> map = EntityUtils.groupBy(userList, User::getDeptId);
            for (DeptVo deptVo : deptVoPage.getRecords()) {
                deptVo.setUserList(map.get(deptVo.getDeptId()));
            }
        }
        return deptVoPage;
    }
    
    
    /**
     * 优化版 一行代码完成userList属性注入
     *
     * @return
     */
    @Override
    public IPage<DeptVo> selectDeptPage4() {
        LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class);
        Page<Dept> deptPage = this.page(new Page<>(1, 3), wrapper);
        IPage<DeptVo> deptVoPage = EntityUtils.toPage(deptPage, DeptVo::new);
        //  一行代码完成userList属性注入
        FieldInjectUtils.injectListField(deptVoPage, DeptVo::getDeptId, UserServiceImpl.class, User::getDeptId, DeptVo::getUserList);
        return deptVoPage;
    }
    
    
}
