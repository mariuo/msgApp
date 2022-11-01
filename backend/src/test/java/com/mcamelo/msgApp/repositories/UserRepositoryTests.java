package com.mcamelo.msgApp.repositories;

import com.mcamelo.msgApp.entities.User;
import com.mcamelo.msgApp.utils.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    private long existingId;
    private long nonExistingId;
    private long countTotalUsers;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalUsers = 4L;
    }
    @Test
    public void findByIdShouldReturnObjectWhenIdExist(){
        Optional<User> user = userRepository.findById(existingId);

        Assertions.assertTrue(user.isPresent());
    }

    @Test
    public void findByIdShouldReturnObjectWhenIdDoesNotExist(){
        Optional<User> user = userRepository.findById(nonExistingId);

        Assertions.assertTrue(user.isEmpty());
    }


    public void saveShouldPersistWithAutoincrementWhenNullId(){
        User user = Factory.createUser();
        user.setId(null);

        user = userRepository.save(user);

        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals(countTotalUsers+1, user.getId());
    }
    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){
        userRepository.deleteById(existingId);

        Optional<User> result = userRepository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }
    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdNotExists(){
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            userRepository.deleteById(nonExistingId);
        });
    }

}
