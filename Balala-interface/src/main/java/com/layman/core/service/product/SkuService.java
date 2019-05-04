package com.layman.core.service.product;

import com.layman.core.bean.product.Sku;

import java.util.List;

/**
 * @InterfaceName SkuService
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/5/4 16:03
 * @Version 3.0
 **/
public interface SkuService {

    // 通过商品Id 查询 库存结果集
    public List<Sku> selectSkuByProductId(Long productID);

    public void updateSkuById(Sku sku);
}
