package com.layman.core.service.cms;

import com.layman.core.bean.product.Product;
import com.layman.core.bean.product.Sku;

import java.util.List;

/**
 * @InterfaceName CmsService
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/6/27 21:26
 * @Version 3.0
 **/
public interface CmsService {

    // 查询商品
    public Product selectProductById(Long productId);

    // 查询sku结果集 有货
    public List<Sku> selectSkuListByProductId(Long productId);
}
