package com.mcamelo.msgApp.controllers;

import com.mcamelo.msgApp.dtos.UserDTO;
import com.mcamelo.msgApp.dtos.UserInsertDTO;
import com.mcamelo.msgApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    public ResponseEntity<UserDTO> getUserByUserName(@PathVariable String userName){
        UserDTO userDTO = userService.findUserByUsername(userName);
        return ResponseEntity.ok().body(userDTO);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO dto = userService.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserInsertDTO dto){
        UserDTO newDto = userService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);

    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserInsertDTO dto){
        UserDTO newDto = userService.update(id, dto);
        return ResponseEntity.ok().body(newDto);

    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
