package com.mcamelo.msgApp.repositories;

import com.mcamelo.msgApp.dtos.CommentDTO;
import com.mcamelo.msgApp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    //@Query("select c from Comment c where c.POST_ID = :id")
    @Query(value = "SELECT * FROM TB_COMMENT WHERE POST_ID = ?1", nativeQuery = true)
    List<Comment> findAllCommentsById(Long id);
}
