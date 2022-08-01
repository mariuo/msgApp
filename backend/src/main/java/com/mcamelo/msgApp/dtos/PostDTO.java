package com.mcamelo.msgApp.dtos;

import com.mcamelo.msgApp.entities.Comment;
import com.mcamelo.msgApp.entities.Post;
import com.mcamelo.msgApp.entities.User;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PostDTO implements Serializable {

    private Long id;
    private String content;
    private Instant created;
    private String imageUrl;
    private UserDTO author;
    private List<CommentDTO> comments = new ArrayList<>();
    private List<UserDTO> likes = new ArrayList<>();

    public PostDTO() {
    }

    public PostDTO(Long id, String content, Instant created, String imageUrl, UserDTO author) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.imageUrl = imageUrl;
        this.author = author;
    }
    public PostDTO(Post entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.created = entity.getCreated();
        this.imageUrl = entity.getImageUrl();
        this.author = new UserDTO(entity.getAuthor());
    }

    public PostDTO(Post entity, List<Comment> comments, List<User> likes){
        this(entity);
        comments.forEach(x -> this.comments.add(new CommentDTO(x)));
        likes.forEach(y -> this.likes.add(new UserDTO(y)));
    }
    public PostDTO(Post entity, List<Comment> comments){
        this(entity);
        comments.forEach(x -> this.comments.add(new CommentDTO(x)));
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

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public List<UserDTO> getLikes() {
        return likes;
    }

    public void setLikes(List<UserDTO> likes) {
        this.likes = likes;
    }
}
