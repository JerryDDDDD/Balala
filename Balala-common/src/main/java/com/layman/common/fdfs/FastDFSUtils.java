package com.layman.common.fdfs;

import org.apache.commons.io.FilenameUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

/**
 * @ClassName FastDFSUtils
 * @Description 图片上传
 * @Author 叶泽文
 * @Data 2019/4/22 17:10
 * @Version 3.0
 **/
public class FastDFSUtils {


    public static String uploadPic(byte[] pic, String name, long size) {

        // 读取classpath下资源
        ClassPathResource resource = new ClassPathResource("fdfs_client.conf");
        String path = "";
        // 1. clientGlobe 读配置文件
        try {
            ClientGlobal.init(resource.getClassLoader().getResource("fdfs_client.conf").getPath());
            // 创建 主服务器 客户端 tracker
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);

            String file_ext_name = FilenameUtils.getExtension(name);

            NameValuePair[] meta_list = new NameValuePair[3];
            meta_list[0] = new NameValuePair("fileName", name);
            meta_list[1] = new NameValuePair("fileExtName", file_ext_name);
            meta_list[2] = new NameValuePair("fileLength", String.valueOf(size));

            path = storageClient1.upload_file1(pic, file_ext_name, meta_list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}
