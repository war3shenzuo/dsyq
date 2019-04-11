package com.etop.management.bean.page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/9/26
 */
public class NoticePage extends Page{

    private String title;

    private String addressee;

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
