package com.mcamelo.msgApp.controllers;

import com.mcamelo.msgApp.dtos.PostDTO;
import com.mcamelo.msgApp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    public PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        return ResponseEntity.ok().body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getAllPosts(@PathVariable Long id){

        return ResponseEntity.ok().body(postService.getById(id));
    }
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
        return ResponseEntity.ok().body(postService.create(postDTO));
    }

}
