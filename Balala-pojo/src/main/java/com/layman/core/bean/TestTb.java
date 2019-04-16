package com.layman.core.bean;

import java.util.Date;

/**
 * @ClassName TestTb
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/15 21:28
 * @Version 3.0
 **/
public class TestTb {


    private Integer id;

    private String name;

    private Date birthday;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "TestTb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
