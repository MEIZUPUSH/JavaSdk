package com.meizu.push.sdk.server.model.push;

import java.io.Serializable;

/**
 * 上传图片结果
 */
public class ImageInfo implements Serializable {

    public static int NOTICE_BAR_IMG = 0;// 通知栏图片 请上传100kb以内的图片 gif,jpg,bmp,png,jpeg
    public static int NOTICE_EXPAND_IMG = 1; // 展开大图 请上传200kb以内的图片 gif,jpg,bmp,png,jpeg
    public static int NOTICE_BACKGROUND_IMG = 4; // 通知栏底图 请上传200kb以内的图片 jpg,png,jpeg
    public static int NOTICE_SMALL_ICON = 5; // 通知栏小图标 请上传100kb以内的图片 jpg,png,jpeg
    public static int NOTICE_BIG_ICON = 6; // 通知栏大图标 请上传100kb以内的图片 jpg,png,jpeg
    private int imgType;
    private String imgUrl;


    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "imgType=" + imgType +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
