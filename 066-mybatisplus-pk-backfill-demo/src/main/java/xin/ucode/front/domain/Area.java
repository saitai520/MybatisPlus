package xin.ucode.front.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_area")
public class Area extends Model<Area> {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    
    public Area(Area area) {
        if (Objects.nonNull(area)) {
            this.id = area.id;
            this.name = area.name;
        }
    }
    
    public Area(String name) {
        this.name = name;
    }
    
    @Override
    public Serializable pkVal() {
        return id;
    }
}
