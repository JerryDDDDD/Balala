package com.layman.core.service.impl.product;

import cn.itcast.common.page.Pagination;
import com.layman.core.bean.product.Color;
import com.layman.core.bean.product.ColorQuery;
import com.layman.core.bean.product.ProductQuery;
import com.layman.core.dao.product.ColorDao;
import com.layman.core.dao.product.ProductDao;
import com.layman.core.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName ProductServiceImpl
 * @Description 商品
 * @Author 叶泽文
 * @Data 2019/4/30 19:49
 * @Version 3.0
 **/
@Service("productServiceImpl")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    // 分页对象
    @Override
    public Pagination selectPaginationByQuery(Integer pageNo, String name, Long brandId, Boolean isShow) {

        ProductQuery productQuery = new ProductQuery();
        productQuery.setPageNo(Pagination.cpn(pageNo));

        //排序
        productQuery.setOrderByClause("id desc");
        ProductQuery.Criteria criteria = productQuery.createCriteria();

        StringBuilder params = new StringBuilder();
        if (name != null){
            criteria.andNameLike("%" + name + "%");
            params.append("name=").append(name);
        }
        if (brandId != null){
            criteria.andBrandIdEqualTo(brandId);
            params.append("&brandId=").append(brandId);
        }
        if (isShow != null){
            criteria.andIsShowEqualTo(isShow);
            params.append("&isShow=").append(isShow);
        } else {
            criteria.andIsShowEqualTo(false);
            params.append("&isShow=").append(false);
        }

        Pagination pagination = new Pagination(
                productQuery.getPageNo(),
                productQuery.getPageSize(),
                productDao.countByExample(productQuery),
                productDao.selectByExample(productQuery)
                );
        String url = "/product/list.do";
        pagination.pageView(url, params.toString());
        return pagination;
    }

    // 加载颜色
    @Autowired
    private ColorDao colorDao;

    // 颜色结果集
    public List<Color> selectColorList() {

        ColorQuery colorQuery = new ColorQuery();
        colorQuery.createCriteria().andParentIdNotEqualTo(0L);
        return colorDao.selectByExample(colorQuery);
    }
}
