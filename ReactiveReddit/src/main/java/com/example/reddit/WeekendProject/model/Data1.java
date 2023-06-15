package com.example.reddit.WeekendProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data1 {
    List<Children> children;



    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public Data1(
                 @JsonProperty("score") List<Children> children) {

        this.children = children;
    }

    @Override
    public String toString() {
        return "Data1{" +
                ", children=" + children +
                '}';
    }
}
