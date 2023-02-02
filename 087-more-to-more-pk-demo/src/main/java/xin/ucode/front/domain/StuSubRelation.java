package xin.ucode.front.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.collect.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 学生与课程关系表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_stu_sub_relation")
public class StuSubRelation implements Table.Cell<Integer, Integer, Integer> {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer stuId;
    private Integer subId;
    private Integer score;
    
    public StuSubRelation(StuSubRelation stuSubRelation) {
        if (Objects.nonNull(stuSubRelation)) {
            this.id = stuSubRelation.id;
            this.stuId = stuSubRelation.stuId;
            this.subId = stuSubRelation.subId;
            this.score = stuSubRelation.score;
        }
    }
    
    @Override
    public Integer getRowKey() {
        return stuId;
    }
    
    @Override
    public Integer getColumnKey() {
        return subId;
    }
    
    @Override
    public Integer getValue() {
        return score;
    }
}
