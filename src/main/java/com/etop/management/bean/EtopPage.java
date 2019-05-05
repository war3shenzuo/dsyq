package com.etop.management.bean;

import java.util.List;

import com.github.pagehelper.Page;

public class EtopPage<T> extends PageParam {

    private long total;    // 总条目数

    private List<T> rows;    // 数据


    public EtopPage(Page<T> page) {
        this.rows = page;
        super.order = page.getOrderBy();
        super.offset = page.getPageNum();
        super.limit = page.getPageSize();
        this.total = page.getTotal();
    }

    public EtopPage(List<T> rows, long total) {
        this.rows = rows;
        this.total = total;

    }

    public EtopPage() {

    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long l) {
        this.total = l;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }


}
