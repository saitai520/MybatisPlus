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
/** 部门 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_dept")
public class Dept{
    private static final long serialVersionUID = 1L;
    private Boolean deleted;
    @TableId(type = IdType.AUTO)
    private Long deptId;
    private String deptName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;
    private Integer staff;
    private String tel;
    
    public Dept(Dept dept) {
        if (Objects.nonNull(dept)) {
            this.deleted=dept.deleted;
            this.deptId=dept.deptId;
            this.deptName=dept.deptName;
            this.gmtCreate=dept.gmtCreate;
            this.gmtModified=dept.gmtModified;
            this.staff=dept.staff;
            this.tel=dept.tel;
        }
    }
}
