package com.devender.blog.payloads;

import com.devender.blog.entities.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Integer id;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
