package com.mcamelo.msgApp.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String imageUrlProfile;

    @OneToMany(mappedBy = "userTo")
    private List<Notification> notifications = new ArrayList<>();

    @ManyToMany(mappedBy = "likedUsers")
    private List<Post> likes;

    public User() {
    }

    public User(Long id, String userName, String password, String imageUrlProfile) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.imageUrlProfile = imageUrlProfile;
    }

    public List<Post> getLikes(){return likes;}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrlProfile() {
        return imageUrlProfile;
    }

    public void setImageUrlProfile(String imageUrlProfile) {
        this.imageUrlProfile = imageUrlProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
