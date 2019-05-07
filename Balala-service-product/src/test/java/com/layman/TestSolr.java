package com.layman;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

/**
 * @ClassName TestRedis
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/5/4 15:12
 * @Version 3.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestSolr {

    @Autowired
    private SolrServer solrServer;

    @Test
    public void testSolrjSpring() throws Exception {
        SolrInputDocument doc = new SolrInputDocument();
        doc.setField("id", 4);
        doc.setField("name", "bbbb");

        solrServer.add(doc);
        solrServer.commit();
    }


//    @Test
//    public void testSlorJ() throws Exception {
//        String baseUrl = "http://192.168.219.90:8080/solr";
//        SolrServer solrServer = new HttpSolrServer(baseUrl);
//
//        SolrInputDocument doc = new SolrInputDocument();
//        doc.setField("id", 3);
//        doc.setField("name", "aaaa");
//
//        solrServer.add(doc);
//        solrServer.commit();
//    }
}
