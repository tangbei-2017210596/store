package com.tb.store.mapper;

import com.tb.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
//@RunWith:表示启动这个单元测试类需要传递一个参数，必须是springRunner类型
@RunWith(SpringRunner.class)
public class UserMapperTests {
    //如果用@Autowired就不行，因为idea有检测功能，接口是不能直接创建Bean的（动态代理技术来解决）
    @Resource
    private UserMapper userMapper;
    /**
     * 1必须被test注解修饰
     * 2返回值必须是void
     * 3方法的参数不指定任何类型
     * 4方法访问修饰符必须是public
     * 满足以上方法，不用启动整个项目，就可以做单元测试
     */
    @Test
    public void ins(){
        User user = new User();
        user.setUsername("jack2");
        user.setPassword("1223");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }
    @Test
    public void findbyName(){
        User jack2 = userMapper.findByUserName("jack2");
        System.out.println(jack2);
    }
    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(9,"222","管理员",new Date());
    }
    @Test
    public void findByUserUid(){
        System.out.println(userMapper.findByUserUid(9));
    }
    @Test
    public void updateUserInfoByUid(){
        userMapper.updateUserInfoByUid(10,"1302833","120783@qq.com",0,"管理员",new Date());
    }
    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(10,"/upload/avatar.png","管理员",new Date());
    }
}
