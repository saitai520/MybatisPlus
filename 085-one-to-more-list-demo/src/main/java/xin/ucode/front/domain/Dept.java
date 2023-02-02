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
 * 部门
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_dept")
public class Dept {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long deptId;
    private String deptName;
    private String tel;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;
    
    public Dept(Dept dept) {
        if (Objects.nonNull(dept)) {
            this.deptId = dept.deptId;
            this.deptName = dept.deptName;
            this.tel = dept.tel;
            this.gmtCreate = dept.gmtCreate;
            this.gmtModified = dept.gmtModified;
        }
    }
}
