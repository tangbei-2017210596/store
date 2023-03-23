package com.tb.store.service;

import com.tb.store.entity.User;

import com.tb.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
//@RunWith:表示启动这个单元测试类需要传递一个参数，必须是springRunner类型
@RunWith(SpringRunner.class)
public class UserServiceTests {
    //如果用@Autowired就不行，因为idea有检测功能，接口是不能直接创建Bean的（动态代理技术来解决）
    @Resource
    private UserService userService;
    /**
     * 1必须被test注解修饰
     * 2返回值必须是void
     * 3方法的参数不指定任何类型
     * 4方法访问修饰符必须是public
     * 满足以上方法，不用启动整个项目，就可以做单元测试
     */
    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("tomcat");
            user.setPassword("1223");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
        User jack = userService.login("chenjiao", "123");
        System.out.println(jack);
    }
    @Test
    public void modifyPassword(){
        userService.modifyPassword(10,"管理员","123","1234");
    }

    @Test
    public void getByUid(){
        System.out.println(userService.getByUid(10));
    }

    @Test
    public void modifyUserInfo(){
        userService.modifyUserInfo(10,"管理员2","130","120@qq.com",0);
    }

    @Test
    public void modifyAvatar(){
        userService.modifyAvatar(10,"/uplaod/avatar2.png","唐备");
    }

}
