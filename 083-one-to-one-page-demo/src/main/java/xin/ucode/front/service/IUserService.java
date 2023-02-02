package xin.ucode.front.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import xin.ucode.front.domain.User;
import xin.ucode.front.entity.UserVo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface IUserService extends IService<User> {
    /**
     * 场景一：无任何筛选条件分页查询用户表关联数据
     * @return
     */
    IPage<UserVo> selectUserPage1();
    
    /**
     * 场景一：无任何筛选条件分页查询用户表关联数据(对部门表中的空属性完成赋值)
     *
     * @return
     */
    IPage<UserVo> selectUserPage2();
    
    /**
     * 场景三：主表（用户表）中添加查询条件 筛选条件分页查询用户表关联数据(对部门表中的空属性完成赋值)
     *
     * @return
     */
    IPage<UserVo> selectUserPage3();
    
    /**
     * 场景四：副表（部门表）中添加查询条件 筛选条件分页查询用户表关联数据
     *
     * @return
     */
    IPage<UserVo> selectUserPage4();
    
    /**
     * 代码优化
     *
     * @return
     */
    IPage<UserVo> selectUserPage5();

}
