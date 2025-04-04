package com.example.tomatomall.exception;

public class TomatoMallException extends RuntimeException {
    public TomatoMallException(String message) {
        super(message);
    }
    public static TomatoMallException usernameOrPasswordError(){
        return new TomatoMallException("用户不存在/用户密码错误");
    }
    public static TomatoMallException usernameExist(){
        return new TomatoMallException("用户名已存在");
    }
    public static TomatoMallException needLogin(){
        return new TomatoMallException("用户未登录");
    }
    public static TomatoMallException usernameNotExist(){
        return new TomatoMallException("用户名不存在");
    }
    public static TomatoMallException fileUploadFail() { return new TomatoMallException("文件上传失败");}
}
