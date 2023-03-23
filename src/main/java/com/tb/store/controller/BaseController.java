package com.tb.store.controller;


import com.tb.store.controller.ex.*;
import com.tb.store.service.ex.*;
import com.tb.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpSession;


public class BaseController {
    public static final int OK=200;

    /**
     * 被@ExceptionHandler修饰的方法就是要处理异常，并将结果返回给前端的方法
     * 系统会自动将异常对象传递给此方法的参数列表上，传过来的异常要么是UsernameDuplicatedException，要么是InsertException，
     * 根据不同的异常，确定返回给前端的JsonResult类型的对象result的状态码以及Message
     * @param e
     * @return
     */
    @ExceptionHandler({ServiceException.class, FileUploadException.class})//统一处理抛出的异常
    public JsonResult<Void> handlerException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已被注册");
        }else if (e instanceof AddressCountLimitException){
            result.setState(4003);
            result.setMessage("用户的收货地址数量超出异常");
        }else if (e instanceof AccountNotFoundException){
            result.setState(4004);
            result.setMessage("用户的收货地址不存在");
        }else if (e instanceof AccessDeniedException){
            result.setState(4005);
            result.setMessage("收货地址非法访问异常");
        }else if (e instanceof AccessDeniedException){
            result.setState(4006);
            result.setMessage("商品数据不存在的异常");
        }else if (e instanceof CartNotFoundException){
            result.setState(4007);
            result.setMessage("购物车数据不存在的异常");
        }else if (e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生未知异常");
        }else if (e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage("用户数据不存在");
        }else if (e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage("用户密码错误");
        }else if (e instanceof UpdateException){
            result.setState(5003);
            result.setMessage("更新数据时产生未知的异常");
        }else if (e instanceof FileEmptyException){
            result.setState(6000);
            result.setMessage("空文件异常");
        }else if (e instanceof FileSizeException){
            result.setState(6001);
            result.setMessage("文件大小异常");
        }else if (e instanceof FileTypeException){
            result.setState(6002);
            result.setMessage("文件类型异常");
        }else if (e instanceof FileStateException){
            result.setState(6003);
            result.setMessage("文件状态异常");
        }else if (e instanceof FileUploadIOException){
            result.setState(6004);
            result.setMessage("文件上传时IO异常");
        }
        return result;
    }

    protected final Integer getUidFromSession(HttpSession session){
        return  Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session){
        return  session.getAttribute("username").toString();
    }

}
