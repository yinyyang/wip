package com.springcloud.app.model.news;


import java.io.Serializable;


public class TNews implements Serializable {


    private Integer id;


    private String title;


    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TNews{" +
        "id=" + id +
        ", title=" + title +
        ", content=" + content +
        "}";
    }
}
