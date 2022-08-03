package com.mcamelo.msgApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeRequest {
    private Long idPost;
    private UserDTO user;
}
