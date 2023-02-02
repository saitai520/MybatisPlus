package xin.ucode.front.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
/** 用户 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_user")
public class User{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long userId;
    private String userName;
    private Integer age;
    private Long deptId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;
    
    public User(User user) {
        if (Objects.nonNull(user)) {
            this.userId=user.userId;
            this.userName=user.userName;
            this.age=user.age;
            this.deptId=user.deptId;
            this.gmtCreate=user.gmtCreate;
            this.gmtModified=user.gmtModified;
        }
    }
}
