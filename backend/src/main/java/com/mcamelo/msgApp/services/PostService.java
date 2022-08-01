package com.mcamelo.msgApp.services;

import com.mcamelo.msgApp.dtos.PostDTO;
import com.mcamelo.msgApp.entities.Comment;
import com.mcamelo.msgApp.entities.Post;
import com.mcamelo.msgApp.entities.User;
import com.mcamelo.msgApp.repositories.CommentRepository;
import com.mcamelo.msgApp.repositories.PostRepository;
import com.mcamelo.msgApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    public PostRepository postRepository;

    @Autowired
    public CommentRepository commentRepository;

    @Autowired
    public UserRepository userRepository;

    public List<PostDTO> getAllPosts(){
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
    }

    public PostDTO getById(Long id){
        Post post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Post not found") );
        List<Comment> commentList = commentRepository.findAllCommentsById(id);
        List<Long> likesIds = postRepository.findAllPostsLikesById(id);
        List<User> listUser = userRepository.findAllById(likesIds);
        return new PostDTO(post, commentList, listUser);
    }
}
