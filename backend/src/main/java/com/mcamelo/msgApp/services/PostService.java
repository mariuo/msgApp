package com.mcamelo.msgApp.services;

import com.mcamelo.msgApp.dtos.CommentDTO;
import com.mcamelo.msgApp.dtos.CommentRequest;
import com.mcamelo.msgApp.dtos.PostDTO;
import com.mcamelo.msgApp.dtos.UserDTO;
import com.mcamelo.msgApp.entities.Comment;
import com.mcamelo.msgApp.entities.Post;
import com.mcamelo.msgApp.entities.User;
import com.mcamelo.msgApp.repositories.CommentRepository;
import com.mcamelo.msgApp.repositories.PostRepository;
import com.mcamelo.msgApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Autowired
    public CommentRepository commentRepository;


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
    @Transactional
    public void remove(Long id){
        var post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Post not found"));
        for (Comment c : post.getComments()){
            commentRepository.delete(c);
        }
        postRepository.delete(post);
    }
    @Transactional
    public PostDTO createComment(CommentRequest commentRequest) {
        Post post = postRepository.getReferenceById(commentRequest.getIdPost());
        User authorComment = userRepository.getReferenceById(commentRequest.getComment().getAuthorComment().getId());
        Comment comment = new Comment();
        comment.setContent(commentRequest.getComment().getContent());
        comment.setUser(authorComment);
        comment.setPost(post);
        comment = commentRepository.save(comment);
        post.getComments().add(comment);
        post = postRepository.save(post);
        return new PostDTO(post);
    }
}
