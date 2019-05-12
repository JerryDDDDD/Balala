package com.layman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

/**
 * @ClassName TestRedis
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/5/4 15:12
 * @Version 3.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestRedis {

    @Autowired
    private Jedis jedis;

    @Test
    public void testSpringJedis() throws Exception {
        jedis.set("11", "aaa");
    }


    @Test
    public void testRedis() throws Exception{
        Jedis jedis = new Jedis("192.168.219.90", 6379);
        Long pno = jedis.incr("pno");
        System.out.println(pno);
        jedis.close();
    }
}
