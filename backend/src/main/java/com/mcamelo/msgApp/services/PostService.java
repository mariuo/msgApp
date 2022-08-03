package com.mcamelo.msgApp.services;

import com.mcamelo.msgApp.dtos.PostDTO;
import com.mcamelo.msgApp.dtos.UserDTO;
import com.mcamelo.msgApp.entities.Post;
import com.mcamelo.msgApp.entities.User;
import com.mcamelo.msgApp.repositories.PostRepository;
import com.mcamelo.msgApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    public PostRepository postRepository;

    @Autowired
    public UserRepository userRepository;

    @Transactional
    public List<PostDTO> getAllPosts(){
        List<Post> posts = postRepository.findAll();
        if(posts == null){
            return null;
        }
        List<PostDTO> postDTOList = new ArrayList<>(posts.size());
        for(Post p : posts){
            Post entity = new Post(
                    p.getId(),
                    p.getContent(),
                    p.getCreated(),
                    p.getImageUrl(),
                    p.getAuthor(),
                    p.getLikedUsers(),
                    p.getComments()
            );
            PostDTO dto = new PostDTO(entity, p.getComments(), p.getLikedUsers());
            postDTOList.add(dto);
        }
        return postDTOList;
    }

    @Transactional
    public PostDTO getById(Long id){
        Post post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Post not found") );
        return new PostDTO(post, post.getComments(), post.getLikedUsers());
    }

    @Transactional
    public PostDTO create(PostDTO postDTO) {
        Post entity = new Post();
        entity.setCreated(Instant.now());
        entity.setContent(postDTO.getContent());
        entity.setImageUrl(postDTO.getImageUrl());
        entity.setAuthor(userRepository.getReferenceById(postDTO.getAuthor().getId()));
        entity = postRepository.save(entity);
        return new PostDTO(entity);
    }
}
