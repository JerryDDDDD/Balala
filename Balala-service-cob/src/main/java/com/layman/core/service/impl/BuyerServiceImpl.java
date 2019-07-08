package com.layman.core.service.impl;

import com.layman.core.bean.user.Buyer;
import com.layman.core.bean.user.BuyerQuery;
import com.layman.core.dao.user.BuyerDao;
import com.layman.core.service.cob.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BuyerServiceImpl
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/7/7 17:25
 * @Version 3.0
 **/

@Service("buyerServiceImpl")
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerDao buyerDao;

    // 通过用户名查询用户对象
    public Buyer selectBuyerByUsername(String username) {
        BuyerQuery buyerQuery = new BuyerQuery();
        buyerQuery.createCriteria().andUsernameEqualTo(username);
        List<Buyer> buyerList = buyerDao.selectByExample(buyerQuery);
        if (buyerList != null && buyerList.size() > 0) {
            return buyerList.get(0);
        }
        return null;
    }
}
