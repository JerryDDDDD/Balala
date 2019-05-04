package com.layman.core.service.impl.product;

import cn.itcast.common.page.Pagination;
import com.layman.core.bean.product.*;
import com.layman.core.dao.product.ColorDao;
import com.layman.core.dao.product.ProductDao;
import com.layman.core.dao.product.SkuDao;
import com.layman.core.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.Date;
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

    // 加载颜色
    @Autowired
    private ColorDao colorDao;

    @Autowired
    private SkuDao skuDao;

    @Autowired
    private Jedis jedis;


    // 分页对象
    @Override
    public Pagination selectPaginationByQuery(Integer pageNo, String name, Long brandId, Boolean isShow) {

        ProductQuery productQuery = new ProductQuery();
        productQuery.setPageNo(Pagination.cpn(pageNo));

        //排序
        productQuery.setOrderByClause("id desc");
        ProductQuery.Criteria criteria = productQuery.createCriteria();

        StringBuilder params = new StringBuilder();
        if (name != null) {
            criteria.andNameLike("%" + name + "%");
            params.append("name=").append(name);
        }
        if (brandId != null) {
            criteria.andBrandIdEqualTo(brandId);
            params.append("&brandId=").append(brandId);
        }
        if (isShow != null) {
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


    // 颜色结果集
    public List<Color> selectColorList() {

        ColorQuery colorQuery = new ColorQuery();
        colorQuery.createCriteria().andParentIdNotEqualTo(0L);
        return colorDao.selectByExample(colorQuery);
    }

    // 商品保存
    public void insertProduct(Product product) {
        // 保存商品
        Long id = jedis.incr("pno");
        product.setId(id);
        // 下架状态
        product.setIsShow(false);
        product.setIsDel(true);
        productDao.insertSelective(product);
        // 返回ID

        String[] colors = product.getColors().split(",");
        // 遍历颜色
        for (String color : colors) {
            String[] sizes = product.getSizes().split(",");
            for (String size : sizes) {
                // 保存SKU
                Sku sku = new Sku();
                sku.setProductId(product.getId());
                sku.setColorId(Long.parseLong(color));
                sku.setSize(size);
                sku.setMarketPrice(99f);
                sku.setPrice(66f);
                // 运费
                sku.setDeliveFee(8f);
                // 库存
                sku.setStock(0);
                // 限制
                sku.setUpperLimit(200);
                // 时间
                sku.setCreateTime(new Date());
                skuDao.insertSelective(sku);
            }
        }
    }

    @Override
    public void isShow(Long[] ids) {
        Product product = new Product();
        // shang jia
        product.setIsShow(true);
        for (Long id : ids) {
            product.setId(id);
            productDao.updateByPrimaryKeySelective(product);
        }
    }
}
