package xin.ucode.front.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;
import xin.ucode.front.domain.User;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface UserMapper extends BaseMapper<User> {

}
