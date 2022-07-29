package com.example.wanandroid.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BannerBean implements Serializable {

        private List<DataBean> data;
    private Integer errorCode;
    private String errorMsg;

@NoArgsConstructor
@Data
public static class DataBean {
    private String imagePath;
    private String title;
    private String url;

    public DataBean(String imagePath, String title, String url) {
        this.imagePath = imagePath;
        this.title = title;
        this.url = url;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

    public BannerBean(List<DataBean> data) {
        this.data = data;
    }

    public BannerBean(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public BannerBean(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
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
