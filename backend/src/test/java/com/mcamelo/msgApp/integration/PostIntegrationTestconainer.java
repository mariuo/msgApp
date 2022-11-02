package com.mcamelo.msgApp.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcamelo.msgApp.dtos.CommentRequest;
import com.mcamelo.msgApp.dtos.LikeRequest;
import com.mcamelo.msgApp.dtos.PostDTO;
import com.mcamelo.msgApp.dtos.UserDTO;
import com.mcamelo.msgApp.entities.User;
import com.mcamelo.msgApp.repositories.UserRepository;
import com.mcamelo.msgApp.utils.Factory;
import com.mcamelo.msgApp.utils.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@Transactional
@AutoConfigureMockMvc
public class PostIntegrationTestconainer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenUtil tokenUtil;

    private Long existingId;
    private Long existingId2;
    private Long nonExistingId;
    private String adminUsername;
    private String adminPassword;
    private String userUsername;
    private String userPassword;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        existingId2 = 2L;
        nonExistingId = 1000L;

        adminUsername = "mario";
        adminPassword = "123";
        userUsername = "felipe";
        userPassword = "123";
    }

    @Test
    public void getAllShouldReturnPageWhenAdminValid() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/post")
                        .header("Authorization", "Bearer " + accessToken)

                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
    @Test
    public void getAllShouldReturnPageWhenUserValid() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, userUsername, userPassword);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/post")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
    @Test
    public void findAllShouldReturnUnauthorizedWhenNotAuthenticaded() throws Exception{
        String accessToken = "oasijdOIAJSodijasd";
        UserDTO userDTO = Factory.createUserDTONormal();
        String jsonBody = objectMapper.writeValueAsString(userDTO);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/post")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isUnauthorized());
    }

    @Test
    public void insertPostShouldReturnOkWhenPostValid() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        PostDTO postDTO = Factory.createPostDTO();
        String jsonBody = objectMapper.writeValueAsString(postDTO);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/post/")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists());
   }

    @Test
    public void deleteShouldReturnNocontentWhenIdExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.delete("/post/{$id}", existingId2)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNoContent());
    }
    @Test
    public void deleteShouldReturnNotfoundWhenIdDoesNotExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);


        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.delete("/post/{$id}", nonExistingId)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }
    @Test
    public void insertLikeInPostShouldReturnCreatedWhenValid() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        LikeRequest likeDTO = Factory.createLike();
        String jsonBody = objectMapper.writeValueAsString(likeDTO);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/post/like")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }
    @Test
    public void deleteLikeShouldReturnOkWhenIdExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        LikeRequest likeDTO = Factory.createLike();

        String jsonBody = objectMapper.writeValueAsString(likeDTO);
        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.delete("/post/like/")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
    @Test
    public void insertCommentInPostShouldReturnOkWhenValid() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        CommentRequest commentDTO = Factory.createComment();
        String jsonBody = objectMapper.writeValueAsString(commentDTO);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/post/comment")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }
    @Test
    public void deleteCommentShouldReturnOkWhenIdExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        CommentRequest commentDTO = Factory.createComment();
        String jsonBody = objectMapper.writeValueAsString(commentDTO);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.delete("/post/comment")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

}
