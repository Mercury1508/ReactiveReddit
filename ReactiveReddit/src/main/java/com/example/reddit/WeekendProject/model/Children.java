package com.example.reddit.WeekendProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Children {

//    private String kind;
    private UserPosts data;



    public UserPosts getData() {
        return data;
    }

    public void setData(UserPosts data) {
        this.data = data;
    }


    public Children(
                    @JsonProperty("data") UserPosts data) {
//        this.kind = kind;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Children{" +
                ", data=" + data +
                '}';
    }
}
