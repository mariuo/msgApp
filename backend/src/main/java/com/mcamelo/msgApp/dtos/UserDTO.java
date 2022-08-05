package com.mcamelo.msgApp.dtos;

import com.mcamelo.msgApp.entities.User;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @Size(min = 3, max = 30, message = "Must has between 3 and 30 chars.")
    private String userName;
    private String imageUrlProfile;

    Set<RoleDTO> roles = new HashSet<>();

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
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }

    public Set<RoleDTO> getRoles() {
        return roles;
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
