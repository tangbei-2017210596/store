package com.tb.store.service;

import com.tb.store.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    void reg(User user); //参数是user的原因 是因为持久层的UserMappper里面的Insert方法需要的是User对象

    /**
     * 用户登录功能
     * @param username
     * @param password
     * @return  当前登录用户的数据，如果没登陆成功则返回null
     */
    User login(String username,String password);

    /**
     * 根据id查找用户 ，对应底层mapper的findByUserUid（）方法
     * @param uid
     * @return
     */
    User getByUid(Integer uid);

    void modifyPassword(Integer uid,String modifyUserName,String oldPassword,String newPassword);
    void modifyUserInfo(Integer uid,String modifyUserName,String phone,String email,Integer gender);

    /**
     * 修改用户的头像
     * @param uid 用户id
     * @param avatar 用户头像的路径
     * @param modifiedUser  用户的名称
     */
    void modifyAvatar(Integer uid, String avatar,String modifiedUser);

}
