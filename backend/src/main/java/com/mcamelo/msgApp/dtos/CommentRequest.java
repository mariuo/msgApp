package com.mcamelo.msgApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentRequest {
    private Long idPost;
    private CommentDTO comment;
}
