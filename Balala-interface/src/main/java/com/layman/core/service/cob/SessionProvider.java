package com.layman.core.service.cob;

/**
 * @InterfaceName SessionProvider
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/7/8 19:58
 * @Version 3.0
 **/
public interface SessionProvider {

    // 先行提供接口
    // 保存用户名到Redis中
    public void setAttributeForUsername(String name, String value);

    // 取用户名从Redis中
    public String getAttributeForUsername(String name);

    // 验证码

    // 退出登录
}
