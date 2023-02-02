package xin.ucode.front.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xin.ucode.front.domain.User;
import xin.ucode.front.mapper.UserMapper;
import xin.ucode.front.service.IUserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
