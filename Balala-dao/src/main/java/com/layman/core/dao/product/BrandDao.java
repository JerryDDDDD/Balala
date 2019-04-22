package com.layman.core.dao.product;

import com.layman.core.bean.product.Brand;
import com.layman.core.bean.product.BrandQuery;

import java.util.List;

/**
 * @InterfaceName BrandDao
 * @Description 查询
 * @Author 叶泽文
 * @Data 2019/4/18 21:46
 * @Version 3.0
 **/
public interface BrandDao {

    // 查询结果集
    public List<Brand> selectBrandListByQuery(BrandQuery brandQuery);

    // 查询中条数
    public Integer selectCount(BrandQuery brandQuery);

    // 通过Id查询品牌
    public Brand selectBrandById(Long id);

    // 修改
    public void updateBrandById(Brand brand);
}
