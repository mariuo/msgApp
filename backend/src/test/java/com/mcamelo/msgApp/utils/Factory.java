package com.mcamelo.msgApp.utils;

import antlr.BaseAST;
import com.mcamelo.msgApp.dtos.RoleDTO;
import com.mcamelo.msgApp.dtos.UserDTO;
import com.mcamelo.msgApp.dtos.UserInsertDTO;
import com.mcamelo.msgApp.entities.Role;
import com.mcamelo.msgApp.entities.User;

public class Factory {
    public static User createUser(){
        User user = new User();
        user.setId(1L);
        user.setName("mario");
        user.setPassword("$2a$10$UoZsTxHmdN2zho36tBHYl.wNEM7M.UAB3Lma4oJzi5ZQYSfmYv/ly");
        user.setImageUrlProfile("https://avatars.githubusercontent.com/u/30843415?v=4");
        user.getRoles().add(createRole());
        return user;
    }

    public static Role createRole(){
        Role role = new Role(1L,"ROLE_ADMIN");
        return role;
    }
    public static RoleDTO createRoleDTO(){
        return new RoleDTO(createRole());
    }
    public static UserDTO createUserDTO(){
        User user = createUser();
        return new UserDTO(user);
    }

    public static UserInsertDTO createUserInsertDTO() {
        User ent = createUser();
        UserInsertDTO insertDTO = new UserInsertDTO();
        insertDTO.setId(ent.getId());
        insertDTO.setName(ent.getName());
        insertDTO.setImageUrlProfile(ent.getImageUrlProfile());
        insertDTO.setPassword("123");
        insertDTO.getRoles().add(createRoleDTO());
        return insertDTO;
    }
}
