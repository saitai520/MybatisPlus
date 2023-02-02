package xin.ucode.front.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.ucode.front.domain.Area;
import xin.ucode.front.mapper.AreaMapper;
import xin.ucode.front.service.IAreaService;
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper,Area> implements IAreaService{
}
