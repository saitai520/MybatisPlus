package xin.ucode.front.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import xin.ucode.front.domain.Dept;
import xin.ucode.front.domain.User;

/**
 * @author 赛先生和泰先生
 * @author 笔者专题技术博客 —— http://www.altitude.xin
 * @author B站视频 —— https://space.bilibili.com/1936685014
 **/
@Data
@NoArgsConstructor
public class UserVo extends User {
    private String deptName;
    private Integer staff;
    private String tel;
    
    /**
     * 关键构造器
     *
     * @param user
     */
    public UserVo(User user) {
        super(user);
    }
    
    
    /**
     * 注入部门属性信息
     *
     * @param dept
     */
    public void addDeptInfo(Dept dept) {
        this.deptName = dept.getDeptName();
        this.staff = dept.getStaff();
        this.tel = dept.getTel();
    }
}
