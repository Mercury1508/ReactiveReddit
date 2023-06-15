package com.example.reddit.WeekendProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "UserPosts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPosts {

    private String author;
    @Id
    private String link_title;
    private String body;
    private long created;
    @JsonIgnore
    private Date date_of_comment;


    public void setCreated(long created) {
        this.created = created;
    }

    public Date getDate_of_comment() {
        return date_of_comment;
    }

    public void setDate_of_comment(Date date_of_comment) {
        this.date_of_comment = date_of_comment;
    }

    public long getCreated() {
        return created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink_title() {
        return link_title;
    }

    public void setLink_title(String link_title) {
        this.link_title = link_title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public UserPosts(@JsonProperty("author_fullname")String author,
                     @JsonProperty("title")String link_title,
                     @JsonProperty("selftext")String body,
                     @JsonProperty("created") long created) {
        this.author = author;
        this.link_title = link_title;
        this.body = body;
        this.created = created;
    }

    @Override
    public String toString() {
        return "UserComments{" +
                ", author='" + author + '\'' +
                ", link_title='" + link_title + '\'' +
                ", body='" + body + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
