package com.mcamelo.msgApp.services;

import com.mcamelo.msgApp.dtos.UserDTO;
import com.mcamelo.msgApp.dtos.UserInsertDTO;
import com.mcamelo.msgApp.entities.Role;
import com.mcamelo.msgApp.entities.User;
import com.mcamelo.msgApp.repositories.RoleRepository;
import com.mcamelo.msgApp.repositories.UserRepository;
import com.mcamelo.msgApp.services.exceptions.DatabaseException;
import com.mcamelo.msgApp.services.exceptions.ResourceNotFoundException;
import com.mcamelo.msgApp.utils.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@ExtendWith(SpringExtension.class)
public class UserServiceTests {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    private long existindId;
    private long nonExistingId;
    private long dependentId;
    private PageImpl<User> page;
    private User user;
    private UserInsertDTO userInsertDTO;
    private Role role;


    @BeforeEach
    void setUp() throws Exception{
        existindId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;
        user = Factory.createUser();
        userInsertDTO = Factory.createUserInsertDTO();
        page = new PageImpl<>(List.of(user));
        role = Factory.createRoleAdmin();

        Mockito.when(userRepository.findById(existindId)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(userRepository.save((User) ArgumentMatchers.any())).thenReturn(user);

        Mockito.when(userRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

        doNothing().when(userRepository).deleteById(existindId);
        doThrow(EmptyResultDataAccessException.class).when(userRepository).deleteById(nonExistingId);
        doThrow(DataIntegrityViolationException.class).when(userRepository).deleteById(dependentId);

        Mockito.when(roleRepository.getOne(existindId)).thenReturn(role);
        Mockito.when(roleRepository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);

    }

    public void insertShouldReturnUserDTO(){
        UserDTO result = userService.insert(userInsertDTO);
        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenDoesNotIdExist(){
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> {
            userService.findById(nonExistingId);
        });
    }

    @Test
    public void findByIdShouldReturnUserDTOWhenIdExists(){
         UserDTO result = userService.findById(existindId);

        Assertions.assertNotNull(result);
    }

    @Test
    public void getAllUsersShouldReturnPage(){
        Pageable pageable = PageRequest.of(0,10);
        Page<UserDTO> result = userService.getAllUsers(pageable);
        Assertions.assertNotNull(result);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists(){
        Assertions.assertDoesNotThrow(()-> {
            userService.delete(existindId);
        });
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(existindId);
    }
    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists(){
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> {
            userService.delete(nonExistingId);
        });
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(nonExistingId);
    }
    @Test
    public void deleteShouldThrowDataBaseExceptionWhenIdDoesNotExists(){
        Assertions.assertThrows(DatabaseException.class, ()-> {
            userService.delete(dependentId);
        });
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(dependentId);
    }



}
