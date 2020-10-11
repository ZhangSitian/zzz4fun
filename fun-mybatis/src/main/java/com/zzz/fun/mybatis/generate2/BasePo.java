package com.zzz.fun.mybatis.generate2;

import java.util.Date;

public class BasePo {
    private Long id;
    private Date gmtCreated;
    private Date gmtModified;

    private String orderBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderByAsc(String orderBy) {
        this.orderBy = orderBy + " ASC";
    }

    public void setOrderByDesc(String orderBy) {
        this.orderBy = orderBy + " DESC";
    }
}
