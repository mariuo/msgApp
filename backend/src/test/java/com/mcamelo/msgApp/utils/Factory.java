package com.mcamelo.msgApp.utils;

import antlr.BaseAST;
import com.mcamelo.msgApp.dtos.*;
import com.mcamelo.msgApp.entities.Role;
import com.mcamelo.msgApp.entities.User;

import java.time.Instant;

public class Factory {
    public static User createUser(){
        User user = new User();
        user.setId(1L);
        user.setName("mario");
        user.setPassword("$2a$10$UoZsTxHmdN2zho36tBHYl.wNEM7M.UAB3Lma4oJzi5ZQYSfmYv/ly");
        user.setImageUrlProfile("https://avatars.githubusercontent.com/u/30843415?v=4");
        user.getRoles().add(createRoleAdmin());
        return user;
    }
    public static User createNormalUser(){
        User user = new User();
        user.setId(2L);
        user.setName("Renata");
        user.setPassword("123");
        user.setImageUrlProfile("https://avatars.githubusercontent.com/u/30843415?v=4");
        user.getRoles().add(createRoleUser());
        return user;
    }

    public static Role createRoleAdmin(){
        Role role = new Role(1L,"ROLE_ADMIN");
        return role;
    }
    public static Role createRoleUser(){
        Role role = new Role(2L,"ROLE_USER");
        return role;
    }
    public static RoleDTO createRoleDTOAdmin(){
        return new RoleDTO(createRoleAdmin());
    }
    public static UserDTO createUserDTO(){
        User user = createUser();
        return new UserDTO(user);
    }
    public static UserDTO createUserDTONormal(){
        User user = createNormalUser();
        return new UserDTO(user);
    }

    public static UserInsertDTO createUserInsertDTO() {
        User ent = createUser();
        UserInsertDTO insertDTO = new UserInsertDTO();
        insertDTO.setId(ent.getId());
        insertDTO.setName(ent.getName());
        insertDTO.setImageUrlProfile(ent.getImageUrlProfile());
        insertDTO.setPassword("123");
        insertDTO.getRoles().add(createRoleDTOAdmin());
        return insertDTO;
    }

    public static PostDTO createPostDTO(){
        return new PostDTO(1L,"Contenu", Instant.now(),"http://image",createUserDTONormal());
    }

    public static LikeRequest createLike(){
        return new LikeRequest(createPostDTO().getId(), createUserDTONormal());
    }
    public static CommentRequest createComment(){
        return new CommentRequest(1L, new CommentDTO(1L,"Teste", createUserDTONormal()));
    }
}
