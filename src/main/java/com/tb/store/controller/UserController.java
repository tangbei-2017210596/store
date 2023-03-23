package com.tb.store.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.tb.store.controller.ex.*;
import com.tb.store.entity.User;
import com.tb.store.service.UserService;
import com.tb.store.service.ex.InsertException;
import com.tb.store.service.ex.UsernameDuplicatedException;
import com.tb.store.util.JsonResult;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    /**
     * springMVC会将前端的URL地址中的参数名pojo类的属性名进行比较，如果这两个名称相同，则将值注入到pojo类对应的属性上
     * @param user
     * @return
     */
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return  new JsonResult<>(OK);
    }

    /**
     * 对请求进行处理的方法的参数列表设置为非pojo类型，springMVC会将请求的参数名和方法的参数名进行直接比较，如果名称相同，则完成值的注入
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login")
    public JsonResult<User> login(String username,String password,HttpSession session){
        User loginuser = userService.login(username, password);

        //向session对象中完成数据的绑定(session是全局的)
        session.setAttribute("username",loginuser.getUsername());
        session.setAttribute("uid",loginuser.getUid());

        System.out.println(getUidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        return new JsonResult<User>(OK,loginuser);
    }
    @RequestMapping("modifyPassword")
    public  JsonResult<Void> modifyPassword(String oldPassword,String newPassword,HttpSession session){
        userService.modifyPassword(getUidFromSession(session),getUsernameFromSession(session),oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> get_by_uid(HttpSession session){
        User dataforDisplay = userService.getByUid(getUidFromSession(session));
        return  new JsonResult<>(OK,dataforDisplay);
    }

    @RequestMapping("modifyInfo")
    public  JsonResult<Void> modifyInfo(String phone,String email,Integer gender,HttpSession session){
        userService.modifyUserInfo(getUidFromSession(session),getUsernameFromSession(session),phone,email,gender);
        return new JsonResult<>(OK);
    }

    public static final int AVATAR_MAXSIZE=10*1024*1024;
    public static final List<String> AVATAR_TYPE=new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");

    }

    /**
     * MultipartFile是springM提供的，这个接口为我们包装了获取文件类型的数据（任何类型的file都可以接收）
     * SpirngBOOT可以自动将前端浏览器上传过来的file文件注入到同名为file的请求处理方法的参数列表中
     * @param session
     * @param file
     * @return
     */
    @RequestMapping("modifyAvatar")
    public JsonResult<String> modifyAvatar(HttpSession session,MultipartFile file){
        //判断文件是否为空
        if (file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize()>AVATAR_MAXSIZE){
            throw new FileSizeException("文件大小超过10MB");
        }
        String contentType = file.getContentType();
        if (!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPE);
        }
        //上传的文件 /upload/文件.png
        String parent = session.getServletContext().getRealPath("upload");
        File avatarDir = new File(parent);
        if (!avatarDir.exists()){
            avatarDir.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        System.out.println("originalFilename="+originalFilename);
        String suffix="";
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString().toUpperCase()+suffix;
        try{
            file.transferTo(new File(avatarDir, filename));
        }catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        }catch (IOException e){
            throw new FileUploadIOException("文件读写异常");
        }
        String avatar="/upload/"+filename;
        userService.modifyAvatar(getUidFromSession(session),avatar,getUsernameFromSession(session));
        return new JsonResult<>(OK,avatar);
    }

}
