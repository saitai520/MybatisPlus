package xin.ucode.front.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 用户
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_user")
public class User {
    private static final long serialVersionUID = 1L;
    private Integer age;
    private Long deptId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;
    @TableId(type = IdType.AUTO)
    private Long userId;
    private String userName;
    
    public User(User user) {
        if (Objects.nonNull(user)) {
            this.age = user.age;
            this.deptId = user.deptId;
            this.gmtCreate = user.gmtCreate;
            this.gmtModified = user.gmtModified;
            this.userId = user.userId;
            this.userName = user.userName;
        }
    }
}
