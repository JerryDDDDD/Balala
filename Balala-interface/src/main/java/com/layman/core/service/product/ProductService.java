package com.layman.core.service.product;

import cn.itcast.common.page.Pagination;
import com.layman.core.bean.product.Color;
import com.layman.core.bean.product.Product;

import java.util.List;

/**
 * @InterfaceName ProductService
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/30 19:54
 * @Version 3.0
 **/
public interface ProductService {

    public Pagination selectPaginationByQuery(Integer pageNo, String name, Long brandId, Boolean isShow);

    List<Color> selectColorList();

    public void insertProduct(Product product);
}
