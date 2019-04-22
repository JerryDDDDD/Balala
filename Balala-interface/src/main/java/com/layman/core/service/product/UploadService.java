package com.layman.core.service.product;

/**
 * @InterfaceName UploadService
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/22 17:39
 * @Version 3.0
 **/
public interface UploadService {

    // 上传图片
    public String uploadPic(byte[] pic, String name, long size);
}
