package com.example.reddit.WeekendProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Overall {
    @Id
    private String kind;
    private Data1 data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Data1 getData() {
        return data;
    }

    public void setData(Data1 data) {
        this.data = data;
    }

    public Overall(@JsonProperty("kind") String kind,
                   @JsonProperty("data") Data1 data) {
        this.kind = kind;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Overall{" +
                "kind='" + kind + '\'' +
                ", data=" + data +
                '}';
    }
}
