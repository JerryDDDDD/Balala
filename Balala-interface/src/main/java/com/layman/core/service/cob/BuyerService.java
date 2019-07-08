package com.layman.core.service.cob;

import com.layman.core.bean.user.Buyer;

/**
 * @InterfaceName BuyerService
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/7/7 17:26
 * @Version 3.0
 **/
public interface BuyerService {
    public Buyer selectBuyerByUsername(String username);
}
