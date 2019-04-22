package com.layman.core.service.impl.product;

import com.layman.common.fdfs.FastDFSUtils;
import com.layman.core.service.product.UploadService;
import org.springframework.stereotype.Service;

/**
 * @ClassName UploadServiceImpl
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/22 17:38
 * @Version 3.0
 **/
@Service("uploadServiceImpl")
public class UploadServiceImpl implements UploadService {

    // 上传图片
    @Override
    public String uploadPic(byte[] pic, String name, long size) {
        return FastDFSUtils.uploadPic(pic, name, size);
    }
}
