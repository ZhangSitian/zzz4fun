package com.zzz.learn.mybatis.dao.domain;

import java.util.List;

public class OrderDetail {
    private long id;
    private long orders_id;
    private long items_id;
    private long items_num;
    private Items items;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(long orders_id) {
        this.orders_id = orders_id;
    }

    public long getItems_id() {
        return items_id;
    }

    public void setItems_id(long items_id) {
        this.items_id = items_id;
    }

    public long getItems_num() {
        return items_num;
    }

    public void setItems_num(long items_num) {
        this.items_num = items_num;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }
}
