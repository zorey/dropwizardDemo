package com.demo.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Saying {
    private Integer id;
    private String content;

    @JsonProperty
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
