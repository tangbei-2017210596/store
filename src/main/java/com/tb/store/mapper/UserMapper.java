package com.tb.store.mapper;

import com.tb.store.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 用户模块的持久层接口
 */
//@Mapper
public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return  受影响的行数（增，删，改，都受到影响的行数作为返回值）
     */
    Integer insert(User user);
    User findByUserName(String username);

    /**
     * 修改用户密码的抽象方法
     * @param uid
     * @return
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据id查询用户
     * @param uid
     * @return
     */
    User findByUserUid(Integer uid);

    Integer updateUserInfoByUid(Integer uid,String phone,String email,Integer gender,String modifiedUser, Date modifiedTime);

    /**
     * 根据uid来修改用户头像，@Param("modifiedperson")是为了复习@param注解
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(Integer uid, String avatar, @Param("modifiedperson") String modifiedUser, Date modifiedTime);

}
