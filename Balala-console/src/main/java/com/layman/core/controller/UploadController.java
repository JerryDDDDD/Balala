package com.layman.core.controller;

import com.layman.common.web.Constants;
import com.layman.core.service.product.UploadService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName UploadController
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/22 17:42
 * @Version 3.0
 **/
@Controller
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping("/uploadPic.do")
    public void uploadPic(@RequestParam(required = false) MultipartFile pic,
                          HttpServletResponse response) throws IOException {

        String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());

        String url = Constants.IMAGE_URL + path;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", url);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonObject.toString());

    }


    @ResponseBody
    @RequestMapping("/uploadPics.do")
    public List<String> uploadPics(@RequestParam(required = false) MultipartFile[] pics,
                                   HttpServletResponse response) throws IOException {

        List<String> urls = new ArrayList<>();

        for (MultipartFile pic : pics) {
            String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
            String url = Constants.IMAGE_URL + path;
            urls.add(url);
        }
        return urls;
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write(jsonObject.toString());
    }

    // 富文本编辑器 图片上传
    @RequestMapping("/uploadFck.do")
    public void uploadFck(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // 接收
        // 强转为 spring 提供的 MultipartRequest
        MultipartRequest mr = (MultipartRequest) request;
        Map<String, MultipartFile> fileMap = mr.getFileMap();
        Set<Map.Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
        for (Map.Entry<String, MultipartFile> entry: entrySet) {
            MultipartFile pic = entry.getValue();
            String path = uploadService.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());

            String url = Constants.IMAGE_URL + path;

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("url", url);
            // 表示上传成功
            jsonObject.put("error", 0);

            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(jsonObject.toString());
        }
    }
}
