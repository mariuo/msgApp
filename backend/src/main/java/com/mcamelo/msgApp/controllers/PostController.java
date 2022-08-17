package com.mcamelo.msgApp.controllers;

import com.mcamelo.msgApp.dtos.CommentDTO;
import com.mcamelo.msgApp.dtos.CommentRequest;
import com.mcamelo.msgApp.dtos.LikeRequest;
import com.mcamelo.msgApp.dtos.PostDTO;
import com.mcamelo.msgApp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    public PostService postService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        return ResponseEntity.ok().body(postService.getAllPosts());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getAllPosts(@PathVariable Long id){

        return ResponseEntity.ok().body(postService.getById(id));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        return ResponseEntity.ok().body(postService.create(postDTO));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePost(@PathVariable Long id){
        postService.remove(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/comment")
    public ResponseEntity<PostDTO> createComment(@RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok().body(postService.createComment(commentRequest));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/comment")
    public ResponseEntity<PostDTO> deleteComment(@RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok().body(postService.deleteComment(commentRequest));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/like")
    public ResponseEntity<PostDTO> createLike(@RequestBody LikeRequest likeRequest){
        return ResponseEntity.ok().body(postService.createLike(likeRequest));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/like")
    public ResponseEntity<PostDTO> deleteComment(@RequestBody LikeRequest likeRequest){
        return ResponseEntity.ok().body(postService.deleteLike(likeRequest));
    }
}
