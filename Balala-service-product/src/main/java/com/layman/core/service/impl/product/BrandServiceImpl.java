package com.layman.core.service.impl.product;

import cn.itcast.common.page.Pagination;
import com.layman.core.bean.product.BrandQuery;
import com.layman.core.dao.product.BrandDao;
import com.layman.core.service.product.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BrandServiceImpl
 * @Description 品牌管理
 * @Author 叶泽文
 * @Data 2019/4/18 22:16
 * @Version 3.0
 **/
@Service("brandServiceImpl")
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    //查询分页对象
    public Pagination selectPaginationByQuery(String name, Integer isDisplay, Integer pageNo) {

        BrandQuery brandQuery = new BrandQuery();
        // 当前页
        brandQuery.setPageNo(Pagination.cpn(pageNo));
        // 页号
        brandQuery.setPageSize(3);

        if (null != name) {
            brandQuery.setName(name);
        }
        if (null != isDisplay) {
            brandQuery.setIsDisplay(isDisplay);
        } else {
            brandQuery.setIsDisplay(1);
        }

        Pagination pagination = new Pagination(brandQuery.getPageNo(),
                brandQuery.getPageSize(),
                brandDao.selectCount(brandQuery));
        pagination.setList(brandDao.selectBrandListByQuery(brandQuery));

        return pagination;
    }

}
