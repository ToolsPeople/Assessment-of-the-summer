package com.example.wanandroid.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RegisterErroBean {


    private Object data;
    private Integer errorCode;
    private String errorMsg;


    public RegisterErroBean(Object data) {
        this.data = data;
    }

    public RegisterErroBean(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public RegisterErroBean(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
