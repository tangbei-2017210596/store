package com.tb.store.service.impl;

import com.tb.store.entity.User;
import com.tb.store.mapper.UserMapper;
import com.tb.store.service.UserService;
import com.tb.store.service.ex.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.rmi.ServerException;
import java.util.Date;
import java.util.UUID;

@Service //将当前类的对象交给spring来管理，自动创建对象以及维护
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        if ((userMapper.findByUserName(user.getUsername()))!=null){
            throw new UsernameDuplicatedException("用户名已经被注册");
        }
        //补全数据
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //密码加密处理的实现 md5算法
        String oldpassword=user.getPassword();
        String salt= UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        user.setPassword(getMD5Password(oldpassword,salt));

        Integer rows = userMapper.insert(user);

        if (rows!=1){
            throw new ServiceException("注册过程出现异常");
        }
    }

    @Override
    public User login(String username, String password) {
        //根据用户名查询是否存在
        User loginUser = userMapper.findByUserName(username);
        if (loginUser==null){
            throw new UserNotFoundException("用户不存在");
        }

        //检测密码是否匹配
        String salt=loginUser.getSalt();
        String oldMd5Password=loginUser.getPassword();
        String newMd5Password=getMD5Password(password,salt);
        if (!newMd5Password.equals(oldMd5Password)){
            throw   new PasswordNotMatchException("密码输入错误");
        }
        //判断是否已删除
        if (loginUser.getIsDelete()==1){
            throw new UserNotFoundException("该用户已注销");
        }
        //用simpleUser来传输登录用户的id，name等关键信息 性能优化
        User simpleUser = new User();
        simpleUser.setUsername(loginUser.getUsername());
        simpleUser.setUid(loginUser.getUid());
        simpleUser.setAvatar(loginUser.getAvatar());
        return simpleUser;
    }

    @Override
    public void modifyPassword(Integer uid, String modifyUserName, String oldPassword, String newPassword) {
        User user = userMapper.findByUserUid(uid);
        if (user==null||user.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        //原始密码与数据库密码进行比较
        String oldMd5password = getMD5Password(oldPassword, user.getSalt());
        if (!oldMd5password.equals(user.getPassword())){
            throw  new PasswordNotMatchException("原始密码不正确");
        }
        String newMd5password = getMD5Password(newPassword, user.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5password, modifyUserName, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据时产生未知异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User user = userMapper.findByUserUid(uid);
        if (user==null||user.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPhone(user.getPhone());
        user1.setEmail(user.getEmail());
        user1.setGender(user.getGender());
        return user1;
    }

    @Override
    public void modifyUserInfo(Integer uid, String modifyUserName, String phone, String email, Integer gender) {
        User user = userMapper.findByUserUid(uid);
        if (user==null||user.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        Integer rows = userMapper.updateUserInfoByUid(uid, phone, email, gender, modifyUserName, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据时产生未知异常");
        }

    }

    @Override
    public void modifyAvatar(Integer uid, String avatar, String modifiedUser) {
        User user = userMapper.findByUserUid(uid);
        if (user==null||user.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, modifiedUser, new Date());
        if (rows!=1){
            throw new UpdateException("更新用户头像时产生未知异常");
        }
    }

    private String getMD5Password(String password,String salt){
        for (int i = 0; i < 3; i++) {
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
