package com.layman;

import com.layman.core.bean.order.Order;
import com.layman.core.bean.product.Product;
import com.layman.core.dao.order.OrderDao;
import com.layman.core.dao.product.ProductDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName TestTbTest
 * @Description junit + Spring
 * @Author 叶泽文
 * @Data 2019/4/15 21:43
 * @Version 3.0
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestProduct {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderDao orderDao;

    @Test
    public void testAdd() throws Exception {
        // 通过Id查询商品
        Product product = productDao.selectByPrimaryKey(441L);

        Order order = orderDao.selectByPrimaryKey(49L);
        System.out.println("[INFO]" + product);
        System.out.println("[INFO]" + order);
    }
}
