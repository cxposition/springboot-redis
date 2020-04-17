package com.cx.po;

import java.io.Serializable;

/**
 * @author cx
 * @Time 2020/4/16 15:08
 * @Description
 */
public class Article implements Serializable {

    /**主键id*/
    private String id;
    /**文章标题*/
    private String title;
    /**正文内容*/
    private String content;
    /**作者*/
    private String author;
    /**创建日期*/
    private String createDate;
    /**点击量 or 阅读量*/
    private Long clickNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getClickNum() {
        return clickNum;
    }

    public void setClickNum(Long clickNum) {
        this.clickNum = clickNum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"content\":\"")
                .append(content).append('\"');
        sb.append(",\"author\":\"")
                .append(author).append('\"');
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"clickNum\":")
                .append(clickNum);
        sb.append('}');
        return sb.toString();
    }
}
