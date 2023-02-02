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
/** 书籍 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_user")
public class User{
    private static final long serialVersionUID = 1L;
    private Integer age;
    private Boolean deleted;
    private Long deptId;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;
    @TableId(type = IdType.AUTO)
    private Long userId;
    private String userName;
    
    public User(User user) {
        if (Objects.nonNull(user)) {
            this.age=user.age;
            this.deleted=user.deleted;
            this.deptId=user.deptId;
            this.email=user.email;
            this.gmtCreate=user.gmtCreate;
            this.gmtModified=user.gmtModified;
            this.userId=user.userId;
            this.userName=user.userName;
        }
    }
}
