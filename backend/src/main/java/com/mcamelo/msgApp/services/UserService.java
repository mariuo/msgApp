package com.mcamelo.msgApp.services;

import com.mcamelo.msgApp.dtos.RoleDTO;
import com.mcamelo.msgApp.dtos.UserDTO;
import com.mcamelo.msgApp.dtos.UserInsertDTO;
import com.mcamelo.msgApp.entities.Role;
import com.mcamelo.msgApp.entities.User;
import com.mcamelo.msgApp.repositories.RoleRepository;
import com.mcamelo.msgApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllUsers(Pageable pageable){
        Page<User> list = userRepository.findAll(pageable);
        return list.map(x -> new UserDTO(x));
    }
    @Transactional(readOnly = true)
    public UserDTO findUserByUsername(String userName) {
        User entity = userRepository.findUserByUsername(userName).orElseThrow(()-> new RuntimeException("User not found"));
        return new UserDTO(entity);
    }
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new RuntimeException("Entity not found"));
        return new UserDTO(entity);
    }
    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }
    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = userRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = userRepository.save(entity);
            return new UserDTO(entity);
        }
        catch(EntityNotFoundException e) {
            throw new RuntimeException("Id not found " + id);

        }
    }
    @Transactional
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new RuntimeException("Id not found" + id);
        }
        catch(DataIntegrityViolationException e) {
            throw new RuntimeException("Integrity violation");
        }

    }
    @Transactional
    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setUserName(dto.getUserName());
        entity.setImageUrlProfile(dto.getImageUrlProfile());
        entity.getRoles().clear();
        for(RoleDTO roleDto : dto.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDto.getId());
            entity.getRoles().add(role);
        }

    }

}
