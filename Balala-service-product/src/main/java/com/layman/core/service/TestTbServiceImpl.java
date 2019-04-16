package com.layman.core.service;

import com.layman.core.bean.TestTb;
import com.layman.core.dao.TestTbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName TestTbServiceImpl
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/15 22:06
 * @Version 3.0
 **/
@Service
@Transactional
public class TestTbServiceImpl implements TestTbService {

    @Autowired
    private TestTbDao testTbDao;

    public void insertTestTb(TestTb testTb) {
        testTbDao.insertTestTb(testTb);

        //throw new RuntimeException();
    }

}
