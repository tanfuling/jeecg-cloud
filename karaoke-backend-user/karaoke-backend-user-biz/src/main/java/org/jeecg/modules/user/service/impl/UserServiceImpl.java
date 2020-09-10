package org.jeecg.modules.user.service.impl;

import org.jeecg.modules.user.entity.User;
import org.jeecg.modules.user.mapper.UserMapper;
import org.jeecg.modules.user.service.IUserService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 用户模块
 * @Author: jeecg-boot
 * @Date:   2020-09-09
 * @Version: V1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
