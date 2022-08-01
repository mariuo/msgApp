package com.mcamelo.msgApp.repositories;

import com.mcamelo.msgApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.userName like %?1")
    Optional<User> findUserByUsername(String userName);
}
