package com.layman.common.conversion;

import org.springframework.core.convert.converter.Converter;

/**
 * @ClassName CustomConverter
 * @Description 自定义转换器
 * @Author 叶泽文
 * @Data 2019/7/7 16:15
 * @Version 3.0
 **/
public class CustomConverter implements Converter<String, String> {

    // 去掉前后空格
    @Override
    public String convert(String source) {
        try {
            if (source != null) {
                source = source.trim();
                if (!"".equals(source)) {
                    return source;
                }
            }
        } catch (Exception e) {

        }
        return null;
    }
}
