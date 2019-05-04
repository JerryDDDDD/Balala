package com.layman.core.service.impl.product;

import com.layman.core.bean.product.Sku;
import com.layman.core.bean.product.SkuQuery;
import com.layman.core.dao.product.ColorDao;
import com.layman.core.dao.product.SkuDao;
import com.layman.core.service.product.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName SkuServiceImpl
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/5/4 16:03
 * @Version 3.0
 **/
@Service(value = "skuServiceImpl")
@Transactional
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuDao skuDao;

    @Autowired
    private ColorDao colorDao;


    @Override
    public List<Sku> selectSkuByProductId(Long productID) {
        SkuQuery skuQuery = new SkuQuery();
        skuQuery.createCriteria().andProductIdEqualTo(productID);
        List<Sku> skus = skuDao.selectByExample(skuQuery);
        for (Sku sku : skus) {
            sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
        }
        return skus;
    }

    // 修改
    public void updateSkuById(Sku sku) {
        skuDao.updateByPrimaryKeySelective(sku);
    }
}
