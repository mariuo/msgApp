package com.mcamelo.msgApp.repositories;

import com.mcamelo.msgApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("select u from User u where u.userName = :userName")
//    Optional<User> findUserByUsername(String userName);
    User findByName(String userName);
//    @Query("SELECT DISTINCT obj FROM User obj INNER JOIN obj.likesIds cats WHERE "
//            + "(COALESCE(:categories) IS NULL OR cats IN :categories) AND "
//            + "(LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%')))")
    //List<User> findAllUsersLikes(List<Long> likesIds);
}
