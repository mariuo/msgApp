package com.mcamelo.msgApp.services;

import com.mcamelo.msgApp.dtos.UserDTO;
import com.mcamelo.msgApp.entities.User;
import com.mcamelo.msgApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllUsers(Pageable pageable){
        Page<User> list = userRepository.findAll(pageable);
        return list.map(x -> new UserDTO(x));
    }

    public UserDTO findUserByUsername(String userName) {
        User entity = userRepository.findUserByUsername(userName).orElseThrow(()-> new RuntimeException("User not found"));
        return new UserDTO(entity);
    }
}
