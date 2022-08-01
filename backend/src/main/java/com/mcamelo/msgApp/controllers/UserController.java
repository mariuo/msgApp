package com.mcamelo.msgApp.controllers;

import com.mcamelo.msgApp.dtos.UserDTO;
import com.mcamelo.msgApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable){
        Page<UserDTO> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserDTO> getUserByUserName(@PathVariable String userName){
        UserDTO userDTO = userService.findUserByUsername(userName);
        return ResponseEntity.ok().body(userDTO);
    }
}
