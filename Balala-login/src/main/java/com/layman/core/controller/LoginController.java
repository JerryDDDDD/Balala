package com.layman.core.controller;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/7/7 14:34
 * @Version 3.0
 **/

import com.layman.common.utils.RequestUtils;
import com.layman.core.bean.user.Buyer;
import com.layman.core.service.cob.BuyerService;
import com.layman.core.service.cob.SessionProvider;
import org.apache.commons.codec.binary.Hex;
import org.apache.zookeeper.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 单点登录系统
 * 1. 去登录页面
 * 2. 提交登录表单
 * 3. 加密 MD5+十六进制+salt
 **/

@Controller
public class LoginController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SessionProvider sessionProvider;

    // 1. 去登录页面
    @RequestMapping(value = "/login.aspx", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    // 2. 提交登录
    @RequestMapping(value = "/login.aspx", method = RequestMethod.POST)
    public String login(String username, String password, String returnUrl, Model model,
                        HttpServletRequest request, HttpServletResponse response) {
        // 1. 用户名不能为空
        if (username != null) {
            // 2. 密码不能为空
            if (password != null) {
                // 3. 用户名必须正确
                Buyer buyer = buyerService.selectBuyerByUsername(username);
                if (buyer != null) {
                    // 4. 密码必须正确
                    if (buyer.getPassword().equals(encodePassword(password))) {
                        // 5. 保存用户名到Session中 用户对象到Session
                        sessionProvider.setAttributeForUsername(RequestUtils.getCESSIONID(request, response), buyer.getUsername() );
                        // 6. 跳转到之前访问的页面
                        if (!returnUrl.equals("") && returnUrl != null) {
                            return "redirect:" + returnUrl;
                        } else {
                            return "http://localhost:8002";
                        }
                    } else {
                        model.addAttribute("error", "密码必须正确");
                    }
                } else {
                    model.addAttribute("error", "用户名必须正确");
                }

            } else {
                model.addAttribute("error", "密码不能为空");
            }

        } else {
            model.addAttribute("error", "用户名不能为空");
        }
        return "login";
    }

    // 加密
    public String encodePassword(String password) {
        char[] chars = null;
        // 1 MD5
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            // 加密后的二进制
            byte[] digest = instance.digest(password.getBytes());
            // 2 十六进制
            chars = Hex.encodeHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        LoginController l = new LoginController();
        String w = l.encodePassword("123456");
        System.out.println(w);
    }
}
