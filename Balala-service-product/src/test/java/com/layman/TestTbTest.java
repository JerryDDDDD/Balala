package com.layman;

import com.layman.core.bean.TestTb;
import com.layman.core.dao.TestTbDao;
import com.layman.core.service.TestTbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName TestTbTest
 * @Description junit + Spring
 * @Author 叶泽文
 * @Data 2019/4/15 21:43
 * @Version 3.0
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestTbTest {

    @Autowired
    private TestTbDao testTbDao;

    @Autowired
    private TestTbService testTbService;

    @Test
    public void testAdd() throws Exception {
        TestTb testTb = new TestTb();
        testTb.setName("李四");
        testTb.setBirthday(new Date());

        testTbService.insertTestTb(testTb);
    }
}
