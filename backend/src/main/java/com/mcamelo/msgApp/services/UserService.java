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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private PasswordEncoder passwordEncoder;
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
    public UserDTO findUserByName(String userName) {
         User obj = userRepository.findByName(userName);
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
            User entity = userRepository.getOne(id);
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
        entity.setName(dto.getName());
        entity.setImageUrlProfile(dto.getImageUrlProfile());
        entity.getRoles().clear();
        for(RoleDTO roleDto : dto.getRoles()) {
            Role role = roleRepository.getOne(roleDto.getId());
            entity.getRoles().add(role);
        }

    }
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);

        if(user == null){
            logger.error("User not found: "+ username);
            throw new UsernameNotFoundException("Username not found " +username);
        }
        logger.error("Logged: " + username);
        return user;
    }
}
