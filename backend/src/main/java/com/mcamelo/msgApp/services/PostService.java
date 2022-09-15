package com.mcamelo.msgApp.services;

import com.mcamelo.msgApp.dtos.CommentRequest;
import com.mcamelo.msgApp.dtos.LikeRequest;
import com.mcamelo.msgApp.dtos.PostDTO;
import com.mcamelo.msgApp.entities.Comment;
import com.mcamelo.msgApp.entities.Post;
import com.mcamelo.msgApp.entities.User;
import com.mcamelo.msgApp.repositories.CommentRepository;
import com.mcamelo.msgApp.repositories.PostRepository;
import com.mcamelo.msgApp.repositories.UserRepository;
import com.mcamelo.msgApp.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
            final PostDTO dto = new PostDTO(entity, p.getComments(), p.getLikedUsers());
            postDTOList.add(dto);
        }
        return postDTOList;
    }

    @Transactional
    public PostDTO getById(Long id){
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post not found") );
        return new PostDTO(post, post.getComments(), post.getLikedUsers());
    }

    @Transactional
    public PostDTO create(PostDTO postDTO) {
        Post entity = new Post();
        entity.setCreated(Instant.now());
        entity.setContent(postDTO.getContent());
        entity.setImageUrl(postDTO.getImageUrl());
        entity.setAuthor(userRepository.getOne(postDTO.getAuthor().getId()));
        entity = postRepository.save(entity);
        return new PostDTO(entity);
    }
    @Transactional
    public void remove(Long id){
        var post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post not found"));
        for (Comment c : post.getComments()){
            commentRepository.delete(c);
        }
        postRepository.delete(post);
    }
    @Transactional
    public PostDTO createComment(CommentRequest commentRequest) {
        Post post = postRepository.getOne(commentRequest.getIdPost());
        User authorComment = userRepository.getOne(commentRequest.getComment().getAuthorComment().getId());
        Comment comment = new Comment();
        comment.setContent(commentRequest.getComment().getContent());
        comment.setUser(authorComment);
        comment.setPost(post);
        comment = commentRepository.save(comment);
        post.getComments().add(comment);
        post = postRepository.save(post);
        return new PostDTO(post);
    }
    @Transactional
    public PostDTO deleteComment(CommentRequest commentRequest){
        Post post = postRepository.getOne(commentRequest.getIdPost());
        Comment comment = commentRepository.getOne(commentRequest.getComment().getId());
        List<Comment> listComments = post.getComments();
        listComments.stream().filter(x -> x.getId().equals(comment.getId())).findAny().map( x -> {
            listComments.remove(x);
            return x;
        });
        post.setComments(listComments);
        commentRepository.delete(comment);
        post = postRepository.save(post);
        return new PostDTO(post);
    }

    @Transactional
    public PostDTO createLike(LikeRequest likeRequest){
        Post post = postRepository.getOne(likeRequest.getIdPost());
        User authorLIke = userRepository.getOne(likeRequest.getUser().getId());
        var listLikesUpdated = post.getLikedUsers();
        listLikesUpdated.add(authorLIke);
        post = postRepository.save(post);
        return new PostDTO(post);
    }
    @Transactional
    public PostDTO deleteLike(LikeRequest likeRequest){
        Post post = postRepository.getOne(likeRequest.getIdPost());
        User authorLike = userRepository.getOne(likeRequest.getUser().getId());
        Set<User> userSet = post.getLikedUsers();
        userSet.remove(authorLike);
        post.setLikedUsers(userSet);
        post = postRepository.save(post);
        return new PostDTO(post);
    }

    public Flux<ServerSentEvent<List<PostDTO>>> streamPosts() {
        return Flux.interval(Duration.ofSeconds(2))
                .publishOn(Schedulers.boundedElastic())
                .map(sequence -> ServerSentEvent.<List<PostDTO>>builder().id(String.valueOf(sequence))
                        .event("post-list-event")
                        .data(getAllPosts())
                        .build());
    }
}
