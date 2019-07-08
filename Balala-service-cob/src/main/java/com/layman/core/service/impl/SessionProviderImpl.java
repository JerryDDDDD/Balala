package com.layman.core.service.impl;

import com.layman.common.web.Constants;
import com.layman.core.service.cob.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

/**
 * @ClassName SessionProviderImpl
 * @Description 保存用户名或验证码到Redis中
 * @Author 叶泽文
 * @Data 2019/7/8 19:57
 * @Version 3.0
 **/
public class SessionProviderImpl implements SessionProvider {

    @Autowired
    private Jedis jedis;

    private Integer exp = 30;

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    @Override
    public void setAttributeForUsername(String name, String value) {
        // 保存用户到Redis中
        jedis.set(name + ":" + Constants.USER_NAME, value);
        jedis.expire(name + ":" + Constants.USER_NAME, 60 * exp);
    }

    @Override
    public String getAttributeForUsername(String name) {
        String value = jedis.get(name + ":" + Constants.USER_NAME);
        // 不为null重新计算时间
        if (null != value) {
            jedis.expire(name + ":" + Constants.USER_NAME, 60 * exp);
        }
        return value;
    }
}
