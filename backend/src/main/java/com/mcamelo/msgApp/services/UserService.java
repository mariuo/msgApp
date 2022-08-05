package com.mcamelo.msgApp.services;

import com.mcamelo.msgApp.dtos.RoleDTO;
import com.mcamelo.msgApp.dtos.UserDTO;
import com.mcamelo.msgApp.dtos.UserInsertDTO;
import com.mcamelo.msgApp.entities.Role;
import com.mcamelo.msgApp.entities.User;
import com.mcamelo.msgApp.repositories.RoleRepository;
import com.mcamelo.msgApp.repositories.UserRepository;
import com.mcamelo.msgApp.services.exceptions.DatabaseException;
import com.mcamelo.msgApp.services.exceptions.ResourceNotFoundException;
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
         User obj = userRepository.findByUserName(userName);
         if(obj == null){
             throw new ResourceNotFoundException("Username not found " + userName);
         }
        return new UserDTO(obj);
    }
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
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
            throw new ResourceNotFoundException("Id not found " + id);

        }
    }
    @Transactional
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch(DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
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
