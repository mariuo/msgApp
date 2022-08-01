package com.mcamelo.msgApp.dtos;

import com.mcamelo.msgApp.entities.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String userName;
    private String imageUrlProfile;

    public UserDTO(){

    }

    public UserDTO(Long id, String userName, String imageUrlProfile) {
        this.id = id;
        this.userName = userName;
        this.imageUrlProfile = imageUrlProfile;
    }
    public UserDTO(User entity) {
        id = entity.getId();
        userName = entity.getUserName();
        imageUrlProfile = entity.getImageUrlProfile();
    }

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

    public String getImageUrlProfile() {
        return imageUrlProfile;
    }

    public void setImageUrlProfile(String imageUrlProfile) {
        this.imageUrlProfile = imageUrlProfile;
    }

}
