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
/** 课程表 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_subject")
public class Subject{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer subId;
    private String subName;
    
    public Subject(Subject subject) {
        if (Objects.nonNull(subject)) {
            this.subId=subject.subId;
            this.subName=subject.subName;
        }
    }
}
