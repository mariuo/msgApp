package com.mcamelo.msgApp.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcamelo.msgApp.dtos.UserDTO;
import com.mcamelo.msgApp.entities.User;
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
public class UserIntegrationTestconainer {

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
    public void findAllShouldReturnPageWhenAdminValid() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        UserDTO userDTO = Factory.createUserDTO();
        String jsonBody = objectMapper.writeValueAsString(userDTO);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
    @Test
    public void findAllShouldReturnForbiddenWhenNormalUser() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, userUsername, userPassword);
        UserDTO userDTO = Factory.createUserDTONormal();
        String jsonBody = objectMapper.writeValueAsString(userDTO);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isForbidden());
    }
    @Test
    public void findByIdShouldReturnForbiddenWhenNormalUser() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, userUsername, userPassword);
        UserDTO userDTO = Factory.createUserDTONormal();
        String jsonBody = objectMapper.writeValueAsString(userDTO);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/user/{$id}", existingId)
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isForbidden());

    }
    @Test
    public void findByIdShouldReturnUserDTOWhenIdExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        UserDTO userDTO = Factory.createUserDTO();
        String jsonBody = objectMapper.writeValueAsString(userDTO);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/user/{$id}", existingId)
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingId));

    }
    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        UserDTO userDTO = Factory.createUserDTO();
        String jsonBody = objectMapper.writeValueAsString(userDTO);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/user/{$id}", nonExistingId)
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }
    @Test
    public void findByUsernameShouldReturnUserDTOWhenIdExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        UserDTO userDTO = Factory.createUserDTO();
        String jsonBody = objectMapper.writeValueAsString(userDTO);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.get("/user/username/{$userUsername}", userUsername)
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(userUsername));

    }
    @Test
    public void insertUserShouldReturnUserAndStatusCreatedWhenUserValid() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        User user = Factory.createNormalUser();
        String jsonBody = objectMapper.writeValueAsString(user);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/user/")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").value(user.getName()));
    }
    @Test
    public void insertUserShouldReturnUnprocessableEntityWhenDataInvalid() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);
        UserDTO dto = Factory.createUserDTONormal();
        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.post("/user/")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void deleteShouldReturnNocontentWhenIdExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);


        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.delete("/user/{$id}", existingId2)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNoContent());
    }
    @Test
    public void deleteShouldReturnNotfoundWhenIdDoesNotExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, adminUsername, adminPassword);


        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders.delete("/user/{$id}", nonExistingId)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }


}
