package com.newsfeed.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;

/**
 * Created by havea on 29/08/15.
 */
@Entity
public class NewsFeed extends BaseEntity{

    private String title;

    @Column( name = "published_date")
    private Date publishedDate;

    @ManyToOne
    private RssFeed source;

    @Column( name = "image_url")
    private String imageUrl;

    @Column( columnDefinition = "TEXT")
    private String content;

    private String articleUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public RssFeed getSource() {
        return source;
    }

    public void setSource(RssFeed source) {
        this.source = source;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }
}
