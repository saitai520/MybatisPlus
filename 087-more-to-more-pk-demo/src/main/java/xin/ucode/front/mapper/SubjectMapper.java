package xin.ucode.front.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import xin.ucode.front.domain.Subject;
@Mapper
@Transactional(rollbackFor = Exception.class)
public interface SubjectMapper extends BaseMapper<Subject>{
}
