package com.mcamelo.msgApp.dtos;

import com.mcamelo.msgApp.entities.Comment;

import java.io.Serializable;

public class CommentDTO implements Serializable {

    private Long id;
    private String content;
    private UserDTO authorComment;


    public CommentDTO(Long id, String content, UserDTO authorComment) {
        this.id = id;
        this.content = content;
        this.authorComment = authorComment;
    }

    public CommentDTO(Comment entity) {
        id = entity.getId();
        content = entity.getContent();
        authorComment = new UserDTO(entity.getUser());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDTO getAuthorComment() {
        return authorComment;
    }

    public void setAuthorComment(UserDTO authorComment) {
        this.authorComment = authorComment;
    }
}
