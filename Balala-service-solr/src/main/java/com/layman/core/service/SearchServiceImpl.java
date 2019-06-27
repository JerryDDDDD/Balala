package com.layman.core.service;

import cn.itcast.common.page.Pagination;
import com.layman.core.bean.product.Product;
import com.layman.core.bean.product.ProductQuery;
import com.layman.core.bean.product.Sku;
import com.layman.core.bean.product.SkuQuery;
import com.layman.core.dao.product.ProductDao;
import com.layman.core.dao.product.SkuDao;
import com.layman.core.service.solr.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SearchServiceImpl
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/5/11 17:14
 * @Version 3.0
 **/
@Service("searchServiceImpl")
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrServer solrServer;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private SkuDao skuDao;

    // 全文搜索
//    public List<Product> selectProductListByQuery(String keyword) throws SolrServerException {
//
//        List<Product> products = new ArrayList<>();
//        SolrQuery solrQuery = new SolrQuery();
//        // 关键字
//        solrQuery.set("q", "name_ik:" + keyword);
//        // 过滤条件
//        // 高亮
//        // 排序
//        // 分页
//        QueryResponse response = solrServer.query(solrQuery);
//        SolrDocumentList docs = response.getResults();
//        // 发现的总条数  构建分页的时候使用
//        long numFound = docs.getNumFound();
//        for (SolrDocument doc : docs) {
//            Product product = new Product();
//            String id = (String) doc.get("id");
//            product.setId(Long.parseLong(id));
//            // 上品名称 IK
//            String name = (String) doc.get("name_ik");
//            product.setName(name);
//            // 图片
//            String url = (String) doc.get("url");
//            product.setImgUrl(url);
//            product.setPrice((float) 99.99);
//            product.setBrandId(8L);
//            products.add(product);
//        }
//        return products;
//
//    }

    //  分页的全文检索
    public Pagination selectPaginationByQuery(Integer pageNo, String keyword) throws SolrServerException {
        // 创建包装类
        ProductQuery productQuery = new ProductQuery();
        // 当前页
        productQuery.setPageNo(Pagination.cpn(pageNo));
        // 每页显示
        productQuery.setPageSize(12);

        // 拼接条件
        StringBuilder params = new StringBuilder();


        List<Product> products = new ArrayList<>();

        SolrQuery solrQuery = new SolrQuery();
        // 关键字
        solrQuery.set("q", "name_ik:" + keyword);
        params.append("keyword=").append(keyword);
        // 过滤条件
        // 高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("name_ik");
        // 设置高亮样式
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        solrQuery.setHighlightSimplePost("</span>");
        // 排序
        solrQuery.addSort("price", SolrQuery.ORDER.asc);
        // 分页
        solrQuery.setStart(productQuery.getStartRow());
        solrQuery.setRows(productQuery.getPageSize());
        QueryResponse response = solrServer.query(solrQuery);

        // 取高亮
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        // Map k : V
        SolrDocumentList docs = response.getResults();
        // 发现的总条数  构建分页的时候使用
        long numFound = docs.getNumFound();
        for (SolrDocument doc : docs) {
            Product product = new Product();
            String id = (String) doc.get("id");
            product.setId(Long.parseLong(id));
            // 上品名称 IK
            Map<String, List<String>> map = highlighting.get(id);
            List<String> list = map.get("name_ik");
            //String name = (String) doc.get(list.get(0));
            product.setName(list.get(0));
            // 图片
            String url = (String) doc.get("url");
            product.setImgUrl(url);
            product.setPrice((float) 99.99);
            product.setBrandId(8L);
            products.add(product);
        }

        // 构建分页对
        Pagination pagination = new Pagination(
                productQuery.getPageNo(),
                productQuery.getPageSize(),
                (int) numFound,
                products
        );

        // 页面展示
        String url = "/search?keyword";
        pagination.pageView(url, params.toString());
        return pagination;
    }

    // 保存商品信息到Solr服务器
    public void insertProductToSolr(Long id) {
        // 保存商品信息到Solr服务器
        SolrInputDocument doc = new SolrInputDocument();
        // 商品ID 图片 价格 商品名称 品牌id 时间 可选
        doc.setField("id", id);
        Product p = productDao.selectByPrimaryKey(id);
        doc.setField("name_ik", p.getName());
        doc.setField("url", p.getImages()[0]);
        // 查询售价
        SkuQuery skuQuery = new SkuQuery();
        skuQuery.createCriteria().andProductIdEqualTo(id);
        skuQuery.setOrderByClause("price asc");
        skuQuery.setPageNo(1);
        skuQuery.setPageSize(1);
        List<Sku> skus = skuDao.selectByExample(skuQuery);
        doc.setField("price", skus.get(0));

        try {
            solrServer.add(doc);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
