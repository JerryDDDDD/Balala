package com.layman.core.service.solr;

import cn.itcast.common.page.Pagination;
import com.layman.core.bean.product.Product;
import org.apache.solr.client.solrj.SolrServerException;

import java.util.List;

/**
 * @InterfaceName SearchService
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/5/11 17:16
 * @Version 3.0
 **/
public interface SearchService {
//    public List<Product> selectProductListByQuery(Integer pageNo ,String keyword) throws SolrServerException;
    public Pagination selectPaginationByQuery(Integer pageNo, String keyword) throws SolrServerException;

    public void insertProductToSolr(Long id);
}
