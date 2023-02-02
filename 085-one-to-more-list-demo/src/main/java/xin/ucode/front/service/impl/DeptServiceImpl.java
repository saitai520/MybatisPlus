package xin.ucode.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.ColUtils;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.common.util.FieldInjectUtils;
import xin.altitude.cms.common.util.MapUtils;
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
     * 一对多查询 单条记录 DeptVo 中userList属性为null
     *
     * @param deptId
     * @return
     */
    @Override
    public DeptVo selectDeptList1(Long deptId) {
        Dept dept = this.getById(deptId);
        DeptVo deptVo = EntityUtils.toObj(dept, DeptVo::new);
        return deptVo;
    }
    
    /**
     * 一对多查询 单条记录 DeptVo 中userList属性完成赋值
     *
     * @param deptId
     * @return
     */
    @Override
    public DeptVo selectDeptList2(Long deptId) {
        Dept dept = this.getById(deptId);
        DeptVo deptVo = EntityUtils.toObj(dept, DeptVo::new);
        List<User> userList = userMapper.selectList(Wrappers.lambdaQuery(User.class).eq(User::getDeptId, deptVo.getDeptId()));
        deptVo.setUserList(userList);
        return deptVo;
    }
    
    /**
     * 一对多 查询列表数据
     *
     * @return
     */
    @Override
    public List<DeptVo> selectDeptList3() {
        LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class);
        List<Dept> deptList = this.list(wrapper);
        List<DeptVo> deptVoList = EntityUtils.toList(deptList, DeptVo::new);
        return deptVoList;
    }
    
    /**
     * 一对多 查询列表数据 空属性完成赋值
     *
     * @return
     */
    @Override
    public List<DeptVo> selectDeptList4() {
        LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class);
        List<Dept> deptList = this.list(wrapper);
        List<DeptVo> deptVoList = EntityUtils.toList(deptList, DeptVo::new);
        
        FieldInjectUtils.injectListField(deptVoList, DeptVo::getDeptId, UserServiceImpl.class, User::getDeptId, DeptVo::getUserList);
        return deptVoList;
    }
    
    
    /**
     * 一对多查询列表数据 取出每个部门年龄最大的用户
     *
     * @return
     */
    @Override
    public List<DeptVo> selectDeptList5() {
        LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class);
        List<Dept> deptList = this.list(wrapper);
        List<DeptVo> deptVoList = EntityUtils.toList(deptList, DeptVo::new);
        
        Set<Long> deptIds = EntityUtils.toSet(deptVoList, DeptVo::getDeptId);
        if (deptIds.size() > 0) {
            LambdaQueryWrapper<User> in = Wrappers.lambdaQuery(User.class).in(User::getDeptId, deptIds);
            List<User> userList = userMapper.selectList(in);
            // 以部门ID为单位对用户数据分组
            Map<Long, List<User>> map = EntityUtils.groupBy(userList, User::getDeptId);
            
            Map<Long, User> transMap = MapUtils.transMap(map, e -> ColUtils.max(e, User::getAge));
            for (DeptVo deptVo : deptVoList) {
                deptVo.setUserList(ColUtils.toCol(transMap.get(deptVo.getDeptId())));
            }
        }
        return deptVoList;
    }
    
}
