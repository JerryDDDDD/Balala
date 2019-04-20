package com.layman.core.service.product;

import cn.itcast.common.page.Pagination;

/**
 * @InterfaceName BrandService
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/18 22:17
 * @Version 3.0
 **/
public interface BrandService {

    public Pagination selectPaginationByQuery(String name, Integer isDisplay, Integer pageNo);
}
