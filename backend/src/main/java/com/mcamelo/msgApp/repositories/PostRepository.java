package com.mcamelo.msgApp.repositories;

import com.mcamelo.msgApp.entities.Post;
import com.mcamelo.msgApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT USER_ID FROM TB_POST_USER_LIKES WHERE POST_ID = ?1", nativeQuery = true)
    List<Long> findAllPostsLikesById(Long id);
}
