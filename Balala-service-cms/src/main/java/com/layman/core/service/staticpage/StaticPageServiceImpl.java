package com.layman.core.service.staticpage;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Map;


/**
 * @ClassName StaticPageServiceImpl
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/7/4 17:37
 * @Version 3.0
 **/
public class StaticPageServiceImpl implements StaticPageService, ServletContextAware {

    private Configuration conf;

    private ServletContext servletContext;

    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.conf = freeMarkerConfigurer.getConfiguration();
    }

    // 静态化 商品
    public void productStaticPage(Map<String, Object> root, String id) {
        String path = getPath("/html/product/" + id + ".html");
        // 保证路径存在
        File f = new File(path);
        File parentFile = f.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        // 输出模板
        Writer out = null;
        try {
            Template template = conf.getTemplate("product.html");
            out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
            template.process(root, out);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关流
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 获取路径
    public String getPath(String name) {
        return servletContext.getRealPath(name);
    }
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    // 静态化订单
}
