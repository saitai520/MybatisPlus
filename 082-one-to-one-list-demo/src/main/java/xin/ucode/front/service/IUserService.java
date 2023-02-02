package xin.ucode.front.service;
import com.baomidou.mybatisplus.extension.service.IService;
import xin.ucode.front.domain.User;
import xin.ucode.front.entity.UserVo;

import java.util.List;

public interface IUserService extends IService<User>{
    /**
     * 无条件查询用户表与部门表连接后的数据
     *
     * @return
     */
    List<UserVo> selectUserList1();
    
    /**
     * 完成部门表空属性赋值
     *
     * @return
     */
    List<UserVo> selectUserList2();
    
    /**
     * 有条件数据查询 条件在用户表（主表）中
     * 年龄大于1岁的用户
     *
     * @return
     */
    List<UserVo> selectUserList3();
    
    /**
     * 有条件查询副表（部门表）中的数据
     * 查询部门名称为Tomcat的用户信息
     *
     * @return
     */
    List<UserVo> selectUserList4();
    
    List<UserVo> selectUserList5();
}
