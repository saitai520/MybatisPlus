package xin.ucode.front.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xin.ucode.front.domain.Dept;
import xin.ucode.front.domain.User;

import java.util.List;

/**
 * @author <a href="http://www.altitude.xin" target="_blank">Java知识图谱</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptVo extends Dept {
    private List<User> userList;
    
    
    /**
     * 实现部门DO 转 部门VO
     * @param dept
     */
    public DeptVo(Dept dept) {
        super(dept);
    }
}
