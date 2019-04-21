package com.layman.core.bean.product;

import lombok.Getter;

import java.io.Serializable;

/**
 * @ClassName Brand
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/18 21:49
 * @Version 3.0
 **/
public class BrandQuery extends Brand implements Serializable {

    private Integer pageNo =1;

    private Integer pageSize = 10;

    private Integer startRow;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}