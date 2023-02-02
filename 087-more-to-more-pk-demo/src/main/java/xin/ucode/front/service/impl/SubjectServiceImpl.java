package xin.ucode.front.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.ucode.front.domain.Subject;
import xin.ucode.front.mapper.SubjectMapper;
import xin.ucode.front.service.ISubjectService;
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper,Subject> implements ISubjectService{
}
