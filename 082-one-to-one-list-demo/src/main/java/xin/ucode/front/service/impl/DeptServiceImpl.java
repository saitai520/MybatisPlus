package xin.ucode.front.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.ucode.front.domain.Dept;
import xin.ucode.front.mapper.DeptMapper;
import xin.ucode.front.service.IDeptService;
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper,Dept> implements IDeptService{
}
