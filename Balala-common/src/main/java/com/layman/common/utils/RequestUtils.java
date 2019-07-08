package com.layman.common.utils;

import com.layman.common.web.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @ClassName RequestUtils
 * @Description 获取CESSIONID
 * @Author 叶泽文
 * @Data 2019/7/8 20:30
 * @Version 3.0
 **/
public class RequestUtils {

    // 获取
    public static String getCESSIONID(HttpServletRequest request, HttpServletResponse response) {

        // 1. 取出Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            // 2. 判断Cookie中是否有CSESSIONID
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("CESSIONID")) {
                    // 3. 有 直接使用
                    return cookie.getValue();
                }
            }
        }
        // 4. 没有则创建一个CSESSIONID 并保存到Cookie中 同时把此Cookie写会浏览器中
        String cessionId = UUID.randomUUID().toString().replaceAll("-", "");
        Cookie cookie = new Cookie("CSESSIONID", cessionId);
        // 设置存活时间 -1: 关闭浏览器销毁  0: 立即销毁  >0 最大存活时间
        cookie.setMaxAge(-1);
        // 设置路径
        cookie.setPath("/");
        // 设置跨域
        //cookie.setDomain(".jd.com");
        // 把Cookie写会浏览器
        response.addCookie(cookie);
        return cessionId;
    }
}
